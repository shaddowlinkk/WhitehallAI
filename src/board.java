import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import com.github.cliftonlabs.json_simple.*;

public class board extends JPanel{
    private int y;
    private int x;
    private ArrayList<Point> pointso = new ArrayList<Point>();
    private ArrayList<Point> pointsi = new ArrayList<Point>();
    private DataIO d;
    private int type;

    public board(JFrame f) {
        d = new DataIO();
        type = 1;
        f.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                x=e.getX();
                y=e.getY();
                repaint();
            }
        });
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_1) {
                    pointso.add(new Point(x - 16, y - 34));
                    int ID = Integer.parseInt(JOptionPane.showInputDialog("Enter Node ID"));
                    int conn = Integer.parseInt(JOptionPane.showInputDialog("Enter Node Conections"));
                    d.addData(ID,conn,type,x,y);
                    repaint();
                }
                else if (e.getKeyCode()==KeyEvent.VK_2){
                    System.out.println(type);
                    System.out.println(d.getJson().toJson());
                }
                else if(e.getKeyCode()==KeyEvent.VK_3){
                    type = type==1 ? 2:1;

                }
            }
        });
    }
    Image img = Toolkit.getDefaultToolkit().getImage("board.jpg");
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0,getWidth(),getHeight(),this);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.fillOval(x-(16),y-(34),25,25);
        g2.setColor(Color.blue);
        for (Point point : pointso) {
            g2.fillOval(point.x, point.y, 25, 25);
        }
    }
}
/* addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(x+","+y);

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
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {


            }
                System.out.println("pressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });*/