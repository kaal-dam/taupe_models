package Tools;

import java.awt.Color;

/**
 * 
 * Classe regroupant tous les outils nécessaires au maniement des modèles.<br>
 * 
 *
 */
public class ToolBox {
	
	//couleur du modeles
	public static Color defaultColor = Color.CYAN;
	
	//Anti-aliasing on/off
	public static boolean antiAlia = false;
	
	//parametre de la vue:
	public static boolean arrete = false;
	public static boolean point = false;
	public static boolean face = true;

    //coef de scaling
    public static float coefX = 1;
    public static float coefY = 1;
    public static float coefZ = 1;

    // ancienne version (buggee)
    /*public static double[][] mRot(double x, double y) {
        return new double[][]{
                    {Math.cos(y * Math.PI / 64), 0, -Math.sin(y * Math.PI / 64), 0},
                    {0, Math.cos(x * Math.PI / 64), Math.sin(x * Math.PI / 64), 0},
                    {Math.sin(y * Math.PI / 64), -Math.sin(x * Math.PI / 64),
                        Math.cos((x + y) * Math.PI / 64), 0}, {0, 0, 0, 1}};
    }*/

    /**
     * Permet de générer une matrice de rotation sur l'axe X
     * plus ou moins important en fonction du parametre x.
     * @param x : deplacement horizontal effectue a la souris
     * @return Une matrice de rotation X en fonction du mouvement en X
     */
    public static double[][] mRotX(double x) {
        return new double[][]{
                    {1, 0, 0, 0},
                    {0, Math.cos(x * Math.PI / 64), Math.sin(x * Math.PI / 64), 0},
                    {0, -Math.sin(x * Math.PI / 64), Math.cos(x * Math.PI / 64), 0},
                    {0, 0, 0, 1}};
    }

    /**
     * Permet de générer une matrice de rotation sur l'axe Y
     * plus ou moins important en fonction du parametre y.
     * @param y : deplacement vertical effectue a la souris
     * @return Une matrice de rotation X en fonction du mouvement en Y
     */
    public static double[][] mRotY(double y) {
        return new double[][]{
                    {Math.cos(y * Math.PI / 64), 0, -Math.sin(y * Math.PI / 64), 0},
                    {0, 1, 0, 0},
                    {Math.sin(y * Math.PI / 64), 0, Math.cos(y * Math.PI / 64), 0},
                    {0, 0, 0, 1}};
    }
    
    // constante de cadrage
    public static double zoomIn = 1.5;
    public static double zoomOut = 1 / zoomIn;
    
    // matrice zoomIn
    public static double[][] mZoomIn = new double[][]{
    							{zoomIn, 0, 0, 0},
    							{0, zoomIn, 0, 0},
    							{0, 0, zoomIn, 0},
    							{0, 0, 0, 1}
    };
    
    // matrice zoomOut
    public static double[][] mZoomOut = new double[][]{
    							{zoomOut, 0, 0, 0},
    							{0, zoomOut, 0, 0},
    							{0, 0, zoomOut, 0},
    							{0, 0, 0, 1}
    };

    /**
     * Permet de générer une matrice de translation
     * en fonction des parametres x et y
     * @param x : deplacement horizontal effectue a la souris
     * @param y : deplacement vertical effectue a la souris
     * @return Une matrice de translation en fonction du mouvement effectue
     */
    public static double[][] mTrans(double x, double y) {
        return new double[][]{
        					{1, 0, 0, 0},
        					{0, 1, 0, 0},
        					{0, 0, 1, 0},
        					{x, y, 0, 1}};
    }

    /**
     * Permet de calculer un produit matriciel
     * @param m1 : Matrice 1
     * @param m2 : Matrice 2
     * @return Nouvelle matrice correspondant au produit matriciel des matrices m1 et m2
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
    
    /**
     * Permet de calculer un produit vectoriel
     * @param v1 : Vecteur 1
     * @param v2 : Vecteur 2
     * @return Nouveau vecteur correspondant au produit vectoriel des vecteurs v1 et v2
     */
    public static double[] produitVectoriel(double[] v1, double[] v2) {
        double[] res = new double[3];
        res[0] = (v1[1] * v2[2]) - (v1[2] * v2[1]);
        res[1] = (v1[2] * v2[0]) - (v1[0] * v2[2]);
        res[2] = (v1[0] * v2[1]) - (v1[1] * v2[0]);
        return res;
    }
    
    /**
     * Permet de calculer un produit scalaire
     * @param v1 : Vecteur 1
     * @param v2 : Vecteur 2
     * @return Nouveau vecteur correspondant au produit vectoriel des vecteurs v1 et v2
     */
    public static double produitScalaire(double[] v1, double[] v2) {
        return (v1[0] * v2[0]) + (v1[1] * v2[1]) + (v1[2] * v2[2]);
    }

    /**
     * Permet de calculer la norme vectorielle d'un vecteur
     * @param v : Vecteur
     * @return Norme vectorielle du vecteur v
     */
    public static double normeVectoriel(double[] v) {
        return Math.sqrt(produitScalaire(v, v));
    }

    /**
     * Permet d'obtenir le vecteur correspondant a deux points
     * @param p1 : Point 1
     * @param p2 : Point 2
     * @return Un vecteur
     */
    public static double[] getVecteur(double[] p1, double[] p2) {
        double[] res = new double[3];
        for (int i = 0; i < 3; i++) {
            res[i] = p1[i] - p2[i];
        }
        return res;
    }

    /**
     * Permet de calculer la couleur qu'aura un triangle lors de son affichage
     * @param t : Triangle
     * @return La couleur du Triangle
     */
    public static Color getColor(Triangle t) {
        double[] n = produitVectoriel(
                getVecteur(t.matrixPoint[0], t.matrixPoint[1]),
                getVecteur(t.matrixPoint[0], t.matrixPoint[2]));
        double[] v = new double[]{0, 0, 1};

        double coef =1- Math.abs(Math.cos(((Math.abs(produitScalaire(v, n)) / (normeVectoriel(v) * normeVectoriel(n))))));
        return new Color((int) (defaultColor.getRed() * coef), (int) (defaultColor.getGreen() * coef), (int) (defaultColor.getBlue() * coef));
        //return new Color((int) (Math.random()*256 * coef), (int) (Math.random()*256 * coef), (int) (Math.random()*256 * coef));
    }
    
    
    public static double[][] axe = new double[][]{{30,0,0,0},{0,30,0,0},{0,0,30,0}};
}