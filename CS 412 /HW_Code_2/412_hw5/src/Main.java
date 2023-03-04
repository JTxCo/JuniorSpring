import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner given =new Scanner(System.in);
        System.out.print("enter parantheses and stuff to check: ");
        String input = given.nextLine();
        System.out.print("input = "+ input+ ", ");
        String parentheses = input.replaceAll("[a-zA-Z]","");
        long openCount = parentheses.chars().filter(ch -> ch == '(').count();
        long closeCount = parentheses.chars().filter(ch -> ch == ')').count();
        long bracOpenCount = parentheses.chars().filter(ch -> ch == '{').count();
        long bracClosedCount = parentheses.chars().filter(ch -> ch == '}').count();
        long braceOpenCount = parentheses.chars().filter(ch -> ch == '[').count();
        long braceClosedCount = parentheses.chars().filter(ch -> ch == ']').count();
        boolean equal = true;
        if(openCount!=closeCount|| braceOpenCount!=braceClosedCount || bracOpenCount!=bracClosedCount){
            equal = false;
        }
        System.out.println("balanced:  " +  equal);
    }
}

