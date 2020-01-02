/*-------------------------------------------------------------
Ficheiro: Pesquisar.java
Autor: Edson Gregório
Objectivo: Visão/Interface de pesquisa
--------------------------------------------------------------*/
import SwingComponents.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class Pesquisar extends JFrame {

    private JPanel p1 = new JPanel( new FlowLayout() );
    private List Lista;
    private JButton pesquisar = new JButton("Pesquisar");
    private JComboBox lojasJC, sexoJC;
    private String[] sexo = {"Masculino", "Feminino"};

    public Pesquisar(final String entidade, final String criterioDePesquisa ) {
        super("Pesquisar " + entidade + " por "+criterioDePesquisa);

         if( entidade.equals("funcionarios") && criterioDePesquisa.equals("loja"))
            p1.add( lojasJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_LOJAS) );

        else if( entidade.equals("funcionarios") && criterioDePesquisa.equals("sexo"))
            p1.add( sexoJC = new JComboBox(sexo));
 

        pesquisar.addActionListener( new ActionListener() { 
            public void actionPerformed(ActionEvent event) 
            {
                if( entidade.equals("funcionarios") )
                    if( criterioDePesquisa.equals("loja") )
                        Funcionario_File.pesquisarFuncionariosPorLoja( lojasJC.getSelectedItem().toString() );
                    else
                        if( criterioDePesquisa.equals("sexo") )
                            Funcionario_File.pesquisarFuncionariosPorSexo( sexoJC.getSelectedItem().toString() );
            }
        });

        getContentPane().setLayout(new GridLayout(3, 1));
        getContentPane().add( new JLabel(" Selecione um item: "));
        getContentPane().add( p1 );
        getContentPane().add( pesquisar );
        setSize(300, 220);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
       
    public static int getDia(String data) 
    {
        StringTokenizer stDdata1 = new StringTokenizer(data, "-");
        return Integer.parseInt(stDdata1.nextToken());
    }
    public static String getMes(String data) 
    {
        StringTokenizer stDdata1 = new StringTokenizer(data, "-");
        stDdata1.nextToken(); //dia
        return stDdata1.nextToken(); //mes
    }
    public static int getAno(String data) 
    {
        StringTokenizer stDdata1 = new StringTokenizer(data, "-");
        stDdata1.nextToken(); //dia
        stDdata1.nextToken(); //mes
        return Integer.parseInt(stDdata1.nextToken());  //ano
    }

}