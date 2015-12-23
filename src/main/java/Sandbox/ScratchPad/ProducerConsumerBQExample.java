package Sandbox.ScratchPad;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerBQExample {
    public static void main(String[] args) {
        BlockingQueue<String> drop = new SynchronousQueue<String>();

        (new Thread(new ProducerBQ(drop))).start();
        (new Thread(new ConsumerBQ(drop))).start();
    }
}