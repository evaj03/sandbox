package Sandbox.MultiThread;


import java.util.concurrent.Callable;

public interface ILicenceRefresh extends Callable<Boolean>, Runnable {

    public void setLicenceDownload(ILicenceDownload licenceDownload);

    public void setLicenceGeneration(ILicenceGeneration licenceGeneration);

    @Override
    public Boolean call() throws Exception;

    @Override
    void run();
}
