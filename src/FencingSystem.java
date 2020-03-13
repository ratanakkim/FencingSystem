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
import javafx.event.Event;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
public class FencingSystem extends  Application implements EventHandler<ActionEvent>{
	
	Button makeBkgBtn;
	//Button btn2;
	Scene index, makeBooking, seeBooking, addVenues;
	private ObservableList<Booking> bkgsData;
	//private TableView bookingTbl = new TableView();
	private ObservableList<Venue> vensData = FXCollections.observableArrayList();
	private ArrayList<Venue> myVenues = new ArrayList<Venue>();
	private Stage myWindow;
	private BookingTable bookingTbl = new BookingTable();
	private VenueTable vensTbl = new VenueTable();
	public static void main (String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage window) throws Exception {
		myWindow = window;
		myWindow.setTitle("Fencing System");
		
		
		VBox venPgVB = new VBox();
		VBox bkgPgVB = new VBox();	
		VBox addVensVB = new VBox();
		HBox vensPgBtns = new HBox();
		HBox bkgsViewbtns = new HBox();
		
		index = new Scene(venPgVB, 1000, 1000);
		makeBooking = new Scene(bkgPgVB, 1000,1000);
		addVenues = new Scene(addVensVB, 1000, 1000);
		
		makeBkgBtn = new Button();
		makeBkgBtn.setText("Make booking");
		Button newVenueBtn = new Button("Add Venues");
		Button newBooking = new Button("Add Booking");
		Button bookingToVenue = new Button("Back");
		Button editBkgBtn = new Button ("Edit");
		
		Label lblVenues = new Label("Venues");
		lblVenues.setFont(new Font ("Verdana",20));
		Label lblBooking = new Label ("Bookings");
		lblBooking.setFont(new Font("Verdana", 20));
		
		bkgPgVB.setSpacing(10);
		bkgPgVB.setPadding(new Insets(10, 10, 20, 10));
		

		/*
		 * Adding new venue section
		 */
		
		Label lblAddVen = new Label("Add venue");
		final TextField venLocTF = new TextField();
		final TextField venWpnTF = new TextField();
		
		
		final TextField teamTF = new TextField();
		final TextField wpnTF = new TextField();
		final TextField ageTF = new TextField();
		final TextField weekTF = new TextField();
		vensTbl = new VenueTable();
		
		/*
		 * Index page setting
		 */
		vensTbl.setFencingSys(this);
		vensTbl.setItems(vensData);
		
		addVensVB.getChildren().addAll(lblAddVen,venLocTF,venWpnTF,newVenueBtn);
		addVensVB.setSpacing(10);
		addVensVB.setPadding(new Insets(10,10,10,10));
		
		addVensVB.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) ) ));
		vensPgBtns.setSpacing(10);
		vensPgBtns.getChildren().addAll(makeBkgBtn);
		venPgVB.getChildren().addAll(lblVenues,vensTbl,addVensVB,vensPgBtns);
		venPgVB.setSpacing(20);
		venPgVB.setPadding(new Insets(10,10,20,10));
		bkgsViewbtns.setSpacing(10);
		bkgsViewbtns.getChildren().addAll(newBooking, editBkgBtn, bookingToVenue);
		Alert emptyvalues = new Alert(AlertType.WARNING);
		emptyvalues.setTitle("ERROR");
		emptyvalues.setContentText("field can't be empty");
		
		lblAddVen.setFont(new Font("Verdana",20));
		/*
		 * New Venue's page settings 
		 */
		venLocTF.setPromptText("Enter Name or Address");
		venWpnTF.setPromptText("Enter weapons as number between 1-8");
		
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

		
		//TODO FIX
		

		
		
		

//		bookingTbl.getColumns().addAll(tmNumCol,wpnCol,weekCol);
		
//		final TextField venTF = new TextField();

		//TODO Create Custom class for alerts and textfields 
//		venTF.setPromptText("Venue Name");
		teamTF.setPromptText("Team Number");
		wpnTF.setPromptText("Weapon");
		ageTF.setPromptText("age");
		weekTF.setPromptText("Week");
		//TODO Fix bookingTbl nolonger exist since we move to new class
		
		//TODO fix this
		
		bkgPgVB.getChildren().addAll(lblBooking,bookingTbl,teamTF,wpnTF, ageTF,weekTF,bkgsViewbtns );
		
		
		
		//myWindow.setMaximized(true);
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
		
		makeBkgBtn.setOnAction(e -> window.setScene(makeBooking));
		newBooking.setOnAction(e -> {

			if (teamTF.getText().trim().isEmpty() || wpnTF.getText().trim().isEmpty()||
				ageTF.getText().trim().isEmpty() || weekTF.getText().trim().isEmpty()) {
				bookingTbl.refresh();
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
				//bkgsData.addAll(aBooking);
				bookingTbl.getBookings().add(aBooking);
				//TODO Switch booking for each venue
				//TODO table population and refresh only on setItems(bkgsData)
				bkgsData = FXCollections.observableArrayList(bookingTbl.getBookings());
				bookingTbl.setItems(bkgsData);
				
				this.bookingTbl.refresh();
				//Venue currVen = vensTbl.getSelectionModel().getSelectedItem();
				// currVen.getMyBookings().add(aBooking);
				
				teamTF.clear();
				wpnTF.clear();
				ageTF.clear();
				weekTF.clear();
			}
		});
		editBkgBtn.setOnAction(e ->{
			if (this.bookingTbl.getSelectionModel().getSelectedItem() !=  null) {
				//TODO finish booking editing 
				System.out.println( "Editing booking");
			
			}
			else {
				//TODO print error message
			}
		});
		newVenueBtn.setOnAction(e ->{
			String locStr = venLocTF.getText().trim();
			String wpnStr = venWpnTF.getText().trim();
			if ( locStr.isEmpty() || wpnStr.isEmpty() ) {
				//TODO finish this
				System.out.println( "Can't make new Venue");
			}
			else {
				Venue aVen = new Venue(locStr, Integer.parseInt(wpnStr) );
				vensData.add(aVen);
				//TODO availability
				System.out.println("ARGHHG");
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
		
		bkgsData = FXCollections.observableArrayList(aVen.getMyBookings());
		System.out.println("venue's booking is: " +bkgsData);
		//TODO This transfers the data to the bookingTable but it's not great yet
		bookingTbl.setItems(bkgsData);
		
		myWindow.setScene(makeBooking);
		
	}
	public void switchToVenues() {
		//TODO not transferring booking data properly
		//this.bkgsData.remove(bkgsData.size()-1);
		this.vensTbl.refresh();
		myWindow.setScene(index);
	}
	
}
