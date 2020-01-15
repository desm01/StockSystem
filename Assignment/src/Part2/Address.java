package Part2;

public class Address {

	//	Here I am creating the private instance variables that will be later set when a new address object is created
	private int bldNum;
	private String bldStreet;
	private String bldTown;
	private String bldPCode;
	private String bldCountry;

	//	The constructor takes in these parameters and uses them to create a new address object
	public Address(int bldNum, String bldStreet, String bldTown, String bldPCode, String bldCountry) {
		this.bldNum = bldNum;
		this.bldStreet = bldStreet;
		this.bldTown = bldTown;
		this.bldPCode = bldPCode;
		this.bldCountry = bldCountry;
	}

	//	This method returns the full address for a given address object
	public String getFullAddress() {
		String fullAddress = "";
		fullAddress +=  getBldNum() + "~" 
		+ getBldStreet() + "~" + getBldTown() + "~" 
		+ getBldPCode() + "~" + getBldCountry();
		
		return fullAddress;
	}

	//	This method is called when the building number for a specific object needs used. This will be used when writing to the text file and when printing data
	public int getBldNum() {
		return this.bldNum;
	}

	//	This method is used to return the string that contains a specific objects street name
	public String getBldStreet() {
		return this.bldStreet;
	}

	//	This method is used to return a string that contains a specific town name
	public String getBldTown() {
		return this.bldTown;
	}

	//	This method is used to return the postcode for a specific supplier. This will later be used for printing 
	public String getBldPCode() {
		return this.bldPCode;
	}

	//	This method will be used to return a string containg the country for the building. This will be used later on for printing
	public String getBldCountry() {
		return this.bldCountry;
	}

	//	This method will be called when the user is modifying data and wants to change the building number
	public void setBldNum(int BldNum) {
		this.bldNum = BldNum;
	}

	//	This method will be called when the user is modifying data and wants to change the building street
	public void setBldStreet(String BldStreet) {
		this.bldStreet = BldStreet;
	}

	//	This method will be called when the user is modifying data and wants to change the buildings town
	public void setBldTown(String BldTown) {
		this.bldTown = BldTown;
	}

	//	This method will be called when the user is modifying data and wants to change the building post code
	public void setBldPCode(String BldPCode) {
		this.bldPCode = BldPCode;
	}

	//This method will be called when the user is modifying data and wants to change the addresses country
	public void setBldCountry(String BldCountry) {
		this.bldCountry = BldCountry;
	}

}
