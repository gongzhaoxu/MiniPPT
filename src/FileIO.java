import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class FileIO {

    public static File[] files;
    public static int FileNumber;

    private static JFileChooser saveFileChooser = new JFileChooser();
    private static JFileChooser openFileChooser = new JFileChooser();

    private static FileNameExtensionFilter savFilter = new FileNameExtensionFilter("SAV文件", "sav");
    private static FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG图像", "jpg");

    public FileIO() {
        saveFileChooser.setFileFilter(savFilter);
        saveFileChooser.setFileFilter(jpgFilter);


        openFileChooser.setFileFilter(savFilter);
//        openFileChooser.setFileFilter(jpgFilter);
//        openFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFileChooser.setMultiSelectionEnabled(true);
    }

    public File saveFile(Component c, Vector<PaintPanel> boards) {
        saveFileChooser.showSaveDialog(c);
        File file = saveFileChooser.getSelectedFile();
        if (saveFileChooser.getFileFilter() == jpgFilter) {
            for (int i = 0; i < EditPptFrame.boards.size(); i++) {
                file = new File(file.getAbsoluteFile() + ".jpg");
                try {
                    ImageIO.write(boards.get(i).getImage(), "jpg", file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(c, "保存出错！请重试！");
                    e.printStackTrace();
                }
            }
        } else if (saveFileChooser.getFileFilter() == savFilter) {
            if (!file.getName().endsWith(".sav")) {

                for (int i = 0; i < EditPptFrame.boards.size(); i++) {
                    file = new File(file.getAbsoluteFile() + ".sav");
                    boards.get(i).writeImage(file);
                }
            }
        }
        return file;
    }

    public File[] openFile(Component c) {
        openFileChooser.showOpenDialog(c);
        files = openFileChooser.getSelectedFiles();
        FileNumber = files.length;
        return openFileChooser.getSelectedFiles();
    }
}
