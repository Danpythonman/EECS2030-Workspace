package model;

public class StudentManagementSystem {

	private Student[] students;
	private int numberOfStudents;
	private final int MAXIMUM_NUMBER_OF_STUDENTS = 10;

	// --------------- CONSTRUCTORS ---------------

	public StudentManagementSystem() {
		this.students = new Student[this.MAXIMUM_NUMBER_OF_STUDENTS];
		this.numberOfStudents = 0;
	}

	// --------------- ACCESSORS ---------------

	/**
	 * Get an array of students that have been added to the
	 * student management system
	 */
	public Student[] getStudents() {
		Student[] nonNullStudents = new Student[this.numberOfStudents];

		for (int i = 0; i < this.numberOfStudents; i++) {
			nonNullStudents[i] = this.students[i];
		}

		return nonNullStudents;
	}

	// --------------- MUTATORS ---------------

	/** Add a student to the students management system */
	public void addStudent(Student student) {
		this.students[this.numberOfStudents] = student;
		this.numberOfStudents++;
	}

	/**
	 * Register all students in the student management system
	 * for the specified course.
	 */
	public void registerAll(Course course) {
		for (int i = 0; i < this.numberOfStudents; i++) {
			students[i].register(course);
		}
	}

}
