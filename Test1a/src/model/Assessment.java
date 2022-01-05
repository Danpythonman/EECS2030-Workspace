package model;

public class Assessment {

	private String assessmentName;
	private double weight;
	public int marks; // marks out of 100
	private String status;

	// --------------- CONSTRUCTORS ---------------
	// 2 CONSTRUCTORS

	public Assessment(String assessmentName, double weight) {
		this.assessmentName = assessmentName;
		this.weight = weight;
		this.marks = 0;

		this.status = "Assessment created: " + assessmentName + " accounts for "
				+ String.format("%.3f", weight * 100)
				+ " percents of the course.";
	}

	public Assessment(String assessmentName, double weight, int marks) {
		this.assessmentName = assessmentName;
		this.weight = weight;
		this.marks = marks;

		this.status = "Assessment created: " + assessmentName + " accounts for "
				+ String.format("%.3f", weight * 100)
				+ " percents of the course.";
	}


	// --------------- ACCESSORS ---------------

	/** Returns the name of the assessment */
	public String getAssessmentName() {
		return this.assessmentName;
	}

	/** Returns the weight of the assessment (as a number between 0 and 1) */
	public double getWeight() {
		return this.weight;
	}

	/** Returns the marks for the assessment */
	public int getMarks() {
		return this.marks;
	}

	public String toString() {
		return this.status;
	}

	// --------------- MUTATORS ---------------

	/** Set the marks for the assessment */
	public void setMarks(int marks) {
		int previousMarks = this.marks;

		this.marks = marks;

		this.status = "Marks of assessment " + this.assessmentName
				+ " (accounting for " + String.format("%.3f", this.weight * 100)
				+ " percents of the course) is changed from " + previousMarks + " to " + this.marks + ".";
	}

	/** Set the weight for the assessment */
	public void setWeight(double weight) {
		double previousWeight = this.weight;

		this.weight = weight;

		this.status = "Weight of assessment " + this.assessmentName
				+ " (with marks " + this.marks + ") is changed from "
				+ String.format("%.3f", previousWeight * 100) + " percents to "
				+ String.format("%.3f", this.weight * 100) + " percents.";
	}

}
