import javax.swing.*;
import java.awt.*;

public class RunBoard extends JPanel {

     private Image img = Toolkit.getDefaultToolkit().getImage("board.jpg");
     public RunBoard(JFrame mainFrame){
     }

     public void paintComponent(Graphics g){
         g.drawImage(img, 0, 0,getWidth(),getHeight(),this);

     }
}
