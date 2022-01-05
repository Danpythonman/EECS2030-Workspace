package aggregation;

public class Point {

	private int x;
	private int y;

	// --------------- CONSTRUCTORS ---------------

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	// --------------- ACCESSORS ---------------

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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
		Point otherPoint = (Point) obj;

		return this.x == otherPoint.x && this.y == otherPoint.y;
	}

	// --------------- MUTATORS ---------------

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
