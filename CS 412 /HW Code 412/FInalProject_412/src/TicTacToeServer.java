import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicTacToeServer extends JFrame{
    private final static int player_X = 0;
    private final static int player_O = 1;
    private final ClientCreate[] clients;
    private ServerSocket serverSocket;
    private final ExecutorService run;
    private final Lock boardLock;
    private final Condition player2Connect;
    private final Condition player2Turn;
    private Scanner input;
    private final String[] c = {"X", "O"};//at position 1 is x, player 1 is x
    private String[][] board_array;
    private int currentPlayer;
    private JTextArea outputArea;

    //functions
    public TicTacToeServer() {
        run = Executors.newFixedThreadPool(2);
        boardLock = new ReentrantLock();
        player2Connect = boardLock.newCondition();
        player2Turn = boardLock.newCondition();
        board_array= new String[3][3];
        for (String[] row : board_array) Arrays.fill(row, " ");
        clients = new ClientCreate[2];
        currentPlayer = player_X;
        try {
            serverSocket = new ServerSocket(8000, 2);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        outputArea = new JTextArea();
        add(outputArea, BorderLayout.CENTER);
        outputArea.setText("server waiting \n");
        setSize(200,100);
        setVisible(true);
    }
    public void ConnectionStart(){
        //starting each client
        for(int i=0; i<2; i++){
            try {
                clients[i] = new ClientCreate(serverSocket.accept(), i);
                run.execute(clients[i]);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        boardLock.lock();
        try{
            clients[player_X].isSus = false;
            player2Connect.signal();
        }
        finally{
            boardLock.unlock();
        }
    }
    private void displayMessage(String messsage){
        SwingUtilities.invokeLater(()-> {
            outputArea.append(messsage);
        });
    }

    private int RowReturn(int position){
        return position/3;
    }
    private int ColumnReturn(int position){
        return position%3;
    }
    private void putOnBoard(String[][] board_array, int position, String c){
        board_array[RowReturn(position)][ColumnReturn(position)] = c;
    }
    private boolean checkValidMove(String[][] board_array, int position) {
        int row = RowReturn(position);
        int column = ColumnReturn(position);
        if ((row < 0) || (column < 0) || (row > 2) || (column > 2)) return false;
        else return Objects.equals(board_array[row][column], " ");
    }

    private boolean checkOccupied(String[][]board_array, int position){
        return Objects.equals(board_array[RowReturn(position)][ColumnReturn(position)], "X") || Objects.equals(board_array[RowReturn(position)][ColumnReturn(position)], "O");
    }
    private boolean moveGood(String[][]board_array, int position){
        return checkValidMove(board_array, position) && checkOccupied(board_array, position);
    }
    private String checkWinner(String[][] board_array) {
        //rows
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(board_array[i][0], board_array[i][1]) && Objects.equals(board_array[i][1], board_array[i][2]) && !Objects.equals(board_array[i][2], " ")) {
                return board_array[i][0];
            }
        }
        //columns
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(board_array[0][i], board_array[1][i]) && Objects.equals(board_array[1][i], board_array[2][i]) && !Objects.equals(board_array[0][i], " ")) {
                return board_array[0][i];
            }
        }
        //diag left to right
        if (Objects.equals(board_array[0][0], board_array[1][1]) && Objects.equals(board_array[1][1], board_array[2][2]) && !Objects.equals(board_array[0][0], " ")) {
            return board_array[0][0];
        }
        //diag right to left
        if (Objects.equals(board_array[0][2], board_array[1][1]) && Objects.equals(board_array[1][1], board_array[2][0]) && !Objects.equals(board_array[0][2], " ")) {
            return board_array[0][2];
        }
    return " ";
    }

    private boolean isCats(String[][]board_array){
        for (String[] row : board_array)
            for(String val : row)
                if(Objects.equals(val, " "))return false;
        return true;
    }
    private boolean doneYet(String[][]board_array){
        String value = checkWinner(board_array);
        return switch (value) {
            case " " -> false;
            case "X" -> true;
            case "O" -> true;
            default -> isCats(board_array);
        };
    }
    private boolean doMove(int position, int playerNum){
        while(playerNum !=currentPlayer){
            boardLock.lock();
            try {
                player2Turn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                boardLock.unlock();
            }
        }
        if(!checkOccupied(board_array, position)){
            putOnBoard(board_array, position, c[playerNum]);
            currentPlayer=(currentPlayer+1)%2;
            clients[currentPlayer].opponentPlayerMoved(position);
            boardLock.lock();
            try{
                player2Turn.signal();
            }finally {
                boardLock.unlock();
            }
            return true;
        }
        else {
            return false;
        }
    }


    private class ClientCreate implements Runnable{
        private final Socket clientSocket;
        private Scanner input;
        private Formatter output;
        private final String XorO;
        private final int playerNum;
        private boolean isSus = true;
        public ClientCreate(Socket socket, int numPassed){
            playerNum = numPassed;
            if(playerNum==0){
                XorO = "X";
            }
            else{
                XorO = "O";
            }
            clientSocket = socket;
            try{
                input = new Scanner(clientSocket.getInputStream());
                output = new Formatter(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void opponentPlayerMoved(int position){
            output.format("the opponent has played\n");
            output.format("%s\n",position);
            output.flush();
            if(isCats(board_array)){
                output.format("tie\n");
                output.flush();
            }
            else if(!Objects.equals(checkWinner(board_array), " ")){
                output.format("defeat\n");
                output.flush();
            }
        }
        @Override
        public void run() {
            try {
                displayMessage("Player " + XorO + " connected\n");
                output.format("%s\n", XorO);
                output.flush();

                if (playerNum == player_X) {
                    output.format("player x connected waiting on 1 more\n");
                    output.flush();
                    boardLock.lock();
                    try {
                        while (isSus) {
                            player2Connect.await();
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        boardLock.unlock();
                    }
                    output.format("player connected, your move now\n");
                    output.flush();
                }
                else {
                    output.format("player x connected, wait\n");
                    output.flush();
                }

                while(!doneYet(board_array)){
                    int move =0;
                    if(input.hasNext()){
                        move = input.nextInt();
                    }
                    if(doMove(move, playerNum)){
                        displayMessage("\nlocation: "+ move);
                        output.format("Valid  move\n");
                        output.flush();
                        if(isCats(board_array)){
                            output.format("tie\n");
                            output.flush();
                        }
                        else if(!Objects.equals(checkWinner(board_array), " ")){
                            output.format("victory\n");
                            output.flush();
                        }
                    }
                    else{
                        output.format("try moving again that was invalid\n");
                        output.flush();
                    }
                }
            }
            finally {
                try{
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

    }
}

