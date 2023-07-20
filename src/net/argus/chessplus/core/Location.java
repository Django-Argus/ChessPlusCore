package net.argus.chessplus.core;

import net.argus.chessplus.core.team.Direction;

public class Location {
	
	private String loc;
	
	public Location(String loc) {
		if(loc == null || loc.length() < 2)
			throw new IllegalArgumentException();
		if(Character.isAlphabetic(loc.charAt(1)))
			throw new IllegalArgumentException();
		
		this.loc = loc.toLowerCase();
	}
	
	public Location addX(int x) {
		if(x + getX() > 25 || x + getX() < 0)
			return null;
		
		return new Location((char) (x + getX() + 97) + getNumber());
	}
	
	public Location addY(int y) {
		if(getY() + y < 0)
			return null;
		return new Location(getLettres() + (getY() + 1 + y));
	}
	
	/*public int getX() {
		String lettre = getLettres();
		int x = -1;
		for(int i = 0; i < lettre.length(); i++) {
			char c = lettre.charAt(i);
			x += ((int) c - 97) * Math.pow(26, lettre.length() - 1 - i);
			x += Math.pow(26, i);
		}
		return x;
	}*/
	
	public int getX() {
		String lettre = loc.substring(0, 1);
		return (int) lettre.charAt(0) - 97;
	}
	
	public int getY() {
		String number = getNumber();
		
		return Integer.valueOf(number) - 1;
	}
	
	public String getLettres() {
		return loc.substring(0, indexNumber());
	}
	
	public String getNumber() {
		return loc.substring(indexNumber());
	}
	
	private int indexNumber() {
		for(int i = 0; i < loc.length(); i++)
			if(Character.isDigit(loc.charAt(i)))
				return i;
		return -1;
	}
	
	public String getLocation() {
		return loc;
	}
	
	public Location up(int up, Direction dir) {
		switch(dir) {
			case NORTH:
				return addY(up);
			case SOUTH:
				return addY(-up);
			case EAST:
				return addX(up);
			case WEST:
				return addX(-up);
		}
		return null;
	}
	
	public Location side(int side, Direction dir) {
		switch(dir) {
			case NORTH:
				return addX(side);
			case SOUTH:
				return addX(-side);
			case EAST:
				return addY(-side);
			case WEST:
				return addY(side);
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof String) {
			if(loc.toLowerCase().equals(((String) obj).toLowerCase()))
				return true;
		}else if(obj instanceof Location) {
			if(loc.toLowerCase().equals(((Location) obj).getLocation().toLowerCase()))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "location@" + loc;
	}
	
}
