import com.github.cliftonlabs.json_simple.*;
import com.github.cliftonlabs.json_simple.JsonObject;

public class DataIO {
    private JsonArray data = new JsonArray();
    public void DataIO(){

    }
    public void addData(int id, int conn,int type,int x, int y){
        JsonObject node = new JsonObject();
        JsonArray point = new JsonArray();
        node.put("ID",id);
        node.put("Connections",conn);
        node.put("Type",type);
        point.add(x-16);
        point.add(y-34);
        node.put("Points",point);
        data.add(node);
    }
    public JsonArray getJson(){
        return data;
    }
}
