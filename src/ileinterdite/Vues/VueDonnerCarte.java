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

    private SelectionCarteUnique cartesA;
    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private JLabel txtlololo;
    private JLabel textNomJ;

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
        police = new Font("Pieces of Eight", Font.PLAIN, 35);

        //Parametrage de la fenetre
        fenetre = new JFrame(pionActif.getNomj() + " - Donner une carte");
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setSize(800, 300 + pions.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        //Creation du JPanel du haut
        conteneurPionActif = new JPanel(new GridLayout(2, 1));
        conteneurPionActif.setOpaque(false);
        conteneurPionActif.setPreferredSize(new Dimension(800, 200));

        txtlololo = new JLabel("Choisissez la carte à donner :");
        txtlololo.setFont(police);
        conteneurPionActif.add(txtlololo);

        //creation d'une Selection de carte
        cartesA = new SelectionCarteUnique(pionActif, false);
        cartesA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changerTextCarte();
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
        });

        changerTextCarte();
        conteneurPionActif.add(cartesA);

        conteneur.add(conteneurPionActif, BorderLayout.NORTH);

        pionSelection = new SelectionPionUnique[pions.size() - 1];

        conteneurDon = new JPanel();
        conteneurDon.setOpaque(false);
        conteneurDon.setPreferredSize(new Dimension(800, pions.size() * 100));

        JPanel conteneurDonGau = new JPanel(new GridLayout(pions.size(), 1));
        conteneurDonGau.setPreferredSize(new Dimension(100, pions.size() * 100));
        conteneurDonGau.setOpaque(false);
        JLabel texteD = new JLabel("");
        texteD.setFont(police);
        texteD.setText("Donner");
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
        textNomJ = new JLabel("");
        textNomJ.setFont(police);
        changerTextNomJ(pions);

        conteneurDonDroi.add(textNomJ);
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

        fenetre.setVisible(true);
    }

    public void changerTextCarte() {
        txtlololo.setText("Choisissez la carte à donner : (" + cartesA.getCarte().getType() + ")");
    }

    public void changerTextNomJ(ArrayList<Pion> pions) {

        for (int i = 0; i < pions.size() - 1; i++) {
            if (pionSelection[i].isActif()) {
                textNomJ.setText("à " + pionSelection[i].getPion().getNomj() + "[" + pionSelection[i].getPion().getRole().getNomA() + "]");
            }
        }

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
                changerTextNomJ(pions);
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
                    if (!pionSelection[j].equals(pionSelection[1])) {
                        pionSelection[j].setActif(false);
                    }
                }
                changerTextNomJ(pions);
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
                changerTextNomJ(pions);
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
