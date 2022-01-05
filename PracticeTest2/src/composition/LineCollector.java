package composition;

public class LineCollector {

	private Line[] lines;
	private int numberOfLines;
	private final int MAXIMUM_NUMBER_OF_LINES = 100;

	// --------------- CONSTRUCTORS ---------------

	public LineCollector() {
		this.lines = new Line[this.MAXIMUM_NUMBER_OF_LINES];
		this.numberOfLines = 0;
	}

	public LineCollector(LineCollector lineCollector) {
		this.lines = new Line[this.MAXIMUM_NUMBER_OF_LINES];

		for (int i = 0; i < lineCollector.numberOfLines; i++) {
			this.lines[i] = new Line(lineCollector.lines[i]);
		}
		this.numberOfLines = lineCollector.numberOfLines;
	}

	// --------------- ACCESSORS ---------------

	public Line getLineAt(int index) {
		return new Line(this.lines[index]);
	}

	public Line[] getLines() {
		Line[] nonNullLines = new Line[this.numberOfLines];

		for (int i = 0; i < this.numberOfLines; i++) {
			nonNullLines[i] = new Line(this.lines[i]);
		}

		return nonNullLines;
	}

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
		LineCollector otherLineCollector = (LineCollector) obj;

		if (this.numberOfLines == otherLineCollector.numberOfLines) {
			// Make copy of other lines so that we can remove elements (set to null)
			// if a match is found. This will prevent comparing elements that
			// were already matched with elements in the this.lines array.
			Line[] otherLines = new Line[otherLineCollector.numberOfLines];
			for (int i = 0; i < otherLineCollector.numberOfLines; i++) {
				otherLines[i] = otherLineCollector.lines[i];
			}

			boolean equalLines = true;

			for (int i = 0; i < this.numberOfLines && equalLines == true; i++) {

				equalLines = false;

				for (int j = 0; j < this.numberOfLines && equalLines == false; j++) {
					if (this.lines[i].equals(otherLines[i])) {
						equalLines = true;
						otherLines[i] = null;
					}
				}

			}

			return equalLines;
		}
		return false;
	}

	// --------------- MUTATORS ---------------

	public void addLine(Line line) {
		this.lines[this.numberOfLines] = new Line(line);
		this.numberOfLines++;
	}

}
