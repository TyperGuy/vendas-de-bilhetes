/*-------------------------------------------------------------
Ficheiro: NotaFiscal_File.java
Autor: Edson Gregório
Objectivo: Manipulacao do ficheiro de Notas Fiscais: NatasFiscais.dat
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class  NotaFiscal_File extends ObjectsFile
{
	NotaFiscal_Modelo modelo;

	public NotaFiscal_File(NotaFiscal_Modelo modelo)
	{
		super(Defs.FILE_NOTAS_FISCAIS, modelo);
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




	public static NotaFiscal_Modelo getNotaFiscalModelo ( int cod )
	{
		NotaFiscal_Modelo model = new NotaFiscal_Modelo();
		NotaFiscal_File file = new  NotaFiscal_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer(); //atual posicao do ponteiro no arquivo
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );	//prenche o modelo com dados do arquivo			
				if ( model.getCodNotaFiscal() == cod )
					return model;
			}
			file.stream.seek(currentPosition);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
		return null;
	}


	public static void pesquisarNotasFiscaisPorLoja(String tipoNotaFiscal)
	{
		NotaFiscal_Modelo model = new NotaFiscal_Modelo();
		NotaFiscal_File file = new  NotaFiscal_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			String output = "Listagem de NotasFiscais " + tipoNotaFiscal + "\n\n";

			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );				
				if (model.getLoja().equalsIgnoreCase(tipoNotaFiscal) )
				{
					output += model.toString() + "\n";
					output += "-------------------------------------\n";
				}
			}
			file.stream.seek(currentPosition);				
			JTextArea area = new JTextArea(70, 60);
			area.setText( output );
			JOptionPane.showMessageDialog(null, new JScrollPane(area) );
		}
		catch(IOException ex) { ex.printStackTrace(); }		
	}

	//devolve um Vector com todos NotasFiscais
	public static StringVector getAllNotasFiscais()
	{
        StringVector listaNotasFiscais = new StringVector();
		NotaFiscal_Modelo model = new NotaFiscal_Modelo();
		NotaFiscal_File file = new  NotaFiscal_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodNotaFiscal() > 0)
					listaNotasFiscais.add(model.getCodNotaFiscal() +" - Nota Fiscal - [ "+ model.getDataEmissao() + " ]" );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }	
		listaNotasFiscais.sort();
		return listaNotasFiscais;
	}


	//devolve um Vector com todos clientes eliminados
	public static StringVector getAllNotasFisciasEliminados()
	{
		StringVector vector = new StringVector();
		NotaFiscal_Modelo model = new NotaFiscal_Modelo();
		NotaFiscal_File file = new  NotaFiscal_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodNotaFiscal() < 0)
					vector.add("Nota Fiscal 00" + model.getCodNotaFiscal() + " - [ "+ model.getDataEmissao() + " ]" );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }		
		vector.sort();
		return vector;
	}

		
	//Pesquisar notas fiscais emitidas no intervalo ano1 - ano2
	public static void pesquisarNotaFiscal(int ano1, int ano2)
	{
		NotaFiscal_Modelo model = new NotaFiscal_Modelo();
		NotaFiscal_File file = new  NotaFiscal_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			String output = "";
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );				
				if ( model.getCodNotaFiscal() > 0)
				{
					String data = model.getDataEmissao();
					if ( ano1 <= Pesquisar.getAno(data) && Pesquisar.getAno(data) <= ano2 )
						output += model.toString() + "-----------------------------------\n";
				} 
			}
			file.stream.seek(currentPosition);				
			JTextArea area = new JTextArea(25, 40);
			area.setText( output );
			area.setFocusable(false);
			JPanel pane = new JPanel(new BorderLayout());
			pane.add( new JLabel("Lista de Notas fiscais ("+ano1+"-"+ano2+")"), BorderLayout.NORTH);
			pane.add( new JScrollPane(area), BorderLayout.CENTER);
			JOptionPane.showMessageDialog(null, pane);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
	}
}