public abstract class Shape2D extends Shape implements TwoDimensional{
    public Shape2D(String type, int size) {
        super(type, size);
    }



    @Override
    public String toString() {
        return String.format("2D shape %5s size = %4d area = %.2f perimeter = %.2f", type, size, area(), perimeter());
    }
}
