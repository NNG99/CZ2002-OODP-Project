package Boundary;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the MOBLIMA Application startup page.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */
public class MoblimaApp {
	/**
	 * movieFile references text file 'MovieDetails.txt'
	 */
	public static final String movieFile = "MovieDetails.txt";
	/**
	 * cinemaLayout references text file 'CinemaLayout.txt'
	 */
	public static final String cinemaLayout = "CinemaLayout.txt";
	/**
	 * timingsFile references text file 'Dates.txt'
	 */
	public static final String timingsFile = "Dates.txt";
	/**
	 * pricingFile references text file 'Pricing.txt'
	 */
	public static final String pricingFile = "Pricing.txt";
	/**
	 * holidayFile references text file 'Holiday.txt'
	 */
	public static final String holidayFile = "Holiday.txt";
	/**
	 * bookedFile references text file 'Booked.txt'
	 */
	public static final String bookedFile = "Booked.txt";
	/**
	 * A staff with a StaffID = 'Staff123' is created.
	 */
	private static final String staffID = "Staff123";
	/**
	 * The password for the account StaffID = 'Staff123' would be 'qwerty123!'
	 */
	private static final String staffPW = "qwerty123!";

	/**
	 * Application users are required to select if they are a staff or a user. Users
	 * would select option 1 if they are a staff. Staff are required to enter their
	 * StaffID and password and only 3 tries are given if any fields are entered
	 * incorrectly. Users would select option 2 if they a movie-goer.
	 * 
	 * @param args the main args
	 * @throws IOException If an input or output exception occurred
	 */
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================");
		System.out.println("|Welcome to MOBLIMA!	|");
		System.out.println("|Login As:		| ");
		System.out.println("|1. Staff		|");
		System.out.println("|2. User		|");
		System.out.println("|3. Exit		|");	
		System.out.println("=========================");
		int choice;
		while (true) {
			try {
				choice = sc.nextInt();
				if (choice < 1 || choice > 3) {
					System.out.println("Enter valid integer");
					continue;
				}
				if (choice == 1) {
					int tries = 3;
					while (tries > 0) {
						System.out.println("Enter ID:");
						String idInput = sc.next();
						System.out.println("Enter Password:");
						String pwInput = sc.next();
						if (idInput.equals(staffID) && pwInput.equals(staffPW)) {
							System.out.println("Login success!");
							StaffUI.main(null);
							break;
						} else
							System.out.println("Login failed");
						tries -= 1;
						System.out.println("You have " + tries + " tries left");
					}
					System.out.println("No more tries/");
				}
				if (choice == 2) {
					UserUI.main(null);
				}
				if(choice == 3) {
					System.out.println("Thank you for using our MOBLIMA app");
					break;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Please input a valid integer! ");
				continue;
			}
		}
	}

}
