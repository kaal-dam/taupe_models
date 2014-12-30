package Tools;

/**
 * Segment de points p1 et p2
 */
public class Segment {
    Point pt1;
    Point pt2;
    
    /**
     * Constructeur de Segment
     * @param p1 : Point 1
     * @param p2 : Point 2
     */
    public Segment(Point p1, Point p2){
        this.pt1 = p1;
        this.pt2 = p2;
    }
    
    /**
     * Constructeur de Segment
     * @param s : Segment
     */
    public Segment(Segment s) {
    	this.pt1 = s.pt1;
    	this.pt2 = s.pt2;
	}
    
    /**
     * @return Point 1 du segment
     */
    public Point getPt1() {
        return pt1;
    }

    /**
     * @return Point 2 du segment
     */
    public Point getPt2() {
        return pt2;
    }
    
    
}
