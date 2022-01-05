package composition;

public class Line {

	private Point p1;
	private Point p2;

	// --------------- CONSTRUCTORS ---------------

	public Line(Point p1, Point p2) {
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
	}

	public Line(Line line) {
		this.p1 = new Point(line.p1);
		this.p2 = new Point(line.p2);
	}

	// --------------- ACCESSORS ---------------

	public Point getStart() {
		return new Point(this.p1);
	}

	public Point getEnd() {
		return new Point(this.p2);
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
		this.p1 = new Point(point);
	}

	public void setEnd(Point point) {
		this.p2 = new Point(point);
	}

}
