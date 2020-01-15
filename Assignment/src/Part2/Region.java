package Part2;

public enum Region {
	
//Initalise these enumerations and set their positions
	UNITED_KINGDOM(0), EUROPE(1), OUTSIDE_EU(2);
	
	private int rNum;
	
	//This array contains all the regions that the user can select from
	private String [] Regions = {"United Kingdom", "Europe", "Outside EU"  };
	
	//Construct a new enumeration using the number that is passed in
	private Region(int num) {
		rNum = num;
	}
	
	//This returns the region as a string. This is used when displaying data
	public String getRegionAsString() {
		return Regions[rNum];
	}
	
}
