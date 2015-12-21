package Sandbox.Threading.Application;

/**
 * Created by jonathanevans on 18/12/2015.
 */
public interface IThreadSafeApplication {

    String getFileName();

    void write(final int length);

    void write(final String prepend, final int length);
}
