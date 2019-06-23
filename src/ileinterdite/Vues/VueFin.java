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
import ileinterdite.Vues.Custom.FondMonde;
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
    private FondMonde conteneur;
        
    private JLabel texteDefaite;
    private JLabel votreTemps;
    private JLabel conditions;

    
    private JButton recommencer,quitter;
    
    private Font police;
    private Font police2;
    
    private File chemin = new File("");
    
    public VueFin(String etatPartie,String temps,String conditionsFin){
        //Configuration de la fenêtre
        fenetre = new JFrame("Fin de la partie ");
        fenetre.setSize(1350, 800);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Initialisation du fond
        conteneur = new FondMonde();
        conteneur.setLayout(new BorderLayout());
        
////////////////////////////////////////////////////////////////////////////////
///////////////////////// Initialisation des JPanels ///////////////////////////
////////////////////////////////////////////////////////////////////////////////        
        conteneurLabel = new JPanel(new GridLayout(3,1));
        conteneurLabel.setOpaque(false);
        conteneurBoutons = new JPanel(new GridLayout(1,5));
        conteneurBoutons.setOpaque(false);
        
////////////////////////////////////////////////////////////////////////////////
///////////////////////// Initialisation des JLabels ///////////////////////////
////////////////////////////////////////////////////////////////////////////////     

        conditions = new JLabel(conditionsFin,SwingConstants.CENTER);
        texteDefaite = new JLabel(etatPartie,SwingConstants.CENTER);
        votreTemps = new JLabel(temps,SwingConstants.CENTER);
        
////////////////////////////////////////////////////////////////////////////////
//////// Initialisation des JButtons & ajout des ActionListeners ///////////////
////////////////////////////////////////////////////////////////////////////////  

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
        
////////////////////////////////////////////////////////////////////////////////
///////////////////////// Initialisation de la police //////////////////////////
////////////////////////////////////////////////////////////////////////////////

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
        police2 = new Font("Pieces of Eight", Font.PLAIN,40);
        
        conditions.setFont(police2);
        texteDefaite.setFont(police);
        votreTemps.setFont(police);
        
////////////////////////////////////////////////////////////////////////////////
//////////////// Ajout des composants dans le conteneur & la fenêtre ///////////
////////////////////////////////////////////////////////////////////////////////      
        conteneurLabel.add(texteDefaite);
        conteneurLabel.add(conditions);
        conteneurLabel.add(votreTemps);
        
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(recommencer);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(quitter);
        conteneurBoutons.add(new JLabel(""));
        
        
        conteneur.add(conteneurLabel,BorderLayout.CENTER);
        conteneur.add(conteneurBoutons,BorderLayout.SOUTH);
        
        fenetre.add(conteneur);
        
        fenetre.setVisible(true);
    }
    
    
    
    
////////////////////////////////////////////////////////////////////////////////
/////// Creation des Methodes permettant de communiquer avec le controleur /////
////////////////////////////////////////////////////////////////////////////////    
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
