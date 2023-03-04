public class Triangle extends Shape{
    public double area(){
        double size;
        size = getSize();
        double area= (Math.sqrt(3)/4.) * (size*size);
        return area;
    }

    public double perimeter(){
        double size;
        size= getSize();
        double perimeter = size*3;
        return perimeter;
    }

}
