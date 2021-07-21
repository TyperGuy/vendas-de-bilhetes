/*-------------------------------------------------------------
Ficheiro: Home.java
Autor: Raimundo Tony
Objectivo: Visão/Interface principal
--------------------------------------------------------------*/
import SwingComponents.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    private Menu ficheiro, funcionario, cliente, material, notaFiscal, tabelas, pesquisar, listagens, exame, ajuda;

    private MenuItem sair, novoFuncionario, visualizarFuncionario, 
            novoCliente, visualizarCliente,
            novoMaterial, visualizarMaterial, 
            novaNotaFiscal, visualizarNotaFiscal,
            provincia, municipio, comuna, loja, tipoCliente, valorIva, estadoCivil, nacionalidade,
            pesqFUN_porLoja, pesqFUN_porSexo, pesqMAT_porIva,
            defesa;
		

    private MenuBar barraMenus;

    public Home() throws IOException {
        super("Menu Principal");

        barraMenus = new MenuBar();
        ficheiro = new Menu("Ficheiro");
        funcionario = new Menu("Funcionario");
        cliente = new Menu("Cliente");
        material = new Menu("Material");
        tabelas = new Menu("Tabela");
        notaFiscal = new Menu("Nota Fiscal");
        pesquisar = new Menu("Pesquisar");
        listagens = new Menu("Defesa");
        ajuda = new Menu("Ajuda");
        
        exame = new Menu("Exame");
        //exame.add( defesa = new MenuItem("Defesa") );
        listagens.add( defesa = new MenuItem("Pesquisa Defesa"));

        ficheiro.add( sair = new MenuItem("Sair") );

        funcionario.add( novoFuncionario = new MenuItem("Novo Funcionario") );
        funcionario.add( visualizarFuncionario = new MenuItem("Visualizar Funcionarios") );

        cliente.add( novoCliente = new MenuItem("Novo Cliente") );
        cliente.add( visualizarCliente = new MenuItem("Visualizar Clientes") );

        material.add( novoMaterial = new MenuItem("Novo Material") );
        material.add( visualizarMaterial = new MenuItem("Visualizar Materiais") );

        notaFiscal.add( novaNotaFiscal = new MenuItem("Nova Nota Fiscal") );
        notaFiscal.add( visualizarNotaFiscal = new MenuItem("Visualizar Notas Fiscais") );

        tabelas.add( provincia = new MenuItem("Provincias") );
        tabelas.add( municipio = new MenuItem("Municipios") );
        tabelas.add( comuna = new MenuItem("Comunas") );
        tabelas.add( tipoCliente = new MenuItem("Tipo de Cliente") );
        tabelas.add( loja = new MenuItem("Lojas") );
        tabelas.add( estadoCivil = new MenuItem("Estado Civil") );
        tabelas.add( nacionalidade = new MenuItem("Subsidiu") );
        tabelas.add( valorIva = new MenuItem("Valor de IVA") );

        listagens.add(nacionalidade);

        pesquisar.add( pesqFUN_porLoja = new MenuItem("Pesquisar funcionarios por Loja") );
        pesquisar.add( pesqFUN_porSexo = new MenuItem("Pesquisar funcionarios por Sexo") );    
        pesquisar.add( pesqMAT_porIva = new MenuItem("Pesquisar materiais por Valor de IVA") );

        barraMenus.add(ficheiro);
        barraMenus.add(funcionario);
        barraMenus.add(cliente);
        barraMenus.add(material);
        barraMenus.add(notaFiscal);
        barraMenus.add(tabelas);
        barraMenus.add(pesquisar);
        barraMenus.add(listagens);
        barraMenus.add(ajuda);

        setMenuBar(barraMenus);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        adicionarEventos(); 
    }

    
	public void adicionarEventos()
	{
        sair.addActionListener(this);
        //defesa.addActionListener(this);

        novoFuncionario.addActionListener(this);
        visualizarFuncionario.addActionListener(this);

        novoCliente.addActionListener(this);
        visualizarCliente.addActionListener(this);
            
        novoMaterial.addActionListener(this);
        visualizarMaterial.addActionListener(this);

        novaNotaFiscal.addActionListener(this);  
        visualizarNotaFiscal.addActionListener(this);
            
        //menu tabelas
        provincia.addActionListener(this); 
        municipio.addActionListener(this); 
        comuna.addActionListener(this);
        loja.addActionListener(this); 
        tipoCliente.addActionListener(this); 
        estadoCivil.addActionListener(this); 
        nacionalidade.addActionListener(this); 
        valorIva.addActionListener(this); 

        //menu pesquisar
        pesqFUN_porLoja.addActionListener(this); 
        pesqFUN_porSexo.addActionListener(this); 
        pesqMAT_porIva.addActionListener(this); 
     
     
	}

    public void actionPerformed(ActionEvent evt)
	{
       
		if (evt.getSource() == novoFuncionario) new Funcionario_GUI(null);
		else if (evt.getSource() == visualizarFuncionario) new Visualizar("funcionarios");

        else if (evt.getSource() == novoCliente) new Cliente_GUI(null);
		else if (evt.getSource() == visualizarCliente) new Visualizar("clientes");

        else if (evt.getSource() == novoMaterial) new Material_GUI(null);  
        else if (evt.getSource() == visualizarMaterial) new Visualizar("materiais");

        else if (evt.getSource() == novaNotaFiscal) new NotaFiscal_GUI(null);
        else if (evt.getSource() == visualizarNotaFiscal) new Visualizar("notas fiscais");

        // pesquisas
        else if (evt.getSource() == pesqFUN_porLoja) new Pesquisar("funcionarios", "loja");
        else if (evt.getSource() == pesqFUN_porSexo) new Pesquisar("funcionarios", "sexo");
        else if (evt.getSource() == pesqMAT_porIva) new PesquisarPorIVA();

        //pesquisar material por iva
        else if (evt.getSource() == defesa) new PesquisarPorIVA();


        else if (evt.getSource() == loja)
        Tabela2.editarNovosItems(Defs.FILE_LOJAS, "Nova Loja");
        
        else if (evt.getSource() == tipoCliente)
            Tabela2.editarNovosItems(Defs.FILE_TIPO_CLIENTE, "Novo Tipo de Cliente");
        
        else if (evt.getSource() == estadoCivil)
            Tabela2.editarNovosItems(Defs.FILE_ESTADO_CIVIL, "Novo Estado Civil");

        else if (evt.getSource() == nacionalidade)
            Tabela2.editarNovosItems(Defs.FILE_SUBSIDIO, "Novo Subsidio");

        else if (evt.getSource() == valorIva)
            Tabela2.editarNovosItems(Defs.FILE_VALOR_IVA, "Novo Valor de IVA");

        else if (evt.getSource() == provincia)
            Tabela2.editarNovosItems(Defs.FILE_PROVINCIAS, "Nova Provincia");

        else if (evt.getSource() == municipio)
             Tabela3_2.editarNovosItems(Defs.FILE_PROVINCIAS, Defs.FILE_MUNICIPIOS, "Provincia", "Municipio", "Novo Municipio");
                  
	    else if (evt.getSource() == comuna)
            Tabela3_3.editarNovosItems(Defs.FILE_PROVINCIAS, Defs.FILE_MUNICIPIOS, Defs.FILE_COMUNAS, "Provincia", "Municipio", "Comuna", "Nova Comuna ");

		else  if (evt.getSource() == sair)
			        if (JOptionPane.showConfirmDialog(null, "Quer Sair?", "Saindo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        System.exit(0);
                        
	}	


}
