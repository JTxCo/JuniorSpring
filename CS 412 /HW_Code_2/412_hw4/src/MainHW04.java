import java.util.ArrayList;
import java.util.Random;

public class MainHW04 {
    public static void main(String[] args){
        Random random = new Random();
        ArrayList<Shape> shapes = new ArrayList<Shape>();

        for(int i = 0; i < 1000; i++){
            int size = random.nextInt(10) + 1;

            switch(size % 4){
                case 0:
                    shapes.add(new Square(size));
                    break;
                case 1:
                    shapes.add(new Circle(size));
                    break;
                case 2:
                    shapes.add(new Cube(size));
                    break;
                case 3:
                    shapes.add(new Sphere(size));
                    break;
            }
        }

        for(Shape shape : shapes){
            System.out.println(shape);
        }
    }
}
