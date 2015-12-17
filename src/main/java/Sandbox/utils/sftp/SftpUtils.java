//package Sandbox.utils.sftp;
//
//
//
//public class SftpUtils implements ISftpUtils {
//    private final static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SftpUtils.class.getName());
//
//    private String sftpHost;
//    private int    sftpPort;
//    private String sftpWorkingDirectory;
//    private String sftpUsername;
//    private String sftpPassword;
//    private String sourceFilename;
//    private String target;
//
//    private JSch jSch        = null;
//    private Session session     = null;
//    private Channel channel     = null;
//    private ChannelSftp channelSftp = null;
//    private Properties  config      = null;
//
//
//    @Override
//    public String getSftpHost() {
//        return sftpHost;
//    }
//
//
//    @Override
//    public void setSftpHost(final String sftpHost) {
//        this.sftpHost = sftpHost;
//    }
//
//
//    @Override
//    public int getSftpPort() {
//        return sftpPort;
//    }
//
//
//    @Override
//    public void setSftpPort(final int sftpPort) {
//        this.sftpPort = sftpPort;
//    }
//
//
//    @Override
//    public String getSftpWorkingDirectory() {
//        return sftpWorkingDirectory;
//    }
//
//
//    @Override
//    public void setSftpWorkingDirectory(final String sftpWorkingDirectory) {
//        this.sftpWorkingDirectory = sftpWorkingDirectory;
//    }
//
//
//    @Override
//    public String getSftpUsername() {
//        return sftpUsername;
//    }
//
//
//    @Override
//    public void setSftpUsername(final String sftpUsername) {
//        this.sftpUsername = sftpUsername;
//    }
//
//
//    @Override
//    public String getSftpPassword() {
//        return sftpPassword;
//    }
//
//
//    @Override
//    public void setSftpPassword(final String sftpPassword) {
//        this.sftpPassword = sftpPassword;
//    }
//
//
//    @Override
//    public String getSourceFilename() {
//        return sourceFilename;
//    }
//
//
//    @Override
//    public void setSourceFilename(final String sourceFilename) {
//        this.sourceFilename = sourceFilename;
//    }
//
//
//    @Override
//    public String getTarget() {
//        return target;
//    }
//
//
//    @Override
//    public void setTarget(final String target) {
//        this.target = target;
//    }
//
//
//    @Override
//    public void download() throws JSchException, SftpException, IOException, NullPointerException {
//        try {
//            jSch    = new JSch();
//            config  = new Properties();
//            session = jSch.getSession(sftpUsername, sftpHost, sftpPort);
//
//            session.setPassword(sftpPassword);
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//
//            channel = session.openChannel("sftp");
//            channel.connect();
//
//            channelSftp = (ChannelSftp) channel;
//            channelSftp.cd(sftpWorkingDirectory);
//
//            byte[] buffer = new byte[1024];
//
//            BufferedInputStream  bufferedInputStream  = new BufferedInputStream(channelSftp.get(sourceFilename));
//            File                 newFile              = new File(target);
//            OutputStream         outputStream         = new FileOutputStream(newFile);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//            int                  readCount;
//
//            while((readCount = bufferedInputStream.read(buffer)) > 0) {
//                bufferedOutputStream.write(buffer, 0, readCount);
//            }
//
//            bufferedInputStream.close();
//            bufferedOutputStream.close();
//        }
//        catch (SftpException | JSchException | IOException | NullPointerException ex) {
//            logger.severe(ex.toString());
//            throw ex;
//        } finally {
//            channelSftp.exit();
//            ((ChannelSftp) channel).exit();
//            session.disconnect();
//        }
//    }
//
//
//    @Override
//    public void upload() throws FileNotFoundException, JSchException, SftpException {
//        try {
//            jSch    = new JSch();
//            config  = new Properties();
//            session = jSch.getSession(sftpUsername, sftpHost, sftpPort);
//
//            session.setPassword(sftpPassword);
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//
//            channel = session.openChannel("sftp");
//            channel.connect();
//
//            channelSftp = (ChannelSftp) channel;
//            channelSftp.cd(sftpWorkingDirectory);
//
//            File uploadFile = new File(target);
//
//            channelSftp.put(new FileInputStream(uploadFile), uploadFile.getName());
//        }
//        catch (SftpException | JSchException | IOException ex) {
//            logger.severe(ex.toString());
//            throw ex;
//        } finally {
//            channelSftp.exit();
//            ((ChannelSftp) channel).exit();
//            session.disconnect();
//        }
//    }
//
//
//    @Override
//    public String toString() {
//        StringBuilder params = new StringBuilder();
//
//        params.append("SftpUtils: Host[").append(sftpHost)
//                .append("] Port[").append(sftpPort)
//                .append("] Working Directory[").append(sftpWorkingDirectory)
//                .append("] Username[").append(sftpUsername)
//                .append("] Password[").append(sftpPassword)
//                .append("] Source Filename[").append(sourceFilename)
//                .append("] Target Filename[").append(target);
//
//        return params.toString();
//    }
//
//
//    @Override
//    public ISftpUtils autoConfigure() {
//        ISftpUtils sftpUtils = new SftpUtils();
//
//        sftpUtils.setSftpHost("192.168.0.46");
//        sftpUtils.setSftpPort(Integer.parseInt("22"));
//        sftpUtils.setSftpUsername("ftp");
//        sftpUtils.setSftpPassword("ftp");
//
//        return sftpUtils;
//    }
//}
