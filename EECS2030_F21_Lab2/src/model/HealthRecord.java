package model;

public class HealthRecord {

	private String name;
	private Vaccine[] vaccinesReceived;
	private String[] vaccinationLocations;
	private String[] vaccinationDates;
	private int numberOfVaccinesReceived;
	private String appointmentStatus;

	// --------------- CONSTRUCTORS ---------------

	public HealthRecord(String name, int maximumNumberOfDoses) {
		this.name = name;

		this.vaccinesReceived = new Vaccine[maximumNumberOfDoses];
		this.vaccinationLocations = new String[maximumNumberOfDoses];
		this.vaccinationDates = new String[maximumNumberOfDoses];
		this.numberOfVaccinesReceived = 0;

		this.appointmentStatus = "No vaccination appointment for " + name + " yet";
	}

	// --------------- ACCESSORS ---------------

	/** Returns the name of the owner of the health record */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns a string containing information about the number of doses received by
	 * the owner of the health record and information about each dose
	 * (whether is it recognized by Canada or not, code name, vaccine type, manufacturer, location, and date)
	 */
	public String getVaccinationReceipt() {
		if (this.numberOfVaccinesReceived > 0) {
			String vaccinationReceipt = "Number of doses " + this.name + " has received: " + this.numberOfVaccinesReceived + " [";

			for (int i = 0; i < this.numberOfVaccinesReceived; i++) {
				vaccinationReceipt += ((this.vaccinesReceived[i].isRecognizedByCanada()) ? "Recognized vaccine: " : "Unrecognized vaccine: ")
							+ this.vaccinesReceived[i].getCodeName()
							+ " (" + this.vaccinesReceived[i].getVaccineType() + "; " + this.vaccinesReceived[i].getManufacturer()
							+ ") in " + this.vaccinationLocations[i]
							+ " on " + this.vaccinationDates[i];

				if (i != this.numberOfVaccinesReceived - 1) {
					vaccinationReceipt += "; ";
				}
			}

			vaccinationReceipt += "]";

			return vaccinationReceipt;
		} else {
			return this.name + " has not yet received any doses.";
		}
	}

	/** Get the appointment status of the health record */
	public String getAppointmentStatus() {
		return this.appointmentStatus;
	}

	// --------------- MUTATORS ---------------

	/** Add a information about a vaccine dose to the health record.
	 * The information contains the vaccine and the location and date of administration.
	 */
	public void addRecord(Vaccine vaccine, String vaccinationLocation, String vaccinationDate) {
		this.vaccinesReceived[this.numberOfVaccinesReceived] = vaccine;
		this.vaccinationLocations[this.numberOfVaccinesReceived] = vaccinationLocation;
		this.vaccinationDates[this.numberOfVaccinesReceived] = vaccinationDate;
		this.numberOfVaccinesReceived++;
	}

	/** Set the appointment status of the health record */
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
