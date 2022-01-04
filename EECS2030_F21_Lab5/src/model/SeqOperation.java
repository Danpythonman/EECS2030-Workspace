package model;

public abstract class SeqOperation {

	public abstract String getOutputString();

	/** Get sub-array of an array up to specified index */
	protected int[] getArrayUpToIndex(int[] fullArray, int length) {
		int[] newArray = new int[length];

		for (int i = 0; i < length; i++) {
			newArray[i] = fullArray[i];
		}

		return newArray;
	}

	/** Get string of sequence, square brackets used to enclose the sequence */
	protected String getSequenceString(int[] sequence) {
		String sequenceString = "[";

		for (int i = 0; i < sequence.length; i++) {
			sequenceString += sequence[i];

			if (i < sequence.length - 1) {
				sequenceString += ", ";
			}
		}

		sequenceString += "]";

		return sequenceString;
	}

}
