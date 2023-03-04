public class Circle extends Shape2D{
    public Circle( int size){
        super("Circle", size);
    };
    @Override
    public double area() {
        return Math.PI* Math.pow(size,2);
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*size;
    }
}
