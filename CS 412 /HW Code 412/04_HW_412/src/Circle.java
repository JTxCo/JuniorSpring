public class Circle extends Shape2D{
    public Circle(int size){
        super("Circle", size);
    }

    public double area(){
        return  size*size*Math.PI;
    }

    public double perimeter(){
        return 2*Math.PI*size;
    }
}
