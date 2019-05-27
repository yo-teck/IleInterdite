/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class VueJeu implements Observe {

    private JFrame fenetre;
    private JPanel contenuMilieu;
    private JPanel contenuGauche;
    private JPanel contenuDroite;
    private JPanel map;
    private JPanel niveauEau;
    private JPanel zoneBoutons;
    private JPanel contenuBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;

    public VueJeu(Grille g, NiveauEau lvlEau, ArrayList<Pion> pions) {
        fenetre = new JFrame("Ile Interdite");
        this.configureWindow(fenetre);

        contenuMilieu = new JPanel();
        contenuMilieu.setLayout(new BorderLayout());
        
        contenuGauche = new JPanel();
        contenuGauche.setLayout(new BorderLayout());
        
        contenuDroite = new JPanel();
        contenuDroite.setLayout(new BorderLayout());

        map = new JPanel();
        map.setLayout(new GridLayout(6, 6));
        contenuMilieu.add(map, BorderLayout.CENTER);

        niveauEau = new JPanel();
        niveauEau.setLayout(new GridLayout(10, 1));
        contenuGauche.add(niveauEau, BorderLayout.CENTER);

        zoneBoutons = new JPanel();
        zoneBoutons.setLayout(new GridLayout(12, 3));
        contenuDroite.add(zoneBoutons, BorderLayout.CENTER);

        contenuBas = new JPanel();
        contenuBas.setLayout(new GridLayout(1, 3));
        contenuMilieu.add(contenuBas, BorderLayout.SOUTH);

        zoneTresors = new JPanel();
        zoneTresors.setLayout(new GridLayout(2, 2));
        contenuBas.add(zoneTresors);

        zoneCartes = new JPanel();
        zoneCartes.setLayout(new GridLayout(5, 3));
        contenuBas.add(zoneCartes);

        zoneJoueurs = new JPanel();
        zoneJoueurs.setLayout(new GridLayout(4, 2));
        contenuBas.add(zoneJoueurs);

        JLabel nbActions = new JLabel("Nombre d'actions restantes : ");

        JButton dplaC = new JButton("Se déplacer");
        JButton asseche = new JButton("Assécher");
        JButton finT = new JButton("Fin du tour");
        JButton utilCAJ = new JButton("Utiliser Carte D'un autre joueur");
        JButton recupT = new JButton("Récupérer Trésor");

        /* // CARTE = BOUTONS 
        JButton utilC = new JButton("Utiliser Carte");
         */
        // A CREER 6 FOIS
        JButton donCarte = new JButton("Donner Carte");
        //
        
        for(int i = 0; i < 6; i++){
            for(int j = 0 ; i < 6; j++){
                map.add(new JButton(g.getTuile(i, j).getNom()));
            }
        }

    }



    private void configureWindow(JFrame window) {
        window.setSize(500, 200);
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowListener() {
            public void windowOpened(java.awt.event.WindowEvent e) {
            }

            public void windowClosed(java.awt.event.WindowEvent e) {
            }

            public void windowIconified(java.awt.event.WindowEvent e) {
            }

            public void windowDeiconified(java.awt.event.WindowEvent e) {
            }

            public void windowActivated(java.awt.event.WindowEvent e) {
            }

            public void windowDeactivated(java.awt.event.WindowEvent e) {
            }

            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
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
