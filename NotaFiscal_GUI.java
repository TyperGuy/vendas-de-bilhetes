/*-------------------------------------------------------------
Ficheiro: NotaFiscal_GUI.java
Autor: Edson Gregório
Objectivo: Visão/Interface para cadastro de nova nota fiscal
--------------------------------------------------------------*/
import Calendario.JTextFieldData;
import SwingComponents.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class NotaFiscal_GUI extends JFrame {

    private JTextField codNotaFiscalJT, valorUnitarioJT,  quantidadeJT, valorTotalJT;
    private JComboBox clienteJC, funcionarioJC, lojaJC, materialJC;
    private JTextFieldData dataEmissaoJTD;
  
    private JButton salvar, limpar, cancelar, eliminar, editar;
    private JPanel p1, p2;

    public NotaFiscal_GUI ( NotaFiscal_Modelo modelo ) {
        super("Nova Nota Fiscal");
        addComponents();
        limpar();

        if (modelo != null) preencherCampos( modelo );
        
        lojaJC.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getAllFuncionarios(); 
            }
        });

        materialJC.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarValorUnitario(); 
            }
        });
        salvar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarValorTotal();
                salvar();
                limpar();
                codNotaFiscalJT.setText( String.valueOf(new NotaFiscal_File( new NotaFiscal_Modelo()).getProximoCodigo()) );
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
                new Visualizar("notas fiscais");
            }
        });

        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(new JLabel("   "), BorderLayout.EAST);
        getContentPane().add(new JLabel("   "), BorderLayout.WEST);
        getContentPane().add(new JLabel("   "), BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);

        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void addComponents() 
    {
        p1 = new JPanel( new GridLayout(9, 2, 5, 5) );
        p2 = new JPanel( new FlowLayout() );

        p1.add( new JLabel("Nota Fiscal") );
		p1.add( codNotaFiscalJT = new JTextField() );
	    codNotaFiscalJT.setText( String.valueOf(new NotaFiscal_File( new NotaFiscal_Modelo()).getProximoCodigo()) );
		codNotaFiscalJT.setEditable(false);

        p1.add( new JLabel(" Cliente") );
        p1.add( clienteJC = new JComboBox(Cliente_File.getAllClientes()) );
        
        p1.add( new JLabel(" Loja:"));
        p1.add( lojaJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_LOJAS) );

        p1.add( new JLabel(" Funcionario:") );
        p1.add( funcionarioJC = new JComboBox(Funcionario_File.getAllFuncionarios()) );

        p1.add( new JLabel(" Material") );
        p1.add( materialJC = new JComboBox(Material_File.getAllMateriais()) );

        p1.add( new JLabel(" Valor Unitario") );
        p1.add( valorUnitarioJT = new JTextField() );
        valorUnitarioJT.setEditable(false);

        p1.add( new JLabel(" Quantidade") );
        p1.add(  quantidadeJT = new JTextField() );

        p1.add( new JLabel(" Valor Total") );
        p1.add( valorTotalJT = new JTextField() );
        valorTotalJT.setEditable(false);

        p1.add(new JLabel(" Data de Emissao"));
        dataEmissaoJTD = new JTextFieldData("");
        JPanel painelDataEmissao = new JPanel( new GridLayout(1, 2, 2, 0) );
        painelDataEmissao.add( dataEmissaoJTD.getDTestField() );
        painelDataEmissao.add( dataEmissaoJTD.getDButton() );
        p1.add( painelDataEmissao );

        eliminar = new JButton("Eliminar");
        

        p2.add( limpar   = new JButton("Limpar") );
        p2.add( salvar = new JButton("Salvar") );
        p2.add( editar  = new JButton("Editar") );
        p2.add( cancelar = new JButton("Cancelar") );
    }
    
    public void getAllFuncionarios()
    {
        StringVector funcionariosVT = Funcionario_File.getFuncionariosPorLoja( getLoja() );
        p1.remove(7); //remove o elemento da posicao 7 (funcionarioJC)
        funcionarioJC.setModel(new DefaultComboBoxModel(funcionariosVT));
        p1.add(funcionarioJC, 7); //add funcionarioJC na posicao 7 do painel
        p1.repaint();
    }

    public void actualizarValorUnitario()
	{
		valorUnitarioJT.setText( String.valueOf(Material_File.getValorUnitario( getMaterial() )));
	}

    public void actualizarValorTotal()
	{
        float valorT, valorU;
        int quant;
        valorU = Float.parseFloat( getValorUnitario());
        quant = Integer.parseInt( getQuantidade());
        valorT = valorU * quant;
		valorTotalJT.setText(""+valorT);
    }
    

    public void preencherCampos ( NotaFiscal_Modelo modelo ) 
    {
        codNotaFiscalJT.setText(""+ modelo.getCodNotaFiscal() );  
        clienteJC.setSelectedItem( modelo.getCliente() );
        funcionarioJC.setSelectedItem( modelo.getFuncionario() );
        lojaJC.setSelectedItem( modelo.getLoja() );
        materialJC.setSelectedItem( modelo.getMaterial() );
        quantidadeJT.setText(""+ modelo.getQuantidade() ); 
        valorTotalJT.setText(""+ modelo.getValorTotal() );
        dataEmissaoJTD.getDTestField().setText( modelo.getDataEmissao() );
        getAllFuncionarios();
        actualizarValorUnitario();
        salvar.setVisible(false);
    }

    public void limpar() 
    {
        quantidadeJT.setText(""); 
        valorTotalJT.setText("");;
        clienteJC.setSelectedIndex(0);
        funcionarioJC.setSelectedIndex(0);
        lojaJC.setSelectedIndex(0); 
        materialJC.setSelectedIndex(0);
        dataEmissaoJTD.getDTestField().setText("");
        getAllFuncionarios();
        actualizarValorUnitario();
    }

    public void salvar()
    {
        //Enviar os dados para modelo
        NotaFiscal_Modelo modelo = new NotaFiscal_Modelo(
            this.getCodNotaFiscal(), 
            this.getCliente(), 
            this.getFuncionario(), 
            this.getLoja(), 
            this.getMaterial(),
            Float.parseFloat( this.getValorUnitario() ), 
            Integer.parseInt( this.getQuantidade() ),
            Float.parseFloat( this.getValorTotal() ),
            this.getDataEmissao()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.salvar();
    }

    private void editar() 
    {
        //Enviar os dados para modelo
        NotaFiscal_Modelo modelo = new NotaFiscal_Modelo(
            this.getCodNotaFiscal(), 
            this.getCliente(), 
            this.getFuncionario(), 
            this.getLoja(), 
            this.getMaterial(), 
            Float.parseFloat( this.getValorUnitario() ), 
            Integer.parseInt( this.getQuantidade() ),
            Float.parseFloat( this.getValorTotal() ),
            this.getDataEmissao()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.editar();
    }


    // Metodos get da view
    public int getCodNotaFiscal() { return Integer.parseInt( codNotaFiscalJT.getText().trim() ); }

    public String getCliente() { return clienteJC.getSelectedItem().toString(); }
    
    public String getFuncionario() { return funcionarioJC.getSelectedItem().toString(); }
    
    public String getLoja() { return  lojaJC.getSelectedItem().toString(); }
    
    public String getMaterial() { return materialJC.getSelectedItem().toString(); }

    public String getValorUnitario() {	return valorUnitarioJT.getText().trim(); }

    public String getQuantidade() { return  quantidadeJT.getText().trim(); }

    public String getValorTotal() {	return valorTotalJT.getText().trim(); }
    
    public String getDataEmissao() { return dataEmissaoJTD.getDTestField().getText().trim(); }

     @Override
    public String toString() {
        return "NotaFiscalGUI = {" 
                + "\n Codigo:" + getCodNotaFiscal()
                + "\n Cliente: " + getCliente() 
                + "\n Funcionario: " + getFuncionario() 
                + "\n Loja: " +  getLoja()
                + "\n Material: " + getMaterial() 
                + "\n Valor Unitario: " + getValorUnitario() 
                + "\n Quantidade: " + getQuantidade() 
                + "\n Valor Total: " + getValorTotal() 
                + "\n Data de Emissao: " + getDataEmissao() 
                + "\n }";
    }

}
