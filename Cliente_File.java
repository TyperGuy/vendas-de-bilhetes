/*-------------------------------------------------------------
Ficheiro: Cliente_File.java
Autor: Edson Gregório
Objectivo: Manipulacao do ficheiro de Clientes: Clientes.dat
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class  Cliente_File extends ObjectsFile
{
	Cliente_Modelo modelo;

	public Cliente_File(Cliente_Modelo modelo)
	{
		super(Defs.FILE_CLIENTES, modelo);
		this.modelo = modelo;
	}

	public void salvar()
	{
		try {
			stream.seek( stream.length() );
			modelo.write( stream );
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


	public static Cliente_Modelo getClienteModelo ( String nome)
	{
		Cliente_Modelo model = new Cliente_Modelo();
		Cliente_File file = new  Cliente_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer(); //atual posicao do ponteiro no arquivo
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );	//prenche o modelo com dados do arquivo			
				if ( model.getNome().equals(nome) )
					return model;
			}
			file.stream.seek(currentPosition);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
		return null;
	}


	//devolve um Vector com todos Clientes
	public static StringVector getAllClientes()
	{
        StringVector listaClientes = new StringVector();
		Cliente_Modelo model = new Cliente_Modelo();
		Cliente_File file = new  Cliente_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodCliente() > 0)
				listaClientes.add( model.getNome() );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }	
		listaClientes.sort();
		return listaClientes;
	}

	//devolve um Vector com todos clientes eliminados
	public static StringVector getAllClientesEliminados()
	{
		StringVector vector = new StringVector();
		Cliente_Modelo model = new Cliente_Modelo();
		Cliente_File file = new  Cliente_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodCliente() < 0)
					vector.add( "["+  model.getCodCliente()+"] " + model.getNome() );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }		
		vector.sort();
		return vector;
	}
}