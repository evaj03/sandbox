//package Sandbox.ZipExtractor;
//
//
//import org.apache.commons.lang3.time.StopWatch;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Scanner;
//import java.util.concurrent.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//public class ProcessZip {
//    static String theZipFile = "C:\\Temp\\App_Data\\PracticeConfigsDummy.zip";
//    static String staticXml  = "C:\\Temp\\App_Data\\StaticPracticeData.xml";
//    String        staticXmlNode;
//    List<ZipFileEntry> practiceLicences;
//    StopWatch stopWatch = new StopWatch();
//
//    public static void main(String [] args) {
//
//        ProcessZip processZip = new ProcessZip();
//
//        processZip.process();
//    }
//
//
//    public void process() {
//        //Open the zip file
//        try {
//            setStaticXmlNode();
//
//            stopWatch.start();
//            practiceLicences = readZipFile(theZipFile);
//            stopWatch.stop();
//
//            System.out.println("Reading of Zip File took: " + stopWatch.toString());
//            stopWatch.reset();
//
//            stopWatch.start();
//            mergeConfigurationsWithStaticXmlNode();
//            stopWatch.stop();
//
//            System.out.println("Merge of XML took: " + stopWatch.toString());
//            stopWatch.reset();
//
//            // Do this bit in parallel!
//            stopWatch.start();
//            callableWriteToFile();
//            //partition();
//            stopWatch.stop();
//
//            System.out.println("File generation took: " + stopWatch.toString());
//            stopWatch.reset();
//
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        catch(JAXBException ex) {
//            ex.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static List<ZipFileEntry> readZipFile(String zippedFile) throws IOException, JAXBException {
//        final ZipFile zipFile          = new ZipFile(zippedFile);
//        int                fileNumber  = 0;
//        List<ZipFileEntry> zippedFiles = new ArrayList<ZipFileEntry>();
//
//        try {
//            final Enumeration<? extends ZipEntry> zipEntries   = zipFile.entries();
//            JAXBContext jaxbContext  = JAXBContext.newInstance(Configuration.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            while (zipEntries.hasMoreElements()) {
//                final ZipEntry zipEntry     = zipEntries.nextElement();
//                ZipFileEntry   zipFileEntry = new ZipFileEntry();
//
//                zipFileEntry.setFileNumber(++fileNumber);
//                zipFileEntry.setFileName(zipEntry.getName());
//                zipFileEntry.setXmlPayload((Configuration) unmarshaller.unmarshal(zipFile.getInputStream(zipEntry)));
//
//                zippedFiles.add(zipFileEntry);
//            }
//        }
//        finally {
//            zipFile.close();
//        }
//
//        return zippedFiles;
//    }
//
//
//    private void setStaticXmlNode() throws IOException {
//        FileInputStream in = null;
//
//        try {
//            in = new FileInputStream(staticXml);
//            staticXmlNode = new Scanner(in, GenericSettings.GLOBAL_UTF).useDelimiter(GenericSettings.INPUT_START_REGEX).next();
//        }
//        finally {
//            if (in != null){
//                in.close();
//            }
//        }
//    }
//
//
//    private boolean mergeConfigurationsWithStaticXmlNode() {
//        boolean status = false;
//
//        try {
//            StaticData staticData = XmlUtils.unMarshal(staticXmlNode, StaticData.class);
//
//            for (ZipFileEntry practiceLicense : practiceLicences) {
//                Configuration configuration = practiceLicense.getXmlPayload();
//
//                configuration.setAny(staticData);
//
//                practiceLicense.setXmlPayload(configuration);
//
//                //System.out.println(XmlUtils.marshal(configuration));
//            }
//
//            status = true;
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        return status;
//    }
//
//
////    private void partition() {
////        final int totalPartitions = 1;
////
////        List<ExecutorService> executorServices = new ArrayList<ExecutorService>(totalPartitions);
////
////        int fromIndex = 0;
////        int toIndex   = 0;
////
////        for (int count = 0; count < totalPartitions; count++) {
////            toIndex += (practiceLicences.size() / totalPartitions) - 1;
////
////            if (count == totalPartitions - 1) {
////                toIndex = practiceLicences.size();
////            }
////
////            System.out.println("fromIndex: " + fromIndex + " toIndex:" + toIndex);
////
////            List<ZipFileEntry> partition = practiceLicences.subList(fromIndex, toIndex);
////
////            fromIndex = toIndex;
////
////            //allPartitions.add(partition);
////
////            executorServices.add(count, Executors.newFixedThreadPool(100));
////
////            callableWriteToFile(partition, count, executorServices.get(count));
////        }
////
////        for (int count = 0; count < totalPartitions; count++) {
////            executorServices.get(count).shutdown();
////
////            while (! executorServices.get(count).isTerminated()) {
////            }
////
////            System.out.println("Finished all threads!");
////        }
////    }
//
//    private void callableWriteToFile() throws FileNotFoundException, InterruptedException, ExecutionException {
//    //private void callableWriteToFile(List<ZipFileEntry> partition, int partNo, ExecutorService refreshExecutor) {
////        int  corePoolSize  = 100;
////        int  maxPoolSize   = 500;
////        long keepAliveTime = 5000L;
////
////        // Create thread pool for any Manual Refreshes.
////        ThreadPoolExecutor refreshExecutor = new ThreadPoolExecutor(
////                corePoolSize,
////                maxPoolSize,
////                keepAliveTime,
////                TimeUnit.MILLISECONDS,
////                new LinkedBlockingQueue<Runnable>());
//
//        //ExecutorService executorServiceA = Executors.newFixedThreadPool(10);
//
//        ExecutorService executorService = Executors.newCachedThreadPool( );
//
//        // List to keep track of all futures (results of Callable)
//        List<Future<Boolean>> futures = new ArrayList< Future<Boolean> >( );
//
//        int counter = 0;
//
//        for (ZipFileEntry zipFileEntry : practiceLicences) {
//        //for (ZipFileEntry zipFileEntry : partition) {
//            //System.out.println("\tPartition No: " + partNo + " Processing: " + ++counter);
//            //Runnable worker = new WriteToFile("C:\\Temp\\App_Data\\TEST", zipFileEntry);
//            //refreshExecutor.execute(worker);
//
//            Callable<Boolean> worker = new WriteToFile("C:\\Temp\\App_Data\\TEST", zipFileEntry);
//            Future<Boolean>   submit = executorService.submit( worker );
//
//            futures.add(submit);
//        }
//
//        // Process futures to create combined list
//        for ( Future<Boolean> future: futures ) {
//            try {
//                if(future.get( ).equals(Boolean.FALSE)) {
//                    throw new FileNotFoundException("Failed to create file.");
//                }
//            }
//            catch ( InterruptedException e ) {
//                // Write error to log file then re-throw
//                throw new InterruptedException( e.getMessage( ) );
//            }
//            catch ( ExecutionException e ) {
//                // Write error to log file then re-throw
//                throw new ExecutionException( e.getMessage( ), e.getCause( ) );
//            }
//        }
//
//        executorService.shutdown();
//
//        //while(!refreshExecutor.isTerminated()) {
//        //}
//
//        //System.out.println("Finished all threads!");
//    }
//}
