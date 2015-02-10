package Sandbox.MultiThread;


import Sandbox.utils.sftp.ISftpUtils;
import Sandbox.utils.sftp.SftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Multithread {
    public static void main(String args[]) throws IOException, JAXBException {
        Multithread multithread = new Multithread();

        ILicenceDownload licenceDownload     = new LicenceDownload();
        ILicenceGeneration licenceGeneration = multithread.configurePracticeLicensing();

        licenceDownload.setSftpUtils(multithread.configureSftp());

        try {
            licenceDownload.downloadLicenceZipFile();
        } catch (JSchException | SftpException ex) {
            throw new IOException("Failed to download zip file.");
        }

        int totalLicences = licenceGeneration.extract();

        //licenceGeneration.merge();

        //for(int thread = 0; thread < 100; thread++) {
            licenceGeneration.merge();
            //RunnableThread runnableThread = new RunnableThread("Thread"+thread);
            //runnableThread.start();
        //}
    }


    private ISftpUtils configureSftp() {
        ISftpUtils sftpUtils = new SftpUtils().autoConfigure();

        sftpUtils.setSftpWorkingDirectory("/home/ftp/downloads/");
        sftpUtils.setSourceFilename("dummy.zip");
        sftpUtils.setTarget("C:\\Temp\\App_Data\\PracticeConfigsDummy.zip");

        return sftpUtils;
    }


    private ILicenceGeneration configurePracticeLicensing() {
        ILicenceGeneration practiceLicensing = new LicenceGeneration();

        return practiceLicensing.autoConfigure();
    }
}
