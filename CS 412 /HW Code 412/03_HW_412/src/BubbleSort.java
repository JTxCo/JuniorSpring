import static java.nio.file.Files.size;
public class BubbleSort extends Sort {

    public BubbleSort() {
        name = "BubbleSort";
    }
    @Override
    public void sortData() {
        startTimer();
        int N = data.length;
        boolean swapped = true;
        while (swapped){
            swapped=false;
            for (int i=0; i<N-1; i++){
                if (data[i] >data[i+1]){
                    swap(i,i+1);
                    swapped = true;
                }
            }
        }
        stopTimer();
    }
}
