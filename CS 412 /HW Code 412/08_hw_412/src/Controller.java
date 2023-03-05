import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private Model model;

    public  Controller(){
        view = new View();
        model = new Model();
        view.setaddChangeListner(new StateChanglistner());
        view.setDeleteActionListener(new DeleteButtonActionListner());
        view.setListSelectionListner(new addUpdateListSelectionListener());
        view.setUpdateButtonListener(new addUpdateButtonListener());
        view.setCreateButtonListener(new addCreateButtonListener());
        model.dotheimportantstuff();
    }

    class StateChanglistner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            model.doStateChange();
            view.getListModel().clear();
            for(String s : model.arrayList){
                view.getListModel().addElement(s);
            }
        }
    }


    class DeleteButtonActionListner implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        int i = view.getjlistdelete().getSelectedIndex();
        String s  = (String) view.getjlistdelete().getSelectedValue();
        String[] words = s.trim().split(" ");
//        view.setWords(words); maybe do this?
        int id = Integer.parseInt(words[0]);
        if(s!=null && i!=-1) {
            view.getListModel().remove(i);
            System.out.println("eror here?" );
            model.arrayList.remove(i);
            model.dodeletebutton(id);
        }
    }
    }

    class addUpdateListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting() == false){
                int index = view.getjList().getSelectedIndex();
                if (index != -1){
                    String s = view.getListModel().elementAt(index);
                    String [] words  = s.trim().split("\\s+");
                    view.setWords(words);
                    String name = view.getWords(1);
                    int age = Integer.parseInt(view.getWords(2));
                    view.getUpdateFieldName().setText("jerry");
                    view.getUpdateFieldAge().setText(String.valueOf(age));
                    view.getUpdateButton().setEnabled(true);
                }
            }
        }
    }

    class addUpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String name = view.getUpdateFieldName().getText();
            int id = Integer.parseInt(view.getWords(0));
            int age = Integer.parseInt(view.getUpdateFieldAge().getText());
            if(id!=-1) {
                model.doupdatebutton(name, age, id);
            }
        }
    }

    class addCreateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getCrtFieldName().getText();
            int age = Integer.parseInt(view.getCrtFieldAge().getText());
            if(view.getCrtFieldAge().getText()==" "||name==null||age==0){
                System.out.println("erorr with what has been inputted\n");
            }
            else {
                model.doCreateButton(name, age);
            }
        }
    }

}

