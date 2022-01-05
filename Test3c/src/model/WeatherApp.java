package model;

public class WeatherApp {

	protected String name;
	protected WeatherStation[] connectedStations;
	protected int numberOfConnectedStations;
	protected int MAXIMUM_NUMBER_OF_CONNECTED_STATIONS;

	// --------------- CONSTRUCTORS ---------------

	public WeatherApp(String name, int MAXIMUM_NUMBER_OF_CONNECTED_STATIONS) {
		this.name = name;
		this.MAXIMUM_NUMBER_OF_CONNECTED_STATIONS = MAXIMUM_NUMBER_OF_CONNECTED_STATIONS;

		this.connectedStations = new WeatherStation[this.MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
		this.numberOfConnectedStations = 0;
	}

	// --------------- ACCESSORS ---------------

	public String getName() {
		return this.name;
	}

	// --------------- MUTATORS ---------------

	public void connect(WeatherStation weatherStation) {
		this.connectedStations[this.numberOfConnectedStations] = weatherStation;
		this.numberOfConnectedStations++;
	}

}
