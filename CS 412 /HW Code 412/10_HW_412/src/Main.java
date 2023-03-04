import java.util.ArrayList;

public class Main {
    private int threadcount;
    private ArrayList<Thread> threads;
    private int iterations;
    private int successes;

    public static void main(String[] args) {
        (new Main()).go(args);
    }
    private void go(String [] args) {
        if (Integer.parseInt(args[0]) > 1000 || Integer.parseInt(args[1]) > 100000) {
            System.out.println("numbers inputted are too big");
        } else {
            successes = 0;
            threadcount = Integer.parseInt(args[0]);
            threads = new ArrayList<Thread>();
            iterations = Integer.parseInt(args[1]);
            for (int i = 0; i < threadcount; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < iterations; j++) {
                            double x = Math.random();
                            double y = Math.random();
                            if (Math.sqrt(x * x + y * y) < 1) {
                                successes++;
                            }
                        }
                    }
                });
                threads.add(thread);
                thread.start();
            }
            for (Thread th : threads) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println((double) successes / (double) iterations);
            System.out.println("iterations " + iterations);
            System.out.println("succcesses: " + successes);
            System.out.println("threads: " + threadcount);
            double result = ((double) 4 * ((double) successes / ((double) iterations * (double) threadcount)));
            String output = String.format(" pie is maybe: %.10f", result);
            System.out.println(output);
        }
    }
}
