package model;

public class StudentRecord {

	private String courseName;
	private Assessment[] assessments;
	private int numberOfAssessments;
	private final int MAXIMUM_NUMBER_OF_ASSESSMENTS = 10;

	// --------------- CONSTRUCTORS ---------------

	public StudentRecord(String courseName) {
		this.courseName = courseName;

		this.assessments = new Assessment[this.MAXIMUM_NUMBER_OF_ASSESSMENTS];
		this.numberOfAssessments = 0;
	}

	// --------------- HELPER METHODS ---------------

	/**
	 * Returns the index of an assessment in this.assessments given an assessment name.
	 * If not found, -1 will be returned.
	 */
	private int getIndexOfAssessmentByName(String assessmentName) {
		int index = -1;

		for (int i = 0; i < this.numberOfAssessments; i++) {
			if (this.assessments[i].getAssessmentName().equals(assessmentName)) {
				index = i;
			}
		}

		return index;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the course */
	public String getCourse() {
		return this.courseName;
	}

	/**
	 * Returns a string containing the course name, the number of assessments,
	 * and the name, weight, and marks for each assessment
	 */
	public String getAssessmentReport() {
		String assessmentReport = "Number of assessments in "
				+ this.courseName + ": " + this.numberOfAssessments + " [";

		for (int i = 0; i < this.numberOfAssessments; i++) {
			assessmentReport += this.assessments[i].getAssessmentName()
					+ " (weight: " + String.format("%.3f", this.assessments[i].getWeight() * 100)
					+ " percents; marks: " + this.assessments[i].getMarks() + ")";

			if (i != this.numberOfAssessments - 1) {
				assessmentReport += ", ";
			}
		}

		assessmentReport += "]";

		return assessmentReport;
	}

	/**
	 * Returns the completion rate of the record.
	 * Calculatied by adding up the weights of each assessment in the record.
	 */
	public double getCompletionRate() {
		double completionRate = 0.0;

		for (int i = 0; i < this.numberOfAssessments; i++) {
			completionRate += this.assessments[i].getWeight();
		}

		return completionRate;
	}

	/**
	 * Returns the raw marks of the record.
	 * Calculatied by adding up the product of marks and weight of each assessment in the record.
	 */
	public double getRawMarks() {
		double rawMarks = 0.0;

		for (int i = 0; i < this.numberOfAssessments; i++) {
			rawMarks += this.assessments[i].getWeight() * this.assessments[i].getMarks();
		}

		return rawMarks;
	}

	// --------------- MUTATORS ---------------

	// 2 MUTATORS FOR addAssessment

	/** Adds an assessment given the name of an assessment, its weight, and the its marks */
	public void addAssessment(String assessmentName, double weight, int marks) {
		this.assessments[this.numberOfAssessments] = new Assessment(assessmentName, weight, marks);
		this.numberOfAssessments++;
	}

	/** Adds an assessment given the an assessment object */
	public void addAssessment(Assessment assessment) {
		this.assessments[this.numberOfAssessments] = assessment;
		this.numberOfAssessments++;
	}

	/** Change the marks of an assessment */
	public void changeMarksOf(String assessmentName, int newMarks) {
		int indexOfAssessment = this.getIndexOfAssessmentByName(assessmentName);

		if (indexOfAssessment != -1) {
			this.assessments[indexOfAssessment].setMarks(newMarks);
		}
	}

	/** Remove an assessment from the array of assessments in the record */
	public void removeAssessment(String assessmentName) {
		int indexOfAssessment = this.getIndexOfAssessmentByName(assessmentName);

		if (indexOfAssessment != -1) {
			for (int i = indexOfAssessment; i < this.numberOfAssessments; i++) {
				if (i != this.MAXIMUM_NUMBER_OF_ASSESSMENTS - 1) {
					this.assessments[i] = this.assessments[i + 1];
				} else {
					this.assessments[MAXIMUM_NUMBER_OF_ASSESSMENTS - 1] = null;
				}
			}

			this.numberOfAssessments--;
		}
	}

}
