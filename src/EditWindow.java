import javax.swing.*;

public class EditWindow {
    public JComponent[] EditWindow(){
        String[] test = {"a","b"};
        JTextField ID = new JTextField();
        JTextField type = new JTextField();
        JComboBox poins = new JComboBox();
        JPasswordField password = new JPasswordField();
        ID.setText("3");
        final JComponent[] inputs = new JComponent[] {
                new JLabel("id"),
                ID,
                new JLabel("type"),
                type,
        };
        return inputs;
    }
}
