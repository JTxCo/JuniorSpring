import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class View {
    private final JFrame jFrame;
    private final JTabbedPane jTabs;
    private JPanel jPanel;
    private JList jList;
    private DefaultListModel<String> listModel;

    private JPanel updatePanel;
    private JList<String> jlistUpdate ;
    private JPanel inner;
    private JTextField updateFieldName;
    private JTextField updateFieldAge;

    private JButton updateButton;
    private String [] words;

    private JPanel readPanel;
    private JList jlistRead;



    private JPanel deletepanel;
    private JList<String> jlistdelete;
    private JButton deleteButton;

    private JPanel crtpanel;

    private  JTextField crtFieldName;
    private JTextField crtFieldAge;
    private JButton createButton;

    public View() {
        listModel = new DefaultListModel<String>();
        jlistdelete = new JList<>(listModel);
        jlistRead = new JList<>(listModel);
        jlistUpdate = new JList<>(listModel);
        jlistUpdate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList = new JList(listModel);
        deletepanel = new JPanel();
        updatePanel = new JPanel();
        jFrame = new JFrame();
        jTabs = new JTabbedPane();
        jPanel = new JPanel();
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList);
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("READ", makeReadTab());
        jTabs.add("UPDATE", makeUpdateTab());
        jTabs.add("DELETE", makeDeleteTab());
        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }
    private JPanel makeUpdateTab() {
        inner = new JPanel();
        jlistUpdate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        updatePanel.add(jlistUpdate);
        inner.setLayout(new GridLayout(2, 2));
        updateFieldName = new JTextField(10);
        updateFieldAge = new JTextField(10);
        inner.add(new JLabel("NAME:"));
        inner.add(updateFieldName);
        inner.add(new JLabel("AGE:"));
        inner.add(updateFieldAge);
        updatePanel.add(inner);
        updateButton = new JButton("UPDATE");
        updatePanel.add(updateButton);
        updateButton.setEnabled(true);
        return updatePanel;
    }
    private JPanel makeReadTab(){
        readPanel = new JPanel();
        readPanel.add(jlistRead);
        readPanel.setVisible(true);
        return readPanel;
    }
    private JPanel makeDeleteTab(){
        deletepanel.add(jlistdelete);
        deleteButton = new JButton("DELETE");
        deletepanel.add(deleteButton);
        deleteButton.setEnabled(true);
        deletepanel.setVisible(true);
        return deletepanel;
    }
    private JPanel makeCreateTab(){
        crtpanel = new JPanel();
        crtFieldName = new JTextField(20);
        crtFieldAge = new JTextField(10);
        crtpanel.add(new JLabel("NAME:"));
        crtpanel.add(crtFieldName);
        crtpanel.add(new JLabel("AGE:"));
        crtpanel.add(crtFieldAge);
        createButton = new JButton("CREATE");
        createButton.setEnabled(true);
        crtpanel.add(createButton);
        crtpanel.setVisible(true);
        return crtpanel;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }
    public JList getjList(){
        return jlistUpdate;
    }

    public JList getjlistdelete(){
        return jlistdelete;
    }
    public JList getjlistUpdate(){
        return jlistUpdate;
    }
    public JTextField getUpdateFieldAge() {
        return updateFieldAge;
    }
    public JTextField getUpdateFieldName() {
        return updateFieldName;
    }
    public String getWords(int x) {
        String word = words[x];
        return word;
    }
    public String[] getWordsArray(){
        return words;
    }
    public void setWords(String[] words) {
        this.words = words;
    }
    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getCrtFieldName() {
        return crtFieldName;
    }
    public JTextField getCrtFieldAge() {
        return crtFieldAge;
    }

    public void setaddChangeListner(ChangeListener aL){
        jTabs.addChangeListener(aL);
    }
    public void setDeleteActionListener(ActionListener aL) {
        deleteButton.addActionListener(aL);
    }
    public void setListSelectionListner(ListSelectionListener aL){
        jlistUpdate.addListSelectionListener(aL);
    }
    public void setUpdateButtonListener(ActionListener aL){
        updateButton.addActionListener(aL);
    }
    public void setCreateButtonListener(ActionListener aL){ createButton.addActionListener(aL);}
}
