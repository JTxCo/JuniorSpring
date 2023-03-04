import java.util.Arrays;

public class JavaSort extends Sort {

    public JavaSort() {
        name = "JavaSort";
    }
    @Override
    public void sortData() {
        this.startTimer();
        Arrays.sort(data);
        this.stopTimer();
    }
}