package Tools;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
	public double[][] matrice;
	public List<Point> points; 
	
	public Polygon() {
		points = new ArrayList<Point>();
	}
	
	public void initMatrice(){
		matrice = new double[points.size()][3];
		for(int i = 0; i < points.size(); i++){
			matrice[i] = points.get(i).toArray();
		}
	}
	
	public void setMatrice(double[][] m){
		matrice = m;
	}
}
