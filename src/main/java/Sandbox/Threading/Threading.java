package Sandbox.Threading;

import Sandbox.Threading.Application.Application;
import Sandbox.Threading.Application.IApplication;
import Sandbox.Threading.Application.IThreadSafeApplication;
import Sandbox.Threading.Application.ThreadSafeApplication;
import Sandbox.Threading.Callable.CallableApplication;
import Sandbox.Threading.Runnable.RunnableApplication;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Create an application that will create a text file and write random text to it.
// Ensure that the application is thread safe
// Call the application in a multi-threaded manner.
public class Threading {


    public static void main(String[] args) {
        Threading threading = new Threading();

        // Set the random text generator.
        IApplication application = new Application();

        application.setRandomText(new RandomText());
        application.setFileName("RandomTextAppOutput.txt");
        application.create();

        for (int i = 0; i < 10; i++) {
            application.write(75);
        }


        // Set the thread-safe random text generator - this is not multi-threaded.
        IThreadSafeApplication threadSafeApplication = new ThreadSafeApplication("RandomTextThreadSafeAppOutput.txt", new RandomText());

        for (int i = 0; i < 20; i++) {
            threadSafeApplication.write(50);
        }


        // Create multi-threaded via runnable.
        for (int i = 0; i < 5; ++i) {
            RunnableApplication runnableApplication = new RunnableApplication("Runnable-Thread-" + i, threadSafeApplication);

            runnableApplication.start();
        }


        final ExecutorService executorService = Executors.newCachedThreadPool();


        // Create multi-threaded via callable.
        for (int i = 0; i < 5; ++i) {
            RunnableApplication executeRunnable        = new RunnableApplication("ER-Thread-" + i, threadSafeApplication);
            RunnableApplication runnableSubmitRunnable = new RunnableApplication("CSR-Callable-Thread-" + i, threadSafeApplication);
            CallableApplication callableSubmitCallable = new CallableApplication("CSC-Callable-Thread-" + i, threadSafeApplication);

            executorService.execute(executeRunnable);

            Future csrFuture = executorService.submit(runnableSubmitRunnable);
            Future cscFuture = executorService.submit(callableSubmitCallable);

            try {
                System.out.println("csrFuture = " + csrFuture.get());
                System.out.println("cscFuture = " + cscFuture.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
