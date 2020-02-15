
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class DataIO {
    private JSONArray data = new JSONArray();
    public void DataIO(){

    }
    public void addData(int id, int conn,int type,int x, int y){
        JSONObject node = new JSONObject();
        JSONArray point = new JSONArray();
        node.put("ID",id);
        node.put("Connections",conn);
        node.put("Type",type);
        point.add(x-16);
        point.add(y-34);
        node.put("Points",point);
        data.add(node);
    }
    public JSONArray getJson(){
        return data;
    }
}
