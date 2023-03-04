import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class View {
    private DefaultListModel<String> listModel;
    private JFrame jFrame;
    private JTabbedPane jTabs;
    private JList jList;
    //update tab stuff
    private JPanel updatePanel;
    private JPanel updateInner;
    private JTextField UpdateTextFieldName;
    private JTextField UpdateTextFieldAge;
    //read tab stuff
    private JPanel ReadPanel;
    private JList  Readlist;
    //delete panel stuff
    private JPanel DeletePanel;
    private JList<String> DeletejList;
    private JButton DeleteButton;
    //create tab stuf
    private JPanel createPanel;

    private JTextField CreateTexfieldName;
    private JTextField CreateTexfieldAge;
    private JButton CreateButton;

    public View(){
        listModel = new DefaultListModel<String>();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTabbedPane jTabs = new JTabbedPane();
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("READ", makeReadTab());
//        jTabs.add("UPDATE", makeUpdateTab());
//        jTabs.add("DELETE", makeDeleteTab());
        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);

    }
    private JPanel makeUpdateTab() {
         updatePanel = new JPanel();
        JList<String> jList = new JList(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updatePanel.add(jList);

        updateInner = new JPanel();
        updateInner.setLayout(new GridLayout(2,2));
        UpdateTextFieldName = new JTextField(10);
        UpdateTextFieldAge = new JTextField(10);
        updateInner.add(new JLabel("NAME:"));
        updateInner.add(UpdateTextFieldName);
        updateInner.add(new JLabel("AGE:"));
        updateInner.add(UpdateTextFieldAge);

        updatePanel.add(updateInner);

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
                        UpdateTextFieldName.setText(name);
                        UpdateTextFieldAge.setText(String.valueOf(age));
                        jButton.setEnabled(true);

                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (id != -1) {
                                    String name = UpdateTextFieldName.getText();
                                    int age = Integer.parseInt(UpdateTextFieldAge.getText());
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

        updatePanel.add(jButton);
        return updatePanel;
    }
    private JPanel makeReadTab(){
        ReadPanel = new JPanel();
        Readlist = new JList(listModel);
        ReadPanel.add(Readlist);
        return ReadPanel;
    }
    private JPanel makeDeleteTab(){
        DeletePanel = new JPanel();
        DeletejList = new JList(listModel);
        DeletejList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DeletePanel.add(jList);
        DeleteButton = new JButton("DELETE");
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = DeletejList.getSelectedIndex();
                String s = DeletejList.getSelectedValue();
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
        DeletePanel.add(DeleteButton);
        return DeletePanel;
    }
    private JPanel makeCreateTab(){
        createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(3,2));
        CreateTexfieldName = new JTextField(20);
        CreateTexfieldAge = new JTextField(10);
        createPanel.add(new JLabel("NAME:"));
        createPanel.add(CreateTexfieldName);
        createPanel.add(new JLabel("AGE:"));
        createPanel.add(CreateTexfieldAge);
        CreateButton = new JButton("CREATE");
        createPanel.add(CreateButton);
        return createPanel;
    }

    public void setCreateButton(ActionListener al){
        CreateButton.addActionListener(al);
    }
    public JTextField getCreateTexfieldName() {
        return CreateTexfieldName;
    }

    public JTextField getCreateTexfieldAge() {
        return CreateTexfieldAge;
    }
}
