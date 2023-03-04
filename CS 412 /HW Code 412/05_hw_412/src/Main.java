
import java.util.Scanner;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter parantheses to check: " );
        String usergiven = input.nextLine();
        System.out.print("input= "+ usergiven + ", ");
        Stack<Character> holder = new Stack<>();
        boolean inspector= true;
        char [] chars = usergiven.toCharArray();
        holder.push(chars[0]);
            for (char c : chars) {
                if (inspector) {
                    if (c == '}') {
                        char compare = holder.pop();
                        if (compare != '{') {
                            inspector = false;
                        }
                    } else if (c == ')') {
                        char compare = holder.pop();
                        if (compare != '(') {
                            inspector = false;
                        }
                    } else if (c == ']') {
                        char compare = holder.pop();
                        if (compare != '[') {
                            inspector = false;
                        }
                    } else {
                        if (c == '{' || c == '[' || c == '(')
                            holder.push(c);
                    }
                }
        }
        //popping and looking
        System.out.println("balanced= " + inspector);



    }

}
