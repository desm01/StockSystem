package Part2;

//Importing the arraylist library
import java.util.ArrayList;



public class Supplier {

	//Here I am creating the private and blank instance variables
	private static int supCode = 1;
	private String supName;
	private Address address;
	private Region supRegion;
	private ArrayList <Product> supProducts = new ArrayList<Product>();
	
	//The constructor takes in these values and creates a new instance of the object using the passed in parameters
	public Supplier(String supplierName, Address address, Region supRegion) {
		this.supCode += supCode++;
		setSupName(supplierName);
		setSupAddress(address);
		setSupRegion(supRegion);
	}
	
	//This method is called in the supplierTest class whenever the user wants to delete a product
	public boolean removeProduct(Product pro) {
		boolean bool = false;
		for (Product pro1 : supProducts) {
			//If any of the currently stored products are the same as the one that's being passed in
			if (pro == pro1) {
				//Set the product to null, thus putting it in garbage collection
				pro1 = null;
				bool = true;
			}
			else {
				bool = false;
			}
			}
		//Return a boolean to say that the product has been succesfully deeleted or not
		return bool;
		}
	
	//This method goes through each product and adds it to a string which is returned whenever the method is called
	public String getProductList() {		
		
		//	Initalise the empty string
		String str= "";
		for (Product pro: this.supProducts) {
			
			//	Add the product details to the string
			str += pro.getProDetails() + "\n";
		}
		//	Return the string at the end
		return str;
	}
	
	//	This method is called when the client wants to find out what a given suppliers ID is
	public int getSupCodeNum() {
		return this.supCode;
	}
	
	
	//	This method is called when the client wants to find out what a given suppliers name is
	public String getSupName() {
		return this.supName;
	}
	
	//	This method is called when the user wants to find out what a given suppliers address is
	public String getSupAddress() {
		String add = "";
		add += address.getFullAddress();
		return add;
	}
	
	//	This method is called when the user wants to find out what a given suppliers region is
	public Region getSupRegion() {
		return this.supRegion;
	}
	
	
	
	//	This method is called when the user wants to return a list of products that contain all the products for a given supplier
	public Product[] getSupProducts() {
		//Return all the products
		
		Product[] proArray = new Product[supProducts.size()];
		
		//	Loop through all the products that are stored for this supplier
		for (int i =0; i < supProducts.size(); i++) {
			
			//Add it to the proArray, which will be returned at the end
			proArray[i] = supProducts.get(i);
		}
		
		
		return proArray;
	}	
	
	//This sets the suppliers name to a diffrent name. This method is called when the user is modifying suppliers
	public void setSupName(String name) {
		this.supName = name;
	}
	
	//This sets the suppliers address to a diffrent address. This method is called when the user is modifying suppliers
	public void setSupAddress(Address address) {
		this.address = address;
	}
	
	//This sets the region of a supplier to a new  region. This is used when the client is modifying the supplier details
	public void setSupRegion(Region reg) {
		this.supRegion = reg;
	}
	
	//This method adds the new product to the supProducts array list.
	public void addProduct(Product pro) {
	//	ArrayList<Product> supProducts = new ArrayList<Product>() ;
		this.supProducts.add(pro);		
	}


	
}
