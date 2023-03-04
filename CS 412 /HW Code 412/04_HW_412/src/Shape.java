public abstract class Shape{
    public String type;
    public int size;

    public Shape(String type, int size){
        this.size=size;
        this.type = type;
    }

    public abstract String toString();

}
