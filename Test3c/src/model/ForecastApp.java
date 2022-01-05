package model;

public class ForecastApp extends WeatherApp {

	private int[] avgTemperatures;
	private int[] numberOfTemperatures;
	private int[] maxTemperatures;
	private int[] previousPressures;
	private int[] currentPressures;

	// --------------- CONSTRUCTORS ---------------

	public ForecastApp(String name, int MAXIMUM_NUMBER_OF_CONNECTED_STATIONS) {
		super(name, MAXIMUM_NUMBER_OF_CONNECTED_STATIONS);

		this.avgTemperatures = new int[MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
		this.numberOfTemperatures = new int[MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
		this.maxTemperatures = new int[MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
		this.previousPressures = new int[MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
		this.currentPressures = new int[MAXIMUM_NUMBER_OF_CONNECTED_STATIONS];
	}

	// --------------- ACCESSORS ---------------

	public String toString() {
		String forecastAppString = "Weather Forecast App " + this.name;

		WeatherStation station;

		if (this.numberOfConnectedStations == 0) {
			forecastAppString += " is connected to no stations.";
		} else {
			forecastAppString += " is connected to " + this.numberOfConnectedStations + " stations: <";

			for (int i = 0; i < this.numberOfConnectedStations; i++) {
				station = this.connectedStations[i];
				forecastAppString += station.getName();

				if (station.getNumberOfMeasurements() == 1) {
					forecastAppString += " {"
						+ "max temperature: " + station.getMaximumTemperature()
						+ ", avg temperature: " + String.format("%.1f", station.getAverageTemperature())
						+ ", unlikely to rain"
						+ "}";
				} else if (station.getNumberOfMeasurements() > 1) {
					forecastAppString += " {"
						+ "max temperature: " + station.getMaximumTemperature()
						+ ", avg temperature: " + String.format("%.1f", station.getAverageTemperature())
						+ (station.pressureDecrease() ? ", likely to rain" : ", unlikely to rain")
						+ "}";
				}

				if (i < this.numberOfConnectedStations - 1) {
					forecastAppString += ", ";
				}
			}

			forecastAppString += ">.";
		}

		return forecastAppString;
	}

	// --------------- MUTATORS ---------------

	public void updateStation(WeatherStation station) {
		int index = -1;
		for (int i = 0; i < this.numberOfConnectedStations && index == -1; i++) {
			if (this.connectedStations[i] == station) {
				index = i;
			}
		}

		if (index > -1) {

		}
	}

}
