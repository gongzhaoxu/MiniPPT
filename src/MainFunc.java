import java.awt.*;
import javax.swing.*;

public class MainFunc {
    public static void main(String[] args) {
        EditPptFrame editPptFrame = new EditPptFrame();
        editPptFrame.setExtendedState(Frame.NORMAL);
        editPptFrame.setTitle("PPT制作");
        editPptFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        editPptFrame.setLocationRelativeTo(null);
        editPptFrame.setVisible(true);
    }
}