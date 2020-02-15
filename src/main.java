import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class main extends javax.swing.JFrame {


    public main() throws IOException {
        this.setContentPane(new board(this));
        setPreferredSize(new Dimension(900,900));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
    public static void main(String[] args) throws Exception {
        new main();
    }
}