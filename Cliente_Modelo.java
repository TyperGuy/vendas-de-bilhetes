/*-------------------------------------------------------------
Ficheiro: Cliente_Modelo.java
Autor: Raimundo Tony
Objectivo: Modelo para entidade Cliente
--------------------------------------------------------------*/
import SwingComponents.*;
import java.io.*;
import javax.swing.*;

public class Cliente_Modelo implements RegistGeneric {

    private int codCliente;
    private StringBufferModelo nome, telefone, provincia, municipio, comuna, tipoCliente;
 
    public Cliente_Modelo() 
    {
        codCliente = 0;
        nome = new StringBufferModelo("", 30);
        telefone = new StringBufferModelo("", 15);
        provincia = new StringBufferModelo("", 15); 
        municipio = new StringBufferModelo("", 15); 
        comuna = new StringBufferModelo("", 15); 
        tipoCliente = new StringBufferModelo("", 15);
    }

    public Cliente_Modelo( int codCliente, String nome, String telefone, String provincia, String municipio, String comuna, String tipoCliente ) 
    {
        this.codCliente = codCliente;
        this.nome = new StringBufferModelo(nome, 30);
        this.telefone = new StringBufferModelo(telefone, 15);
        this.provincia = new StringBufferModelo(provincia, 15); 
        this.municipio = new StringBufferModelo(municipio, 15); 
        this.comuna = new StringBufferModelo(comuna, 15); 
        this.tipoCliente = new StringBufferModelo(tipoCliente, 15);
    }
    

    public int  getCodCliente() { return codCliente; }

    public String getNome() { return nome.toStringEliminatingSpaces(); }
    
    public String getTelefone() { return telefone.toStringEliminatingSpaces(); }

    public String getProvincia() { return provincia.toStringEliminatingSpaces(); }

    public String getMunicipio() { return municipio.toStringEliminatingSpaces(); }

    public String getComuna() { return comuna.toStringEliminatingSpaces(); }

    public String getTipoCliente() { return tipoCliente.toStringEliminatingSpaces(); }
    
    
    public void  setCodCliente(int codCliente) { this.codCliente = codCliente; }
    
    public void setNome(String nome) { this.nome = new StringBufferModelo(nome, 30); }

    public void setTelefone(String telefone) { this.telefone = new StringBufferModelo(telefone, 15); }

    public void setProvincia(String provincia) { this.provincia = new StringBufferModelo(provincia, 15); }

    public void setMunicipio(String municipio) { this.municipio = new StringBufferModelo(municipio, 15); }

    public void setComuna(String comuna) { this.comuna = new StringBufferModelo(comuna, 15); }

    public void setTipoCliente(String tipoCliente) { this.tipoCliente = new StringBufferModelo(tipoCliente, 15); }


    @Override
    public String toString() {
        return "\n Codigo: " + getCodCliente()
            + "\n Nome: " + getNome() 
            + "\n Telefone: " + getTelefone() 
            + "\n Provincia: " +  getProvincia()
            + "\n Municipio: " + getMunicipio() 
            + "\n Comuna: " + getComuna() 
            + "\n Tipo de Cliente: " + getTipoCliente() 
            + "\n\n";
    }

    
	public long sizeof()
	{
		long size = 0;
        size = 214; // 109 * 2 - 4 Bytes 
		return size;
	}


	public void read ( RandomAccessFile stream )
	{
		try
		{
			codCliente = stream.readInt();
			nome.read(stream);
			telefone.read(stream);
            provincia.read(stream);
            municipio.read(stream);
            comuna.read(stream);
            tipoCliente.read(stream);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO: Não foi possível ler o ficheiro! \n" + ex);
		}
	}

	
	public void write ( RandomAccessFile stream )
	{
		try
		{
            stream.writeInt(codCliente);
			nome.write(stream);
			telefone.write(stream);
            provincia.write(stream);
            municipio.write(stream);
            comuna.write(stream);
            tipoCliente.write(stream);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO: Não foi possível escrever no ficheiro! \n" + ex);
		}
	}


	public void salvar()
	{
		Cliente_File file = new Cliente_File( this );
		file.salvar();		
		JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso!");
    } 
    
    public void editar()
	{
		Cliente_File file = new Cliente_File( this );
		file.editar( getCodCliente() );		
		JOptionPane.showMessageDialog(null, "Cliente Alterado com Sucesso!");
    }
    
    public void eliminar()
	{
		Cliente_File file = new Cliente_File( this );
		file.eliminar( getCodCliente() );		
		JOptionPane.showMessageDialog(null, "Cliente Eliminado com Sucesso!");
	}
	
}
