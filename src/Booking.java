import java.util.ArrayList;

public class Booking {
	private int week;
	private ArrayList<Team> teams = new ArrayList<Team>();
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public ArrayList<Team> getTeam() {
		return teams;
	}
	public String getTeamsString() {
		String ret = "";
		for (Team t : this.teams) {
			ret +=t + ",";
		}
		
		return ret;
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
	
	public void addTeam(Team ateam) {
		this.teams.add(ateam);
	}
	@Override
	public String toString() {
		String ret = "Booking for week: "+week+" for " +teams.size() +" teams.";
		return ret;
		
	}
}
