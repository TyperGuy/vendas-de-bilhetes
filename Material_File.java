/*-------------------------------------------------------------
Ficheiro: Material_File.java
Autor: Edson Gregório
Objectivo: Manipulacao do ficheiro de Materiais: Material.dat
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class  Material_File extends ObjectsFile
{
	Material_Modelo modelo;

	public Material_File(Material_Modelo modelo)
	{
		super(Defs.FILE_MATERIAIS, modelo);
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


	public static Material_Modelo getMaterialModelo ( String nome)
	{
		Material_Modelo model = new Material_Modelo();
		Material_File file = new  Material_File(model);
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


	//devolve um Vector com todos Materiais
	public static StringVector getAllMateriais()
	{
        StringVector listaMateriais = new StringVector();
		Material_Modelo model = new Material_Modelo();
		Material_File file = new  Material_File(model);

		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );
				if( model.getCodMaterial() > 0)
					listaMateriais.add( model.getNome() );
			}
			file.stream.seek(currentPosition);				
		}
		catch(IOException ex) {	ex.printStackTrace(); }	
		listaMateriais.sort();
		return listaMateriais;
	}

	public static float getValorUnitario(String nomeMaterial)
	{
		Material_Modelo model = new Material_Modelo();
		Material_File file = new Material_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer(); //atual posicao do ponteiro no arquivo
			file.stream.seek(4); 
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );	//prenche o modelo com dados do arquivo			
				if (model.getNome().equalsIgnoreCase(nomeMaterial) )
					return model.getValorUnitario();
			}
			file.stream.seek(currentPosition);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
		return 0;
	}

	
		
	//faz listagem de todos Materiais que possuem iva informado
	public static void pesquisarMaterialPorIVA(float iva)
	{
		Material_Modelo model = new Material_Modelo();
		Material_File file = new  Material_File(model);
		try
		{
			long currentPosition = file.stream.getFilePointer();
			file.stream.seek(4);
			String output = "";
			for (int i = 0; i < file.getNregistos(); ++i)
			{
				model.read( file.stream );				
				if ( model.getCodMaterial() > 0 && model.getIva() == iva )
					output += model.toString() + "-----------------------------------\n";
			}
			file.stream.seek(currentPosition);				
			JTextArea area = new JTextArea(20, 30);
			area.setText( output );
			area.setFocusable(false);
			
			JPanel pane = new JPanel(new BorderLayout());
			pane.add( new JLabel("Lista de Materials com iva "+iva), BorderLayout.NORTH);
			pane.add( new JScrollPane(area), BorderLayout.CENTER);

			JOptionPane.showMessageDialog(null, pane);
		}
		catch(IOException ex) { ex.printStackTrace(); }	
	}

}
