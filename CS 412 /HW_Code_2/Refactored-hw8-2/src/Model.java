import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    private ArrayList data;

    public Model() {
        data = new ArrayList<>();
    }

    public void makeConnection() {//this connects or makes the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name STRING," +
                    "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void CreateButton(String name, int age) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().executeUpdate(String.format("INSERT INTO students (name, age) VALUES ('%s',%d);", name, age));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
