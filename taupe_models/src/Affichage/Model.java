package Affichage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tools.Point;
import Tools.Segment;
import Tools.Triangle;

public class Model {

    public List<Triangle> triangle;

    public Model(List<Triangle> t) {
        triangle = t;
    }

    public Model(String url) {
        triangle = new ArrayList<Triangle>();
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
        int nbPts = Integer.parseInt(str[0]);
        int nbSeg = Integer.parseInt(str[1]);
        int nbTri = Integer.parseInt(str[2]);

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
}