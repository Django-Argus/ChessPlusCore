package net.argus.chessplus.core.board.history;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.pieces.ChessPiece;

public class HistoryItem {
	
	private Location before, after;
	private ChessPiece piece;
	
	public HistoryItem(ChessPiece piece, Location before, Location after) {
		this.piece = piece;
		this.before = before;
		this.after = after;
	}
	
	public Location getAfter() {
		return after;
	}
	
	public Location getBefore() {
		return before;
	}
	
	public ChessPiece getPiece() {
		return piece;
	}

}
