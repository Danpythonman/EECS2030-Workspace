package model;

public class VaccineDistribution {

	private Vaccine vaccine;
	private int numberOfDoses;

	// --------------- CONSTRUCTORS ---------------

	public VaccineDistribution(Vaccine vaccine, int numberOfDoses) {
		this.vaccine = vaccine;
		this.numberOfDoses = numberOfDoses;
	}

	// --------------- ACCESSORS ---------------

	/** Return the number of doses of the distribution */
	public int getNumberOfDoses() {
		return this.numberOfDoses;
	}

	/** Returns the vaccine in the distribution */
	public Vaccine getVaccine() {
		return this.vaccine;
	}

	public String toString() {
		return this.numberOfDoses + " doses of " + this.vaccine.getCodeName() + " by " + this.vaccine.getManufacturer();
	}

	// --------------- MUTATORS ---------------

	/** Add additional doses of the vaccine to the distribution */
	public void addDoses(int additionalDoses) {
		this.numberOfDoses += additionalDoses;
	}

	/** Remove a does of the vaccine from the distribution (use this when a dose is administered) */
	public void removeDose() {
		this.numberOfDoses--;
	}

}
