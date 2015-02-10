package Sandbox.CallableTest;

import java.util.concurrent.Callable;

public class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("MyCallable's Asynchronous Callable... so there!");
        return true;
    }
}
