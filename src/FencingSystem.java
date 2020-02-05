import java.util.ArrayList;

public class FencingSystem {
	private ArrayList<Venue> myVenues = new ArrayList<Venue>();
	
	public static void main(String[] args) {
		Venue aVen = new Venue();
		aVen.setWeapons(aVen.sabre);
		
		for (int i= 0; i < 14; i++) {
			int rand = (int) (Math.random() * 2);
			aVen.getMyAvail().add((rand%2 ==0) );
			if (rand%2 ==0) {
				Booking aBooking = new Booking();
				aBooking.setWeek(i);
				aVen.getMyBookings().add(aBooking);
				
				rand = (int) (Math.random() *25) + 1;
				
				for (int j = 0; j <= rand; j++) {
					Team aTeam = new Team(aVen.sabre, 1, j);
					aBooking.getTeam().add(aTeam);
				}
				
			}
			
		}
		
		System.out.println(aVen.getMyBookings().size());
//		for (Booking b: aVen.getMyBookings()) {
//			System.out.println(b);
//		}
		Booking fstBkng = aVen.getMyBookings().get(0);
		System.out.println(fstBkng);
		for (Team t : fstBkng.getTeam()) {
			System.out.println(t);
		}
		ArrayList<ArrayList<Team>> ageGroups = new ArrayList<ArrayList<Team>>();
		ageGroups.add(fstBkng.getTeam());
		ageGroups.add(new ArrayList<Team>());
		ageGroups.add(new ArrayList<Team>());
		aVen.setCurrentWeek(fstBkng.getWeek());
		try {
			aVen.genPoule(ageGroups, aVen.sabre);
			for (Poule p : aVen.getMyPoules()) {
				System.out.println(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
