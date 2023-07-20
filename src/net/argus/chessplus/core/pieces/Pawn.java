package net.argus.chessplus.core.pieces;


import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.board.history.HistoryItem;
import net.argus.chessplus.core.team.Direction;

public class Pawn extends ChessPiece {

	public Pawn(ChessBoard board) {
		super(board, "pawn");
	}

	@Override
	public List<MovePosibility> getPossibleMove() {
		List<MovePosibility> locs = new ArrayList<MovePosibility>();
		
		Location up1 = up(1);
		if(!isBusy(up1)) {
			locs.add(new MovePosibility(up1, true));
			if(!isAlreadyMove() && !isBusy(up(2)))
				locs.add(new MovePosibility(up(2), true));
		}
		
		ChessPiece piece = null;
		
		Location loc = side(1);
		if(loc != null) {
			loc = loc.up(1, getTeam().getDir());
			if(loc != null) {
				piece = getBoard().getPiece(loc);
				if(piece != null)
					locs.add(new MovePosibility(loc, piece));
			}
		}
	
		loc = side(-1);
		if(loc != null) {
			loc = loc.up(1, getTeam().getDir());
			if(loc != null) {
				piece = getBoard().getPiece(loc);
				if(piece != null)
					locs.add(new MovePosibility(loc, piece));
			}
		}
		
		HistoryItem item = getBoard().getHistory().getLast();
		if(item == null)
			return locs;
		
		if(item.getPiece() instanceof Pawn) {
			ChessPiece p = item.getPiece();
			switch (getTeam().getDir()) {
				case NORTH:
					if(p.getTeam().getDir() == Direction.SOUTH) {
						if(item.getBefore().getY() - item.getAfter().getY() == 2) {
							if(getBoard().getPiece(side(1)) != null || getBoard().getPiece(side(-1)) != null) {
								if(p.equals(getBoard().getPiece(side(1))))
									locs.add(new MovePosibility(side(1).up(1, getTeam().getDir()), p));
								else if(p.equals(getBoard().getPiece(side(-1))))
									locs.add(new MovePosibility(side(-1).up(1, getTeam().getDir()), p));
							}
						}
					}
					break;
				case SOUTH:
					if(p.getTeam().getDir() == Direction.NORTH) {
						if(item.getAfter().getY() - item.getBefore().getY() == 2) {
							if(getBoard().getPiece(side(1)) != null || getBoard().getPiece(side(-1)) != null) {
								if(p.equals(getBoard().getPiece(side(1))))
									locs.add(new MovePosibility(side(1).up(1, getTeam().getDir()), p));
								else if(p.equals(getBoard().getPiece(side(-1))))
									locs.add(new MovePosibility(side(-1).up(1, getTeam().getDir()), p));
							}
						}
					}
					break;
				case WEST:
					if(p.getTeam().getDir() == Direction.EAST) {
						if(item.getAfter().getX() - item.getBefore().getX() == 2) {
							if(getBoard().getPiece(side(1)) != null || getBoard().getPiece(side(-1)) != null) {
								if(p.equals(getBoard().getPiece(side(1))))
									locs.add(new MovePosibility(side(1).up(1, getTeam().getDir()), p));
								else if(p.equals(getBoard().getPiece(side(-1))))
									locs.add(new MovePosibility(side(-1).up(1, getTeam().getDir()), p));
							}
						}
					}
					break;
				case EAST:
					if(p.getTeam().getDir() == Direction.WEST) {
						if(item.getBefore().getX() - item.getAfter().getX() == 2) {
							if(getBoard().getPiece(side(1)) != null || getBoard().getPiece(side(-1)) != null) {
								if(p.equals(getBoard().getPiece(side(1))))
									locs.add(new MovePosibility(side(1).up(1, getTeam().getDir()), p));
								else if(p.equals(getBoard().getPiece(side(-1))))
									locs.add(new MovePosibility(side(-1).up(1, getTeam().getDir()), p));
							}
						}
					}
					break;
	
			}
		}
		
		return locs;
	}
	
	public Location up(int up) {
		return getLocation().up(up, getTeam().getDir());
	}
	
	public Location side(int side) {
		return getLocation().side(side, getTeam().getDir());
	}

}
