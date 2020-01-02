/*-------------------------------------------------------------
Ficheiro: NotaFiscal_Modelo.java
Autor: Edson Gregório
Objectivo: Modelo para entidade Nota Fiscal
--------------------------------------------------------------*/
import SwingComponents.*;
import java.io.*;
import javax.swing.*;

public class NotaFiscal_Modelo implements RegistGeneric {

    private int codNotaFiscal, quantidade;
    private StringBufferModelo cliente, funcionario, loja, material, dataEmissao; 
    private float valorUnitario, valorTotal;

    public NotaFiscal_Modelo() 
    {
        codNotaFiscal = 0;
        cliente = new StringBufferModelo("", 30);
        funcionario = new StringBufferModelo("", 30); 
        loja = new StringBufferModelo("", 20); 
        material = new StringBufferModelo("", 30); 
        valorUnitario = 0;
        quantidade = 0;
        valorTotal = 0;
        dataEmissao = new StringBufferModelo("", 15);
    }

    public NotaFiscal_Modelo( int codNotaFiscal, String cliente, String NotaFiscal, String loja, String material, float valorUnitario, int  quantidade, float valorTotal, String dataEmissao ) 
    {
        this.codNotaFiscal = codNotaFiscal;
        this.cliente = new StringBufferModelo(cliente, 30);
        this.funcionario = new StringBufferModelo(NotaFiscal, 30);
        this.loja = new StringBufferModelo(loja, 20); 
        this.material = new StringBufferModelo(material, 30); 
        this.valorUnitario = valorUnitario; 
        this.quantidade =  quantidade;
        this.valorTotal = valorTotal;
        this.dataEmissao = new StringBufferModelo(dataEmissao, 15); 
    }
    

    public int  getCodNotaFiscal() { return codNotaFiscal; }

    public String getCliente() { return cliente.toStringEliminatingSpaces(); }
    
    public String getFuncionario() { return funcionario.toStringEliminatingSpaces(); }

    public String getLoja() { return loja.toStringEliminatingSpaces(); }

    public String getMaterial() { return material.toStringEliminatingSpaces(); }

    public float getValorUnitario() { return valorUnitario; }

    public int getQuantidade() { return quantidade; }
    
    public float getValorTotal() { return valorTotal; }

    public String getDataEmissao() { return dataEmissao.toStringEliminatingSpaces(); }
    

    public void  setCodNotaFiscal(int codNotaFiscal) { this.codNotaFiscal = codNotaFiscal; }
    
    public void setCliente(String cliente) { this.cliente = new StringBufferModelo(cliente, 30); }

    public void setFuncionario(String funcionario) { this.funcionario = new StringBufferModelo(funcionario, 30); }

    public void setLoja(String loja) { this.loja = new StringBufferModelo(loja, 20); }

    public void setMaterial(String material) { this.material = new StringBufferModelo(material, 30); }

    public void  setValorUnitario(float valorUnitario) { this.valorUnitario = valorUnitario; }
    
    public void  setQuantidade(int  quantidade) { this. quantidade =  quantidade; }

    public void  setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }

    public void setDataEmissao(String dataEmissao) { this.dataEmissao = new StringBufferModelo(dataEmissao, 15); }

    @Override
    public String toString() {
        return "\n Codigo: " + getCodNotaFiscal()
            + "\n Cliente: " + getCliente() 
            + "\n Funcionario: " + getFuncionario() 
            + "\n Loja: " +  getLoja()
            + "\n Material: " + getMaterial() 
            + "\n Valor Unitario: " + getValorUnitario() 
            + "\n Quantidade: " + getQuantidade() 
            + "\n Valor Total: " + getValorTotal() 
            + "\n Data de Emissao: " + getDataEmissao() 
            + "\n\n ";
    }

    
	public long sizeof()
	{
		long size = 0;
        size = 266; // 141 * 2 - 16 Bytes 
		return size;
	}


	public void read ( RandomAccessFile stream )
	{
		try
		{
			codNotaFiscal = stream.readInt();
			cliente.read(stream);
			funcionario.read(stream);
            loja.read(stream);
            material.read(stream);
            valorUnitario = stream.readFloat();
            quantidade = stream.readInt();
            valorTotal = stream.readFloat();
            dataEmissao.read(stream);
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
            stream.writeInt(codNotaFiscal);
			cliente.write(stream);
			funcionario.write(stream);
            loja.write(stream);
            material.write(stream);
            stream.writeFloat(valorUnitario);
            stream.writeInt( quantidade);
            stream.writeFloat(valorTotal);
            dataEmissao.write(stream);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO: Não foi possível escrever no ficheiro! \n" + ex);
		}
	}


	public void salvar()
	{
		NotaFiscal_File file = new NotaFiscal_File( this );
		file.salvar();		
		JOptionPane.showMessageDialog(null, "NotaFiscal Salvo com Sucesso!");
    }
    
    public void editar()
	{
		NotaFiscal_File file = new NotaFiscal_File( this );
		file.editar( getCodNotaFiscal() );		
		JOptionPane.showMessageDialog(null, "NotaFiscal Alterado com Sucesso!");
    }
    
    public void eliminar()
	{
		NotaFiscal_File file = new NotaFiscal_File( this );
		file.eliminar( getCodNotaFiscal() );		
		JOptionPane.showMessageDialog(null, "NotaFiscal Eliminado com Sucesso!");
	}
	
	
}
