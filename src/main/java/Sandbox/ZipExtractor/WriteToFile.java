package Sandbox.ZipExtractor;


import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public final class WriteToFile implements Callable<Boolean> {
    private String target;
    private ZipFileEntry zipFileEntry;


    public WriteToFile(final String targetPath, ZipFileEntry zipFileEntry) {
        this.target       = targetPath;
        this.zipFileEntry = zipFileEntry;
    }


    public final Boolean call() throws IOException {
        return this.writeToFile();
    }


    private Boolean writeToFile() {
        createPath(Paths.get(target));
        try {

            PrintWriter printWriter = new PrintWriter(
                    target + File.separator + zipFileEntry.getFileName(),
                    GenericSettings.GLOBAL_UTF);

            printWriter.println(XmlUtils.marshal(zipFileEntry.getXmlPayload()));
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }

        return true;
    }


    private static boolean createPath(final Path pathToCreate) {
        try {
            Files.createDirectories(pathToCreate);
        }
        catch (UnsupportedOperationException ex) {
            return false;
        }
        catch (IOException ex) {
            return false;
        }

        return true;
    }
}
