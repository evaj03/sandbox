package Sandbox.Threading.Application;

import Sandbox.Threading.IRandomText;

/**
 * Created by jonathanevans on 18/12/2015.
 */
public interface IApplication {
    void setRandomText(final IRandomText randomText);

    void setFileName(final String filename);

    String getFileName();

    void create();

    void write(final int length);
}
