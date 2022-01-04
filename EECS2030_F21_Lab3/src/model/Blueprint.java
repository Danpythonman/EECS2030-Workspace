package model;

public class Blueprint {

	private Floor[] floors;
	private int maximumNumberOfFloors;
	private int numberOfFloors;

	// --------------- CONSTRUCTORS ---------------
	// 2 CONSTRUCTORS

	public Blueprint(int maximumNumberOfFloors) {
		this.floors = new Floor[maximumNumberOfFloors];
		this.maximumNumberOfFloors = maximumNumberOfFloors;
		this.numberOfFloors = 0;
	}

	public Blueprint(Blueprint blueprint) {
		this.maximumNumberOfFloors = blueprint.maximumNumberOfFloors;
		this.floors = new Floor[blueprint.maximumNumberOfFloors];

		for (int i = 0; i < blueprint.numberOfFloors; i++) {
			this.floors[i] = new Floor(blueprint.floors[i]);
		}

		this.numberOfFloors = blueprint.numberOfFloors;
	}

	// --------------- ACCESSORS ---------------

	/** Returns array of floors in the blueprint */
	public Floor[] getFloors() {
		Floor[] nonNullFloors = new Floor[this.numberOfFloors];

		for (int i = 0; i < this.numberOfFloors; i++) {
			nonNullFloors[i] = this.floors[i];
		}

		return nonNullFloors;
	}

	public String toString() {
		return String.format("%.1f", (100.0 * this.numberOfFloors) / this.maximumNumberOfFloors)
			+ " percents of building blueprint completed (" + this.numberOfFloors
			+ " out of " + this.maximumNumberOfFloors + " floors)";
	}

	// --------------- MUTATORS ---------------

	/** Add a floor plan to the blueprint */
	public void addFloorPlan(Floor floor) {
		this.floors[this.numberOfFloors] = new Floor(floor);
		this.numberOfFloors++;
	}

}
