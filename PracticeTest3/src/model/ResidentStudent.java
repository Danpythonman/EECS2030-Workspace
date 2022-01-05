package model;

public class ResidentStudent extends Student {

	double premiumRate;

	// --------------- CONSTRUCTORS ---------------

	public ResidentStudent(String name) {
		super(name);
	}

	// --------------- ACCESSORS ---------------

	/** Get the premium rate for the resident student */
	public double getPremiumRate() {
		return this.premiumRate;
	}

	/**
	 * Get the tuition for the resident student.
	 * This is the sum of the fees of all the courses that the student is
	 * registered in, with the resident student's premium rate applied
	 */
	public double getTuition() {
		return super.getTuition() * this.premiumRate;
	}

	// --------------- MUTATORS ---------------

	/** Set the premium rate for the resident student */
	public void setPremiumRate(double rate) {
		this.premiumRate = rate;
	}

}
