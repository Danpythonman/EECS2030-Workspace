package model;

public class App {

	private String name;
	private int maximumNumberOfRatings;
	private int[] ratings;
	private int numberOfRatings;
	private Log[] updateLogs;
	private int numberOfUpdateLogs;
	private final int MAXIMUM_NUMBER_OF_UPDATE_LOGS = 20;
	private Log currentVersion;
	private double averageRating;

	// --------------- CONSTRUCTORS ---------------

	public App(String name, int maximumNumberOfRatings) {
		this.name = name;

		this.maximumNumberOfRatings = maximumNumberOfRatings;
		this.numberOfRatings = 0;
		this.ratings = new int[this.maximumNumberOfRatings];
		this.averageRating = 0.0;

		this.updateLogs = new Log[this.MAXIMUM_NUMBER_OF_UPDATE_LOGS];
		this.numberOfUpdateLogs = 0;

		this.currentVersion = null;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the app */
	public String getName() {
		return this.name;
	}

	/** Returns the latest update log */
	public String getWhatIsNew() {
		if (this.numberOfUpdateLogs > 0) {
			return this.currentVersion.toString();
		} else {
			return "n/a";
		}
	}

	/** Returns the array of update logs, not including the ones not initialized */
	public Log[] getUpdateHistory() {
		Log[] validUpdateLogs = new Log[this.numberOfUpdateLogs];

		for (int i = 0; i < this.numberOfUpdateLogs; i++) {
			validUpdateLogs[i] = this.updateLogs[i];
		}

		return validUpdateLogs;
	}

	/** Returns the log of the specified version */
	public Log getVersionInfo(String version) {
		// Iterate through the update logs to find the specified version
		for (int i = 0; i < this.numberOfUpdateLogs; i++) {
			if (this.updateLogs[i].getVersion().equals(version)) {
				return this.updateLogs[i];
			}
		}

		return null;
	}

	/** Returns a string containing the average rating and the number of each star rating */
	public String getRatingReport() {
		if (this.numberOfRatings > 0) {
			int fiveStarRatings = 0;
			int fourStarRatings = 0;
			int threeStarRatings = 0;
			int twoStarRatings = 0;
			int oneStarRatings = 0;

			for (int i = 0; i < this.numberOfRatings; i++) {
				switch (this.ratings[i]) {
					case 5: { fiveStarRatings++; break; }
					case 4: { fourStarRatings++; break; }
					case 3: { threeStarRatings++; break; }
					case 2: { twoStarRatings++; break; }
					case 1: { oneStarRatings++; break; }
				}
			}

			return "Average of " + this.numberOfRatings + " ratings: "
					+ String.format("%.1f", this.averageRating)
					+ " (Score 5: " + fiveStarRatings
					+ ", Score 4: " + fourStarRatings
					+ ", Score 3: " + threeStarRatings
					+ ", Score 2: " + twoStarRatings
					+ ", Score 1: " + oneStarRatings + ")";
		} else {
			return "No ratings submitted so far!";
		}
	}

	public String toString() {
		return this.name
				+ " (Current Version: "
				+ (this.currentVersion != null ? this.currentVersion.toString() : "n/a")
				+ "; Average Rating: "
				+ ((this.numberOfRatings > 0) ? String.format("%.1f", this.averageRating) : "n/a") + ")";
	}

	// --------------- MUTATORS ---------------

	/** Add an new update to the app's update logs */
	public void releaseUpdate(String version) {
		// Add the new update to the update log list
		this.updateLogs[this.numberOfUpdateLogs] = new Log(version);
		// Make the new update the current version
		this.currentVersion = this.updateLogs[this.numberOfUpdateLogs];
		// Increment number of update logs
		this.numberOfUpdateLogs++;
	}

	/** Add a rating (integer between 1 and 5) */
	public void submitRating(int rating) {
		// Add the new rating to the array of ratings
		this.ratings[this.numberOfRatings] = rating;

		// Calculate the average rating by multiplying the previous average
		// by the previous number of ratings, adding the new rating, and dividing
		// by the new number of ratings (previous number of ratings + 1)
		this.averageRating = ((this.averageRating * this.numberOfRatings) + rating) / (this.numberOfRatings + 1);

		// Increment number of ratings
		this.numberOfRatings++;
	}

}
