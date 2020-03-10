package Entity;

/**
 * Represent the sales of movie tickets.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 *
 */
public class Sales {
	/**
	 * The name of the movie sold.
	 */
	private String movieName;
	/**
	 * The profit earned from ticket sales.
	 */
	private double ticketSales;

	/**
	 * Constructor for sales. Used for method overloading.
	 */
	public Sales() {

	}

	/**
	 * Create a new sales given the movie name and the amount earned from that
	 * transaction.
	 * 
	 * @param movieName   This is the name of the movie sold.
	 * @param ticketSales This is the amount earned from the sale.
	 */
	public Sales(String movieName, double ticketSales) {
		this.movieName = movieName;
		this.ticketSales = ticketSales;
	}

	/**
	 * Get the amount earned from a transaction.
	 * 
	 * @return the amount earned.
	 */
	public double getticketSales() {
		return ticketSales;
	}

	/**
	 * Get the name of the movie.
	 * 
	 * @return the name of the movie.
	 */
	public String getmovieName() {
		return movieName;
	}

	/**
	 * Change the value of the sale to the value of this sale.
	 * 
	 * @param sale  This is sale object.
	 * @param price This is the new price we are setting.
	 */
	public void setticketSale(Sales sale, double price) {
		sale.ticketSales = sale.getticketSales() + price;
	}
}
