package model;

public class Unit {

	private String function;
	private int width;
	private int length;
	private char measurement; // measurement will either be 'f' for feet or 'm' for meters
	private final double footToMeterFactor = 0.3048;

	// --------------- CONSTRUCTORS ---------------
	// 2 CONSTRUCTORS

	public Unit(String function, int width, int length) {
		this.function = new String(function);
		this.width = width;
		this.length = length;

		this.measurement = 'f';
	}

	public Unit(Unit unit) {
		this.function = new String(unit.function);
		this.width = unit.width;
		this.length = unit.length;

		this.measurement = 'f';
	}

	// --------------- ACCESSORS ---------------

	/** Return the function of the unit */
	public String getFunction() {
		return this.function;
	}

	/** Return the width of the unit (in ft.) */
	public int getWidth() {
		return this.width;
	}

	/** Return the length of the unit (in ft.) */
	public int getLength() {
		return this.length;
	}

	/** Return the area of the unit (in sq. ft.) */
	public int getArea() {
		return this.width * this.length;
	}

	public String toString() {
		// Meaurement in feet
		if (this.measurement == 'f') {
			return "A unit of " + this.width * this.length + " square feet ("
				+ this.width + "' wide and " + this.length + "' long) functioning as " + this.function;
		// Measurement in meters
		} else {
			double widthInMeters = this.width * this.footToMeterFactor;
			double lengthInMeters = this.length * this.footToMeterFactor;

			return "A unit of " + String.format("%.2f", widthInMeters * lengthInMeters)
				+ " square meters (" + String.format("%.2f", widthInMeters) + " m wide and "
				+ String.format("%.2f", lengthInMeters) + " m long) functioning as " + this.function;
		}
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Unit otherUnit = (Unit) obj;
		return this.function.equals(otherUnit.function)
			&& this.width * this.length == otherUnit.width * otherUnit.length;
	}

	// --------------- MUTATORS ---------------

	/**
	 * Change the units of measurement (toggles between feet and meters).
	 * When the toString method is called, the measurements will be reported in the specified units.
	 */
	public void toogleMeasurement() {
		this.measurement = this.measurement == 'f' ? 'm' : 'f';
	}

}
