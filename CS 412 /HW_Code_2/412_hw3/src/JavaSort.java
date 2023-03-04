import java.util.Arrays;

public class JavaSort extends Sort{
    public JavaSort(){
        name = "JavaSort";
    }
    @Override
    public void sortData() {
        startTimer();
        Arrays.sort(getData());
        stopTimer();
    }
}
