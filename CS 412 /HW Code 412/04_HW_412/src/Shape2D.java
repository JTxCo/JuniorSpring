public abstract class Shape2D extends Shape implements TwoDimensional{
public Shape2D(String type, int size){
   super(type, size);
}

    public String toString(){
    return "2D shape " + type + ": size = " + size + ", area = " + this.area() + ", perimeter = "+ this.perimeter();
    }
}
