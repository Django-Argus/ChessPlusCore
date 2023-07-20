package net.argus.chessplus.core.board.basic;

import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.pieces.ChessPiece;

public interface BasicChessPromote {
	
	public ChessPiece promote(ChessBoard board, ChessPiece piece);

}
