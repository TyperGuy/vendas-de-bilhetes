/*-------------------------------------------------------------
Ficheiro: Funcionario_File.java
Autor: Edson Gregório
Objectivo: Manipulacao do ficheiro de Funcionario: Funcionarios.dat
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class  Funcionario_File extends ObjectsFile
{
	Funcionario_Modelo modelo;

	public Funcionario_File(Funcionario_Modelo modelo)
	{
		super(Defs.FILE_FUNCIONARIOS, modelo);
		this.modelo = modelo;
	}

	public void salvar()
	{
		try {
			stream.seek( stream.length() ); //vai para o fim do arquivo
			modelo.write( stream ); //grava no fim do arquivo o modelo
			incrementarProximoCodigo();
		}
		catch(IOException ex) { ex.printStackTrace(); }
	}

	public void editar( int codigo ) 
	{
		try {
			stream.seek( (codigo - 1) * modelo.sizeof() + 4 ); //modelo.sizeof() -> tamanho registo + 4 (pula o id do registo a ser alterado)
			modelo.write( stream );
		}
		catch(IOException ex) { ex.printStackTrace(); }
	}

	public void eliminar ( int codigo )
	{
		try {
			stream.seek( (codigo - 1)  * modelo.sizeof() + 4 );
			stream.writeInt( codigo * (-1) );
		}
		catch(IOException ex) { ex.printStackTrace(); }
	}

	public static Funcionario_Modelo getFuncionarioModelo ( String nome)
	{
		Funcionario_Modelo model = new Funcionario_Modelo();
		Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer(); //atual posicao do ponteiro no arquivo
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );	//prenche o modelo com dados do arquivo			
				if ( model.getNome().equalsIgnoreCase(nome) )
					return model;
			}
			file.stream.seek(currentPosition);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
		return null;
	}

	//devolve um Vector com todos Funcionarios
	public static StringVector getAllFuncionarios()
	{
		StringVector vector = new StringVector();
		Funcionario_Modelo model = new Funcionario_Modelo();
		Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodFuncionario() > 0)
					vector.add( model.getNome() );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }		
		vector.sort();
		return vector;
	}

	//devolve um Vector com todos Funcionarios eliminados
	public static StringVector getAllFuncionariosEliminados()
	{
		StringVector vector = new StringVector();
		Funcionario_Modelo model = new Funcionario_Modelo();
		 Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodFuncionario() < 0)
					vector.add( "["+  model.getCodFuncionario()+"] " + model.getNome() );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }		
		vector.sort();
		return vector;
	}

	//devolve um Vector com todos Funcionarios que trabalham na loja
	public static StringVector getFuncionariosPorLoja(String loja)
	{
		StringVector vector = new StringVector();
		Funcionario_Modelo model = new Funcionario_Modelo();
		Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer(); //atual posicao do ponteiro no arquivo
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );	//prenche o modelo com dados do arquivo			
				if ( model.getLocalTrabalho().equals(loja) )
					vector.add(model.getNome());
			}
			file.stream.seek(currentPosition);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
		vector.sort();
		return vector;	
	}


	
	//devolve um Vector com todos Funcionarios que trabalham na loja
	public static void pesquisarFuncionariosPorLoja(String loja)
	{
		Funcionario_Modelo model = new Funcionario_Modelo();
		Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			String output = "";
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );				
				if ( model.getCodFuncionario() > 0 && model.getLocalTrabalho().equals(loja) )
					output += model.toString() + "-----------------------------------\n";
			}
			file.stream.seek(currentPosition);				
			JTextArea area = new JTextArea(30, 50);
			area.setText( output );
			area.setFocusable(false);
			
			JPanel pane = new JPanel(new BorderLayout());
			pane.add( new JLabel("Lista de Funcionarios da "+loja), BorderLayout.NORTH);
			pane.add( new JScrollPane(area), BorderLayout.CENTER);

			JOptionPane.showMessageDialog(null, pane);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
	}

	//devolve um Vector com todos Funcionarios do sexo indicado
	public static void pesquisarFuncionariosPorSexo(String sexo)
	{
		Funcionario_Modelo model = new Funcionario_Modelo();
		Funcionario_File file = new  Funcionario_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			String output = "";
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );				
				if ( model.getCodFuncionario() > 0 && model.getSexo().equalsIgnoreCase(sexo) )
					output += model.toString() + "-----------------------------------\n";
			}
			file.stream.seek(currentPosition);				
			JTextArea area = new JTextArea(30, 50);
			area.setText( output );
			area.setFocusable(false);
			
			JPanel pane = new JPanel(new BorderLayout());
			pane.add( new JLabel("Lista de Funcionarios "+sexo), BorderLayout.NORTH);
			pane.add( new JScrollPane(area), BorderLayout.CENTER);

			JOptionPane.showMessageDialog(null, pane);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
	}
}