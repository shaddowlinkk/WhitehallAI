
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class DataIO {
    private String data ="";
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
        data= data+node.toJSONString()+"\n";
    }
    public String getJson(){
        return data;
    }
}
