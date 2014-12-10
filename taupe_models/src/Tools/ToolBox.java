package Tools;

import java.awt.Color;

public class ToolBox {
	
	//coef de scaling
	public static float coefX = 1;
	public static float coefY = 1;
	public static float coefZ = 1;

	// ancienne version (buggée)
	public static double[][] mRot(double x, double y) {
		return new double[][] {
				{ Math.cos(y * Math.PI / 64), 0, -Math.sin(y * Math.PI / 64), 0 },
				{ 0, Math.cos(x * Math.PI / 64), Math.sin(x * Math.PI / 64), 0 },
				{ Math.sin(y * Math.PI / 64), -Math.sin(x * Math.PI / 64),
						Math.cos((x + y) * Math.PI / 64), 0 }, { 0, 0, 0, 1 } };
	}

	public static double[][] mRotX(double x) {
		return new double[][] {
				{ 1, 0, 0, 0 },
				{ 0, Math.cos(x * Math.PI / 64), Math.sin(x * Math.PI / 64), 0 },
				{ 0, -Math.sin(x * Math.PI / 64), Math.cos(x * Math.PI / 64), 0 },
				{ 0, 0, 0, 1 } };
	}

	public static double[][] mRotY(double y) {
		return new double[][] {
				{ Math.cos(y * Math.PI / 64), 0, -Math.sin(y * Math.PI / 64), 0 },
				{ 0, 1, 0, 0 },
				{ Math.sin(y * Math.PI / 64), 0, Math.cos(y * Math.PI / 64), 0 },
				{ 0, 0, 0, 1 } };
	}

	// constante de cadrage
	public static double zoomIn = 1.5;
	public static double zoomOut = 1 / zoomIn;
	// matrice zoomIn
	public static double[][] mZoomIn = new double[][] { { zoomIn, 0, 0, 0 },
			{ 0, zoomIn, 0, 0 }, { 0, 0, zoomIn, 0 }, { 0, 0, 0, 1 } };
	// matrice zoomOut
	public static double[][] mZoomOut = new double[][] { { zoomOut, 0, 0, 0 },
			{ 0, zoomOut, 0, 0 }, { 0, 0, zoomOut, 0 }, { 0, 0, 0, 1 } };

	// matrice de translation
	public static double[][] mTrans(double x, double y) {
		return new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 },
				{ x, y, 0, 1 } };
	}

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
		return Math.sqrt(produitScalaire(v, v));
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
		int color = (int) (150 * coef);
		return new Color(color, color, color);
	}
}