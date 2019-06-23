/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Vues.Custom.SelectionCarteUnique;
import ileinterdite.Vues.Custom.InfoCarte;
import ileinterdite.Vues.Custom.pionUnique;
import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author richomml
 */
public class VueDonnerCarte implements Observe {

    private JFrame fenetre;

    private FondMonde conteneur;
    private JPanel conteneurPionActif;
    private JPanel conteneurDon;
    private JPanel conteneurValidation;

    private SelectionCarteUnique cartesA;
    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private JLabel text;
    private JLabel textInfoAction;

    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    int index;
    private pionUnique[] pionSelection;
    private ArrayList<Pion> pionsPossible;

    public VueDonnerCarte(Pion pionActif, ArrayList<Pion> pions) {

        ///////////////////////////////////////////////////////////////////////
        //Creation de la variable qui stock les joueurs valable pour l'action
        pionsPossible = new ArrayList<>();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (pionActif.getRole().getNomA().equals("Messager")) {
            pionsPossible = pions;
        } else {
            for (Pion pion : pions) {
                if (pion.getTuilePosition().equals(pionActif.getTuilePosition())) {
                    pionsPossible.add(pion);
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////
        //Initialisation de la police
        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 35);

        ////////////////////////////////////////////////////////////////////////
        //Paramétrage de la fenetre en fonction du nombre des joueurs valables
        ////////////////////////////////////////////////////////////////////////
        fenetre = new JFrame(pionActif.getNomj() + " - Donner une carte");
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setSize(800, 300 + pionsPossible.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        ////////////////////////////////////////////////////////////////////////
        //Creation du JPanel haut
        ////////////////////////////////////////////////////////////////////////
        conteneurPionActif = new JPanel(new GridLayout(2, 1));
        conteneurPionActif.setOpaque(false);
        conteneurPionActif.setPreferredSize(new Dimension(800, 200));

        //Ajout d'un text informatif
        text = new JLabel("Choisissez la carte à donner :");
        text.setFont(police);
        conteneurPionActif.add(text);

        //creation d'une Selection de carte unique
        cartesA = new SelectionCarteUnique(pionActif, false);
        changerText();
        conteneurPionActif.add(cartesA);

        conteneur.add(conteneurPionActif, BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        //Creation du JPanel central en fonction du nombre des joueurs valables
        ////////////////////////////////////////////////////////////////////////
        conteneurDon = new JPanel();
        conteneurDon.setOpaque(false);
        conteneurDon.setPreferredSize(new Dimension(800, pionsPossible.size() * 100));

        //Creation d'une partie gauche
        JPanel conteneurDonGau = new JPanel(new GridLayout(pionsPossible.size(), 1));
        conteneurDonGau.setPreferredSize(new Dimension(100, pionsPossible.size() * 100));
        conteneurDonGau.setOpaque(false);

        //Creation d'un texte informatif
        JLabel texteD = new JLabel("");
        texteD.setFont(police);
        texteD.setText("Donner");
        conteneurDonGau.add(texteD);

        //Intialisation des mouseListeners
        creeMouseListener(pionsPossible);

        //Creation d'une variable contenant des composants customs
        pionSelection = new pionUnique[pionsPossible.size() - 1];

        //Creation des composants customs pour selection un joueur et ajout à la section gauche
        index = 0;
        for (int i = 0; i < pionsPossible.size(); i++) {

            if (!pionsPossible.get(i).equals(pionActif)) {
                pionSelection[index] = new pionUnique(pionsPossible.get(i), false);
                pionSelection[index].setActif(false);
                pionSelection[index].addMouseListener(ms[index]);
                conteneurDonGau.add(pionSelection[index]);
                index++;
            }
        }

        //Initialisation du premier pion selection 
        pionSelection[0].setActif(true);
        conteneurDon.add(conteneurDonGau, BorderLayout.WEST);

        //creation d'une partie a droite
        JPanel conteneurDonDroi = new JPanel(new GridLayout(pionsPossible.size(), 1));
        conteneurDonDroi.setPreferredSize(new Dimension(650, pionsPossible.size() * 100));

        //Ajout d'un texte informatif sur l'action à effectuer
        textInfoAction = new JLabel("");
        textInfoAction.setFont(police);
        changerTextInfoAction(pionsPossible);

        //Creation des composants customs montrant les cartes et ajout à la section droite
        conteneurDonDroi.add(textInfoAction);
        for (int i = 0; i < pionsPossible.size(); i++) {
            if (!pionsPossible.get(i).equals(pionActif)) {
                conteneurDonDroi.add(new InfoCarte(pionsPossible.get(i), false, false));
            }
        }
        conteneurDonDroi.setOpaque(false);

        //Ajout du composant au centre
        conteneurDon.add(conteneurDonDroi, BorderLayout.CENTER);
        conteneur.add(conteneurDon, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////
        //Creation du JPanel du bas
        ////////////////////////////////////////////////////////////////////////
        conteneurValidation = new JPanel(new GridLayout(1, 5));
        conteneurValidation.setPreferredSize(new Dimension(800, 20));
        conteneurValidation.setOpaque(false);

        //Création du bouton valider
        btnValider = new JButton("Valider");
        //creation ActionListener permettant de valider l'action
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.DONNER_CARTE);
                //recuperation du joueur selectionné
                for (int i = 0; i < pionsPossible.size() - 1; i++) {
                    if (pionSelection[i].isActif()) {
                        msg.setPion(pionSelection[i].getPion());
                    }
                }
                //recuperation de la carte selectionner
                msg.setCarteTresor(cartesA.getCarte());

                fenetre.setVisible(false);
                notifierObservateur(msg);
            }
        });

        //Création du bouton valider
        btnAnnuler = new JButton("Annuler");
        //creation ActionListener permettant de valider l'action
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        conteneurValidation.add(new JLabel(""));
        conteneurValidation.add(btnValider);
        conteneurValidation.add(new JLabel(""));
        conteneurValidation.add(btnAnnuler);
        conteneurValidation.add(new JLabel(""));

        conteneur.add(conteneurValidation, BorderLayout.SOUTH);

        fenetre.add(conteneur);

        fenetre.setVisible(true);
    }

    //Permet d'ajouter la carte selection au texte informatif
    public void changerText() {
        text.setText("Choisissez la carte à donner : (" + cartesA.getCarte().getType() + ")");
    }

    //Permet d'ajouter le pion selectionner au textInfoAction
    public void changerTextInfoAction(ArrayList<Pion> pions) {

        for (int i = 0; i < pions.size() - 1; i++) {
            if (pionSelection[i].isActif()) {
                textInfoAction.setText("à " + pionSelection[i].getPion().getNomj() + "[" + pionSelection[i].getPion().getRole().getNomA() + "]");
            }
        }

    }

    //Creation des mouseListener permettant d'activer un pion et rendre inactif les autres
    public void creeMouseListener(ArrayList<Pion> pions) {
        ms = new MouseListener[3];
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //rendre le joueur selectionner actif
                pionSelection[0].setActif(true);
                //rendre les autres joueurs selectionner inactif
                for (int j = 0; j < pions.size() - 1; j++) {
                    if (j != 0) {                        pionSelection[j].setActif(false);

                    }
                }
                //permet de modifier le  textInfoAction
                changerTextInfoAction(pions);
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
                changerTextInfoAction(pions);
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
                changerTextInfoAction(pions);
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
