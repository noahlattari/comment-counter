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

        JFileChooser chooser = new JFileChooser(); // Java UI to pick a file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java files", "java");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Choose a file to analyze");
        int returnVal = chooser.showOpenDialog(null); // Determines if frame opened proeprly

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            CodeAnalyzer analyzer = new CodeAnalyzer();
            String result = analyzer.countLines(currentFile);
            System.out.println(result); // Reulting output in command line
        } else if (returnVal == JFileChooser.CANCEL_OPTION) { // If the user closes the frame
            System.exit(0); // Exit the application so app doesn't use any more resources
        }

    }
}