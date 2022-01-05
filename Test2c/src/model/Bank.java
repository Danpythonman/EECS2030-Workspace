package model;

public class Bank {

	private Account[] accounts;
	private int numberOfAccounts;
	private final int MAXIMUM_NUMBER_OF_ACCOUNTS = 5;

	// --------------- CONSTRUCTORS ---------------

	/** Create an empty Bank object */
	public Bank() {
		this.accounts = new Account[this.MAXIMUM_NUMBER_OF_ACCOUNTS];
		this.numberOfAccounts = 0;
	}

	/** Create a Bank object from another Bank object */
	public Bank(Bank bank) {
		this.accounts = new Account[this.MAXIMUM_NUMBER_OF_ACCOUNTS];

		for (int i = 0; i < bank.numberOfAccounts; i++) {
			this.accounts[i] = bank.accounts[i];
		}

		this.numberOfAccounts = bank.numberOfAccounts;
	}

	// --------------- ACCESSORS ---------------

	/** Returns the number of accounts in the bank */
	public int getNumberOfAccounts() {
		return this.numberOfAccounts;
	}

	/**
	 * Returns an array containing the accounts in the bank.
	 * The accounts in the array are referencing the accounts in the bank's array of accounts.
	 */
	public Account[] getReferencesOfAccounts() {
		Account[] nonNullAccounts= new Account[this.numberOfAccounts];

		for (int i = 0; i < this.numberOfAccounts; i++) {
			nonNullAccounts[i] = this.accounts[i];
		}

		return nonNullAccounts;
	}

	/**
	 * Returns an array containing copies of the accounts in the bank.
	 * The accounts in the array are not referencing the accounts in the bank's array of accounts.
	 */
	public Account[] getCopiesOfAccounts() {
		Account[] nonNullAccountCopies = new Account[this.numberOfAccounts];

		for (int i = 0; i < this.numberOfAccounts; i++) {
			nonNullAccountCopies[i] = new Account(this.accounts[i]);
		}

		return nonNullAccountCopies;
	}

	/**
	 * Check if two Bank objects are equal.
	 * Two banks are equal if they are referencing the same object or
	 * if they contain the same number of accounts and the accounts at corresponding
	 * positions in the banks' arrays of accounts are equal.
	 */
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
		Bank otherBank = (Bank) obj;

		if (this.numberOfAccounts == otherBank.numberOfAccounts) {

			boolean equalAccounts = true;

			for (int i = 0; i < this.numberOfAccounts && equalAccounts; i++) {
				if (!this.accounts[i].equals(otherBank.accounts[i])) {
					equalAccounts = false;
				}
			}

			return equalAccounts;
		}
		return false;
	}

	// --------------- MUTATORS ---------------

	/** Add an account to the bank */
	public void addAccount(Account account) {
		this.accounts[this.numberOfAccounts] = account;
		this.numberOfAccounts++;
	}

	/** Add multiple accounts to the bank */
	public void addAccounts(Account[] accounts) {
		for (int i = 0; i < accounts.length; i++) {
			this.accounts[this.numberOfAccounts] = accounts[i];
			this.numberOfAccounts++;
		}
	}

}
