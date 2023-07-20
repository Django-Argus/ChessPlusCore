package net.argus.chessplus.core.board;

import net.argus.chessplus.core.pieces.ChessPiece;

public class MoveReturn {
	
	private boolean ok, caught;
	
	private ChessPiece piece;
	
	public MoveReturn(boolean ok, boolean caught, ChessPiece piece) {
		this.ok = ok;
		this.caught = caught;
		
		this.piece = piece;
	}
	
	public MoveReturn(boolean ok) {
		this.ok = ok;
	}
	
	
	public boolean isOk() {
		return ok;
	}
	
	public boolean isCaught() {
		return caught;
	}
	
	public ChessPiece getPiece() {
		return piece;
	}

}
