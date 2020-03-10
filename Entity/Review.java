package Entity;

import java.util.ArrayList;

/**
 * Represents the review, ratings and sales of movies
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 * @author Marcus
 *
 */
public class Review {
	/**
	 * The name of the movie.
	 */
	private String movieName;
	/**
	 * An array list containing the reviews of a movie.
	 */
	private ArrayList<String> moviereviewArray;
	/**
	 * An array list containing the ratings for a movie.
	 */
	private ArrayList<Integer> movieratingArray;

	/**
	 * Create
	 */
//	public Review() {
//	}
	/**
	 * Create a new review given the movie name
	 * 
	 * @param movieName the name of the movie
	 */
	public Review(String movieName) {
		this.movieName = movieName;
		moviereviewArray = new ArrayList<String>();
		movieratingArray = new ArrayList<Integer>();
	}

	/**
	 * Get the array list of ratings.
	 * 
	 * @return an array list of ratings.
	 */
	public ArrayList<Integer> getratingArray() {
		return movieratingArray;
	}

	/**
	 * Get an array list of reviews.
	 * 
	 * @return the array list of reviews
	 */
	public ArrayList<String> getreviewArray() {
		return moviereviewArray;
	}

	/**
	 * Get the name of the movie.
	 * 
	 * @return the name of the movie
	 */
	public String getmovieName() {
		return movieName;
	}

	/**
	 * Update the array list containing all the movie ratings
	 * 
	 * @param review this is the review object for a particular movie
	 * @param rating this is the rating for that movie
	 */
	public static void updatemovieRating(Review review, int rating) {
		review.getratingArray().add(rating);
	}

	/**
	 * Update the array list containing all the movie reviews.
	 * 
	 * @param review this is the review object for a particular movie.
	 * @param rw     this is the review for that movie
	 */
	public static void updatemovieReview(Review review, String rw) {
		review.getreviewArray().add(rw);
	}

	/**
	 * Prints out all ratings for a particular movie.
	 * 
	 * @param review this is the review object for that particular movie
	 */
	public static void printmovieRating(Review review) {
		for (int j = 0; j < review.getratingArray().size(); j++) {
			int k = review.getratingArray().get(j);
			System.out.print(k + " ");
			for (int l = 0; l < k; l++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Prints out all reviews for a particular movie.
	 * 
	 * @param review this is the review object for a particular movie.
	 */
	public static void printReviews(Review review) {
		for (int j = 0; j < review.getreviewArray().size(); j++) {
			System.out.print(j + 1 + ". " + review.getreviewArray().get(j));
			System.out.print("\n");
		}
	}

	/**
	 * Calculate the overall rating of a particular movie Done by dividing total
	 * ratings by total number of users who rated that movie.
	 * 
	 * @param review this is the review object that a particular movie.
	 * @return the overall rating of the movie
	 */
	public double getoverallRating(Review review) {
		double overallrating = 0;
		int x = 0;
		if (review.getratingArray().size() == 0) {
			return 0;
		} else {
			for (int i = 0; i < review.getratingArray().size(); i++) {
				overallrating = review.getratingArray().get(i) + overallrating;
				x++;
			}
		}
		double avg = overallrating / x;
		return avg;
	}

	/**
	 * Prints out the top 5 movies based on sales prices
	 * 
	 * @param al Array list containing all the sales.
	 */
	public static void printtop5Sales(ArrayList<Sales> al) {
		String[][] top5 = new String[5][2];
		double sale = 0;
		for (int i = 0; i < 5; i++) {
			sale = al.get(i).getticketSales();
			top5[i][0] = al.get(i).getmovieName();
			top5[i][1] = Double.toString(sale);
		}

		String[][] temp = new String[1][2];

		for (int i = 1; i < 5; i++) {
			for (int j = i; j > 0; j--) {
				if (Double.parseDouble(top5[j][1]) > Double.parseDouble(top5[j - 1][1])) {
					temp[0][0] = top5[j][0];
					temp[0][1] = top5[j][1];
					top5[j][0] = top5[j - 1][0];
					top5[j][1] = top5[j - 1][1];
					top5[j - 1][0] = temp[0][0];
					top5[j - 1][1] = temp[0][1];
				}
			}
		}

		temp[0][0] = top5[4][0];
		temp[0][1] = top5[4][1];

		for (int i = 1; i < al.size(); i++) {
			if (al.get(i).getticketSales() > Double.parseDouble(temp[0][1])) {
				for (int j = 1; j < 5; j++) {
					for (int k = j; k > 0; k--) {
						if (Double.parseDouble(top5[k][1]) > Double.parseDouble(top5[k - 1][1])) {
							temp[0][0] = top5[k][0];
							temp[0][1] = top5[k][1];
							top5[j][0] = top5[k - 1][0];
							top5[k][1] = top5[k - 1][1];
							top5[k - 1][0] = temp[0][0];
							top5[k - 1][1] = temp[0][1];
						}
					}
				}
			}
		}
		System.out.println("====Top 5 Movies Based on Sales====");
		for (int a = 0; a < 5; a++) {
			System.out.print(a + 1 + ". " + top5[a][0] + ": $");
			System.out.printf("%.2f ",  + Double.parseDouble(top5[a][1])) ;
			System.out.println() ;
		}
	}

	/**
	 * Prints out the top 5 movie base on ratings
	 * 
	 * @param al this is an array list containing all the review
	 */
	public static void printtop5Rating(ArrayList<Review> al) {
		String[][] top5 = new String[5][2];
		double rating = 0;
		for (int i = 0; i < 5; i++) {
			rating = al.get(i).getoverallRating(al.get(i));
			top5[i][0] = al.get(i).getmovieName();
			top5[i][1] = Double.toString(rating);
		}
		String[][] temp = new String[1][2];

		for (int i = 1; i < 5; i++) {
			for (int j = i; j > 0; j--) {
				if (Double.parseDouble(top5[j][1]) > Double.parseDouble(top5[j - 1][1])) {
					temp[0][0] = top5[j][0];
					temp[0][1] = top5[j][1];
					top5[j][0] = top5[j - 1][0];
					top5[j][1] = top5[j - 1][1];
					top5[j - 1][0] = temp[0][0];
					top5[j - 1][1] = temp[0][1];
				}
			}
		}

		temp[0][0] = top5[4][0];
		temp[0][1] = top5[4][1];

		for (int i = 1; i < al.size(); i++) {
			if (al.get(i).getoverallRating(al.get(i)) > Double.parseDouble(temp[0][1])) {
				for (int j = 1; j < 5; j++) {
					for (int k = j; k > 0; k--) {
						if (Double.parseDouble(top5[k][1]) > Double.parseDouble(top5[k - 1][1])) {
							temp[0][0] = top5[k][0];
							temp[0][1] = top5[k][1];
							top5[j][0] = top5[k - 1][0];
							top5[k][1] = top5[k - 1][1];
							top5[k - 1][0] = temp[0][0];
							top5[k - 1][1] = temp[0][1];
						}
					}
				}
			}
		}
		System.out.println("====Top 5 Movies Based on Rating====");
		for (int a = 0; a < 5; a++) {
			System.out.print(a + 1 + ". " + top5[a][0] + ": ");
			System.out.printf("%.1f ",  + Double.parseDouble(top5[a][1])) ;
			System.out.println() ;
		}
	}

}
