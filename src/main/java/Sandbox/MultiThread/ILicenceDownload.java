package Sandbox.MultiThread;


import Sandbox.utils.sftp.ISftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


import java.io.IOException;

public interface ILicenceDownload {
    public void    downloadLicenceZipFile() throws JSchException, SftpException, IOException;
    public boolean isFilePresent();
    public void    setSftpUtils(ISftpUtils iSftpUtils);
}
