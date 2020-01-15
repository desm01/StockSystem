package Part1;

public class Product {

	//The private instance variables are created
	private static int proCode = 1;
	private String proMake;
	private String proModel;
	private double proPrice;
	private int proQtyAvailable;
	private boolean proDiscontinued;
	

	//The constructor takes in these parameters and creates a new instance of product using them
	public Product(String proMake, String proModel, double proPrice, int proQtyAvaliable, boolean proDiscontiuned ) {
	
		this.proCode += proCode++;
		this.proMake = proMake;
		this.proModel = proModel;
		this.proPrice = proPrice;
		this.proQtyAvailable = proQtyAvaliable;
		this.proDiscontinued = proDiscontiuned;
	}
	
	
	//This method returns the details for a specific instance of a given product as a string. This is used when printing
	public String getProDetails() {
		String details = "";
		details += "Product Code: " + getProCodeNum() + "\nProduct Make: " +getProMake()
		+ "\nProduct Model: " + getProModel() + "\nProduct Price: " + getProPrice() + 
		"\nQuantity Available: " + getProQty() + "\nProduct Discontinued: " + getProDiscontinued();
		
		//The details are returned as a string
		return details;
	}
	
	//	The unique product code is returned by this method. This will be used when reading and writing to a textfile
	public int getProCodeNum() {
		return this.proCode;
	}
	
	//	This method returns the make of the product. This will be used when printing the product details
	public String getProMake() {
		return this.proMake;
	}
	
	//This method is used to return the product model details as a string. This will also be used for printing
	public String getProModel() {
		return this.proModel;
	}
	
	//	This method returns a given products price. This will be used when searching through the data
	public double getProPrice() {
		return this.proPrice;
	}
	
	//	This method returns the quantity of the given product. This will be used when producing quotes 
	public int getProQty() {
		return this.proQtyAvailable;
	}
	
	//This method returns a boolean value that is used to represnt whether a product is discontinued or not. This will be used for printing
	public boolean getProDiscontinued() {
		return this.proDiscontinued;
	}
	
	//This method is used to set the price for a given product. This will be used when the client is modifying data 
	public void setProPrice(double price) {
		this.proPrice = price;
	}
	
	//This method is used for setting the avaliable quantity of a given item. This will be used when the client wants to modify data
	public void setProQty(int qty) {
		this.proQtyAvailable = qty;
	}
	
	//This method is called when the user wants to change the avaliabliilty of a product. This is used when the client is modifying data
	public void setProDiscontinued(boolean discontinued) {
		this.proDiscontinued = discontinued;
	}
	
}
