package net.argus.chessplus.core.pieces;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board) {
		super(board, "knight");
	}

	@Override
	public List<MovePosibility> getPossibleMove() {
		List<MovePosibility> locs = new ArrayList<MovePosibility>();
		
		Location loc = getLocation().addY(2);
		if(loc != null) {
			testTo(loc.addX(-1), locs);
			testTo(loc.addX(1), locs);
		}
		
		loc = getLocation().addY(-2);
		if(loc != null) {
			testTo(loc.addX(-1), locs);
			testTo(loc.addX(1), locs);
		}
		
		loc = getLocation().addX(2);
		if(loc != null) {
			testTo(loc.addY(-1), locs);
			testTo(loc.addY(1), locs);
		}
		
		loc = getLocation().addX(-2);
		if(loc != null) {
			testTo(loc.addY(-1), locs);
			testTo(loc.addY(1), locs);
		}
		
		return locs;
	}
	
	private void testTo(Location loc, List<MovePosibility> locs) {
		if(loc == null)
			return;
		
		if(getBoard().getPiece(loc) == null)
			locs.add(new MovePosibility(loc));
		else if(!getBoard().getPiece(loc).getTeam().equals(getTeam()))
			locs.add(new MovePosibility(loc, getBoard().getPiece(loc)));
	
	}

}
