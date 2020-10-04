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
            currentFile = chooser.getSelectedFile();
            CodeAnalyzer analyzer = new CodeAnalyzer();
            String result = analyzer.countLines(currentFile);
            System.out.println(result);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            System.exit(0);
        }

    }
}