
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Model {

    List<Triangle> triangle;

    public Model(List<Triangle> t) {
        triangle = t;
    }

    public Model(String url) {
        triangle = new ArrayList<>();
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

        /**
         * Analyser le fichier ....
         */
        /* extrait le nombre de points, segments et triangles indiqués par le fichier */
        String ligne = bufferReader.readLine();
        String str[] = ligne.split(" ");
        if (str.length != 3) {
            System.out.println("Erreur : Nombres de points, segments, triangle indiqué incorrect");
            return false;
        }
        for (int i = 0; i < str.length; ++i) {
            if (!isNumber(str[i])) {
                System.out.println("Erreur : La première ligne de " + url + " comporte des caractères au lieu de nombres");
                return false;
            }
        }
        int nbPts = Integer.parseInt(str[0]);
        int nbSeg = Integer.parseInt(str[1]);
        int nbTri = Integer.parseInt(str[2]);

        /* Vérifie la liste des points */
        List<Point> listPoints = new ArrayList<>();
        if (!verifPoints(bufferReader, nbPts, listPoints)) {
            return false;
        }

        /* Vérifie la liste des segments */
        List<Segment> listSegments = new ArrayList<>();
        if (!verifSegments(bufferReader, nbSeg, listSegments, listPoints)) {
            return false;
        }

        /* Vérifie la liste des triangles */
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

    public boolean verifPoints(BufferedReader br, int nbPts, List<Point> list) throws Exception {
        String ligne;
        List<String> points = new ArrayList<String>();
        int i = 0;
        while (i < nbPts) {
            ligne = br.readLine();
            if (ligne.charAt(0) == '#') {
                System.out.println("Nombre de points incorrect");
                i--;
            }
            String str[] = ligne.split(" ");
            //nb de poin,t inccorect
            if (str.length != 3) {
                System.out.println("Erreur : Nombre de coordoonnées d'un point incorrect");
                return false;
            }
            //anomalie
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnée d'un point n'est pas un nombre");
                    return false;
                }
            }
            //doublons
            if (points.contains(ligne)) {
                System.out.println("Le point " + ligne + "est présent plusieurs fois");
                return false;
            } else {
                list.add(new Point(Float.parseFloat(str[0]), Float.parseFloat(str[1]), Float.parseFloat(str[2])));
                points.add(ligne);
            }
            ++i;
        }
        return list.size() == nbPts;
    }

    public boolean verifSegments(BufferedReader br, int nbSeg, List<Segment> list, List<Point> lPoints) throws Exception {
        String ligne;
        List<String> segments = new ArrayList<String>();
        int i = 0;
        while (i < nbSeg) {
            ligne = br.readLine();
            if (ligne.charAt(0) == '#') {
                System.out.println("Nombre de segments incorrect");
                i--;
            }
            String str[] = ligne.split(" ");
            if (str.length != 2) {
                System.out.println("Erreur : Nombre de points d'un segment incorrect");
                return false;
            }
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnée d'un segment n'est pas un nombre");
                    return false;
                }
                if (Integer.parseInt(str[j]) > lPoints.size()) {
                    System.out.println("Le point " + str[j] + " n'existe pas");
                    return false;
                }
            }
            if (segments.contains(ligne)) {
                System.out.println("Le segment " + ligne + " est présent plusieurs fois");
                return false;
            } else {
                list.add(new Segment(lPoints.get(Integer.parseInt(str[0]) - 1), lPoints.get(Integer.parseInt(str[1]) - 1)));
                segments.add(ligne);
            }
            ++i;
        }
        return list.size() == nbSeg;
    }

    public boolean verifTriangles(BufferedReader br, int nbTri, List<Segment> lSegments) throws Exception {
        String ligne;
        List<String> triangles = new ArrayList<String>();
        int i = 0;
        while (i < nbTri) {
            ligne = br.readLine();
            if (ligne == null) {
                System.out.println("Nombre de Triangles incorrect");
                i--;
            }
            String str[] = ligne.split(" ");
            if (str.length != 3) {
                System.out.println("Erreur : Nombre de segments d'un triangle incorrect");
                return false;
            }
            for (int j = 0; j < str.length; ++j) {
                if (!isNumber(str[j])) {
                    System.out.println("Erreur : Une coordonnée d'un triangle n'est pas un nombre");
                    return false;
                }
                if (Integer.parseInt(str[j]) > lSegments.size()) {
                    System.out.println("Le segment " + str[j] + " n'existe pas");
                    return false;
                }
            }
            if (triangles.contains(ligne)) {
                System.out.println("Le triangle " + ligne + " est présent plusieurs fois");
                return false;
            } else {
                triangle.add(new Triangle(lSegments.get(Integer.parseInt(str[0]) - 1), lSegments.get(Integer.parseInt(str[1]) - 1), lSegments.get(Integer.parseInt(str[2]) - 1)));
                triangles.add(ligne);
            }
            ++i;
        }
        return triangle.size() == nbTri;
    }
}
