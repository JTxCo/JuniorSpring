import java.util.Scanner;


public class NumGam {
    public static void main(String[] args) {
        Scanner given =new Scanner(System.in);
        GameStart a = new GameStart();
        System.out.print("Guess a random number: ");
        int numGuess = given.nextInt();
        String result = a.guess(numGuess);
        System.out.println(result);
        while(result != "nailed it!") {
            System.out.print("Guess a random number: ");
            numGuess = given.nextInt();
            result = a.guess(numGuess);
            System.out.println(result);
        }
    }
}
