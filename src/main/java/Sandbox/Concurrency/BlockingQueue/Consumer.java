package Sandbox.Concurrency.BlockingQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

public class Consumer implements Runnable {
    protected BlockingQueue<Future> queue = null;


    public Consumer() {
        this.queue = SynchronousBlockingQueue.getQueue();
    }


    @Override
    public void run() {
        try {
            while (true) {
                Future future = queue.take();
                process(future);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    private void process(Future future) {
        System.out.println("Attempting to consume " + future.toString());
    }
}
