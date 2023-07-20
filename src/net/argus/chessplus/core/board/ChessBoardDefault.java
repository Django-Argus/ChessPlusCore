package net.argus.chessplus.core.board;

import net.argus.chessplus.core.pieces.ChessPiece;
import net.argus.chessplus.core.team.Team;

public abstract class ChessBoardDefault {
	
	private int w, h;
	private Team[] teams;
	private boolean castle;
	
	public ChessBoardDefault(int w, int h, boolean castle, Team ... teams) {
		if(w < 1 && h  < 1)
			throw new IllegalArgumentException("w and h must be superior to 0");
		if(teams.length < 1)
			throw new IllegalArgumentException("no team register");
		
		this.w = w;
		this.h = h;
		this.teams = teams;
		this.castle = castle;
	}
	
	public abstract void init(ChessBoard cb);
	
	public abstract ChessPiece promote(ChessBoard board, ChessPiece piece);

	
	public int getHeight() {
		return h;
	}
	
	public int getWidth() {
		return w;
	}
	
	public Team[] getTeams() {
		return teams;
	}
	
	public boolean isCastle() {
		return castle;
	}
}
