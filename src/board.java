import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class board extends JPanel{
    private int y;
    private int x;
    private ArrayList<Point> pointso = new ArrayList<Point>();
    private ArrayList<Point> pointsi = new ArrayList<Point>();
    private DataIO d;
    private int type;
    private int mode = 2;
    private boolean imported = false;
    private int selected,root;
    private ArrayList<Integer> conections = new ArrayList<Integer>();
    private ArrayList<Integer> conn = new ArrayList<Integer>();

    public board(JFrame f) {
        d = new DataIO();
        type = 1;
        f.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(mode==1) {
                    x = e.getX();
                    y = e.getY();
                    repaint();
                }else{
                    for (int i =1;i<=pointsi.size();i++){
                        if(e.getX()-8<=pointsi.get(i-1).x&&e.getX()-8>=pointsi.get(i-1).x-11){
                            if(e.getY()-30<=pointsi.get(i-1).y&&e.getY()-30>=pointsi.get(i-1).y-11){
                                selected=i;
                                repaint();
                            }
                        }
                    }
                    for (int i =1;i<=pointso.size();i++) {
                        if (e.getX() - 24 <= pointso.get(i - 1).x && e.getX() - 24 >= pointso.get(i - 1).x - 25) {
                            if (e.getY() - 50 <= pointso.get(i - 1).y && e.getY() - 50 >= pointso.get(i - 1).y - 25) {
                                selected = i + 174;
                                repaint();
                            }
                        }
                    }
                }
            }
        });
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_1&&mode==1) {
                    if(type==1) {
                        pointso.add(new Point(x - 16, y - 34));
                        int ID = pointso.size()+174;
                        e.consume();
                        int conn = Integer.parseInt(JOptionPane.showInputDialog("Enter Node #("+(pointso.size())+") Conections"));
                        e.consume();
                        d.addData(ID, conn, type, x-16, y-34);
                        repaint();
                    }
                    else {
                        pointsi.add(new Point(x - 8, y - 30));
                        int ID =pointsi.size();
                        e.consume();
                        int conn = Integer.parseInt(JOptionPane.showInputDialog("Enter Node #("+pointsi.size()+") Conections"));
                        e.consume();
                        d.addData(ID, conn, type, x-8, y-30);
                        repaint();

                    }
                }
                else if (e.getKeyCode()==KeyEvent.VK_2&&mode==1){
                    System.out.println(type);
                    System.out.println(d.getJson());
                }
                else if(e.getKeyCode()==KeyEvent.VK_3&&mode==1){
                    type = type==1 ? 2:1;

                }else if (e.getKeyCode()==KeyEvent.VK_4 && imported){
                    System.out.println("Export data");
                    try(FileWriter file = new FileWriter("Data.data")){
                        file.write(d.getJson());
                    }catch (IOException es){
                        System.out.println("Failed to Write");
                    }
                }else if (e.getKeyCode()==KeyEvent.VK_5 && !imported){
                    System.out.println("importing data");
                    try{
                        FileReader read = new FileReader("Data.data");
                        BufferedReader bread = new BufferedReader(read);
                        String line="";
                        while ((line =bread.readLine())!= null) {
                            JSONObject indata= (JSONObject) new JSONParser().parse(line);
                            JSONArray inpoints = (JSONArray) indata.get("Points");
                            int ID=(int)(long)indata.get("ID");
                            int conn=(int)(long)indata.get("Connections");
                            int type=(int)(long)indata.get("Type");
                            int x=(int)(long)inpoints.get(0);
                            int y=(int)(long)inpoints.get(1);
                            d.addData(ID, conn, type, x, y,(JSONArray) indata.get("Links"));
                            if(type==1){
                                pointso.add(new Point(x,y));
                            }else if(type==2){
                                pointsi.add(new Point(x,y));
                            }
                        }
                        bread.close();
                        imported=true;
                        repaint();
                    }catch (Exception es){
                        es.printStackTrace();
                    }
                }else if (e.getKeyCode()==KeyEvent.VK_6){
                    mode = mode==1 ? 2:1;
                }else if(e.getKeyCode()== KeyEvent.VK_1 && mode==2 && selected!=0){
                    editing ed = new editing(SArray(d.getLinks(selected)),d.getID(selected),d.getType(selected));
                    ed.setVisible(true);
                }else if (e.getKeyCode() == KeyEvent.VK_2 && mode ==2){
                    root=selected;
                    System.out.println(root);
                }else if (e.getKeyCode() == KeyEvent.VK_3 && mode ==2 && !conections.contains(selected)&& selected!= root){
                    conections.add(selected);
                    System.out.println(Arrays.toString(conections.toArray()));
                }else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println(root);
                    d.addLinks(root,conections);
                    root=0;
                    conections.clear();
                    repaint();
                }else if (e.getKeyCode() == KeyEvent.VK_7 && mode ==2){
                    conections=d.getLinks(selected);
                    repaint();
                    System.out.println(Arrays.toString(conections.toArray()));
                }
                else if (e.getKeyCode() == KeyEvent.VK_8 && mode ==2){
                    conections.clear();
                    repaint();
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
        if(type==1) {
            g2.fillOval(x - (16), y - (34), 25, 25);
        }else{
            g2.fillRect(x-8,y-30,10,10);
        }
        g2.setColor(Color.blue);
        for (int i =1;i<=pointsi.size();i++) {
            if (i == selected|| conections.contains(i)) {
                g2.setColor(Color.MAGENTA);
                g2.fillRect(pointsi.get(i - 1).x, pointsi.get(i - 1).y, 10, 10);
                g2.setColor(Color.blue);

            }else if (i == root) {
                g2.setColor(Color.yellow);
                g2.fillRect(pointsi.get(i - 1).x, pointsi.get(i - 1).y, 10, 10);
                g2.setColor(Color.blue);

            }else if(d.hasLinks(i)){
                g2.setColor(Color.red);
                g2.fillRect(pointsi.get(i - 1).x, pointsi.get(i - 1).y, 10, 10);
                g2.setColor(Color.blue);
            }else{
                g2.fillRect(pointsi.get(i - 1).x, pointsi.get(i - 1).y, 10, 10);
            }
        }
        for (int i =1;i<=pointso.size();i++){

            if(i == (selected-174)|| conections.contains(i+174)){
                g2.setColor(Color.MAGENTA);
                g2.fillOval(pointso.get(i - 1).x, pointso.get(i - 1).y, 25, 25);
                g2.setColor(Color.blue);
            }else if(i == (root-174)){
                g2.setColor(Color.yellow);
                g2.fillOval(pointso.get(i - 1).x, pointso.get(i - 1).y, 25, 25);
                g2.setColor(Color.blue);
            }else if(d.hasLinks(i+174)){
                g2.setColor(Color.red);
                g2.fillOval(pointso.get(i - 1).x, pointso.get(i - 1).y, 25, 25);
                g2.setColor(Color.blue);
            }else {
                g2.fillOval(pointso.get(i - 1).x, pointso.get(i - 1).y, 25, 25);
            }
        }
    }
    public String[] SArray(ArrayList<Integer> l){
        String[] out = new String[l.size()];
        for (int i =0; i< out.length; i++){
            out[i]=Integer.toString(l.get(i));
        }
        return out;
    }
}
