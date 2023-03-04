public abstract class Shape3D extends Shape implements ThreeDimensional{
    public Shape3D(String type, int size) {
        super(type, size);
    }

    public String toString() {
        return "3D shape " + type + ", size = " + size + ", volume = " + this.volume() + ", surface area = " + this.surfaceArea();
    }
}