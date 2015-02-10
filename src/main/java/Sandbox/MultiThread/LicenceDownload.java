package Sandbox.MultiThread;

import Sandbox.utils.sftp.ISftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Logger;

public class LicenceDownload implements ILicenceDownload {

    private final static Logger logger            = Logger.getLogger(LicenceDownload.class.getName());
    //private AsyncContext        asyncContext;
    private ISftpUtils sftpUtils         = null;


    public LicenceDownload() {
    }


    //public DownloadPracticeLicences(AsyncContext asyncCtx) {
    //    this.asyncContext = asyncCtx;
    //}


    public void setSftpUtils(ISftpUtils sftpUtils) {
        this.sftpUtils = sftpUtils;
    }


    @Override
    public void downloadLicenceZipFile() throws IOException {
        logger.info("downloadLicenceZipFile run at '" + new Date() + "'");

        if (sftpUtils == null) {
            throw new IllegalStateException("No SFTP details have been supplied.");
        }

        try {
            sftpUtils.download();
        } catch (IOException | SftpException | JSchException | NullPointerException ex) {
            logger.severe(ex.toString());

            throw new IOException(ex.getMessage());
        }
    }


    @Override
    public boolean isFilePresent() {
        return Files.exists(Paths.get(sftpUtils.getTarget()));
    }
}
