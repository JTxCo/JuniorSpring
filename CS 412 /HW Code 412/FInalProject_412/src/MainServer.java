import javax.swing.JFrame;
public class MainServer {
    public static void main(String[] args) {
        TicTacToeServer m = new TicTacToeServer();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.ConnectionStart();
    }
}
