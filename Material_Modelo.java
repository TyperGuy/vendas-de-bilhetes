/*-------------------------------------------------------------
Ficheiro: Material_Modelo.java
Autor: Edson Gregório
Objectivo: Modelo para entidade material
--------------------------------------------------------------*/
import SwingComponents.*;
import java.io.*;
import javax.swing.*;

public class Material_Modelo implements RegistGeneric {

    private int codMaterial;
    private StringBufferModelo nome;
    private float valorUnitario, iva;

    public Material_Modelo() 
    {
        codMaterial = 0;
        nome = new StringBufferModelo("", 30);
		valorUnitario = 0;
		iva = 0;
    }

    public Material_Modelo( int codMaterial, String nome, float valorUnitario, float iva) 
    {
        this.codMaterial = codMaterial;
        this.nome = new StringBufferModelo(nome, 30);
		this.valorUnitario = valorUnitario;
		this.iva = iva;
    }

    public int  getCodMaterial() { return codMaterial; }

    public String getNome() { return nome.toStringEliminatingSpaces(); }
    
	public float getValorUnitario() { return valorUnitario; }

	public float getIva() { return iva; }
	
    
    public void  setCodMaterial(int codMaterial) { this.codMaterial = codMaterial; }
    
    public void setNome(String nome) { this.nome = new StringBufferModelo(nome, 30); }

	public void setValorUnitario(float valorUnitario) { this.valorUnitario = valorUnitario; }

    public void setIva(float iva) { this.iva = iva; }
    
    @Override
    public String toString() {
        return "\n Codigo: " + getCodMaterial()
			+ "\n Nome: " + getNome() 
			+ "\n Valor Unitario: " + getValorUnitario()    
			+ "\n IVA: " + getIva()     
			+ "\n\n";
    }

    
	public long sizeof()
	{
		long size = 0;
        size = 72; // 42 * 2 - 12
		return size;
	}


	public void read ( RandomAccessFile stream )
	{
		try
		{
			codMaterial = stream.readInt();
			nome.read(stream);
			valorUnitario = stream.readFloat();
			iva = stream.readFloat();
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
            stream.writeInt(codMaterial);
			nome.write(stream);
			stream.writeFloat(valorUnitario);
			stream.writeFloat(iva);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO: Não foi possível escrever no ficheiro! \n" + ex);
		}
	}

	public void salvar()
	{
		Material_File file = new Material_File( this );
		file.salvar();		
		JOptionPane.showMessageDialog(null, "Material Salvo com Sucesso!");
	}

    public void editar()
	{
		Material_File file = new Material_File( this );
		file.editar( getCodMaterial() );		
		JOptionPane.showMessageDialog(null, "Material Alterado com Sucesso!");
    }
    
    public void eliminar()
	{
		Material_File file = new Material_File( this );
		file.eliminar( getCodMaterial() );		
		JOptionPane.showMessageDialog(null, "Material Eliminado com Sucesso!");
	}
	
}
