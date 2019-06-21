/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.TypesMessage;
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
public class VueFin implements Observe {
    
    private JFrame fenetre;
    
    private JPanel conteneurLabel;
    private JPanel conteneurBoutons;
    
    private JLabel texteDefaite;
    private JLabel votreTemps;
    private JLabel conditions;
    
    private JButton recommencer,quitter;
    
    private Font police;
    private Font police2;
    
    private File chemin = new File("");
    
    public VueFin(String etatPartie,String temps,String conditionsFin){
        fenetre = new JFrame("Fin de la partie ");
        fenetre.setSize(1350, 800);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        
        conteneurLabel = new JPanel(new GridLayout(3,1));
        conteneurBoutons = new JPanel(new GridLayout(1,5));
        conditions = new JLabel(conditionsFin,SwingConstants.CENTER);
        texteDefaite = new JLabel(etatPartie,SwingConstants.CENTER);
        votreTemps = new JLabel(temps,SwingConstants.CENTER);
        
        recommencer = new JButton("Recommencer");
        recommencer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Message m = new Message(TypesMessage.RECOMMENCER);
                fenetre.setVisible(false);
                notifierObservateur(m);
            }
        });
        quitter = new JButton("Quitter");
        quitter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
             System.exit(0);
            }
        });
        
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
        police2 = new Font("Pieces of Eight", Font.PLAIN,30);
        conditions.setFont(police2);
        texteDefaite.setFont(police);
        votreTemps.setFont(police);
        
        conteneurLabel.add(texteDefaite);
        conteneurLabel.add(conditions);
        conteneurLabel.add(votreTemps);
        
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(recommencer);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(quitter);
        conteneurBoutons.add(new JLabel(""));
        
        
        fenetre.add(conteneurLabel,BorderLayout.CENTER);
        fenetre.add(conteneurBoutons,BorderLayout.SOUTH);
        
        fenetre.setVisible(true);
    }
    
    
    
    
    
    private Observateur observateur;

    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }

    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
    
}
