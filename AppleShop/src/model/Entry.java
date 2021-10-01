package model;

/**
 * Template of a unit of storage in the Apple refurbished shop.
 * The Apple refurbished shop is a collection of entries.
 */
public class Entry {

	private String serialNumber; // Each product has a unique serial number
	private Product product;

	public Entry(String serialNumber, Product product) {
		this.serialNumber = serialNumber;
		this.product = product;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * An overloaded version of the setProduct mutator.
	 * This version does not expect the user to create a Product and pass it in as an argument
	 * Instead, the user passes in a model (String) and originalPrice (double).
	 * Internally, a local new Product object is created.
	 */
	public void setProduct(String model, double originalPrice) {
		this.product = new Product(model, originalPrice);
	}

	public String toString() {
		return "[" + this.serialNumber + "] " + this.product.toString();
	}

}
