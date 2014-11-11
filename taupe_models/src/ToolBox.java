import java.awt.Color;
import java.lang.Math.*;

public class ToolBox {
	// constante de rotation
	public static final double angleRotHorraire = Math.PI / 32;
	public static final double angleRotAntiHorraire = -Math.PI / 32;
	// rotation horraire
	public static double[][] mRotationXHorraire = new double[][] {
			{ 1, 0, 0, 0 },
			{ 0, Math.cos(angleRotHorraire), Math.sin(angleRotHorraire), 0 },
			{ 0, -Math.sin(angleRotHorraire), Math.cos(angleRotHorraire), 0 },
			{ 0, 0, 0, 1 } };
	public static double[][] mRotationYHorraire = new double[][] {
			{ Math.cos(angleRotHorraire), 0, Math.sin(angleRotHorraire), 0 },
			{ 0, 1, 0, 0 },
			{ -Math.sin(angleRotHorraire), 0, Math.cos(angleRotHorraire), 0 },
			{ 0, 0, 0, 1 } };
	// rotation anti-horraire
	public static double[][] mRotationYAntiHorraire = new double[][] {
			{ Math.cos(angleRotAntiHorraire), 0,
					Math.sin(angleRotAntiHorraire), 0 },
			{ 0, 1, 0, 0 },
			{ -Math.sin(angleRotAntiHorraire), 0,
					Math.cos(angleRotAntiHorraire), 0 }, { 0, 0, 0, 1 } };
	public static double[][] mRotationXAntiHorraire = new double[][] {
			{ 1, 0, 0, 0 },
			{ 0, Math.cos(angleRotAntiHorraire),
					Math.sin(angleRotAntiHorraire), 0 },
			{ 0, -Math.sin(angleRotAntiHorraire),
					Math.cos(angleRotAntiHorraire), 0 }, { 0, 0, 0, 1 } };

	// constante de cadrage
	public static double zoomIn = 1.5;
	public static double zoomOut = 0.5;
	// matrice zoomIn
	public static double[][] mZoomIn = new double[][] { { zoomIn, 0, 0, 0 },
			{ 0, zoomIn, 0, 0 }, { 0, 0, zoomIn, 0 }, { 0, 0, 0, 1 } };
	// matrice zoomOut
	public static double[][] mZoomOut = new double[][] { { zoomOut, 0, 0, 0 },
			{ 0, zoomOut, 0, 0 }, { 0, 0, zoomOut, 0 }, { 0, 0, 0, 1 } };

	//constante de translation
	public static double transX = 20;
	public static double transY = 20;
	//trans sur X
	public static double[][] mTransXPos = new double[][]{
		{1,0,0,transX},
		{0,1,0,0},
		{0,0,1,0},
		{0,0,0,1},
	};
	public static double[][] mTransXNeg = new double[][]{
		{1,0,0,-transX},
		{0,1,0,0},
		{0,0,1,0},
		{0,0,0,1},
	};
	//trans sur Y
	public static double[][] mTransYPos = new double[][]{
		{1,0,0,transY},
		{0,1,0,0},
		{0,0,1,0},
		{0,0,0,1},
	};
	public static double[][] mTransYNeg = new double[][]{
		{1,0,0,-transY},
		{0,1,0,0},
		{0,0,1,0},
		{0,0,0,1},
	};
	
	/**
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static double[][] produitMatriciel(double[][] m1, double[][] m2) {
		double[][] res = new double[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				res[i][j] = 0;
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}

	public static double[] produitVectoriel(double[] v1, double[] v2) {
		double[] res = new double[3];
		res[0] = (v1[1] * v2[2]) - (v1[2] * v2[1]);
		res[1] = (v1[2] * v2[0]) - (v1[0] * v2[2]);
		res[2] = (v1[0] * v2[1]) - (v1[1] * v2[0]);
		return res;
	}

	public static double produitScalaire(double[] v1, double[] v2) {
		return (v1[0] * v2[0]) + (v1[1] * v2[1]) + (v1[2] * v2[2]);
	}

	public static double normeVectoriel(double[] v) {
		return produitScalaire(v, v);
	}

	public static double[] getVecteur(double[] p1, double[] p2) {
		double[] res = new double[3];
		for (int i = 0; i < 3; i++) {
			res[i] = p1[i] - p2[i];
		}
		return res;
	}

	public static Color getColor(Triangle t) {
		double[] n = produitVectoriel(
				getVecteur(t.matrixPoint[0], t.matrixPoint[1]),
				getVecteur(t.matrixPoint[0], t.matrixPoint[2]));
		double[] v = new double[] { 0, 0, 1 };

		double coef = Math
				.abs(Math.cos(((Math.abs(produitScalaire(v, n)) / (normeVectoriel(v) * normeVectoriel(n))))));
		int color = (int) (255 * coef);
		return new Color(color, color, color);
	}
}
