package model;

public class Course {

	private String title;
	private double fee;

	// --------------- CONSTRUCTORS ---------------

	public Course(String title, double fee) {
		this.title = title;
		this.fee = fee;
	}

	// --------------- ACCESSORS ---------------

	/** Get the title of the course */
	public String getTitle() {
		return this.title;
	}

	/** Get the fee of the course */
	public double getFee() {
		return this.fee;
	}

}
