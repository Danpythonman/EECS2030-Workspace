package model;

public class Vaccine {

	private String codeName;
	private String vaccineType;
	private String manufacturer;
	private boolean recognizedByCanada;

	// --------------- CONSTRUCTORS ---------------

	public Vaccine(String codeName, String vaccineType, String manufacturer) {
		this.codeName = codeName;
		this.vaccineType = vaccineType;
		this.manufacturer = manufacturer;

		// The vaccines that are recognized by Canada are (by code name):
		// "mRNA-1273", "BNT162b2", "Ad26.COV2.S", and "AZD1222"
		if (
			codeName.equals("mRNA-1273")
			|| codeName.equals("BNT162b2")
			|| codeName.equals("Ad26.COV2.S")
			|| codeName.equals("AZD1222")
		) {
			this.recognizedByCanada = true;
		} else {
			this.recognizedByCanada = false;
		}
	}

	// --------------- ACCESSORS ---------------

	/** Returns the code name of the vaccine */
	public String getCodeName() {
		return this.codeName;
	}

	/** Returns the vaccine type of the vaccine */
	public String getVaccineType() {
		return this.vaccineType;
	}

	/** Returns the manufacturer of the vaccine */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/** Returns true if the vaccine is recognized by Canada, returns false otherwise */
	public boolean isRecognizedByCanada() {
		return this.recognizedByCanada;
	}

	public String toString() {
		return (this.recognizedByCanada)
			? "Recognized vaccine: " + this.codeName + " (" + this.vaccineType + "; " + this.manufacturer + ")"
			: "Unrecognized vaccine: " + this.codeName + " (" + this.vaccineType + "; " + this.manufacturer + ")";
	}

}
