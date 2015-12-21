package Sandbox.Threading.Callable;

import Sandbox.Threading.Application.IThreadSafeApplication;

import java.util.concurrent.Callable;

/**
 * Created by jonathanevans on 21/12/2015.
 */
public class CallableApplication implements Callable<String> {
    private final String                 threadName;
    private final IThreadSafeApplication application;


    public CallableApplication(final String name, IThreadSafeApplication application) {
        this.threadName = name;
        this.application = application;
        System.out.println("CallableApplication - Creating thread: " + threadName);
    }


    @Override
    public String call() throws Exception {
        System.out.println("CallableApplication:call - Running " + threadName);

        for (int i = 0; i < 50; i++) {
            System.out.println("CallableApplication:call - Thread " + threadName + ", " + i);

            application.write(threadName, 50 + i);
        }

        System.out.println("CallableApplication:call - Thread " + threadName + " exiting.");

       return threadName;
    }

//    public void asExecuteRunnable() {
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("CallableApplication:asExecuteRunnable - Running " + threadName);
//
//                for (int i = 0; i < 50; i++) {
//                    System.out.println("CallableApplication:asExecuteRunnable - Thread " + threadName + ", " + i);
//                    application.write(threadName, 50 + i);
//                }
//
//                System.out.println("CallableApplication:asExecuteRunnable - Thread " + threadName + " exiting.");
//            }
//        });
//
//        executorService.shutdown();
//    }


//    public void asSubmitRunnable() throws ExecutionException, InterruptedException {
//        Future future = executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("CallableApplication:asSubmitRunnable - Running " + threadName);
//
//                for (int i = 0; i < 50; i++) {
//                    System.out.println("CallableApplication:asSubmitRunnable - Thread " + threadName + ", " + i);
//                    application.write(threadName, 50 + i);
//                }
//
//                System.out.println("CallableApplication:asSubmitRunnable - Thread " + threadName + " exiting.");
//            }
//        });
//
//        System.out.println("future.get() = " + future.get());
//
//        executorService.shutdown();
//    }


//    public void asSubmitCallable() throws ExecutionException, InterruptedException {
//        Future future = executorService.submit(new Callable() {
//            @Override
//            public Object call() throws Exception {
//                System.out.println("CallableApplication:asSubmitCallable - Running " + threadName);
//
//                for (int i = 0; i < 50; i++) {
//                    System.out.println("CallableApplication:asSubmitCallable - Thread " + threadName + ", " + i);
//                    application.write(threadName, 50 + i);
//                }
//
//                System.out.println("CallableApplication:asSubmitCallable - Thread " + threadName + " exiting.");
//
//                return threadName;
//            }
//        });
//
//        System.out.println("future.get() = " + future.get());
//
//        executorService.shutdown();
//    }
}