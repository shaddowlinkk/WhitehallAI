package MainRun;

import Util.DataIO;
import Util.configeration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class pathing {
    DataIO dataIO ;
    configeration con = new configeration("main.config");
    public pathing(DataIO D) throws IOException {
        dataIO=D;
        ajMatrix();
    }
    private int [][] ajMatrix() throws IOException {
        FileWriter myWriter = new FileWriter("test.csv");
        int LCV=0;
        try {
            LCV= Integer.parseInt(con.getProp("Number_Of_Nodes"));
        }catch (Exception e){
            System.out.println("Property not found");
        }
        int [][] mat = new int[LCV][LCV];
        for (int i=0; i< LCV;i++){
            ArrayList<Integer> list = dataIO.getLinks(i+1);
            for (int j=0;j< list.size();j++){
                mat[i][list.get(j)-1]=1;
            }
            myWriter.append(Arrays.toString(mat[i]));
            myWriter.append("\n");
        }
        return mat;
    }
}
