package model;

public class Log {
	private String version;
	private int numberOfFixes;
	private String[] fixes;
	private final int MAXIMUM_NUMBER_OF_FIXES = 10;

	// --------------- CONSTRUCTORS ---------------

	public Log(String version) {
		this.version = version;
		this.numberOfFixes = 0;
		this.fixes = new String[this.MAXIMUM_NUMBER_OF_FIXES];
	}

	// --------------- ACCESSORS ---------------

	/** Returns the version of the log */
	public String getVersion() {
		return this.version;
	}

	/** Returns the number of fixes in the log */
	public int getNumberOfFixes() {
		return this.numberOfFixes;
	}

	/** Returns a string of the array of fixes */
	public String getFixes() {
		String fixesString = "[";

		for (int i = 0; i < this.numberOfFixes; i++) {
			fixesString += this.fixes[i];

			// Add a comma and space after every fix except the last one
			if (i < this.numberOfFixes - 1) {
				fixesString += ", ";
			}
		}

		fixesString += "]";

		return fixesString;
	}

	public String toString() {
		return "Version " + this.version + " contains " + this.numberOfFixes + " fixes " + this.getFixes();
	}

	// --------------- MUTATORS ---------------

	/** Adds a fix to the log's array of fixes */
	public void addFix(String fix) {
		this.fixes[this.numberOfFixes] = fix;
		this.numberOfFixes++;
	}

}
