package Boundary;

import Control.*;
import Entity.*;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * This is the page displayed after the staff has successfully logged in.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */
public class StaffUI {
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Creates a new StaffUI
	 */
	public StaffUI() {
	}

	/**
	 * Staff are able to select from the options listed which action they would like
	 * to perform. To configure the system settings, option 1 is selected. Staff
	 * would be able to configure ticket prices, holiday dates, add movie, movie
	 * showtime, movie status and movie details. To view current movie listings,
	 * option 2 is chosen. To view movie showtimes and the seating availability,
	 * option 3 is chosen. To list the top 5 movies, option 4 is chosen. To reset
	 * all the seat availability of movies, option 5 is chosen. To log out from the
	 * system, option 6 is chosen.
	 * 
	 * @param args This is the main args
	 * @throws IOException If an input or output exception occurred
	 */
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int userChoice = 0;
		while (userChoice != 6) {
			System.out.println("===================================");
			System.out.println("|What would you like to do?       |");
			System.out.println("===================================");
			System.out.println("|Staff Action:                    |");
			System.out.println("|1. Configure system settings     |");
			System.out.println("|2. View current movie Listings   |");
			System.out.println("|3. View movie Showtimes and	  |");
			System.out.println("|   available seats	          |");
			System.out.println("|4. List top 5 movies.            |");
			System.out.println("|5. Reset Booking                 |");
			System.out.println("|6. Log Out                       |");
			System.out.println("===================================");
			System.out.print("Choice: ");
			try {
				userChoice = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! ");
				System.out.println("Please input a number!");
				sc.nextLine();
				continue;
			}

			if (0 >= userChoice || userChoice >= 7) {
				System.out.println("Invalid Input!");
				System.out.println("Please enter a value between 1 to 6!");
				System.out.println("Choice: ");

				continue;
			} // invalid input if()

			else if (userChoice == 1) {
				int settingChoice = 0;

				while (settingChoice != 7) {
					System.out.println("===================================");
					System.out.println("|System Configuration:            |");
					System.out.println("|1. Ticket Prices                 |");
					System.out.println("|2. Holidays                      |");
					System.out.println("|3. Add Movie                     |");
					System.out.println("|4. Configure Showtime            |");
					System.out.println("|5. Update Status of Movie        |");
					System.out.println("|6. Update Movie Details          |");
					System.out.println("|7. Go Back                       |");
					System.out.println("===================================");
					System.out.print("Choice: ");
					try {
						settingChoice = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input! ");
						System.out.println("Please input a number!");
						sc.nextLine();
						continue;
					}

					if (0 >= settingChoice || settingChoice >= 8) {
						System.out.println("Invalid Input!");
						System.out.println("Please enter a value between 1 to 7!\n");
						System.out.print("Choice: ");
						continue;
					}

					if (settingChoice == 1) {
						int tickets = 0;

						while (tickets != 3) {
							System.out.println("===================================");
							System.out.println("|Ticket Prices                    |");
							System.out.println("|1. View pricing                  |");
							System.out.println("|2. Edit ticket pricing           |");
							System.out.println("|3. Back        		  |");
							System.out.println("===================================");
							System.out.print("Choice: ");
							try {
								tickets = sc.nextInt();
							} catch (InputMismatchException e) {
								System.out.println("Invalid input! ");
								System.out.println("Please input a number!");
								sc.nextLine();
								continue;
							}

							if (0 >= tickets || tickets >= 4) {
								System.out.println("Invalid Input!");
								System.out.println("Please enter a value between 1 to 3!\n");
								System.out.print("Choice: ");
								continue;
							}

							if (tickets == 1) {
								TicketMgr.viewPrices();
							}

							if (tickets == 2) {
								int changePrices = 0;
								while (true) {
									TicketMgr.viewPrices();
									System.out.println("Which prices do you want to update:");
									try {
										changePrices = sc.nextInt();
									} catch (InputMismatchException e) {
										System.out.println("Invalid input! ");
										System.out.println("Please input a number!");
										sc.nextLine();
										continue;
									}

									if (0 >= changePrices || tickets >= 25) {
										System.out.println("Invalid Input!");
										System.out.println("Please enter a value between 1 to 24!\n");
										System.out.print("Choice: ");
										continue;
									} else
										break;
//
								}
								TicketMgr.changePrice(changePrices);
							}
						}
					}
					if (settingChoice == 2) {
						HolidayManager holidayman = new HolidayManager();
						Scanner scan = new Scanner(System.in);
						int holidayChoice = 0;

						while (holidayChoice != 4) {
							System.out.println("===================================");
							System.out.println("|Holidays                         |");
							System.out.println("|1. View list of holidays         |");
							System.out.println("|2. Add Holidays                  |");
							System.out.println("|3. Remove Holidays               |");
							System.out.println("|4. Back                          |");
							System.out.println("===================================");
							System.out.println("Choice: ");
							try {
								holidayChoice = scan.nextInt();
								if (holidayChoice < 1 || holidayChoice > 4) {
									System.out.println("Enter an integer between 1 to 4");
								}
							} catch (InputMismatchException e) {
								System.out.println("Invalid input! ");
								System.out.println("Please input a number!");
								scan.nextLine();
								continue;
							}

							if (holidayChoice == 1) {
								System.out.println("");
								System.out.println("------------------------------");
								System.out.println("List of Holidays:");
								System.out.println("------------------------------");
								holidayman.readHoliday();
								System.out.println("------------------------------");
								System.out.println("");
							}

							if (holidayChoice == 2) {
								int phChoice = 0;

								while (phChoice != 3) {
									System.out.println("===================================");
									System.out.println("|Add Public Holiday               |");
									System.out.println("|1. Public Holiday                |");
									System.out.println("|2. Back                          |");
									System.out.println("===================================");
									System.out.println("Choice: ");
									try {
										phChoice = scan.nextInt();
									} catch (InputMismatchException e) {
										System.out.println("Invalid input! ");
										System.out.println("Please input a number!");
										scan.nextLine();
										continue;
									}
									if (phChoice == 2)
										break;

									if (phChoice >= 3 || phChoice <= 0) {
										System.out.println("Invalid input");
										continue;
									}

									scan.nextLine(); // clear buffer
									System.out.println("");
									System.out.print("Name of Holiday: ");
									String name = scan.nextLine();
									// scan.nextLine(); //clear buffer
									int day = 0;
									int month = 0;
									while (true) {
										try {
											System.out.print("Date Number (DD): ");
											day = scan.nextInt();
											if (day <= 0 || day > 31) {
												System.out.println("Enter a day between 1 and 31");
												continue;
											}
											System.out.print("Month Number (MM): ");
											month = scan.nextInt();
											if (month <= 0 || month > 12) {
												System.out.println("Enter a month between 1 and 12");
												continue;
											} else
												break;
										} catch (InputMismatchException e) {
											System.out.println("Invalid input! ");
											System.out.println("Please input a number!");
											scan.nextLine();
											continue;
										}
									}

									if (phChoice == 1) {
										holidayman.addHoliday(name, day, month);
										System.out.println("Successfuly added! \n");
									}

								}
							}
							if (holidayChoice == 3) {
								int phChoice = 0;

								try {
									while (phChoice != 3) {

										System.out.println("===================================");
										System.out.println("|Remove Public Holiday            |");
										System.out.println("|1. Public Holiday                |");
										System.out.println("|2. Back                          |");
										System.out.println("===================================");
										System.out.println("Choice: ");

										phChoice = scan.nextInt();

										if (phChoice >= 3 || phChoice <= 0) {
											System.out.println("Invalid input");
											continue;
										}
										if (phChoice == 2)
											break;
										scan.nextLine();
										System.out.println("");
										System.out.print("EXACT Name of Holiday to remove: ");
										String name = scan.nextLine();

										if (phChoice == 1) {
											boolean check = holidayman.deleteHoliday(name);
											if (check == true) {
												System.out.println("Successfully removed! \n");
											} else {
												System.out.println("Error! Holiday not removed!");
											}
										}

									}

								} catch (InputMismatchException e) {
									System.out.println("Invalid input! ");
									System.out.println("Please input a number!");
									scan.nextLine();
								}
							}
						}
					}

					if (settingChoice == 3) {
						Scanner scan = new Scanner(System.in);
						System.out.print("Enter Movie Name:");
						String name = scan.nextLine();
						String PGRating = "null";
						while (true) {
							System.out.print("Enter PG Rating (PG13/NC16/M18/R21):");
							PGRating = scan.nextLine();
							String upperPGRating = PGRating.toUpperCase();
							if (upperPGRating.equals("PG13") || upperPGRating.equals("NC16")
									|| upperPGRating.equals("M18") || upperPGRating.equals("R21")) {
								break;
							}
						}
						String movieType = "null";
						while (true) {
							System.out.print("Enter Movie Type (2D/3D):");
							movieType = scan.nextLine();
							String uppermovieType = movieType.toUpperCase();
							if (uppermovieType.equals("2D") || uppermovieType.equals("3D")) {
								break;
							}
						}
						String status = "null";
						while (true) {
							System.out.print("Enter Status (Preview/Showing/Not Showing): ");
							status = scan.nextLine();
							if (status.equals("Preview") || status.equals("Showing") || status.equals("Not Showing")) {
								break;
							}
						}

						System.out.print("Enter Director: ");
						String director = scan.nextLine();
						System.out.print("Enter All Casts: ");
						String cast = scan.nextLine();
						System.out.print("Enter Sypnosis: ");
						String sypnosis = scan.nextLine();
						String newMovie = name + "|" + PGRating.toUpperCase() + "|" + movieType.toUpperCase() + "|"
								+ status + "|" + director + "|" + cast + "|" + sypnosis;
						MovieMgr.saveMovie(newMovie);
						Review review = new Review(name);
						UserUI.reviews.add(review);
						Sales sale = new Sales(name, 0);
						UserUI.sales.add(sale);
						System.out.println("Movie details added.");
					}
					if (settingChoice == 4) {
						ArrayList<Movie> movieAL = MovieMgr.readMovie(MoblimaApp.movieFile);
						System.out.println("Select movie to configure timing: ");
						for (int i = 0; i < movieAL.size(); i++) {
							System.out.println(i + 1 + ". " + ((Movie) movieAL.get(i)).getName() + " ("
									+ ((Movie) movieAL.get(i)).gettypeofMovie() + ")");
						}
						Scanner scan = new Scanner(System.in);
						int movieChoice = 0;
						while (movieChoice == 0) {
							try {
								movieChoice = scan.nextInt();
								if (movieChoice > movieAL.size() || movieChoice < 1) {
									System.out.println("Enter valid movie");
									movieChoice = 0;
								}

							} catch (InputMismatchException e) {
								scan.next();
								System.out.println("Enter Valid Integer");
								continue;
							}
						}

						Movie movietoChange = (Movie) movieAL.get(movieChoice - 1);
						System.out.println("===================================");
						System.out.println("|1. Add Timing                    |");
						System.out.println("|2. Update Timing                 |");
						System.out.println("|3. Go Back                       |");
						System.out.println("===================================");
						int changeChoice = 0;
						String cineplex = "";
						while (changeChoice == 0) {
							try {
								changeChoice = sc.nextInt();
							} catch (InputMismatchException e) {
								scan.next();
								System.out.println("Enter Valid Integer");
								continue;
							}
						}

						switch (changeChoice) {
						case 1:
							int theDate = 0;
							while (true) {
								try {
									System.out.print("Date (DD): ");
									theDate = scan.nextInt();
									if (theDate <= 0 || theDate > 31) {
										System.out.println("Enter a date between 1 and 31");
										continue;
									} else {
										break;
									}
								} catch (InputMismatchException e) {
									scan.next();
									System.out.println("Enter Valid Integer");
									continue;
								}
							}
							int theMonth = 0;
							while (true) {
								try {
									System.out.print("Month (MM): ");
									theMonth = scan.nextInt();
									if (theMonth <= 0 || theMonth > 12) {
										System.out.println("Enter a month between 1 and 12");
										continue;
									} else {
										break;
									}
								} catch (InputMismatchException e) {
									scan.next();
									System.out.println("Enter Valid Integer");
									continue;
								}
							}
							String theDay = "null";
							String correcttheDay = "null";
							while (true) {
								System.out.print("Day (Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday): ");
								theDay = scan.next();
								char temp = Character.toUpperCase(theDay.charAt(0));
								correcttheDay = temp + theDay.substring(1);
								if (correcttheDay.equals("Monday") || correcttheDay.equals("Tuesday")
										|| correcttheDay.equals("Wednesday") || correcttheDay.equals("Thursday")
										|| correcttheDay.equals("Friday") || correcttheDay.equals("Saturday")
										|| correcttheDay.equals("Sunday")) {
									break;
								}
							}

							int theHour = 0;
							int theMin = 0;
							String isAm = "";
							String theTiming = "";
							while (true) {
								try {
									System.out.print("Enter the hour: ");
									theHour = scan.nextInt();
									if (theHour <= 0 || theHour > 12) {
										System.out.println("We use 12 hour clock, eneter an integer between 1 and 12");
									}
									System.out.println("Enter the minutes: ");
									theMin = scan.nextInt();
									if (theMin < 0 || theMin >= 60) {
										System.out.println("Enter an integer between 0 and 59");
										continue;
									}
									System.out.println("Is it AM or PM");
									scan.nextLine();
									isAm = scan.nextLine();
									if (isAm.equals("AM") || isAm.equals("PM") || isAm.equals("am")
											|| isAm.equals("pm")) {
										theTiming = Integer.toString(theHour) + "." + Integer.toString(theMin) + isAm;
										break;
									} else {
										System.out.println("Enter AM or PM only");
										break;
									}
								} catch (InputMismatchException e) {
									System.out.println("Enter an integer!");
								}

							}

							int theHall = 0;
							while (theHall == 0) {
								try {
									System.out.print("Hall number: ");
									theHall = scan.nextInt();
								} catch (InputMismatchException e) {
									scan.nextLine();
									System.out.println("Enter Valid Integer");
									continue;
								}
							}

							while (true) {
								System.out.println("Enter cineplex (Vivo City / Bishan / Jurong Point)");
								sc.nextLine();
								cineplex = sc.nextLine();
								if (cineplex.equals("Vivo City") || cineplex.equals("Bishan")
										|| cineplex.equals("Jurong Point")) {
									break;
								} else {
									System.out.println("Enter the name of the cineplex exactly");
								}
							}
							int a;
							ArrayList<Timings> timingList = Timings.readTimings(MoblimaApp.timingsFile);
							for (a = 0; a < timingList.size(); a++) {
								Timings timing = (Timings) timingList.get(a);
								if (theTiming.equals(timing.getTiming()) && timing.gethallNum() == theHall
										&& theDate == timing.getDate() && theMonth == timing.getMonth()) {
									System.out.println("Timing clash!");
									break;
								}
							}
							if (a == timingList.size()) {
								String theClass = "null";
								String correcttheClass = "null";
								while (true) {
									System.out.print("Class of Cinema? (Cathay/Platinum): ");
									theClass = scan.next();

									char temp = Character.toUpperCase(theClass.charAt(0));
									correcttheClass = temp + theClass.substring(1);
									if (correcttheClass.equals("Cathay") || correcttheClass.equals("Platinum")) {
										break;
									}
								}
								String dday;
								if (correcttheDay.equals("Monday") || correcttheDay.equals("Tuesday")
										|| correcttheDay.equals("Wednesday") || correcttheDay.equals("Thursday")
										|| correcttheDay.equals("Friday")) {
									dday = "Weekday";
								} else {
									dday = "Weekend";
								}

								String addtheTiming = Integer.toString(theDate) + "|" + Integer.toString(theMonth) + "|"
										+ correcttheDay + "|" + dday + "|"
										+ ((Movie) movieAL.get(movieChoice - 1)).getName() + "|"
										+ ((Movie) movieAL.get(movieChoice - 1)).gettypeofMovie() + "|" + theTiming
										+ "|" + theHall + "|" + correcttheClass + "|"
										+ ((Movie) movieAL.get(movieChoice - 1)).getStatus() + "|"
										+ ((Movie) movieAL.get(movieChoice - 1)).getPGRating() + "|" + cineplex;
								File file = new File(MoblimaApp.timingsFile);
								FileWriter fr = new FileWriter(file, true);
								fr.write("\n");
								fr.write(addtheTiming);
								fr.close();
								System.out.println("Timing Added");
							}
							break;
						case 2:
							ArrayList<Timings> timingAL = new ArrayList<Timings>();
							ArrayList<Timings> timingList2 = Timings.readTimings(MoblimaApp.timingsFile);
							int index = 1;
							System.out.println("Choose the timing to update: ");
							for (int j = 0; j < timingList2.size(); j++) {
								Timings timing = (Timings) timingList2.get(j);
								if (timing.getName().equals(movietoChange.getName())) {
									System.out.println(
											index + ") " + timing.getName() + "(" + timing.gettypeofMovie() + ")");
									System.out.println("Date: " + timing.getDay() + ", " + timing.getDate() + "/"
											+ timing.getMonth());
									System.out.println("Timing: " + timing.getTiming());
									System.out.println();
									index++;
									timingAL.add(timing);
								}
							}
							int timingChoice = 0;
							while (timingChoice == 0) {
								try {
									timingChoice = scan.nextInt();
									if (timingChoice > timingAL.size()) {
										System.out.println("Enter valid timing");
										timingChoice = 0;
									}
								} catch (InputMismatchException e) {
									scan.next();
									System.out.println("Enter Valid Integer");
									continue;
								}
							}

							Timings timingtoChange = (Timings) timingAL.get(timingChoice - 1);
							int theHour1 = 0;
							int theMin1 = 0;
							String isAm1 = "";
							String newTiming = "";
							while (true) {
								try {
									System.out.println("Enter the hour: ");
									theHour1 = scan.nextInt();
									if (theHour1 <= 0 || theHour1 > 12) {
										System.out.println("We use 12 hour clock, eneter an integer between 1 and 12");
										continue;
									} else {
										System.out.println("Enter the minutes: ");
										theMin1 = scan.nextInt();
										if (theMin1 < 0 || theMin1 >= 60) {
											System.out.println("Enter an integer between 0 and 59");
											continue;
										} else {
											System.out.println("Is it AM or PM");
											scan.nextLine();
											isAm1 = scan.nextLine();
											if (isAm1.equals("AM") || isAm1.equals("PM") || isAm1.equals("am")
													|| isAm1.equals("pm")) {
												newTiming = Integer.toString(theHour1) + "." + Integer.toString(theMin1)
														+ isAm1;
												break;
											} else {
												System.out.println("Enter AM or PM only");
												continue;
											}
										}
									}
								} catch (InputMismatchException e) {
									System.out.println("Enter an integer!");
								}
							}
//							System.out.print("Enter new timing: ");
//							scan.nextLine();
//							String newTiming = scan.nextLine();
							int b = 0;
							for (b = 0; b < timingAL.size(); b++) {
								Timings timing = (Timings) timingAL.get(b);
								if (newTiming.equals(timing.getTiming())
										&& timingtoChange.gethallNum() == timing.gethallNum()
										&& timingtoChange.getDate() == timing.getDate()
										&& timingtoChange.getMonth() == timing.getMonth()) {
									System.out.println("Timing clash!");
									break;
								}
							}
							if (b == timingAL.size()) {

								String originalString = timingtoChange.getDate() + "|" + timingtoChange.getMonth() + "|"
										+ timingtoChange.getDay() + "|" + timingtoChange.gettypeofDay() + "|"
										+ timingtoChange.getName() + "|" + timingtoChange.gettypeofMovie() + "|"
										+ timingtoChange.getTiming() + "|" + timingtoChange.gethallNum() + "|"
										+ timingtoChange.gettypeofCinema() + "|" + timingtoChange.getstatus() + "|"
										+ timingtoChange.getPGRating();
								String newString = timingtoChange.getDate() + "|" + timingtoChange.getMonth() + "|"
										+ timingtoChange.getDay() + "|" + timingtoChange.gettypeofDay() + "|"
										+ timingtoChange.getName() + "|" + timingtoChange.gettypeofMovie() + "|"
										+ newTiming + "|" + timingtoChange.gethallNum() + "|"
										+ timingtoChange.gettypeofCinema() + "|" + timingtoChange.getstatus() + "|"
										+ timingtoChange.getPGRating() + "|" + timingtoChange.getcineplexName();
								TicketMgr.modifyFile(MoblimaApp.timingsFile, originalString, newString);
								System.out.println("Update Successful!");

							}

							break;

						case 3:
							break;
						}
					}
					if (settingChoice == 5) {
						// Update status of movie
						String[] statusarray = { "Showing", "Not Showing", "Preview", "Coming Soon" };
						String[] intermediatearray = new String[statusarray.length - 1];
						Scanner scan = new Scanner(System.in);
						System.out.println("What movie you want to change the status of? (Enter Name) ");
						ArrayList<Movie> movielist = MovieMgr.readMovie(MoblimaApp.movieFile);
						int counter = 1;
						for (int i = 0; i < movielist.size(); i++) {
							Movie movielists = (Movie) movielist.get(i);
							System.out.println(counter + ") " + movielists.getName());
							counter++;
						}
						String movie = scan.nextLine();
						ArrayList<Timings> timingAL = Timings.readTimings(MoblimaApp.timingsFile);
						ArrayList<Movie> movieAL = MovieMgr.readMovie(MoblimaApp.movieFile);
						boolean changedstatus = false;
						boolean available = false;
						int statuschoice = 0;

						String movienamenospace = movie.replaceAll("\\s+", "").toLowerCase();
						int countx = 0;
						for (int i = 0; i < movieAL.size(); i++) {
							Movie movie1 = (Movie) movieAL.get(i);
							if (movie1.getName().toLowerCase().replaceAll("\\s+", "").equals(movienamenospace)) {
								available = true;
								System.out.println(movie1.getName() + " is now " + movie1.getStatus());
								System.out.println("Choose the status you want to change to: ");

								int count = 1;

								for (int a = 0; a < statusarray.length; a++) {
									if (!movie1.getStatus().equals(statusarray[a])) {
										System.out.println(count + ") " + statusarray[a]);
										intermediatearray[count - 1] = statusarray[a];
										count++;
									}
								}
								statuschoice = scan.nextInt();
								scan.nextLine();

								System.out.println("Change to " + intermediatearray[statuschoice - 1] + "? (Y/N)");
								String answer = scan.nextLine();
								if (answer.equals("y") || answer.equals("Y")) {
									String originalstatus = movie1.getStatus();
									String originalentry = movie1.getName() + "|" + movie1.getPGRating() + "|"
											+ movie1.gettypeofMovie() + "|" + originalstatus + "|"
											+ movie1.getDirector() + "|" + movie1.getCast() + "|"
											+ movie1.getSynopsis();
									String newstatus = intermediatearray[statuschoice - 1];
									String updatestatus = movie1.getName() + "|" + movie1.getPGRating() + "|"
											+ movie1.gettypeofMovie() + "|" + newstatus + "|" + movie1.getDirector()
											+ "|" + movie1.getCast() + "|" + movie1.getSynopsis();
									TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatestatus);
									System.out.println("Status changed to " + newstatus + "!");
									changedstatus = true;
									break;
								}
							}
						}
						if (countx == movieAL.size()) {
							System.out.println("Status not changed.");
						}

						if (available == false) {
							System.out.println("Movie not available.");
						}
						if (changedstatus == true) {
							for (int i = 0; i < timingAL.size(); i++) {
								Timings timing = (Timings) timingAL.get(i);
								if (timing.getName().toLowerCase().replaceAll("\\s+", "").equals(movienamenospace)) {
									String originalstatus = timing.getstatus();
									String originalentry = timing.getDate() + "|" + timing.getMonth() + "|"
											+ timing.getDay() + "|" + timing.gettypeofDay() + "|" + timing.getName()
											+ "|" + timing.gettypeofMovie() + "|" + timing.getTiming() + "|"
											+ timing.gethallNum() + "|" + timing.gettypeofCinema() + "|"
											+ originalstatus + "|" + timing.getPGRating() + "|"
											+ timing.getcineplexName();
									String newstatus = intermediatearray[statuschoice - 1];
									String updatestatus = timing.getDate() + "|" + timing.getMonth() + "|"
											+ timing.getDay() + "|" + timing.gettypeofDay() + "|" + timing.getName()
											+ "|" + timing.gettypeofMovie() + "|" + timing.getTiming() + "|"
											+ timing.gethallNum() + "|" + timing.gettypeofCinema() + "|" + newstatus
											+ "|" + timing.getPGRating() + "|" + timing.getcineplexName();
									TicketMgr.modifyFile(MoblimaApp.timingsFile, originalentry, updatestatus);
								}
							}
						}
					}
					if (settingChoice == 6) {
						Scanner scan = new Scanner(System.in);
						System.out.println("What movie do you want to update (Enter Name):");
						MovieMgr.showavailMovie(MoblimaApp.movieFile);
						String movieupdate = scan.nextLine();
						String movieupdatenospace = movieupdate.replaceAll("\\s+", "").toLowerCase();
						System.out.println("What details do you want to update?");
						System.out.println("1. PG Rating (PG13/NC16/M18/R21)");
						System.out.println("2. Type Of Movie (2D/3D)");
						System.out.println("3. Director ");
						System.out.println("4. Cast ");
						System.out.println("5. Synopsis ");
						System.out.println("6. Go Back ");
						int detailschoice = 0;
						while (true) {
							try {
								detailschoice = scan.nextInt();
								if (detailschoice > 6 || detailschoice <= 0) {
									System.out.println("Enter an integer between 1 and 6!");
									continue;
								} else {
									break;
								}
							} catch (InputMismatchException e) {
								scan.next();
								System.out.println("Enter Valid Integer");
								continue;
							}
						}
						scan.nextLine();
						while (detailschoice < 6) {
							switch (detailschoice) {
							case 1:
								ArrayList<Timings> timingAL = Timings.readTimings(MoblimaApp.timingsFile);
								ArrayList<Movie> movieAL = MovieMgr.readMovie(MoblimaApp.movieFile);
								String newPGRating = "null";
								String uppernewPGRating = "null";
								while (true) {
									System.out.print("Enter PG Rating (PG13/NC16/M18/R21):");
									newPGRating = scan.next();
									uppernewPGRating = newPGRating.toUpperCase();
									if (uppernewPGRating.equals("PG13") || uppernewPGRating.equals("NC16")
											|| uppernewPGRating.equals("M18") || uppernewPGRating.equals("R21")) {
										break;
									}
								}
								boolean available = false;
								boolean updated = false;
								for (int i = 0; i < movieAL.size(); i++) {
									Movie movie1 = (Movie) movieAL.get(i);
									if (movie1.getName().toLowerCase().replaceAll("\\s+", "")
											.equals(movieupdatenospace)) {
										String originalPGrating = movie1.getPGRating();
										String originalentry = movie1.getName() + "|" + originalPGrating + "|"
												+ movie1.gettypeofMovie() + "|" + movie1.getStatus() + "|"
												+ movie1.getDirector() + "|" + movie1.getCast() + "|"
												+ movie1.getSynopsis();
										String updatePGrating = movie1.getName() + "|" + uppernewPGRating + "|"
												+ movie1.gettypeofMovie() + "|" + movie1.getStatus() + "|"
												+ movie1.getDirector() + "|" + movie1.getCast() + "|"
												+ movie1.getSynopsis();
										TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatePGrating);
										System.out.println("PG rating changed from " + originalPGrating + " to "
												+ uppernewPGRating);
										available = true;
										updated = true;
										break;
									}

								}

								if (updated == true) {
									for (int i = 0; i < timingAL.size(); i++) {
										Timings timing = (Timings) timingAL.get(i);
										if (timing.getName().toLowerCase().replaceAll("\\s+", "")
												.equals(movieupdatenospace)) {
											String originalPGrating = timing.getPGRating();
											String originalentry = timing.getDate() + "|" + timing.getMonth() + "|"
													+ timing.getDay() + "|" + timing.gettypeofDay() + "|"
													+ timing.getName() + "|" + timing.gettypeofMovie() + "|"
													+ timing.getTiming() + "|" + timing.gethallNum() + "|"
													+ timing.gettypeofCinema() + "|" + timing.getstatus() + "|"
													+ originalPGrating + "|" + timing.getcineplexName();
											String updatePGrating = timing.getDate() + "|" + timing.getMonth() + "|"
													+ timing.getDay() + "|" + timing.gettypeofDay() + "|"
													+ timing.getName() + "|" + timing.gettypeofMovie() + "|"
													+ timing.getTiming() + "|" + timing.gethallNum() + "|"
													+ timing.gettypeofCinema() + "|" + timing.getstatus() + "|"
													+ newPGRating + "|" + timing.getcineplexName();
											TicketMgr.modifyFile(MoblimaApp.timingsFile, originalentry, updatePGrating);
										}
									}
								}
								if (available == false) {
									System.out.println("Movie not available.");
								}
								break;
							case 2:
								ArrayList<Timings> timingAL2 = Timings.readTimings(MoblimaApp.timingsFile);
								ArrayList<Movie> movieAL2 = MovieMgr.readMovie(MoblimaApp.movieFile);
								String newtypeofmovie = "null";
								String uppernewtypeofmovie = "null";
								while (true) {
									System.out.print("What Type of Movie do you want to change it to?(2D/3D)");
									newtypeofmovie = scan.next();
									uppernewtypeofmovie = newtypeofmovie.toUpperCase();
									if (uppernewtypeofmovie.equals("2D") || uppernewtypeofmovie.equals("3D")) {
										break;
									}
								}
								boolean available2 = false;
								boolean updated2 = false;
								for (int i = 0; i < movieAL2.size(); i++) {
									Movie movie2 = (Movie) movieAL2.get(i);
									if (movie2.getName().toLowerCase().replaceAll("\\s+", "")
											.equals(movieupdatenospace)) {
										String originaltypeofmovie = movie2.gettypeofMovie();
										String originalentry = movie2.getName() + "|" + movie2.getPGRating() + "|"
												+ originaltypeofmovie + "|" + movie2.getStatus() + "|"
												+ movie2.getDirector() + "|" + movie2.getCast() + "|"
												+ movie2.getSynopsis();
										String updatetypeofmovie = movie2.getName() + "|" + movie2.getPGRating() + "|"
												+ uppernewtypeofmovie + "|" + movie2.getStatus() + "|"
												+ movie2.getDirector() + "|" + movie2.getCast() + "|"
												+ movie2.getSynopsis();
										TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatetypeofmovie);
										System.out.println("Type of Movie changed from " + originaltypeofmovie + " to "
												+ newtypeofmovie);
										available2 = true;
										updated2 = true;
										break;
									}

								}
								if (updated2 == true) {
									for (int i = 0; i < timingAL2.size(); i++) {
										Timings timing2 = (Timings) timingAL2.get(i);
										if (timing2.getName().toLowerCase().replaceAll("\\s+", "")
												.equals(movieupdatenospace)) {
											String originaltypeofmovie = timing2.gettypeofMovie();
											String originalentry = timing2.getDate() + "|" + timing2.getMonth() + "|"
													+ timing2.getDay() + "|" + timing2.gettypeofDay() + "|"
													+ timing2.getName() + "|" + originaltypeofmovie + "|"
													+ timing2.getTiming() + "|" + timing2.gethallNum() + "|"
													+ timing2.gettypeofCinema() + "|" + timing2.getstatus() + "|"
													+ timing2.getPGRating() + "|" + timing2.getcineplexName();
											String updatetypeofmovie = timing2.getDate() + "|" + timing2.getMonth()
													+ "|" + timing2.getDay() + "|" + timing2.gettypeofDay() + "|"
													+ timing2.getName() + "|" + newtypeofmovie + "|"
													+ timing2.getTiming() + "|" + timing2.gethallNum() + "|"
													+ timing2.gettypeofCinema() + "|" + timing2.getstatus() + "|"
													+ timing2.getPGRating() + "|" + timing2.getcineplexName();
											TicketMgr.modifyFile(MoblimaApp.timingsFile, originalentry,
													updatetypeofmovie);
										}
									}

								}
								if (available2 == false) {
									System.out.println("Movie not available.");
								}
								break;
							case 3:
								ArrayList<Movie> movieAL3 = MovieMgr.readMovie(MoblimaApp.movieFile);
								System.out.print("Enter the name of the new Director:");
								String newDirector = scan.nextLine();
								boolean available3 = false;
								for (int i = 0; i < movieAL3.size(); i++) {
									Movie movie3 = (Movie) movieAL3.get(i);
									if (movie3.getName().toLowerCase().replaceAll("\\s+", "")
											.equals(movieupdatenospace)) {
										String originaldirector = movie3.getDirector();
										String originalentry = movie3.getName() + "|" + movie3.getPGRating() + "|"
												+ movie3.gettypeofMovie() + "|" + movie3.getStatus() + "|"
												+ originaldirector + "|" + movie3.getCast() + "|"
												+ movie3.getSynopsis();
										String updatetypeofmovie = movie3.getName() + "|" + movie3.getPGRating() + "|"
												+ movie3.gettypeofMovie() + "|" + movie3.getStatus() + "|" + newDirector
												+ "|" + movie3.getCast() + "|" + movie3.getSynopsis();
										TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatetypeofmovie);
										System.out.println(
												"Director changed from " + originaldirector + " to " + newDirector);
										available3 = true;
										break;
									}

								}
								if (available3 == false) {
									System.out.println("Movie not available.");
								}
								break;

							case 4:
								ArrayList<Movie> movieAL4 = MovieMgr.readMovie(MoblimaApp.movieFile);
								System.out.print(
										"Enter the names of the new Cast: (separate each cast's name with a comma)");
								String newcast = scan.nextLine();
								boolean available4 = false;
								for (int i = 0; i < movieAL4.size(); i++) {
									Movie movie4 = (Movie) movieAL4.get(i);
									if (movie4.getName().toLowerCase().replaceAll("\\s+", "")
											.equals(movieupdatenospace)) {
										String originalcast = movie4.getCast();
										String originalentry = movie4.getName() + "|" + movie4.getPGRating() + "|"
												+ movie4.gettypeofMovie() + "|" + movie4.getStatus() + "|"
												+ movie4.getDirector() + "|" + originalcast + "|"
												+ movie4.getSynopsis();
										String updatetypeofmovie = movie4.getName() + "|" + movie4.getPGRating() + "|"
												+ movie4.gettypeofMovie() + "|" + movie4.getStatus() + "|"
												+ movie4.getDirector() + "|" + newcast + "|" + movie4.getSynopsis();
										TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatetypeofmovie);
										System.out.println("Cast changed from " + originalcast + " to " + newcast);
										available4 = true;
										break;
									}

								}
								if (available4 == false) {
									System.out.println("Movie not available.");
								}
								break;
							case 5:
								ArrayList<Movie> movieAL5 = MovieMgr.readMovie(MoblimaApp.movieFile);
								System.out.print("Enter the new Synopsis:");
								String newsypnosis = scan.nextLine();
								boolean available5 = false;
								for (int i = 0; i < movieAL5.size(); i++) {
									Movie movie5 = (Movie) movieAL5.get(i);
									if (movie5.getName().toLowerCase().replaceAll("\\s+", "")
											.equals(movieupdatenospace)) {
										String originalsypnosis = movie5.getSynopsis();
										String originalentry = movie5.getName() + "|" + movie5.getPGRating() + "|"
												+ movie5.gettypeofMovie() + "|" + movie5.getStatus() + "|"
												+ movie5.getDirector() + "|" + movie5.getCast() + "|"
												+ originalsypnosis;
										String updatetypeofmovie = movie5.getName() + "|" + movie5.getPGRating() + "|"
												+ movie5.gettypeofMovie() + "|" + movie5.getStatus() + "|"
												+ movie5.getDirector() + "|" + movie5.getCast() + "|" + newsypnosis;
										TicketMgr.modifyFile(MoblimaApp.movieFile, originalentry, updatetypeofmovie);
										System.out.println(
												"Sypnosis changed from " + originalsypnosis + " to " + newsypnosis);
										available5 = true;
										break;
									}

								}
								if (available5 == false) {
									System.out.println("Movie not available.");
								}
								break;
							}
							System.out.println("What details do you want to update?");
							System.out.println("1. PG Rating (PG13/NC16/M18/R21)");
							System.out.println("2. Type Of Movie (2D/3D)");
							System.out.println("3. Director ");
							System.out.println("4. Cast ");
							System.out.println("5. Synopsis ");
							System.out.println("6. Go Back ");
							try {
								detailschoice = scan.nextInt();
							} catch (InputMismatchException e) {
								scan.next();
								System.out.println("Enter Valid Integer");
								continue;
							}
						}
					}
				}

			} else if (userChoice == 2) {
				MovieMgr.showavailMovie(MoblimaApp.movieFile);
			} else if (userChoice == 3) {
				MovieMgr.showMovie();
			} else if (userChoice == 4) {
				ArrayList<Movie> al = MovieMgr.readMovie(MoblimaApp.movieFile);
//			        ArrayList<Review> reviews = new ArrayList<Review>() ;
				for (int i = 0; i < al.size(); i++) {
					Movie movie = (Movie) al.get(i);
					Review review = new Review(movie.getName());
					UserUI.reviews.add(review);
				}
//			        ArrayList<Sales> sales = new ArrayList<Sales>() ;
				for (int i = 0; i < al.size(); i++) {
					Movie movie = (Movie) al.get(i);
					Sales sale = new Sales(movie.getName(), 0);
					UserUI.sales.add(sale);
				}
				Review.printtop5Rating(UserUI.reviews);
				System.out.println();
				Review.printtop5Sales(UserUI.sales);
			} else if (userChoice == 5) {
				final String bookedFile = "Booked.txt";
				ArrayList<String> booked = new ArrayList<String>();
				booked = MovieMgr.readBooked(bookedFile);
				for (int i = 0; i < booked.size(); i++) {
					File file = new File(booked.get(i) + ".txt");
					file.delete();
				}
				new FileOutputStream(bookedFile).close();
			} else if (userChoice == 6) {
				MoblimaApp.main(null);
			}
		}
	}

}
