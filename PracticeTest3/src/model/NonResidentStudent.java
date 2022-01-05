package model;

public class NonResidentStudent extends Student {

	double discountRate;

	// --------------- CONSTRUCTORS ---------------

	public NonResidentStudent(String name) {
		super(name);
	}

	// --------------- ACCESSORS ---------------

	/** Get the discount rate for the non resident student */
	public double getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * Get the tuition for the non resident student.
	 * This is the sum of the fees of all the courses that the student is
	 * registered in, with the non resident student's discount rate applied.
	 */
	public double getTuition() {
		return super.getTuition() * this.discountRate;
	}

	// --------------- MUTATORS ---------------

	/** Set the discount rate for the non resident student */
	public void setDiscountRate(double rate) {
		this.discountRate = rate;
	}

}
