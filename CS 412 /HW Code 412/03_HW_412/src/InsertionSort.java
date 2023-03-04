import static java.nio.file.Files.size;
public class InsertionSort extends Sort{

    public InsertionSort() {
        name = "Insertion Sort";
    }

    @Override
    public void sortData() {
        startTimer();
        int n = data.length;
        for(int i=1; i<n; i++){
            int val= data[i];
            int loc = i-1;
            while(loc>=0 && data[loc]>val){
                data[loc +1] =data[loc];
                loc -=1;
            }
            data[loc + 1] = val;
        }
        stopTimer();
    }
}
