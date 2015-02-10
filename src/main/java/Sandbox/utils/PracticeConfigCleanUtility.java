package Sandbox.utils;


import Sandbox.utils.file.FileUtils;
import Sandbox.utils.file.ZipFileEntry;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PracticeConfigCleanUtility {
    private final String source = "C:\\Temp\\PracticeConfigsDummy\\PracticeConfigsDummy.zip";
    private final String target = "C:\\Temp\\PracticeConfigsDummy\\dummy";

    private List<ZipFileEntry> practiceLicences;
    private List<String>       licenceXmlOnly;

    public PracticeConfigCleanUtility() {
        try {
            practiceLicences = FileUtils.readZipFile(source);

            if (removeStaticXmlFromConfiguration()) {
                writeToFile();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }


    private boolean removeStaticXmlFromConfiguration() {
        boolean status = false;

        try {
            licenceXmlOnly = new ArrayList<String>();

            for (ZipFileEntry practiceLicense : practiceLicences) {
                //System.out.println("PAY LOAD : " + practiceLicense.getXmlPayload());
                //System.out.println("Static XML: " + staticXmlNode);

                String theFile = Sandbox.utils.XmlUtils.marshal(practiceLicense.getXmlPayload());

                // remove StaticData Node
                if (theFile.contains("<static-data")) {
                    String everythingUpToOpeningStaticDataNode = theFile.substring(0, theFile.indexOf("<static-data"));
                    String everythingFromClosingStaticDataNode = theFile.substring(theFile.lastIndexOf("</static-data>") + 14);

                    theFile = everythingUpToOpeningStaticDataNode + everythingFromClosingStaticDataNode;
                }

                // remove Practice Node
                if (theFile.contains("<practice-data")) {
                    String everythingUpToOpeningPracticeDataNode = theFile.substring(0, theFile.indexOf("<practice-data"));
                    String everythingFromClosingPracticeDataNode = theFile.substring(theFile.lastIndexOf("</practice-data>") + 16);

                    theFile = everythingUpToOpeningPracticeDataNode + everythingFromClosingPracticeDataNode;
                }

                //System.out.println( "COMBI : " + theFile);

                licenceXmlOnly.add(theFile);
            }

            status = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return status;
    }


    private boolean writeToFile() {
        try {
            for (ZipFileEntry zipFileEntry : practiceLicences) {
                FileUtils.createPath(target);

                PrintWriter printWriter = new PrintWriter(target + File.separator + zipFileEntry.getFileName(), "UTF-8");

                printWriter.println(licenceXmlOnly.get(zipFileEntry.getFileNumber()-1));

                printWriter.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return false;
    }
}
