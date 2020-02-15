
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

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
        nodes.add(node);
    }
    public String getJson(){
        for(JSONObject o : nodes){
            data= data+o.toJSONString()+"\n";
        }
        return data;
    }
}
