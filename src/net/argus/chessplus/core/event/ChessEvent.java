package net.argus.chessplus.core.event;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.pieces.ChessPiece;

public class ChessEvent {
	
	private ChessPiece source, target;
	private Location begin;
	private boolean mate;
	
	public ChessEvent(ChessPiece source, ChessPiece target, Location begin, boolean mate) {
		this.source = source;
		this.target = target;
		this.begin = begin;
		this.mate = mate;
	}
	
	public ChessPiece getSource() {
		return source;
	}
	
	public ChessPiece getTarget() {
		return target;
	}
	
	public Location getBegin() {
		return begin;
	}
	
	public boolean isMate() {
		return mate;
	}

}
