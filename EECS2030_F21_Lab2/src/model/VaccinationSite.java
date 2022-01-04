package model;

public class VaccinationSite {

	private String name;
	private int maximumNumberOfVaccines;
	private VaccineDistribution[] vaccineDistributions;
	private int numberOfDistributions;
	private final int MAXIMUM_NUMBER_OF_DISTRIBUTIONS = 4;
	private HealthRecord[] appointments;
	private int numberOfAppointments;
	private final int MAXIMUM_NUMBER_OF_APPOINTMENTS = 200;

	// --------------- CONSTRUCTORS ---------------

	public VaccinationSite(String name, int maximumNumberOfVaccines) {
		this.name = name;
		this.maximumNumberOfVaccines = maximumNumberOfVaccines;

		this.vaccineDistributions = new VaccineDistribution[this.MAXIMUM_NUMBER_OF_DISTRIBUTIONS];
		this.numberOfDistributions = 0;

		this.appointments = new HealthRecord[this.MAXIMUM_NUMBER_OF_APPOINTMENTS];
		this.numberOfAppointments = 0;
	}

	// --------------- HELPER METHODS ---------------

	/**
	 * Return the index of a vaccine distribution in the array of vaccine distributions
	 * corresponding to the vaccine with the specified code name.
	 * If the no such distribution exists, -1 is returned.
	 */
	private int getIndexOfDistributionByCodeName(String codeName) {
		int index = -1;

		for (int i = 0; i < this.numberOfDistributions && index == -1; i++) {
			if (this.vaccineDistributions[i].getVaccine().getCodeName().equals(codeName)) {
				index = i;
			}
		}
	
		return index;
	}

	// --------------- ACCESSORS ---------------

	// 2 ACCESSORS FOR getNumberOfAvailableDoses

	/** Gets the total number of available doses */
	public int getNumberOfAvailableDoses() {
		int numberOfAvailableDoses = 0;

		for (int i = 0; i < this.numberOfDistributions; i++) {
			numberOfAvailableDoses += this.vaccineDistributions[i].getNumberOfDoses();
		}

		return numberOfAvailableDoses;
	}

	/**
	 * Gets the total number of available doses of the vaccine distribution
	 * corresponding to the vaccine with the specified code name
	 */
	public int getNumberOfAvailableDoses(String codeName) {
		int index = getIndexOfDistributionByCodeName(codeName);

		// If vaccine distribution with vaccine specified by code name exists,
		// return the number of doses in that distribution, otherwise return 0
		return index > -1 ? this.vaccineDistributions[index].getNumberOfDoses() : 0;
	}

	public String toString() {
		String vaccinationSiteString = this.name + " has " + this.getNumberOfAvailableDoses()
			+ " available doses: <";

		for (int i = 0; i < this.numberOfDistributions; i++) {
			vaccinationSiteString += this.vaccineDistributions[i].getNumberOfDoses() + " doses of "
					+ this.vaccineDistributions[i].getVaccine().getManufacturer();

			if (i < this.numberOfDistributions - 1) {
				vaccinationSiteString += ", ";
			}
		}

		vaccinationSiteString += ">";

		return vaccinationSiteString;
	}

	// --------------- MUTATORS ---------------

	/** Add a vaccine distribution to the vaccination site */
	public void addDistribution(Vaccine vaccine, int numberOfDoses)
			throws UnrecognizedVaccineCodeNameException, TooMuchDistributionException {
		// If vaccine not recognized by Canada, throw UnrecognizedVaccineCodeNameException
		if (!vaccine.isRecognizedByCanada()) {
			throw new UnrecognizedVaccineCodeNameException();
		}

		// If adding a vaccine with the specified number of doses causes the total number
		// of vaccines in the vaccination site to surpass the maximum number of vaccines,
		// then throw TooMuchDistributionException
		if (this.getNumberOfAvailableDoses() + numberOfDoses > this.maximumNumberOfVaccines) {
			throw new TooMuchDistributionException();
		}

		int index = getIndexOfDistributionByCodeName(vaccine.getCodeName());

		if (index == -1) {
			// If specified vaccine is not already in vaccination site, add a new
			// distribution for that vaccine
			this.vaccineDistributions[this.numberOfDistributions] = new VaccineDistribution(vaccine, numberOfDoses);
			this.numberOfDistributions++;
		} else {
			// If specified vaccine already in vaccination site, add the doses to its distribution
			this.vaccineDistributions[index].addDoses(numberOfDoses);
		}
	}

	/** Book an appointment with the vaccination site */
	public void bookAppointment(HealthRecord healthRecord) throws InsufficientVaccineDosesException {
		// Check if there are enough available doses for adding another appointment
		if (this.numberOfAppointments < this.getNumberOfAvailableDoses()) {
			// If there are enough available doses, then add the health record
			// to the array of appointments and set health record appointment status
			// to a success status message
			this.appointments[this.numberOfAppointments] = healthRecord;
			this.numberOfAppointments++;

			healthRecord.setAppointmentStatus(
				"Last vaccination appointment for " + healthRecord.getName()
				+ " with " + this.name + " succeeded"
			);
		} else {
			// If there is not enough available doses for the number of appointments,
			// then do not add the appointment.
			// Instead set health record appointment status to a failed status message
			// and throw InsufficientVaccineDosesException.
			healthRecord.setAppointmentStatus(
				"Last vaccination appointment for " + healthRecord.getName()
				+ " with " + this.name + " failed"
			);
			throw new InsufficientVaccineDosesException();
		}
	}

	/** Administer doses for all the booked appointments on the given date */
	public void administer(String date) {
		// The vaccine distribution at this index will be used first. If it runs out,
		// the index will be incremented to start using the next distribution in the
		// array of vaccine distributions.
		int vaccineIndex = 0;

		for (int i = 0; i < this.numberOfAppointments; i++) {
			// If all the vaccine doses for the vaccine at the current index have been
			// administered, then increment the index (start using the next vaccine)
			if (this.vaccineDistributions[vaccineIndex].getNumberOfDoses() == 0) {
				vaccineIndex++;
			}

			// Add record the the health record
			this.appointments[i].addRecord(this.vaccineDistributions[vaccineIndex].getVaccine(), this.name, date);
			// Remove dose from the distribution (because it has been administered)
			this.vaccineDistributions[vaccineIndex].removeDose();
			// Set the appointment to null (going through the all the appointments sets all the appointments to null)
			this.appointments[i] = null;
		}

		// Since all the appointments have been completed, set the number of appointments to 0
		this.numberOfAppointments = 0;
	}

}
