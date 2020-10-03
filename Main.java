import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

class Main {
    public static void main(String[] args) {
        File currentFile;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java files", "java");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            currentFile = chooser.getSelectedFile();
            CodeAnalyzer analyzer = new CodeAnalyzer();
            int lineCount = analyzer.countLines(currentFile);
            System.out.println(lineCount);
        }

    }
}