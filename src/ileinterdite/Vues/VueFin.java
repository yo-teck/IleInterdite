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
public class VueFin {
    
    private JFrame fenetre;
    
    private JPanel conteneurLabel;
    
    private JLabel texteDefaite;
    
    private Font font;
    
    public VueFin(String etatPartie){
        fenetre = new JFrame("Fin de la partie ");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        
        conteneurLabel = new JPanel(new BorderLayout());
        
        texteDefaite = new JLabel(etatPartie);
        
        font = new Font(etatPartie,Font.PLAIN,80);
        
        texteDefaite.setFont(font);
        
        
        
        conteneurLabel.add(texteDefaite,BorderLayout.CENTER);
        
        fenetre.add(conteneurLabel,BorderLayout.CENTER);
        
        fenetre.setVisible(true);
    }
    
}
