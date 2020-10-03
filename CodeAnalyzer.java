import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CodeAnalyzer {
    public CodeAnalyzer() {
        System.out.println("yo");
    }

    public int countLines(File file) {
        BufferedReader reader;
        String currentLine = "";
        int lineCount = 0;
        int commentCount = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            currentLine = reader.readLine();
            while (currentLine != null) {
                lineCount++;
                if (currentLine.contains("//")) {
                    commentCount++;
                } else if (currentLine.contains("/*")) {
                    commentCount++;
                    while (!currentLine.contains("*/") && !(currentLine = reader.readLine()).contains("*/"))
                        ;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
}