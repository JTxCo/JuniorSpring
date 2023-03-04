import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.io.IOException;
import javax.swing.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class TicTacToClients extends JFrame implements Runnable {
    private JTextField XorO;
    private JTextArea textArea;
    private JPanel gameboard;
    private JPanel boardforBoard;
    private TicBoardCreate[][] array_board;
    private Socket clientConnection;
    private Scanner input;
    private Formatter output;
    private ExecutorService client;
    private TicBoardCreate currentSquare;
    private String clientSymbol;
    private boolean myTurn;
    private final String[] symbols = {"X", "O"};
    public TicTacToClients(){
        textArea = new JTextArea(4, 30);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        gameboard = new JPanel();
        gameboard.setLayout(new GridLayout(3,3,0,0));
        array_board = new TicBoardCreate[3][3];

        for( int row =0; row< array_board.length; row++){
            for( int column = 0; column< array_board[row].length; column++){
                array_board[row][column] = new TicBoardCreate(" ", (row*3)+ column);
                gameboard.add(array_board[row][column]);
            }
        }
        XorO = new JTextField();
        XorO.setEditable(false);
        add(XorO, BorderLayout.NORTH);

        boardforBoard = new JPanel();
        boardforBoard.add(gameboard, BorderLayout.CENTER);
        add(boardforBoard, BorderLayout.CENTER);
        setSize(500, 425);
        setVisible(true);
        runClient();
    }
    public void runClient(){
        try {
            clientConnection = new Socket("127.0.0.1", 8000);
            input = new Scanner(clientConnection.getInputStream());
            output = new Formatter(clientConnection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        client = Executors.newFixedThreadPool(1);
        client.execute(this);
    }

    @Override
    public void run() {
        clientSymbol = input.nextLine();
        //make the id field set to your player
        SwingUtilities.invokeLater(()->{
            XorO.setText("You are player: " + clientSymbol);
        });
        myTurn = (clientSymbol.equals(symbols[0]));
        while(true){
            if(input.hasNextLine()){
                checkDatInput(input.nextLine());
            }
        }
    }
    private void checkDatInput(String daInput){
        switch (daInput){
            case "try moving again that was invalid":
                display(daInput+"\n");
                myTurn = true;
                break;
            case"Valid move":
                display(daInput + "\n");
                setSquare(currentSquare, clientSymbol);
                break;

            case "the opponent has played":
                int position = input.nextInt();
                input.nextLine();
                String opponentSym;
                if(clientSymbol == symbols[0]){
                    opponentSym = "O";
                }
                else{
                    opponentSym = "X";
                }
                setSquare(array_board[position/3][position%3], opponentSym);
                display("opponent moved\n");
                myTurn = true;
                break;
            case "defeat":
            case "tie":
            case "victory":
                display(daInput+"\n");
                myTurn=false;
                break;
            default:
                display(daInput+ "\n");
                break;
        }
    }

    private void display(String message){
        SwingUtilities.invokeLater(() -> {
            textArea.append((message));
        });
        System.out.println( message+ "\n");
    }
    private void sendPositionServer(int location){
        if(myTurn){
            output.format("%d\n", location);
            output.flush();
            myTurn=false;
        }
    }

    public void setSquare(TicBoardCreate square, String symbol){
        SwingUtilities.invokeLater(()->{
            square.setSymbol(symbol);
        });
    }
    public void setCurrentSquare(TicBoardCreate square){
        currentSquare = square;
    }

    public class TicBoardCreate extends JPanel {
        private String XorO;
        private int position;
        private TicBoardCreate(String hexSymbol, int location){
            XorO = hexSymbol;
            System.out.println("symbol: "+hexSymbol);
            position = location;
            System.out.println("location: "+location);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setCurrentSquare(TicBoardCreate.this);
                    sendPositionServer(positionReturn());
                }
            });
        }
        public int positionReturn(){
            return position;
        }
        public Dimension getSize(){
            return new Dimension(30, 30);
        }
        public Dimension minSize(){
            return getSize();
        }
        public void setSymbol(String newmark){
            clientSymbol = newmark;
            repaint();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 30, 30); // draw square
            g.drawString(clientSymbol, 11, 20); // draw mark
        }
    }
}