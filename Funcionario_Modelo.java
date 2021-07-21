/*-------------------------------------------------------------
Ficheiro: Funcionario_Modelo.java
Autor: Raimundo Tony
Objectivo: Modelo para entidade funcionario
--------------------------------------------------------------*/
import SwingComponents.*;
import java.io.*;
import javax.swing.*;

public class Funcionario_Modelo implements RegistGeneric {

    private int codFuncionario;
    private StringBufferModelo nome, telefone, bi, estadoCivil, dataNascimento, sexo, nacionalidade, localTrabalho;

    public Funcionario_Modelo() 
    {
        codFuncionario = 0;
        nome = new StringBufferModelo("", 30);
        telefone = new StringBufferModelo("", 15);
        bi = new StringBufferModelo("", 15); 
        estadoCivil = new StringBufferModelo("", 15); 
        dataNascimento = new StringBufferModelo("", 15); 
        sexo = new StringBufferModelo("", 10);
        nacionalidade = new StringBufferModelo("", 15); 
        localTrabalho = new StringBufferModelo("", 20);
    }

    public Funcionario_Modelo( int codFuncionario, String nome, String telefone, String bi, String estadoCivil, String dataNascimento, String sexo, String nacionalidade, String localTrabalho ) 
    {
        this.codFuncionario = codFuncionario;
        this.nome = new StringBufferModelo(nome, 30);
        this.telefone = new StringBufferModelo(telefone, 15);
        this.bi = new StringBufferModelo(bi, 15); 
        this.estadoCivil = new StringBufferModelo(estadoCivil, 15); 
        this.dataNascimento = new StringBufferModelo(dataNascimento, 15); 
        this.sexo = new StringBufferModelo(sexo, 10);
        this.nacionalidade = new StringBufferModelo(nacionalidade, 15); 
        this.localTrabalho = new StringBufferModelo(localTrabalho, 20);
    }
    

    public int getCodFuncionario() { return codFuncionario; }

    public String getNome() { return nome.toStringEliminatingSpaces(); }
    
    public String getTelefone() { return telefone.toStringEliminatingSpaces(); }

    public String getBi() { return bi.toStringEliminatingSpaces(); }

    public String getEstadoCivil() { return estadoCivil.toStringEliminatingSpaces(); }

    public String getDataNascimento() { return dataNascimento.toStringEliminatingSpaces(); }

    public String getSexo() { return sexo.toStringEliminatingSpaces(); }
    
	public String getNacionalidade() { return nacionalidade.toStringEliminatingSpaces(); }

	public String getLocalTrabalho() { return localTrabalho.toStringEliminatingSpaces(); }
    
    
    public void setCodFuncionario(int codFuncionario) { this.codFuncionario = codFuncionario; }
    
    public void setNome(String nome) { this.nome = new StringBufferModelo(nome, 30); }

    public void setTelefone(String telefone) { this.telefone = new StringBufferModelo(telefone, 15); }

    public void setBi(String bi) { this.bi = new StringBufferModelo(bi, 15); }

    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = new StringBufferModelo(estadoCivil, 15); }

    public void setDataNascimento(String dataNascimento) { this.dataNascimento = new StringBufferModelo(dataNascimento, 15); }

    public void setSexo(String sexo) { this.sexo = new StringBufferModelo(sexo, 10); }

    public void setNacionalidade(String nacionalidade) { this.nacionalidade = new StringBufferModelo(nacionalidade, 15); }

    public void setLocalTrabalho(String localTrabalho) { this.localTrabalho = new StringBufferModelo(localTrabalho, 20); }
       
    @Override
    public String toString() 
    {
        return "\n Codigo: " + getCodFuncionario()
            + "\n Nome: " + getNome() 
            + "\n Telefone: " + getTelefone() 
            + "\n BI: " + getBi()
            + "\n Estado Civil: " + getEstadoCivil() 
            + "\n Data de Nascimento: " + getDataNascimento() 
            + "\n Sexo: " + getSexo() 
            + "\n Subsidio: " + getNacionalidade() 
            + "\n Local de Trabalho: " + getLocalTrabalho() 
            + "\n\n";
    }
    
	public long sizeof()
	{
		long size = 0;
        size = 274;     // => 139 + 139 - 4 - Bytes 
		return size;
	}

	public void read ( RandomAccessFile stream )
	{
		try
		{
			codFuncionario = stream.readInt();
			nome.read(stream);
			telefone.read(stream);
            bi.read(stream);
            estadoCivil.read(stream);
            dataNascimento.read(stream);
            sexo.read(stream);
            nacionalidade.read(stream); 
            localTrabalho.read(stream);
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
            stream.writeInt(codFuncionario);
			nome.write(stream);
			telefone.write(stream);
            bi.write(stream);
            estadoCivil.write(stream);
            dataNascimento.write(stream);
            sexo.write(stream);
            nacionalidade.write(stream); 
            localTrabalho.write(stream);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO: Não foi possível escrever no ficheiro! \n" + ex);
		}
	}

	public void salvar()
	{
		Funcionario_File file = new Funcionario_File( this );
		file.salvar();		
		JOptionPane.showMessageDialog(null, "Funcionario Salvo com Sucesso!");
    }
    
    public void editar()
	{
		Funcionario_File file = new Funcionario_File( this );
		file.editar( getCodFuncionario() );		
		JOptionPane.showMessageDialog(null, "Funcionario Alterado com Sucesso!");
    }
    
    public void eliminar()
	{
		Funcionario_File file = new Funcionario_File( this );
		file.eliminar( getCodFuncionario() );		
		JOptionPane.showMessageDialog(null, "Funcionario Eliminado com Sucesso!");
	}
	
}
