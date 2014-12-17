package Tools;
/**
 *
 * @author flo
 */
public class Point {
    double x;
    double y;
    double z;
    
    public Point(double x, double y, double z){
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
    
    public double getX() {
		return x;
	}
    
    public double getY() {
		return y;
	}
    
    public double getZ() {
		return z;
	}
    
    @Override
    public String toString() {
    	return "[" + x + " : " + y + " : " + z  +"]";
    }
}