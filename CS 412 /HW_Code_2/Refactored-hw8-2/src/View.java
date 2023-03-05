import javax.swing.*;
import javax.swing.event.ChangeListener;
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
    private String[] words;

    //update tab stuff
    private JButton updateButton;
    private JPanel updatePanel;
    private JPanel updateInner;
    private JList updateJList;
    private JTextField UpdateTextFieldName;
    private JTextField UpdateTextFieldAge;

    //read tab stuff
    private JPanel ReadPanel;
    private JList  Readlist;

    //delete panel stuff
    private JPanel DeletePanel;
    private JList<String> DeletejList;
    private JButton DeleteButton;
    //create tab stuff
    private JPanel createPanel;
    private JTextField CreateTexfieldName;
    private JTextField CreateTexfieldAge;
    private JButton CreateButton;



    public View(){
        listModel = new DefaultListModel<String>();
        jList = new JList(listModel);
        updateJList = new JList<>(listModel);
        Readlist = new JList<>(listModel);
        DeletejList = new JList<>(listModel);
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jTabs = new JTabbedPane();
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("READ", makeReadTab());
        jTabs.add("UPDATE", makeUpdateTab());
        jTabs.add("DELETE", makeDeleteTab());
        jFrame.add(jTabs);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }




    private JPanel makeUpdateTab() {
        updatePanel = new JPanel();
        updateJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        updatePanel.add(updateJList);
        updateInner = new JPanel();
        updateInner.setLayout(new GridLayout(2,2));
        UpdateTextFieldName = new JTextField(10);
        UpdateTextFieldAge = new JTextField(10);
        updateInner.add(new JLabel("NAME:"));
        updateInner.add(UpdateTextFieldName);
        updateInner.add(new JLabel("AGE:"));
        updateInner.add(UpdateTextFieldAge);
        updatePanel.add(updateInner);
        updateButton = new JButton("UPDATE");
        updatePanel.add(updateButton);
        updatePanel.setVisible(true);
        return updatePanel;
    }
    private JPanel makeReadTab(){
        ReadPanel = new JPanel();
        ReadPanel.add(Readlist);
        ReadPanel.setVisible(true);
        return ReadPanel;
    }

    private JPanel makeDeleteTab(){
        DeletePanel = new JPanel();
        DeletejList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DeletePanel.add(DeletejList);
        DeleteButton = new JButton("DELETE");
        DeletePanel.add(DeleteButton);
        DeleteButton.setEnabled(true);
        DeletePanel.setVisible(true);
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
        CreateButton.setEnabled(true);
        createPanel.setVisible(true);
        return createPanel;
    }

    public void setCreateButton(ActionListener al){
        CreateButton.addActionListener(al);
    }
    public void setChangeListener(ChangeListener changeListener){
        jTabs.addChangeListener(changeListener);
    }
    public void setDeleteButton(ActionListener actionListener){
        DeleteButton.addActionListener(actionListener);
    }
    public void setUpdateButton(ActionListener actionListener){ updateButton.addActionListener(actionListener);}
    public void setListSelectionListener(ListSelectionListener listSelectionListener){ updateJList.addListSelectionListener(listSelectionListener);}
    public JTextField getCreateTexfieldName() {
        return CreateTexfieldName;
    }

    public JTextField getCreateTexfieldAge() {
        return CreateTexfieldAge;
    }
    public DefaultListModel<String> getListModel() {
        return listModel;
    }
    public JList<String> getDeletejList() {
        return DeletejList;
    }
    public JList<String> getUpdateJList() {
        return updateJList;
    }
    public JTextField getUpdateTextFieldName() {
        return UpdateTextFieldName;
    }

    public JTextField getUpdateTextFieldAge() {
        return UpdateTextFieldAge;
    }
    public JButton getUpdateButton() {
        return updateButton;
    }
    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }
}
