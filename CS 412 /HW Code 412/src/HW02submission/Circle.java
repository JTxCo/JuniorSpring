public class Circle extends Shape{
public double area(){
    double size;
    size = getSize();
    double area= (size * size) * Math.PI;
    return area;
    }

public double perimeter(){
double size;
size= getSize();
double perimeter = 2*Math.PI*size;
return perimeter;
}


}
