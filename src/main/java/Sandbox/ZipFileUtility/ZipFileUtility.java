package Sandbox.ZipFileUtility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileUtility {
    private final String source = "C:\\Temp\\App_Data\\PracticeConfigsDummy.zip";
    private final String target = "C:\\Temp\\App_Data\\PracticeConfigurations";

    public void sudoMain() throws IOException, JAXBException {
        System.out.println("Starting @ " + new Date());

        List<ZipFileEntry> zippedFiles = new ArrayList<ZipFileEntry>();

        ZipFile zipFile = new ZipFile(source);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        int count = 0;

        JAXBContext jaxbContext  = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            ZipFileEntry   zipFileEntry = new ZipFileEntry();

            zipFileEntry.setFileNumber(count);
            zipFileEntry.setFileName(entry.getName());

            //InputStream stream = zipFile.getInputStream(entry);
            zipFileEntry.setXmlPayload((Configuration) unmarshaller.unmarshal(zipFile.getInputStream(entry)));
            ++count;

            zippedFiles.add(zipFileEntry);
        }

        System.out.println("Processed Files # " + count);

        System.out.println("Ending @ " + new Date());

        for (ZipFileEntry zfe : zippedFiles) {
            System.out.println("File # " + zfe.getFileNumber());
            System.out.println("File Name: " + zfe.getFileName());
            System.out.println("Payload: " + zfe.getXmlPayload());
        }
    }
}
