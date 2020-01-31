import java.util.ArrayList;

public class Poule {
	private ArrayList<Bout> myBouts = new ArrayList<Bout>();

	public ArrayList<Bout> getMyBouts() {
		System.out.println();
		return myBouts;
	}

	public void setMyBouts(ArrayList<Bout> myBouts) {
		this.myBouts = myBouts;
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
