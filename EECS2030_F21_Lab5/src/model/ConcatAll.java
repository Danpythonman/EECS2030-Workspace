package model;

public class ConcatAll extends SeqEvaluator {

	public ConcatAll(int maximumNumberOfOperations) {
		super(maximumNumberOfOperations);
	}

	/**
	 * Add a sequence operation to the sequence concatenation evaluator.
	 * The operations "op:projection", "op:occursWithin", and "op:sumsOfPrefixes"
	 * can all be added to the evaluator.
	 * Any other operation will cause IllegalOperationException to be thrown.
	 * Out of the three allowable operations, only "op:projection" and "op:sumsOfPrefixes"
	 * are considered valid.
	 */
	public void addOperation(String operation, int[] sequence1, int[] sequence2) throws IllegalOperationException {

		if (operation.equals("op:projection")) {
			this.operations[this.numberOfOperations] = new Projection(sequence1, sequence2);
		} else if (operation.equals("op:occursWithin")) {
			// Occurs within does not return a sequence, so it is an invalid operation
			this.numberOfInvalidOperations++;
		} else if (operation.equals("op:sumsOfPrefixes")) {
			this.operations[this.numberOfOperations] = new SumsOfPrefixes(sequence1);
		} else {
			throw new IllegalOperationException();
		}

		this.numberOfOperations++;
	}

	/**
	 * Concatenate all the sequences in the evaluator.
	 * This should only be called if there are no invalid operations.
	 */
	private int[] concatAll() {
		int lengthOfConcatSequence = 0;
		SeqOperation currentOperation = null;

		// Calculate the length of the concatenated sequence
		for (int i = 0; i < this.numberOfOperations; i++) {

			currentOperation = this.operations[i];

			if (currentOperation instanceof Projection) {
				lengthOfConcatSequence += ((Projection) currentOperation).getOutputSequence().length;
			} else if (currentOperation instanceof SumsOfPrefixes) {
				lengthOfConcatSequence += ((SumsOfPrefixes) currentOperation).getOutputSequence().length;
			}
		}

		int[] concatSequence = new int[lengthOfConcatSequence];
		int numberOfNumbersAdded = 0;
		int[] currentSequence = null;
		currentOperation = null;

		// Iterate through the operations (sequences outputted by operations)
		for (int i = 0; i < this.numberOfOperations; i++) {

			currentOperation = this.operations[i];

			// Get the current output sequence
			if (currentOperation instanceof Projection) {
				currentSequence = ((Projection) currentOperation).getOutputSequence();
			} else if (currentOperation instanceof SumsOfPrefixes) {
				currentSequence = ((SumsOfPrefixes) currentOperation).getOutputSequence();
			}

			// Add all the elements from the current output sequence into the concatenated sequence
			for (int j = 0; j < currentSequence.length; j++) {
				concatSequence[numberOfNumbersAdded] = currentSequence[j];
				numberOfNumbersAdded++;
			}
		}

		return concatSequence;
	}

	public String toString() {
		// If there are invalid operations, then the concatenation cannot be evaluated
		if (this.numberOfInvalidOperations > 0) {
			return "Concat cannot be evaluated due to " + this.numberOfInvalidOperations + " incompatile operations.";
		}

		String concatAllString = "Concat(";

		// Add each input sequence to the output string
		for (int i = 0; i < this.numberOfOperations; i++) {
			// Get the operation sequence as a string
			concatAllString += this.operations[i].getOutputString();

			if (i < this.numberOfOperations - 1) {
				concatAllString += ", ";
			}
		}

		concatAllString += ") = [";

		int[] concatAllSequence = this.concatAll();

		// Add the elements of the concatenated sequence to the output string
		for (int i = 0; i < concatAllSequence.length; i++) {
			concatAllString += concatAllSequence[i];

			if (i < concatAllSequence.length - 1) {
				concatAllString += ", ";
			}
		}

		concatAllString += "]";

		return concatAllString;
	}

}
