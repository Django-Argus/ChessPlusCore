package net.argus.chessplus.core.board.history;

import java.util.ArrayList;
import java.util.List;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.pieces.ChessPiece;

public class History {
	
	private List<HistoryItem> items = new ArrayList<HistoryItem>();
	
	public void add(ChessPiece piece, Location before, Location after) {
		add(new HistoryItem(piece, before, after));
	}
	
	public void add(HistoryItem item) {
		items.add(item);
	}
	
	public List<HistoryItem> getItems() {
		return items;
	}
	
	public HistoryItem getLast() {
		if(items.size() <= 0)
			return null;
		
		return items.get(items.size()-1);
	}
	
	public ChessPiece getLastPiece() {
		if(items.size() <= 0)
			return null;
		
		return items.get(items.size()-1).getPiece();
	}

}
