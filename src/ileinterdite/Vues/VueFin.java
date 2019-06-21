/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author richomml
 */
public class VueFin {
    
    private JFrame fenetre;
    
    private JPanel conteneurLabel;
    private JPanel conteneurBoutons;
    
    private JLabel texteDefaite;
    
    private JButton recommencer,quitter;
    
    private Font police;
    
    private File chemin = new File("");
    
    public VueFin(String etatPartie){
        fenetre = new JFrame("Fin de la partie ");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        
        conteneurLabel = new JPanel(new BorderLayout());
        conteneurBoutons = new JPanel(new GridLayout(1,5));
        
        texteDefaite = new JLabel(etatPartie,SwingConstants.CENTER);
        
        recommencer = new JButton("Recommencer");
        recommencer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
      
            }
        });
        quitter = new JButton("Quitter");
        
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT,new File(chemin.getAbsolutePath()+"/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");
          
        }
        police = new Font("Pieces of Eight", Font.PLAIN,80);
        
        texteDefaite.setFont(police);
        
        conteneurLabel.add(texteDefaite,BorderLayout.CENTER);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(recommencer);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(quitter);
        conteneurBoutons.add(new JLabel(""));
        
        
        fenetre.add(conteneurLabel,BorderLayout.CENTER);
        fenetre.add(conteneurBoutons);
        
        fenetre.setVisible(true);
    }
    
}
