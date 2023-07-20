package net.argus.chessplus.core.pieces;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.team.Team;

public class King extends ChessPiece {

	public King(ChessBoard board) {
		super(board, "king");
	}

	@Override
	public List<MovePosibility> getPossibleMove() {
		List<MovePosibility> locs = new ArrayList<MovePosibility>();
		
		Location loc = getLocation().addY(1);
		if(loc != null) {
			testTo(loc, locs);
			testTo(loc.addX(-1), locs);
			testTo(loc.addX(1), locs);
		}
		
		loc = getLocation().addY(-1);
		if(loc != null) {
			testTo(loc, locs);
			testTo(loc.addX(-1), locs);
			testTo(loc.addX(1), locs);
		}
		testTo(getLocation().addX(-1), locs);
		testTo(getLocation().addX(1), locs);

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
	
	public static boolean isCheck(Location loc, Team yTeam, ChessBoard board) {
		return sourceCheck(loc, yTeam, board).size() > 0;
	}
	
	public static boolean isCheck(Location loc, Team yTeam, List<ChessPiece> pieces) {
		return sourceCheck(loc, yTeam, pieces).size() > 0;
	}
	
	public static List<ChessPiece> sourceCheck(Location loc, Team yTeam, ChessBoard board) {
		return sourceCheck(loc, yTeam, board.getActivePieces());
	}
	
	public static List<ChessPiece> sourceCheck(Location loc, Team yTeam, List<ChessPiece> board) {
		List<ChessPiece> sources = new ArrayList<ChessPiece>();
		for(ChessPiece p : board) {
			if(p.getTeam().equals(yTeam))
				continue;
			
			for(MovePosibility mp : p.getPossibleMove())
				if(mp.getLocation().equals(loc) && !mp.isNotAgressive()) {
					sources.add(p);
				}
		}
		return sources;
	}

}
