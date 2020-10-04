import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Capital One Coding Assessment Main.java Purpose: Opens a Java file to be
 * analyzed, prints analysis to command line.
 *
 * @author Noah Mifsud-Lattari
 * @version 1.0
 */
class Main {
    public static void main(String[] args) {
        File currentFile;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java files", "java");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Choose a file to analyze");
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            CodeAnalyzer analyzer = new CodeAnalyzer();
            String result = analyzer.countLines(currentFile);
            System.out.println(result);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            System.exit(0);
        }

    }
}