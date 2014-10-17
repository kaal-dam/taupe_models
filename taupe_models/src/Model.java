

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Model {
    List<Triangle> triangle;   
    public Model(List<Triangle> t){
        triangle = t;
    }
    
    public boolean loadFile(String url) throws Exception{
        BufferedReader bufferReader = null;
        FileReader fichierSrc = null;
        try{
            fichierSrc = new FileReader(url);
            bufferReader = new BufferedReader(fichierSrc);
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            return false;
        }
        /**
         * Analyser le fichier ....
         */
        
        bufferReader.close();
        return true;
    }
}
