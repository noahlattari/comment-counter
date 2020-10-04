import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CodeAnalyzer {
    public CodeAnalyzer() {

    }

    public String countLines(File file) {
        BufferedReader reader;
        String currentLine = "";
        int lineCount = 0;
        int singleLineCommentCount = 0;
        int lineInBlockCommentsCount = 0;
        int blockCommentCount = 0;
        int todoCount = 0;
        boolean inBlockComment = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            currentLine = reader.readLine();
            while (currentLine != null) {
                if (inBlockComment) {
                    if (currentLine.contains("*/")) {
                        inBlockComment = false;
                    }
                    lineInBlockCommentsCount++;
                } else {
                    if (hasSingleLineComment(currentLine)) {
                        singleLineCommentCount++;
                        if (hasTODO(currentLine)) {
                            todoCount++;
                        }
                    } else if (hasSingleLineBlockComment(currentLine)) {
                        lineInBlockCommentsCount++;
                        blockCommentCount++;
                    } else if (atStartOfBlockComment(currentLine)) {
                        blockCommentCount++;
                        lineInBlockCommentsCount++;
                        inBlockComment = true;
                    }
                }
                lineCount++;
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Total # of lines: " + lineCount + "\nTotal # of comment lines:"
                + (singleLineCommentCount + lineInBlockCommentsCount) + "\nTotal # of single line comments: "
                + singleLineCommentCount + "\nTotal # of comment lines within block comments: "
                + lineInBlockCommentsCount + "\nTotal number of block line comments: " + blockCommentCount
                + "\nTotal number of TODOs: " + todoCount;
    }

    public boolean hasSingleLineComment(String line) {
        if (!line.contains("//")) {
            return false;
        }
        boolean hasDoubleQuotes = false;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == '\"') {
                if (hasDoubleQuotes) {
                    hasDoubleQuotes = false;
                } else {
                    hasDoubleQuotes = true;
                }
            }
            if ((line.charAt(i) == '/' && line.charAt(i + 1) == '/') && !hasDoubleQuotes) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSingleLineBlockComment(String line) {
        if (!line.contains("/*")) {
            return false;
        }
        for (int i = 0; i < line.length() - 1; i++) {
            if ((line.charAt(i)) == '/' && line.charAt(i + 1) == '*' && line.substring(i + 1).contains("*/")) {
                return true;
            }
        }
        return false;
    }

    public boolean atStartOfBlockComment(String line) {
        if (!line.contains("/*")) {
            return false;
        }
        boolean hasDoubleQuotes = false;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == '\"') {
                if (hasDoubleQuotes) {
                    hasDoubleQuotes = false;
                } else {
                    hasDoubleQuotes = true;
                }
            }
            if ((line.charAt(i) == '/' && line.charAt(i + 1) == '*') && !hasDoubleQuotes) {
                return true;
            }
        }
        return false;
    }

    public boolean hasTODO(String line) {
        System.out.println(line);
        if (!line.contains("TODO")) {
            System.out.println("broken");
            return false;
        }
        boolean hasDoubleQuotes = false;
        for (int i = 0; i < line.length() - 3; i++) {
            if (line.charAt(i) == '\"') {
                if (hasDoubleQuotes) {
                    hasDoubleQuotes = false;
                } else {
                    hasDoubleQuotes = true;
                }
            }
            if (line.substring(i, i + 4).equals("TODO")) {
                return true;
            }
        }
        return false;
    }
}