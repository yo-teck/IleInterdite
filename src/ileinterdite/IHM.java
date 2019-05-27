/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class IHM implements Observateur {

    private JFrame fenetre;
    private JPanel contenu;
    private JPanel map;
    private JPanel niveauEau;
    private JPanel zoneBoutons;
    private JPanel contenuBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;
    
    public IHM(){
        fenetre = new JFrame("Ile Interdite");
        this.configureWindow(fenetre);
        
        contenu = new JPanel();
        contenu.setLayout(new BorderLayout());
        
        map = new JPanel();
        map.setLayout(new GridLayout(6,6));
        contenu.add(map, BorderLayout.CENTER);
        
        niveauEau = new JPanel();
        niveauEau.setLayout(new GridLayout(10,1));
        contenu.add(niveauEau, BorderLayout.WEST);
        
        zoneBoutons = new JPanel();
        zoneBoutons.setLayout(new GridLayout(12, 3));
        contenu.add(zoneBoutons, BorderLayout.EAST);
        
        contenuBas = new JPanel();
        contenuBas.setLayout(new GridLayout(1, 3));
        contenu.add(contenuBas, BorderLayout.SOUTH);
        
        zoneTresors = new JPanel();
        zoneTresors.setLayout(new GridLayout(2,2));
        contenuBas.add(zoneTresors);
        
        zoneCartes = new JPanel();
        zoneCartes.setLayout(new GridLayout(5,3));
        contenuBas.add(zoneCartes);
        
        zoneJoueurs = new JPanel();
        zoneJoueurs.setLayout(new GridLayout(6,2));
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
        
        
        
        
        
    }
    
    @Override
    public void traiterMessage(Message m) {
        
    }
    
        private void configureWindow(JFrame window) {
        window.setSize(500, 200);
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowListener() {
            public void windowOpened(java.awt.event.WindowEvent e) {}
            public void windowClosed(java.awt.event.WindowEvent e) {}
            public void windowIconified(java.awt.event.WindowEvent e) {}
            public void windowDeiconified(java.awt.event.WindowEvent e) {}
            public void windowActivated(java.awt.event.WindowEvent e) {}
            public void windowDeactivated(java.awt.event.WindowEvent e) {}
            public void windowClosing(java.awt.event.WindowEvent e) { 
                System.exit(0);
            }
        });
    }
}
