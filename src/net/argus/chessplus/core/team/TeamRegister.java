package net.argus.chessplus.core.team;

import java.util.ArrayList;
import java.util.List;

public class TeamRegister {
	
	private List<Team> teams = new ArrayList<Team>();
	
	public void add(Team team) {
		teams.add(team);
	}
	
	public List<Team> getTeams() {
		return teams;
	}

}
