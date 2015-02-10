package Sandbox.MultiThread;


import Sandbox.MultiThread.ILicenceDownload;
import Sandbox.MultiThread.ILicenceGeneration;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.logging.Logger;

public class LicenceRefresh implements ILicenceRefresh {
    private static final Logger logger = Logger.getLogger(LicenceRefresh.class.getName());

    private ILicenceDownload licenceDownload;
    private ILicenceGeneration licenceGeneration;


    @Override
    public void setLicenceDownload(final ILicenceDownload licenceDownload) {
        this.licenceDownload = licenceDownload;
    }


    @Override
    public void setLicenceGeneration(final ILicenceGeneration practiceLicensing) {
        this.licenceGeneration = practiceLicensing;
    }


    @Override
    public Boolean call() throws Exception {
        this.licenceDownload.downloadLicenceZipFile();
        this.licenceGeneration.extract();
        return this.licenceGeneration.merge();
    }


    @Override
    public void run() {
        try {
            this.licenceDownload.downloadLicenceZipFile();
            this.licenceGeneration.extract();
            this.licenceGeneration.merge();
        } catch (IOException | JAXBException | SftpException | JSchException ex) {
            logger.severe(ex.toString());
        }
    }
}
