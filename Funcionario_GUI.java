/*-------------------------------------------------------------
Ficheiro: Funcionario_GUI.java
Autor: Edson Gregório
Objectivo: Visão/Interface para cadastro de novo funcionario
--------------------------------------------------------------*/
import Calendario.JTextFieldData;
import SwingComponents.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Funcionario_GUI extends JFrame {

    
    private JTextField codFuncionarioJT, nomeJT, telefoneJT, biJT;
    private JComboBox estadoCivilJC, nacionalidadeJC,  localTrabalhoJC;
    private JRadioButton masculinoJRB, femininoJRB;
    private ButtonGroup grupo;
    private JTextFieldData dataNascimentoJTD;
    private JButton salvar, limpar, cancelar, eliminar, editar;
    private JPanel p1, p2;

    public Funcionario_GUI( Funcionario_Modelo modelo ) {
        super("Novo Funcionario");
        addComponents();
        limpar();

        if (modelo != null) preencherCampos( modelo );

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvar();
                limpar();
                codFuncionarioJT.setText( String.valueOf(new Funcionario_File( new Funcionario_Modelo()).getProximoCodigo()) );
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
                new Visualizar("funcionarios");
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
        p1 = new JPanel( new GridLayout(9, 2, 5, 5) );
        p2 = new JPanel( new FlowLayout() );

        p1.add( new JLabel("N.") );
		p1.add( codFuncionarioJT = new JTextField() );
	    codFuncionarioJT.setText( String.valueOf(new Funcionario_File( new Funcionario_Modelo()).getProximoCodigo()) );
		codFuncionarioJT.setEditable(false);
        
        p1.add( new JLabel(" Nome do Funcionario") );
        p1.add( nomeJT = new JTextField() );

        p1.add( new JLabel(" Telefone") );
        p1.add( telefoneJT = new JTextField() );

        p1.add( new JLabel(" BI") );
        p1.add( biJT = new JTextField() );

        p1.add(new JLabel(" Data de Nascimento"));
        dataNascimentoJTD = new JTextFieldData("");
        JPanel painelDataNascimento = new JPanel( new GridLayout(1, 2, 2, 0) );
        painelDataNascimento.add( dataNascimentoJTD.getDTestField() );
        painelDataNascimento.add( dataNascimentoJTD.getDButton() );
        p1.add( painelDataNascimento );
       
        p1.add( new JLabel(" Estado Civil"));
        p1.add( estadoCivilJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_ESTADO_CIVIL) );

        p1.add(new JLabel(" Sexo"));
		JPanel painelSexo = new JPanel();
		painelSexo.setLayout( new GridLayout(1, 2, 2, 0) );
		painelSexo.add( masculinoJRB = new JRadioButton("M", true) );
		painelSexo.add( femininoJRB = new JRadioButton("F") );
		grupo = new ButtonGroup();
		grupo.add( masculinoJRB );
		grupo.add( femininoJRB );
		p1.add( painelSexo );

        p1.add(new JLabel(" Nacionalidade"));
        p1.add( nacionalidadeJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_NACIONALIDADE) );

        p1.add( new JLabel(" Local de Trabalho") );
        p1.add( localTrabalhoJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_LOJAS) );

        p2.add( limpar = new JButton("Limpar") );
        p2.add( salvar = new JButton("Salvar") );
        p2.add( editar = new JButton("Editar") );
        p2.add( cancelar = new JButton("Fechar") );
    }

    public void preencherCampos ( Funcionario_Modelo modelo ) 
    {
        codFuncionarioJT.setText(""+ modelo.getCodFuncionario() );  
        nomeJT.setText( modelo.getNome() );
        telefoneJT.setText( modelo.getTelefone() );
        biJT.setText( modelo.getBi() );
        dataNascimentoJTD.getDTestField().setText( modelo.getDataNascimento());
        estadoCivilJC.setSelectedItem( modelo.getEstadoCivil() );
        nacionalidadeJC.setSelectedItem( modelo.getNacionalidade() );  
        localTrabalhoJC.setSelectedItem( modelo.getLocalTrabalho() );
        if( modelo.getSexo().equals("Masculino")) masculinoJRB.setSelected(true);
        else femininoJRB.setSelected(true);
        salvar.setVisible(false);
    }

    public void limpar() 
    {
        nomeJT.setText("");
        telefoneJT.setText("");
        biJT.setText("");
        dataNascimentoJTD.getDTestField().setText("");
        estadoCivilJC.setSelectedIndex(0);
        nacionalidadeJC.setSelectedIndex(0);  
        localTrabalhoJC.setSelectedIndex(0);
    }

    public void salvar()
    {
        //Enviar os dados para modelo
        Funcionario_Modelo modelo = new Funcionario_Modelo(
            this.getCodFuncionario(), 
            this.getNome(), 
            this.getTelefone(), 
            this.getBi(), 
            this.getEstadoCivil(), 
            this.getDataNascimento(), 
            this.getSexo(), 
            this.getNacionalidade(), 
            this.getLocalTrabalho()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.salvar();
    }

    private void editar() {
        Funcionario_Modelo modelo = new Funcionario_Modelo(
            this.getCodFuncionario(), 
            this.getNome(), 
            this.getTelefone(), 
            this.getBi(), 
            this.getEstadoCivil(), 
            this.getDataNascimento(), 
            this.getSexo(), 
            this.getNacionalidade(), 
            this.getLocalTrabalho()
        );
        //JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.editar();
    }

 
    // Metodos get da Visao
    public int getCodFuncionario() { return Integer.parseInt( codFuncionarioJT.getText().trim() ); }

    public String getNome() {	return nomeJT.getText().trim(); }

    public String getTelefone() { return telefoneJT.getText().trim(); }

    public String getDataNascimento() { return dataNascimentoJTD.getDTestField().getText().trim(); }

    public String getBi() { return biJT.getText().trim().trim(); }

    public String getNacionalidade() { return nacionalidadeJC.getSelectedItem().toString(); }
    
    public String getLocalTrabalho() { return localTrabalhoJC.getSelectedItem().toString(); }
    
    public String getEstadoCivil() { return estadoCivilJC.getSelectedItem().toString(); }

    public String getSexo() {
        if ( masculinoJRB.isSelected() ) return "Masculino";
        return "Feminino";
    }

      @Override
    public String toString() 
    {
        return "FuncionarioGUI = {" 
                + "\n Codigo: " + getCodFuncionario()
                + "\n Nome: " + getNome() 
                + "\n Telefone: " + getTelefone() 
                + "\n BI: " + getBi()
                + "\n Estado Civil: " + getEstadoCivil() 
                + "\n Data de Nascimento: " + getDataNascimento() 
                + "\n Sexo: " + getSexo() 
                + "\n Nacionalidade: " + getNacionalidade() 
                + "\n Local de Trabalho: " + getLocalTrabalho() 
                + "\n }";
    }

}
