package softwaredesign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//TODO: CLEAN COMMENTS OUT OF CLASS
public class FileReader {

    public String readFile(String fileName) throws IOException {
        //System.out.println("getResourcesAsStream : " + fileName);
        InputStream is = getFileFromResourceAsStream(fileName);
        String output = inputStreamToString(is);
        return output;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static String inputStreamToString(InputStream i) throws IOException {
        String fileContent = "";
        BufferedReader r = new BufferedReader(new InputStreamReader(i));
        String l;
        while((l = r.readLine()) != null) {
            //System.out.println("LINE: "+l);
            fileContent += l + "\n";
        }
        i.close();
        return fileContent;
    }
}
