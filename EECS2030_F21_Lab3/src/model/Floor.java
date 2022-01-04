package model;

public class Floor {

	private int maximumSqFt;
	private Unit[] units;
	private int numberOfUnits;
	private final int maximumNumberOfUnits = 20;

	// --------------- CONSTRUCTORS ---------------
	// 2 CONSTRUCTORS

	public Floor(int maximumSqFt) {
		this.maximumSqFt = maximumSqFt;
		this.units = new Unit[this.maximumNumberOfUnits];
		this.numberOfUnits = 0;
	}

	public Floor(Floor floor) {
		this.maximumSqFt = floor.maximumSqFt;
		this.units = new Unit[this.maximumNumberOfUnits];

		for (int i = 0; i < floor.numberOfUnits; i++) {
			this.units[i] = new Unit(floor.units[i]);
		}

		this.numberOfUnits = floor.numberOfUnits;
	}

	// --------------- HELPER METHODS ---------------

	/** Return the sum of the areas of all the units in the floor */
	private int getAccumulatedSqFt() {
		int accumulatedSqFt = 0;

		for (int i = 0; i < this.numberOfUnits; i++) {
			accumulatedSqFt += this.units[i].getArea();
		}

		return accumulatedSqFt;
	}

	// --------------- ACCESSORS ---------------

	/** Returns array of units in the floor */
	public Unit[] getUnits() {
		Unit[] nonNullUnits = new Unit[this.numberOfUnits];

		for (int i = 0; i < this.numberOfUnits; i++) {
			nonNullUnits[i] = this.units[i];
		}

		return nonNullUnits;
	}

	public String toString() {
		int totalSqFt = getAccumulatedSqFt();
		String floorString = "Floor's utilized space is " + totalSqFt + " sq ft ("
			+ (this.maximumSqFt - totalSqFt) + " sq ft remaining): [";

		for (int i = 0; i < this.numberOfUnits; i++) {
			floorString += this.units[i].getFunction() + ": "
				+ this.units[i].getArea() + " sq ft (" + this.units[i].getWidth()
				+ "' by " + this.units[i].getLength() + "')";

			if (i != this.numberOfUnits - 1) {
				floorString += ", ";
			}
		}

		floorString += "]";

		return floorString;
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
		Floor otherFloor = (Floor) obj;

		if (this.maximumSqFt == otherFloor.maximumSqFt && this.numberOfUnits == otherFloor.numberOfUnits) {
			// Many units in this floor may be equal to one unit in the other floor.
			// When two units are found to be equal we delete (set to null) that unit
			// in the other floor's array of units.
			// We make a copy of the other floor's array of units so that we can
			// delete elements without it affecting the other floor.
			Unit[] otherFloorUnits = new Unit[this.numberOfUnits];
			for (int i = 0; i < this.numberOfUnits; i++) {
				otherFloorUnits[i] = otherFloor.units[i];
			}

			boolean equalUnits = true;
			for (int i = 0; i < this.numberOfUnits && equalUnits == true; i++) {

				equalUnits = false;

				for (int j = 0; j < this.numberOfUnits && equalUnits == false; j++) {
					if (this.units[i].equals(otherFloorUnits[j])) {
						equalUnits = true;
						otherFloorUnits[j] = null;
					}
				}
			}

			return equalUnits;
		}
		return false;
	}

	// --------------- MUTATORS ---------------

	/** Add a unit to the floor */
	public void addUnit(String function, int width, int length) throws InsufficientFloorSpaceException {
		if (getAccumulatedSqFt() + width * length <= this.maximumSqFt) {
			this.units[this.numberOfUnits] = new Unit(function, width, length);
			this.numberOfUnits++;
		} else {
			throw new InsufficientFloorSpaceException();
		}
	}

}
