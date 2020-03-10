package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Boundary.MoblimaApp;
import Entity.AdultTicket;
import Entity.Cinema;
import Entity.ContentUpdater;
import Entity.Holiday;
import Entity.Movie;
import Entity.Receipt;
import Entity.Review;
import Entity.SeniorTicket;
import Entity.StudentTicket;
import Entity.Timings;

/**
 * To manage movie details. It contains the abstract method from the abstrat
 * class ContentUpdater.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class MovieMgr extends ContentUpdater {
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * To show the seat availability of the selected movie
	 */
	public static void showMovie() { // showing the cinema seats
		ArrayList<Timings> movieNames = new ArrayList<Timings>();
		ArrayList<String> booked = new ArrayList<String>();
		int index = 1;
		int a = 0;
		try {
			movieNames = Timings.readTimings(MoblimaApp.timingsFile);
			booked = readBooked(MoblimaApp.bookedFile);
			for (int k = 0; k < movieNames.size(); k++) {
				Timings timingName = (Timings) movieNames.get(k);
				System.out.println(index + ") " + timingName.getName() + "(" + timingName.gettypeofMovie() + ")");
				System.out.println("Date: " + timingName.getDay() +", "+ timingName.getDate() + "/"+timingName.getMonth());
				System.out.println("Class: " +timingName.gettypeofCinema() + ", Hall " +timingName.gethallNum());
				System.out.println("Timing: " + timingName.getTiming() );
				System.out.println("Cineplex Name: " + timingName.getcineplexName());
				System.out.println("");
				index++;
			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		Scanner sc = new Scanner(System.in);
		int selection = 0;
		while (selection == 0) {
			try {
				selection = sc.nextInt();
				if (selection > movieNames.size() || selection < 0) {
					System.out.println("Enter valid movie");
					selection = 0;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Enter Valid Integer");
				continue;
			}
		}

		int movieDate = ((Timings) movieNames.get(selection - 1)).getDate();
		String movieDateS = Integer.toString(movieDate);
		int movieMonth = ((Timings) movieNames.get(selection - 1)).getMonth();
		String movieMonthS = Integer.toString(movieMonth);
		String movieName = ((Timings) movieNames.get(selection - 1)).getName();
		String movieTime = ((Timings) movieNames.get(selection - 1)).getTiming();
		String option = movieName + movieTime + movieDateS + movieMonthS;
		for (a = 0; a < booked.size(); a++) {
			if (booked.get(a).compareTo(option) == 0) {
				Cinema.showSeats(booked.get(a) + ".txt");
				break;
			}

		}
		if (a == booked.size()) {
			Cinema.showSeats("Clean_Slate.txt");
		}

	}

	/**
	 * To show the movies that are now showing in cinemas
	 * 
	 * @param filename Input the filename that contains the data
	 * @return Returns the list of movies that are now showing
	 * @throws IOException If an input or output exception occurred
	 */
	public static ArrayList<Movie> showavailMovie(String filename) throws IOException {
		ArrayList<Movie> movieList;
		ArrayList<Movie> movieShowing = new ArrayList<Movie>();
		Scanner sc = new Scanner(System.in);
		int j = 1;
		movieList = readMovie(filename);
		for (int i = 0; i < movieList.size(); i++) {
			Movie movie = (Movie) movieList.get(i);
			if (movie.getStatus().equals("Showing") || movie.getStatus().equals("Preview") ) {
				System.out.println(j + ") " + movie.getName());
				j++;
				movieShowing.add(movie);
			}
		}
		return movieShowing;
	}

	/*
	 * First, the movies that are now showing will be printed. After the user has
	 * selected a specific movie, the available timings of that selected movie will
	 * be printed. The seat layout showing the availability of seats of that movie
	 * timing will be printed next and users will be asked to enter the number of
	 * seats they would like to book. They would select and book the seats they want
	 * by entering the seat numbers. When the seat is selected by the user, the
	 * seating plan would be updated to show that it is taken by changing from [ ]
	 * to [X]. Following that, they are required to specify the number of the
	 * student, adult and senior tickets respectively. If this number exceeds or is
	 * less than the number of tickets they wanted to booked before, an exception
	 * will be raised and the user would be asked to enter the correct number of
	 * tickets. Each ticket has different prices based on the type of ticket
	 * (adult/student/senior), type of movie (2D/3D) and the date of the movie
	 * (weekend price/weekday price). The pricing for each ticket will be retrieved.
	 * Then, the title, name of movie, PG Rating of the movie, type of movie, type
	 * of Cinema, time, movie day, hall, seat number of seats booked, transactionID
	 * and cost will be returned as a Receipt(entity).
	 */
	public static Receipt chooseMovie(String filename, String moviename) throws IOException {
		ArrayList timingAL = new ArrayList();
		ArrayList timingList = Timings.readTimings(MoblimaApp.timingsFile);
		int index = 1;
		System.out.println("Choose the timing: ");
		for (int j = 0; j < timingList.size(); j++) {
			Timings timing = (Timings) timingList.get(j);
			if (timing.getName().equals(moviename)) {
				System.out.println(index + ") " + timing.getName() + " " + timing.getPGRating() + " ("
						+ timing.gettypeofMovie() + ")");
				System.out.println(timing.gettypeofCinema() + ", Hall " + timing.gethallNum());
				System.out.println("Date: " + timing.getDay() + ", " + timing.getDate() + "/" + timing.getMonth());
				System.out.println("Timing: " + timing.getTiming());
				System.out.println("Cineplex Name: " + timing.getcineplexName());
				System.out.println();
				index++;
				timingAL.add(timing);
			}
		}

		Scanner sc = new Scanner(System.in);
		int timingchoice = 0;
		File temp;
		while (timingchoice == 0) {
			try {
				timingchoice = sc.nextInt();
				if (timingchoice > timingAL.size() || timingchoice < 0) {
					System.out.println("Enter valid timing");
					timingchoice = 0;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Enter valid Integer");
				continue;
			}
		}
		int movieDate = ((Timings) timingAL.get(timingchoice - 1)).getDate();
		String movieDateS = Integer.toString(movieDate);
		int movieMonth = ((Timings) timingAL.get(timingchoice - 1)).getMonth();
		String movieMonthS = Integer.toString(movieMonth);
		String movieName = ((Timings) timingAL.get(timingchoice - 1)).getName();
		String movieTime = ((Timings) timingAL.get(timingchoice - 1)).getTiming();
		String filename1 = movieName + movieTime + movieDateS + movieMonthS;
		ArrayList<String> books = new ArrayList<String>();
		books = readBooked(MoblimaApp.bookedFile);
		String loc = null;
		int a;
		for (a = 0; a < books.size(); a++) {
			if (filename1.compareTo(books.get(a)) == 0) {
				String loc1 = books.get(a) + ".txt";
				Cinema.showSeats(loc1);
				loc = loc1;
				break;
			}

		}
		if (a == books.size()) {
			File file = new File(filename1 + ".txt"); // put the file inside the folder
			file.createNewFile(); // create the file
			String source = ("Clean_Slate.txt");
			filecopy(source, file.getAbsolutePath());
			Cinema.showSeats(file.getAbsolutePath());
			addToBook("Booked.txt", filename1);
			loc = file.getAbsolutePath();
		}

		int numofSeats = 0;
		int i = 0;
		String userSeat;
		String str ;
		char alphabet;
		char tempalpha ;
		int seatNum;
		ArrayList<String> seatArray = new ArrayList<String>();
		System.out.println("Enter number of seats to book ");
		while (numofSeats == 0) {
			try {
				numofSeats = sc.nextInt();
				if(numofSeats < 0 ) {
					System.out.println("Enter positive number ");
					continue;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Enter valid Integer");
				continue;
			}
		}
		while (i < numofSeats) {
			try {
				System.out.print("Enter row alphabet (A to G)");
				str = sc.next(); 
				alphabet = str.charAt(0);
				tempalpha = Character.toUpperCase(alphabet) ;
				if (Character.valueOf(tempalpha).compareTo(Character.valueOf('A')) < 0 || Character.valueOf(tempalpha).compareTo(Character.valueOf('G')) > 0) {
					System.out.println("Invalid alphabet, try again");
					continue;
				} else {
					System.out.print("Enter seat number");
					seatNum = sc.nextInt();
					if (seatNum < 1 || seatNum > 6) {
						System.out.println("Invalid seat number, try again");
						continue;
					}
				}
				
				userSeat = (alphabet + Integer.toString(seatNum)).toUpperCase();
				int q = 0;
				for (q = 0; q < seatArray.size(); q++) {
					if (userSeat.equals(seatArray.get(q))) {
						System.out.print("Cannot book same seat twice. Try again.");
						break;
					}
				}

				if (q == i) {
					modifyFile(loc, userSeat, "X");
					seatArray.add(userSeat);
					i++;
				}

				else {
					continue;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Enter valid Integer");
				i = 0;
				continue;
			}
		}

		String allSeats = seatArray.get(0);
		i = 1;
		while (i < numofSeats) {
			allSeats = allSeats + ", " + seatArray.get(i);
			i++;
		}

		int x = 0;
		int numofstudT = 0;
		int numofAT = 0;
		int numofST = 0;
		while (x < numofSeats) {
			try {
				System.out.println("Enter number of student tickets");
				numofstudT = sc.nextInt();
				x += numofstudT;
				if (x > numofSeats) {
					System.out.println("Too many tickets. Try again");
					x = 0;
					numofstudT = 0;
					continue;
				}
				System.out.println("Enter number of adult tickets");
				numofAT = sc.nextInt();
				x += numofAT;
				if (x > numofSeats) {
					System.out.println("Too many tickets. Try again");
					x = 0;
					numofAT = 0;
					continue;
				}
				System.out.println("Enter number of senior tickets");
				numofST = sc.nextInt();
				x += numofST;
				if (x > numofSeats) {
					System.out.println("Too many tickets. Try again");
					x = 0;
					numofAT = 0;
					numofST = 0;
					continue;
				} else if (x < numofSeats) {
					System.out.println("Too little tickets. Try again");
					x = 0;
					numofAT = 0;
					numofstudT = 0;
					numofST = 0;
					continue;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Enter valid Integer");
				x = 0;
				numofAT = 0;
				numofstudT = 0;
				numofST = 0;
				continue;
			}
		}

		ArrayList pricingAL = TicketMgr.readPrices(MoblimaApp.pricingFile);
		ArrayList<Holiday> holidayAL = Holiday.readHoliday(MoblimaApp.holidayFile);
		double cost = 0;
		String movieday = ((Timings) timingAL.get(timingchoice - 1)).gettypeofDay();
		boolean holidaystatus = false;
		int date = ((Timings) timingAL.get(timingchoice - 1)).getDate();
		int month = ((Timings) timingAL.get(timingchoice - 1)).getMonth();
		for (int k = 0; k < holidayAL.size(); k++) {
			Holiday holiday = (Holiday) holidayAL.get(k);
			if (date == holiday.getDay() && month == holiday.getMonth()) {
				holidaystatus = true;
				break;
			}

		}

		for (int j = 0; j < pricingAL.size(); j++) {
			if (((Timings) timingAL.get(timingchoice - 1)).gettypeofCinema().equals("Cathay")) {
				if (((Timings) timingAL.get(timingchoice - 1)).gettypeofMovie().equals("2D")) {
					if (movieday.equals("Weekday") && holidaystatus != true) {
						cost = Double.parseDouble(AdultTicket.getCathay2DWeekdayAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getCathay2DWeekdayChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getCathay2DWeekdaySenior(pricingAL)) * numofST;
						break;
					} else {
						cost = Double.parseDouble(AdultTicket.getCathay2DWeekendAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getCathay2DWeekendChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getCathay2DWeekendSenior(pricingAL)) * numofST;
						break;
					}
				} else {
					if (movieday.equals("Weekday") && holidaystatus != true) {
						cost = Double.parseDouble(AdultTicket.getCathay3DWeekdayAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getCathay3DWeekdayChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getCathay3DWeekdaySenior(pricingAL)) * numofST;
						break;
					} else {
						cost = Double.parseDouble(AdultTicket.getCathay3DWeekendAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getCathay3DWeekendChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getCathay3DWeekendSenior(pricingAL)) * numofST;
						break;
					}
				}
			} else {
				if (((Timings) timingAL.get(timingchoice - 1)).gettypeofMovie().equals("2D")) {
					if (movieday.equals("Weekday") && holidaystatus != true) {
						cost = Double.parseDouble(AdultTicket.getPlat2DWeekdayAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getPlat2DWeekdayChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getPlat2DWeekdaySenior(pricingAL)) * numofST;
						break;
					} else {
						cost = Double.parseDouble(AdultTicket.getPlat2DWeekendAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getPlat2DWeekendChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getPlat2DWeekendSenior(pricingAL)) * numofST;
						break;
					}
				} else {
					if (movieday.equals("Weekday") && holidaystatus != true) {
						cost = Double.parseDouble(AdultTicket.getPlat3DWeekdayAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getPlat3DWeekdayChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getPlat3DWeekdaySenior(pricingAL)) * numofST;
						break;
					} else {
						cost = Double.parseDouble(AdultTicket.getPlat3DWeekendAdult(pricingAL)) * numofAT
								+ Double.parseDouble(StudentTicket.getPlat3DWeekendChild(pricingAL)) * numofstudT
								+ Double.parseDouble(SeniorTicket.getPlat3DWeekendSenior(pricingAL)) * numofST;
						break;
					}
				}
			}
		}

		String name = ((Timings) timingAL.get(timingchoice - 1)).getName();
		String PGRating = ((Timings) timingAL.get(timingchoice - 1)).getPGRating();
		String movieDay = ((Timings) timingAL.get(timingchoice - 1)).getDay() + ", "
				+ Integer.toString(((Timings) timingAL.get(timingchoice - 1)).getDate()) + "/"
				+ Integer.toString(((Timings) timingAL.get(timingchoice - 1)).getMonth());
		String time = ((Timings) timingAL.get(timingchoice - 1)).getTiming();
		String typeofMovie = ((Timings) timingAL.get(timingchoice - 1)).gettypeofMovie();
		String typeofCinema = ((Timings) timingAL.get(timingchoice - 1)).gettypeofCinema();
		String hall = "Hall " + Integer.toString(((Timings) timingAL.get(timingchoice - 1)).gethallNum());
		String cinemaCode = "HA" + Integer.toString(((Timings) timingAL.get(timingchoice - 1)).gethallNum());
		String transactionID = cinemaCode + CurrentDateTime();
		int countSeats = 0;
		for (i = 0; i < numofAT; i++) {
			AdultTicket at = new AdultTicket(name, PGRating, typeofMovie, typeofCinema, time, movieDay, hall,
					seatArray.get(countSeats), transactionID, cost);
			countSeats++;
		}
		for (i = 0; i < numofST; i++) {
			SeniorTicket at = new SeniorTicket(name, PGRating, typeofMovie, typeofCinema, time, movieDay, hall,
					seatArray.get(countSeats), transactionID, cost);
			countSeats++;
		}
		for (i = 0; i < numofstudT; i++) {
			StudentTicket at = new StudentTicket(name, PGRating, typeofMovie, typeofCinema, time, movieDay, hall,
					seatArray.get(countSeats), transactionID, cost);
			countSeats++;
		}

		Receipt receipt = new Receipt(name, PGRating, typeofMovie, typeofCinema, time, movieDay, hall, allSeats,
				transactionID, cost);
		return receipt;
	}

	/**
	 * Add information to the data (textfile)
	 * 
	 * @param destination Filename of location for string to be added
	 * @param name        Name of string to add
	 */
	public static void addToBook(String destination, String name) {
		try (FileWriter fw = new FileWriter(destination, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(name);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage()) ;
		}

	}

	/**
	 * Get the current date and time
	 * 
	 * @return Returns the current data and time according to the format
	 *         'YYYYMMddHHMM' (year, month, day, hour, minutes)
	 */
	public static String CurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHMM");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * Get the details of the movie
	 * 
	 * @param filename The name of the file
	 * @return Returns the movie name, PG Rating, type of Movie, Status of movie,
	 *         Director, Cast and Synopsis
	 * @throws IOException If an input or output exception occurred
	 */
	public static ArrayList<Movie> readMovie(String filename) throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<Movie> alr = new ArrayList<Movie>();// to store Movie data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			String name = star.nextToken().trim();
			String PGRating = star.nextToken().trim();
			String typeofMovie = star.nextToken().trim();
			String Status = star.nextToken().trim();
			String Director = star.nextToken().trim();
			String Cast = star.nextToken().trim();
			String Synopsis = star.nextToken().trim();
			Movie movie = new Movie(name, PGRating, typeofMovie, Status, Director, Cast, Synopsis);
			alr.add(movie);
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
		List<String> data = new ArrayList();
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
	 * Copy one file to another
	 * 
	 * @param source      Name of source file
	 * @param destination Name of destination file
	 */
	public static void filecopy(String source, String destination) {
		try {
			File fin = new File(source);
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			FileWriter fstream = new FileWriter(destination, true);
			BufferedWriter out = new BufferedWriter(fstream);

			String aLine = null;
			while ((aLine = in.readLine()) != null) {
				// Process each line and add output to Dest.txt file
				out.write(aLine);
				out.newLine();
			}

			// do not forget to close the buffer reader
			in.close();

			// close buffer writer
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Search for the movie that the user wants to know the details of. If the
	 * search is successful, the details of the movie consisting of the name, PG
	 * rating, director, status, cast, synopsis, overall rating, past ratings and
	 * past reviews will be printed. If the search is unsuccessful, the movie is
	 * unavailable and no details would be printed.
	 * 
	 * @param al Arraylist that consists of Movies
	 * @param rw Arraylist that consists of Reviews
	 */
	public static void searchMovie(ArrayList<Movie> al, ArrayList<Review> rw) {
		String moviename;
		boolean check = false;
		while (check == false) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the movie you are searching for. Enter 0 to go back.");
			moviename = scan.nextLine();
			if (moviename.equals("0")) {
				check = true;
			}
			String movienamenospace = moviename.replaceAll("\\s+", "").toLowerCase();
			for (int i = 0; i < al.size(); i++) {
				if ((al.get(i).getName().toLowerCase().replaceAll("\\s+", "")).contains(movienamenospace)) {

					System.out.println("Name: " + al.get(i).getName() + "(" + al.get(i).gettypeofMovie() + ")");
					System.out.println("PG Rating: " + al.get(i).getPGRating());
					System.out.println("Director: " + al.get(i).getDirector());
					System.out.println("Status: " + al.get(i).getStatus());
					System.out.println("Cast: " + al.get(i).getCast());
					System.out.println("Synopsis: " + al.get(i).getSynopsis());
					for (int j = 0; j < rw.size(); j++) {
						if (rw.get(j).getmovieName().equals(al.get(i).getName())) {
							if (rw.get(j).getratingArray().size() == 0) {
								System.out.println("Rating: No ratings yet.");
							} else {
								System.out.println("Rating: " + rw.get(j).getoverallRating(rw.get(j)));
								System.out.println("Past Rating(s): ");
								rw.get(i);
								Review.printmovieRating(rw.get(j));
							}
							break;
						}
					}
					for (int j = 0; j < rw.size(); j++) {
						if (rw.get(j).getmovieName().equals(al.get(i).getName())) {
							if (rw.get(j).getreviewArray().size() == 0) {
								System.out.println("Reviews: No reviews yet.");
							} else {
								System.out.println("Reviews: ");
								rw.get(j);
								Review.printReviews(rw.get(j));
							}
							break;
						}
					}
					System.out.println();
					check = true;
					break;
				}
			}
			if (check == false) {
				System.out.println("Movie Unavailable. Please search for another movie.");
				System.out.println();
			}
		}
	}

	/**
	 * Reads the booked file
	 * 
	 * @param filename Name of file
	 * @return Returns the data of the file
	 * @throws IOException If an input or output exception occurred
	 */
	public static ArrayList<String> readBooked(String filename) throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<String> alr = new ArrayList<String>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			alr.add(st);
		}
		return alr;
	}

	/**
	 * Saves Movie to the file
	 * 
	 * @param movie Movie to be saved
	 * @throws IOException If an input or output exception occurred
	 */
	public static void saveMovie(String movie) throws IOException {
		File file = new File(MoblimaApp.movieFile);
		FileWriter fr = new FileWriter(file, true);
		fr.write("\n");
		fr.write(movie);
		fr.close();
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
			String newContent = oldContent.replaceAll(oldString, newString);

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
