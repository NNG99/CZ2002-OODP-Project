package Control;

import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Boundary.MoblimaApp;
import Entity.AdultTicket;
import Entity.ContentUpdater;
import Entity.SeniorTicket;
import Entity.StudentTicket;

/**
 * To manage the ticket details. It contains the abstract method from the
 * abstrat class ContentUpdater.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */
public class TicketMgr extends ContentUpdater {
	/**
	 * The day of the week
	 */
	private String dayofWeek;
	/**
	 * The type of movie (2D/3D)
	 */
	private String typeofMovie;
	/**
	 * The class of cinema (Cathay/Platinum)
	 */
	private String classofCinema;
	/**
	 * The age of the movie goer
	 */
	private String ageofmovieGoer;
	/**
	 * The price of the ticket
	 */
	private Double price;
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Create a new TicketMgr given the day of the week, type of movie, class of
	 * cinema, age of movie goer and price.
	 * 
	 * @param dayofWeek      This is the day of the week.
	 * @param typeofMovie    This is the type of movie.
	 * @param classofCinema  This is the class of cinema.
	 * @param ageofmovieGoer This is the age of movie goer.
	 * @param price          This is the price of ticket.
	 */
	public TicketMgr(String dayofWeek, String typeofMovie, String classofCinema, String ageofmovieGoer, Double price) {
		this.dayofWeek = dayofWeek;
		this.typeofMovie = typeofMovie;
		this.classofCinema = classofCinema;
		this.ageofmovieGoer = ageofmovieGoer;
		this.price = price;
	}

	/**
	 * Reads line by line from a text file consisting of prices and add it into an
	 * ArrayList.
	 * 
	 * @param filename This is the directory of the file we want to read.
	 * @return an Array list of prices.
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static ArrayList<TicketMgr> readPrices(String filename) throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<TicketMgr> alr = new ArrayList<TicketMgr>();// to store Price data
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String dayofWeek = star.nextToken().trim();
			String typeofMovie = star.nextToken().trim();
			String classofCinema = star.nextToken().trim();
			String ageofmovieGoer = star.nextToken().trim();
			double price = Double.parseDouble(star.nextToken().trim());
			// create Price object from file data
			TicketMgr pricing = new TicketMgr(dayofWeek, typeofMovie, classofCinema, ageofmovieGoer, price);
			// add to Pricing List
			alr.add(pricing);
		}
		return alr;
	}

	/**
	 * Reads data from the file
	 * 
	 * @param fileName Filename of file to be read
	 * @return Return the data of the file
	 * @throws IOException If an input or output exception occurred
	 */
	public static List<String> read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}

	/**
	 * View the all possible prices of movie tickets This includes weekdays,
	 * weekends, public holidays, 2D, 3D and also accounts for people of different
	 * age groups.
	 */
	public static void viewPrices() {
		ArrayList<TicketMgr> pricesAL;
		try {
			pricesAL = readPrices(MoblimaApp.pricingFile);
			System.out.println("===================================");
			System.out.println("Current ticket prices            ");
			System.out.println("");
			System.out.println("2D Cathay: ");
			System.out.println("-----------------------------------");
			System.out.println("Weekdays (Monday to Thursday): ");
			System.out.println("1. Adult			:$" + AdultTicket.getCathay2DWeekdayAdult(pricesAL));
			System.out.println("2. Children under 7 or Students	:$" + StudentTicket.getCathay2DWeekdayChild(pricesAL));
			System.out.println("3. Senior Citizen		:$" + SeniorTicket.getCathay2DWeekdaySenior(pricesAL));
			System.out.println("");
			System.out.println("Weekends & PH: ");
			System.out.println("4. Adult			:$" + AdultTicket.getCathay2DWeekendAdult(pricesAL));
			System.out.println("5. Children under 7 or Students	:$" + StudentTicket.getCathay2DWeekendChild(pricesAL));
			System.out.println("6. Senior Citizen		:$" + SeniorTicket.getCathay2DWeekendSenior(pricesAL));
			System.out.println("");
			System.out.println("3D Cathay: ");
			System.out.println("-----------------------------------");
			System.out.println("Weekdays (Monday to Thursday): ");
			System.out.println("7. Adult			:$" + AdultTicket.getCathay3DWeekdayAdult(pricesAL));
			System.out.println("8. Children under 7 or Students :$" + StudentTicket.getCathay3DWeekdayChild(pricesAL));
			System.out.println("9. Senior Citizen		:$" + SeniorTicket.getCathay3DWeekdaySenior(pricesAL));
			System.out.println("");
			System.out.println("Weekends & PH: ");
			System.out.println("10. Adult			:$" + AdultTicket.getCathay3DWeekendAdult(pricesAL));
			System.out.println("11. Children under 7 or Students:$" + StudentTicket.getCathay3DWeekendChild(pricesAL));
			System.out.println("12. Senior Citizen		:$" + SeniorTicket.getCathay3DWeekendSenior(pricesAL));
			System.out.println("");
			System.out.println("2D Platinum Suites: ");
			System.out.println("-----------------------------------");
			System.out.println("Weekdays (Monday to Thursday): ");
			System.out.println("13. Adult			:$" + AdultTicket.getPlat2DWeekdayAdult(pricesAL));
			System.out.println("14. Children under 7 or Students:$" + StudentTicket.getPlat2DWeekdayChild(pricesAL));
			System.out.println("15. Senior Citizen		:$" + SeniorTicket.getPlat2DWeekdaySenior(pricesAL));
			System.out.println("");
			System.out.println("Weekends & PH: ");
			System.out.println("16. Adult			:$" + AdultTicket.getPlat2DWeekendAdult(pricesAL));
			System.out.println("17. Children under 7 or Students:$" + StudentTicket.getPlat2DWeekendChild(pricesAL));
			System.out.println("18. Senior Citizen		:$" + SeniorTicket.getPlat2DWeekendSenior(pricesAL));
			System.out.println("");
			System.out.println("3D Platinum Suites: ");
			System.out.println("-----------------------------------");
			System.out.println("Weekdays (Monday to Thursday): ");
			System.out.println("19. Adult			:$" + AdultTicket.getPlat3DWeekdayAdult(pricesAL));
			System.out.println("20. Children under 7 or Students:$" + StudentTicket.getPlat3DWeekdayChild(pricesAL));
			System.out.println("21. Senior Citizen		:$" + SeniorTicket.getPlat3DWeekdaySenior(pricesAL));
			System.out.println("");
			System.out.println("Weekends & PH: ");
			System.out.println("22. Adult			:$" + AdultTicket.getPlat3DWeekendAdult(pricesAL));
			System.out.println("23. Children under 7 or Students:$" + StudentTicket.getPlat3DWeekendChild(pricesAL));
			System.out.println("24. Senior Citizen		:$" + SeniorTicket.getPlat3DWeekendSenior(pricesAL));
			System.out.println("");
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

	}

	/**
	 * Get the day of the week
	 * 
	 * @return Day of the week
	 */
	public String getdayofWeek() {
		return dayofWeek;
	}

	/**
	 * Get the type of movie
	 * 
	 * @return type of move
	 */
	public String gettypeofMovie() {
		return typeofMovie;
	}

	/**
	 * Get the class of cinema
	 * 
	 * @return the class of the cinema
	 */
	public String getclassofCinema() {
		return classofCinema;
	}

	/**
	 * Get the age of the movie goer
	 * 
	 * @return the age of movie goer
	 */
	public String getageofmovieGoer() {
		return ageofmovieGoer;
	}

	/**
	 * Get the price of the ticket
	 * 
	 * @return the price of the ticket
	 */
	public double getprice() {
		return price;
	}

	/**
	 * Show all possible prices and ask the user to select which price they would
	 * want to change. After selecting the price, the user will input a new price
	 * and the new price will be updated to the text file containing all the prices.
	 * 
	 * @param choice This is the user choice.
	 * @throws IOException If an input or output exception occurred
	 */
	@SuppressWarnings("resource")
	public static void changePrice(int choice) throws InputMismatchException, IOException {
		try {
			Scanner sc = new Scanner(System.in);
			ArrayList<TicketMgr> pricesAL2;
			String newPrice;
			String updatePrice;
			String originalPrice;
			String originalString;
			int dollar;
			int cents;
			pricesAL2 = readPrices(MoblimaApp.pricingFile);
			switch (choice) {
			case 1:
				System.out.println("Original Price is:		$" + AdultTicket.getCathay2DWeekdayAdult(pricesAL2));
				originalPrice = AdultTicket.getCathay2DWeekdayAdult(pricesAL2);
				originalString = "Weekdays|2D|Cathay|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Cathay|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 2:
				System.out.println("Original Price is:		$" + StudentTicket.getCathay2DWeekdayChild(pricesAL2));
				originalPrice = StudentTicket.getCathay2DWeekdayChild(pricesAL2);
				originalString = "Weekdays|2D|Cathay|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Cathay|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 3:
				System.out.println("Original Price is:		$" + SeniorTicket.getCathay2DWeekdaySenior(pricesAL2));
				originalPrice = SeniorTicket.getCathay2DWeekdaySenior(pricesAL2);
				originalString = "Weekdays|2D|Cathay|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Cathay|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 4:
				System.out.println("Original Price is:		$" + AdultTicket.getCathay2DWeekendAdult(pricesAL2));
				originalPrice = AdultTicket.getCathay2DWeekendAdult(pricesAL2);
				originalString = "Weekends|2D|Cathay|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Cathay|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 5:
				System.out.println("Original Price is:		$" + StudentTicket.getCathay2DWeekendChild(pricesAL2));
				originalPrice = StudentTicket.getCathay2DWeekendChild(pricesAL2);
				originalString = "Weekends|2D|Cathay|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Cathay|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 6:
				System.out.println("Original Price is:		$" + SeniorTicket.getCathay2DWeekendSenior(pricesAL2));
				originalPrice = SeniorTicket.getCathay2DWeekendSenior(pricesAL2);
				originalString = "Weekends|2D|Cathay|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Cathay|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 7:
				System.out.println("Original Price is:		$" + AdultTicket.getCathay3DWeekdayAdult(pricesAL2));
				originalPrice = AdultTicket.getCathay3DWeekdayAdult(pricesAL2);
				originalString = "Weekdays|3D|Cathay|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Cathay|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 8:
				System.out.println("Original Price is:		$" + StudentTicket.getCathay3DWeekdayChild(pricesAL2));
				originalPrice = StudentTicket.getCathay3DWeekdayChild(pricesAL2);
				originalString = "Weekdays|3D|Cathay|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Cathay|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 9:
				System.out.println("Original Price is:		$" + SeniorTicket.getCathay3DWeekdaySenior(pricesAL2));
				originalPrice = SeniorTicket.getCathay3DWeekdaySenior(pricesAL2);
				originalString = "Weekdays|3D|Cathay|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Cathay|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 10:
				System.out.println("Original Price is:		$" + AdultTicket.getCathay3DWeekendAdult(pricesAL2));
				originalPrice = AdultTicket.getCathay3DWeekendAdult(pricesAL2);
				originalString = "Weekends|3D|Cathay|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Cathay|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 11:
				System.out.println("Original Price is:		$" + StudentTicket.getCathay3DWeekendChild(pricesAL2));
				originalPrice = StudentTicket.getCathay3DWeekendChild(pricesAL2);
				originalString = "Weekends|3D|Cathay|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Cathay|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 12:
				System.out.println("Original Price is:		$" + SeniorTicket.getCathay3DWeekendSenior(pricesAL2));
				originalPrice = SeniorTicket.getCathay3DWeekendSenior(pricesAL2);
				originalString = "Weekends|3D|Cathay|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Cathay|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 13:
				System.out.println("Original Price is:		$" + AdultTicket.getPlat2DWeekdayAdult(pricesAL2));
				originalPrice = AdultTicket.getPlat2DWeekdayAdult(pricesAL2);
				originalString = "Weekdays|2D|Platinum|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Platinum|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 14:
				System.out.println("Original Price is:		$" + StudentTicket.getPlat2DWeekdayChild(pricesAL2));
				originalPrice = StudentTicket.getPlat2DWeekdayChild(pricesAL2);
				originalString = "Weekdays|2D|Platinum|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Platinum|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 15:
				System.out.println("Original Price is:		$" + SeniorTicket.getPlat2DWeekdaySenior(pricesAL2));
				originalPrice = SeniorTicket.getPlat2DWeekdaySenior(pricesAL2);
				originalString = "Weekdays|2D|Platinum|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|2D|Platinum|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 16:
				System.out.println("Original Price is:		$" + AdultTicket.getPlat2DWeekendAdult(pricesAL2));
				originalPrice = AdultTicket.getPlat2DWeekendAdult(pricesAL2);
				originalString = "Weekends|2D|Platinum|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Platinum|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 17:
				System.out.println("Original Price is:		$" + StudentTicket.getPlat2DWeekendChild(pricesAL2));
				originalPrice = StudentTicket.getPlat2DWeekendChild(pricesAL2);
				originalString = "Weekends|2D|Platinum|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Platinum|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 18:
				System.out.println("Original Price is:		$" + SeniorTicket.getPlat2DWeekendSenior(pricesAL2));
				originalPrice = SeniorTicket.getPlat2DWeekendSenior(pricesAL2);
				originalString = "Weekends|2D|Platinum|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|2D|Platinum|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 19:
				System.out.println("Original Price is:		$" + AdultTicket.getPlat3DWeekdayAdult(pricesAL2));
				originalPrice = AdultTicket.getPlat3DWeekdayAdult(pricesAL2);
				originalString = "Weekdays|3D|Platinum|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Platinum|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 20:
				System.out.println("Original Price is:		$" + StudentTicket.getPlat3DWeekdayChild(pricesAL2));
				originalPrice = StudentTicket.getPlat3DWeekdayChild(pricesAL2);
				originalString = "Weekdays|3D|Platinum|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Platinum|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 21:
				System.out.println("Original Price is:		$" + SeniorTicket.getPlat3DWeekdaySenior(pricesAL2));
				originalPrice = SeniorTicket.getPlat3DWeekdaySenior(pricesAL2);
				originalString = "Weekdays|3D|Platinum|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekdays|3D|Platinum|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 22:
				System.out.println("Original Price is:		$" + AdultTicket.getPlat3DWeekendAdult(pricesAL2));
				originalPrice = AdultTicket.getPlat3DWeekendAdult(pricesAL2);
				originalString = "Weekends|3D|Platinum|Adult|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Platinum|Adult|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 23:
				System.out.println("Original Price is:		$" + StudentTicket.getPlat3DWeekendChild(pricesAL2));
				originalPrice = StudentTicket.getPlat3DWeekendChild(pricesAL2);
				originalString = "Weekends|3D|Platinum|Student|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Platinum|Student|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			case 24:
				System.out.println("Original Price is:		$" + SeniorTicket.getPlat3DWeekendSenior(pricesAL2));
				originalPrice = SeniorTicket.getPlat3DWeekendSenior(pricesAL2);
				originalString = "Weekends|3D|Platinum|Senior Citizen|" + originalPrice;
				while (true) {
					System.out.println("Enter how much dollars it cost: ");
					dollar = sc.nextInt();
					if (dollar >= 0) {
						break;
					}

				}
				while (true) {
					System.out.println("Enter how much cents it costs: ");
					cents = sc.nextInt();
					if (cents < 100 && cents > 0) {
						break;
					}
				}
				newPrice = Integer.toString(dollar) + "." + Integer.toString(cents);
				updatePrice = "Weekends|3D|Platinum|Senior Citizen|" + newPrice;
				modifyFile(MoblimaApp.pricingFile, originalString, updatePrice);
				System.out.println("Update Successful!");
				break;
			default:
				System.out.println("Error: Enter an integer between 1-24!");
			}

		} catch (InputMismatchException e) {
			System.out.println("Input an integer! ");
		}

	}

	/**
	 * Mofifies the contents of the file
	 * 
	 * @param filePath  Name of file that would be modified
	 * @param oldString Old string that will be replaced
	 * @param newString New string that will replace the old string
	 */
	public static void modifyFile(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);

		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));

			// Reading all the lines of input text file into oldContent

			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();

			}

			// Replacing oldString with newString in the oldContent

			String newContent = oldContent.replace(oldString, newString);

			// Rewriting the input text file with newContent

			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources

				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
