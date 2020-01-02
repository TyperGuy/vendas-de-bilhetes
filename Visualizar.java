/*-------------------------------------------------------------
Ficheiro: Visualizar.java
Autor: Edson Gregório
Objectivo: Visão/Interface para cadastro de novo visualizar
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;


public class Visualizar extends JFrame {

    private JPanel painel, painel2;
    private List Lista;
    private JButton eliminar, editar, visualizar;
    

    public Visualizar(final String entidade) {
        super("Lista de " + entidade + " disponiveis");

        initComponents();

        if( entidade.equals("clientes") )
        {
            StringVector clientes = Cliente_File.getAllClientes();
            for (int i=0; i < clientes.size(); i++)
                Lista.add ( (String) clientes.get(i) );
        }
        else if( entidade.equals("funcionarios") )
        {
            StringVector funcionarios = Funcionario_File.getAllFuncionarios();
            for (int i=0; i < funcionarios.size(); i++)
                Lista.add ( (String) funcionarios.get(i) );
        }
        else if( entidade.equals("materiais") )
        {
            StringVector materiais = Material_File.getAllMateriais();
            for (int i=0; i < materiais.size(); i++)
                Lista.add ( (String) materiais.get(i) );
        }
        else if( entidade.equals("notas fiscais") )
        {
            StringVector notasFiscais = NotaFiscal_File.getAllNotasFiscais();
            for (int i=0; i < notasFiscais.size(); i++)
                Lista.add ( (String) notasFiscais.get(i) );
        }


        visualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) 
            {
                Object objecto = null;
                if( entidade.equals("clientes") )
                    objecto = Cliente_File.getClienteModelo ( Lista.getSelectedItem() );

                else if( entidade.equals("funcionarios") )
                    objecto = Funcionario_File.getFuncionarioModelo ( Lista.getSelectedItem() );
                 
                else if( entidade.equals("materiais") )
                    objecto = Material_File.getMaterialModelo ( Lista.getSelectedItem() );
                
                else if( entidade.equals("notas fiscais") )
                {
                    StringTokenizer sep = new StringTokenizer( Lista.getSelectedItem(), "-");
                    objecto = NotaFiscal_File.getNotaFiscalModelo (Integer.parseInt(sep.nextToken().trim()) );
                }
                JOptionPane.showMessageDialog(null, objecto.toString());  //mostrar o modelo via message dialog
            }
        });

        editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ( Lista.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Selecione um elemento!" +  Lista.getSelectedItem());
                else {
                    try {
                        if( entidade.equals("clientes") )
                        {
                            Cliente_Modelo cliente = Cliente_File.getClienteModelo ( Lista.getSelectedItem() );
                            new Cliente_GUI(cliente);
                        }
                        else if( entidade.equals("funcionarios") )
                        {
                            Funcionario_Modelo funcionario = Funcionario_File.getFuncionarioModelo ( Lista.getSelectedItem() );
                            new Funcionario_GUI(funcionario);
                        }
                        else if( entidade.equals("materiais") )
                        {
                            Material_Modelo material = Material_File.getMaterialModelo ( Lista.getSelectedItem() );
                            new Material_GUI(material);
                        }
                        else if( entidade.equals("notas fiscais") )
                        {
                            StringTokenizer sep = new StringTokenizer( Lista.getSelectedItem(), "-");
                            NotaFiscal_Modelo notaFiscal = NotaFiscal_File.getNotaFiscalModelo ( Integer.parseInt(sep.nextToken().trim()));
                            new NotaFiscal_GUI(notaFiscal);
                        }
                        dispose();
                    }  
                    catch (Exception e) { e.printStackTrace(); } 
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ( Lista.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Selecione um elemento!" +  Lista.getSelectedItem());
                else {
                    try {
                        if( entidade.equals("clientes") )
                        {
                            Cliente_Modelo cliente = Cliente_File.getClienteModelo ( Lista.getSelectedItem() );
                            cliente.eliminar();
                        }
                        else if( entidade.equals("funcionarios") )
                        {
                            Funcionario_Modelo funcionario = Funcionario_File.getFuncionarioModelo ( Lista.getSelectedItem() );
                            funcionario.eliminar();
                        }
                        else if( entidade.equals("materiais") )
                        {
                            Material_Modelo material = Material_File.getMaterialModelo ( Lista.getSelectedItem() );
                            material.eliminar();
                        }
                        else if( entidade.equals("notas fiscais") )
                        {
                            StringTokenizer sep = new StringTokenizer( Lista.getSelectedItem(), "-");
                            NotaFiscal_Modelo notaFiscal = NotaFiscal_File.getNotaFiscalModelo ( Integer.parseInt(sep.nextToken().trim())  );
                            notaFiscal.eliminar();
                        }
                        dispose();
                        new Visualizar(entidade);
                    }  
                    catch (Exception e) { e.printStackTrace(); } 
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painel, BorderLayout.CENTER);
        getContentPane().add(new JLabel("   "), BorderLayout.EAST);
        getContentPane().add(new JLabel("   "), BorderLayout.WEST);
        getContentPane().add(new JLabel("   "), BorderLayout.NORTH);
        getContentPane().add(painel2, BorderLayout.SOUTH);
        setSize(350, 370);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents()
    {
        painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add( Lista = new List(10, false), BorderLayout.CENTER);

        painel2 = new JPanel();    
        painel2.setLayout(new FlowLayout());
        painel2.add( visualizar  = new JButton("Visualizar") );
        painel2.add( editar  = new JButton("Editar") );
        painel2.add( eliminar = new JButton("Eliminar") );
    }
}