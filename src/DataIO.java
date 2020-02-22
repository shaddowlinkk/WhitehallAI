
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import sun.plugin.javascript.navig.Link;

import java.awt.*;
import java.util.ArrayList;

public class DataIO {
    private String data ="";
    private ArrayList<JSONObject> nodes = new ArrayList<>();
    public void DataIO(){

    }
    public void addData(int id, int conn,int type,int x, int y){
        JSONObject node = new JSONObject();
        JSONArray point = new JSONArray();
        node.put("ID",id);
        node.put("Connections",conn);
        node.put("Type",type);
        point.add(x);
        point.add(y);
        node.put("Points",point);
        node.put("Links",null);
        nodes.add(node);
    }

    public void addData(int id, int conn,int type,int x, int y,JSONArray Links){
        JSONObject node = new JSONObject();
        JSONArray point = new JSONArray();
        node.put("ID",id);
        node.put("Connections",conn);
        node.put("Type",type);
        point.add(x);
        point.add(y);
        node.put("Points",point);
        node.put("Links",Links);
        nodes.add(node);
    }
    public void addLinks(int root,ArrayList<Integer> links){
        JSONArray link =new JSONArray();
        JSONObject node = nodes.get(root-1);
        node.remove("Links");
        for (int i : links){
            link.add(i);
        }
        node.put("Links",link);
    }

    public void addLinks(int root,Integer[] links){
        JSONArray link =new JSONArray();
        JSONObject node = nodes.get(root-1);
        node.remove("Links");
        for (int i : links){
            link.add(i);
        }
        node.put("Links",link);
    }

    public ArrayList<Integer> getLinks(int selected){
        ArrayList<Integer> out = new ArrayList<Integer>();
        JSONObject node = nodes.get(selected-1);
        JSONArray links = (JSONArray) node.get("Links");
        if(links != null) {
            for (int i = 0; i < links.toArray().length; i++) {
                out.add(((int)(long) links.toArray()[i]));
            }
        }
        return out;
    }
    public boolean hasLinks(int selected){

        try {
            JSONObject node = nodes.get(selected - 1);
            JSONArray links = (JSONArray) node.get("Links");
            return (links != null);
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }
    public String getJson(){
        if (data.isEmpty()) {
            for (JSONObject o : nodes) {
                data = data + o.toJSONString() + "\n";
            }
        }
        return data;
    }
    public void setPoints(int index, Point p){
        JSONObject node =nodes.get(index-1);
        JSONArray tpoint = new JSONArray();
        node.remove("Points");
        tpoint.add(p.x);
        tpoint.add(p.y);
        node.put("Points",tpoint);
    }
    public void setType(int index,int type){
        JSONObject node =nodes.get(index);
        node.remove("Type");
        node.put("Type",type);
    }
    public void setID(int index,int id){
        JSONObject node =nodes.get(index);
        node.remove("ID");
        node.put("id",id);
    }
    public int getID( int index){
        return (Integer) nodes.get(index).get("ID");
    }
    public int getType( int index){
        return (Integer) nodes.get(index).get("Type");
    }
}
