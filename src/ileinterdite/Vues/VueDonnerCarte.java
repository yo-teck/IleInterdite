/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import ileinterdite.Vues.Fond.FondMonde;
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

    private SelectionCarteUniqueBool cartesA;
    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    int index;
    private SelectionPionUnique[] pionSelection;

    public VueDonnerCarte(Pion pionActif, ArrayList<Pion> pions) {
        /*
        ArrayList<Pion> pionsDon = new ArrayList<>();
        pionsDon.addAll(pions);*/

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
        police = new Font("Pieces of Eight", Font.PLAIN, 24);

        //Parametrage de la fenetre
        fenetre = new JFrame(pionActif.getNomj() + " - Donner une carte");
        fenetre.setLayout(new BorderLayout());

        fenetre.setSize(800, 300 + pions.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        //Creation du JPanel du haut
        conteneurPionActif = new JPanel(new GridLayout(2, 1));
        conteneurPionActif.setOpaque(false);
        conteneurPionActif.setPreferredSize(new Dimension(800, 200));

        JLabel texteD = new JLabel("Choisissez la carte à donner :");
        texteD.setFont(police);
        conteneurPionActif.add(texteD);

        //creation d'une Selection de carte
        cartesA = new SelectionCarteUniqueBool(pionActif, false);
        conteneurPionActif.add(cartesA);

        conteneur.add(conteneurPionActif, BorderLayout.NORTH);

        pionSelection = new SelectionPionUnique[pions.size() - 1];

        conteneurDon = new JPanel();
        conteneurDon.setOpaque(false);
        conteneurDon.setPreferredSize(new Dimension(800, pions.size() * 100));

        JPanel conteneurDonGau = new JPanel(new GridLayout(pions.size(), 1));
        conteneurDonGau.setPreferredSize(new Dimension(100, pions.size() * 100));
        conteneurDonGau.setOpaque(false);

        texteD.setText("Donner à :");
        conteneurDonGau.add(texteD);
        creeMouseListener(pions);

        index = 0;
        for (int i = 0; i < pions.size(); i++) {

            if (!pions.get(i).equals(pionActif)) {
                System.out.println("ind : " + index);
                pionSelection[index] = new SelectionPionUnique(pions.get(i), false);
                pionSelection[index].setActif(false);
                pionSelection[index].addMouseListener(ms[index]);
                conteneurDonGau.add(pionSelection[index]);
                index++;
            }
        }
        for (int i = 0; i < pions.size() - 1; i++) {

        }
        pionSelection[0].setActif(true);
        conteneurDon.add(conteneurDonGau, BorderLayout.WEST);

        JPanel conteneurDonDroi = new JPanel(new GridLayout(pions.size(), 1));
        conteneurDonDroi.setPreferredSize(new Dimension(650, pions.size() * 100));
        conteneurDonDroi.add(new JLabel(""));
        for (int i = 0; i < pions.size(); i++) {
            if (!pions.get(i).equals(pionActif)) {
                conteneurDonDroi.add(new InfoCarte(pions.get(i), false, false));
            }
        }
        conteneurDonDroi.setOpaque(false);
        conteneurDon.add(conteneurDonDroi, BorderLayout.CENTER);
        conteneur.add(conteneurDon, BorderLayout.CENTER);

        conteneurValidation = new JPanel(new GridLayout(1, 5));
        conteneurValidation.setPreferredSize(new Dimension(800, 20));
        conteneurValidation.setOpaque(false);
        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.DONNER_CARTE);

                for (int i = 0; i < pions.size() - 1; i++) {
                    if (pionSelection[i].isActif()) {
                        msg.setPion(pionSelection[i].getPion());
                    }
                }
                msg.setCarteTresor(cartesA.getCarte());

                fenetre.setVisible(false);
                notifierObservateur(msg);
            }
        });
        btnAnnuler = new JButton("Annuler");
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
        /*
        //On enlève le joueur actif de l'ArrayList des pions car il ne peut pas se donner de carte à lui-même
        int n = 0;
        for (Pion pion : pions) {
            if (pion == pionActif) {
                numPion = n;
            }
            n++;
        }
        if (!pionActif.getRole().getNomA().equals("Messager")) {
            pionsDon = pionActif.getTuilePosition().getPions();
        }
        pionsDon.remove(pionActif);

        //Création de la zone de choix du joueur
        conteneurJoueurs = new JPanel(new GridLayout(pionsDon.size() + 1, 1));

        labelJoueurs = new JLabel("Choisissez un joueur :");
        conteneurJoueurs.add(labelJoueurs);

        boutonsJoueurs = new JRadioButton[pionsDon.size()];
        groupeJoueurs = new ButtonGroup();
        int i = 0;
        for (Pion pion : pionsDon) {
            JRadioButton nouveauBouton = new JRadioButton(pion.getNomj());
            groupeJoueurs.add(nouveauBouton);
            boutonsJoueurs[i] = nouveauBouton;
            conteneurJoueurs.add(boutonsJoueurs[i]);
            i++;
        }
        boutonsJoueurs[0].setSelected(true);

        fenetre.add(conteneurJoueurs, BorderLayout.WEST);

        //Création de la zone de choix de la carte à envoyer
        conteneurCartes = new JPanel(new GridLayout(pionActif.getNbCartes() + 1, 1));

        labelCartes = new JLabel("Choisissez une carte :");
        conteneurCartes.add(labelCartes);

        boutonsCartes = new JRadioButton[pionActif.getNbCartes()];
        groupeCartes = new ButtonGroup();
        i = 0;
        for (CarteTresor ct : pionActif.getCartesTresors()) {
            JRadioButton nouveauBouton = new JRadioButton(ct.getType().toString());
            groupeCartes.add(nouveauBouton);
            boutonsCartes[i] = nouveauBouton;
            conteneurCartes.add(boutonsCartes[i]);
            i++;
        }
        boutonsCartes[0].setSelected(true);

        fenetre.add(conteneurCartes, BorderLayout.EAST);

        conteneurBoutons = new JPanel(new GridLayout(1, 5));
        //Ajout bouton valider
        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.DONNER_CARTE);
                int i = 0;
                while (!boutonsCartes[i].isSelected()) {
                    i++;
                }
                m.setCarteTresor(pionActif.getCartesTresors().get(i));

                i = 0;
                while (!boutonsJoueurs[i].isSelected()) {
                    i++;
                }
                //On vérifie la position du pion dans l'ArrayList et on le met dans le message en conséquence
                if (numPion == 0) {
                    m.setPion(pions.get(i + 1));
                } else if (numPion == 1) {
                    if (i < 1) {
                        m.setPion(pions.get(i));
                    } else {
                        m.setPion(pions.get(i + 1));
                    }
                } else if (numPion == 2) {
                    if (i < 2) {
                        m.setPion(pions.get(i));
                    } else {
                        m.setPion(pions.get(i + 1));
                    }
                } else if (numPion == 3) {
                    if (i < 3) {
                        m.setPion(pions.get(i));
                    } else {
                        m.setPion(pions.get(i + 1));
                    }
                }

                fenetre.setVisible(false);
                notifierObservateur(m);
            }
        });
        btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        pionsDon.add(pionActif);
        
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(btnValider);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(btnAnnuler);
        conteneurBoutons.add(new JLabel(""));
        
        fenetre.add(conteneurBoutons, BorderLayout.SOUTH);
         */
        fenetre.setVisible(true);
    }

    public void creeMouseListener(ArrayList<Pion> pions) {
        ms = new MouseListener[3];
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ms" + 0);
                pionSelection[0].setActif(true);

                System.out.println("pas bug 1");

                for (int j = 0; j < pions.size() - 1; j++) {
                    System.out.println("pas bug 2");
                    if (!pionSelection[j].equals(pionSelection[0])) {
                        pionSelection[j].setActif(false);

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
                System.out.println("ms" + 1);
                pionSelection[1].setActif(true);

                System.out.println("pas bug 1");

                for (int j = 0; j < pions.size() - 1; j++) {
                    System.out.println("pas bug 2");
                    if (!pionSelection[j].equals(pionSelection[1])) {
                        pionSelection[j].setActif(false);

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
                System.out.println("ms" + 2);
                pionSelection[2].setActif(true);

                System.out.println("pas bug 1");

                for (int j = 0; j < pions.size() - 1; j++) {
                    System.out.println("pas bug 2");
                    if (!pionSelection[j].equals(pionSelection[2])) {
                        pionSelection[j].setActif(false);

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
