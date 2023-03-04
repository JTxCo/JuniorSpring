public class Cube extends Shape3d{
    public Cube( int size) {
        super("Cube", size);
    }

    @Override
    public double volume() {
        return Math.pow(size,3);
    }

    @Override
    public double surfaceArea() {
        return 6*Math.pow(size,2);
    }
}
