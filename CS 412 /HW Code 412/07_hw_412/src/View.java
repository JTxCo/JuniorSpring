import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    //makes it
    private JPanel panel;
    private JButton add;
    private JButton delete;
    private JList list;
    private JTextField input;
    private DefaultListModel listModel;


    public View() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JFrame frame = new JFrame("theframe");
        //textfield
        input = new JTextField();
        panel.add(input);

        //button to add
        add = new JButton("add");
        panel.add(add);
        //need to create new object of something


        //once button clicked then text will be taken and then put into the list

        //list
        listModel = new DefaultListModel();
        list = new JList(listModel);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(list, BorderLayout.CENTER);

        //delete button
        delete = new JButton("DELETE");
        frame.add(delete, BorderLayout.SOUTH);

        frame.setSize(400,500);
        frame.setVisible(true);
    }



    public void setAddButtonActionListener(ActionListener aL) {
        add.addActionListener(aL);
    }

    public void setDeleteButtonActionListener(ActionListener aL){
        delete.addActionListener(aL);
    }

    public void setListData(String [] items){
        for(String item: items) {
            listModel.addElement(item);
        }
    }

    public void addListData(String item){
        listModel.addElement(item);
    }

    public void removeListData(String item){
        listModel.removeElement(item);
    }

    public String getInputText(){
        String text = input.getText();
        return text;
    }

    public String getSelectedListValue(){
        String clicked = (String) list.getSelectedValue();
        return clicked;
    }
}
