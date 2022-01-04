package model;

public class FilterAll extends SeqEvaluator {

	public FilterAll(int maximumNumberOfOperations) {
		super(maximumNumberOfOperations);
	}

	/**
	 * Add a sequence operation to the sequence concatenation evaluator.
	 * The operations "op:projection", "op:occursWithin", and "op:sumsOfPrefixes"
	 * can all be added to the evaluator.
	 * Any other operation will cause IllegalOperationException to be thrown.
	 * Out of the three allowable operations, only "op:occursWithin"
	 * is considered valid.
	 */
	public void addOperation(String operation, int[] sequence1, int[] sequence2) throws IllegalOperationException {

		if (operation.equals("op:projection")) {
			// Projection does not return a boolean, so it is an invalid operation
			this.numberOfInvalidOperations++;
		} else if (operation.equals("op:occursWithin")) {
			this.operations[this.numberOfOperations] = new OccursWithin(sequence1, sequence2);
		} else if (operation.equals("op:sumsOfPrefixes")) {
			// Sums of prefixes does not return a boolean, so it is an invalid operation
			this.numberOfInvalidOperations++;
		} else {
			throw new IllegalOperationException();
		}

		this.numberOfOperations++;
	}

	public String toString() {
		// If there are invalid operations, then the filtering cannot be evaluated
		if (this.numberOfInvalidOperations > 0) {
			return "Filter cannot be evaluated due to " + this.numberOfInvalidOperations + " incompatile operations.";
		}

		String filterAllString = "Filter result is: ";

		for (int i = 0; i < this.numberOfOperations; i++) {
			// Get the output of the current operation as a string
			filterAllString += this.operations[i].getOutputString();

			if (i < this.numberOfOperations - 1) {
				filterAllString += ", ";
			}
		}

		return filterAllString;
	}

}
