/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Grille;
import ileinterdite.Message;
import ileinterdite.OTresor;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import ileinterdite.Vues.Custom.Fond;
import ileinterdite.Vues.Custom.FondMonde;
import ileinterdite.Vues.Custom.InfoCarte;
import ileinterdite.Vues.Custom.InfoTresor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author richomml
 */
public class VueInformation implements Observe {

    private JFrame fenetre;

    private JPanel conteneurGlobal;
    private FondMonde infoPartie;
    private JPanel infoTroph;
    private JPanel infoTuile;

    private JPanel conteneurDescriptions;
    private JPanel conteneurNoms;
    private JButton passerInfo;
    private JButton passerInfoIHM;
    private JButton passerRegle;

    private JButton infoIHMSuivant;
    private JButton infoIHMPrecedent;

    private JButton regleSuivant;
    private JButton reglePrecedent;

    private JPanel reglePanel;
    private JPanel infoIHMPanel;

    private Fond[] infoIHMCard;
    private Fond[] regleCard;

    private CardLayout swtch;

    private CardLayout regle;
    private CardLayout infoIHM;

    private JLabel texte;
    private Font police;

    private int positionRegle;
    private int positionInfoIHM;

    public VueInformation(ArrayList<Pion> pions, ArrayList<OTresor> tresors, Grille grille) {
        ////////////////////////////////////////////////////////////////////////
        //Creation de la police
        File chemin = new File("");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Police non trouvée");
        } catch (IOException ex) {
            System.out.println("Police non trouvée");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 24);

        //Parametrage de la fenetre
        fenetre = new JFrame("Informations");
        fenetre.setSize(650, 800);
        configureWindow(fenetre);

        //Creation cardLayout regles
        regle = new CardLayout();
        reglePanel = new JPanel(regle);
        positionRegle = 0;

        regleCard = new Fond[8];

        for (int i = 0; i < 8; i++) {
            Fond regleFond = new Fond("/src/ressources/regles/regle-" + (i + 1) + ".png");
            regleFond.setLayout(new BorderLayout());
            regleFond.setPreferredSize(new Dimension(600, 800));

            JPanel conteneurV = new JPanel(new GridLayout(1, 5));
            conteneurV.setOpaque(false);
            regleSuivant = new JButton("Suivant");
            regleSuivant.setFont(police);
            regleSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    positionRegle++;
                    positionRegle %= 8;
                    regle.show(reglePanel, "" + positionRegle);
                }
            });
            reglePrecedent = new JButton("Precedent");
            reglePrecedent.setFont(police);
            reglePrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    positionRegle--;
                    positionRegle %= 8;
                    regle.show(reglePanel, "" + positionRegle);
                }
            });
            conteneurV.setPreferredSize(new Dimension(600, 20));
            conteneurV.add(new JLabel(""));
            conteneurV.add(reglePrecedent);
            conteneurV.add(new JLabel(""));
            conteneurV.add(regleSuivant);
            conteneurV.add(new JLabel(""));

            regleFond.add(conteneurV, BorderLayout.SOUTH);

            regleCard[i] = regleFond;
            reglePanel.add(regleFond, i + "");
        }

        regle.show(reglePanel, "0");

        //Creation carte infoIHM 
        infoIHM = new CardLayout();
        infoIHMPanel = new JPanel(infoIHM);
        positionInfoIHM = 0;

        infoIHMCard = new Fond[6];

        for (int i = 0; i < 6; i++) {
            Fond infoIHMFond = new Fond("/src/ressources/info/info" + (i + 1) + ".png");
            infoIHMFond.setLayout(new BorderLayout());
            infoIHMFond.setPreferredSize(new Dimension(600, 800));

            JPanel conteneurV = new JPanel(new GridLayout(1, 5));
            conteneurV.setOpaque(false);
            infoIHMSuivant = new JButton("Suivant");
            infoIHMSuivant.setFont(police);
            infoIHMSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    positionInfoIHM++;
                    positionInfoIHM %= 6;
                    infoIHM.show(infoIHMPanel, "" + positionInfoIHM);
                }
            });
            infoIHMPrecedent = new JButton("Precedent");
            infoIHMPrecedent.setFont(police);
            infoIHMPrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    positionInfoIHM--;
                    positionInfoIHM %= 6;
                    infoIHM.show(infoIHMPanel, "" + positionInfoIHM);
                }
            });
            conteneurV.setPreferredSize(new Dimension(600, 20));
            conteneurV.add(new JLabel(""));
            conteneurV.add(infoIHMPrecedent);
            conteneurV.add(new JLabel(""));
            conteneurV.add(infoIHMSuivant);
            conteneurV.add(new JLabel(""));

            infoIHMFond.add(conteneurV, BorderLayout.SOUTH);

            infoIHMCard[i] = infoIHMFond;
            infoIHMPanel.add(infoIHMFond, i + "");
        }
        infoIHM.show(infoIHMPanel, "0");

        /////////////////////////////////////////////////////////////////////////
        //Creation info
        infoPartie = new FondMonde();
        infoPartie.setLayout(new BorderLayout());
        infoPartie.setPreferredSize(new Dimension(600, 800));
        //info trophee 
        infoTroph = new JPanel(new GridLayout(1, 5));
        infoTroph.setPreferredSize(new Dimension(600, 50));
        infoTroph.setOpaque(false);

        JLabel txt1 = new JLabel("Trophées :");
        txt1.setFont(police);
        infoTroph.add(txt1);

        for (int i = 0; i < 4; i++) {
            infoTroph.add(new InfoTresor(tresors.get(i)));
        }

        infoPartie.add(infoTroph, BorderLayout.NORTH);

        //Creation du panel central avec les infos du jeu
        conteneurNoms = new JPanel(new GridLayout(12, 1));
        conteneurNoms.setOpaque(false);
        conteneurNoms.setPreferredSize(new Dimension(600, 600));
        for (int i = 0; i < pions.size(); i++) {
            JLabel infoJ =new JLabel( pions.get(i).getNomj() + " (" + pions.get(i).getRole().getNomA() + ")");
            infoJ.setFont(police);
            conteneurNoms.add(infoJ);
            JLabel descriJ=new JLabel(pions.get(i).getRole().getDescription());
            descriJ.setFont(police);
            conteneurNoms.add(descriJ);    
            conteneurNoms.add(new InfoCarte(pions.get(i), false, false));
        }
        for (int i = pions.size(); i < 4; i++) {
            conteneurNoms.add(new JLabel(""));
            conteneurNoms.add(new JLabel(""));
            conteneurNoms.add(new JLabel(""));
        }
        infoPartie.add(conteneurNoms, BorderLayout.CENTER);

        //Creation du panel du bas avec les infoTuiles
        infoTuile = new JPanel(new GridLayout(3, 1));
        infoTuile.setPreferredSize(new Dimension(600, 100));
        infoTuile.setOpaque(false);
        ArrayList<Tuile> tuiles = grille.getTuiles();
        int nbSec = 0;
        int nbIno = 0;
        int nbSub = 0;

        for (Tuile tuile : tuiles) {
            if (tuile.getEtat().equals(Etat.SEC)) {
                nbSec++;
            } else if (tuile.getEtat().equals(Etat.INONDE)) {
                nbIno++;
            } else if (tuile.getEtat().equals(Etat.SUBMERGE)) {
                nbSub++;
            }
        }
        JLabel txt2 = new JLabel("Nombre de truile séche : " + nbSec);
        txt2.setFont(police);
        infoTuile.add(txt2);

        JLabel txt3 = new JLabel("Nombre de truile inondé : " + nbIno);
        txt3.setFont(police);
        infoTuile.add(txt3);

        JLabel txt4 = new JLabel("Nombre de truile submergé : " + nbSub);
        txt4.setFont(police);
        infoTuile.add(txt4);

        infoPartie.add(infoTuile, BorderLayout.SOUTH);

        ////////////////////////////////////////////////////////////////////////
        //Creation cardLayout swtch
        swtch = new CardLayout();
        conteneurGlobal = new JPanel(swtch);

        conteneurGlobal.add(infoPartie,
                0 + "");
        conteneurGlobal.add(infoIHMPanel,
                1 + "");
        conteneurGlobal.add(reglePanel,
                2 + "");

        swtch.show(conteneurGlobal,
                0 + "");

        JPanel conteneurBouton = new JPanel(new GridLayout(1, 5));

        conteneurBouton.setPreferredSize(
                new Dimension(600, 20));

        passerInfo = new JButton("Info");

        passerInfo.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                swtch.show(conteneurGlobal, 0 + "");
            }
        }
        );
        passerInfoIHM = new JButton("InfoIHM");

        passerInfoIHM.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                swtch.show(conteneurGlobal, 1 + "");
            }
        }
        );
        passerRegle = new JButton("InfoRegle");

        passerRegle.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                swtch.show(conteneurGlobal, 2 + "");
            }
        }
        );

        conteneurBouton.add(passerInfo);

        conteneurBouton.add(
                new JLabel(""));
        conteneurBouton.add(passerInfoIHM);

        conteneurBouton.add(
                new JLabel(""));
        conteneurBouton.add(passerRegle);

        fenetre.add(conteneurGlobal, BorderLayout.CENTER);

        fenetre.add(conteneurBouton, BorderLayout.SOUTH);

        fenetre.setVisible(
                true);
    }

    private void configureWindow(JFrame window) {
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
                Message m = new Message(TypesMessage.FERMER_INFO);
                notifierObservateur(m);
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
