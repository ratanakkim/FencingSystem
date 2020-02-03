import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Poule {
	private ArrayList<Bout> myBouts = new ArrayList<Bout>();
	private ArrayList<Team> teams = new ArrayList<Team>();

	public ArrayList<Bout> getMyBouts() {
		System.out.println();
		return myBouts;
	}
	public void addBouts(Bout aBout) {
		myBouts.add(aBout);
	}
	public void setMyBouts(ArrayList<Bout> myBouts) {
		this.myBouts = myBouts;
	}
	/**
	 * Take a list of possible bouts for an age group, and the teams
	 * form n number of bouts without referees where n is the maximum number of poule possible given 
	 * the number of teams
	 * Grab n number of random team to be referee 
	 * @param ageGroup
	 * @param bouts
	 */
	public void formBouts( ArrayList<Team> ageGroup, LinkedList<Bout> bouts) {
		int possPoule = ageGroup.size()/3;
		Bout curr;
		for (int i = 0; i < possPoule; i++) {
			curr = bouts.peek();
			/*
			 * if any of the team is taken,
			 * don't use this bout; and change curr
			 * to a random bout
			 */
			//Check while either teams are taken or there are bouts left 
			while ( curr != null && (curr.getTeam1().getCounted() == true || curr.getTeam2().getCounted() == true) && bouts.size() > 0) {
				Collections.shuffle(bouts);
				curr = bouts.peek();
			}
			Team team1 = curr.getTeam1();
			Team team2 = curr.getTeam2();
			team1.setCounted(true);
			team2.setCounted(true);
			
			
			/*
			 * Add created bout to poule
			 * and remove it from possible bouts list;
			 */
			myBouts.add(curr);
			bouts.remove(curr);
			
			ageGroup.remove(team1);
			ageGroup.remove(team2);
			
			teams.add(team1);
			teams.add(team2);
			
			/**
			 * For each bouts created
			 * get a random team to be referee
			 * remove referee from the original team list
			 */
			for (int j =0 ; j < possPoule;j++) {
				int rand = (int) (Math.random() * ageGroup.size());
				Team aRef = ageGroup.get(rand);
				aRef.setCounted(true);
				myBouts.get(j).setReferee(aRef);
				
				teams.add(aRef);
				ageGroup.remove(aRef);
			}
		}
	}
	
	@Override
	public String toString() {
		String retStr ="";
		for (Bout i : myBouts) {
			retStr += i.toString() + "\n";
		}
		return retStr;
	}
}
