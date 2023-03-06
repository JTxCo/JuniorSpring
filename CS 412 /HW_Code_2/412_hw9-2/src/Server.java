import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {
//    private ServerSocket server;
//    private Socket conn;
//    private BufferedReader reader;
//    private PrintWriter writer;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            while(true) {
               Socket conn = server.accept();
               BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
               PrintWriter writer = new PrintWriter(conn.getOutputStream());
               writer.println(("you gave me: " + reader));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
