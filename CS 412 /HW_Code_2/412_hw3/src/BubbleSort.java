public class BubbleSort extends Sort{
   public BubbleSort(){
       name = "BubbleSort";
   }
    @Override
    public void sortData() {
        startTimer();
        int length = getData().length;
        int [] data = getData();
        boolean stuff = true;
        while (stuff){
            stuff = false;
            for (int i= 0; i< length-1; i++){
                if (data[i] > data[i+1]){
                    swap(i,i+1);
                    stuff=true;
                }
            }

        }
        stopTimer();
    }

}
