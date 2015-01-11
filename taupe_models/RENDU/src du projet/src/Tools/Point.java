package Tools;

/**
 * Point de coordonn�es (x,y,z) 
 */
public class Point {
    double x;
    double y;
    double z;
    
    /**
     * Constructeur de Point
     * @param x : coordonn�es x
     * @param y : coordonn�es y
     * @param z : coordonn�es z
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
     * @return Coordonn�es X du point
     */
    public double getX() {
		return x;
	}
    
    /**
     * @return Coordonn�es Y du point
     */
    public double getY() {
		return y;
	}
    
    /**
     * @return Coordonn�es Z du point
     */
    public double getZ() {
		return z;
	}
    
    /**
     * 
     * convertie le point en un tableau de double
     * @return tableau de double du point
     */
    public double[] toArray(){
    	return new double[]{x,y,z};
    }
    
    @Override
    /**
     * retourne un string du point
     */
    public String toString() {
    	return "[" + x + " : " + y + " : " + z  +"]";
    }
}