package softwaredesign;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public String readFile(String file_name) {
        String fileContent = "";
        try {
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContent += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        fileContent = fileContent.substring(0, fileContent.length() - 1);
        return fileContent;
    }
}
