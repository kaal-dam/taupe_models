/**
 *
 * @author flo
 */
public class Point {
    float x;
    float y;
    float z;
    
    public Point(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    } 
    
    public Point(Point p) {
		// TODO Auto-generated constructor stub
    	this.x = p.x;
    	this.y = p.y;
    	this.z = p.z;
	}
    
    @Override
    public String toString() {
    	return "[" + x + " : " + y + " : " + z  +"]";
    }
}