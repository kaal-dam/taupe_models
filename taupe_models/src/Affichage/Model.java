package Affichage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Main.MainClass;
import Tools.Point;
import Tools.Segment;
import Tools.ToolBox;
import Tools.Triangle;

public class Model {
	
	/**
	 * permet d'identifier le X minimum et maximum du modele
	 */
	public float xMin, xMax;
	/**
	 * permet d'identifier le Y minimum et maximum du modele
	 */
	public float yMin, yMax;
	/**
	 * permet d'identifier le Z minimum et maximum du modele
	 */
	public float zMin, zMax;

	/***
	 * liste de triangle du modele
	 */
    public List<Triangle> triangle;
    
    /**
     * permet de connaitre le nb de points, de segments, et de triangles du modele
     */
    public static int nbPts, nbSeg, nbTri;
    
    /**
     * nom/url du modele
     */
    public String url;
    
    /**
     *creer un modeele en fonction d'une liste de triangle
     * @param t liste des triangle du futur modele
     */
    public Model(List<Triangle> t) {
        triangle = t;
    }

    /**
     * creer un modele a partir du fichier donner en parameetre
     * @param url chemin veers le fichier GTS du modele
     */ 
    public Model(String url) {
    	this.url = url;
        triangle = new ArrayList<Triangle>();
        xMax = -999;
        xMin = 999;
        yMax = -999;
        yMin = 999;
        zMax = -999;
        zMin = 999;
        try {
            this.loadFile(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    /**
     * charge un modele contenu dans un fichier
     * @param url chemin vers le fichier a charger
     * @return vrai si le modele est bien charger sinon faux
     * @throws Exception DK
     */
    public boolean loadFile(String url) throws Exception {
        BufferedReader bufferReader = null;
        FileReader fichierSrc = null;
        try {
            fichierSrc = new FileReader(url);
            bufferReader = new BufferedReader(fichierSrc);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
        /* extrait le nombre de points, segments et triangles indiques par le fichier */
        String ligne = bufferReader.readLine();
        String str[] = ligne.split(" ");
        if (str.length != 3) {
            System.out.println("Erreur : Nombre de points, segments, triangle indique incorrect");
            return false;
        }
        for (int i = 0; i < str.length; ++i) {
            if (!isNumber(str[i])) {
                System.out.println("Erreur : La premiere ligne de " + url + " comporte des caracteres au lieu de nombres");
                return false;
            }
        }
        nbPts = Integer.parseInt(str[0]);
        nbSeg = Integer.parseInt(str[1]);
        nbTri = Integer.parseInt(str[2]);

        /* Verifie la liste des points */
        Map<Integer,Point> listPoints = new HashMap<Integer,Point>();
        if (!verifPoints(bufferReader, nbPts, listPoints)) {
            return false;
        }

        /* Verifie la liste des segments */
        Map<Integer,Segment> listSegments = new HashMap<Integer,Segment>();
        if (!verifSegments(bufferReader, nbSeg, listSegments, listPoints)) {
            return false;
        }

        /* Verifie la liste des triangles */
        if (!verifTriangles(bufferReader, nbTri, listSegments)) {
            return false;
        }

        bufferReader.close();
        /**
         * recentrer model et zoomer direct
         */
        
        double x =0, y = 0, z = 0;
        for(Triangle t : this.triangle){
        	x += t.getBarycentre().getX();
        	y += t.getBarycentre().getY();
        	z += t.getBarycentre().getZ();
        }
        x = x/triangle.size();
        y = y/triangle.size();
        z = z/triangle.size();
        
        float coefZoom = (((float) (MainClass.panelW)/100) * 15)/xMin;
        for(Triangle t : triangle){
        	t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, new double[][]{{1, 0, 0, 0},
        																		   {0, 1, 0, 0},
        																		   {0, 0, 1, 0},
        																		   {-x, -y, -z, 1}});
        	t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, new double[][]{{coefZoom, 0, 0, 0},
        	        {0, coefZoom, 0, 0}, {0, 0, coefZoom, 0}, {0, 0, 0, 1}});
        	
        }
        
        return true;
    }
    
    /**
     * retourne si le string donner est un nombre ou non
     * @param s chaine contenant le chiffre
     * @return vrai si le string est un chiffre
     */
    public static boolean isNumber(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    
    /**
     * verifie si les points sont correct dans le buffer et les stock dans la map
     * @param br buffer a lire
     * @param nbPts nb de points a verifier
     * @param list map ou ranger les different points
     * @return vrai si l'operation est complete
     * @throws Exception NC
     */
    public boolean verifPoints(BufferedReader br, int nbPts, Map<Integer,Point> list) throws Exception {
        String ligne;
        int i = 0;
        while (i < nbPts) {
            ligne = br.readLine();
            String str[] = ligne.split(" ");
            //nb de point inccorect
            if (str.length != 3) {
                System.out.println("Erreur : Nombre de coordoonnees d'un point incorrect");
                return false;
            }
            //anomalie
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnee d'un point n'est pas un nombre");
                    return false;
                }
            }
            //on recupere le xMin et xMax
            if(Float.parseFloat(str[0]) > xMax){
            	xMax = Float.parseFloat(str[0]);
            }else if(Float.parseFloat(str[0]) < xMin){
            	xMin = Float.parseFloat(str[0]);
            }
            //on recupere le yMin, yMax
            if(Float.parseFloat(str[1]) > yMax){
            	yMax = Float.parseFloat(str[1]);
            }else if(Float.parseFloat(str[1]) < yMin){
            	yMin = Float.parseFloat(str[1]);
            }
            //on recupere z min/max
            if(Float.parseFloat(str[2]) > zMax){
            	zMax = Float.parseFloat(str[2]);
            }else if(Float.parseFloat(str[2]) < zMin){
            	zMin = Float.parseFloat(str[2]);
            }
            list.put(i, new Point(Float.parseFloat(str[0]), Float.parseFloat(str[1]), Float.parseFloat(str[2])));
            ++i;
        }
        return list.size() == nbPts;
    }

    /**
     * verifie si les segment du buffer sont correct et les range dans la map correspondante
     * @param br buffer a lire
     * @param nbSeg nb de segment a verifier
     * @param list map ou ranger les segment
     * @param lPoints liste des points formant les segment
     * @return vrai si tout est ok
     * @throws Exception NC
     */
    public boolean verifSegments(BufferedReader br, int nbSeg, Map<Integer,Segment> list, Map<Integer,Point> lPoints) throws Exception {
        String ligne;
        int i = 0;
        while (i < nbSeg) {
            ligne = br.readLine();
            String str[] = ligne.split(" ");
            if (str.length != 2) {
                System.out.println("Erreur : Nombre de points d'un segment incorrect");
                return false;
            }
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnee d'un segment n'est pas un nombre");
                    return false;
                }
                if (Integer.parseInt(str[j]) > lPoints.size() || Integer.parseInt(str[j]) < 1) {
                    System.out.println("Le point " + str[j] + " n'existe pas");
                    return false;
                }
            }
            list.put(i, new Segment(lPoints.get(Integer.parseInt(str[0]) - 1), lPoints.get(Integer.parseInt(str[1]) - 1)));
            ++i;
        }
        return list.size() == nbSeg;
    }
    
    @Override
    /**
     * retourne l'url du modele
     */
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.url;
    }

    /**
     * verifie si tous les triangles du buffer son t correct et les stock dans la map correspondante.
     * @param br buffer a lire
     * @param nbTri nb de triangle a verifier
     * @param lSegments liste des segment composant les triangles
     * @return vrai si tous est ok
     * @throws Exception NC
     */
    public boolean verifTriangles(BufferedReader br, int nbTri, Map<Integer,Segment> lSegments) throws Exception {
        String ligne;
        int i = 0;
        while (i < nbTri) {
            ligne = br.readLine();
            if (ligne == null) {
                System.out.println("Nombre de Triangles incorrect");
                return false;
            }
            String str[] = ligne.split(" ");
            if (str.length != 3) {
                System.out.println("Erreur : Nombre de segments d'un triangle incorrect");
                return false;
            }
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnee d'un triangle n'est pas un nombre");
                    return false;
                }
                if (Integer.parseInt(str[j]) > lSegments.size() || Integer.parseInt(str[j]) < 1) {
                    System.out.println("Le segment " + str[j] + " n'existe pas");
                    return false;
                }
            }
            triangle.add(new Triangle(lSegments.get(Integer.parseInt(str[0]) - 1)
              , lSegments.get(Integer.parseInt(str[1]) - 1)
              , lSegments.get(Integer.parseInt(str[2]) - 1)));
            ++i;
        }
        return triangle.size() == nbTri;
    }
    
    /**
     * calcul le barrycentre du modele 3d
     * @return Point correzspondant a ce barrycentre
     */
    public Point getCentreGravite() {
    	double x=0;
    	double y=0;
    	double z=0;
    	for(int i=0; i<triangle.size(); ++i) {
    		x += triangle.get(i).getBarycentre().getX();
    		y += triangle.get(i).getBarycentre().getY();
    		z += triangle.get(i).getBarycentre().getZ();
    	}
    	x = x/triangle.size();
    	y = y/triangle.size();
    	z = z/triangle.size();
    	return new Point(x, y, z);
    }
}