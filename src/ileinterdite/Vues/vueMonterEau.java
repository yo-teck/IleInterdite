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
import ileinterdite.Vues.Custom.Fond;
import ileinterdite.Vues.Custom.FondMonde;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Yoann
 */
public class vueMonterEau implements Observe {

    private JFrame fenetre;

    private JPanel conteneurLabel;
    private JPanel conteneurBoutons;
    private Fond conteneur;

    private JLabel texteDefaite;
    private JLabel votreTemps;
    private JLabel conditions;

    private JButton btnOk, quitter;

    private Font police;
    private Font police2;

    private File chemin = new File("");

    public vueMonterEau() {
        //Configuration de la fenêtre
        fenetre = new JFrame("Monter des Eaux");
        fenetre.setSize(128, 225);
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Initialisation du fond
        conteneur = new Fond("/src/ressources/imgCarte/MonterEau.png");
        conteneur.setLayout(new BorderLayout());

        conteneurBoutons = new JPanel();
        conteneurBoutons.setPreferredSize(new Dimension(128, 25));
        conteneurBoutons.setOpaque(false);

        btnOk = new JButton("OK");
        btnOk.setFont(police2);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fenetre.dispose();
            }
        });

////////////////////////////////////////////////////////////////////////////////
///////////////////////// Initialisation de la police //////////////////////////
////////////////////////////////////////////////////////////////////////////////
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }

        police = new Font("Pieces of Eight", Font.PLAIN, 80);
        police2 = new Font("Pieces of Eight", Font.PLAIN, 24);

////////////////////////////////////////////////////////////////////////////////
//////////////// Ajout des composants dans le conteneur & la fenêtre ///////////
////////////////////////////////////////////////////////////////////////////////      
        conteneurBoutons.add(btnOk);

        conteneur.add(conteneurBoutons, BorderLayout.SOUTH);

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
