import com.sun.jdi.PathSearchingVirtualMachine;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Client {
    private final JFrame jFrame;
    private JPanel panel;
    private JTextField addfield;
    private JTextField resultfield;
    private JButton computebutton;
    private PrintWriter printWriter;
    private Socket socket;
    private BufferedReader bufferedReader;
    private String answer;

    public Client(){
        System.out.println("Client: connecting to 127.0.0.1:500)");
        jFrame = new JFrame("your mom can't add");
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        addfield = new JTextField(10);
        resultfield = new JTextField(10);
        computebutton = new JButton("Compute!!!");
        panel.add(addfield);
        panel.add(computebutton);
        panel.add(resultfield);
        jFrame.add(panel);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            socket = new Socket("127.0.0.1", 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client: success!!");
        computebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String addstring = addfield.getText();
            printWriter.println(addstring);
                System.out.println("Client: sedding over: "+ addstring);
            answer = null;
            printWriter.flush();
            try {
                answer = bufferedReader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            resultfield.setText(answer);
            System.out.println("Client: their response is: " + answer);
            }
        });

    }

    public static void main(String[] args) {
        new Client();
    }
}
