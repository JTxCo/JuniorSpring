public class Cube extends Shape3D{
    public Cube(int size){
        super("Cube" ,size);
    }

    public double volume(){
        return size*size*size;
    }

    public double surfaceArea(){
        return 6*(size*size);
    }
}
