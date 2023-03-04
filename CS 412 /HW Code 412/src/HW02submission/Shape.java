public abstract class Shape {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private String type;
    private int size;

    public abstract double area();

    public double perimeter(){
        return 0;
    }

    public void print(){
        System.out.println(type+" of size " + size+ " has area "+area()+ " perimeter "+perimeter() );
    }


}
