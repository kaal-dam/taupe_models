import java.awt.Color;
import java.lang.Math.*;
public class ToolBox {
	
	public static final double angleRot = Math.PI/32;
	public static double[][] mRotationX = new double[][]{{1,0,0,0},
		{0,Math.cos(angleRot),Math.sin(angleRot),0},
		{0,-Math.sin(angleRot),Math.cos(angleRot), 0},
		{0,0,0,1}};
	public static double[][] mRotationY = new double[][]{{Math.cos(angleRot),0,Math.sin(angleRot),0},
		{0,1,0,0},
		{-Math.sin(angleRot),0,Math.cos(angleRot), 0},
		{0,0,0,1}};
	
	
	/**
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static double[][] produitMatriciel(double[][] m1, double[][] m2){
		double[][] res = new double[m1.length][m2[0].length];
		for(int i =0; i < m1.length; i++){
			for(int j = 0; j < m2[0].length; j++){
				res[i][j] = 0;
				for(int k =0; k < m2.length; k++){
					res[i][j] += m1[i][k]*m2[k][j];
				}
			}
		}
		return res;
	}
	
	public static double[] produitVectoriel(double[] v1, double[] v2){
		double[] res = new double[3];
		res[0] =(v1[1]*v2[2]) - (v1[2]*v2[1]);
		res[1] =(v1[2]*v2[0]) - (v1[0]*v2[2]);
		res[2] =(v1[0]*v2[1]) - (v1[1]*v2[0]);
		return res;
	}
	
	public static double produitScalaire(double[] v1, double[] v2){
		return (v1[0]*v2[0]) + (v1[1]*v2[1]) +(v1[2]*v2[2]);
	}
	
	public static double normeVectoriel(double[] v){
		return produitScalaire(v, v);
	}
	
	public static double[] getVecteur(double[] p1, double[] p2){
		double[] res = new double[3];
		for(int i =0; i<3; i++){
			res[i] = p1[i] - p2[i];
		}
		return res;
	}
	
	public static Color getColor(Triangle t){
		double[] n = produitVectoriel(getVecteur(t.matrixPoint[0], t.matrixPoint[1]), getVecteur(t.matrixPoint[0], t.matrixPoint[2]));
		double[] v = new double[]{0,0,1};
		
		double coef = Math.abs(Math.cos(((Math.abs(produitScalaire(v, n))/(normeVectoriel(v)*normeVectoriel(n))))));
		int color = (int) (255*coef);
		return new Color(color,color,color);
	}
}
