import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CodeAnalyzer {
    public CodeAnalyzer() {
        String d = "dsss//sdsdsdsds";
        System.out.println(d);
    }

    public String countLines(File file) {
        BufferedReader reader;
        String currentLine = "";
        int lineCount = 0;
        int singleLineCommentCount = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            currentLine = reader.readLine();
            while (currentLine != null) {
                // System.out.println(currentLine);
                lineCount++;
                if (hasSingleLineComment(currentLine)) {
                    singleLineCommentCount++;
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Line Count: " + lineCount + "Single Line Comments: " + singleLineCommentCount;
    }

    public boolean hasSingleLineComment(String line) {
        if (!line.contains("//")) {
            return false;
        } else {

        }
        boolean hasDoubleQuotes = false;
        for (int i = 0; i < line.length() - 1; i++) {
            if (!hasDoubleQuotes && line.charAt(i) == '\"') {
                hasDoubleQuotes = !hasDoubleQuotes;
            }

            if ((line.charAt(i) == '/' && line.charAt(i + 1) == '/') && !hasDoubleQuotes) {
                return true;
            }
        }
        return false;
    }
}