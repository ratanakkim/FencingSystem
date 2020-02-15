import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class FencingSystem extends  Application implements EventHandler<ActionEvent>{
	
	Button btn1;
	private ArrayList<Venue> myVenues = new ArrayList<Venue>();
	
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage priStg) throws Exception {
		 
		priStg.setTitle("Fencing System");
		btn1 = new Button();
		btn1.setText("Make booking");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(btn1);
		
		Scene scn = new Scene(layout, 500, 500);
		priStg.setScene(scn);
		priStg.show();
		btn1.setOnAction(this);
		
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
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btn1) {
			btn1.setText("Making Work");
		}
	}
	
	
}
