package model;

public class AppStore {

	private String branchName;
	private int maximumNumberOfApps;
	private App[] apps;
	private int numberOfApps;

	// --------------- CONSTRUCTORS ---------------

	public AppStore(String branchName, int maximumNumberOfApps) {
		this.branchName = branchName;

		this.maximumNumberOfApps = maximumNumberOfApps;

		this.apps = new App[this.maximumNumberOfApps];
		this.numberOfApps = 0;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the app store branch */
	public String getBranch() {
		return this.branchName;
	}

	/** Returns the app specified by the app name */
	public App getApp(String appName) {
		for (int i = 0; i < this.numberOfApps; i++) {
			if (this.apps[i].getName().equals(appName)) {
				return this.apps[i];
			}
		}
		return null;
	}

	/** Returns information about the apps that have the specified updates or more */
	public String[] getStableApps(int minimumNumberOfUpdates) {
		int[] indices = new int[this.numberOfApps];
		int count = 0;

		// First, get the indices of the apps in this.apps that have the specified
		// number of updates or more
		for (int i = 0; i < this.numberOfApps; i++) {
			if (this.apps[i].getUpdateHistory().length >= minimumNumberOfUpdates) {
				indices[count] = i;
				count++;
			}
		}

		// The indices array may not be completely filled (since some apps may
		// have less than the specified number of updates).
		// So make a new array that will be completely filled with the information of
		// the apps that correspond to the indices in the indices array.
		String[] stableApps = new String[count];
		for (int i = 0; i < count; i++) {
			stableApps[i] = this.apps[indices[i]].getName()
					+ " (" + this.apps[indices[i]].getUpdateHistory().length + " versions; Current Version: "
					+ this.apps[indices[i]].getWhatIsNew() + ")";
		}

		return stableApps;
	}

	// --------------- MUTATORS ---------------

	public void addApp(App app) {
		this.apps[this.numberOfApps] = app;
		this.numberOfApps++;
	}
}
