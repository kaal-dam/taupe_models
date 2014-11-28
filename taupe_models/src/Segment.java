/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author flo
 */
public class Segment {
    Point pt1;
    Point pt2;
    
    public Segment(Point p1, Point p2){
        this.pt1 = p1;
        this.pt2 = p2;
    }
    
    public Segment(Segment s) {
		// TODO Auto-generated constructor stub
    	this.pt1 = s.pt1;
    	this.pt2 = s.pt2;
	}

    public Point getPt1() {
        return pt1;
    }

    public Point getPt2() {
        return pt2;
    }
    
    
}
