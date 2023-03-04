import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//2022
public class Controller {
    private View view;
    private Model model;

    public Controller() {
        view = new View();
        model = new Model();
        view.setAddButtonActionListener(new AddButtonActionListener());
        view.setDeleteButtonActionListener(new DeleteButtonActionListener());
    }

    class AddButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getInputText();
            view.addListData(input);
        }
    }

    class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.removeListData(view.getSelectedListValue());
            model.deletinginput(view.getSelectedListValue());
        }
    }
}

