package model;

public class Transcript {

	private String name;
	private Registration[] registrations;
	private int numberOfRegistrations;
	private final int MAXIMUM_NUMBER_OF_REGISTRATIONS = 60;

	// --------------- CONSTRUCTORS ---------------

	public Transcript(String name) {
		this.name = name;
		this.registrations = new Registration[this.MAXIMUM_NUMBER_OF_REGISTRATIONS];
		this.numberOfRegistrations = 0;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the student */
	public String getStudentName() {
		return this.name;
	}

	/** Returns an array of all the registrations that have been added to the transcript */
	public Registration[] getReport() {
		Registration[] validRegistrations = new Registration[this.numberOfRegistrations];

		for (int i = 0; i < this.numberOfRegistrations; i++) {
			validRegistrations[i] = this.registrations[i];
		}

		return validRegistrations;
	}

	/** Gets the marks from the registration specified by the course name */
	public int getMarks(String courseName) {
		for (int i = 0; i < this.numberOfRegistrations; i++) {
			if (this.registrations[i].getCourseName().equals(courseName)) {
				return this.registrations[i].getMarks();
			}
		}

		return -1;
	}

	/**
	 * Returns the weighted GPA of the transcript.
	 * Weighted GPA denotes the Weighted Grade Point Average, which is calculated by
	 * first calculating the sum of the WEIGHTED grade points of all registrations on the transcript,
	 * then by dividing the sum by the number of registrations. 
	 * For example, t1 has two registrations: 
	 * 	CSE1 (with weighted grade point 7 * 3) and CSE3 (with weighed grade point 5 * 4),
	 *  then t1's weighed GPA is calculated by (21 + 20) / 2. 
	 * Requirement: When the number of registrations on the transcript is zero, then
	 * return 0 as the weighted GPA.
	 */
	public double getWeightedGPA() {
		int sumOfWeightedGrades = 0;

		for (int i = 0; i < this.numberOfRegistrations; i++) {
			sumOfWeightedGrades += this.registrations[i].getWeightedGradePoint();
		}

		// The 1.0 is to convert int to double
		double weightedGPA = sumOfWeightedGrades / (1.0 * this.numberOfRegistrations);

		return weightedGPA;
	}

	// --------------- MUTATORS ---------------

	// 2 MUTATORS FOR addRegistration

	/** Adds a registration to the transcript with a registration object */
	public void addRegistration(Registration registration) {
		this.registrations[this.numberOfRegistrations] = registration;
		this.numberOfRegistrations++;
	}

	/** Adds a registration to the transcript with a registration name and number of credits */
	public void addRegistration(String name, int credits) {
		this.registrations[this.numberOfRegistrations] = new Registration(name, credits);
		this.numberOfRegistrations++;
	}

	/** Adds registrations to the transcript that are in an array of registrations */
	public void addRegistrations(Registration[] registrationsArray) {
		for (int i = 0; i < registrationsArray.length; i++) {
			this.registrations[this.numberOfRegistrations] = registrationsArray[i];
			this.numberOfRegistrations++;
		}
	}

	/** Set the marks for a registration */
	public void setMarks(String courseName, int marks) {
		for (int i = 0; i < this.numberOfRegistrations; i++) {
			if (this.registrations[i].getCourseName().equals(courseName)) {
				this.registrations[i].setMarks(marks);
				break;
			}
		}
	}
}
