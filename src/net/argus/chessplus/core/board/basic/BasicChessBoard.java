package net.argus.chessplus.core.board.basic;

import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.board.ChessBoardDefault;
import net.argus.chessplus.core.pieces.Bishop;
import net.argus.chessplus.core.pieces.ChessPiece;
import net.argus.chessplus.core.pieces.King;
import net.argus.chessplus.core.pieces.Knight;
import net.argus.chessplus.core.pieces.Pawn;
import net.argus.chessplus.core.pieces.Queen;
import net.argus.chessplus.core.pieces.Rook;
import net.argus.chessplus.core.team.Team;

public class BasicChessBoard extends ChessBoardDefault {
	
	private BasicChessPromote promote;

	public BasicChessBoard(BasicChessPromote pomote) {
		super(8, 8, true, Team.WHITE, Team.BLACK);
		this.promote = pomote;
	}

	@Override
	public void init(ChessBoard cb) {
		cb.pop(new Rook(cb), "a1", Team.WHITE);
		cb.pop(new Knight(cb), "b1", Team.WHITE);
		cb.pop(new Bishop(cb), "c1", Team.WHITE);
		cb.pop(new Queen(cb), "d1", Team.WHITE);
		cb.pop(new King(cb), "e1", Team.WHITE);
		cb.pop(new Bishop(cb), "f1", Team.WHITE);
		cb.pop(new Knight(cb), "g1", Team.WHITE);
		cb.pop(new Rook(cb), "h1", Team.WHITE);
		
		cb.pop(new Pawn(cb), "a2", Team.WHITE);
		cb.pop(new Pawn(cb), "b2", Team.WHITE);
		cb.pop(new Pawn(cb), "c2", Team.WHITE);
		cb.pop(new Pawn(cb), "d2", Team.WHITE);
		cb.pop(new Pawn(cb), "e2", Team.WHITE);
		cb.pop(new Pawn(cb), "f2", Team.WHITE);
		cb.pop(new Pawn(cb), "g2", Team.WHITE);
		cb.pop(new Pawn(cb), "h2", Team.WHITE);
		
		
		cb.pop(new King(cb), "e8", Team.BLACK);
		cb.pop(new Rook(cb), "a8", Team.BLACK);
		cb.pop(new Knight(cb), "b8", Team.BLACK);
		cb.pop(new Bishop(cb), "c8", Team.BLACK);
		cb.pop(new Queen(cb), "d8", Team.BLACK);
		cb.pop(new King(cb), "e8", Team.BLACK);
		cb.pop(new Bishop(cb), "f8", Team.BLACK);
		cb.pop(new Knight(cb), "g8", Team.BLACK);
		cb.pop(new Rook(cb), "h8", Team.BLACK);
		
		cb.pop(new Pawn(cb), "a7", Team.BLACK);
		cb.pop(new Pawn(cb), "b7", Team.BLACK);
		cb.pop(new Pawn(cb), "c7", Team.BLACK);
		cb.pop(new Pawn(cb), "d7", Team.BLACK);
		cb.pop(new Pawn(cb), "e7", Team.BLACK);
		cb.pop(new Pawn(cb), "f7", Team.BLACK);
		cb.pop(new Pawn(cb), "g7", Team.BLACK);
		cb.pop(new Pawn(cb), "g7", Team.BLACK);
		
		
	}

	@Override
	public ChessPiece promote(ChessBoard board, ChessPiece piece) {
		ChessPiece p = promote.promote(board, piece);
		p.setTeam(piece.getTeam());
		return p;
	}

}