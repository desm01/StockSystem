package Part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class supplierTest {

	//	These are all the options that will be available to the user in the main menu
	static final String options[] = {"Print All Products", "Add New Supplier", "Add New Product", "Delete Item", "Search", "Produce Quote", "Save Data", "Load Data", "Exit"};
	
	//	This stores the title of the application
	static String title = "\nSPLY";
	
	static final int QUIT = options.length;
	
	//	A new menu is created
	static Menu myMenu = new Menu(title, options);
	
	// This stores a list of products, and is used when printing all the products in the system and when quotes are produced 
	public static Product[] product;
	
	//	This stores a list of the suppliers, and is used when the suppliers are being displayed
	public static Supplier [] supList;
	
	//This is an arraylist of string that will store the detials for each product, this will be used when reading data from the text files
	public static ArrayList <String> pro = new ArrayList<String>();
	
	//This is an arraylist of products that will be used to store all the products that are in the system. This is useful for when the user wants to modify entries
	public static ArrayList<Product> proArrayList = new ArrayList<Product>();
	
	//This is an arraylist of suppliers that will be used to store all the suppliers that are in the system. This is useful for when the user wants to modify entries
    public static ArrayList<Supplier> supplierArray = new ArrayList<Supplier>();

    //This is an arraylist of strings, each string will contain the details for each individual supplier. This will be used when writing and loading to the text files
    public static ArrayList<String> supplierString = new ArrayList<String>();
    
    //This is an arraylist of strings that will be used to store the details for each product in the system. This will also be used for reading and writing to textfiles
    public static ArrayList<String> productString = new ArrayList<String>();
  
    //	This is an array of Strings that will be used to store the details for each product. This will be used for printing
    public static String [] prod;

    //	This is an array of strings that will be used to store the details of each supplier. This will be used later on for printing
    public static String [] sup;
	
    //These instance variables will be used to create and instiate new products, addresses, & suppliers
	public static Supplier supplier;
	public static String name;
	public static Address add;
	public static Region reg;
	
	public static void main(String[] args) throws IOException {
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
	
	//This method allows the main menu to work, the number that the user enters to the menu is passed into the method
	private static void processChoice(int choice) throws IOException {
		
		switch(choice) {
		case 1	:	printAllProducts();
					break;
		case 2	:	addNewSupplier();
					break;
		case 3	:	addNewProduct();
					break;
		case 4 : deleteItem();
					break;
		case 5 : searchItem();
					break;
		case 6 : produceQuote();
					break;
		case 7 : saveData();
					break;
		case 8 : loadData();
					break;
		case 9 : System.exit(0);
		
			//	If the user enters the incorrect data, an error message will be displayed
		default	:	System.out.println("\nThe selected option is invalid./n");
		}
	}
	
	//	This method is called when the user wants to save data, if an error happens when reading the text files exceptions are thrown
	public static void saveData() throws FileNotFoundException, UnsupportedEncodingException {
		
		// Create printWriter objects that allow the user to write to the product and supplier text files
		PrintWriter productWriter = new PrintWriter("product.txt", "UTF-8");
		PrintWriter supplierWriter = new PrintWriter("supplier.txt", "UTF-8");
		
		//	Loop through each element in the supplierArray
		for (int i = 0; i<supplierArray.size(); i++) {
			
			//Add all the suppliers products to the product arraylist
			product = supplierArray.get(i).getSupProducts();
			
			//Add the supplier details to the supplier.txt file
			supplierWriter.println(supplierArray.get(i).getSupName() +"~"+ supplierArray.get(i).getSupAddress() +"~"+ supplierArray.get(i).getSupRegion());
		
			//	Go through the product arraylist and store the details of the products alongside the supplier name in the product.txt file
			for (Product prod : product) {
				productWriter.println(supplierArray.get(i).getSupName() + "~" + prod.getProMake() + "~" + prod.getProModel() + "~" + prod.getProPrice() + "~" + prod.getProQty() + "~" + prod.getProDiscontinued());
			}
		}
		
		//Close the printWriter, thus saving the files
		supplierWriter.close();
		productWriter.close();
	}
	
	
	//	This method is called whenever the user wants to read data in from the text file. If an error occurs, an IOException is thrown
	public static void loadData() throws IOException {
		
		//Initalise the line as null
		String line = null;
		
		//Reads the supplier details from the supplier.txt file
		FileReader fileRead = new FileReader("supplier.txt");
		BufferedReader buffRead = new BufferedReader(fileRead);
		while ((line = buffRead.readLine()) != null ) {
			
			//	Add each line of the textfile to a new position in the supplierString array list
			supplierString.add(line);
		}
		
		//	For every string in the supplierString
		for (String str : supplierString) {
			// Split each line where there's a '~' symbol
			sup = str.split("~");
			
			//	Create a new address using the data that's come from the textfile
			Address ad = new Address(Integer.parseInt(sup[1]), sup[2], sup[3], sup[4], sup[5]);
			
			//	If the string says UNITED_KINGDOM, create a new supplier and add it to the supplierArray 
			if (sup[6].equals("UNITED_KINGDOM")) {
				reg = Region.UNITED_KINGDOM;
				Supplier supp = new Supplier(sup[0], ad, reg);
				supplierArray.add(supp);
			}
			
			//	If the string says EUROPE, create a new supplier and add it to the supplierArray
			else if (sup[6].equals("EUROPE")) {
				reg = Region.EUROPE;
				Supplier supp = new Supplier(sup[0], ad, reg);
				supplierArray.add(supp);
			}
			
			//	If the string says OUTSIDE_EU, create a new supplier and add it to the supplierArray
			else if (sup[6].equals("OUTSIDE_EU")) {
				reg = Region.OUTSIDE_EU;
				Supplier supp = new Supplier(sup[0], ad, reg);
				supplierArray.add(supp);
			}
			
		}
		
		//	Close the reader
		buffRead.close();
		
		//	Create a new string that'll store the product details for each line of the text file
		String product = null;
		
		//	Read data from the product.txt file
		FileReader productRead = new FileReader("product.txt");
		BufferedReader productBuffRead = new BufferedReader(productRead);
		
		//	Add each product details to the productString array list
		while ((product = productBuffRead.readLine()) != null) {
			productString.add(product);			
		}
		
		//	Go through each element in the productString array list and split the element where the '~' symbol occurs
		for (String str : productString) {
			prod = str.split("~");
			// Create a new product by calling the overloaded method and by passing in these parameters
			addNewProduct(prod[0], prod[1],  prod[2], Double.parseDouble(prod[3]), Integer.parseInt(prod[4]), Boolean.parseBoolean(prod[5])); 
		}
		
		//	Close the reader
		productBuffRead.close();
	}
	
	
	//	This method is used to produce a quote for all the items stored and sorts them from the lowest quantity to the highest
	public static void produceQuote() {
		
		//	If the supplierArray has elements stored in it
		if (supplierArray.size() != 0) {
			
			//	Loop through each supplier in the supplierArray and add their products to the product array list
			for (int i =0; i < supplierArray.size(); i++) {
				product = supplierArray.get(i).getSupProducts();
			}
			
			//Create an array of strings and populate it with the details of each product
			String [] ArrayOfProductNamesAndQty = new String[product.length];
			
			for (int i = 0; i < product.length; i++) {
				ArrayOfProductNamesAndQty[i] = "\nMake: " + product[i].getProMake() + "\nModel: " + product[i].getProModel() + "\nQTY: " + Integer.toString(product[i].getProQty());
			}
			
			// Sort the array and order it from the lowest to highest quantity
			
			List<String> strings = Arrays.asList(ArrayOfProductNamesAndQty);
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
		    
		    //	Print out the details
		    for (String str : strings) {
		    	System.out.println(str);
		    }
		  
			
		}
			
		//	If no suppliers have been registered, display an error message
		else {
			System.out.println("\nSorry, you haven't registered any products yet");
		}
	}
	
	//This method is used to search for items between a specified price range
	public static void searchItem() {
		
		//Create a scanner called input so the user can enter values and initalise the lowest and highest values
		Scanner input = new Scanner(System.in);
		double lowValue = 0;
		double highValue = 0;
		
		//If suppliers are registered in the system
		if (supplierArray.size() != 0) {
			
			try {
				
				//	Get the user to enter the lowest price and highest price
			System.out.println("\nEnter the lowest price");
			lowValue = input.nextDouble();
			System.out.println("\nEnter the highest value");
			highValue = input.nextDouble();
			
			}
			
			//	If the user enters the data in the incorrect format, display an error message and restart the method
			catch(InputMismatchException ex) {
				System.out.println("Error, you have entered the data in the incorrect format");
				searchItem();
			}
			
			// Loop through each supplier and add their stored products to the product array list 
		for (int i = 0; i < supplierArray.size(); i++ ) {
			product = supplierArray.get(i).getSupProducts();
	}
		
		//	Loop through the arraylist that stores all the products
		for (int y = 0; y < product.length; y++) {
			// If the given product is within the price range that the user specified
			if (product[y].getProPrice() > lowValue && product[y].getProPrice() < highValue) {
				
				//	Display the products on screen 
				System.out.println("Products Within Price Range ↓↓↓\n");
				System.out.println(product[y].getProDetails() + "\n");
			}
		}
		
		}
		
		//	If suppliers have not been registered, display an error message		
		else {
			System.out.println("\nSorry, you haven't registered any products yet");
		}
		
	}
	
	//	This method is called whenever the user deicides that they want to delete something.
	//	The method seeks to find out what the user wants to delete
	public static void deleteItem() {
		
		//Display a message asking the user to pick what they want to delete
		System.out.println("\n*************************************");
		System.out.println("\n1. Do You Want To Modify An Entry?");
		System.out.println("2. Do You Want To Delete An Entry?");
		
		Scanner input = new Scanner(System.in);
		String selected = input.nextLine();
		
		//	If the user enters '1', call the modifyEntry method
		if (selected.equals("1")) {
			modifyEntry();
		}
		
		//	If the user enters '2', find out if they want to delete a supplier or product
		else if (selected.equals("2")) {
			System.out.println("\n1. Delete Supplier ");
			System.out.println("2. Delete Product");
			
			String sel = input.nextLine();
			
			//	If the user enters '1', call the deleteSupplier method
			if (sel.equals("1")) {
				deleteSupplier();
			}
			
			//	If the user enters '2', call the deleteProduct method
			else if (sel.equals("2")) {
				deleteProduct();
			}
			
			//	If the user doesnt enter either 1 or 2, show an error message and restart the method
			else if (!sel.equals("1") && !sel.equals("2")) {
				System.out.println("You have entered the wrong numbers");
				deleteItem();
			}
			
		}
		
//		If the user doesnt enter either 1 or 2, show an error message and restart the method
		else if (!selected.equals("1") && !selected.equals("2")) {
			System.out.println("Please enter either '1' or '2'");
			deleteItem();
		}		
	}
	
	
	//	This method is called whenever the user decides they want to modify something. 
	public static void modifyEntry() {
		//	Display these messages and create a new scanner object called inp.
		Scanner inp = new Scanner(System.in);
		System.out.println("\nWhat do you want to modify?");
		System.out.println("1. Product details");
		System.out.println("2. Supplier details");
		String selectedOption = inp.nextLine();
		
		//	If the user enters '1', the modifyProduct method will be called
		if (selectedOption.equals("1")) {
			modifyProduct();
		}
		
		//	If the user enters '2', the modifySupplier method will be called
		else if (selectedOption.equals("2")) {
			modifySupplier();
		}
		
		//	If the user has entered the incorrect informartiop, display an error message and restart the method
		else {
			System.out.println("Sorry, incorrect information entered");
			modifyEntry();
		}
		
	}
	
	
	//	This method is called whenever the user wants to modify a supplier
	public static void modifySupplier() {
		
		//	This loops through each element in the supplierArray and writes out the details of each individual supplier
		for (int i=0; i<supplierArray.size(); i++) {
			System.out.println( "\nSupplier Code: " + supplierArray.get(i).getSupCodeNum());
			System.out.println("Supplier Name: " + supplierArray.get(i).getSupName());
			System.out.println("Supplier Address: " + supplierArray.get(i).getSupAddress().replace("~", ", "));
			System.out.println("Supplier Region: " + supplierArray.get(i).getSupRegion().getRegionAsString());
		}
		
		//	Here the user is entering the ID for the supplier that they want to modify
		System.out.println("\nEnter the ID of the supplier you want to modify");
		
		Scanner input = new Scanner(System.in);
		//	Initalise the id to zero
		int id = 0;
		
		//	Get the user to enter an ID then parse it to an integer
		try {
		String str = input.nextLine();
		id = Integer.parseInt(str);
		}
		
		//	If there's an error, display a message telling the user what has went wrong and restart the method
		catch(InputMismatchException ex) {
			System.out.println("Error, you have entered data in the incorrect format. Please try again");
			modifySupplier();
		}
		
		//	Go through each supplier in the supplierArray
		for (Supplier supp : supplierArray) {
			//	If the user entered supplier code matches any of the stored supplier codes
			if (supp.getSupCodeNum() == id) {
				
				//	Get the user to set a new supplier name
				System.out.println("Enter the new supplier name, for " + supp.getSupName());
				String name = input.nextLine();
				
				//	Find out if the user want to edit the address
				System.out.println("Do you want to edit the address?");
				Scanner inp = new Scanner(System.in);
				String address = inp.nextLine();
				
				// Convert the users choice to lower case
				address.toLowerCase();
				
				//	If the user has typed yes or y
				if (address.equals("yes") || address.equals("y")) {
					
					//Get the user to edit the address information
					try {
					System.out.println("Enter the new building number");
				    String bldNumb = input.nextLine();
				    int bldNum = Integer.parseInt(bldNumb);
				    
					System.out.println("\nEnter the new Street ↓↓↓" );
					String bldStreet = input.nextLine();
					
					System.out.println("\nEnter the new Town ↓↓↓");
					String bldTown = input.nextLine();
					
					System.out.println("\nEnter the new PostCode ↓↓↓");
					String bldPCode = input.nextLine();
					
					System.out.println("\nEnter the new Country ↓↓↓");
					String country = input.nextLine();
					
					//	Create a new address object using the above data and set it as the address for the selected supplier
					add = new Address(bldNum, bldStreet, bldTown, bldPCode, country);
					supp.setSupAddress(add);
					}
					
					//	If the user accidentally enters data in the incorrect format, an error message will be displayed and the method will restart
					catch (InputMismatchException ex) {
						System.out.println("Error, you have entered the data in the incorrect format. Please try again");
						modifySupplier();
					}
				}
				
				//	If the user does not want to edit the addresses, display this message
				else {
					System.out.println("\nYou have decided to not edit supplier, " + supp.getSupName() + " address");
				}
				
				// Set the suppliers name as the newly entered name
				supp.setSupName(name);
			}
		}
		
	}
	
	//This method is called whenever the user wants to modify a product
	public static void modifyProduct() {
		
		//Loop through each element in the supplierArray
		for (int i=0; i<supplierArray.size(); i++) {
			// Add all the products to the product array
			product = supplierArray.get(i).getSupProducts();
			
			//For each product in the product array
			for (Product prod : product) {
				//Add it to the proArrayList. This makes it easier to print the product details
				proArrayList.add(prod);
			}
		}
		//Print the details of every product
		for (Product prod : proArrayList) {
			System.out.println(prod.getProDetails());
		}
		
		
		System.out.println("\nEnter the ID of the product you want to modify");
		//Initalise the selected ID as zero
		int id = 0;
		Scanner input = new Scanner(System.in);
		try {
			
		//Get the user to enter the product ID for the item they want to modify, then parse it to an integer
		
		String str = input.nextLine();
		id = Integer.parseInt(str);
		}
		
		//If the user enters the data in the incorrect format, display an error message and restart the method again
		catch (InputMismatchException ex) {
			System.out.println("\nError, you have entered the data in the incorrect format");
			modifyProduct();
		}
		
		//Loop through each product in the product list
		for (Product prod : product) {
			
			//If any of the product codes match the code that the user entered in, let the user edit the details of that specific product 
			if (prod.getProCodeNum() == id) {
				try {
					//Get the new product information
				System.out.println("\nEnter the new product price");
				double price = input.nextDouble(); 
				
				System.out.println("\nEnter the new product quantity");
				int quantity = input.nextInt();
				
				System.out.println("\nIs the product discontinued?");
				boolean discontinued = input.nextBoolean();
				
				//Set the data
				prod.setProDiscontinued(discontinued);
				prod.setProPrice(price);
				prod.setProQty(quantity);
				}
				
				//If the user enters the data incorrectly, an error message will be displayed and the method will restart
				catch (InputMismatchException ex) {
					System.out.println("Error, you have entered the data in the incorrect format, please try again");
					modifyProduct();
					
				}
				
			}
		}		
	}
	
	
	//	This method is used to delete a supplier
	public static void deleteSupplier() {
		//	Go through each supplier in the supplierArray
		for (Supplier sup : supplierArray) {
			//	Output the name and address for each supplier
			System.out.println("***********************");
			System.out.println(sup.getSupName());
			System.out.println(sup.getSupAddress().replace("~", ", "));
		}
		
		//	If no suppliers have been registered, display an error message telling the user that no suppliers are registered
		if (supplierArray.size() == 0) {
			System.out.println("\nSorry, no suppliers have been registered");
		}
		//	If suppliers are registered, show these messages 
		else {
		System.out.println("\nAbove is a list of the registered suppliers");
		System.out.println("\n\nEnter the name of the supplier which you want to delete");
		
		//	Get the user to enter the name of the supplier that they want to delete
		Scanner input = new Scanner(System.in);
		
		String supplierName = input.nextLine();
		
		//	Go through each element in the supplierArray array list and if the supplier name 
		//	is the same as the name that the user entered, remove the instance from the array list and display a message
		for (int i = 0; i < supplierArray.size(); i++) {
			if (supplierArray.get(i).getSupName().contentEquals(supplierName)) {
				String name = supplierArray.get(i).getSupName();
				supplierArray.remove(i);
				System.out.println(name + " has been succesfully deleted");
			}	
		}	
		
		}
	}
	
	//This method is called whenever the user wants to delete a product
	public static void deleteProduct() {
		
		//Create a new scanner object called input
		Scanner input = new Scanner(System.in);
		
		//If the supplier size is equal to zero
		if (supplierArray.size() == 0) {
			//Dispaly a message saying that no products are currently stored
			System.out.println("\nSorry, no products are currently stored");
		}
		
		//If products are stored
		else {
		
			//For every supplier in the supplierArray
		for (Supplier sup : supplierArray) {
			//Pass all their products into the product array
			product = sup.getSupProducts();
		}
		
		//Go through the products and print the details
		for (int i = 0; i < product.length; i++) {
			System.out.println("*******************************");
			System.out.println(product[i].getProDetails() + "\n");
		}
		
		//Get the user to enter the make of the product that they want to delete and pass it into a String called make
		System.out.println("\nEnter the make of the product you want to delete");	
		String make = input.nextLine();
		
		
		//Get the user to enter the model of the product that they want to delete and pass it into a string called model
		System.out.println("\nEnter the model of the product you want to delete");
		String model = input.nextLine();
		
		//Loop through each product
		for (Product prod : product) {
			//If there's a product that matches the entered make and model
			if (prod.getProMake().equals(make) && prod.getProModel().equals(model)) {
			//Set this product to null, this means it will be deleted later on in garbage collection	
				prod = null;
			}
		}			
		}
	}
	
	//This method prints all the products alongside their suppliers name
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
	
}
	
//This method is called whenever the user wants to add a new supplier

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

//This overloaded method is also used to create a new object of the product class, but this time it's called when data is being loaded
//from the text file and then the loaded in data is passed to this method so products can be made
public static void addNewProduct(String name, String make, String model, double price, int quantity, boolean discontinuted) {
	
	//Create a list of names containing all the names that are stored in the supplier array
	String [] names = new String [supplierArray.size()];
for (int i = 0; i < supplierArray.size(); i++) {		
		names[i] = supplierArray.get(i).getSupName();
	}
	
	//Initalise a default string called str that'll be used to store the product details
	
	try {

		//Initalise a default string called str that'll be used to store the product details
		String str = "";
		
		//Go through all the supplier names
		for (int n = 0; n < names.length; n++) 
		{
			//If the current supplier name matches the user entered supplier name
			if (names[n].contains(name)) {
				//Create a new product object using all the previously made variables and assosiciate it with the entered supplier
				Part2.Product product = new Part2.Product(make, model, price, quantity, discontinuted);
				supplierArray.get(n).addProduct(product);
				
				//String stores the product details and adds it to the pro array
				str = "\nSupplier Code: " + supplierArray.get(n).getSupCodeNum() +  "\n" +supplierArray.get(n).getSupName() + "\n" + product.getProDetails();	
				pro.add(str);
			}
		}

	}
	
	catch (ArrayIndexOutOfBoundsException ex) {
		System.out.println("\nThat supplier does not exist, please try again.");
		
	}
	
}


//This method is used to instaniate a new product object
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
				
				//String stores the product details and adds it to the pro array
				str = "\nSupplier Code: " + supplierArray.get(n).getSupCodeNum() +  "\n" +supplierArray.get(n).getSupName() + "\n" + product.getProDetails() + "\n";	
				pro.add(str);
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
