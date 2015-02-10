package Sandbox.utils.file;


import Sandbox.ZipExtractor.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileUtils {
    private final static Logger logger = Logger.getLogger(FileUtils.class.getName());

    private static Path       sourcePath;
    private static Path       targetPath;
    private static boolean    isInError = false;
    private static String     errorMessage;
    private static List<Path> matchedPaths = new ArrayList<Path>();


    public static String getErrorMessage( ) {
        if (isInError) {
            return errorMessage;
        }

        return (errorMessage = null);
    }


    public static Iterable<Path> getRootDirs() {
        return FileSystems.getDefault().getRootDirectories();
    }


    public static Collection<Path> findRootOf(final String path) throws IOException {
        String newPath;


        for (Path name : getRootDirs()) {
            if (path.startsWith(File.separator)) {
                newPath = name.toString() + path;
            }
            else {
                newPath = name.toString() + File.separator + path;
            }

            if (Files.exists(Paths.get(newPath))) {
                matchedPaths.add(Paths.get(newPath));
            }
        }

        return matchedPaths;
    }


    public static boolean createPath(final String pathToCreate) {
        return createPath(Paths.get(pathToCreate));
    }


    public static boolean createPath(final Path pathToCreate) {
        statusReset();

        try {
            Files.createDirectories(pathToCreate);
        }
        catch (UnsupportedOperationException | IOException ex) {
            logger.severe(String.format("Error creating path '%s', exception: '%s'%n", pathToCreate.toString(), ex));
            setErrorMessage(String.format("Error creating path '%s', exception: '%s'%n", pathToCreate.toString(), ex));
            return false;
        }

        return true;
    }


    public static boolean copyFile(final String source, final String target, final CopyOption[] copyOptions) {
        statusReset();
        initialise(source, target);

        if (Files.exists(sourcePath) && createPath(targetPath.getParent())) {
            CopyOption[] options = new CopyOption[]{COPY_ATTRIBUTES, REPLACE_EXISTING};

            if (copyOptions != null && copyOptions.length > 0) {
                options = copyOptions;
            }

            try {
                Files.copy(sourcePath, targetPath, options);
            }
            catch (IOException ex) {
                logger.severe(String.format("Unable to copy: '%s' to '%s' : %s%n", sourcePath, targetPath, ex));
                setErrorMessage(String.format("Unable to copy: '%s' to '%s' : %s%n", sourcePath, targetPath, ex));
                return false;
            }
        }

        return true;
    }


    public static boolean moveFile(final String source, final String target, final CopyOption[] copyOptions) {
        statusReset();
        initialise(source, target);

        if (Files.exists(sourcePath) && createPath(targetPath.getParent())) {
            CopyOption[] options = new CopyOption[]{REPLACE_EXISTING};

            if (copyOptions != null && copyOptions.length > 0) {
                options = copyOptions;
            }

            try {
                Files.move(sourcePath, targetPath, options);
            } catch (IOException ex) {
                logger.severe(String.format("Unable to move: '%s' to '%s' : %s%n", sourcePath, targetPath, ex));
                setErrorMessage(String.format("Unable to move: '%s' to '%s' : %s%n", sourcePath, targetPath, ex));
                return false;
            }
        }

        return true;
    }


    public static List<ZipFileEntry> readZipFile(String zippedFile) throws IOException, JAXBException {
        final ZipFile      zipFile     = new ZipFile(zippedFile);
        int                fileNumber  = 0;
        List<ZipFileEntry> zippedFiles = new ArrayList<>();


        try {
            final Enumeration<? extends ZipEntry> zipEntries   = zipFile.entries();
            JAXBContext                           jaxbContext  = JAXBContext.newInstance(Configuration.class);
            Unmarshaller                          unmarshaller = jaxbContext.createUnmarshaller();

            while (zipEntries.hasMoreElements()) {
                final ZipEntry zipEntry     = zipEntries.nextElement();
                ZipFileEntry   zipFileEntry = new ZipFileEntry();

                zipFileEntry.setFileNumber(++fileNumber);
                zipFileEntry.setFileName(zipEntry.getName());
                zipFileEntry.setXmlPayload((Configuration) unmarshaller.unmarshal(zipFile.getInputStream(zipEntry)));

                zippedFiles.add(zipFileEntry);
            }
        }
        finally {
            zipFile.close();
        }

        return zippedFiles;
    }


    public static void createZipFile(final String source, final String target) throws IOException {
        if (Files.exists(Paths.get(target))) {
            logger.severe("File '" + target + "' already exists.  Will not overwrite.");
            throw new FileAlreadyExistsException("File '" + target + "' already exists.  Will not overwrite.");
        }

        if (Files.exists(Paths.get(source))) {
            List<String> fileList = new ArrayList<String>();
            getAllFiles(new File(source), source, fileList);

            writeZipFile(fileList, source, target);
        }
        else {
            logger.severe("File '" + source + "' could not be found, or is not readable.");
            throw new FileNotFoundException("File '" + source + "' could not be found, or is not readable.");
        }
    }


    private static void writeZipFile(List<String> fileList, String source, String target) throws IOException {
        byte[] buffer = new byte[1024];

        try {
            if (( target.contains(File.separator))
                    && (createPath(target.substring(0, target.lastIndexOf(File.separator))))){

                FileOutputStream fileOutputStream = new FileOutputStream(target);
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

                for (String file : fileList) {
                    ZipEntry zipEntry = new ZipEntry(file);

                    zipOutputStream.putNextEntry(zipEntry);

                    FileInputStream fileInputStream = new FileInputStream(source + File.separator + file);

                    int length;

                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }

                    fileInputStream.close();
                }

                zipOutputStream.closeEntry();
                zipOutputStream.close();
            }
            else {
                throw new FileNotFoundException("Failed to locate file:" + target);
            }
        }
        catch (Exception ex) {
            logger.severe(String.format("Unable to create zip file: '%s' to '%s' : %s%n", source, target, ex));
            throw ex;
        }
    }


    private static void getAllFiles(final File node, final String source, List<String> fileList) {
        // Add files
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), source));
        }

        if (node.isDirectory()) {
            String[] subNode = node.list();

            for (String filename : subNode) {
                getAllFiles(new File(node, filename), source, fileList);
            }
        }
    }


    private static String generateZipEntry(String file, final String source){
        return file.substring(source.length(), file.length());
    }


    private static void initialise(final String source, final String target) {
        sourcePath = Paths.get(source);
        targetPath = Paths.get(target);
    }


    private static void statusReset() {
        isInError    = false;
        errorMessage = null;
    }


    private static void setErrorMessage(final String message) {
        isInError    = true;
        errorMessage = message;
    }
}
