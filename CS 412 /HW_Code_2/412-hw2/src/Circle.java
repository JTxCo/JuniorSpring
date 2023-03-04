import java.lang.Math;
public class Circle extends Shape{
    @Override
    double area() {
        return  (Math.PI)*(Math.pow(getSize() ,2));
    }

    @Override
    double perimeter() {
        return 2*Math.PI*getSize();
    }
}
