public class Sphere extends Shape3d{
    public Sphere( int size) {
        super("Sphere", size);
    }

    @Override
    public double volume() {
        double mathandPi= (4)*Math.PI/3;
        return mathandPi*Math.pow(size,3);
    }

    @Override
    public double surfaceArea() {
        return 4*Math.PI*Math.pow(size,2);
    }
}
