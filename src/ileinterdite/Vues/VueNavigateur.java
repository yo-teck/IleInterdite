/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Vues.Custom.SelectionCarteUnique;
import ileinterdite.Vues.Custom.SelectionPionUnique;
import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import ileinterdite.Vues.Custom.FondMonde;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class VueNavigateur implements Observe {

    private Observateur observateur;
    private JFrame fenetre;
    private FondMonde conteneur;
    private JPanel conteneurPionActif;
    private JPanel conteneurDon;
    private JPanel conteneurValidation;

    private SelectionCarteUnique cartesA;
    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private JLabel infoAction;
    private JLabel texteCapa;

    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    int index;
    private SelectionPionUnique[] pionSelection;

    public VueNavigateur(Pion pionActif, ArrayList<Pion> pions) {

        ////////////////////////////////////////////////////////////////////////
        //Création de la police d'écriture
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 30);

        ////////////////////////////////////////////////////////////////////////
        //Paramétrage de la fenetre en fonction du nombre de joueur
        ////////////////////////////////////////////////////////////////////////
        fenetre = new JFrame(pionActif.getNomj() + " - Selectionner joueur");
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setSize(800, 100 + pions.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        //Creation du JPanel du haut
        //Ajout d'un texte informatif
        texteCapa = new JLabel("");
        texteCapa.setFont(police);
        texteCapa.setText("Selectionner un joueur pour utiliser votre capacité");
        conteneur.add(texteCapa, BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        //Paramétrage du conteneur central pour selection du joueur
        ////////////////////////////////////////////////////////////////////////
        conteneurDon = new JPanel();
        conteneurDon.setOpaque(false);
        conteneurDon.setPreferredSize(new Dimension(800, pions.size() * 100 + 200));

        //Creation de la zone cauche en fonction du nb de joueur
        JPanel conteneurDonGau = new JPanel(new GridLayout(pions.size() - 1, 1));
        conteneurDonGau.setPreferredSize(new Dimension(100, pions.size() * 100 - 100));
        conteneurDonGau.setOpaque(false);

        //Creation d'une variable contenant des pions
        pionSelection = new SelectionPionUnique[pions.size() - 1];
        //creation des mouseListeneur
        creeMouseListener(pions);

        index = 0;

        //creation des pion et ajout au panel si le pion est different de celui actif
        for (int i = 0; i < pions.size(); i++) {

            if (!pions.get(i).equals(pionActif)) {
                pionSelection[index] = new SelectionPionUnique(pions.get(i), false);
                pionSelection[index].setActif(false);
                pionSelection[index].addMouseListener(ms[index]);
                conteneurDonGau.add(pionSelection[index]);
                index++;
            }
        }

        //Initialisation d'une variable active au debut
        pionSelection[0].setActif(true);

        //ajout de panel au conteneur central
        conteneurDon.add(conteneurDonGau, BorderLayout.WEST);

        //Creation de la zone droite en fonction du nb de joueur
        JPanel conteneurDonDroi = new JPanel(new GridLayout(pions.size() - 1, 1));
        conteneurDonDroi.setPreferredSize(new Dimension(650, pions.size() * 100 - 100));

        //creation des noms des pions et ajout au panel afin de faire correpondre avec le panel droit 
        for (int i = 0; i < pions.size(); i++) {
            if (!pions.get(i).equals(pionActif)) {
                JLabel nomJ = new JLabel(pions.get(i).getNomj() + " [" + pions.get(i).getRole().getNomA() + "]");
                nomJ.setFont(police);
                conteneurDonDroi.add(nomJ);
            }
        }

        conteneurDonDroi.setOpaque(false);
        //ajout au conteneur central
        conteneurDon.add(conteneurDonDroi, BorderLayout.EAST);

        //Creation d'un label pour savoir le pion selection
        infoAction = new JLabel("");
        infoAction.setFont(police);
        //Mise a jour de ce label
        setInfoAction(pions);

        //ajout en bas du conteneur central
        conteneurDon.add(infoAction, BorderLayout.SOUTH);

        //ajout du conteneur central au centre du conteneur principal
        conteneur.add(conteneurDon, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////
        //Creation du conteneur en bas
        ////////////////////////////////////////////////////////////////////////
        conteneurValidation = new JPanel(new GridLayout(1, 5));
        conteneurValidation.setPreferredSize(new Dimension(800, 20));
        conteneurValidation.setOpaque(false);
        //creation du bouton valider
        btnValider = new JButton("Valider");
        //creation ActionListener permettant de valider l'action
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.DEPLACEMENT_AMI);
                // il recupere dans les pions celui qui est actif 
                for (int i = 0; i < pions.size() - 1; i++) {
                    if (pionSelection[i].isActif()) {
                        msg.setPion(pionSelection[i].getPion());
                    }
                }
                notifierObservateur(msg);

                fenetre.setVisible(false);
            }
        });
        //creation du bouton valider
        btnAnnuler = new JButton("Annuler");
        //creation ActionListener permettant de annuler l'action
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ANNULER);
                fenetre.setVisible(false);
                notifierObservateur(msg);
            }
        });

        conteneurValidation.add(new JLabel(""));
        conteneurValidation.add(btnValider);
        conteneurValidation.add(new JLabel(""));
        conteneurValidation.add(btnAnnuler);
        conteneurValidation.add(new JLabel(""));
        //ajout en bas du conteneur central
        conteneur.add(conteneurValidation, BorderLayout.SOUTH);

        fenetre.add(conteneur);

        fenetre.setVisible(true);
    }

    //Permet de mettre à jour le texte
    public void setInfoAction(ArrayList<Pion> pions) {

        for (int i = 0; i < pions.size() - 1; i++) {
            if (pionSelection[i].isActif()) {
                infoAction.setText("Vous allez utiliser la capacité sur " + pionSelection[i].getPion().getNomj() + "[" + pionSelection[i].getPion().getRole().getNomA() + "]");
            }
        }

    }
    
    //Creation des mouseListener permettant d'activer un pion et rendre inactif les autres;
    public void creeMouseListener(ArrayList<Pion> pions) {
        //creation un a un des MouseListener car avec un for cela ne marcher pas S
        ms = new MouseListener[3];
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //rendre le pion selection actif
                pionSelection[0].setActif(true);
                setInfoAction(pions);
                
                //rendre les pions non selection inactif
                for (int j = 0; j < pions.size() - 1; j++) {
                    if (j != 0) {
                        pionSelection[j].setActif(false);

                    }
                }
                //modifi le text
                setInfoAction(pions);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener m1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pionSelection[1].setActif(true);
                for (int j = 0; j < pions.size() - 1; j++) {
                    if (j != 1) {
                        pionSelection[j].setActif(false);
                    }
                }
                setInfoAction(pions);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pionSelection[2].setActif(true);

                for (int j = 0; j < pions.size() - 1; j++) {
                    if (j != 2) {
                        pionSelection[j].setActif(false);

                    }
                }
                setInfoAction(pions);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ms[0] = m0;
        ms[1] = m1;
        ms[2] = m2;
    }

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
