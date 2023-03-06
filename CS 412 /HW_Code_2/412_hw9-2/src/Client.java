import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static PrintWriter pr;
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            pr = new PrintWriter(socket.getOutputStream());
            pr.println("input: ");
            pr.flush();
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String message = reader.readLine();
            System.out.println("stuff got back is: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
