package net.argus.chessplus.core.board;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.history.History;
import net.argus.chessplus.core.event.ChessEvent;
import net.argus.chessplus.core.event.ChessListener;
import net.argus.chessplus.core.event.EventChess;
import net.argus.chessplus.core.pieces.ChessPiece;
import net.argus.chessplus.core.pieces.King;
import net.argus.chessplus.core.pieces.Pawn;
import net.argus.chessplus.core.pieces.Rook;
import net.argus.chessplus.core.team.Direction;
import net.argus.chessplus.core.team.Team;
import net.argus.chessplus.core.team.TeamRegister;

public class ChessBoard {
	
	private Cell[][] cells;
	
	
	private ChessBoardDefault def;
	
	private TeamRegister teamRegister = new TeamRegister();
	private History history = new History();
	
	private List<ChessPiece> activePieces = new ArrayList<ChessPiece>();
	private List<ChessPiece> catchPieces = new ArrayList<ChessPiece>();
	
	private EventChess event = new EventChess();
	
	private boolean castle = true;
	
	public ChessBoard(ChessBoardDefault boardDefault) {
		this.def = boardDefault;
		
		cells = new Cell[boardDefault.getWidth()][boardDefault.getHeight()];
		
		for(Team t : boardDefault.getTeams())
			teamRegister.add(t);
		
		boardDefault.init(this);
	}
	
	public void pop(ChessPiece cp, String loc, Team team) {
		pop(cp, new Location(loc), team);
	}
	
	public void pop(ChessPiece cp, Location loc, Team team) {
		cp.setLocation(loc);
		cp.setTeam(team);
		
		activePieces.add(cp);
	}
	
	public void switchPiece(ChessPiece current, ChessPiece newPiece) {
		newPiece.setLocation(current.getLocation());
		
		activePieces.add(newPiece);
		activePieces.remove(current);
	}
	
	public MoveReturn move(Location begin, Location end) {
		return move(getPiece(begin), end);
	}
	
	public MoveReturn move(ChessPiece piece, Location end) {
		if(piece == null)
			return new MoveReturn(false);
		
		Location temp = new Location(piece.getLocation().getLocation());
		
		MoveReturn mr = null;
		int e = EventChess.CASTEL;
		if(piece instanceof King && getPiece(end) != null && getPiece(end) instanceof Rook && getPiece(end).getTeam().equals(piece.getTeam()))
			mr = castle((King) piece, (Rook) getPiece(end));
		else {
			mr = piece.move(end);
			e = EventChess.MOVE;
		}
		
		if(piece instanceof Pawn) {
			boolean ok = false;
			if(piece.getTeam().getDir() == Direction.NORTH && piece.getLocation().getY() == getHeight()-1)
				ok = true;
			else if(piece.getTeam().getDir() == Direction.SOUTH && piece.getLocation().getY() == 0)
				ok = true;
			else if(piece.getTeam().getDir() == Direction.EAST && piece.getLocation().getX() == getWigth()-1)
				ok = true;
			else if(piece.getTeam().getDir() == Direction.WEST && piece.getLocation().getX() == 0)
				ok = true;
			
			if(ok) {
				ChessPiece nP = def.promote(this, piece);
				switchPiece(piece, nP);
			}
		}
		
		Location loc = new Location(piece.getLocation().getLocation());
		if(mr.isOk()) {
			history.add(piece, loc, end);
			
			if(e == EventChess.CASTEL)
				event.startEvent(e, new ChessEvent(piece, getPiece(end), temp, false));
			else if(mr.isCaught())
				event.startEvent(e, new ChessEvent(piece, mr.getPiece(), temp, false));
			else
				event.startEvent(e, new ChessEvent(piece, null, temp, false));
			
			for(Team t : teamRegister.getTeams()) {
				if(King.isCheck(getKing(t).getLocation(), t, this))
					event.startEvent(EventChess.CHECK, new ChessEvent(getKing(t), null, temp, false));
			}
			
			int[] is= new int[teamRegister.getTeams().size()];
			for(ChessPiece p : activePieces) {
				List<MovePosibility> mp = p.getRealPossibleMove();
				if(mp == null)
					continue;

				if(mp.size() > 0) {
					is[teamRegister.getTeams().indexOf(p.getTeam())] += 1;
				}
				
			}
			
			for(int i = 0; i < is.length; i++) {
				if(is[i] == 0) {
					boolean c = King.isCheck(getKing(teamRegister.getTeams().get(i)).getLocation(), teamRegister.getTeams().get(i), this);
					event.startEvent(EventChess.END_GAME, new ChessEvent(getKing(teamRegister.getTeams().get(i)), null, temp, c));
				}
			}
			
			
		}
		
		return mr;
			
	}
	
	public MoveReturn castle(Location k, Location r) {
		ChessPiece kp = getPiece(k);
		ChessPiece rp = getPiece(r);
		if(kp != null && kp instanceof King && rp != null && rp instanceof Rook)
			return castle((King) kp, (Rook) rp);
		return new MoveReturn(false);
			
	}
	
	public MoveReturn castle(King king, Rook rook) {
		if(king.isAlreadyMove() || rook.isAlreadyMove() || !castle || king.getLocation().getX() != rook.getLocation().getX() && king.getLocation().getY() != rook.getLocation().getY())
			return new MoveReturn(false);
		
		if(king.getLocation().getY() == rook.getLocation().getY()) {
			if(king.getLocation().getX() < rook.getLocation().getX()) {
				int dist = rook.getLocation().getX() - king.getLocation().getX();
				
				for(int i = 1; i < dist; i++) {
					if(getPiece(king.getLocation().addX(i)) != null)
						return new MoveReturn(false);
				}
				for(int i = 0; i < 3; i++) {
					if(King.isCheck(king.getLocation().addX(i), king.getTeam(), king.getBoard()))
						return new MoveReturn(false);
				}
				
				king.setLocation(king.getLocation().addX(2));
				rook.setLocation(king.getLocation().addX(-1));
				return new MoveReturn(true);
			}else {
				int dist = king.getLocation().getX() - rook.getLocation().getX();
				for(int i = 1; i < dist; i++) {
					if(getPiece(king.getLocation().addX(-i)) != null)
						return new MoveReturn(false);
				}
				for(int i = 0; i < 3; i++) {
					System.out.println(king.getLocation().addX(-i));
					if(King.isCheck(king.getLocation().addX(-i), king.getTeam(), king.getBoard()))
						return new MoveReturn(false);
				}
			}

			king.setLocation(king.getLocation().addX(-2));
			rook.setLocation(king.getLocation().addX(1));
			return new MoveReturn(true);
		}else {
			if(king.getLocation().getY() < rook.getLocation().getY()) {
				int dist = rook.getLocation().getY() - king.getLocation().getY();
				
				for(int i = 1; i < dist; i++) {
					if(getPiece(king.getLocation().addY(i)) != null)
						return new MoveReturn(false);
				}
				for(int i = 0; i < 3; i++)
					if(King.isCheck(king.getLocation().addY(i), king.getTeam(), king.getBoard()))
						return new MoveReturn(false);
				
				king.setLocation(king.getLocation().addY(2));
				rook.setLocation(king.getLocation().addY(-1));
				return new MoveReturn(true);
			}else {
				int dist = king.getLocation().getY() - rook.getLocation().getY();

				for(int i = 1; i < dist; i++) {
					if(getPiece(king.getLocation().addY(-i)) != null)
						return new MoveReturn(false);
				}
				for(int i = 0; i < 3; i++)
					if(King.isCheck(king.getLocation().addY(-i), king.getTeam(), king.getBoard()))
						return new MoveReturn(false);
				
				king.setLocation(king.getLocation().addY(-2));
				rook.setLocation(king.getLocation().addY(1));
				return new MoveReturn(true);
			}
		}
	}
	
	public ChessPiece getPiece(Location loc) {
		for(ChessPiece p : activePieces) {
			if(p.getLocation().equals(loc))
				return p;
		}
		return null;
	}
	
	public King getKing(Team team) {
		for(ChessPiece p : activePieces)
			if(p instanceof King && p.getTeam().equals(team))
				return (King) p;
		return null;
	}
	
	public void catchPiece(ChessPiece piece) {
		catchPieces.add(piece);
		activePieces.remove(piece);
	}
	
	public List<ChessPiece> getCatchPieces() {
		return catchPieces;
	}
	
	public List<ChessPiece> getActivePieces() {
		return activePieces;
	}
	
	public void addChessListener(ChessListener listener) {
		event.addListener(listener);
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	
	public History getHistory() {
		return history;
	}
	
	public int getWigth() {
		return cells.length;
	}
	
	public int getHeight() {
		return cells[0].length;
	}

}
