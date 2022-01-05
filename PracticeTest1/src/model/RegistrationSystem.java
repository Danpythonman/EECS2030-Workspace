package model;

public class RegistrationSystem {

	private Transcript[] transcripts;
	private int numberOfTranscripts;
	private int MAXIMUM_NUMBER_OF_TRANSCRIPTS = 500;

	// --------------- CONSTRUCTORS ---------------

	public RegistrationSystem() {
		this.transcripts = new Transcript[this.MAXIMUM_NUMBER_OF_TRANSCRIPTS];
		this.numberOfTranscripts = 0;
	}

	// --------------- ACCESSORS ---------------

	/** Returns array of transcripts that have been added to the registration system */
	public Transcript[] getReport() {
		Transcript[] validTranscripts = new Transcript[this.numberOfTranscripts];

		for (int i = 0; i < this.numberOfTranscripts; i++) {
			validTranscripts[i] = this.transcripts[i];
		}

		return validTranscripts;
	}

	/** Returns the marks of of a registration specified by the student name and course name */
	public int getMarks(String studentName, String courseName) {
		for (int i = 0; i < this.numberOfTranscripts; i++) {
			if (this.transcripts[i].getStudentName().equals(studentName)) {
				return this.transcripts[i].getMarks(courseName);
			}
		}

		return -1;
	}

	// --------------- MUTATORS ---------------

	/** Adds a transcript object to the array of transcripts */
	public void addTranscript(Transcript transcript) {
		this.transcripts[this.numberOfTranscripts] = transcript;
		this.numberOfTranscripts++;
	}

}
