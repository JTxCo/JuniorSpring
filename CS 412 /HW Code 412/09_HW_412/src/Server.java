import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Server {
    private static String tocompute;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket client;
    private ServerSocket serverSocket;

    public Server() {
        System.out.println("server: waiting for connection...");
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
        try {
            client = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server: connected to client Socket at: " + client );
        System.out.println("Server: waitng for orders ");
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                printWriter = new PrintWriter(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    if ((tocompute = bufferedReader.readLine()) == null) break;
                    tocompute = answer(tocompute);
                    printWriter.println(tocompute);
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Server: waiting for connection or love ");
        }
    }
    public static void main(String[] args) {
        new Server();

    }
    private String answer(String given) {
        String tokenholder[] = given.split(" ");
        if(tokenholder.length!=3){
            return "invalid formating, your fault ";
        }
        double number1 = Double.parseDouble(tokenholder[0]);
        String opernd = tokenholder[1];
        Double number2 = Double.parseDouble(tokenholder[2]);
        switch (opernd) {
            case "*":
                return String.valueOf(number1 * number2);
            case "/":
                return String.valueOf(number1 / number2);
            case "+":
                return String.valueOf(number1 + number2);
            case "-":
                return String.valueOf(number1 - number2);
            case "^":
                return String.valueOf(Math.pow(number1,number2));
            case "%":
                return String.valueOf(number1 % number2);
            default: return "invalid operation given";
        }
    }
}
