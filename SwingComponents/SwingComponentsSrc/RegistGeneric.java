
package SwingComponents;

import java.io.*;


public interface RegistGeneric extends Serializable
{
	public long sizeof() throws IOException;
	public String toString();
	public void read(RandomAccessFile stream);
	public void write(RandomAccessFile stream);
}

