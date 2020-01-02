/*-------------------------------------------------------------
Ficheiro: Material_GUI.java
Autor: Edson Gregório
Objectivo: Visão/Interface para cadastro de novo material
--------------------------------------------------------------*/
import Calendario.JTextFieldData;
import SwingComponents.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public final class Material_GUI extends JFrame {

    private JTextField codMaterialJT, nomeJT, valorUnitJT;
    private JComboBox ivaJC;
    private JButton salvar, limpar, cancelar, eliminar, editar;
    private JPanel p1, p2;
  

   // private Material_Modelo material;
    public Material_GUI( Material_Modelo modelo ) {
        super("Novo Material");
        addComponents();
        limpar();

        if (modelo != null) preencherCampos( modelo );

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvar();
                limpar();
                codMaterialJT.setText( String.valueOf(new Material_File( new Material_Modelo()).getProximoCodigo()) );
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
                new Visualizar("materiais");
            }
        });

        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(new JLabel("   "), BorderLayout.EAST);
        getContentPane().add(new JLabel("   "), BorderLayout.WEST);
        getContentPane().add(new JLabel("   "), BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);

        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void addComponents() {
        p1 = new JPanel(new GridLayout(4, 2, 5, 5));
        p2 = new JPanel(new FlowLayout());

        p1.add( new JLabel(" N. ") );
        p1.add( codMaterialJT = new JTextField() );
	    codMaterialJT.setText( String.valueOf(new Material_File( new Material_Modelo()).getProximoCodigo()) );
        codMaterialJT.setEditable(false);

        p1.add( new JLabel(" Nome do Material") );
        p1.add( nomeJT = new JTextField() );

        p1.add(new JLabel(" Valor Unitario (Kz)"));
        p1.add( valorUnitJT = new JTextField() );

        p1.add(new JLabel(" IVA"));
        p1.add( ivaJC = UInterfaceBox.createJComboBoxsTabela2(Defs.FILE_VALOR_IVA) );

        p2.add( limpar = new JButton("Limpar") );
        p2.add( salvar = new JButton("Salvar") );
        p2.add( editar = new JButton("Editar") );
        p2.add( cancelar = new JButton("Fechar") );
    }

    public void preencherCampos ( Material_Modelo modelo ) 
    {
        codMaterialJT.setText(""+ modelo.getCodMaterial() );  
        nomeJT.setText( modelo.getNome() );
        valorUnitJT.setText(""+ modelo.getValorUnitario() );
        ivaJC.setSelectedItem(""+ modelo.getIva() );

        salvar.setVisible(false);
    }

    public void limpar() 
    {
        nomeJT.setText("");
        valorUnitJT.setText("");
        ivaJC.setSelectedIndex(0);
    }

    public void salvar()
    {
        //Enviar os dados para modelo
        Material_Modelo modelo = new Material_Modelo(
            this.getCodMaterial(), 
            this.getNome(), 
            this.getValorUnitario(),
            this.getIva()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.salvar();
    }

    private void editar() 
    {
        //Enviar os dados para modelo
        Material_Modelo modelo = new Material_Modelo(
            this.getCodMaterial(), 
            this.getNome(), 
            this.getValorUnitario(),
            this.getIva()
        );
        JOptionPane.showMessageDialog(null, modelo.toString());
        modelo.editar();
    }
 

    public int getCodMaterial() { return Integer.parseInt( codMaterialJT.getText().trim() ); }

    public String getNome() { return nomeJT.getText().trim(); }

    public float getValorUnitario() { return Float.parseFloat(valorUnitJT.getText().trim()); }

    public float getIva() { return Float.parseFloat(ivaJC.getSelectedItem().toString().trim()); }

    @Override
    public String toString() {
        return "MaterialGUI = {" 
                + "\n Codigo:" + getCodMaterial()
                + "\n Nome: " + getNome() 
                + "\n Valor Unitario: " + getValorUnitario()   
                + "\n IVA: " + getIva()     
                + "\n }";
    }
}