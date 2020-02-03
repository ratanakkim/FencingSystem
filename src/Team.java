
public class Team {
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	private int weapon = 0;
	private int ageGroup = 0;
	private int currentPoint=0;
	private Boolean counted = false;
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
	
	public void showPoint() {
		System.out.println("Current point is: " + currentPoint + " for team: "+ number);
	}
}
