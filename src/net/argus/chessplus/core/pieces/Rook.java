package net.argus.chessplus.core.pieces;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;

public class Rook extends ChessPiece {

	public Rook(ChessBoard board) {
		super(board, "rook");
	}

	@Override
	public List<MovePosibility> getPossibleMove() {
		List<MovePosibility> locs = new ArrayList<MovePosibility>();
		
		// R---->
		for(int x = 1; x < getBoard().getWigth() - getLocation().getX(); x++) {
			Location loc = getLocation().addX(x);
			if(loc == null)
				break;
			
			ChessPiece piece = getBoard().getPiece(loc);
			if(piece != null) {
				locs.add(new MovePosibility(loc, piece));
				break;
			}
			
			locs.add(new MovePosibility(loc));
		}
		
		// <----R
		for(int x = 1; x <= getLocation().getX(); x++) {
			Location loc = getLocation().addX(-x);
			if(loc == null)
				break;
			
			ChessPiece piece = getBoard().getPiece(loc);
			if(piece != null) {
				locs.add(new MovePosibility(loc, piece));
				break;
			}
			
			locs.add(new MovePosibility(loc));
		}
		
		/*
		 * R
		 * |
		 * |
		 *\./
		 *
		 */
		for(int y = 1; y <= getLocation().getY(); y++) {
			Location loc = getLocation().addY(-y);
			if(loc == null)
				break;
			
			ChessPiece piece = getBoard().getPiece(loc);
			if(piece != null) {
				locs.add(new MovePosibility(loc, piece));
				break;
			}
			
			locs.add(new MovePosibility(loc));
		}
		
		
		/*
		 * /.\
		 *  |
		 *  |
		 *  R
		 *
		 */
		for(int y = 1; y < getBoard().getHeight() - getLocation().getY(); y++) {
			Location loc = getLocation().addY(y);
			if(loc == null)
				break;
			
			ChessPiece piece = getBoard().getPiece(loc);
			if(piece != null) {
				locs.add(new MovePosibility(loc, piece));
				break;
			}
			
			locs.add(new MovePosibility(loc));
		}
		
		
		return locs;
	}

}
