import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CodeAnalyzer {

    /**
     * Analyze all lines in a Java file counting lines, comment lines, single line
     * comments, lines inside a block comment, and TODOs.
     *
     * @param A file choses by the user in Main.java
     * @return A string containing the code analysis in English.
     */
    public String countLines(File file) {
        BufferedReader reader;
        String line = "";
        boolean inBlockComment = false;

        int lineCount = 0;
        int singleLineCommentCount = 0;
        int lineInBlockCommentsCount = 0;
        int blockCommentCount = 0;
        int todoCount = 0;

        try { // Try catch to handle file IO errors.
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while (line != null) {
                if (inBlockComment) {
                    if (line.contains("*/")) {
                        inBlockComment = false; // Checks if we are at the end of a multi-line block comment.
                    }
                    lineInBlockCommentsCount++;
                } else {
                    if (hasSingleLineComment(line)) {
                        singleLineCommentCount++; // Checks if we have a single line comment.
                        if (hasTODO(line)) {
                            todoCount++; // Checks TODO after checking single line because TODO can only occur inside a
                                         // single line comment.
                        }
                    } else if (hasSingleLineBlockComment(line)) {
                        lineInBlockCommentsCount++;
                        blockCommentCount++; // Checks if we have a block comment on one line.
                    } else if (atStartOfBlockComment(line)) {
                        blockCommentCount++;
                        lineInBlockCommentsCount++;
                        inBlockComment = true; // Checks if we are at the start of a multi-line block comment.
                    }
                }
                lineCount++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Total # of lines: " + lineCount + "\nTotal # of comment lines:"
                + (singleLineCommentCount + lineInBlockCommentsCount) + "\nTotal # of single line comments: "
                + singleLineCommentCount + "\nTotal # of comment lines within block comments: "
                + lineInBlockCommentsCount + "\nTotal number of block line comments: " + blockCommentCount
                + "\nTotal number of TODOs: " + todoCount;
    }

    /**
     * Checks the current line to see if there is a valid single line comment. A
     * valid single line comment starts with "//" and is not inside a String.
     * 
     * @param A String line to analyze
     * @return A boolean determining if this line has a valid single line comment.
     */
    public boolean hasSingleLineComment(String line) {
        if (!line.contains("//")) {
            return false; // If a "//" does not exist at all, return false to not waste any more runtime.
        }
        boolean hasDoubleQuotes = false;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == '\"') {
                if (hasDoubleQuotes) {
                    hasDoubleQuotes = false; // If we encounter an open double-quote, our next found "//" would not
                                             // count as it would be in a String.
                } else {
                    hasDoubleQuotes = true;
                }
            }
            if ((line.charAt(i) == '/' && line.charAt(i + 1) == '/') && !hasDoubleQuotes) {
                return true; // If we encountered a "//" and it is not within quotes, it is a valid comment.
            }
        }
        return false;
    }

    /**
     * Checks the current line to see if there is a valid single line block comment.
     * A valid single line comment starts with "/*", ends with "*\/" on the same
     * line, and is not inside a String.
     * 
     * @param A String line to analyze
     * @return A boolean determining if this line has a valid single block comment.
     */
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

    /**
     * Checks the current line to see if there is a start of a multi-line block
     * comment. A valid start is a line that contains "/*" and is not inside a
     * String.
     *
     * 
     * @param A String line to analyze
     * @return A boolean determining if this line has a start to a multi-line block
     *         comment.
     */
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

    /**
     * Checks the current line to determine if there is a valid "TODO" annotation. A
     * valid TODO annotation exists within a single line comment and is not inside a
     * String.
     * 
     * @param A String line to analyze
     * @return A boolean determining if this line has a valid single block comment.
     */
    public boolean hasTODO(String line) {
        if (!line.contains("TODO")) {
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