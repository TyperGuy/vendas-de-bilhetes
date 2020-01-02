/************************************************************************/
/* file.: ObjectSerializableGeneric.java                                */                 
/* Autor.: Osvaldo Manuel Ramos                                         */
/* Data.: 17-dez-2005                                                   */
/* Num.: 2817                                                           */
/* Objectivo.: Criacao de Janela                            		*/
/* Descricao.:                                				*/
/************************************************************************/


package SwingComponents;
import javax.swing.*;
import java.io.*;

public abstract class ObjectSerializableGeneric implements DebugInterface, RegistGeneric
{
	
	protected ObjectSerializableGeneric() { }
	
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
	
	public long sizeof() throws IOException
	{
		return Sizeof.sizeof(this);
	}
}
