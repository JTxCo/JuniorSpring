import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private Model model;

    public Controller() {
        model = new Model();
        view = new View();
        model.makeConnection();
        view.setCreateButton(new CreateButtonActionListener());
        view.setChangeListener(new StateChangeListener());
        view.setDeleteButton(new DeleteButtonListener());
        view.setUpdateButton(new UpdateButtonActionListener());
        view.setListSelectionListener( new UpdateListSelectionListener());
    }

    public class CreateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getCreateTexfieldName().getText();
                int age = Integer.parseInt(view.getCreateTexfieldAge().getText());
                if (name == " " || name.equals(" ") || name == null || name.equals(null) || age <= 0 || name.contains(" ")) {
                    System.out.println("you have inputted a bad name please do soemthing else");
                } else {
                    model.CreateButton(name, age);
                }
            }
            catch (NumberFormatException nfe){
                System.out.println("you have inputted a bad name please do soemthing else you idiot");
            }
        }
        }
    public class StateChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
           model.StateChange();
           view.getListModel().clear();
            for (String s : model.getData()) {
                view.getListModel().addElement(s);
            }
        }
    }
    public class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = view.getDeletejList().getSelectedIndex();
            String s = view.getDeletejList().getSelectedValue();
            String[] words = s.trim().split(" ");
            int id = Integer.parseInt(words[0]);
            if(s != null && i!=-1){
                view.getListModel().remove(i);
                model.DeleteButton(id);
                model.getData().remove(id);
            }
        }
    }
    public class UpdateButtonActionListener implements ActionListener {
        private int age;
        private int id;
        private String name;
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                    name = view.getUpdateTextFieldName().getText();
                    age = Integer.parseInt(view.getCreateTexfieldAge().getText());
                    id = Integer.parseInt(view.getWords()[0]);
                    if (name == " "|| name.equals(" ")||name == null || name.equals(null)|| age <= 0 || name.contains(" ")){
                        System.out.println("you have inputted a bad name please do something else");
                    }
                    else {
                        if (id != -1) {
                            model.UpdateButton(name, age, id);
                        }
                    }
                }
                catch (NumberFormatException nfe){
                    System.out.println("you have inputted a bad name please do something else you idiot");
                }

        }
    }
    public class UpdateListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting() == false){
                int index = view.getUpdateJList().getSelectedIndex();
                if (index != -1){
                    try {
                        String s = view.getListModel().elementAt(index);
                        String[] words = s.trim().split("\\s+");
                        view.setWords(words);
                        String name = view.getWords()[1];
                        int age = Integer.parseInt(view.getWords()[2]);
                        view.getUpdateTextFieldName().setText(name);
                        view.getUpdateTextFieldAge().setText(String.valueOf(age));
                        view.getUpdateButton().setEnabled(true);
                    }
                    catch (NumberFormatException nfe){
                        System.out.println("bad selection");
                    }
                }
            }
        }
    }
}
