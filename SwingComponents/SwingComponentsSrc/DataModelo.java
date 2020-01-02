package SwingComponents;

import java.io.*;
import javax.swing.*;
import java.util.*;

public class DataModelo extends ObjectSerializableGeneric
{
	int ano, mes, dia;
	
	//private static final int DIA = 0, MES = DIA + 1, ANO = MES + 1;
	private static final int ANO = MES + 1, MES = DIA + 1, DIA = 0; 
	
	public static String meses[] = 
	{
    "Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho",
    "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
	};
	
	public DataModelo()
	{
		
		ano = 2005;
		dia = mes = 1;
	}
	
	public DataModelo(String data)
	{
		StringTokenizer st = new StringTokenizer(data, "/-");
		for (int i = 0; st.hasMoreTokens() && i < 3; i++) 
		{
			try
			{
				switch (i)
				{
					case ANO:
						ano = Integer.parseInt(st.nextToken());
						break;
					case MES:
						geraMes(st.nextToken());
						break;
					case DIA:
						dia = Integer.parseInt(st.nextToken());
						break;
				}
			}
			catch (NumberFormatException ex)
			{
			}
		}
	}
	
	private void geraMes(String mes)
	{
		for (int i = 0; i < meses.length; i++)
		{
			if (mes.equals(meses[i].substring(0, 3)) == true)
			{
				this.mes = i + 1;
				return;
			}
		}
		return;
	}
	
	public int getAno()
		{ return ano; }
		
	public int getMes()
		{ return mes; }
		
	public int getDia()
		{ return dia; }
		
	public String toString()
	{
		return "" + ano + "/" +  mes + "/" +  dia; 
	}
	
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(ano);
			stream.writeInt(mes);
			stream.writeInt(dia);
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita da data no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public void read(RandomAccessFile stream)
	{
		try
		{
			ano = stream.readInt();
			mes = stream.readInt();
			dia = stream.readInt();
		}
		catch (IOException ex)
		{
			String msg = "falha na leitura da data no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
		

}
