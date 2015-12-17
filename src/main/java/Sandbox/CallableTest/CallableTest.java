package Sandbox.CallableTest;


import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {
        CallableTest c = new CallableTest();

        try {
            c.testCallableString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void testCallableString() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());
    }


    public void testCallableBoolean() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return true;
            }
        });

        System.out.println("future.get() = " + future.get());
    }


    public void testCallableInt() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return 1;
            }
        });

        System.out.println("future.get() = " + future.get());
    }


    public void testMyCallable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new MyCallable());

        System.out.println("MyCallable future.get() = " + future.get());
    }


    public void testMyRunnableCallable() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        MyRunnableCallable myRunnableCallable = new MyRunnableCallable();

        Future future = executorService.submit((Callable) myRunnableCallable);

        System.out.println("MyRunnableCallable future.get() = " + future.get());
    }

}
