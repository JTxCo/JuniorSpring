public class Square extends Shape2D {
    public Square(int size){
        super("square",size);
    }
    public double area(){
        return size*size;
    }

    public double perimeter(){
        return size*4;
    }

}

