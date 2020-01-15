package Part1;

//	Import all the necassary libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Part2.Address;
import Part2.Menu;
import Part2.Region;
import Part2.Supplier;

public class supplierTest {

	//	These are all the options that will be available to the user in the main menu
	static final String options[] = {"Print All Products", "Add New Supplier", "Add New Product", "Exit"};
	
	//	This stores the title of the application
	static String title = "\nSPLY";
	
	static final int QUIT = options.length;
	
	//	A new menu is created
	static Menu myMenu = new Menu(title, options);
	

	//	This is an arraylist of suppliers that will contain every indiviudal supplier and all their details and products
public static ArrayList<Supplier> supplierArray = new ArrayList<Supplier>();
	
	//	These instance variables will be used when creating a new supplier	
	public static Supplier supplier;
	public static String name;
	public static Address add;
	public static Region reg;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int choice;
		do { 
			myMenu.display();
			choice = myMenu.getChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		}
		while( choice != QUIT ); // or while (quit == false)
		System.out.println("\nGoodbye!");		
	}
	
	//	This method is what drives the main menu. The user enters a number and based on what number they enter, a method will be triggered
	private static void processChoice(int choice) {
		switch(choice) {
		
					//	Print all the products alongisde the supplier details
		case 1	:	printAllProducts();
					break;
					
					//	Allow the user to register a new supplier
		case 2	:	addNewSupplier();
					break;
					
					//	Allow the user to enter a new product	
		case 3	:	addNewProduct();
					break;
					
					//	Exit the application
		case 4 : System.exit(0);

		//	If incorrect data is entered, display this error message
		default	:	System.out.println("\nThe selected option is invalid.");
		}
		System.out.println();
	}
	
public static void printAllProducts() {
	
	//If no suppliers have been registered, display an error message
		if (supplierArray.size() == 0) {
			System.out.println("++++++++++++++++++++++");
			System.out.println("+ No Products Stored +");
			System.out.println("++++++++++++++++++++++");
		}
		//If supplier have been registered
		else {
			//Go through each supplier that's stored in the supplierArray
		for (int i = 0; i < supplierArray.size(); i++) {
			//Print the name and product details
			System.out.println(supplierArray.get(i).getSupName() + "\n***********\n" + supplierArray.get(i).getProductList());
		}
		}
		
		/*String [] proWithSupp = new String[pro.size()];
		
		for (int i = 0; i < pro.size(); i++) {
			proWithSupp[i] = pro.get(i); 
		}
			
			List<String> strings = Arrays.asList(proWithSupp);
		    Collections.sort(strings, new Comparator<String>() {
		        public int compare(String o1, String o2) {
		            return extractInt(o1) - extractInt(o2);
		        }

		        int extractInt(String s) {
		            String num = s.replaceAll("\\D", "");
		            // return 0 if no digits found
		            return num.isEmpty() ? 0 : Integer.parseInt(num);
		        }
		    });
		    for (String str : strings) {
		    	System.out.println(str);
		    }
				 */
				}
				
				
		


public static void addNewSupplier() {
	
	//Create a new scanner object called input that will allow the user to enter in data
		Scanner input = new Scanner(System.in);
		
		//Display this message saying you will need to enter the suppliers address first
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++You will need to enter the suppliers address+++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		//Initialise the building number as zero
		int bldNum = 0;
		
		//Get the user to enter building number and try parsing it into a integer
		try {
		System.out.println("\nEnter the building number ↓↓↓");
	    String bldNumb = input.nextLine();
	    bldNum = Integer.parseInt(bldNumb);
		}
		
		//If there's an error, display an error message and restart the method
		catch(NumberFormatException e) {
			System.out.println("\n++++++++++++++++++");
			System.out.println("+ Invalid Number +");
			System.out.println("++++++++++++++++++");
			addNewSupplier();
		}
		
		//Create and initalise these strings with blank data
		String bldStreet = "";
		String bldTown = "";
		String bldPCode = "";
		String country = "";
		
		//Get the user to enter the information for the address
		try {
		System.out.println("\nEnter the Street ↓↓↓" );
		bldStreet = input.nextLine();
		
		System.out.println("\nEnter the Town ↓↓↓");
		bldTown = input.nextLine();
		
		System.out.println("\nEnter the PostCode ↓↓↓");
		bldPCode = input.nextLine();
		
		System.out.println("\nEnter the Country ↓↓↓");
		country = input.nextLine();
		}
		//If there's an error, display an error message
		catch (InputMismatchException ex) {
			System.out.println("You have entered data in the incorrect format, please try again.");
			addNewSupplier();
		}
		// Create a new address using the data that the user has entered
		add = new Address(bldNum, bldStreet, bldTown, bldPCode, country);
		
		//Display a message saying that the address was succesfully created
		System.out.println("\n+++++++++++++++++++++++++++++");
		System.out.println("+ ADDRESS SUCCESSFULLY ADDED +");
		System.out.println("+++++++++++++++++++++++++++++");
		
		//Get the user to enter the suppliers name
		System.out.println("\nEnter the suppliers name ↓↓↓");
		String name = input.nextLine();
		
		//Get the user to enter the region of the business
		System.out.println("\nEnter the region number ↓↓↓\n1. UK\n2. EU\n3. Non-EU");
		
		//Initalise and create an integer called chosenRegion that will be used to create the region enumeration
		int chosenRegion = 0;
		
		//Try parsing the entered region string into an integer
		try {
		String chosenRegionString = input.nextLine();
		chosenRegion = Integer.parseInt(chosenRegionString);
		
		}
		
		//If there's an error display the below error message
		catch(NumberFormatException e) {
			System.out.println("\n++++++++++++++++++");
			System.out.println("+ Invalid Number +");
			System.out.println("++++++++++++++++++");
			addNewSupplier();
			
		}
		
		//Initalise the region as null.
		 reg = null;
		
		 //If the user has entered 1 as the region
		if (chosenRegion == 1) {
			//Set the region to the UK
			reg = Region.UNITED_KINGDOM;
			
			//Create the supplier object and add it to the supplierArray
			supplier = new Supplier(name, add, reg);
			supplierArray.add(supplier);
		}
		
		//If the user has entered 2 as the region
		else if (chosenRegion == 2) {
			//Set the region to Europe
			reg = Region.EUROPE;
			
			//Create a new supplier object and add it to the supplierArray 
			supplier = new Supplier(name, add, reg);		
			supplierArray.add(supplier);
		}
		
		//If the user has entered 3 as the region
		else if (chosenRegion == 3) {
			//Set the region to outside europe
			reg = Region.OUTSIDE_EU;
			
			//Create a new supplier object and add it to the supplierArray
			supplier = new Supplier(name, add, reg);		
			supplierArray.add(supplier);		
		}
		
		//If the user has not entered a value that is 1,2 or 3. An error message will be display
		else if (chosenRegion != 1 && chosenRegion != 2 && chosenRegion != 3  ) {
			System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+ ERROR. YOU HAVE NOT SELECTED A PROPER REGION +");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
			addNewSupplier();
		}
	
	
	
	
	}



public static void addNewProduct() {

	//Here I am intialsing the variables that will be used to instiante the product object
	//The variables are all being set to default values, but they will be changed later in the algprtihim

	String proMake = "";
	String proModel = "";
	double proPrice = 0.00;
	int proQtyAvaliable = 0;
	boolean proDiscontinued = false;
	
	
	//This if statement checks to see if any suppliers have been registered. If they haven't, the user will be prompted to create some suppliers
	if(supplierArray.size() == 0) {
		System.out.println("\nNo suppliers have been registered.");
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\nYou must register suppliers before adding a product");
	}
	
	//If suppliers have been registered, do  the following
	else if (supplierArray.size() >= 1) {
	
	//Create a new scanner object called input that will be used to assign data and read from the keyboard
	Scanner input = new Scanner(System.in);
	System.out.println("+++++++++++++++++++");
	//Try this block of code
	try {
		//This populates the variables that were created above with the user entered data
	System.out.println("\nEnter The Product Make ↓↓↓");
	proMake = input.nextLine();	
	
	System.out.println("\nEnter The Product Model ↓↓↓");
	proModel = input.nextLine();

	System.out.println("\nEnter The Product Price ↓↓↓");
	proPrice = input.nextDouble();

	System.out.println("\nHow Many Products Are Avaliable ↓↓↓");
	proQtyAvaliable = input.nextInt();

	System.out.println("\nIs The Product Discontinued? ↓↓↓");
	proDiscontinued = input.nextBoolean();
	}
	//If the above code crashes due to an input mismatch exception, an error message will be displayed and the method will start over again
	catch (InputMismatchException ex) {
		System.out.println("Error, you have entered data in the incorrect format.\nPlease start again");
		addNewProduct();
	}
	
	//This part of the code links the product to the entered supplier
	
	//Gets the suppliers name
	System.out.println("What Is The Supplier Name?");
	Scanner inp = new Scanner(System.in);
	String suppName = inp.nextLine();	
	
	//Creates an array called names that will contain strings. Each element in the array will be a suppliers name
	String [] names = new String [supplierArray.size()];
	
	//Loop through the arraylist that contains all the suppliers
	for (int i = 0; i < supplierArray.size(); i++) {
		//Pass the suppliers names into the names array array
		names[i] = supplierArray.get(i).getSupName();
	}
	
	
	try {
		//Initalise a default string called str that'll be used to store the product details
		String str = "";
		
		//Go through all the supplier names
		for (int n = 0; n < names.length; n++) 
		{
			//If the current supplier name matches the user entered supplier name
			if (names[n].contains(suppName)) {
				//Create a new product object using all the previously made variables and assosiciate it with the entered supplier
				Part2.Product product = new Part2.Product(proMake, proModel, proPrice, proQtyAvaliable, proDiscontinued);
				supplierArray.get(n).addProduct(product);
			}
		}
	}
	//If the user enters a supplier that does not exist, display an error message
	catch (ArrayIndexOutOfBoundsException ex) {
		System.out.println("\nThat supplier does not exist, please try again.");
		
	}
	
	

		
	
}
}

}

		
	



