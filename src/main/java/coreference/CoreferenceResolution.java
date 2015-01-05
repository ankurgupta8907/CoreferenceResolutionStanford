package coreference;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.*;
import java.util.Properties;

/**
 * Created by ankurgupta on 12/29/14.
 */
public class CoreferenceResolution {
    
    public static void main(String[] argv) throws Exception {

        Properties props = new Properties();
        String fileName = "/Users/ankurgupta/Desktop/code/CoreferenceResolution/src/main/java/coreference/coref.properties";
        InputStream inputStream = new FileInputStream(fileName);
        props.load(inputStream);
        

        String textFileName = "/Users/ankurgupta/Desktop/code/CoreferenceResolution/src/main/java/coreference/input.txt";
        FileInputStream textInputStream = new FileInputStream(textFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(textInputStream));
        String text = br.readLine();

        String outputFileName = "/Users/ankurgupta/Desktop/code/CoreferenceResolution/src/main/java/coreference/output.xml";

        FileWriter fileWriter = new FileWriter(outputFileName);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        
        
        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        pipeline.xmlPrint(document, fileWriter);
    }
    
    
}
