package model;

public abstract class BinarySeqOperation extends SeqOperation {

	protected int[] sequence1;
	protected int[] sequence2;

	public BinarySeqOperation(int[] sequence1, int[] sequence2) {
		this.sequence1 = sequence1;
		this.sequence2 = sequence2;
	}

}
