package net.argus.chessplus.core.pieces;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.MovePosibility;
import net.argus.chessplus.core.board.ChessBoard;

public class Bishop extends ChessPiece {

	public Bishop(ChessBoard board) {
		super(board, "bishop");
	}

	@Override
	public List<MovePosibility> getPossibleMove() {
		List<MovePosibility> locs = new ArrayList<MovePosibility>();

		boolean top = true;
		boolean bottom = true;
		for(int x = 1; x < getBoard().getWigth() - getLocation().getX(); x++) {
			if(top) {
				Location t = getLocation().addX(x).addY(x);
				if(t != null) {
					ChessPiece piece = getBoard().getPiece(t);
					if(piece != null) {
						locs.add(new MovePosibility(t, piece));
						top = false;
					}else
						locs.add(new MovePosibility(t));
				}
					
			}
			if(bottom) {
				Location b = getLocation().addX(x).addY(-x);
				if(b == null)
					continue;
				ChessPiece piece = getBoard().getPiece(b);
				if(piece != null) {
					locs.add(new MovePosibility(b, piece));
					bottom = false;
				}else
					locs.add(new MovePosibility(b));
			}
		}
		top = true;
		bottom = true;
		for(int x = 1; x <= getLocation().getX(); x++) {
			if(top) {
				Location t = getLocation().addX(-x).addY(x);
				if(t != null) {
					ChessPiece piece = getBoard().getPiece(t);
					if(piece != null) {
						locs.add(new MovePosibility(t, piece));
						top = false;
					}else
						locs.add(new MovePosibility(t));
				}
			}
			if(bottom) {
				Location b = getLocation().addX(-x).addY(-x);
				if(b == null)
					continue;
				ChessPiece piece = getBoard().getPiece(b);
				if(piece != null) {
					locs.add(new MovePosibility(b, piece));
					bottom = false;
				}else
					locs.add(new MovePosibility(b));
			}
		}
		
		return locs;
	}

}
