package aggregation;

public class Line {

	private Point p1;
	private Point p2;

	// --------------- CONSTRUCTORS ---------------

	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Line(Line line) {
		this.p1 = line.p1;
		this.p2 = line.p2;
	}

	// --------------- ACCESSORS ---------------

	public Point getStart() {
		return this.p1;
	}

	public Point getEnd() {
		return this.p2;
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
		Line otherLine = (Line) obj;

		return this.p1.equals(otherLine.p1) && this.p2.equals(otherLine.p2);
	}

	// --------------- MUTATORS ---------------

	public void setStart(Point point) {
		this.p1 = point;
	}

	public void setEnd(Point point) {
		this.p2 = point;
	}
}
