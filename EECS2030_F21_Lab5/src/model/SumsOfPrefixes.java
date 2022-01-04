package model;

public class SumsOfPrefixes extends SeqOperation{

	private int[] sequence;
	private int[] sumsOfPrefixes;

	public SumsOfPrefixes(int[] sequence) {
		this.sequence = sequence;

		this.sumsOfPrefixes = this.sumsOfPrefixes(sequence);
	}

	/** Get the sums of prefixes of the specified sequence */
	public int[] sumsOfPrefixes(int[] sequence) {
		// If an empty sequence was provided, then the sums of prefixes is also an empty sequence
		if (sequence.length == 0) {
			return new int[0];
		}

		int[] sumsOfPrefixes = new int[sequence.length + 1];
		// First element of sums of prefixes is 0
		sumsOfPrefixes[0] = 0;

		for (int i = 1; i < sumsOfPrefixes.length; i++) {

			// The current element for each sequence is the sum of the prefixes
			// from all the elements before it
			for (int j = 0; j < i; j++) {
				sumsOfPrefixes[i] += sequence[j];
			}

		}

		return sumsOfPrefixes;
	}

	/** Get the sums of prefixes sequence as an integer array */
	public int[] getOutputSequence() {
		return this.sumsOfPrefixes;
	}

	/** Get the sums of prefixes sequence as a string */
	public String getOutputString() {
		return this.getSequenceString(this.sumsOfPrefixes);
	}

	public String toString() {
		return "Sums of prefixes of " + this.getSequenceString(this.sequence)
			+ " is: " + this.getSequenceString(this.sumsOfPrefixes);
	}

}
