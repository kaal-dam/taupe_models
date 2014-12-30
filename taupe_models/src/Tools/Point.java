package Tools;

/**
 * Point de coordonnées (x,y,z) 
 */
public class Point {
    double x;
    double y;
    double z;
    
    /**
     * Constructeur de Point
     * @param x : coordonnées x
     * @param y : coordonnées y
     * @param z : coordonnées z
     */
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    } 
    
    /**
     * Constructeur de Point
     * @param p : Point
     */
    public Point(Point p) {
		// TODO Auto-generated constructor stub
    	this.x = p.x;
    	this.y = p.y;
    	this.z = p.z;
	}
    
    /**
     * @return Coordonnées X du point
     */
    public double getX() {
		return x;
	}
    
    /**
     * @return Coordonnées Y du point
     */
    public double getY() {
		return y;
	}
    
    /**
     * @return Coordonnées Z du point
     */
    public double getZ() {
		return z;
	}
    
    @Override
    public String toString() {
    	return "[" + x + " : " + y + " : " + z  +"]";
    }
}