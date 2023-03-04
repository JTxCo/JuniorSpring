import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    Controller c;
    public static void main(String[] args) {
        Main m = new Main();
        m.go();
    }
    private void go(){
        c = new Controller();
    }
}
