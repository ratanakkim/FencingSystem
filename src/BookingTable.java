import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class BookingTable extends TableView<Booking>{
	private ObservableList<Booking> bkgsData;
	TableColumn<Booking, String> tmNumCol = new TableColumn<Booking, String>("Team Number");
	TableColumn<Booking, String> wpnCol = new TableColumn<Booking, String>("Weapon");
	TableColumn<Booking, String> weekCol = new TableColumn<Booking, String>("Week");
	ArrayList<Booking> myVenBkgs;
	
	@SuppressWarnings("unchecked")
	public BookingTable(){
		this.setEditable(false);
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		weekCol.setPrefWidth(520);
		tmNumCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
		         StringProperty ret = new SimpleStringProperty( b.getValue().getTeamsString());
		         return ret;
		     }
		  });
		wpnCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
		    	
		         StringProperty ret = new SimpleStringProperty( Integer.toString( b.getValue().getTeam().get(0).getWeapon()));
		         return ret;
		     }
		  });
		weekCol.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>,  ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Booking, String> b) {
		    	
		         StringProperty ret = new SimpleStringProperty( Integer.toString( b.getValue().getWeek()));
		         return ret;
		     }
		  });
		
		this.getColumns().addAll(tmNumCol,wpnCol,weekCol);
		this.setItems(bkgsData);
	}
	public ArrayList<Booking> getBookings() {
		
		return this.myVenBkgs;
	}
	public void setBookings(ArrayList<Booking> aBkgLst) {
		this.myVenBkgs = aBkgLst;
		this.bkgsData = FXCollections.observableArrayList(aBkgLst);
		
		this.refresh();
	}
}
