package model;

public abstract class SeqEvaluator {

	int maximumNumberOfOperations;
	SeqOperation[] operations;
	int numberOfOperations;
	int numberOfInvalidOperations;

	public SeqEvaluator(int maximumNumberOfOperations) {
		this.maximumNumberOfOperations = maximumNumberOfOperations;
		this.numberOfOperations = 0;

		this.operations = new SeqOperation[maximumNumberOfOperations];

		this.numberOfInvalidOperations = 0;
	}

	public abstract void addOperation(String operation, int[] sequence1, int[] sequence2) throws IllegalOperationException;

}
