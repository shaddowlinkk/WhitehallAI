import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ClueMarke extends JPanel {
    private int xPosition;
    private int yPosition;
    private int ovalWidth;
    private int ovalHeight;

    public ClueMarke(int xPos, int yPos, int width, int height) {
        xPosition = xPos;
        yPosition = yPos;
        ovalWidth = width;
        ovalHeight = height;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.drawOval(xPosition, yPosition, ovalWidth, ovalHeight);


    }

}