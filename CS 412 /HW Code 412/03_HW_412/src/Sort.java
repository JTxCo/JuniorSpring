public abstract class Sort {

    int[] data;
    String name;
    long startTime;
    long stopTime;


    public abstract void sortData();

    void printData(){
        int N= data.length;
        for(int i=0; i<N;i++){
            System.out.println(data[i] +"\n");
        }

    }

    boolean isSorted(){
        int current;
        int behind;
        int N= data.length;
        for(int i=1; i<N;i++){
            current=i+1;
            behind=i;
            if(behind>current){
                return false;
            }
        }
        return true;
    }

    void swap(int i, int j){
       int temp =data[i];
        data[i]=data[j];
        data[j]=temp;
    }


    long getRuntime() {
        return stopTime-startTime;
    }
    void startTimer(){
        startTime = System.nanoTime();
    }
    void stopTimer(){
        stopTime = System.nanoTime( );
    }

    int[] getData( ){
        return data;
    }

    String getName(){
        return name;
    }

    void setData(int data[]){
        this.data=data;
    }


}
