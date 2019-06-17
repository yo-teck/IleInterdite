/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Grille;
import ileinterdite.Message;
import ileinterdite.NiveauEau;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
public class VueGrille implements Observe {

    private JFrame fenetre;
    private JPanel conteneurMilieu;
    private JPanel conteneurGauche;
    private JPanel conteneurDroite;

    private JPanel niveauEau;
    private JPanel zoneAction;
    private JPanel conteneurBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;
    private JPanel zoneValidation;
    private int ci;
    private int cj;
    private int tourJoueur = 0;

    public VueGrille(Grille grille, NiveauEau niveauEau, ArrayList<Pion> pions) {
        JFrame frame = new JFrame();
        frame.setTitle("Ile Interdite");
        frame.setSize(1400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setLayout(new BorderLayout());
        JPanel conteneurTuile = new JPanel();
        JPanel joueurs = new JPanel();
        JButton[] B_joueurs = new JButton[4];
        

        /////////////////////////////////////////////////////////////////////// Fenetre de demarrage
        ///////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////// Coté niveau eau
        JPanel conteneurNivEau = new JPanel();

        conteneurNivEau.setLayout(new GridLayout(11, 1));
        JButton[] n_Eau = new JButton[10];

        JLabel txtO = new JLabel("Niveau d'eau : " + niveauEau.getNiveau());
        conteneurNivEau.add(txtO);
        for (int i = 0; i < 10; i++) { //////////////////////Initialisation de la barre d'eau

            n_Eau[i] = new JButton(" ");
            n_Eau[i].setEnabled(false);

            if (i >= 10 - niveauEau.getNiveau()) {
                n_Eau[i].setBackground(Color.blue);
            } else {
                n_Eau[i].setBackground(Color.white);
            }

            conteneurNivEau.add(n_Eau[i]);

        }

        frame.add(conteneurNivEau, BorderLayout.WEST);

        ////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////// Coté carte
        conteneurTuile.setLayout(new GridLayout(6, 6));
        conteneurTuile.setPreferredSize(new Dimension(1300, 700));
        JButton[] Tuile = new JButton[36];
        ci = 0;
        cj = 0;
        for (int i = 0; i < 36; i++) { // Boucle afin d'ajouter tout les boutons de la grille 
            Tuile[i] = new JButton();
            Tuile tuileSelect = grille.getTuile(ci, cj);
            Tuile[i].setText(tuileSelect.getNom());
            conteneurTuile.add(Tuile[i]);
            if (i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i == 11 || i == 24 || i == 29 || i == 30 || i == 31 || i == 34 || i == 35) {
                Tuile[i].setEnabled(false); // Cases null non cliquable
                Tuile[i].setText(""); // Nom eau sur les cases nulls
                Tuile[i].setBackground(Color.WHITE); //Couleur fond
                Tuile[i].setForeground(Color.WHITE); // Couleur front
            } else {
                Tuile[i].setEnabled(false);
                Tuile[i].addActionListener( 
                        new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Deplacement standard :");
                        for (Tuile t : grille.getNonSubmerge(grille.getTuilesCroix(tuileSelect))) {
                            System.out.print(t.getNom() + ", ");
                        }

                        System.out.println("");

                        System.out.println("Deplacement explorateur :");
                        for (Tuile t : grille.getNonSubmerge(grille.getTuilesCroix(tuileSelect))) {
                            System.out.print(t.getNom() + ", ");
                        }
                        for (Tuile t : grille.getNonSubmerge(grille.getTuilesDiagonale(tuileSelect))) {
                            System.out.print(t.getNom() + ", ");
                        }

                        System.out.println("");

                        System.out.println("Celles plongeur :");
                        for (Tuile t : grille.getTuilesDispoPourDeplacement(grille, tuileSelect)) {
                            System.out.print(t.getNom() + ", ");
                        }
                        System.out.println("");
                        System.out.println("");
                        
                        
                    }
                }
                );

                if (tuileSelect.getEtat() == Etat.INONDE) {
                    Tuile[i].setBackground(new Color(119, 181, 254));
                } else if (tuileSelect.getEtat() == Etat.SUBMERGE) {
                    Tuile[i].setBackground(new Color(34, 66, 124));
                } else {
                    Tuile[i].setBackground(new Color(145, 93, 15));
                }

            }
            cj++;
            if (cj == 6) {
                ci++;
                cj = 0;
            };
        }

        // fenetre.add(map);
        conteneurTuile.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        conteneurBas = new JPanel(new BorderLayout());

        //Creation temporaire des trophées
        zoneTresors = new JPanel();

        zoneTresors.setLayout(new GridLayout(2, 2));

        JButton tresorEau = new JButton("Eau");
        tresorEau.setBackground(Color.lightGray);
        tresorEau.setEnabled(false);
        zoneTresors.add(tresorEau);

        JButton tresorFeu = new JButton("Feu");
        tresorFeu.setBackground(Color.lightGray);
        tresorFeu.setEnabled(false);
        zoneTresors.add(tresorFeu);

        JButton tresorAir = new JButton("Air");
        tresorAir.setBackground(Color.lightGray);
        tresorAir.setEnabled(false);
        zoneTresors.add(tresorAir);

        JButton tresorTerre = new JButton("Terre");
        tresorTerre.setBackground(Color.lightGray);
        tresorTerre.setEnabled(false);
        zoneTresors.add(tresorTerre);

        conteneurBas.add(zoneTresors, BorderLayout.WEST);

        //Création temporaire zone carte
        CardLayout c1 = new CardLayout();
        zoneCartes = new JPanel(c1);

        JPanel carteJ1 = Carte(pions.get(0));
        JPanel carteJ2 = Carte(pions.get(1));
        JPanel carteJ3 = Carte(pions.get(2));
        JPanel carteJ4 = Carte(pions.get(3));

        zoneCartes.add(carteJ1, "0");
        zoneCartes.add(carteJ2, "1");
        zoneCartes.add(carteJ3, "2");
        zoneCartes.add(carteJ4, "3");

        c1.show(zoneCartes, "0");
        conteneurBas.add(zoneCartes, BorderLayout.CENTER);

        zoneValidation = new JPanel(new GridLayout(2, 1));

        JButton valid = new JButton("Validation");
        JButton annul = new JButton("Annuler");

        zoneValidation.add(valid);
        zoneValidation.add(annul);

        conteneurBas.add(zoneValidation, BorderLayout.EAST);

        conteneurBas.setPreferredSize(new Dimension(1300, 100));
        frame.add(conteneurBas, BorderLayout.SOUTH);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

        //////////////////////////////////////////////////////////////////////////////////// Fenetre joueurs choisis
        conteneurDroite = new JPanel(new BorderLayout());

        zoneJoueurs = new JPanel(new GridLayout(5, 1));
        JButton[] btnJ = new JButton[4];

        JButton btnJ1 = new JButton(pions.get(0).getNomj());
        JButton btnJ2 = new JButton(pions.get(1).getNomj());
        JButton btnJ3 = new JButton(pions.get(2).getNomj());
        JButton btnJ4 = new JButton(pions.get(3).getNomj());

        btnJ2.setEnabled(false);
        btnJ3.setEnabled(false);
        btnJ4.setEnabled(false);

        btnJ[0] = btnJ1;
        btnJ[1] = btnJ2;
        btnJ[2] = btnJ3;
        btnJ[3] = btnJ4;

        btnJ1.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(zoneCartes, "0");
            }
        }
        );
        btnJ2.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(zoneCartes, "1");
            }
        }
        );
        btnJ3.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(zoneCartes, "2");
            }
        }
        );
        btnJ4.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(zoneCartes, "3");
            }
        }
        );

        zoneJoueurs.add(btnJ1);
        zoneJoueurs.add(btnJ2);
        zoneJoueurs.add(btnJ3);
        zoneJoueurs.add(btnJ4);

        JButton info = new JButton("Information");
        zoneJoueurs.add(info);

        conteneurDroite.add(zoneJoueurs, BorderLayout.CENTER);

        zoneAction = new JPanel(new GridLayout(3, 2));

        JButton deplace = new JButton("Se deplacer");
        JButton assecher = new JButton("Assecher");
        JButton donner = new JButton("Donner carte");
        JButton capacite = new JButton("Capacité");
        JButton recupTresor = new JButton("Récuperer Tresor");
        JButton finTour = new JButton("Fin Tour");

        finTour.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message m = new Message(TypesMessage.FIN_TOUR, pions.get(tourJoueur));
                notifierObservateur(m);
                
                //pour decaler le joueur selectionner visible.
                btnJ[tourJoueur].setEnabled(false);
                tourJoueur++;
                tourJoueur %= 4;
                c1.show(zoneCartes, "" + tourJoueur);
                btnJ[tourJoueur].setEnabled(true);

            }
        }
        );

        zoneAction.add(deplace);
        zoneAction.add(assecher);
        zoneAction.add(donner);
        zoneAction.add(capacite);
        zoneAction.add(recupTresor);
        zoneAction.add(finTour);
        conteneurDroite.setPreferredSize(new Dimension(200, 700));
        conteneurDroite.add(zoneAction, BorderLayout.NORTH);

        frame.add(conteneurDroite, BorderLayout.EAST);
        frame.add(conteneurTuile);
        frame.setVisible(true);
    }

    public JPanel Carte(Pion pion) {

        JPanel carteJoueur = new JPanel(new GridLayout(1, 5));

        JButton carteVue;

        for (CarteTresor carte : pion.getCartesTresors()) {
            carteVue = new JButton(carte.getType().toString());
            carteJoueur.add(carteVue);
        }
        for (int i = 0; i < 5 - pion.getCartesTresors().size(); i++) {
            JButton rien = new JButton("[vide]");
            rien.setEnabled(false);
            carteJoueur.add(rien);
        }
        return carteJoueur;
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
