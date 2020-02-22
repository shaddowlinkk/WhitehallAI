import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class main extends javax.swing.JFrame {
    private  static String[] option;


    public main() throws IOException {

        if(option[0].equals("-e")) {
            this.setContentPane(new board(this));
            setPreferredSize(new Dimension(900, 900));
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }

    }
    public static void main(String[] args) throws Exception {
        option = args;
        new main();
    }
}