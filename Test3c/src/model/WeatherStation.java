package model;

public class WeatherStation {

	private String name;
	private WeatherApp[] connectedApps;
	private int numberOfConnectedApps;
	private int MAXIMUM_NUMBER_OF_CONNECTED_APPS;
	private int[] temperatures;
	private int numberOfTemperatures;
	private int maxTemperature;
	private int previousPressure;
	private int currentPressure;

	// --------------- CONSTRUCTORS ---------------

	public WeatherStation(String name,int MAXIMUM_NUMBER_OF_CONNECTED_APPS) {
		this.name = name;
		this.MAXIMUM_NUMBER_OF_CONNECTED_APPS = MAXIMUM_NUMBER_OF_CONNECTED_APPS;

		this.connectedApps = new WeatherApp[this.MAXIMUM_NUMBER_OF_CONNECTED_APPS];
		this.numberOfConnectedApps = 0;

		this.temperatures = new int[100];
		this.numberOfTemperatures = 0;
		this.maxTemperature = 0;
		this.previousPressure = 0;
		this.currentPressure = 0;
	}

	// --------------- ACCESSORS ---------------

	public String getName() {
		return this.name;
	}

	public int getNumberOfMeasurements() {
		return this.numberOfTemperatures;
	}

	public double getAverageTemperature() {
		int temperatureSum = 0;

		for (int i = 0; i < this.numberOfTemperatures; i++) {
			temperatureSum += this.temperatures[i];
		}

		return ((double) temperatureSum) / this.numberOfTemperatures;
	}

	public int getMaximumTemperature() {
		return this.maxTemperature;
	}

	public boolean pressureDecrease() {
		return this.currentPressure < this.previousPressure;
	}

	public SensorApp[] getSensors() {
		int numberOfConnectedSensors = 0;

		for (int i = 0; i < this.numberOfConnectedApps; i++) {
			if (this.connectedApps[i] instanceof SensorApp) {
				numberOfConnectedSensors++;
			}
		}

		SensorApp[] connectedSensors = new SensorApp[numberOfConnectedSensors];
		int numberOfAddedSensors = 0;

		for (int i = 0; i < this.numberOfConnectedApps; i++) {
			if (this.connectedApps[i] instanceof SensorApp) {
				connectedSensors[numberOfAddedSensors] = (SensorApp) this.connectedApps[i];
			}
		}

		return connectedSensors;
	}

	public String[] getConnectedForcasters() {
		int numberOfConnectedForecasters = 0;

		for (int i = 0; i < this.numberOfConnectedApps; i++) {
			if (this.connectedApps[i] instanceof ForecastApp) {
				numberOfConnectedForecasters++;
			}
		}

		String[] connectedForecasters= new String[numberOfConnectedForecasters];
		int numberOfAddedForecasters = 0;

		for (int i = 0; i < numberOfConnectedApps; i++) {
			if (this.connectedApps[i] instanceof ForecastApp) {
				connectedForecasters[numberOfAddedForecasters] = this.connectedApps[i].getName()
					+ " at index " + i;
				numberOfAddedForecasters++;
			}
		}

		return connectedForecasters;
	}

	public String toString() {
		String weatherStationString = this.name;

		if (this.numberOfConnectedApps == 0) {
			weatherStationString += " has no connected apps.";
		} else {
			weatherStationString += " is connected by " + this.numberOfConnectedApps + " apps: <";

			for (int i = 0; i < this.numberOfConnectedApps; i++) {
				weatherStationString += (this.connectedApps[i] instanceof SensorApp)
					? "Weather Sensor App "
					: "Weather Forecast App ";

				weatherStationString += this.connectedApps[i].getName();

				if (i < this.numberOfConnectedApps - 1) {
					weatherStationString += ", ";
				}
			}
			weatherStationString += ">.";
		}

		return weatherStationString;
	}

	// --------------- MUTATORS ---------------

	public void connect(WeatherApp weatherApp) {
		this.connectedApps[this.numberOfConnectedApps] = weatherApp;
		this.numberOfConnectedApps++;

		weatherApp.connect(this);
	}

	public void updateMeasurements(int temperature, int pressure) {
		this.temperatures[this.numberOfTemperatures] = temperature;
		this.numberOfTemperatures++;

		if (temperature > this.maxTemperature) {
			this.maxTemperature = temperature;
		}

		this.previousPressure = currentPressure;
		this.currentPressure = pressure;

		for (int i = 0; i < this.numberOfConnectedApps; i++) {
			if (this.connectedApps[i] instanceof ForecastApp) {

			}
		}
	}

}
