import javax.swing.*;

public class MainClient {
    public static void main(String[] args) {
        TicTacToClients m = new TicTacToClients();
        System.out.println(m);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);
    }
}