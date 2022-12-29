import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    public boolean White;
    public boolean Black;
    public boolean Empty;
    public boolean WhiteQueen;
    public boolean BlackQueen;
    public boolean Current;

    public Field() {
        White = false;
        Black = false;
        Empty = true;
        Current = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Current) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        if (White) {
            g.setColor(Color.WHITE);
            g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        }
        if (Black) {
            g.setColor(Color.BLACK);
            g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        }
        if (BlackQueen) {
            g.setColor(Color.BLACK);
            g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
            g.setColor(Color.white);
            g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
        }
        if (WhiteQueen) {
            g.setColor(Color.WHITE);
            g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
            g.setColor(Color.black);
            g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
        }
    }
}
