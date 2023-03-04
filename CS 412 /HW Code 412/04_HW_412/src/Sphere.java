public class Sphere extends Shape3D{
    public Sphere(int size){
        super("Sphere" , size);
    }
    public double volume(){
        return (4/3)*(Math.PI)*(size*size*size);
    }

    public double surfaceArea(){
        return 4*Math.PI*(size*size);
    }
}
