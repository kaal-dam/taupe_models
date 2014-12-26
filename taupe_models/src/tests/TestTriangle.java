package tests;

import java.util.ArrayList;
import java.util.List;

import Tools.Point;
import Tools.Segment;
import Tools.Triangle;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestTriangle {
	
	Point p1 = new Point(0, 0, 0);
	Point p2 = new Point(0, 0, 1);
	Point p3 = new Point(0, 1, 0);
	Point p4 = new Point(0, 1, 1);
	Point p5 = new Point(1, 0, 0);
	Point p6 = new Point(1, 0, 1);
	Point p7 = new Point(1, 1, 0);
	Point p8 = new Point(1, 1, 1);
	
	List<Point> lp;
	
	Segment s1 = new Segment(p1, p2);
	Segment s2 = new Segment(p2, p3);
	Segment s3 = new Segment(p3, p1);
	
	Triangle t1 = new Triangle(s1, s2, s3);

	@Test
	public void testExistIn() {
		lp = new ArrayList<Point>();
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		assertTrue(Tools.Triangle.existIn(lp, p1));
		assertFalse(Tools.Triangle.existIn(lp, p4));
	}
	
	@Test
	public void testGetBarycentre() {
		double d = 1.0;
		assertEquals(new Point(0.0, d/3, d/3), t1.getBarycentre());
	}

}
