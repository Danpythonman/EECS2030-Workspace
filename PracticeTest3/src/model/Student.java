package model;

public class Student {

	private String name;
	private Course courses[];
	private int numberOfCourses;
	private final int MAXIMUM_NUMBER_OF_COURSES = 10;

	// --------------- CONSTRUCTORS ---------------

	public Student(String name) {
		this.name = name;

		this.courses = new Course[this.MAXIMUM_NUMBER_OF_COURSES];
		this.numberOfCourses = 0;
	}

	// --------------- HELPER METHODS ---------------

	/**
	 * Get the index of the specified course in the student's array of courses.
	 * Returns -1 if course is not found.
	 */
	private int getIndexOfCourse(Course course) {
		int index = -1;

		for (int i = 0; i < this.numberOfCourses && index == -1; i++) {
			if (this.courses[i] == course) {
				index = i;
			}
		}

		return index;
	}

	// --------------- MUTATORS ---------------

	/** Get the name of the student */
	public String getName() {
		return this.name;
	}

	/** Get an array of all the courses that have been initialized */
	public Course[] getCourses() {
		Course[] nonNullCourses = new Course[this.numberOfCourses];

		for (int i = 0; i < this.numberOfCourses; i++) {
			nonNullCourses[i] = this.courses[i];
		}

		return nonNullCourses;
	}

	/** Get the sum of the fees of all the courses (with no rate applied) */
	public double getTuition() {
		double tuition = 0.0;

		for (int i = 0; i < this.numberOfCourses; i++) {
			tuition += this.courses[i].getFee();
		}

		return tuition;
	}

	// --------------- MUTATORS ---------------

	/** Register student for the specified course */
	public void register(Course course) {
		this.courses[this.numberOfCourses] = course;
		this.numberOfCourses++;
	}

	/** Remove student from the specified course */
	public void drop(Course course) {
		int indexOfCourse = this.getIndexOfCourse(course);

		if (indexOfCourse >= 0) {

			this.numberOfCourses--;

			for (int i = indexOfCourse; i < this.numberOfCourses; i++) {
				courses[i] = courses[i + 1];
			}

			this.courses[this.numberOfCourses] = null;
		}
	}

}
