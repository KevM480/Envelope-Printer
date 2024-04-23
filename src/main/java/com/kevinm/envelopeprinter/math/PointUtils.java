package com.kevinm.envelopeprinter.math;

import java.awt.Point;

public class PointUtils {

	public static Point addPoints(Point p1, Point p2) {
		Point p = new Point(p1);
		p.translate(p2.x, p2.y);
		return p;
	}

	public static Point subPoints(Point p1, Point p2) {
		Point p = new Point(p1);
		p.translate(-p2.x, -p2.y);
		return p;
	}

	public static Point divPoints(Point p1, Point p2) {
		Point p = new Point(p1);
		p.x /= p2.x;
		p.y /= p2.y;
		return p;
	}

	public static Point multPoints(Point p1, Point p2) {
		Point p = new Point(p1);
		p.x *= p2.x;
		p.y *= p2.y;
		return p;
	}

	public static Point scalePoint(Point p1, double scale) {
		Point p = new Point(p1);
		p.x *= scale;
		p.y *= scale;
		return p;
	}

}
