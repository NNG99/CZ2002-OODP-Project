package Entity;

/**
 * Represents the receipt after a purchase is being made
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class Receipt {
	/**
	 * The name of the movie that the user bought.
	 */
	private String name;
	/**
	 * The PG rating of the movie that the user bought.
	 */
	private String PGRating;
	/**
	 * The type of movie (2D or 3D) that the user bought.
	 */
	private static String typeofMovie;
	/**
	 * The class of the cinema that the user chose.
	 */
	private static String classofCinema;
	/**
	 * The timing of the movie that the user bought
	 */
	private String timing;
	/**
	 * The day of the movie that the user purchased
	 */
	private String movieDay;
	/**
	 * The hall number of the cinema that is showing the movie
	 */
	private String hall;
	/**
	 * The seats of the cinema that the user booked
	 */
	private String seat;
	/**
	 * The transaction ID of the transaction after a user made a purchase.
	 */
	private String transactionID;
	/**
	 * The total cost of the purchase.
	 */
	private double price;

	/**
	 * Get the name of the movie the user purchased
	 * 
	 * @return the movie name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the PG rating of a movie that the user purchased
	 * 
	 * @return the PG rating of the movie
	 */
	public String getPGRating() {
		return PGRating;
	}

	/**
	 * Get the type of movie the user purchased, 2D or 3D.
	 * 
	 * @return the type of movie
	 */
	public static String gettypeofMovie() {
		return typeofMovie;
	}

	/**
	 * Get the class of the cinema the user purchased, Cathay or Platinum.
	 * 
	 * @return the class of cinema.
	 */
	public static String getclassofCinema() {
		return classofCinema;
	}

	/**
	 * Get the timing of the movie the user purchased.
	 * 
	 * @return the timing of the movie.
	 */
	public String gettiming() {
		return timing;
	}

	/**
	 * Get the day of the movie the user purchased.
	 * 
	 * @return the day of the movie.
	 */
	public String getmovieDay() {
		return movieDay;
	}

	/**
	 * Get the hall number of the cinema showing the movie the user purchased.
	 * 
	 * @return the hall number.
	 */
	public String gethall() {
		return hall;
	}

	/**
	 * Get the seat(s) number that the user booked.
	 * 
	 * @return the seat(s) number.
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * Get the transaction ID of the particular transaction.
	 * 
	 * @return the transaction ID.
	 */
	public String gettransactionID() {
		return transactionID;
	}

	/**
	 * Get the total price of the purchase.
	 * 
	 * @return the price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Create a new receipt with the given name, rating, type of movie, cinema
	 * class, timing, day of showing, hall number, seat number, transaction ID and
	 * price
	 * 
	 * @param name          The movie name.
	 * @param PGRating      The movie rating.
	 * @param typeofMovie   The type of movie (2D or 3D).
	 * @param classofCinema The class of cinema.
	 * @param timing        The movie timing.
	 * @param movieDay      The day of the movie.
	 * @param hall          The hall number.
	 * @param seat          The seat number(s).
	 * @param transactionID The transaction ID.
	 * @param price         The total price of the transaction.
	 */
	public Receipt(String name, String PGRating, String typeofMovie, String classofCinema, String timing,
			String movieDay, String hall, String seat, String transactionID, double price) {
		this.name = name;
		this.PGRating = PGRating;
		Receipt.typeofMovie = typeofMovie;
		Receipt.classofCinema = classofCinema;
		this.timing = timing;
		this.movieDay = movieDay;
		this.hall = hall;
		this.seat = seat;
		this.transactionID = transactionID;
		this.price = price;
	}

	/**
	 * Print the receipt and its details. Contains the name of the movie, the rating
	 * of the movie, the date, timing and class of the move, the hall number the
	 * seat numbers, the total price and the transaction ID.
	 * 
	 * @param receipt The receipt object for a transaction.
	 */
	public static void printReceipt(Receipt receipt) {
		System.out.println(
				"Title: " + receipt.getName() + " " + receipt.getPGRating() + "(" + Receipt.gettypeofMovie() + ")");
		System.out.println("Date: " + receipt.getmovieDay());
		System.out.println("Timing: " + receipt.gettiming());
		System.out.println("Cinema: " + Receipt.getclassofCinema());
		System.out.println(receipt.gethall());
		System.out.println("Seat(s): " + receipt.getSeat());
		System.out.print("$Price: ");
		System.out.printf("%.2f", receipt.getPrice());
		System.out.println();
		System.out.println("Transaction ID: " + receipt.gettransactionID());
		System.out.println();
	}
}
