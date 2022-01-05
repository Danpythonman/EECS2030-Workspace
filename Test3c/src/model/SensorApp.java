package model;

public class SensorApp extends WeatherApp {

	// --------------- CONSTRUCTORS ---------------

	public SensorApp(String name, int MAXIMUM_NUMBER_OF_CONNECTED_STATIONS) {
		super(name, MAXIMUM_NUMBER_OF_CONNECTED_STATIONS);
	}

	// --------------- ACCESSORS ---------------

	public String[] getConnectedForcastersOf(String stationName) {
		WeatherStation station = null;

		for (int i = 0; i < this.numberOfConnectedStations && station == null; i++) {
			if (this.connectedStations[i].getName().equals(stationName)) {
				station = this.connectedStations[i];
			}
		}

		String[] connectedForecasters = null;

		if (station != null) {
			connectedForecasters = station.getConnectedForcasters();
		} else {
			connectedForecasters = new String[0];
		}

		return connectedForecasters;
	}

	public String toString() {
		String sensorAppString = "Weather Sensor App " + this.name;

		if (this.numberOfConnectedStations == 0) {
			sensorAppString += " is connected to no stations.";
		} else {
			sensorAppString += " is connected to " + this.numberOfConnectedStations + " stations: <";

			for (int i = 0; i < this.numberOfConnectedStations; i++) {
				sensorAppString += this.connectedStations[i].getName();

				if (i < this.numberOfConnectedStations - 1) {
					sensorAppString += ", ";
				}
			}

			sensorAppString += ">.";
		}

		return sensorAppString;
	}

	// --------------- MUTATORS ---------------

	public void updateMeasurements(String stationName, int temperature, int pressure) {
		WeatherStation station = null;

		for (int i = 0; i < this.numberOfConnectedStations; i++) {
			if (this.connectedStations[i].getName().equals(stationName)) {
				station = this.connectedStations[i];
			}
		}

		if (station != null) {
			station.updateMeasurements(temperature, pressure);
		}
	}

}
