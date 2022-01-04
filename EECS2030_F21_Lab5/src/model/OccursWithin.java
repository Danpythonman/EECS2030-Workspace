package model;

public class OccursWithin extends BinarySeqOperation {

	private boolean occursWithin;

	public OccursWithin(int[] sequence1, int[] sequence2) {
		super(sequence1, sequence2);

		this.occursWithin = this.occursWithin(sequence1, sequence2);
	}

	/** Return a boolean that is true if the first sequence occurs within the second sequence */
	public boolean occursWithin(int[] sequence1, int[] sequence2) {
		boolean occursWithin = false;

		// Iterate through some of the elements in the second sequence
		// (just enough elements to account for the size of the first sequence,
		// which may occur in the second sequence)
		for (int i = 0; i < sequence2.length - sequence1.length + 1 && !occursWithin; i++) {

			occursWithin = true;

			// Compare the first sequence to the sub-sequence of sequence2 starting at index i
			for (int j = 0; j < sequence1.length; j++) {
				// If two elements not equal, then this sub-sequence cannot be the first sequence,
				// so we can stop this inner loop
				if (sequence1[j] != sequence2[i + j]) {
					occursWithin = false;
					break;
				}
			}

		}

		return occursWithin;
	}

	/**
	 * Return the string "true" if the first sequence occurs in the second sequence,
	 * returns the string "_" otherwise
	 */
	public String getOutputString() {
		return this.occursWithin ? "true" : "_";
	}

	public String toString() {
		return this.getSequenceString(sequence1)
			+ (this.occursWithin ? " occurs within " : " does not occur within ")
			+ this.getSequenceString(sequence2);
	}

}
