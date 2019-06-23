/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
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
public class VueNavigateur implements Observe {

    private Observateur observateur;
    /*
    private JFrame fenetre;

    private int numPion;

    private JPanel conteneurJoueurs;
    private JPanel conteneurBoutons;

    
    private JRadioButton[] boutonsJoueurs;
    private ButtonGroup groupeJoueurs;

    private JButton btnValider;
    private JButton btnAnnuler;
    
    private JLabel labelJoueurs;*/
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
    private JLabel texteCapa;

    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    int index;
    private SelectionPionUnique[] pionSelection;

    public VueNavigateur(Pion pionActif, ArrayList<Pion> pions) {

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

        //Parametrage de la fenetre
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
        conteneurDon = new JPanel();
        conteneurDon.setOpaque(false);
        conteneurDon.setPreferredSize(new Dimension(800, pions.size() * 100 + 200));

        texteCapa = new JLabel("");
        texteCapa.setFont(police);
        texteCapa.setText("Selectionner un joueur pour utiliser votre capacité");
        conteneur.add(texteCapa, BorderLayout.NORTH);

        JPanel conteneurDonGau = new JPanel(new GridLayout(pions.size() - 1, 1));
        conteneurDonGau.setPreferredSize(new Dimension(100, pions.size() * 100 - 100));
        conteneurDonGau.setOpaque(false);
        pionSelection = new SelectionPionUnique[pions.size() - 1];
        creeMouseListener(pions);
        index = 0;
        for (int i = 0; i < pions.size(); i++) {

            if (!pions.get(i).equals(pionActif)) {
                pionSelection[index] = new SelectionPionUnique(pions.get(i), false);
                pionSelection[index].setActif(false);
                pionSelection[index].addMouseListener(ms[index]);
                conteneurDonGau.add(pionSelection[index]);
                index++;
            }
        }

        pionSelection[0].setActif(true);
        conteneurDon.add(conteneurDonGau, BorderLayout.WEST);

        JPanel conteneurDonDroi = new JPanel(new GridLayout(pions.size() - 1, 1));

        conteneurDonDroi.setPreferredSize(new Dimension(650, pions.size() * 100 - 100));

        for (int i = 0; i < pions.size(); i++) {
            if (!pions.get(i).equals(pionActif)) {
                JLabel nomJ = new JLabel(pions.get(i).getNomj() + " [" + pions.get(i).getRole().getNomA() + "]");
                nomJ.setFont(police);
                conteneurDonDroi.add(nomJ);
            }
        }
        conteneurDonDroi.setOpaque(false);
        conteneurDon.add(conteneurDonDroi, BorderLayout.EAST);

        txtlololo = new JLabel("");
        txtlololo.setFont(police);
        setTxtlololo(pions);

        conteneurDon.add(txtlololo, BorderLayout.SOUTH);

        conteneur.add(conteneurDon, BorderLayout.CENTER);

        conteneurValidation = new JPanel(new GridLayout(1, 5));
        conteneurValidation.setPreferredSize(new Dimension(800, 20));
        conteneurValidation.setOpaque(false);

        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.DEPLACEMENT_AMI);

                for (int i = 0; i < pions.size() - 1; i++) {
                    if (pionSelection[i].isActif()) {
                        msg.setPion(pionSelection[i].getPion());
                    }
                }
                notifierObservateur(msg);

                fenetre.setVisible(false);
            }
        });
        btnAnnuler = new JButton("Annuler");
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

        conteneur.add(conteneurValidation, BorderLayout.SOUTH);

        fenetre.add(conteneur);

        fenetre.setVisible(true);
    }

    public void setTxtlololo(ArrayList<Pion> pions) {

        for (int i = 0; i < pions.size() - 1; i++) {
            if (pionSelection[i].isActif()) {
                txtlololo.setText("Vous allez utiliser la capacité sur " + pionSelection[i].getPion().getNomj() + "[" + pionSelection[i].getPion().getRole().getNomA() + "]");
            }
        }

    }

    public void creeMouseListener(ArrayList<Pion> pions) {
        ms = new MouseListener[3];
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pionSelection[0].setActif(true);
                setTxtlololo(pions);

                for (int j = 0; j < pions.size() - 1; j++) {
                    if (j != 0) {
                        pionSelection[j].setActif(false);

                    }
                }
                setTxtlololo(pions);

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
                setTxtlololo(pions);
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
                setTxtlololo(pions);
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

    /*        
        ArrayList<Pion> pionsChoix = new ArrayList<>();
        pionsChoix.addAll(pions);
        fenetre = new JFrame("Déplacer ami");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        int n = 0;

        for (Pion pion : pions) {
            if (pion == pionActif) {
                numPion = n;
            }
            n++;
        }

        pionsChoix.remove(pionActif);

        //Création de la zone de choix du joueur
        conteneurJoueurs = new JPanel(new GridLayout(pionsChoix.size() + 1, 1));
        conteneurBoutons = new JPanel(new GridLayout(1,5));

        labelJoueurs = new JLabel("Choisissez un joueur :");
        conteneurJoueurs.add(labelJoueurs);

        boutonsJoueurs = new JRadioButton[pionsChoix.size()];
        groupeJoueurs = new ButtonGroup();

        int i = 0;
        for (Pion pion : pionsChoix) {
            JRadioButton nouveauBouton = new JRadioButton(pion.getNomj());
            groupeJoueurs.add(nouveauBouton);
            boutonsJoueurs[i] = nouveauBouton;
            conteneurJoueurs.add(boutonsJoueurs[i]);
            i++;
        }
        boutonsJoueurs[0].setSelected(true);

        fenetre.add(conteneurJoueurs, BorderLayout.CENTER);

        
        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.DEPLACEMENT_AMI);
                int i = 0;
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
        btnAnnuler.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });
        
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(btnValider);
        conteneurBoutons.add(new JLabel(""));
        conteneurBoutons.add(btnAnnuler);
        conteneurBoutons.add(new JLabel(""));
        
        fenetre.add(conteneurBoutons, BorderLayout.SOUTH);
        fenetre.setVisible(true);
     */
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
