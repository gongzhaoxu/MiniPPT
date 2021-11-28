import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 *圆形
 */
public class Circle extends Shape {

    public Circle(Color color, int x, int y, float stroke1, boolean isFilled) {
        super(color, x, y, stroke1, isFilled);
    }

    @Override
    public void draw(Graphics2D g2) {
        System.out.print("");
        if (isDeleted){ return;}
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(stroke));
        if(isFilled) {
            g2.fillOval(nwX, nwY, Math.abs(deltaX), Math.abs(deltaX));
        } else {
            g2.drawOval(nwX, nwY, Math.abs(deltaX), Math.abs(deltaX));
        }
    }

    /**
     * contains函数判断坐标(x,y)是否在此Circle中
     */
    @Override
    public boolean contain(int x, int y) {
        Ellipse2D e1 = new Ellipse2D.Double(nwX - stroke, nwY - stroke, Math.abs(deltaX) + 2 * stroke, Math.abs(deltaX) + 2 * stroke);
        Ellipse2D e2 = new Ellipse2D.Double(nwX + stroke, nwY + stroke, Math.abs(deltaX) - 2 * stroke, Math.abs(deltaX) - 2 * stroke);
        if(isFilled) {
            return e1.contains(x, y);
        } else {
            return e1.contains(x, y) && !e2.contains(x, y);
        }
    }

}