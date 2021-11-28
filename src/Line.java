/*
  直线类，继承自Shape类
 */
import java.awt.*;


public class Line extends Shape {

    protected Line(Color color1, int startX1, int startY1, int endX1, int endY1, float stroke1) {
        super(color1, startX1, startY1, endX1, endY1, stroke1, false);
    }

    protected Line(Color color, int x, int y, float stroke1) {
        super(color, x, y, stroke1, false);
    }


    @Override
    public void draw(Graphics2D g2) {
        if(isDeleted) {return;}
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawLine(startX, startY, endX, endY);
    }
    /**
     * 直线无法选中
     */
    @Override
    public boolean contain(int x, int y) { return false; }

}