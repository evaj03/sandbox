package Sandbox.Threading.Runnable;

import Sandbox.Threading.Application.IThreadSafeApplication;

/**
 * Created by jonathanevans on 18/12/2015.
 */
public class RunnableApplication implements Runnable {
    private final String                 threadName;
    private final IThreadSafeApplication application;
    private final Thread                 thread;

    public RunnableApplication(final String name, IThreadSafeApplication application) {
        this.threadName = name;
        this.application = application;
        this.thread      = new Thread(this, threadName);
        System.out.println("Creating thread: " + threadName);
    }


    @Override
    public void run() {
        System.out.println("Running " + threadName);

        //try {
            for (int i = 0; i < 50; i++) {
                System.out.println("Thread " + threadName + ", " + i);
                application.write(threadName, 50 + i);
                //Thread.sleep(50);
            }
        //} catch (InterruptedException e) {
        //    System.out.println("Thread " + threadName + " interrupred.");
        //}

        System.out.println("Thread " + threadName + " exiting.");
    }


    public void start() {
        System.out.println("Starting " + threadName);
        if (thread != null) {
            thread.start();
        }
    }
}