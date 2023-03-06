import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {
    private ServerSocket server;
    private Socket conn;
    private BufferedReader reader;
    private PrintWriter writer;

    public void main(String[] args) {
        try {
            server = new ServerSocket(5000);
            while(true) {
                conn = server.accept();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                writer = new PrintWriter(conn.getOutputStream());
                writer.println(("you gave me: " + reader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
