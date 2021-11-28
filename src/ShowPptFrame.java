import javax.swing.*;
import java.util.Vector;

/**
 * @author gongzhaoxu
 * @create 2021-11-26-20:21
 **/
public class ShowPptFrame {

    public ShowPptFrame() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        Vector<PaintPanel> b = new Vector<PaintPanel>();

        System.out.println("播放窗口构造函数被调用了");
        tabbedPane= EditPptFrame.tabbedPane;
        frame.add(tabbedPane);
        frame.setTitle("PPT播放");
        frame.setContentPane(tabbedPane);
        frame.setVisible(true);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void showAll() {
        System.out.println("播放了ppt");
//        setContentPane(panel);
    }
}
