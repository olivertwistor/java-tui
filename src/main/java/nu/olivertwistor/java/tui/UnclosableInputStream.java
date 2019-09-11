package nu.olivertwistor.java.tui;

import java.io.IOException;
import java.io.InputStream;

/**
 * A decorator for InputStream. It does what the underlying InputStream does
 * with the exception of {@link InputStream#close()}. Instead of closing it, it
 * does nothing.
 *
 * @author nkukhar https://stackoverflow.com/users/369280/nkukhar
 * @author Johan Nilsson
 *
 * @since 0.1.0
 *
 * @see <a href="https://stackoverflow.com/a/14962425/2267803">
 *      Idea taken from an Stack Overflow answer by nkukhar</a>
 */
public class UnclosableInputStream extends InputStream
{
    /**
     * The InputStream to decorate.
     *
     * @since 0.1.0
     */
    private final InputStream inputStream;

    /**
     * Creates an InputStream that can't be closed.
     *
     * @param inputStream the InputStream to make unclosable.
     *
     * @see <a href="https://stackoverflow.com/a/14962425/2267803">
     *      Idea taken from an Stack Overflow answer by nkukhar</a>
     */
    public UnclosableInputStream(final InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException
    {
        return this.inputStream.read();
    }

    @Override
    public int read(final byte[] b) throws IOException
    {
        return this.inputStream.read(b);
    }

    @Override
    public int read(final byte[] b, final int off, final int len)
            throws IOException
    {
        return this.inputStream.read(b, off, len);
    }

    @Override
    public long skip(final long n) throws IOException
    {
        return this.inputStream.skip(n);
    }

    @Override
    public int available() throws IOException
    {
        return this.inputStream.available();
    }

    @Override
    public synchronized void mark(final int readlimit)
    {
        this.inputStream.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException
    {
        this.inputStream.reset();
    }

    @Override
    public boolean markSupported()
    {
        return this.inputStream.markSupported();
    }

    /**
     * Instead of closing the underlying InputStream, this method does nothing.
     *
     * @throws IOException never thrown
     *
     * @since 0.1.0
     */
    @Override
    public void close() throws IOException
    {
        // Do nothing instead of closing the underlying stream.
    }

    @Override
    public String toString()
    {
        return "UnclosableInputStream{" +
                "inputStream=" + this.inputStream +
                '}';
    }
}
