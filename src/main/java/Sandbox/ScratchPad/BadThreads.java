package Sandbox.ScratchPad;

public class BadThreads {

    static String message;

    private static class CorrectorThread
            extends Thread {

        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
            // Key statement 1:
            message = "Mares do eat oats.";
        }
    }

    public static void main(String args[]) throws InterruptedException {

        CorrectorThread c = new CorrectorThread();

        c.start();
        message = "Mares do not eat oats.";
        Thread.sleep(2000);
        c.join();
        // Key statement 2:
        System.out.println(message);
    }
}