import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.ChangeEvent;


import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
public class FencingSystem extends  Application implements EventHandler<ActionEvent>{
	
	Button btn1;
	Button btn2;
	Scene index, makeBooking, seeBooking;
	private ObservableList<Booking> bkgsData = FXCollections.observableArrayList();
	//private TableView bookingTbl = new TableView();
	private ObservableList<Venue> vensData = FXCollections.observableArrayList();
	private ArrayList<Venue> myVenues = new ArrayList<Venue>();
	private Stage myWindow;
	private BookingTable bookingTbl;
	private VenueTable vensTbl;
	public static void main (String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage window) throws Exception {
		myWindow = window;
		myWindow.setTitle("Fencing System");
		btn1 = new Button();
		btn1.setText("Make booking");
		btn2 = new Button();
		Button newBooking = new Button("Add Booking");
		//bookingTbl.setEditable(false);
		//Layout for 1st page
		VBox layout = new VBox();
		//Layout for making a booking
		
		
		VBox layout2 = new VBox();
		layout2.setSpacing(10);
		layout2.setPadding(new Insets(10, 10, 20, 10));
		
		HBox bkgsViewbtns = new HBox();
		Button bookingToVenue = new Button("Back");
		bkgsViewbtns.getChildren().addAll(newBooking,bookingToVenue);
		Alert emptyvalues = new Alert(AlertType.WARNING);
		emptyvalues.setTitle("ERROR");
		emptyvalues.setContentText("field can't be empty");

		//TODO venue table needs to open bookings table on double click
		//bookingTbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//TODO venue table cell factory
		
		
		
		//Booking table columns
//		TableColumn tmNumCol = new TableColumn("Team Number");
//		TableColumn wpnCol = new TableColumn("Weapon");
//		TableColumn weekCol = new TableColumn("Week");
//		weekCol.setPrefWidth(520);
		
		
//		tmNumCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
//		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
//		         StringProperty ret = new SimpleStringProperty( b.getValue().getTeamsString());
//		         return ret;
//		     }
//		  });
//		wpnCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
//		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
//		    	
//		         StringProperty ret = new SimpleStringProperty( Integer.toString( b.getValue().getTeam().get(0).getWeapon()));
//		         return ret;
//		     }
//		  });
//		weekCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
//		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
//		    	
//		         StringProperty ret = new SimpleStringProperty( Integer.toString( b.getValue().getWeek()));
//		         return ret;
//		     }
//		  });
				
		vensTbl = new VenueTable();
		vensTbl.setFencingSys(this);
		vensTbl.setItems(vensData);
		Venue tstVen = new Venue();
		tstVen.genRndAvl();
		tstVen.setWeapons(3);
		vensData.add(tstVen);
		Venue tstVen2 = new Venue();
		tstVen2.genRndAvl();
		tstVen2.setWeapons(4);
		vensData.add(tstVen2);
		
		int num = 1;
		int wea = 3;
		int ag = 2;
		int wek= 3;
		Team aTm = new Team(wea, ag, num);
		Booking aBking = new Booking();
		
		aBking.setWeek(wek);
		aBking.addTeam(aTm);
		tstVen.getMyBookings().add(aBking);
//		vensTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//			
//
//			@Override
//			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
//				if (vensTbl.getSelectionModel().getSelectedItem() != null) {
//					Venue currVen = vensTbl.getSelectionModel().getSelectedItem();
//					System.out.println(currVen);
//				}
//				
//			}
//			
//		});

		Label lblVenues = new Label("Venues");
		lblVenues.setFont(new Font ("Verdana",20));
		//TODO FIX
		layout.getChildren().addAll(lblVenues,vensTbl,btn1);
		layout.setSpacing(20);
		layout.setPadding(new Insets(10,10,20,10));
		
		
		Label lblBooking = new Label ("Bookings");
		lblBooking.setFont(new Font("Verdana", 20));

//		bookingTbl.getColumns().addAll(tmNumCol,wpnCol,weekCol);
		
//		final TextField venTF = new TextField();
		final TextField teamTF = new TextField();
		final TextField wpnTF = new TextField();
		final TextField ageTF = new TextField();
		final TextField weekTF = new TextField();
		//TODO Create Custom class for alerts and textfields 
//		venTF.setPromptText("Venue Name");
		teamTF.setPromptText("Team Number");
		wpnTF.setPromptText("Weapon");
		ageTF.setPromptText("age");
		weekTF.setPromptText("Week");
		//TODO Fix bookingTbl nolonger exist since we move to new class
		bookingTbl = new BookingTable();
		//TODO fix this
		bookingTbl.setItems(bkgsData);
		layout2.getChildren().addAll(lblBooking,bookingTbl,teamTF,wpnTF, ageTF,weekTF,bkgsViewbtns );
		index = new Scene(layout, 500, 500);
		makeBooking = new Scene(layout2, 500,500);
		
		
		
		
		myWindow.setScene(index);
		myWindow.show();
//		btn1.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				btn1.setText("BOOKING");
//				
//			}
//			
//		});
		bookingToVenue.setOnAction(e -> {
			this.switchToVenues();
		});
		btn1.setOnAction(e -> window.setScene(makeBooking));
		newBooking.setOnAction(e -> {

			if (teamTF.getText().trim().isEmpty() || wpnTF.getText().trim().isEmpty()||
				ageTF.getText().trim().isEmpty() || weekTF.getText().trim().isEmpty()) {
				
				emptyvalues.showAndWait();
			}else {
				// TODO check venue's availability
				int number = Integer.parseInt((teamTF.getText()));
				int weapon = Integer.parseInt((wpnTF.getText()));
				int age = Integer.parseInt((ageTF.getText()));
				int week= Integer.parseInt((weekTF.getText()));
				Team aTeam = new Team(weapon, age, number);
				Booking aBooking = new Booking();
				aBooking.setWeek(week);
				aBooking.addTeam(aTeam);
				System.out.println(aBooking);
				//TODO fix since bkgsData moved to new class
				bkgsData.addAll(aBooking);
				//TODO Switch booking for each venue
				bookingTbl.setItems(bkgsData);
				Venue currVen = vensTbl.getSelectionModel().getSelectedItem();
				currVen.getMyBookings().add(aBooking);
				
				teamTF.clear();
				wpnTF.clear();
				ageTF.clear();
				weekTF.clear();
			}
		});
		
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
		
		//System.out.println(aVen.getMyBookings().size());
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
		// TODO Auto-generated method stub
		
	}
	
	public void setBkgsData(ArrayList<Booking> aList) {
		ObservableList<Booking> obsBkgs = FXCollections.observableArrayList(aList);
		this.bkgsData = obsBkgs;
	}
	public void switchToBookings(Venue aVen) {
		//TODO pass data to the booking scene
		//Display bkgsData after switching scene
		this.bookingTbl.setBookings(aVen.getMyBookings());
		this.bookingTbl.refresh();
		myWindow.setScene(makeBooking);
	}
	public void switchToVenues() {
		//TODO not transferring booking data properly
		//this.bkgsData.remove(bkgsData.size()-1);
		myWindow.setScene(index);
	}
	
}
