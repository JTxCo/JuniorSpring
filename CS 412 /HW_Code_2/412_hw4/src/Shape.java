public abstract class Shape {
    String type;
    int size;
    public Shape(String type, int size){
        this.type = type;
        this.size = size;
    };
    public abstract String toString();
}
