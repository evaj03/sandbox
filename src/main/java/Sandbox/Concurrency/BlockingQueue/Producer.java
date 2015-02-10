package Sandbox.Concurrency.BlockingQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

public class Producer {
    protected BlockingQueue<Future> queue  = null;


    public Producer() {
        this.queue = SynchronousBlockingQueue.getQueue();
    }


    public void addToQueue(Future future) throws InterruptedException {
        if (future != null) {
            queue.put(future);
        }
    }
}
