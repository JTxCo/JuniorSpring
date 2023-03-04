import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    public ArrayList<String> arrayList;
    public DefaultListModel<String> listModel;

    public Model() {
        listModel = new DefaultListModel<>();
    }

    public void dotheimportantstuff(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name STRING," +
                    "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void doStateChange(){
        try {
            arrayList = new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "SELECT * FROM students;";
            ResultSet rs = conn.createStatement().executeQuery(cmd);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String s = String.format("%3d %10s %3d", id, name, age);
                arrayList.add(s);
            }
            conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


public void dodeletebutton(int id){
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        conn.createStatement().executeUpdate(String.format("DELETE FROM students WHERE id = %d;", id));
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public void doupdatebutton(String name, int age, int id){
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        conn.createStatement().executeUpdate(String.format("UPDATE students SET name = '%s', age = %d WHERE id = %d;", name, age, id));
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public void doCreateButton(String name, int age) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        conn.createStatement().executeUpdate(String.format("INSERT INTO students (name, age) VALUES ('%s',%d);", name, age));
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}

