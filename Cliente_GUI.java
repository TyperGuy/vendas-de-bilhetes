/*-------------------------------------------------------------
Ficheiro: Cliente_GUI.java
Autor: Edson Gregório
Objectivo: Visão/Interface para cadastro de novo cliente
--------------------------------------------------------------*/

import Calendario.JTextFieldData;
import SwingComponents.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Cliente_GUI extends JFrame {

    private JTextField codClienteJT, nomeJT, telefoneJT;
    private JComboBox provinciaJC, municipioJC,  comunaJC, tipoClienteJC;
  
    private JButton salvar, limpar, cancelar, eliminar, editar;
    private JPanel p1, p2;

    public Cliente_GUI( Cliente_Modelo modelo ) {
        super("Novo Cliente");
        addComponents();
        limpar();

        if (modelo != null) preencherCampos( modelo );

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvar();
                limpar();
                codClienteJT.setText( String.valueOf(new Cliente_File( new Cliente_Modelo()).getProximoCodigo()) );
            }
        });
    
        limpar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        cancelar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        editar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar();
                dispose();
                new Visualizar("clientes");
            }
        });
        
        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(new JLabel("   "), BorderLayout.EAST);
        getContentPane().add(new JLabel("   "), BorderLayout.WEST);
        getContentPane().add(new JLabel("   "), BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);

        setSize(400, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void addComponents() 
    {
        p1 = new JPanel( new GridLayout(8, 2, 5, 5) );
        p2 = new JPanel( new FlowLayout() );

        p1.add( new JLabel("N.") );
		p1.add( codClienteJT = new JTextField() );
	    codClienteJT.setText( String.valueOf(new Cliente_File( new Cliente_Modelo()).getProximoCodigo()) );
		codClienteJT.setEditable(false);
        
        p1.add( new JLabel(" Nome do Cliente") );
        p1.add( nomeJT = new JTextField() );

        p1.add( new JLabel(" Telefone") );
        p1.add( telefoneJT = new JTextField() );

        JComboBoxTabela3_Tabela3 proMuniComu = new JComboBoxTabela3_Tabela3(Defs.FILE_PROVINCIAS, Defs.FILE_MUNICIPIOS, Defs.FILE_COMUNAS);
       
        p1.add( new JLabel(" Provincia"));
        p1.add( provinciaJC = proMuniComu.getComboBoxFather() );

        p1.add(new JLabel(" Municipio"));
        p1.add( municipioJC = proMuniComu.getComboBoxSun() );

        p1.add( new JLabel(" Comuna") );
        p1.add( comunaJC= proMuniComu.getComboBoxNeto() );

        p1.add( new JLabel(" Tipo de Cliente"));
        p1.add( tipoClienteJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_TIPO_CLIENTE) );

        p2.add( limpar = new JButton("Limpar") );
        p2.add( salvar = new JButton("Salvar") );
        p2.add( editar = new JButton("Editar") );
        p2.add( cancelar = new JButton("Fechar") );
    }

    public void preencherCampos ( Cliente_Modelo modelo ) 
    {
        codClienteJT.setText(""+ modelo.getCodCliente() );  
        nomeJT.setText( modelo.getNome() );
        telefoneJT.setText( modelo.getTelefone() );
        provinciaJC.setSelectedItem( modelo.getProvincia() );
        municipioJC.setSelectedItem( modelo.getMunicipio() );  
        comunaJC.setSelectedItem( modelo.getComuna() );
        tipoClienteJC.setSelectedItem( modelo.getTipoCliente() );
        salvar.setVisible(false);
    }

    public void limpar() 
    {
        nomeJT.setText("");
        telefoneJT.setText("");
        provinciaJC.setSelectedIndex(0);
        municipioJC.setSelectedIndex(0);
        comunaJC.setSelectedIndex(0);
        tipoClienteJC.setSelectedIndex(0);
    }

    public void salvar()
    {
        //Enviar os dados para modelo
        Cliente_Modelo modelo = new Cliente_Modelo(
            this.getCodCliente(), 
            this.getNome(), 
            this.getTelefone(), 
            this.getProvincia(), 
            this.getMunicipio(), 
            this.getComuna(),
            this.getTipoCliente()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.salvar();
    }

    private void editar() 
    {
        //Enviar os dados para modelo
        Cliente_Modelo modelo = new Cliente_Modelo(
            this.getCodCliente(), 
            this.getNome(), 
            this.getTelefone(), 
            this.getProvincia(), 
            this.getMunicipio(), 
            this.getComuna(),
            this.getTipoCliente()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.editar();
    }
 
    // Metodos get da view
    public int getCodCliente() { return Integer.parseInt( codClienteJT.getText().trim() ); }

    public String getNome() {	return nomeJT.getText().trim(); }

    public String getTelefone() { return telefoneJT.getText().trim(); }

    public String getProvincia() { return  provinciaJC.getSelectedItem().toString(); }

    public String getMunicipio() { return municipioJC.getSelectedItem().toString(); }
    
    public String getComuna() { return comunaJC.getSelectedItem().toString(); }
    
    public String getTipoCliente() { return tipoClienteJC.getSelectedItem().toString(); }

     @Override
    public String toString() {
        return "ClienteGUI = {" 
                    + "\n Codigo:" + getCodCliente()
                    + "\n Nome: " + getNome() 
                    + "\n Telefone: " + getTelefone() 
                    + "\n Provincia: " +  getProvincia()
                    + "\n Municipio: " + getMunicipio() 
                    + "\n Comuna: " + getComuna() 
                    + "\n Tipo de Cliente: " + getTipoCliente() 
                    + "\n }";
    }

}
