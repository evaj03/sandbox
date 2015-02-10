package Sandbox.CallableTest;


import java.util.concurrent.Callable;

public class MyRunnableCallable implements Runnable, Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("MyRunnableCallable's Asynchronous Callable... so there!");
        return true;
    }

    
    @Override
    public void run() {
        System.out.println("MyRunnableCallable's Asynchronous Runable... so there!");
    }
}
