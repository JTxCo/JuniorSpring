import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Controller {
    private Model model;
    private View view;
    public Controller(){
        model = new Model();
        view = new View();
        view.setAddButton(new AddButtonActionListener());
        view.setDeleteButton(new DeleteButtonActionListener());
    }

    public class DeleteButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.removeListData(view.getSelectedListValue());
            model.removeData(view.getSelectedListValue());
            System.out.println( model.getData());
        }
    }

    public class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addListData(view.getInputText());
            model.addData(view.getInputText());
            view.clearTextField();
        }
    }

}
