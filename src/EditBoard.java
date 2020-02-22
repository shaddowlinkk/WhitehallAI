import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class EditBoard extends JPanel{

    private Image img = Toolkit.getDefaultToolkit().getImage("board.jpg");
    private int y;
    private int x;
    private ArrayList<Point> pointso = new ArrayList<Point>();
    private ArrayList<Point> pointsi = new ArrayList<Point>();
    private DataIO data;
    private Editing ed = new Editing(data);
    private int type;
    private int mode = 2;
    private boolean imported = false;
    private boolean openE= false;
    private int selected,root;
    private Point reset;
    private ArrayList<Integer> conections = new ArrayList<Integer>();
    private ArrayList<Integer> conn = new ArrayList<Integer>();

    public EditBoard(JFrame f) {
        data = new DataIO();
        type = 1;
        f.addMouseListener(new MouseAdapter() {

            @Override
            /*
            Used for selecting the node on the graph
             */
            public void mouseClicked(MouseEvent e) {
                /*
                Click events for modes(only work if not moving node)
                1: places node down
                2: selects a node
                 */
                if (!ed.getMove()) {
                    if (mode == 1) {
                        x = e.getX();
                        y = e.getY();
                        repaint();
                    } else {
                        for (int i = 1; i <= pointsi.size(); i++) {
                            if (e.getX() - 8 <= pointsi.get(i - 1).x && e.getX() - 8 >= pointsi.get(i - 1).x - 11) {
                                if (e.getY() - 30 <= pointsi.get(i - 1).y && e.getY() - 30 >= pointsi.get(i - 1).y - 11) {
                                    selected = i;
                                    if (openE) {
                                        ed.setList(SArray(data.getLinks(selected)));
                                        if(data.getType(selected-1)==1){
                                            ed.setID(data.getID(selected-1)-174);
                                        }else
                                            ed.setID(data.getID(selected-1));
                                        ed.setType(data.getType(selected-1));
                                        ed.setVisible(true);
                                    }
                                    repaint();
                                }
                            }
                        }
                        for (int i = 1; i <= pointso.size(); i++) {
                            if (e.getX() - 24 <= pointso.get(i - 1).x && e.getX() - 24 >= pointso.get(i - 1).x - 25) {
                                if (e.getY() - 50 <= pointso.get(i - 1).y && e.getY() - 50 >= pointso.get(i - 1).y - 25) {
                                    selected = i + 174;
                                    if (openE) {
                                        ed.setList(SArray(data.getLinks(selected)));
                                        if(data.getType(selected-1)==1){
                                            ed.setID(data.getID(selected-1)-174);
                                        }else
                                            ed.setID(data.getID(selected-1));
                                        ed.setType(data.getType(selected-1));
                                        ed.setVisible(true);
                                    }
                                    repaint();
                                }
                            }
                        }
                    }
                }
                /*
                Click Events if moving a node with node editor
                 */
                else {
                    ed.stopMove();
                }
            }
        });

       f.addMouseMotionListener(new MouseMotionAdapter() {
           @Override
           public void mouseMoved(MouseEvent e) {
               if(!ed.isVisible()&&!ed.getApply()&& reset!=null){
                   if(type==1) {
                       pointso.set(selected-175,reset);
                       //data.setPoints(selected,pointso.get(selected-175));
                   }else {
                       pointsi.set(selected-1, reset);
                       //data.setPoints(selected,pointsi.get(selected-1));
                   }
                   reset=null;
                   repaint();
               }else if(!ed.isVisible()&&ed.getApply()&& reset!=null){
                   if(type==1) {
                       data.setPoints(selected,pointso.get(selected-175));
                   }else {
                       data.setPoints(selected,pointsi.get(selected-1));
                   }
                   reset=null;
                   repaint();
               }
               if (ed.getMove()){
                   if(type==1) {
                       pointso.set(selected-175,new Point(e.getX() - 16, e.getY() - 34));
                   }else {
                       pointsi.set(selected-1, new Point(e.getX() - 8, e.getY() - 30));
                   }
                   repaint();
               }
           }
       });
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                /*
                Mode 1 Key Operations
                1: mark current location as an instants of node type X and specify number of connections
                2: changes the type of node
                 */
                if(e.getKeyCode()==KeyEvent.VK_1&&mode==1) {
                    if(type==1) {
                        pointso.add(new Point(x - 16, y - 34));
                        int ID = pointso.size()+174;
                        e.consume();
                        int conn = Integer.parseInt(JOptionPane.showInputDialog("Enter Node #("+(pointso.size())+") Conections"));
                        e.consume();
                        data.addData(ID, conn, type, x-16, y-34);
                        repaint();
                    }
                    else {
                        pointsi.add(new Point(x - 8, y - 30));
                        int ID =pointsi.size();
                        e.consume();
                        int conn = Integer.parseInt(JOptionPane.showInputDialog("Enter Node #("+pointsi.size()+") Conections"));
                        e.consume();
                        data.addData(ID, conn, type, x-8, y-30);
                        repaint();

                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_2&&mode==1){
                    type = type==1 ? 2:1;

                }
                /*
                Universal Key Operations
                4: Export data to data file
                5: import data from data file
                6: switch between modes
                 */

                else if (e.getKeyCode()==KeyEvent.VK_4 && imported){
                    System.out.println("Export data");
                    try(FileWriter file = new FileWriter("Data.data")){
                        file.write(data.getJson());
                    }catch (IOException es){
                        System.out.println("Failed to Write");
                    }
                } else if (e.getKeyCode()==KeyEvent.VK_5 && !imported){
                    //TODO MOVE THIS TO DataIO
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
                            data.addData(ID, conn, type, x, y,(JSONArray) indata.get("Links"));
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
                }

                /*
                Mode 2 Key Operations
                1: opens the node editor
                2: sets selected as root for connections
                3: sets selected node as a connected node to the root node
                4-6: same as be for universal keys
                7: show selected nodes connected nodes
                8: stop showing nodes connections
               Enter: sets root nodes connections to data object
                 */
                else if(e.getKeyCode()== KeyEvent.VK_1 && mode==2 && selected!=0){
                    //TODO add ability to modify all data
                    openE=true;
                    ed.setData(data);
                    if(type==1) {
                        reset=pointso.get(selected-175);
                    }else {
                        reset=pointsi.get(selected-1);
                    }
                    ed.setList(SArray(data.getLinks(selected)));
                    if(data.getType(selected-1)==1){
                        ed.setID(data.getID(selected-1)-174);
                    }else
                        ed.setID(data.getID(selected-1));
                    ed.setType(data.getType(selected-1));
                    ed.setVisible(true);
                }else if (e.getKeyCode() == KeyEvent.VK_2 && mode ==2){
                    root=selected;
                    System.out.println(root);
                }else if (e.getKeyCode() == KeyEvent.VK_3 && mode ==2 && !conections.contains(selected)&& selected!= root){
                    conections.add(selected);
                    System.out.println(Arrays.toString(conections.toArray()));
                }else if (e.getKeyCode() == KeyEvent.VK_ENTER && mode==2){
                    System.out.println(root);
                    data.addLinks(root,conections);
                    root=0;
                    conections.clear();
                    repaint();
                }else if (e.getKeyCode() == KeyEvent.VK_7 && mode ==2){
                    conections= data.getLinks(selected);
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

            }else if(data.hasLinks(i)){
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
            }else if(data.hasLinks(i+174)){
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
