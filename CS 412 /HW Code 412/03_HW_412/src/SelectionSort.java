import static java.nio.file.Files.size;

public class SelectionSort extends Sort {
public SelectionSort(){
    name = "SelectionSort";
}
    @Override
    public void sortData() {
    startTimer();
        int N = data.length;
        for (int i= 0; i<N-1; i++){
            int index =i;
            for(int j=i+1; j<N; j++){
                if(data[j]< data[index]){
                    index = j;
                }
            }
            if(index!=i){
                swap(i,index);
            }
        }
    stopTimer();
    }
}
