/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Grille;
import ileinterdite.Message;
import ileinterdite.NiveauEau;
import ileinterdite.OTresor;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Evenement;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import ileinterdite.Tresor;
import ileinterdite.TypesMessage;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Yoann
 */
public class VueGrille implements Observe {

    private JFrame fenetre;
    private JFrame frame;

    private JPanel conteneurMilieu;
    private JPanel conteneurGauche;
    private JPanel conteneurDroite;
    private JPanel conteneurNivEau;
    private JPanel niveauEau;
    private JPanel zoneAction;
    private JPanel conteneurBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;
    private JPanel infoJoueurActif;
    private JPanel zoneValidation;
    private JPanel conteneurTuile;

    private CardLayout c1;
    private JPanel carteJ1;
    private JPanel carteJ2;
    private JPanel carteJ3;
    private JPanel carteJ4;

    private int ci;
    private int cj;
    private int tourJoueur = 0;

    private JButton[] Tuile;
    private InfoBouton[] infoBouton;

    private Message msg;

    //Bouton des joueurs
    private JButton[] btnJ;
    private int nbJoueurs;

    private JButton info;
    private JButton deplace;
    private JButton assecher;
    private JButton donner;
    private JButton btnUtiliserCarte;
    private JButton capacite;
    private JButton recupTresor;
    private JButton finTour;

    private JButton tresorEau;
    private JButton tresorFeu;
    private JButton tresorAir;
    private JButton tresorTerre;

    private JButton valid;
    private JButton annul;

    private JLabel labelJoueurCourant;
    private JLabel labelNomJoueurCourant;
    private JLabel labelPointsAction;
    private File chemin = new File("");

    public VueGrille(Grille grille, NiveauEau niveauEau, ArrayList<Pion> pions) /*throws IOException*/ {
        frame = new JFrame();
        frame.setTitle("Ile Interdite");
        frame.setSize(1400, 800);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nbJoueurs = pions.size();
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
        conteneurTuile = new JPanel();
        conteneurTuile.setLayout(new GridLayout(6, 6));
        conteneurTuile.setPreferredSize(new Dimension(1300, 1300));
        Tuile = new JButton[36];
        infoBouton = new InfoBouton[36];
        ci = 0;
        cj = 0;
        for (int i = 0; i < 36; i++) { // Boucle afin d'ajouter tout les boutons de la grille 

            Tuile[i] = new JButton();

            Tuile tuileSelect = grille.getTuile(ci, cj);
            conteneurTuile.add(Tuile[i]);

            if (i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i == 11 || i == 24 || i == 29 || i == 30 || i == 31 || i == 34 || i == 35) {

                Tuile[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 0));
                Tuile[i].setEnabled(false);

            } else {
                Tuile[i].setText(tuileSelect.getNom());

                if (tuileSelect.isActif()) {
                    Tuile[i].setEnabled(true);
                } else {
                    Tuile[i].setEnabled(false);
                }

                Tuile[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            }
            infoBouton[i] = new InfoBouton(tuileSelect);
            Tuile[i].add(infoBouton[i]);
            cj++;
            if (cj == 6) {
                ci++;
                cj = 0;
            };
        }
        frame.add(conteneurTuile);

        // fenetre.add(map);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        conteneurBas = new JPanel(new BorderLayout());

        //Creation temporaire des trophées
        zoneTresors = new JPanel();

        zoneTresors.setLayout(new GridLayout(2, 2));

        tresorEau = new JButton("Eau");
        tresorEau.setBackground(Color.lightGray);
        tresorEau.setEnabled(false);
        zoneTresors.add(tresorEau);

        tresorFeu = new JButton("Feu");
        tresorFeu.setBackground(Color.lightGray);
        tresorFeu.setEnabled(false);
        zoneTresors.add(tresorFeu);

        tresorAir = new JButton("Air");
        tresorAir.setBackground(Color.lightGray);
        tresorAir.setEnabled(false);
        zoneTresors.add(tresorAir);

        tresorTerre = new JButton("Terre");
        tresorTerre.setBackground(Color.lightGray);
        tresorTerre.setEnabled(false);
        zoneTresors.add(tresorTerre);

        conteneurBas.add(zoneTresors, BorderLayout.WEST);

        //Création temporaire zone carte
        c1 = new CardLayout();
        zoneCartes = new JPanel(c1);

        carteJ1 = new JPanel();
        carteJ1 = Carte(pions.get(0));
        carteJ2 = new JPanel();
        carteJ2 = Carte(pions.get(1));
        carteJ3 = new JPanel();
        carteJ3 = Carte(pions.get(2));
        carteJ4 = new JPanel();
        carteJ4 = Carte(pions.get(3));

        zoneCartes.add(carteJ1, "0");
        zoneCartes.add(carteJ2, "1");
        zoneCartes.add(carteJ3, "2");
        zoneCartes.add(carteJ4, "3");

        c1.show(zoneCartes, "0");
        conteneurBas.add(zoneCartes, BorderLayout.CENTER);

        zoneValidation = new JPanel(new GridLayout(2, 1));

        valid = new JButton("Validation");
        annul = new JButton("Annuler");

        zoneValidation.add(valid);
        zoneValidation.add(annul);

        conteneurBas.add(zoneValidation, BorderLayout.EAST);

        conteneurBas.setPreferredSize(new Dimension(1300, 100));
        frame.add(conteneurBas, BorderLayout.SOUTH);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

        //////////////////////////////////////////////////////////////////////////////////// Fenetre joueurs choisis
        conteneurDroite = new JPanel(new BorderLayout());

        zoneJoueurs = new JPanel(new GridLayout(5, 1));

        infoJoueurActif = new JPanel(new GridLayout(3, 1));

        labelJoueurCourant = new JLabel("Joueur courant :");
        labelNomJoueurCourant = new JLabel(pions.get(tourJoueur).getNomj() + "[" + pions.get(tourJoueur).getRole().getNomA() + "]");
        labelPointsAction = new JLabel("Points d'Actions : " + pions.get(tourJoueur).getNbAction());

        infoJoueurActif.add(labelJoueurCourant);
        infoJoueurActif.add(labelNomJoueurCourant);
        infoJoueurActif.add(labelPointsAction);
        zoneJoueurs.add(infoJoueurActif);

        btnJ = new JButton[pions.size()];
        for (int i = 0; i < nbJoueurs; i++) {
            int show = i;
            JButton btnj = new JButton(pions.get(i).getNomj(), pions.get(i).getRole().getImgAventurier());
            btnj.setForeground(Color.WHITE);
            btnj.setVerticalTextPosition(JLabel.CENTER);
            btnj.setHorizontalTextPosition(JLabel.CENTER);
            if (i == 0) {
                btnj.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
            } else {
                btnj.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
            }

            btnj.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {

                }

                @Override
                public void mousePressed(MouseEvent arg0) {
                    c1.show(zoneCartes, show + "");
                }

                @Override
                public void mouseReleased(MouseEvent arg0) {
                    c1.show(zoneCartes, tourJoueur + "");
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                }
            });
            btnJ[i] = btnj;
            zoneJoueurs.add(btnj);
        }
        System.out.println(nbJoueurs);
        for (int i = nbJoueurs; i < 4; i++) {
            JButton btnj = new JButton("[Vide]");
            btnj.setEnabled(false);
            zoneJoueurs.add(btnj);
        }

        conteneurDroite.add(zoneJoueurs, BorderLayout.CENTER);

        zoneAction = new JPanel(new GridLayout(4, 2));

        
        
        deplace = new JButton("Se deplacer");
        deplace.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message m = new Message(TypesMessage.DEPLACEMENT, pions.get(tourJoueur));
                notifierObservateur(m);

            }
        }
        );

        assecher = new JButton("Assecher");
        assecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message m = new Message(TypesMessage.ASSECHER, pions.get(tourJoueur));
                notifierObservateur(m);

            }
        }
        );

        donner = new JButton("Donner carte");
        donner.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message m = new Message(TypesMessage.VUE_DONNER_CARTE);
                notifierObservateur(m);
                activationBoutons(false);
            }
        });

        btnUtiliserCarte = new JButton("Utiliser Carte");

        capacite = new JButton("Capacité");

        recupTresor = new JButton("Récuperer Tresor");
        recupTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sommeCleEau = 0;
                int sommeCleFeu = 0;
                int sommeCleTerre = 0;
                int sommeCleAir = 0;

                Message m = new Message(TypesMessage.RECUPERER_TROPHEE);

                for (CarteTresor ct : pions.get(tourJoueur).getCartesTresors()) {
                    if (ct.getType() == CTresor.CLE_EAU) {
                        sommeCleEau++;
                    } else if (ct.getType() == CTresor.CLE_FEU) {
                        sommeCleFeu++;
                    } else if (ct.getType() == CTresor.CLE_TERRE) {
                        sommeCleTerre++;
                    } else if (ct.getType() == CTresor.CLE_AIR) {
                        sommeCleAir++;
                    }

                }
                //On vérifie la somme des clés et la position du joueur
                if (sommeCleAir >= 4 && pions.get(tourJoueur).getTuilePosition().getEvent() == Evenement.AIR) {
                    m.setObjetTresor(new OTresor(Tresor.AIR, true));
                    notifierObservateur(m);
                } else if (sommeCleEau >= 4 && pions.get(tourJoueur).getTuilePosition().getEvent() == Evenement.EAU) {
                    m.setObjetTresor(new OTresor(Tresor.EAU, true));
                    notifierObservateur(m);
                } else if (sommeCleTerre >= 4 && pions.get(tourJoueur).getTuilePosition().getEvent() == Evenement.TERRE) {
                    m.setObjetTresor(new OTresor(Tresor.TERRE, true));
                    notifierObservateur(m);
                } else if (sommeCleFeu >= 4 && pions.get(tourJoueur).getTuilePosition().getEvent() == Evenement.FEU) {
                    m.setObjetTresor(new OTresor(Tresor.FEU, true));
                    notifierObservateur(m);
                }
            }
        });

        info = new JButton("Information");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueInformation vueInfo = new VueInformation(pions);
            }
        });

        finTour = new JButton("Fin Tour");

        finTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message m = new Message(TypesMessage.FIN_TOUR, pions.get(tourJoueur));
                notifierObservateur(m);

            }
        });

        zoneAction.add(deplace);
        zoneAction.add(assecher);
        zoneAction.add(donner);
        zoneAction.add(btnUtiliserCarte);
        zoneAction.add(capacite);
        zoneAction.add(recupTresor);
        zoneAction.add(info);
        zoneAction.add(finTour);
        conteneurDroite.setPreferredSize(new Dimension(200, 700));
        conteneurDroite.add(zoneAction, BorderLayout.NORTH);

        frame.add(conteneurDroite, BorderLayout.EAST);

        frame.setVisible(true);
    }

    public void joueurSuivant() {
        //pour decaler le joueur selectionner visible.
        btnJ[tourJoueur].setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        tourJoueur++;
        tourJoueur %= nbJoueurs;
        c1.show(zoneCartes, "" + tourJoueur);
        btnJ[tourJoueur].setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
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

    public void actualiserCartes(ArrayList<Pion> pions) {

        zoneCartes.remove(carteJ1);
        zoneCartes.remove(carteJ2);
        zoneCartes.remove(carteJ3);
        zoneCartes.remove(carteJ4);

        carteJ1 = Carte(pions.get(0));
        carteJ2 = Carte(pions.get(1));
        carteJ3 = Carte(pions.get(2));
        carteJ4 = Carte(pions.get(3));

        zoneCartes.add(carteJ1, "0");
        zoneCartes.add(carteJ2, "1");
        zoneCartes.add(carteJ3, "2");
        zoneCartes.add(carteJ4, "3");
        c1.show(zoneCartes, tourJoueur + "");

    }

    public void setCliquable(Grille grille, Color couleur) {

        /*for (Button b : this.conteneurTuile.){
           
       }*/
        ci = 0;
        cj = 0;

        for (int i = 0; i < 36; i++) { // Boucle afin d'ajouter tout les boutons de la grille 

            Tuile tuileSelect = grille.getTuile(ci, cj);

            if (tuileSelect.isActif()) {

                Tuile[i].setEnabled(true);
                Tuile[i].setBorder(BorderFactory.createLineBorder(couleur, 2));
                Tuile[i].addActionListener(
                        new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        msg.setTuile(tuileSelect);
                        notifierObservateur(msg);

                    }
                });
            } else {
                Tuile[i].setEnabled(false);
            }
            cj++;
            if (cj == 6) {
                ci++;
                cj = 0;
            };
        }

    }

    public void repaintInfoBouton() {

        for (int i = 0; i < 36; i++) {

            Tuile[i].setEnabled(true);
            infoBouton[i].repaint();
            Tuile[i].setEnabled(false);
        }

    }

    public void setNonCliquable(Grille grille) {

        /*for (Button b : this.conteneurTuile.){
           
       }*/
        ci = 0;
        cj = 0;
        for (int i = 0; i < 36; i++) { // Boucle afin d'ajouter tout les boutons de la grille 

            Tuile tuileSelect = grille.getTuile(ci, cj);
            grille.getTuile(ci, cj).setActif(false);
            if (tuileSelect.getEtat() == Etat.NULL) {
                Tuile[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 0));
            } else {
                Tuile[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }

            Tuile[i].setEnabled(false);

            cj++;
            if (cj == 6) {
                ci++;
                cj = 0;
            };

        }
        repaintInfoBouton();
    }

    public void actualiserGrille(Grille grille) {
        repaintInfoBouton();
    }

    /*public void actualiserNiveauEau(NiveauEau niveauEau){
        
        niveauEau.setNiveau();
    }*/
    
    public void actualiserInfoJA(Pion pionActif){
        labelNomJoueurCourant.setText(pionActif.getNomj() + "[" + pionActif.getRole().getNomA()+ "]" );
        labelPointsAction.setText("Points d'Actions : " + pionActif.getNbAction());
    }
    
    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void activationBoutons(boolean b) {
        donner.setEnabled(b);
        capacite.setEnabled(b);
        deplace.setEnabled(b);
        assecher.setEnabled(b);
        finTour.setEnabled(b);
        recupTresor.setEnabled(b);

        valid.setEnabled(b);
        annul.setEnabled(b);
    }

    public void activerTresor(OTresor objetTresor) {
        if (objetTresor.getType() == Tresor.FEU) {
            tresorFeu.setBackground(Color.RED);
            tresorFeu.setEnabled(true);
            tresorFeu.setForeground(Color.BLACK);
        } else if (objetTresor.getType() == Tresor.AIR) {
            tresorAir.setForeground(Color.BLACK);
            tresorAir.setEnabled(true);
            tresorAir.setBackground(Color.CYAN);
        } else if (objetTresor.getType() == Tresor.TERRE) {
            tresorTerre.setForeground(Color.BLACK);
            tresorTerre.setEnabled(true);
            tresorTerre.setBackground(Color.ORANGE);
        } else if (objetTresor.getType() == Tresor.EAU) {
            tresorEau.setBackground(Color.BLUE);
            tresorEau.setEnabled(true);
            tresorEau.setForeground(Color.BLACK);
        }
    }

    public void activationDeplacement(boolean b) {
        deplace.setEnabled(b);
    }

    public void activationAssechement(boolean b) {
        assecher.setEnabled(b);
    }

    public void activationDon(boolean b) {
        donner.setEnabled(b);
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
