/*
  工具栏类
 */
import javax.swing.*;

class Tools {
    public JButton selectButton = new JButton("选择");
    public JButton ovalButton = new JButton("椭圆形");
    public JButton circle = new JButton("圆形");
    public String[] lineSize = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    public JButton rectangleButton = new JButton("方形");
    public JButton lineButton = new JButton("画笔");
    public JComboBox<String> strokeBox = new JComboBox<>(lineSize);
    public JButton colorButton = new JButton("颜色");
    public JButton segmentButton = new JButton("直线");
    public JButton textButton = new JButton("文字");
    public JButton clearButton = new JButton("清屏");
    public JTextField textField = new JTextField("在此输入您想添加的文字");
    public JButton fontButton = new JButton("字体");
    public JButton undoButton = new JButton("撤销");
    public JButton redoButton = new JButton("重做");
    public JCheckBox fillCheckBox = new JCheckBox("填充");
    public JCheckBox deleteCheckBox = new JCheckBox("删除");


    public Tools() {
    }
}