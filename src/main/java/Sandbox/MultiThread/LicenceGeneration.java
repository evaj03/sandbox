package Sandbox.MultiThread;


import Sandbox.ZipExtractor.Configuration;
import Sandbox.ZipExtractor.StaticData;
import Sandbox.utils.GenericSettings;
import Sandbox.utils.XmlUtils;
import Sandbox.utils.file.FileUtils;
import Sandbox.utils.file.ZipFileEntry;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class LicenceGeneration implements ILicenceGeneration {

    private static final Logger logger           = Logger.getLogger(LicenceGeneration.class.getName());

    private String             source    = null;
    private String             target    = null;
    private String             staticXml = null;
    private int                current;
    private int                total;
    private List<ZipFileEntry> practiceLicences;
    private String             staticXmlNode;
    private StaticData staticData;
    private ExecutorService    executorService;



    @Override
    public void setFullyQualifiedSource(final String source) {
        this.source = source;
    }


    @Override
    public String getFullyQualifiedSource() {
        return this.source;
    }


    @Override
    public void setFullyQualifiedTarget(final String target) {
        this.target = target;
    }


    @Override
    public String getFullyQualifiedTarget() {
        return this.target;
    }


    @Override
    public void setFullyQualifiedStaticPracticeData(final String staticPracticeData) {
        this.staticXml = staticPracticeData;
    }


    @Override
    public String getFullyQualifiedStaticPracticeData() {
        return this.staticXml;
    }


    @Override
    public int getCurrent() {
        return this.current;
    }


    @Override
    public int getTotal() {
        return this.total;
    }


    @Override
    public ILicenceGeneration autoConfigure() {
        ILicenceGeneration practiceLicensing = new LicenceGeneration();

        String source = "C:\\Temp\\App_Data\\PracticeConfigsDummy.zip";
        String target = "C:\\Temp\\App_Data\\PracticeConfigurations\\";
        String staticXml = "C:\\Temp\\App_Data\\StaticPracticeData.xml";

        practiceLicensing.setFullyQualifiedSource(source);
        practiceLicensing.setFullyQualifiedTarget(target);
        practiceLicensing.setFullyQualifiedStaticPracticeData(staticXml);

        return practiceLicensing;
    }


    @Override
    public int extract() throws IOException, JAXBException {
        if (Files.exists(Paths.get(target))) {
            practiceLicences = FileUtils.readZipFile(source);

            setStaticXmlNode();

            return practiceLicences.size();
        }

        return -1;
    }


    @Override
    public boolean merge() throws IOException, JAXBException {
        //setStaticXmlNode();

        //return mergeConfigurationsWithStaticXmlNode() && writeToFile();

        int maxThreads = 500;

        for(int thread = 0; thread < maxThreads; thread++) {
            RunnableThread runnableThread = new RunnableThread("Thread" + thread, thread, maxThreads);
            runnableThread.start();
        }

        return writeToFile();

    }


    @Override
    public void callableMerge(int fileNo) throws IOException, JAXBException {
        //setStaticXmlNode();

        if (mergeConfigurationsWithStaticXmlNode(fileNo)) {
            writeToFile(fileNo);
        }
    }


    private void setStaticXmlNode() throws IOException, JAXBException{
        FileInputStream in = null;

        try {
            in = new FileInputStream(staticXml);
            staticXmlNode = new Scanner(in, GenericSettings.GLOBAL_UTF).useDelimiter(GenericSettings.INPUT_START_REGEX).next();
            staticData    = XmlUtils.unMarshal(staticXmlNode, StaticData.class);
        }
        finally {
            if (in != null){
                in.close();
            }
        }
    }


    private boolean mergeConfigurationsWithStaticXmlNode(final int fileNo) {
        boolean status = false;

        //try {
            //StaticData staticData = XmlUtils.unMarshal(staticXmlNode, StaticData.class);

            //for (ZipFileEntry practiceLicense : practiceLicences) {
        if (practiceLicences.get(fileNo) != null) {
            ZipFileEntry practiceLicence = practiceLicences.get(fileNo);
            Configuration configuration  = practiceLicence.getXmlPayload();

            //Configuration configuration = practiceLicense.getXmlPayload();

            configuration.setAny(staticData);

            practiceLicence.setXmlPayload(configuration);
            //}

            status = true;
        }
        //} catch (JAXBException e) {
        //    e.printStackTrace();
        //}

        return status;
    }


    private boolean mergeConfigurationsWithStaticXmlNode() {
        boolean status = false;

        for (ZipFileEntry practiceLicence : practiceLicences) {
            Configuration configuration = practiceLicence.getXmlPayload();

            configuration.setAny(staticData);

            practiceLicence.setXmlPayload(configuration);
        }

        status = true;

        return status;
    }


    public class RunnableThread implements Runnable {
        private Thread t;
        private String threadName;
        private int    threadNo;
        private int    maxThreads;


        RunnableThread(String name, int threadNo, int maxThreads) {
            this.threadName = name;
            this.threadNo   = threadNo;
            this.maxThreads = maxThreads;

            System.out.println("Creating " + threadName + " - " + threadNo + " of " + maxThreads + " threads.");
        }


        @Override
        public void run() {
            System.out.println("Running " + threadName + " - " + threadNo + " of " + maxThreads + " threads.");

            int batchSize  = (int) Math.floor(practiceLicences.size() / this.maxThreads);
            int batchStart = threadNo * batchSize;
            int batchEnd   = (threadNo + 1) == this.maxThreads ? practiceLicences.size() : (threadNo + 1) * batchSize;

            for( ; batchStart < batchEnd; batchStart++) {
                ZipFileEntry practiceLicence = practiceLicences.get(batchStart);
                System.out.println("Thread: " + threadName + ", " + batchStart);

                Configuration configuration = practiceLicence.getXmlPayload();

                configuration.setAny(staticData);

                practiceLicence.setXmlPayload(configuration);
            }

//            int i = 0;
//            for (ZipFileEntry practiceLicence : practiceLicences) {
//                System.out.println("Thread: " + threadName + ", " + ++i);
//
//                Configuration configuration = practiceLicence.getXmlPayload();
//
//                configuration.setAny(staticData);
//
//                practiceLicence.setXmlPayload(configuration);
//            }


//                for (int i = 150; i > 0; i--) {
//                    System.out.println("Thread: " + threadName + ", " + i);
//                    // Let the thread sleep for a while.
//                    Thread.sleep(3000);
//                }
            System.out.println("Thread " + threadName + " exiting.");
        }

        public void start() {
            System.out.println("Starting " + threadName);
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }


    //@Override
    //public void run() {

    //}


    private boolean writeToFile() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        FileUtils.createPath(target);
        PrintWriter printWriter;

        for (ZipFileEntry zipFileEntry : practiceLicences) {
            printWriter = new PrintWriter(target + File.separator + zipFileEntry.getFileName(), GenericSettings.GLOBAL_UTF);
            printWriter.println(XmlUtils.marshal(zipFileEntry.getXmlPayload()));
            printWriter.close();

            current++;
        }

        return true;
    }


    private void writeToFile(int fileNo) throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        FileUtils.createPath(target);

        PrintWriter  printWriter;
        ZipFileEntry zipFileEntry = practiceLicences.get(fileNo);

        printWriter = new PrintWriter(target + File.separator + zipFileEntry.getFileName(), GenericSettings.GLOBAL_UTF);

        printWriter.println(XmlUtils.marshal(zipFileEntry.getXmlPayload()));
        printWriter.close();

        current++;
    }


    // Just for display purposes.
//    private void printLicences() {
//        try {
//            for (ZipFileEntry entry : practiceLicences) {
//                System.out.println(String.format("%d. File '%s' Contents: '%s'%n", entry.getFileNumber(), entry.getFileName(), XmlUtils.marshal(entry.getXmlPayload())));
//            }
//        }
//        catch (JAXBException ex) {
//            ex.printStackTrace();
//        }
//    }
}
