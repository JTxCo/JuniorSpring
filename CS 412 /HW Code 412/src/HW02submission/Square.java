public class Square extends Shape{
    public double area(){
        double size;
        size = getSize();
        double area= (size * size);
        return area;
    }

    public double perimeter(){
        double size;
        size= getSize();
        double perimeter = 4*size;
        return perimeter;
    }

}
