import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    DefaultListModel<String> listModel;

    public static void main(String[] args){
        (new Main()).go();
    }

    private void go(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name STRING," +
                    "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listModel = new DefaultListModel<String>();
        JFrame jFrame = new JFrame();
        JTabbedPane jTabs = new JTabbedPane();
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("READ", makeReadTab());
        jTabs.add("UPDATE", makeUpdateTab());
        jTabs.add("DELETE", makeDeleteTab());

        jTabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ArrayList<String> arrayList = new ArrayList<String>();
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
                    String cmd = "SELECT * FROM students;";
                    ResultSet rs = conn.createStatement().executeQuery(cmd);
                    while(rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String s = String.format("%3d %10s %3d", id, name, age);
                        arrayList.add(s);
                    }
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                listModel.clear();
                for(String s : arrayList){
                    listModel.addElement(s);
                }
            }
        });

        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }

    private JPanel makeUpdateTab() {
        JPanel jPanel = new JPanel();
        JList<String> jList = new JList(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jPanel.add(jList);

        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(2,2));
        JTextField textFieldName = new JTextField(10);
        JTextField textFieldAge = new JTextField(10);
        inner.add(new JLabel("NAME:"));
        inner.add(textFieldName);
        inner.add(new JLabel("AGE:"));
        inner.add(textFieldAge);

        jPanel.add(inner);

        JButton jButton = new JButton("UPDATE");
        jButton.setEnabled(false);

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting() == false){
                    int index = jList.getSelectedIndex();
                    if (index != -1){
                        String s = listModel.elementAt(index);
                        String[] words = s.trim().split("\\s+");
                        int id = Integer.parseInt(words[0]);
                        String name = words[1];
                        int age = Integer.parseInt(words[2]);
                        textFieldName.setText(name);
                        textFieldAge.setText(String.valueOf(age));
                        jButton.setEnabled(true);

                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (id != -1) {
                                    String name = textFieldName.getText();
                                    int age = Integer.parseInt(textFieldAge.getText());
                                    try {
                                        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
                                        conn.createStatement().executeUpdate(String.format("UPDATE students SET name = '%s', age = %d WHERE id = %d;", name, age, id));
                                        conn.close();
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        jPanel.add(jButton);
        return jPanel;
    }

    private JPanel makeReadTab(){
        JPanel jPanel = new JPanel();
        JList jList = new JList(listModel);
        jPanel.add(jList);
        return jPanel;
    }

    private JPanel makeDeleteTab(){
        JPanel jPanel = new JPanel();
        JList<String> jList = new JList(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList);
        JButton jButton = new JButton("DELETE");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jList.getSelectedIndex();
                String s = jList.getSelectedValue();
                String[] words = s.trim().split(" ");
                int id = Integer.parseInt(words[0]);
                listModel.remove(i);
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
                    conn.createStatement().executeUpdate(String.format("DELETE FROM students WHERE id = %d;", id));
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }            }
        });
        jPanel.add(jButton);
        return jPanel;
    }

    private JPanel makeCreateTab(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,2));
        JTextField textFieldName = new JTextField(20);
        JTextField textFieldAge = new JTextField(10);
        jPanel.add(new JLabel("NAME:"));
        jPanel.add(textFieldName);
        jPanel.add(new JLabel("AGE:"));
        jPanel.add(textFieldAge);
        JButton createButton = new JButton("CREATE");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                int age = Integer.parseInt(textFieldAge.getText());
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
                    conn.createStatement().executeUpdate(String.format("INSERT INTO students (name, age) VALUES ('%s',%d);", name, age));
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jPanel.add(createButton);
        return jPanel;
    }
}
