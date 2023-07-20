package net.argus.chessplus.core.event;

import net.argus.event.Event;

public class EventChess extends Event<ChessListener> {
	
	public static final int CHECK = 0;  
	public static final int END_GAME = 1; 
	public static final int MOVE = 2;
	public static final int CASTEL = 3;  
	
	@Override
	public void event(ChessListener listeners, int event, Object... objs) {
		switch(event) {
			case CHECK:
				listeners.check((ChessEvent) objs[0]);
				break;
			case END_GAME:
				listeners.endGame((ChessEvent) objs[0]);
				break;
			case MOVE:
				listeners.move((ChessEvent) objs[0]);
				break;
			case CASTEL:
				listeners.castel((ChessEvent) objs[0]);
				break;
		}
	}

}
