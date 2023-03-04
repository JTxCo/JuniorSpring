public class InsertionSort extends Sort{
    public InsertionSort(){
        name = "InsertionSort";
    }
    @Override
    public void sortData() {
        startTimer();
        int[] data  = getData();
        int n = data.length;
        for( int i=0; i< n; i++){
            int temp = data[i];
            int loc = i-1;
            while( loc >= 0 && data[loc] > temp){
                data[loc+1] = data[loc];
                loc-=1;
            }
            data[loc+1] = temp;
        }
        stopTimer();
    }
}
