

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * ######################
 * ## A CODER          ##
 * ######################
 * @author flo
 */
public class Analyseur {

    public static Model Analyse(String urlFile) {
        try {
            BufferedReader myfile = new BufferedReader(new FileReader(urlFile));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return null;
    }

}
