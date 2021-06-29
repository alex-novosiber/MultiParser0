package writeToFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TempFileWriter {


    public static void write(String writeToFile) throws IOException {
        String writeString = writeToFile;
        File outputFile = new File("tempOutput.txt");
        FileWriter writer = new FileWriter(outputFile);
        if (!outputFile.exists()) {
            System.out.println(" outputFile not exist \n");
            writer.write(writeString + "\n" + "- - - - - - - - - " + "\n");
        } else {
            System.out.println(" APPEND to outputFile  \n");
            writer.append(writeString + "\n" + "- - - - - - - - - " + "\n");
        }
        writer.close();


    }
}
