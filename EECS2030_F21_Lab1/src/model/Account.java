package model;

public class Account {

	private String name;
	private AppStore linkedAppStore;
	private String status;
	private App[] downloadedAppsArray;
	private int numberOfDownloadedApps;
	private final int MAXIMUM_NUMBER_OF_DOWNLOADS = 50;

	// --------------- CONSTRUCTORS ---------------

	public Account(String name, AppStore linkedAppStore) {
		this.name = name;
		this.linkedAppStore = linkedAppStore;

		this.downloadedAppsArray = new App[this.MAXIMUM_NUMBER_OF_DOWNLOADS];
		this.numberOfDownloadedApps = 0;

		this.status = "An account linked to the "
				+ this.linkedAppStore.getBranch()
				+ " store is created for "
				+ this.name + ".";
	}

	// --------------- HELPER METHODS ---------------

	/** Returns the index of an app in the array of downloaded apps (returns -1 if not in array) */
	private int getIndexOfApp(String appName) {
		int indexOfApp = -1;

		for (int i = 0; i < this.numberOfDownloadedApps && indexOfApp == -1; i++) {
			if (this.downloadedAppsArray[i].getName().equals(appName)) {
				indexOfApp = i;
			}
		}

		return indexOfApp;
	}

	// --------------- ACCESSORS ---------------

	/** Returns an array of the names of the account's downloaded apps */
	public String[] getNamesOfDownloadedApps() {
		String[] namesOfDownloadedApps = new String[this.numberOfDownloadedApps];

		for (int i = 0; i < this.numberOfDownloadedApps; i++) {
			namesOfDownloadedApps[i] = this.downloadedAppsArray[i].getName();
		}

		return namesOfDownloadedApps;
	}

	/** Returns an array of the account's downloaded apps */
	public App[] getObjectsOfDownloadedApps() {
		App[] downloadedApps = new App[this.numberOfDownloadedApps];

		for (int i = 0; i < this.numberOfDownloadedApps; i++) {
			downloadedApps[i] = this.downloadedAppsArray[i];
		}

		return downloadedApps;
	}

	/** Returns the app store that is linked to the account */
	public AppStore getBranch() {
		return this.linkedAppStore;
	}

	/** Returns the status of the account */
	public String toString() {
		return this.status;
	}

	// --------------- MUTATORS ---------------

	/** Adds an app corresponding to the specified app name to the array of downloaded apps */
	public void download(String appName) {
		int indexOfApp = this.getIndexOfApp(appName);

		if (indexOfApp == -1) {
			this.downloadedAppsArray[this.numberOfDownloadedApps] = this.linkedAppStore.getApp(appName);
			this.numberOfDownloadedApps++;

			this.status = appName + " is successfully downloaded for " + this.name + ".";
		} else {
			this.status = "Error: " + appName + " has already been downloaded for " + this.name + ".";
		}
	}

	/** Removes an app corresponding to the specified app name from the array of downloaded apps */
	public void uninstall(String appName) {
		int indexOfApp = this.getIndexOfApp(appName);

		if (indexOfApp > -1) {
			for (int i = indexOfApp; i < this.numberOfDownloadedApps; i++) {
				this.downloadedAppsArray[i] = this.downloadedAppsArray[i + 1];
			}

			this.numberOfDownloadedApps--;

			this.status = appName + " is successfully uninstalled for " + this.name + ".";
		} else {
			this.status = "Error: " + appName + " has not been downloaded for " + this.name + ".";
		}
	}

	/** Submits a rating to the app specified by the app name */
	public void submitRating(String appName, int rating) {
		int indexOfApp = this.getIndexOfApp(appName);

		if (indexOfApp > -1) {
			this.linkedAppStore.getApp(appName).submitRating(rating);

			this.status = "Rating score " + rating + " of " + this.name + " is successfully submitted for " + appName + ".";
		} else {
			this.status = "Error: " + appName + " is not a downloaded app for " + this.name + ".";
		}
	}

	/** Switches account to the specified app store */
	public void switchStore(AppStore newAppStore) {
		this.linkedAppStore = newAppStore;
		this.status = "Account for " + this.name + " is now linked to the " + newAppStore.getBranch() + " store.";
	}

}
