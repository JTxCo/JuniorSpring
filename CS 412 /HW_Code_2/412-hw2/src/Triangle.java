import java.lang.Math;
public class Triangle extends Shape{
    @Override
    double area() {
        double size = getSize();
        return  (Math.sqrt(3)/4)*Math.pow(size,2);
    }

    @Override
    double perimeter() {
        double size = getSize();
        return size*3;
    }
}
