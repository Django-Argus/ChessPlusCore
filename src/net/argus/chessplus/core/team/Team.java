package net.argus.chessplus.core.team;

public class Team {
	
	public static Team WHITE = new Team("white", 0, Direction.NORTH);
	public static Team BLACK = new Team("black", 1, Direction.SOUTH);
	
	private String name;
	private int priority;
	private Direction dir;

	public Team(String name, int priority, Direction dir) {
		this.name = name;
		this.priority = priority;
		this.dir = dir;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public Direction getDir() {
		return dir;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Team)
			return ((Team) obj).getName().toLowerCase().equals(name.toLowerCase());

		return false;
	}

	@Override
	public String toString() {
		return "team@" + name;
	}
	
}
