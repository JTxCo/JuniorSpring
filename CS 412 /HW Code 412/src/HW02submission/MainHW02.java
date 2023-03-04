import java.util.ArrayList;
import java.util.Random;

public class MainHW02 {
    public static void main(String[] args){
        Random random = new Random();

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        for(int i = 0; i < 100; i++){
            Shape shape = null;
            int type = random.nextInt(3);
            int size = random.nextInt(9)+1;
            if(type == 0){
                shape = makeSquare(size);
            } else if(type == 1){
                shape = makeCircle(size);
            } else {
                shape = makeTriangle(size);
            }
            shapes.add(shape);
        }

        for(Shape shape : shapes){
            shape.print();
        }
    }

    private static Square makeSquare(int length){
        Square square = new Square();
        square.setType("Square");
        square.setSize(length);
        return square;
    }
    private static Circle makeCircle(int radius){
        Circle circle = new Circle();
        circle.setType("Circle");
        circle.setSize(radius);
        return circle;
    }
    private static Triangle makeTriangle(int size){
        Triangle triangle = new Triangle();
        triangle.setType("Triangle");
        triangle.setSize(size);
        return triangle;
    }
}
