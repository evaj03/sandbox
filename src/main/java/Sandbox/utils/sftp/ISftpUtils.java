package Sandbox.utils.sftp;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISftpUtils {
    public String getSftpHost();
    public void setSftpHost(final String sftpHost);
    public int getSftpPort();
    public void setSftpPort(final int sftpPort);
    public String getSftpWorkingDirectory();
    public void setSftpWorkingDirectory(final String sftpWorkingDirectory);
    public String getSftpUsername();
    public void setSftpUsername(final String sftpUsername);
    public String getSftpPassword();
    public void setSftpPassword(final String sftpPassword);
    public String getSourceFilename();
    public void setSourceFilename(final String sourceFilename);
    public String getTarget();
    public void setTarget(final String target);
    public void download() throws JSchException, SftpException, IOException;
    public void upload() throws FileNotFoundException, JSchException, SftpException;
    public String toString();
    public ISftpUtils autoConfigure();
}
