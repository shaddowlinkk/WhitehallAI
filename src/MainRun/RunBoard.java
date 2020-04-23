package MainRun;

import Util.DataIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RunBoard extends JPanel {
     //TODO generate adjacency list
     private ArrayList<Point> pointso = new ArrayList<Point>();
     private ArrayList<Point> pointsi = new ArrayList<Point>();
     private DataIO data ;
     private Image img = Toolkit.getDefaultToolkit().getImage("board.jpg");
     private boolean imported = false;

     public RunBoard(JFrame mainFrame){
          data = new DataIO();
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
          try {
               pathing p = new pathing(data);
          }catch (IOException we){

          }

     }

     public void paintComponent(Graphics g){
         g.drawImage(img, 0, 0,getWidth(),getHeight(),this);

     }
}
