/**
 *
 * @author richomml
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Vues.Custom.SelectionCarteUniqueUtilisable;
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
public class VueUtiliserCarte implements Observe {

    private JFrame fenetre;

    private FondMonde conteneur;
    private JPanel conteneurUtil;
    private JPanel conteneurBoutonsConfirmation;

    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private JLabel text;
    private JLabel infoAction;

    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    private SelectionCarteUniqueUtilisable[] carteSelection;
    private Message msg;

    private ArrayList<Pion> pionsCartesUtilise;

    public VueUtiliserCarte(ArrayList<Pion> pions) {

        ///////////////////////////////////////////////////////////////////////
        //Creation de la variable qui stock les joueurs valable pour l'action
        pionsCartesUtilise = new ArrayList<>();
        for (Pion pion : pions) {
            for (CarteTresor carte : pion.getCartesTresors()) {
                if ((carte.getType().equals(CTresor.HELICO) || carte.getType().equals(CTresor.SAC_SABLE)) && !pionsCartesUtilise.contains(pion)) {
                    pionsCartesUtilise.add(pion);

                }
            }
        }

        ///////////////////////////////////////////////////////////////////////
        //Initialisation de la police
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

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
        fenetre = new JFrame(" - Utiliser une carte");
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setSize(800, 300 + pionsCartesUtilise.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        ////////////////////////////////////////////////////////////////////////
        //Creation du JPanel central en fonction du nombre des joueurs valables
        ////////////////////////////////////////////////////////////////////////
        conteneurUtil = new JPanel(new GridLayout(pionsCartesUtilise.size() + 2, 1));
        conteneurUtil.setOpaque(false);
        conteneurUtil.setPreferredSize(new Dimension(800, pionsCartesUtilise.size() * 80));

        //Ajout d'un text informatif
        text = new JLabel("Selectionne un carte : ");
        text.setFont(police);
        conteneurUtil.add(text);

        //Creation d'une variable contenant des composants customs
        carteSelection = new SelectionCarteUniqueUtilisable[pionsCartesUtilise.size()];
        creeMouseListener(pionsCartesUtilise);

        //Creation des composants customs pour selection une carte et ajout au panel central
        for (int i = 0; i < pionsCartesUtilise.size(); i++) {
            carteSelection[i] = new SelectionCarteUniqueUtilisable(pionsCartesUtilise.get(i), false);
            carteSelection[i].setActif(false);
            carteSelection[i].addMouseListener(ms[i]);
            conteneurUtil.add(carteSelection[i]);

        }

        //Ajout d'un text qui informe de la carte selectionnée
        infoAction = new JLabel("");
        infoAction.setFont(police);
        conteneurUtil.add(infoAction);

        //Ajout du conteneur central au centre
        conteneur.add(conteneurUtil, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////
        //Creation du JPanel du bas
        ////////////////////////////////////////////////////////////////////////
        conteneurBoutonsConfirmation = new JPanel(new GridLayout(1, 5));
        conteneurBoutonsConfirmation.setOpaque(false);

        //creation du bouton valider
        btnValider = new JButton("Valider");
        btnValider.setEnabled(false);
        //creation ActionListener permettant de valider l'action
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.UTILISER_CARTE);
                //On recupere la carte selectionner en fonction de la variable active
                for (int i = 0; i < carteSelection.length; i++) {
                    if (carteSelection[i].isActif()) {
                        m.setPion(carteSelection[i].getPion());
                        m.setCarteTresor(carteSelection[i].getCarte());
                    }
                }
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        //creation du bouton valider
        btnAnnuler = new JButton("Annuler");
         //creation ActionListener permettant d'annulé
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnValider);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnAnnuler);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneur.add(conteneurBoutonsConfirmation, BorderLayout.SOUTH);

        fenetre.add(conteneur);

        fenetre.setVisible(true);
    }
    //Creation des mouseListener permettant d'activer un pion et rendre inactif les autres
    public void creeMouseListener(ArrayList<Pion> pions) {
        
        ms = new MouseListener[4];
        //creation un a un des MouseListener car avec une boucle cela ne marcher pas 
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //rendre la carte selectionner active
                carteSelection[0].setActif(true);
                //rendre le bouton valider actif
                btnValider.setEnabled(true);
                //mise à jour du text informatif sur l'action
                infoAction.setFont(police.deriveFont(24f));
                infoAction.setText("Utiliser la carte " + carteSelection[0].getCarte().getType() + " de " + carteSelection[0].getPion().getNomj() + " [" + carteSelection[0].getPion().getRole().getNomA() + "]");
                //rendre les autres cartes selectionners inactif
                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 0) {
                        carteSelection[j].setActif(false);
                    }
                }
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
                carteSelection[1].setActif(true);
                btnValider.setEnabled(true);
                infoAction.setFont(police.deriveFont(24f));
                infoAction.setText("Utiliser la carte " + carteSelection[1].getCarte().getType() + "de " + carteSelection[1].getPion().getNomj() + " [" + carteSelection[1].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 1) {
                        carteSelection[j].setActif(false);
                    }
                }
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
                carteSelection[2].setActif(true);
                btnValider.setEnabled(true);
                infoAction.setFont(police.deriveFont(24f));
                infoAction.setText("Utiliser la carte " + carteSelection[2].getCarte().getType() + "de " + carteSelection[2].getPion().getNomj() + " [" + carteSelection[2].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 2) {
                        carteSelection[j].setActif(false);

                    }
                }
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
        MouseListener m3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteSelection[3].setActif(true);
                btnValider.setEnabled(true);
                infoAction.setFont(police.deriveFont(24f));
                infoAction.setText("Utiliser la carte " + carteSelection[3].getCarte().getType() + "de " + carteSelection[3].getPion().getNomj() + " [" + carteSelection[3].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {

                    if (j != 3) {
                        carteSelection[j].setActif(false);

                    }
                }
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
        ms[3] = m3;
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
