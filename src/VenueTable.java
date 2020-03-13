import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.util.Callback;

public class VenueTable extends TableView<Venue> implements ChangeListener{

	
	private ArrayList<Venue> myVens = new ArrayList<Venue>();
	private final ObservableList<Venue> vensData = FXCollections.observableArrayList();
	TableColumn<Venue, String> locCol = new TableColumn<Venue, String>("Venue Location");
	TableColumn<Venue, ArrayList<Boolean>> avlCol = new TableColumn<Venue, ArrayList<Boolean>>("Venue availabilities");
	TableColumn<Venue, ArrayList<Booking>> bkgsCol = new TableColumn<Venue, ArrayList<Booking>>("Bookings made");
	private FencingSystem myFS;
	
	
	public VenueTable(){
		this.setEditable(true);
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		locCol.setCellValueFactory(new Callback<CellDataFeatures<Venue, String>,  ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Venue, String> b) {
		         StringProperty ret = new SimpleStringProperty( b.getValue().getMyLocation());
		         return ret;
		     }
		  });
		
		avlCol.setCellValueFactory(new Callback<CellDataFeatures<Venue, ArrayList<Boolean>>,  ObservableValue<ArrayList<Boolean>>>() {
		     public ObservableValue<ArrayList<Boolean>> call(CellDataFeatures<Venue, ArrayList<Boolean>> b) {
		    	 ObservableValue<ArrayList<Boolean>> ret = new ReadOnlyObjectWrapper<ArrayList<Boolean>>(( b.getValue().getMyAvail()));
		    	 //ObservableList<Boolean> obsAvl = FXCollections.observableArrayList(b.getValue().getMyAvail());
		         return ret;
		    	 //return null;
		     }
		  });
		bkgsCol.setCellValueFactory(new Callback<CellDataFeatures<Venue, ArrayList<Booking>>,  ObservableValue<ArrayList<Booking>>>() {
		     public ObservableValue<ArrayList<Booking>> call(CellDataFeatures<Venue, ArrayList<Booking>> b) {
		    	 ObservableValue<ArrayList<Booking>> ret = new ReadOnlyObjectWrapper<ArrayList<Booking>>(( b.getValue().getMyBookings()));
		         return ret;
		    	 //return null;
		     }
		  });
		
		this.getColumns().addAll(locCol,avlCol,bkgsCol);

		
		//Enable clicking on row
		this.setRowFactory( tv -> {
			TableRow<Venue> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Venue rowData = row.getItem();
		            System.out.println(rowData.getMyBookings());
		            this.myFS.setBkgsData(rowData.getMyBookings());
		            this.myFS.switchToBookings(rowData);
		            
		        }
		    });
		    return row ;
		});
		
	}


	public void setFencingSys(FencingSystem aFS) {
		myFS = aFS;
	}
	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		
		
	}



	
}
