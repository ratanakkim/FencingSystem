import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author kimph
 *
 */
public class Venue {

	private String myLocation;
	private int weapons;
	private ArrayList<Boolean> myAvail = new ArrayList<Boolean>(14);
	private ArrayList<Poule> myPoules =  new ArrayList<Poule>();
	private ArrayList<Booking> myBookings = new ArrayList<Booking>();
	public static final int sabre = 0b001;
	public static final int foil = 0b010;
	public static final int epee = 0b100;
	private int currentWeek;
	private ArrayList<ArrayList<Team>> ageGroups = new ArrayList<ArrayList<Team>>(); 
	public int getWeapons() {
		return weapons;
	}
	public void setWeapons(int weapons) {
		this.weapons = weapons;
	}
	public ArrayList<Boolean> getMyAvail() {
		return myAvail;
	}
	public void setMyAvail(ArrayList<Boolean> myAvail) {
		this.myAvail = myAvail;
	}
	public ArrayList<Booking> getMyBookings() {
		return myBookings;
	}
	public void setMyBookings(ArrayList<Booking> myBookings) {
		this.myBookings = myBookings;
	}
	public ArrayList<ArrayList<Team>> getAgeGroups() {
		return ageGroups;
	}
	public void setAgeGroups(ArrayList<ArrayList<Team>> ageGroups) {
		this.ageGroups = ageGroups;
	}
	public String getMyLocation() {
		return myLocation;
	}
	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}
	
	public ArrayList<Poule> getMyPoules() {
		return myPoules;
	}
	public void setMyPoules(ArrayList<Poule> myPoules) {
		this.myPoules = myPoules;
	}
	
	public int getCurrentWeek() {
		return currentWeek;
	}
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}
	@Override
	public String toString(){
		return myLocation +" | Weapons: "+weapons + " | booked: "+myBookings;
		
	}

	public void genRndAvl() {
		ArrayList<Boolean> arr = new ArrayList<Boolean>();
		Random rnd = new Random();
		for (int i = 0; i < 14; i++) {
			arr.add(rnd.nextBoolean());
		}
		this.setMyAvail(arr);
			
	}
	
	public Boolean checkAvailability(int week) {
		try {
			if(myAvail.get(week-1) == true) {
				return true;
			}else {
				return false;
			}
		}
		catch(NullPointerException e) {
			System.err.println("Did you try to insert values between 1 and 14 inclusive?");
			return false;
		}
		
	}
	
	/*
	 * Comparator for comparing size of age group
	 */
	class NoPouleComparator implements Comparator<ArrayList<Team>> { 
		@Override
		public int compare(ArrayList<Team> t1, ArrayList<Team> t2) {
			Integer noPoule1 = t1.size()%3;
			Integer noPoule2 = t2.size()%3;
			return noPoule1.compareTo(noPoule2);
		}
	
	}
	class teamSortCmp implements Comparator<Team>{
		@Override
		public int compare(Team t1, Team t2) {
			
			Integer num1 = t1.getNumber();
			Integer num2 = t2.getNumber();
			return num1.compareTo(num2);
		}
		
	}
	/**
	 * Using a mask to see if the weapon type is available
	 * @param weaponType
	 * @return true if weapon type matches
	 */
	
	public Boolean checkTypes(int weaponType) {
		if ((weaponType & weapons) == weaponType) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	private Booking findThisWeekBooking() {
		Booking thisWkBkg = null;
		for (Booking b : myBookings ) {
			if (b.getWeek() == this.getCurrentWeek()) {
				thisWkBkg = b;
			}
			break;
		}
		return thisWkBkg;
		//TODO catch null return
		
	}
	
	/**
	 * Given a list of a team for a certain age group and weapon
	 * try to generate proper poules for that
	 * @param ageGroups
	 * @param allBouts
	 * @throws Exception
	 */
	public void genPoule(ArrayList<ArrayList<Team>> ageGroups, int wpnMask) throws Exception {
		//TODO funky stuff with the agegroups and agegroup1 and 2 and 3 

		ArrayList<Team> ageGroup1 = new ArrayList<Team>();	
		ArrayList<Team> ageGroup2 = new ArrayList<Team>();
		ArrayList<Team> ageGroup3 = new ArrayList<Team>();

		LinkedList<Bout> g1Bouts = new LinkedList<Bout>();
		LinkedList<Bout> g2Bouts = new LinkedList<Bout>();
		LinkedList<Bout> g3Bouts = new LinkedList<Bout>();
		ArrayList<LinkedList<Bout>> allBouts = new ArrayList<LinkedList<Bout>>();
		allBouts.add(g1Bouts);
		allBouts.add(g2Bouts);
		allBouts.add(g3Bouts);
		
		try {
			Booking currBooking = this.findThisWeekBooking();
			//Get all teams into age groups
			for (Team t : currBooking.getTeam()) {
				//Filter out wrong weapons
				if (t.getWeapon() == wpnMask) {
					if (t.getAgeGroup() == 1) {
						ageGroup1.add(t);
					}
					else if (t.getAgeGroup() == 2) {
						ageGroup2.add(t);
					}
					else if (t.getAgeGroup() == 3) {
						ageGroup3.add(t);
					}
					else  {
						throw new Exception("PANIC AT THE HOW OLD ARE YOU");
					}
				}
				
			}
			
			teamSortCmp myTeamsort = new teamSortCmp();
			//Sort the teams in each group
			for (ArrayList<Team> aG : ageGroups) {
				Collections.sort( aG,myTeamsort);
			}
	
			
			
			/*
			 * Add all poules into their respective age group
			 * 
			 */
			for (int a = 0; a < 1; a++) {
				Poule aPoule = new Poule();
				genPossBouts(ageGroups.get(a), allBouts.get(a));
				aPoule.formBouts(ageGroups.get(a), allBouts.get(a));
				System.out.println("Size of ageGroup in Venue: "+ ageGroups.get(a).size());
				myPoules.add(aPoule);
			}
			

			/* Use a new list for leftovers to make life easier
			 * Sort the left over teams by who has more left over
			 * max would be 2 since it's a modulo 3
			 * 
			 */
			int leftOver1 = ageGroups.get(0).size();
			int leftOver2 =ageGroups.get(1).size();
			int leftOver3 = ageGroups.get(2).size();
			System.out.println("age group 1 lo: "+ leftOver1 );
			int totalLO = leftOver1 + leftOver2 + leftOver3;
			NoPouleComparator npCmp = new NoPouleComparator();
			
			
			while (totalLO >= 3){
				System.out.println("TotalLo: "+totalLO);
				Collections.sort(ageGroups, npCmp);
				ArrayList<Team> loTeam = ageGroups.get(0);
				
				/*
				 * If age group with the most left over has 2 team
				 * we look for 1 more from the nearby team.
				 */
				System.out.println("loTeam size: "+loTeam.size());
				if (loTeam.size() == 2 ) {
					
					int loAG = loTeam.get(0).getAgeGroup();
					///Age group 1 or 3 has 2 teams, we can only grab 1 team from age group 2
					if ( loAG == 1 || loAG == 3) {
						if ( ageGroup2.size() > 0) {
							Bout aBout = new Bout(loTeam.get(0), loTeam.get(1), ageGroup2.get(0));
							loTeam.remove(0);
							loTeam.remove(0);
							ageGroup2.remove(0);
							//Add the bout to the appropriate poule
							myPoules.get((loAG -1)).addBouts(aBout);
						}
					}
					// Age group 2 has 2 teams, we can grab from group 1 or 3
					else {
						ArrayList<Team> loTeam2 = ageGroups.get(1);
						Bout aBout = new Bout(loTeam.get(0), loTeam.get(1),loTeam2.get(0));
						loTeam.remove(0);
						loTeam.remove(0);
						loTeam2.remove(0);
						myPoules.get((loAG -1)).addBouts(aBout);
					}
				}
				else if(loTeam.size() == 1){
					
				}
				// There are 1 team left over from each age group
				else {
					Bout aBout = new Bout (ageGroup1.get(0),ageGroup2.get(0),ageGroup3.get(0));
					myPoules.get(1).addBouts(aBout);
					ageGroup1.remove(0);
					ageGroup2.remove(0);
					ageGroup3.remove(0);
				}
				totalLO -=3;
			}
			
			
		}
		finally{
				
		}

	}
	
	/**
	 *
	 * For an age groups generate the possible bouts within the group
	 * Go through the least and pair them off with the rest of the element AFTER them
	 * The element before would have created a bout with them already
	 * i.e. FORALL Team S and T s.t. T's index > S's index
	 * create a bout for S -> T
	 * @param aG
	 * @param aBs
	 */
	private void genPossBouts(ArrayList<Team> aG,LinkedList<Bout> aBs) {
		
		for (Team t : aG) {
			//Start is the next team after t
			int curr = aG.indexOf(t) + 1;				
			while (curr < aG.size()) {
				Bout aBout = new Bout();
				aBout.setTeam1(t);
				aBout.setTeam2(aG.get(curr));
				aBs.add(aBout);
				curr++;
			}
		}
		
	}
	public void unBook(Booking aBooking) {
		myBookings.remove(aBooking);	
	}
	public void Book(Booking aBooking) {
		myBookings.add(aBooking);
	}
}
