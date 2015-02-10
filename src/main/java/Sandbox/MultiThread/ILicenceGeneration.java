package Sandbox.MultiThread;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ILicenceGeneration extends ILicenceExtraction, ILicenceMerge {
    public void setFullyQualifiedSource(final String source);
    public String getFullyQualifiedSource();
    public void setFullyQualifiedTarget(final String target);
    public String getFullyQualifiedTarget();
    public void setFullyQualifiedStaticPracticeData(final String staticPracticeData);
    public String getFullyQualifiedStaticPracticeData();
    public int getCurrent();
    public int getTotal();
    public ILicenceGeneration autoConfigure();

    @Override
    public int extract() throws IOException, JAXBException;

    @Override
    public boolean merge() throws IOException, JAXBException;

    @Override
    public void callableMerge(int fileNo) throws IOException, JAXBException;

    //@Override
    //public void run();
}
