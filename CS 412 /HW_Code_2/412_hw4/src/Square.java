public class Square extends Shape2D{
    public Square( int size) {
        super("Square", size);
    }

    @Override
    public double area() {
        return size*size;
    }

    @Override
    public double perimeter() {
        return size*4;
    }
}
