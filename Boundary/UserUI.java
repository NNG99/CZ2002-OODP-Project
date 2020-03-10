package Boundary;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Control.MovieMgr;
import Entity.Movie;
import Entity.Receipt;
import Entity.Review;
import Entity.Sales;
import Entity.User;

/**
 * This is the page displayed for movie goers.
 *
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */
public class UserUI {
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * Creates an array list for reviews
	 */
	public static ArrayList<Review> reviews = new ArrayList<Review>();
	/**
	 * Creates an array list for sales
	 */
	public static ArrayList<Sales> sales = new ArrayList<Sales>();

	/**
	 * Creates a new UserUI
	 */
	public UserUI() {
	}

	/**
	 * Users are able to select from the options listed which action they would like
	 * to perform. Option 1 is selected if the user would like to make a movie
	 * booking. A list of movies that are currently showing will be listed for the
	 * user to select and after selection, user will select the date, timing and the
	 * class of cinema for the selected movie. Following that, the user is required
	 * to input the number of tickets they would like to book (student/adult/senior)
	 * and select the seats from the seating layout. Users would be asked to enter
	 * their details (name, phone number, email). After a booking is successful, a
	 * transaction ID will be generated. The name of movie, PG rating of the movie,
	 * type of movie(2D/3D), date, timing, type of cinema, hall number, seats
	 * booked, total price and transaction ID will be printed. Option 2 is selected
	 * if the user would like to view details for a movie. Details such as the movie
	 * name, PG rating, director, status, cast, sypnosis, overall ratings and
	 * reviews of the selected movie will be listed. Option 3 is selected if the
	 * user would like to check the seat availability for a selected movie at a
	 * selected date and time. Option 4 is selected if the user would like to view
	 * his past booking transactions. Option 5 is selected if the user would like to
	 * list the top 5 movies. Users would be able to select if they would like to
	 * list the top 5 movies based on the top 5 overall rating or based on the top 5
	 * sales. Option 6 is selected if the user would like to search for a specific
	 * movie. If the search is successful, the movie details would be listed. If the
	 * search is unsuccessful, the movie is unavailable. Option 7 is selected if the
	 * user would like to add a movie rating or review. Option 8 is selected if the
	 * user would like to go back to the main page of the MOBLIMA application.
	 * 
	 * @param args the main argument that is passed in
	 * @throws IOException If an input or output exception occurred
	 */
	public static void main(String[] args) throws IOException {

		ArrayList<Movie> al = MovieMgr.readMovie(MoblimaApp.movieFile);
//		static ArrayList<Review> reviews = new ArrayList<Review>() ;
		for (int i = 0; i < al.size(); i++) {
			Movie movie = (Movie) al.get(i);
			Review review = new Review(movie.getName());
			reviews.add(review);
		}
//		ArrayList<Sales> sales = new ArrayList<Sales>() ;
		for (int i = 0; i < al.size(); i++) {
			Movie movie = (Movie) al.get(i);
			Sales sale = new Sales(movie.getName(), 0);
			sales.add(sale);
		}
//		
		User usr = new User("1234", "pw");

		while (true) {
			System.out.println("===================================");
			System.out.println("|What would you like to do?       |");
			System.out.println("===================================");
			System.out.println("|User Action:                     |");
			System.out.println("|1. Make Booking                  |");
			System.out.println("|2. View Movie Details            |");
			System.out.println("|3. Check Seat Availability       |");
			System.out.println("|4. View Booking History          |");
			System.out.println("|5. List Top 5			  |");
			System.out.println("|6. Search Movie                  |");
			System.out.println("|7. Add Movie Rating/Review       |");
			System.out.println("|8. Go Back                       |");
			System.out.println("===================================");
			System.out.print("Choice: ");
			int userChoice = 0;
			try {
				Scanner scan = new Scanner(System.in);
				userChoice = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid integer! ");
				continue;
			}
			switch (userChoice) {

			case 1:
				ArrayList<Movie> availMovies = MovieMgr.showavailMovie(MoblimaApp.movieFile);
				String moviename;
				Scanner x = new Scanner(System.in);
				while (true) {
					try {
						int am = x.nextInt();
						Movie movie = (Movie) availMovies.get(am - 1);
						moviename = movie.getName();
					} catch (InputMismatchException e) {
						System.out.println("Enter Valid Integer");
						x.next() ;
						continue;
					} catch (IndexOutOfBoundsException ex) {
						System.out.println("Enter Valid Movie");
						x.next() ;
						continue;
					}
					break;
				}
				Receipt receipt = MovieMgr.chooseMovie(MoblimaApp.movieFile, moviename);
				System.out.println("Enter name: ");
				String userName = x.next();
				System.out.println("Enter number: ");
				String number = x.next();
				System.out.println("Enter email: ");
				String email = x.next();
				usr.setuserName(userName);
				usr.setNumber(number);
				usr.setEmail(email);
				usr.updateHistory(receipt.getName(), receipt.getPGRating(), Receipt.gettypeofMovie(),
						Receipt.getclassofCinema(), receipt.gettiming(), receipt.getmovieDay(), receipt.gethall(),
						receipt.getSeat(), receipt.gettransactionID(), receipt.getPrice());
				for (int i = 0; i < sales.size(); i++) {
					if (sales.get(i).getmovieName().equals(receipt.getName())) {
						sales.get(i).setticketSale(sales.get(i), receipt.getPrice());
					}
				}
				Receipt.printReceipt(receipt);
				break;

			case 2:
				System.out.println("Select which movie you want to view:");
				for (int i = 0; i < al.size(); i++) {
					Movie movie = (Movie) al.get(i);
					System.out.println(i + 1 + ". " + movie.getName());
				}

				Scanner xx = new Scanner(System.in);
				int mc = 0;
				while (mc == 0) {
					try {
						mc = xx.nextInt();
						if (mc > al.size() || mc < 0 ) {
							System.out.print("Enter valid movie");
							mc = 0;
							continue;
						}
					} catch (InputMismatchException e) {
						xx.next();
						System.out.print("Enter Valid Integer");
						continue;
					}

				}
				Movie movie = (Movie) al.get(mc - 1);
				System.out.println("===================================");
				System.out.println("Name: " + movie.getName());
				System.out.println("PG Rating: " + movie.getPGRating());
				System.out.println("Director: " + movie.getDirector());
				System.out.println("Status: " + movie.getStatus());
				System.out.println("Cast: " + movie.getCast());
				System.out.println("Synopsis: " + movie.getSynopsis());
				for (int i = 0; i < reviews.size(); i++) {
					if (reviews.get(i).getmovieName().equals(movie.getName())) {
						if (reviews.get(i).getratingArray().size() == 0) {
							System.out.println("Rating: No ratings yet.");
						} else {
							System.out.println("Rating: " + reviews.get(i).getoverallRating(reviews.get(i)));
							System.out.println("Past Rating(s): ");
							reviews.get(i);
							Review.printmovieRating(reviews.get(i));
						}
						break;
					}
				}
				for (int j = 0; j < reviews.size(); j++) {
					if (reviews.get(j).getmovieName().equals(movie.getName())) {
						if (reviews.get(j).getreviewArray().size() == 0) {
							System.out.println("Reviews: No reviews yet.");
						} else {
							System.out.println("Reviews: ");
							reviews.get(j);
							Review.printReviews(reviews.get(j));
						}
						break;
					}
				}
				System.out.println("===================================");

				break;

			case 3:
				System.out.println("Select which movie you want to view: ");
				MovieMgr.showMovie();
				break;

			case 4:
				usr.printHistory();
				break;

			case 5:
				while (true) {
					Scanner sc = new Scanner(System.in);
					System.out.println("===================================");
					System.out.println("|What would you like to do?       |");
					System.out.println("|1. View Top 5 Overall Rating	  |");
					System.out.println("|2. View Top 5 Sales		  |");
					System.out.println("|3. Go Back			  |");
					System.out.println("===================================");
					try {
						int ratingChoice = sc.nextInt();
						if (ratingChoice <= 0 || ratingChoice >= 4) {
							System.out.println("Error! Enter an integer between 1 to 3");
						} else if (ratingChoice == 1) {
							Review.printtop5Rating(reviews);
						} else if (ratingChoice == 2) {
							Review.printtop5Sales(sales);
						} else if (ratingChoice == 3) {
							break;
						}
					} catch (InputMismatchException e) {
						sc.next();
						System.out.println("Enter Valid Integer");
						continue;
					}
				}
				break;
			case 6:
				MovieMgr.searchMovie(al, reviews);
				break;

			case 7:
				System.out.println("Select movie to review");
				availMovies = MovieMgr.showavailMovie(MoblimaApp.movieFile);
				Scanner sc = new Scanner(System.in);
				int movieChoice = 0;
				while (movieChoice == 0) {
					try {
						movieChoice = sc.nextInt();
						if (movieChoice > availMovies.size() || movieChoice < 0) {
							System.out.println("Enter valid movie");
							movieChoice = 0;
						}
					} catch (InputMismatchException e) {
						sc.next();
						System.out.println("Enter Valid Integer");
						continue;
					}
				}
				Movie tempmovie = (Movie) availMovies.get(movieChoice - 1);
				System.out.println("1. Add Rating");
				System.out.println("2. Add Review");
				int add = 0;
				while (add == 0) {
					try {
						add = sc.nextInt();
						if (add > availMovies.size()) {
							System.out.println("Enter valid movie");
							add = 0;
						} else if (add < 1 || add > 2) {
							System.out.println("Enter Valid Integer");
							add = 0;
						}
					} catch (InputMismatchException e) {
						sc.next();
						System.out.println("Enter Valid Integer");
						continue;
					}
				}

				if (add == 1) {
					System.out.println("Enter rating (1-5)");
					int rating = 0;
					while (rating == 0) {
						try {
							rating = sc.nextInt();
							if (rating > 5 || rating <= 0) {
								System.out.println("Enter rating between value 1-5.");
								rating = 0;
							}
						} catch (InputMismatchException e) {
							sc.next();
							System.out.println("Enter Valid Integer");
							continue;
						}
					}
					for (int i = 0; i < reviews.size(); i++) {
						if (reviews.get(i).getmovieName().equals(tempmovie.getName())) {
							reviews.get(i);
							Review.updatemovieRating(reviews.get(i), rating);
							System.out.println("Rating added.");
							break;
						}
					}
					break;
				} else if (add == 2) {
					System.out.println("Enter review: ");
					sc.nextLine();
					String rw = sc.nextLine();
					for (int i = 0; i < reviews.size(); i++) {
						if (reviews.get(i).getmovieName().equals(tempmovie.getName())) {
							reviews.get(i);
							Review.updatemovieReview(reviews.get(i), rw);
							break;
						}
					}
				}
				break;

			case 8:
				MoblimaApp.main(null);
			default:
				System.out.println("Enter value between 1 to 8! ");
			}
		}
	}
}
