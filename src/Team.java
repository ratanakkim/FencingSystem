
public class Team {


	private int weapon = 0;
	private int ageGroup = 0;
	private int currentPoint=0;
	private Boolean counted = false;
	private int number;
	public Team(int aWpn, int aAge, int aNum) {
		this.weapon = aWpn;
		this.ageGroup = aAge;
		this.number = aNum;
	}
	public Boolean getCounted() {
		return counted;
	}
	public void setCounted(Boolean counted) {
		this.counted = counted;
	}
	public int getWeapon() {
		return weapon;
	}
	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}
	public int getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}
	public int getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		String ret ="Team "+number+"; age group: "+ ageGroup +"; point: "+currentPoint;
		return ret;
	}
	public void showPoint() {
		System.out.println("Current point is: " + currentPoint + " for team: "+ number);
	}
	
}
