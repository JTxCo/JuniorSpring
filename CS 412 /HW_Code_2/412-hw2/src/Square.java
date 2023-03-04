public class Square extends Shape{
    @Override
    double area() {
        double size = getSize();
        return size * size;
    }

    @Override
    double perimeter() {
        double size = getSize();
        return 4*size;
    }

}
