package model;

/**
 * Template of a collection of entries
 */
public class RefurbishedStore {

	// Array of entry object references
	private Entry[] entries;

	// Number Of Entries
	// Records number of non-null entries stored in the entries array
	// Also indicated which index to store an entry when it is being added to the entries array
	private int noe;

	// Maximum number of entries in the refurbished store
	private final int MAX_CAPACITY = 5;

	/* CONSTRUCTORS */

	public RefurbishedStore() {
		this.entries = new Entry[this.MAX_CAPACITY];
		this.noe = 0;
	}

	/* ACCESSORS */

	public int getNumberOfEntries() {
		return this.noe;
	}

	/**
	 * Returns the entire entries array
	 */
	public Entry[] getPrivateEntriesArray() {
		return this.entries;
	}

	/**
	 * Returns the array of entries that are not null
	 */
	public Entry[] getEntries() {
		// this.entries has a length of 5
		// es has a length of this.noe
		// so if there are 3 non-null elements in this.entries, es will have 3 elements
		Entry[] es = new Entry[this.noe];

		for (int i = 0; i < noe; i++) {
			es[i] = this.entries[i];
		}

		return es;
	}

	/**
	 * Get product by its associated Entry's serial number
	 */
	public Product getProduct(String sn) {
		for (int i = 0; i < this.noe; i++) {
			if (this.entries[i].getSerialNumber().equals(sn)) {
				return this.entries[i].getProduct();
			}
		}
		return null;
	}

	/**
	 * Return the serial number of all products that have their finish as "Space Grey"
	 * or is a Pro
	 */
	public String[] getSpaceGreyOrPro() {
		// Number of Products that are Pro or have have Space Grey finish
		int count = 0;
		// This array holds the indices of the entries in this.entries that are
		// Pro or have Spacec Grey finish
		int[] indices = new int[this.noe];

		// Get the indices of the entries in this.entries that are Pro or have
		// finish Space Grey and store them in the indices array.
		// Also have to make sure the Product's finish is defined (or else null pointer exception)
		Product p;
		for (int i = 0; i < this.noe; i++) {
			p = this.entries[i].getProduct();
			if (
				p.getModel().contains("Pro")
				||
				p.getFinish() != null
				&&
				p.getFinish().equals("Space Grey")
			) {
				indices[count] = i;
				count++;
			}
		}

		// Using the indices array, get the serial numbers of the entries that are
		// Pro or Space Grey and store them in a new array
		String[] sns = new String[count];
		for (int i = 0; i < count; i++) {
			sns[i] = this.entries[indices[i]].getSerialNumber();
		}

		return sns;
	}

	/**
	 * Return the serial number of all products that have their finish as "Space Grey"
	 * and is a Pro
	 */
	public String[] getSpaceGreyPro() {
		// Number of Products that are Pro and have have Space Grey finish
		int count = 0;
		// This array holds the indices of the entries in this.entries that are
		// Pro and have Spacec Grey finish
		int[] indices = new int[this.noe];

		// Get the indices of the entries in this.entries that are Pro and have
		// finish Space Grey and store them in the indices array.
		// Also have to make sure the Product's finish is defined (or else null pointer exception)
		Product p;
		for (int i = 0; i < this.noe; i++) {
			p = this.entries[i].getProduct();
			if (
				p.getModel().contains("Pro")
				&&
				p.getFinish() != null
				&&
				p.getFinish().equals("Space Grey")
			) {
				indices[count] = i;
				count++;
			}
		}

		// Using the indices array, get the serial numbers of the entries that are
		// Pro and Space Grey and store them in a new array
		String[] sns = new String[count];
		for (int i = 0; i < count; i++) {
			sns[i] = this.entries[indices[i]].getSerialNumber();
		}

		return sns;
	}

	/* MUTATORS */

	/**
	 * Add entry with an already instantiated Entry
	 */
	public void addEntry(Entry e) {
		this.entries[noe] = e;
		this.noe++;
	}

	/**
	 * Add entry with a serial number and an already instantiated Product
	 */
	public void addEntry(String sn, Product p) {
		this.addEntry(new Entry(sn, p));
	}

	/**
	 * Add entry with a serial number, model name, and original price
	 */
	public void addEntry(String sn, String model, double originalPrice) {
		this.addEntry(new Entry(sn, new Product(model, originalPrice)));
	}

}
