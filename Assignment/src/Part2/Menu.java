package Part2;

import java.util.Scanner;

public class Menu {
	private String [] options;
	private String title;
	private Scanner input;
	
	public Menu( String title, String[] options) {
		this.title = title;
		copyOptions(options);
		input = new Scanner(System.in);
	}
	
	public int getChoice() {
		System.out.print("Enter choice: ");
		try {
		String choice = input.nextLine();
		int num = Integer.parseInt(choice);
		
		return num;
		}
		catch(NumberFormatException e) {
			System.out.println("\n++++++++++++++++++");
			System.out.println("+ Invalid Number +");
			System.out.println("++++++++++++++++++");
			return 0;
		}
	}
	
	public void display() {
		if (title != null && options !=null) {
			System.out.println(title);
			for(int i=0;i<title.length();i++) {
				System.out.print("++++++");
			}
			System.out.println("\n");
			int count = 1;
			
			for(String str : options) {
				System.out.println(count+". "+str);
				count++;
			}
			System.out.println();
		}
		else {
			// title and/or options are not valid
			System.out.println("Menu not defined.");
		}
	}

	private void copyOptions(String data[]) {
		if ( data != null) {
			this.options = new String[data.length];
			for(int index=0;index<data.length;index++) {
				options[index] = data[index];
			}
		}
		else {
			options = null;
		}
	}
	
}
