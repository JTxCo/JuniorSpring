import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    Controller c;

    public static void main(String[] args) {
        Main m = new Main();
        m.go();
    }
    public void go() {
        c = new Controller();
    }

}
