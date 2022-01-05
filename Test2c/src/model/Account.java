package model;

public class Account {

	private String name;
	private int balance;
	private char type; // Either 'r' for regular or 'v' for VIP
	private int vipBalance;

	// --------------- CONSTRUCTORS ---------------

	/** Create an Account object from a name and balance */
	public Account(String name, int balance) {
		this.balance = balance;
		this.name = name;

		this.type = 'r';
		this.vipBalance = 0;
	}

	/** Create an Account object from another Account object */
	public Account(Account account) {
		this.balance = account.balance;
		this.name = new String(account.name);

		this.type = account.type;
		this.vipBalance = account.vipBalance;
	}

	// --------------- ACCESSORS ---------------

	/**
	 * Return a string containing information about the account
	 * (name, balance, type ('regular' or 'VIP') and, if VIP, VIP deposit balance)
	 */
	public String toString() {
		return "A " + (this.type == 'r' ? "regular" : "VIP")
				+ " account owned by " + this.name + " with balance $" + this.balance
				+ (this.type == 'r' ? "" : " ($" + this.vipBalance + " deposited for maintaining the VIP stauts)");
	}

	/**
	 * Check if two Account objects are equal.
	 * Two accounts are equal if they are referencing the same object or if their names, balances, and types are equal.
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
		Account otherAccount = (Account) obj;

		return this.name.equals(otherAccount.name)
				&& this.balance == otherAccount.balance
				&& this.type == otherAccount.type;
	}

	// --------------- MUTATORS ---------------

	/**
	 * Switch an account from regular to VIP, depositing an amount.
	 * If the account is already VIP, then an InvalidStatusToSwitchException is thrown.
	 * If the account's balance less than the deposit amount, then an InsufficientBalanceException is thrown.
	 * If the account is already VIP and the account's balance less than the deposit amount,
	 * then an InvalidStatusToSwitchException is thrown.
	 */
	public void switchToVIP(int deposit) throws InvalidStatusToSwitchException, InsufficientBalanceException {
		if (this.type == 'v') {
			throw new InvalidStatusToSwitchException();
		}
		if (this.balance - deposit < 0) {
			throw new InsufficientBalanceException();
		}

		this.type = 'v';
		this.balance -= deposit;
		this.vipBalance += deposit;
	}

	/**
	 * Switch an account from VIP to regular, returning back the depositing VIP amount to the account's balance.
	 * If the account is already regular, then an InvalidStatusToSwitchException is thrown.
	 */
	public void switchToRegular() throws InvalidStatusToSwitchException {
		if (this.type == 'r') {
			throw new InvalidStatusToSwitchException();
		}

		this.type = 'r';
		this.balance += this.vipBalance;
		this.vipBalance = 0;
	}

}
