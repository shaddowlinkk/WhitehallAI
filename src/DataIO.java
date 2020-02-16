
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import sun.plugin.javascript.navig.Link;

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
        JSONObject node = nodes.get(selected-1);
        JSONArray links = (JSONArray) node.get("Links");
        return (links != null);
    }
    public String getJson(){
        for(JSONObject o : nodes){
            data= data+o.toJSONString()+"\n";
        }
        return data;
    }
}
