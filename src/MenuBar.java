import javax.swing.*;

public class MenuBar extends JMenuBar {
    public JLabel label=new JLabel("                                                              " +
            "                                                       " +
            "                                                       " +
            "                                                       " +
            "                        " +
            "此软件由汪亦可、巩钊旭共同制作完成");
    public JMenu fileMenu = new JMenu("文件");
    public JMenu editMenu = new JMenu("编辑");
    public JMenu helpMenu = new JMenu("联系我们");
    public JMenuItem formMenu = new JMenu("格式");
    public JMenuItem newItem = new JMenuItem("新建");
    public JMenuItem saveItem = new JMenuItem("保存");
    public JMenuItem openItem = new JMenuItem("打开");
    public JMenuItem aboutItem = new JMenuItem("关于");
    public JMenuItem undoItem = new JMenuItem("撤销");
    public JMenuItem redoItem = new JMenuItem("重做");
    public JMenuItem fontItem = new JMenuItem("字体");
    public JMenuItem colorItem = new JMenuItem("颜色");

    public MenuBar() {
        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.add(openItem);

        helpMenu.add(aboutItem);

        editMenu.add(undoItem);
        editMenu.add(redoItem);

        formMenu.add(fontItem);
        formMenu.add(colorItem);

        add(fileMenu);
        add(editMenu);
        add(formMenu);
        add(helpMenu);
        add(label);
    }
}
