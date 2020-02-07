import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JPanel;
public class Board extends javax.swing.JFrame {
    private int y;
    private int x;
    Image img = Toolkit.getDefaultToolkit().getImage("board.jpg");
    public Board() throws IOException {
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0,getWidth(),getHeight(),this);
                darwC(g,x,y,25);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                System.out.println(x+","+y);
                repaint();
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
        setPreferredSize(new Dimension(900,900));
        pack();
        setVisible(true);

    }
    public void darwC(Graphics g,int x,int y,int r){
         x = x-(r/2);
         y = y-(r/2);
        g.fillOval(x,y,r,r);
        g.drawOval(x,y,r,r);
    }

    public static void main(String[] args) throws Exception {
        new Board();
    }
}