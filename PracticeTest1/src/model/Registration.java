package model;

public class Registration {

	private String courseName;
	private int credits;
	private int rawMarks;

	// --------------- CONSTRUCTORS ---------------
	// 2 CONSTRUCTORS

	public Registration(String courseName, int credits) {
		this.courseName = courseName;
		this.credits = credits;

		this.rawMarks = 0;
	}

	public Registration(String courseName, int credits, int rawMarks) {
		this.courseName = courseName;
		this.credits = credits;
		this.rawMarks = rawMarks;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the registration */
	public String getCourseName() {
		return this.courseName;
	}

	/** Returnd the number of credits of the registration */
	public int getNumberOfCredits() {
		return this.credits;
	}

	/** Returns the number of marks of the registration */
	public int getMarks() {
		return this.rawMarks;
	}

	/**
	 * Returns the letter grade of the registration.
	 * Here is the map from numerical raw marks to string letter grades:
	 * Marks >= 90			: "A+"
	 * 80 <= Marks <  90	: "A"
	 * 70 <= Marks <  80	: "B"
	 * 60 <= Marks <  70	: "C"
	 * 50 <= Marks <  60	: "D"
	 * Marks < 50			: "F"
	 */
	public String getLetterGrade() {
		String letterGrade;

		if (this.rawMarks >= 90) {
			letterGrade = "A+";
		} else if (this.rawMarks >= 80) {
			letterGrade = "A";
		} else if (this.rawMarks >= 70) {
			letterGrade = "B";
		} else if (this.rawMarks >= 60) {
			letterGrade = "C";
		} else if (this.rawMarks >= 50) {
			letterGrade = "D";
		} else {
			letterGrade = "F";
		}

		return letterGrade;
	}

	/**
	 * Returns the weighted GPA of the registration
	 * Weighted Grade Point: grade point * number of credits.
	 * Here is the map from string letter grades to numerical grade points:
	 * "A+"	: 9
	 * "A"	: 8
	 * "B"	: 7
	 * "C" 	: 6
	 * "D"	: 5
	 * "F"	: 0
	 * For example, for r1 with letter grade B (and hence grade point 7) and 3 credits, 
	 * its weighted grade point is 7 * 3. 
	 */
	public int getWeightedGradePoint() {
		int weightedGradePoint;

		if (this.rawMarks >= 90) {
			weightedGradePoint = 9 * this.credits;
		} else if (this.rawMarks >= 80) {
			weightedGradePoint = 8 * this.credits;
		} else if (this.rawMarks >= 70) {
			weightedGradePoint = 7 * this.credits;
		} else if (this.rawMarks >= 60) {
			weightedGradePoint = 6 * this.credits;
		} else if (this.rawMarks >= 50) {
			weightedGradePoint = 5 * this.credits;
		} else {
			weightedGradePoint = 0;
		}

		return weightedGradePoint;
	}

	// --------------- MUTATORS ---------------

	/** Sets the marks of the registration */
	public void setMarks(int rawMarks) {
		this.rawMarks = rawMarks;
	}

}
