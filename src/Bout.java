
public class Bout {
	private Team team1;
	private Team team2;
	private Team referee;
	private  Team winner = referee; // Default winner is referee
	private int t1Score = 0;
	private int t2Score = 0;
	
	public Bout(Team t1, Team t2, Team ref) {
		this.team1 = t1;
		this.team2 = t2;
		this.referee = ref;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public Team getReferee() {
		return referee;
	}
	public void setReferee(Team referee) {
		this.referee = referee;
	}
	public Team getWinner() {
		return winner;
	}
	public void setWinner(Team winner) {
		this.winner = winner;
	}
	public int getT1Score() {
		return t1Score;
	}
	public void setT1Score(int t1Score) {
		this.t1Score = t1Score;
	}
	public int getT2Score() {
		return t2Score;
	}
	public void setT2Score(int t2Score) {
		this.t2Score = t2Score;
	}
	
	@Override
	public String toString(){
		return("Team 1's score: "+ t1Score+" Team 2's score: "+t2Score+ "Winner is : "+winner );
	}
}
