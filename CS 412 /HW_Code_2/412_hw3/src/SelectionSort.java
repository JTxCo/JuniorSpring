public class SelectionSort extends Sort{
    public SelectionSort(){
        name = "SelectionSort";
    }
    @Override
    public void sortData(){
        startTimer();
        int [] data = getData();
        int size = data.length;
        for ( int i= 0; i< size-1; i++){
            int index = i;
            for (int j= i+1; j< size; j++){
                if (data[j] < data[index]){
                    index = j;
                }
            }
            if(index !=i){
                swap(i,index);
            }
        }
        stopTimer();
    }
}
