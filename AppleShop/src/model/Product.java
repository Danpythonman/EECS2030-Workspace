package model;

/*
 * Template for individual refurbished Apple product
 */
public class Product {
	/* Attributes */
	private String model; // Name of the model (ex. iPad Pro 12.9)
	private String finish; // Colour (ex. Silver)
	private int storage; // Storage space in GB
	private boolean hasCellularConnectivity; // true if has wifi and cellular, false if only wifi
	private double originalPrice; // Original price of the model in dollars
	private double discountValue; // Amount that is discounted in dollars

	/* Constructors */

	// More than one constructor with distinct parameters
	// This is called overloading (multiple methods with the same name, but different parameters)

	// Tihs constructor initializes all attributes to default values
	public Product() {}

	// This constructor initalizes model and original price
	public Product(String model, double originalPrice) {
		this.model = model;
		this.originalPrice = originalPrice;
	}

	/* Accessors */

	public String getModel() {
		return this.model;
	}

	public String getFinish() {
		return this.finish;
	}

	public int getStorage() {
		return this.storage;
	}

	public boolean hasCellularConnectivity() {
		return this.hasCellularConnectivity;
	}

	public double getOriginalPrice() {
		return this.originalPrice;
	}

	public double getDiscountValue() {
		return this.discountValue;
	}

	public double getPrice() {
		// Calculates the discounted price of the model
		return this.originalPrice - this.discountValue;
	}

	public String toString() {
		// Creates a description of the product
		return this.model + " "
				+ this.finish + " "
				+ storage + "GB "
				+ "(cellular connectivity: " + this.hasCellularConnectivity
				+ "): $(" + String.format("%.2f", this.originalPrice)
				+ " - " + String.format("%.2f", this.discountValue) + ")";
	}

	/* Mutators */

	public void setModel(String model) {
		this.model = model;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public void setHasCellularConnectivity(boolean hasCellularConnectivity) {
		this.hasCellularConnectivity = hasCellularConnectivity;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

}
