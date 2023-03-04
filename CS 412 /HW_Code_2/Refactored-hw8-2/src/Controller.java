import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
    private View view;
    private Model model;

    public Controller() {
        model = new Model();
        view = new View();
        model.makeConnection();
        view.setCreateButton(new CreateButtonActionListener());
    }

    public class CreateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getCreateTexfieldName().getText();
            int age = Integer.parseInt(view .getCreateTexfieldAge().getText());
            model.CreateButton(name,age);
            }
        }

}
