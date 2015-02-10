package Sandbox.Concurrency.BlockingQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;

public class SynchronousBlockingQueue {
    private static BlockingQueue<Future> queue = new SynchronousQueue<Future>();

    public static BlockingQueue<Future> getQueue() {
        return queue;
    }
}
