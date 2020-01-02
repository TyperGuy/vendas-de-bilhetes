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

public class PesquisarPorIVA extends JFrame {

    private JPanel p1 = new JPanel( new FlowLayout() );
    private List Lista;
    private JButton pesquisar = new JButton("Pesquisar");
    private JComboBox ivaJC;

    public PesquisarPorIVA( ) {
        super("Pesquisar Materiais por IVA");

        pesquisar.addActionListener( new ActionListener() { 
            public void actionPerformed(ActionEvent event) 
            {
                Material_File.pesquisarMaterialPorIVA( Float.parseFloat( ivaJC.getSelectedItem().toString() ) );
            }
        });

        getContentPane().setLayout(new GridLayout(3, 1, 10, 10));
        getContentPane().add( new JLabel(" Selecione o valor de IVA: "));
        getContentPane().add( ivaJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_VALOR_IVA) );
        getContentPane().add( pesquisar );
        setSize(230, 180);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}