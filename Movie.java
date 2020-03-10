package Entity;

/**
 * 
 * Represents a movie that is available. The movie may be either showing, not
 * showing or waiting to be premiere
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class Movie {
	/**
	 * Name of the movie
	 */
	private String name;
	/**
	 * The PG rating of the movie
	 */
	private String PGRating;
	/**
	 * Whether the movie is 2D or 3D
	 */
	private String typeofMovie;
	/**
	 * Whether it is showing, not showing, coming soon or preview.
	 */
	private String Status;
	/**
	 * The director of the movie
	 */
	private String Director;
	/**
	 * The cast of the movie
	 */
	private String Cast;
	/**
	 * The synopsis of the movie
	 */
	private String Synopsis;

	/**
	 * Create a new movie with the following parameters
	 * 
	 * @param name        the movie name
	 * @param PGRating    the movie rating
	 * @param typeofMovie if the movie is 2D or 3D
	 * @param Status      the status of the movie
	 * @param Director    the name of the director
	 * @param Cast        the casts of the movie
	 * @param Synopsis    the synopsis of the movie
	 */
	public Movie(String name, String PGRating, String typeofMovie, String Status, String Director, String Cast,
			String Synopsis) {
		this.name = name;
		this.PGRating = PGRating;
		this.typeofMovie = typeofMovie;
		this.Status = Status;
		this.Director = Director;
		this.Cast = Cast;
		this.Synopsis = Synopsis;
	}

	/**
	 * Get the name of the movie
	 * 
	 * @return the name of the movie
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the PG rating of the movie
	 * 
	 * @return the PG rating of the movie
	 */
	public String getPGRating() {
		return PGRating;
	}

	/**
	 * Get the type of movie (2D or 3D)
	 * 
	 * @return the type of movie
	 */
	public String gettypeofMovie() {
		return typeofMovie;
	}

	/**
	 * Get showing status of movie
	 * 
	 * @return the status of the movie
	 */
	public String getStatus() {
		return this.Status;
	}

	/**
	 * Get the director of the movie
	 * 
	 * @return the director name
	 */
	public String getDirector() {
		return Director;
	}

	/**
	 * Get the casts of the movie
	 * 
	 * @return the name of casts of the movie
	 */
	public String getCast() {
		return Cast;
	}

	/**
	 * Get the synopsis of the movie
	 * 
	 * @return the synopsis of the movie
	 */
	public String getSynopsis() {
		return Synopsis;
	}

}
