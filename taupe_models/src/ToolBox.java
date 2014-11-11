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
}
