package model;

public class Projection extends BinarySeqOperation {

	private int[] projection;

	// --------------- CONSTRUCTORS ---------------

	public Projection(int[] sequence1, int[] sequence2) {
		super(sequence1, sequence2);

		this.projection = this.projection(sequence1, sequence2);
	}


	/** Calculate the projection of the first sequence onto the second sequence */
	public int[] projection(int[] sequence1, int[] sequence2) {

		int[] projectionSequence = new int[sequence2.length];
		int projectionSequenceLength = 0;
		boolean occursInSequence1;

		// Iterate through the second sequence
		for (int i = 0; i < sequence2.length; i++) {

			occursInSequence1 = false;

			// Iterate through the first sequence
			for (int j = 0; j < sequence1.length && !occursInSequence1; j++) {

				// If the current number occurs in both sequences, add it to the projection
				if (sequence2[i] == sequence1[j]) {
					projectionSequence[projectionSequenceLength] = sequence2[i];
					projectionSequenceLength++;

					// If the current number occurs in both sequences,
					// then we do not need to keep searching for the number,
					// so stop this inner loop
					occursInSequence1 = true;
				}
			}
		}

		// Since the projection array was initialized with the length of sequence2,
		// there may be empty elements (which are numbers in sequence2 which are not in sequence1).
		// To handle this, we return the sub-array up to the number of elements we added to the projection.
		return this.getArrayUpToIndex(projectionSequence, projectionSequenceLength);
	}

	/** Get the projection sequence as an integer array */
	public int[] getOutputSequence() {
		return this.projection;
	}

	/** Get the projection sequence as a string */
	public String getOutputString() {
		return this.getSequenceString(this.projection);
	}

	public String toString() {
		return "Projecting " + this.getSequenceString(this.sequence1)
			+ " to " + this.getSequenceString(this.sequence2)
			+ " results in: " + this.getSequenceString(projection);
	}

}
