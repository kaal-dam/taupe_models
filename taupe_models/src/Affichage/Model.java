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
	
	public float xMin, xMax;
	public float yMin, yMax;
	public float zMin, zMax;

    public List<Triangle> triangle;
    
    public static int nbPts, nbSeg, nbTri;
    
    public String url;
    
    public Model(){
    	triangle = new ArrayList<Triangle>();
    }

    public Model(List<Triangle> t) {
        triangle = t;
    }

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

    public boolean isNumber(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

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
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.url;
    }

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