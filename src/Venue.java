import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
	private static final int sabre = 0b001;
	private static final int foil = 0b010;
	private static final int epee = 0b100;
	private int currentWeek;
	public String getMyLocation() {
		return myLocation;
	}
	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}
	public int getWeaponAvailability() {
		return weapons;
	}
	public void setWeaponAvailability(int weaponAvailability) {
		this.weapons = weaponAvailability;
	}
	public ArrayList<Poule> getMyPoules() {
		return myPoules;
	}
	public void setMyPoules(ArrayList<Poule> myPoules) {
		this.myPoules = myPoules;
	}
	public ArrayList<Booking> getMyBooking() {
		return myBookings;
	}
	public void setMyBooking(ArrayList<Booking> myBooking) {
		this.myBookings = myBooking;
	}
	public int getCurrentWeek() {
		return currentWeek;
	}
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
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
	
	public void genPoule() throws Exception {
		//TODO CHECK WEAPONS CHECK AGEGROUP
		
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
		//NoPouleComparator myComp = new NoPouleComparator();
		
		ArrayList<Team> ageGroup1 = new ArrayList<Team>();	
		ArrayList<Team> ageGroup2 = new ArrayList<Team>();
		ArrayList<Team> ageGroup3 = new ArrayList<Team>();
		ArrayList<ArrayList<Team>> ageGroups = new ArrayList<ArrayList<Team>>(); //EW EW EW EW I HATE MYSELF
		
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
			
			teamSortCmp myTeamsort = new teamSortCmp();
			for (ArrayList<Team> aG : ageGroups) {
				Collections.sort( aG,myTeamsort);
			}
			//Sort groups based on who's lacking teams i.e. sort by %3
			Integer noPoule1 = ageGroup1.size()%3;
			Integer noPoule2 = ageGroup2.size()%3;
			Integer noPoule3 = ageGroup3.size()%3;
			 
			
			/*
			 * For each age group generate a poule by taking n bouts
			 * where n is the maximum number of poule that can be created concurrently from the team number
			 * Take the team corresponding to those bouts and mark them as taken
			 * take some random team to be referee; 
			 */
			for (int a = 0; a < 3; a++) {
				Poule aPoule = new Poule();
				aPoule.formBouts(ageGroups.get(a), allBouts.get(a));
				myPoules.add(aPoule);
			}
		}catch (NullPointerException e) {
			System.out.println("The Booking might not be found");
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
	public void genPossBouts(ArrayList<Team> aG,ArrayList<Bout> aBs) {
		
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
