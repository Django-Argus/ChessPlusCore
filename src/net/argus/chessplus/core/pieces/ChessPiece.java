package net.argus.chessplus.core.pieces;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.board.MoveReturn;
import net.argus.chessplus.core.team.Team;

public abstract class ChessPiece {
	
	private String name;
	
	private Location loc;
	private Team team;
	
	private ChessBoard board;
	
	private boolean alreadyMove = false;
	
	public ChessPiece(ChessBoard board, String name) {
		this.board = board;
		this.name = name;
	}
	
	public MoveReturn move(Location end) {
		List<MovePosibility> locs = getRealPossibleMove();
		
		if(locs == null)
			return new MoveReturn(false);
		
		for(MovePosibility mp : locs)
			if(mp.getLocation().equals(end) && mp.getLocation().getX() < board.getWigth() && mp.getLocation().getY() < board.getHeight()) {
				if(mp.getCurrent() != null && mp.getCurrent().getTeam().equals(getTeam()))
					return new MoveReturn(false);
				
				boolean c = false;
				if(mp.getCurrent() != null) {
					getBoard().catchPiece(mp.getCurrent());
					c = true;
				}
				alreadyMove = true;
				this.loc = end;
				return new MoveReturn(true, c, mp.getCurrent());
			}

		return new MoveReturn(false);
	}
	
	public abstract List<MovePosibility> getPossibleMove();
	
	public List<MovePosibility> getRealPossibleMove() {
		King k = getBoard().getKing(getTeam());
		List<ChessPiece> sources = King.sourceCheck(k.getLocation(), getTeam(), getBoard());
		if(sources.size() > 1 && !(this instanceof King))
			return null;
		
		/** verifie si le roi n'est pas echec apres le coup **/
		List<MovePosibility> locs = getPossibleMove();
		
		List<MovePosibility> temp = new ArrayList<MovePosibility>();
		Location loc = getLocation();
		for(MovePosibility mp : locs) {
			if(mp.getCurrent() != null && mp.getCurrent().getTeam().equals(getTeam()))
				continue;

			if(mp.getLocation().getX() >= board.getWigth() || mp.getLocation().getX() < 0
					|| mp.getLocation().getY() >= board.getHeight() || mp.getLocation().getY() < 0)
				continue;
			
			setLocation(mp.getLocation());
			if(!King.isCheck(k.getLocation(), getTeam(), getBoard()))
				temp.add(mp);
		
		}
		setLocation(loc);
		return temp;
	}
	
	public boolean isBusy(Location loc) {
		return board.getPiece(loc) != null;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public boolean isAlreadyMove() {
		return alreadyMove;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + loc.toString();
	}
	
}

