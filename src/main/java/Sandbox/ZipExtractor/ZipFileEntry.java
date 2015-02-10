package Sandbox.ZipExtractor;

public class ZipFileEntry {
    private int           fileNumber;
    private String        fileName;
    private Configuration xmlPayload;


    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Configuration getXmlPayload() {
        return xmlPayload;
    }

    public void setXmlPayload(Configuration xmlPayload) {
        this.xmlPayload = xmlPayload;
    }
}
