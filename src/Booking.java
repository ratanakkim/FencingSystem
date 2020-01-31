import java.util.ArrayList;

public class Booking {
	private int week;
	private ArrayList<Team> teams;
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public ArrayList<Team> getTeam() {
		return teams;
	}
	public void removeTeam (Team aTeam) {
		for (Team t : teams) {
			if (t.equals(aTeam)) {
				teams.remove(t);
			}
		}
	}
	public void setTeam(ArrayList<Team> team) {
		this.teams = team;
	}
}
