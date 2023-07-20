package net.argus.chessplus.core;

import net.argus.chessplus.core.pieces.ChessPiece;

public class MovePosibility {
	
	private Location loc;
	private ChessPiece current;
	
	private boolean notAgressive = false;
		
	public MovePosibility(Location loc) {
		this.loc = loc;
	}
	
	public MovePosibility(Location loc, boolean notAgressive) {
		this.loc = loc;
		this.notAgressive = notAgressive;
	}
	
	public MovePosibility(Location loc, ChessPiece current) {
		this.loc = loc;
		this.current = current;
	}
	
	public MovePosibility(Location loc, ChessPiece current, boolean notAgressive) {
		this.loc = loc;
		this.current = current;
		this.notAgressive = notAgressive;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public ChessPiece getCurrent() {
		return current;
	}
	
	public boolean isNotAgressive() {
		return notAgressive;
	}
	
	@Override
	public String toString() {
		String str = loc.toString();
		if(current != null)
			str += "->" + current.getClass().getSimpleName();
		return str;
	}

}
