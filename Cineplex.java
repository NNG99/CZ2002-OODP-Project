package Entity;

/**
 * Represents a cineplex. A cineplex contains multiple cinemas.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class Cineplex {
	/**
	 * The name of the cineplex
	 */
	private String cineplexName;

	/**
	 * Create a new cineplex with a given name
	 * 
	 * @param cineplexName this is the cineplex name
	 */
	public Cineplex(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * Get the cineplex name
	 * 
	 * @return the cineplex name
	 */
	public String getcineplexName() {
		return cineplexName;
	}

	Cinema[] cinema = new Cinema[3];
}
