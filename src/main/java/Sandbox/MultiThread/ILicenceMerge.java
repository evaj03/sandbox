package Sandbox.MultiThread;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ILicenceMerge {
    public boolean merge() throws IOException, JAXBException;
    public void callableMerge(int fileNo) throws IOException, JAXBException;
}
