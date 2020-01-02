/*-------------------------------------------------------------
Ficheiro: Login.java
Autor: Edson Gregório
Objectivo: Visão/Interface de Login
--------------------------------------------------------------*/
import Calendario.JTextFieldData;
import SwingComponents.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public final class Login extends JFrame {

    private JTextField loginJT;
    private JPasswordField senhaJT;
    
    private JButton entrar;
    private JPanel p1, p2;
  
    public Login( ) {
        super("LOGIN");
        addComponents();

        entrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if( getLogin().equalsIgnoreCase("edsonpaulo") && getSenha().equalsIgnoreCase("12345") )
                    {
                        new Home();
                        SwingComponents.Vector_Tabelas.inic();
                        dispose();
                    }
                    else JOptionPane.showMessageDialog(null, "Login/Senha incorrecta!");
                }
                catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(new JLabel("   "), BorderLayout.EAST);
        getContentPane().add(new JLabel("   "), BorderLayout.WEST);
        getContentPane().add(new JLabel("   "), BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);

        setSize(200, 230);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void addComponents() {
        p1 = new JPanel(new GridLayout(4, 1, 2, 2));
        p2 = new JPanel(new FlowLayout());

        p1.add( new JLabel(" Login "));
        p1.add( loginJT = new JTextField());

        p1.add( new JLabel("Senha "));
        p1.add( senhaJT = new JPasswordField(10));

        p2.add( entrar = new JButton("Entrar") );
    }

    public String getLogin() { return loginJT.getText().trim(); }

    public String getSenha() { return senhaJT.getText().trim(); }

    @Override
    public String toString() {
        return "LoginGUI = {"
                + "\n Login: " + getLogin() 
                + "\n Valor Unitario: " + getSenha()     
                + "\n }";
    }

    
    public static void ativarTemaDoSistema()   {
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException ex) {
             System.out.println(ex);
         } 
     }
 
     public static void main(String[] args) {
        ativarTemaDoSistema();
        new Login();
     }
}
