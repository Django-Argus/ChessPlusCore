package net.argus.chessplus.core.event;

import net.argus.util.Listener;

public interface ChessListener extends Listener {
	
	public void check(ChessEvent e);
	public void endGame(ChessEvent e);
	public void move(ChessEvent e);
	public void castel(ChessEvent e);
	
}
