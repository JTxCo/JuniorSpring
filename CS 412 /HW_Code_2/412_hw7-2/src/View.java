import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame jFrame;
    private JPanel jPanel;
    private JList jList;
    private DefaultListModel listModel;
    private JTextField jTextField;
    private JButton addButton;
    private JButton deleteButton;

    public View(){
        jFrame = new JFrame();
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        listModel = new DefaultListModel<String>();
        jList = new JList<String>(listModel);
        jTextField = new JTextField();
        addButton = new JButton(" ADD");
        deleteButton =  new JButton(" DELETE");
        jFrame.setLayout(new BorderLayout());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel.add(jTextField);
        jPanel.add(addButton);
        jFrame.add(jPanel, BorderLayout.NORTH);
        jFrame.add(deleteButton, BorderLayout.SOUTH);
        jFrame.add(jList, BorderLayout.CENTER);
        jFrame.setSize(600,600);
        jFrame.setVisible(true);
    }
    public void setAddButton(ActionListener actionListener){
        addButton.addActionListener(actionListener);
    }
    public void setDeleteButton(ActionListener actionListener){
        deleteButton.addActionListener(actionListener);
    }
    public void setListData(String[] items){
        for(String i : items){
            listModel.addElement(i);
        }
    }
    public void addListData(String item){
        listModel.addElement(item);
    }
    public void removeListData(String item){
        listModel.removeElement(item);
        System.out.println("list after: " + listModel);
    }
    public String getInputText(){
        return jTextField.getText();
    }
    public String getSelectedListValue(){
        return (String)jList.getSelectedValue();
    }
    public void clearTextField(){
        jTextField.setText("");
    }

}
