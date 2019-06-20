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
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private InfoTresor[] infoTresors;
    private InfoTuile[] infoBouton;

    private FondPanneau fondPanneau;
    private InfoNiveauEau infoNiveau;

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

    private JButton valid;
    private JButton annul;

    private JLabel labelJoueurCourant;
    private JLabel labelNomJoueurCourant;
    private JLabel labelPointsAction;
    
    private Font police;
    private File chemin = new File("");

    private ActionListener al;

    public VueGrille(Grille grille, NiveauEau niveauEau, ArrayList<Pion> pions, ArrayList<OTresor> LesTresors) {

        //Creation d'une variable qui stock le nombre de joueur pour le reutilisé
        nbJoueurs = pions.size();
        
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT,new File(chemin.getAbsolutePath()+"/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");
          
        }
        police = new Font("Pieces of Eight", Font.PLAIN,24);
        
////////////////////////////////////////////////////////////////////////////////        
//Creation de la fenetre////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        frame = new JFrame();
        frame.setTitle("Ile Interdite");
        frame.setLayout(new BorderLayout());
        configureWindow(frame);

        nbJoueurs = pions.size();
////////////////////////////////////////////////////////////////////////////////        
//Fin creation fenetre//////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    

////////////////////////////////////////////////////////////////////////////////        
//Creation du conteneur gauche//////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        JPanel conteneurNivEau = new JPanel(new GridLayout(1, 1));
        conteneurNivEau.setPreferredSize(new Dimension(200, 750));
        conteneurNivEau.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        infoNiveau = new InfoNiveauEau(niveauEau);
        conteneurNivEau.add(infoNiveau);

        frame.add(conteneurNivEau, BorderLayout.WEST);
////////////////////////////////////////////////////////////////////////////////        
//Fin du conteneur gauche///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////         

////////////////////////////////////////////////////////////////////////////////        
//Creation du conteneur du centre///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        conteneurTuile = new JPanel();
        conteneurTuile.setPreferredSize(new Dimension(750, 750));
        conteneurTuile.setLayout(new GridLayout(6, 6));

        Tuile = new JButton[36];
        infoBouton = new InfoTuile[36];
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
                Tuile[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        msg.setTuile(tuileSelect);
                        notifierObservateur(msg);
                    }
                });
                if (tuileSelect.isActif()) {
                    Tuile[i].setEnabled(true);
                } else {
                    Tuile[i].setEnabled(false);
                }

                Tuile[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            }
            infoBouton[i] = new InfoTuile(tuileSelect);
            Tuile[i].add(infoBouton[i]);
            cj++;
            if (cj == 6) {
                ci++;
                cj = 0;
            };
        }
        frame.add(conteneurTuile);
////////////////////////////////////////////////////////////////////////////////        
//Fin du conteneur du centre////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////        

////////////////////////////////////////////////////////////////////////////////        
//Creation du conteneur du bas//////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        conteneurBas = new JPanel(new BorderLayout());
        conteneurBas.setPreferredSize(new Dimension(1100, 100));
        //Creation temporaire des trophées
        zoneTresors = new JPanel();

        zoneTresors.setLayout(new GridLayout(2, 2));
        zoneTresors.setPreferredSize(new Dimension(100, 100));

        infoTresors = new InfoTresor[4];

        for (int i = 0; i < 4; i++) {

            InfoTresor tresor = new InfoTresor(LesTresors.get(i));

            infoTresors[i] = tresor;

            zoneTresors.add(infoTresors[i]);
        }

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
        ajoutBoisBtn(valid);
        
        annul = new JButton("Annuler");
        ajoutBoisBtn(annul);
        
        
        
        
        zoneValidation.add(valid);
        zoneValidation.add(annul);

        conteneurBas.add(zoneValidation, BorderLayout.EAST);

        frame.add(conteneurBas, BorderLayout.SOUTH);
////////////////////////////////////////////////////////////////////////////////        
//Fin du conteneur du bas///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//Creation du conteneur droit//////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        conteneurDroite = new JPanel(new BorderLayout());
        conteneurDroite.setPreferredSize(new Dimension(250, 750));

////////////////////////////////////////////////////////////////////////////////
//Creation du panel pour faire les différentes actions (positionnée en haut
        zoneAction = new JPanel(new GridLayout(4, 2));
//Creation des boutons et de leurs actionListener
//Creation du bouton permettant de se deplacer
        deplace = new JButton("Se deplacer");
        ajoutBoisBtn(deplace);
        deplace.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.DEPLACEMENT, pions.get(tourJoueur));
                notifierObservateur(m);
            }
        }
        );
//Creation du bouton permettant d'assecher un case
        assecher = new JButton("Assecher");
        ajoutBoisBtn(assecher);
        assecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ASSECHER, pions.get(tourJoueur));
                notifierObservateur(m);
            }
        }
        );
//Creation du bouton permettant de donner une carte
        donner = new JButton("Donner carte");
        ajoutBoisBtn(donner);
        donner.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.VUE_DONNER_CARTE);
                notifierObservateur(m);
                activationBoutons(false);
            }
        });
//Creation du bouton permettant d'utilisé une carte
        btnUtiliserCarte = new JButton("Utiliser Carte");
        btnUtiliserCarte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Message m = new Message(TypesMessage.VUE_UTILISER_CARTE);
                notifierObservateur(m);
            }
        });
        ajoutBoisBtn(btnUtiliserCarte);
//Creation du bouton permettant d'utilisé la capacité spécial
        capacite = new JButton("Capacité");
        capacite.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Message m = new Message(TypesMessage.CAPACITE);
                notifierObservateur(m);
            }
        });
        ajoutBoisBtn(capacite);
//Creation du bouton permettant de recuperer un tresor
        recupTresor = new JButton("Tresor");
        ajoutBoisBtn(recupTresor);
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
//Creation du bouton permettant d'avoir les informations des joueurs
        info = new JButton("Information");
        ajoutBoisBtn(info);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.AFFICHER_INFO);
                notifierObservateur(m);
            }
        });
//Creation du bouton permettant la fin du tour
        finTour = new JButton("Fin Tour");
        ajoutBoisBtn(finTour);
        finTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.FIN_TOUR, pions.get(tourJoueur));
                notifierObservateur(m);
            }
        });
//Ajout au panel des differentes actions
        zoneAction.add(deplace);
        zoneAction.add(assecher);
        zoneAction.add(donner);
        zoneAction.add(btnUtiliserCarte);
        zoneAction.add(capacite);
        zoneAction.add(recupTresor);
        zoneAction.add(info);
        zoneAction.add(finTour);
//Ajout du panel en haut à la zone de droite 
        conteneurDroite.add(zoneAction, BorderLayout.NORTH);
////////////////////////////////////////////////////////////////////////////////  

////////////////////////////////////////////////////////////////////////////////        
//Creation du panel concernant les joueurs (positionner au centre)
        zoneJoueurs = new JPanel(new GridLayout(5, 1));
//Creation du panel des informations sur le tour (nb action, joueur actif, role)
        infoJoueurActif = new JPanel(new GridLayout(3, 1));
        
        fondPanneau = new FondPanneau();
        fondPanneau.setLayout(new GridLayout(3,1));
        FondPlanche fp = new FondPlanche();
        
        labelJoueurCourant = new JLabel("Joueur courant :");
        labelJoueurCourant.setFont(police);
        labelNomJoueurCourant = new JLabel("[" + pions.get(tourJoueur).getRole().getNomA() + "] " + pions.get(tourJoueur).getNomj());
        labelNomJoueurCourant.setFont(police);
        labelPointsAction = new JLabel("Points d'Actions : " + pions.get(tourJoueur).getNbAction());
        labelPointsAction.setFont(police);


        fondPanneau.add(labelJoueurCourant);
        fondPanneau.add(labelNomJoueurCourant);
        fondPanneau.add(labelPointsAction);
        zoneJoueurs.add(fondPanneau);
//Creation des boutons pour voire les cartes du joueur
        btnJ = new JButton[pions.size()];

        for (int i = 0; i < nbJoueurs; i++) {

            int show = i;
            JButton btnj = new JButton(pions.get(i).getNomj(), pions.get(i).getRole().getImgAventurier());
            btnj.setVerticalTextPosition(JLabel.CENTER);
            btnj.setHorizontalTextPosition(JLabel.CENTER);
            btnj.setForeground(Color.WHITE);
            btnj.setFont(police.deriveFont(35f));

            if (i == 0) {
                btnj.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
            } else {
                btnj.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
            }
            //MouseListener qui permet de voire les cartes d'un joueur uniquement quand on appuies sur sa case            
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
//Si le nb de joueur est inferieur à 4 on rajoute des boutons nul pour remplir le GridLayout
        for (int i = nbJoueurs; i < 4; i++) {
            JButton btnj = new JButton("[Vide]");
            btnj.setEnabled(false);
            zoneJoueurs.add(btnj);
        }
//Ajout du Layout au centre à la zone à droite
        conteneurDroite.add(zoneJoueurs, BorderLayout.CENTER);
////////////////////////////////////////////////////////////////////////////////        

////////////////////////////////////////////////////////////////////////////////
//Fin du conteneur droit////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
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

    public void actualiserInfoNiveauEau() {
        infoNiveau.repaint();
    }

    public void actualiserInfoTresor() {
        for (int i = 0; i < 4; i++) {
            infoTresors[i].repaint();
        }
    }

    public void repaintInfoTuile() {

        for (int i = 0; i < 36; i++) {

            Tuile[i].setEnabled(true);
            infoBouton[i].repaint();
            Tuile[i].setEnabled(false);
        }

    }

    public void setNonCliquable(Grille grille) {

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
        repaintInfoTuile();
    }

    public void actualiserGrille(Grille grille) {
        repaintInfoTuile();
    }

    public void actualiserInfoJA(Pion pionActif) {
        labelNomJoueurCourant.setText("[" + pionActif.getRole().getNomA() + "] " + pionActif.getNomj());
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
        btnUtiliserCarte.setEnabled(b);

        valid.setEnabled(b);
        annul.setEnabled(b);
    }

    public void ajoutBoisBtn(JButton bouton) {
        bouton.setIcon(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgDecor/boisBtnAct.png"));
        bouton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 0));
        bouton.setVerticalTextPosition(JLabel.CENTER);
        bouton.setHorizontalTextPosition(JLabel.CENTER);
        bouton.setFont(police);

    }
    public void activationFinTour(boolean b) {
        finTour.setEnabled(b);}
    
    public void activationDeplacement(boolean b) {
        deplace.setEnabled(b);
    }

    public void activationAssechement(boolean b) {
        assecher.setEnabled(b);
    }

    public void activationDon(boolean b) {
        donner.setEnabled(b);
    }

    public void activationRecupe(boolean b) {
        recupTresor.setEnabled(b);
    }

    public void activationCapacite(boolean b) {
        capacite.setEnabled(b);
    }

    public void activationUtilise(boolean b) {
        btnUtiliserCarte.setEnabled(b);
    }
    
    public void activationInfo(boolean b) {
        info.setEnabled(b);
    }
    


    private void configureWindow(JFrame window) {
        window.setSize(1300, 850);
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
                window.setVisible(false);
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
