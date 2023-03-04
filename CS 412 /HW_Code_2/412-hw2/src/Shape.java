public class Shape {
    private String type;
    private double size ;

    double area() {
        return 0;
    }

    double perimeter() {
        return 0;
    }

    public String getType(){
        return type;
    }
    public double getSize(){
        return size;
    }
    void setType(String s){
        type = s;
    }
    void setSize(int i){
        size = i;
    }

    void print(){
        System.out.println(type + " of " + size+  " has  area " + String.format("%.2f", area())  + ", perimeter " +String.format("%.2f", perimeter()));
    }
}
