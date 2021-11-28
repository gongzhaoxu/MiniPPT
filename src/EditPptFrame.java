/*
  编辑PPT主窗口
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class EditPptFrame extends JFrame implements Serializable {
    private static int pageIndex = 2;
    private static int selectedIndex = 0;
    private PaintPanel board;
    private Tools tool;
    private JToolBar toolBar;
    private MenuBar menu;
    private ChooseFontDialog fontDialog;
    private FileIO fileIO;
    public static Vector<PaintPanel> boards = new Vector<>();
    public static Vector<PaintPanel> showboards = new Vector<>();
    public static JTabbedPane tabbedPane;


    public EditPptFrame() {
        setBackground(Color.white);
        board = new PaintPanel();
        board.setVisible(false);
        fontDialog = new ChooseFontDialog(this);
        fileIO = new FileIO();
        tool = new Tools();
        toolBar = new JToolBar();
        menu = new MenuBar();
        toolBar.setSize(new Dimension(700, 30));

        setSize(1500, 1000);

        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        tabbedPane.setAutoscrolls(true);

        PaintPanel b = new PaintPanel();

        boards.add(b);
        showboards.add(b);
        tabbedPane.addTab("第1页", b);
        this.setContentPane(tabbedPane);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                board = (PaintPanel) tabbedPane.getSelectedComponent();
                selectedIndex = tabbedPane.getSelectedIndex();
                System.out.println("selectedIndex=" + selectedIndex);
            }
        });


        tool.selectButton.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.SELECT));
        tool.ovalButton.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.OVAL));
        tool.circle.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.CIRCLE));
        tool.rectangleButton.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.RECTANGLE));
        tool.lineButton.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.LINE));
        tool.colorButton.addActionListener(actionEvent -> board.setFore(JColorChooser.showDialog(null, "调色板", Color.BLACK)));
        tool.strokeBox.addActionListener(actionEvent -> board.setStroke(tool.strokeBox.getSelectedIndex() + 1));
        tool.segmentButton.addActionListener(actionEvent -> board.setTool(PaintPanel.tools.SEGMENT));
        tool.textButton.addActionListener(actionEvent -> {
            board.setCurrentText(tool.textField.getText());
            board.setTool(PaintPanel.tools.TEXT);
        });
        tool.clearButton.addActionListener(actionEvent -> board.clear());
        tool.textField.addActionListener(actionEvent -> board.setCurrentText(tool.textField.getText()));
        tool.fontButton.addActionListener(actionEvent -> {
            fontDialog.setVisible(true);
            board.setFont(fontDialog.getFont());
        });
        tool.fillCheckBox.addActionListener(actionEvent -> board.setFill(tool.fillCheckBox.isSelected()));
        tool.deleteCheckBox.addActionListener(actionEvent -> board.setDelete(tool.deleteCheckBox.isSelected()));

        tool.undoButton.addActionListener(actionEvent -> board.undo());
        tool.redoButton.addActionListener(actionEvent -> board.redo());

        menu.newItem.addActionListener(actionEvent -> {
            if (board.isSaved()) {
                board.clear();
            } else {
                int op = JOptionPane.showConfirmDialog(null, "您还没有保存，是否保存？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    fileIO.saveFile(null, boards);
                    board.clear();
                } else if (op == JOptionPane.NO_OPTION) {
                    board.clear();
                }
            }
        });
        menu.saveItem.addActionListener(actionEvent -> fileIO.saveFile(this, boards));
        menu.openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boards.clear();
                showboards.clear();
                System.out.println("画板向量清零完成");
                pageIndex = 1;
                System.out.println("tabbedpanel清零完成");
                //获取文件选择器获取到的文件，同时更新文件个数供之后使用
                tabbedPane.removeAll();
                File[] files = fileIO.openFile(tabbedPane);
                System.out.println("您一共选择了" + files.length + "个文件");
                for (int i = 0; i < files.length; i++) {
                    PaintPanel p = new PaintPanel();
                    boards.add(p);
                    showboards.add(p);
                    System.out.println("创建了第" + i + "个画板");
                    boards.get(i).readImage(files[i]);
                }
                for (int i = 0; i < boards.size(); i++) {
                    tabbedPane.addTab("第" + pageIndex++ + "页", boards.get(i));
                }

            }
        });

        menu.aboutItem.addActionListener(actionEvent ->
                JOptionPane.showMessageDialog(null, "计算机1904汪亦可\n计算机1904巩钊旭", "关于我们", JOptionPane.INFORMATION_MESSAGE));
        menu.fontItem.addActionListener(actionEvent -> {
            fontDialog.setVisible(true);
            board.setFont(fontDialog.getFont());
        });
        menu.colorItem.addActionListener(actionEvent -> board.setFore(JColorChooser.showDialog(null, "调色板", Color.BLACK)));
        menu.redoItem.addActionListener(actionEvent -> board.redo());
        menu.undoItem.addActionListener(actionEvent -> board.undo());

        JButton createNewPage = new JButton("新增一页");
        createNewPage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("新建了一页ppt");
                PaintPanel b = new PaintPanel();
                boards.add(b);
                showboards.add(b);
                tabbedPane.addTab("第" + (pageIndex++) + "页", b);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



        JButton showPPT = new JButton("播放");
        showPPT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ShowPptFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        add(board, FlowLayout.LEFT);




        toolBar.add(showPPT);
        toolBar.add(createNewPage);
        toolBar.add(tool.undoButton);
        toolBar.add(tool.redoButton);
        toolBar.add(tool.selectButton);
        toolBar.add(tool.clearButton);
        toolBar.add(tool.deleteCheckBox);
        toolBar.add(tool.colorButton);
        toolBar.add(tool.strokeBox);
        toolBar.add(tool.fillCheckBox);

        toolBar.add(tool.lineButton);
        toolBar.add(tool.segmentButton);
        toolBar.add(tool.rectangleButton);
        toolBar.add(tool.circle);
        toolBar.add(tool.ovalButton);

        toolBar.add(tool.textButton);
        toolBar.add(tool.fontButton);
        toolBar.add(tool.textField);




        JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        top.setLeftComponent(menu);
        top.setRightComponent(toolBar);
        top.setDividerSize(1);
        top.setDividerLocation(30);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(top);
        splitPane.setRightComponent(tabbedPane);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(60);
        setContentPane(splitPane);

        /*
          关闭窗口时检查是否已经保存
         */
        addWindowListener(new MyWindowAdapter());
    }

    private class MyWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            super.windowClosing(windowEvent);
            if (board.isSaved()) {
                System.exit(0);
            } else {
                int op = JOptionPane.showConfirmDialog(null, "您还没有保存，是否保存？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    fileIO.saveFile(null, boards);
                    System.exit(0);
                } else if (op == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        }
    }
}

