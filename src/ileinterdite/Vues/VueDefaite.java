/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author richomml
 */
public class VueDefaite {
    
    private JFrame fenetre;
    
    private JPanel conteneurLabel;
    
    private JLabel texteDefaite;
    
    private Font font;
    
    public VueDefaite(){
        fenetre = new JFrame("Vous avez perdu ! ");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        
        conteneurLabel = new JPanel(new BorderLayout());
        
        texteDefaite = new JLabel("DEFAITE !");
        
        font = new Font("Defaite",Font.PLAIN,80);
        
        texteDefaite.setFont(font);
        
        
        
        conteneurLabel.add(texteDefaite,BorderLayout.CENTER);
        
        
        
    }
    
}
