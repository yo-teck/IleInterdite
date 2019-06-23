/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Vues.Custom.FondDemarrer;
import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.Grille;
import ileinterdite.Message;
import ileinterdite.NiveauEau;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author richomml
 */
public class VueDemarrer implements Observe {

    private String[] difficulte;
    private JRadioButton[] choixInit;
    private ButtonGroup groupeBoutons;
    private JTextField[] nomJ;
    private int nb;
    private JLabel infoValid;
    private JLabel[] infoLabel;
    private Font police;
    private File chemin;
    private JButton btnValider;
    private JFrame fenetre;
    private JPanel joueurs;
    private JPanel options;
    private JPanel validation;

    public VueDemarrer() {
        ////////////////////////////////////////////////////////////////////////
        //Création de la police d'écriture
        chemin = new File("");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 20);

        ////////////////////////////////////////////////////////////////////////
        //Paramétrage de la fenetre
        ////////////////////////////////////////////////////////////////////////
        fenetre = new JFrame("Ile Interdite - Menu Démarrage");
        fenetre.setSize(650, 550);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        //Creation d'un tableau pour l

        ////////////////////////////////////////////////////////////////////////
        //Creation d'un JPanel custom avec le fond de demarrage
        ////////////////////////////////////////////////////////////////////////
        JPanel conteneur = new FondDemarrer();
        //Creation de margin invisible
        conteneur.setBorder(BorderFactory.createEmptyBorder(160, 20, 130, 20));
        conteneur.setLayout(new BorderLayout());
        fenetre.add(conteneur);

        ////////////////////////////////////////////////////////////////////////
        //Creation des differents  utilisé
        ////////////////////////////////////////////////////////////////////////
        joueurs = new JPanel(new GridLayout(3, 4));
        joueurs.setOpaque(false);

        options = new JPanel(new GridLayout(3, 5));
        conteneur.add(options, BorderLayout.CENTER);
        options.setOpaque(false);

        validation = new JPanel(new GridLayout(1, 5));
        conteneur.add(validation, BorderLayout.SOUTH);
        validation.setOpaque(false);

        ////////////////////////////////////////////////////////////////////////
        //Creation du JComboBox permettant de selectionner le nb de joueur
        ////////////////////////////////////////////////////////////////////////
        JLabel nbJoueur = new JLabel("Nombre de joueur :");
        nbJoueur.setFont(police);
        joueurs.add(nbJoueur);
        Object[] nbjPossible = {2, 3, 4};
        JComboBox choixNbJoueur = new JComboBox(nbjPossible);
        choixNbJoueur.setFocusable(false);
        choixNbJoueur.setFont(police);
        choixNbJoueur.setBackground(new Color(210, 182, 106));

        //Creation d'un action permettant d'activer la bonne zone de saisie
        nb = (int) choixNbJoueur.getSelectedItem() - 1;
        choixNbJoueur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nb = (int) choixNbJoueur.getSelectedItem() - 1;

                for (int i = 0; i < 4; i++) {
                    if (i <= nb) {
                        nomJ[i].setEnabled(true);
                        infoLabel[i].setEnabled(true);
                        nomJ[i].setBackground(new Color(210, 182, 106));
                    } else {
                        nomJ[i].setText("");
                        nomJ[i].setEnabled(false);
                        infoLabel[i].setEnabled(false);
                        nomJ[i].setBackground(Color.LIGHT_GRAY);
                    }
                }
            }
        });
        joueurs.add(choixNbJoueur);
        joueurs.add(new JLabel(""));
        joueurs.add(new JLabel(""));

        //Creation d'une variable contenant les zones de saisie
        nomJ = new JTextField[4];
        //Creation d'une variable contenant des labels
        infoLabel = new JLabel[4];

        int numeroJ = 1;

        //Creation automatisé de la zone de nom
        for (int i = 0; i < 4; i++) {

            JLabel info = new JLabel("Nom du Joueur " + numeroJ + " : ");
            info.setFont(police);
            infoLabel[i] = info;

            JTextField nom = new JTextField("Joueur "+(i+1));
            nom.setFont(police);
            nomJ[i] = nom;
            nomJ[i].setBackground(new Color(210, 182, 106));
            if (i > nb) {
                nomJ[i].setEnabled(false);
                nomJ[i].setBackground(Color.LIGHT_GRAY);
                infoLabel[i].setEnabled(false);
            }
            numeroJ++;

            joueurs.add(info);
            joueurs.add(nomJ[i]);

        }
        //ajout de cette zone dans un conteneur
        conteneur.add(joueurs, BorderLayout.NORTH);

        //Zone pour les options
        JLabel labelDiff = new JLabel("Difficulté : ");
        difficulte = new String[]{"Novice", "Normal", "Elite", "Legendaire"};
        labelDiff.setFont(police);
        options.add(labelDiff);
        JComboBox choixDiff = new JComboBox(difficulte);
        choixDiff.setFocusable(false);
        choixDiff.setBackground(new Color(210, 182, 106));
        choixDiff.setFont(police);
        options.add(choixDiff);

        JLabel labelInit = new JLabel("Choix Initialisation :");
        labelInit.setFont(police);

        JRadioButton bouton;

        //Creation de groupe de bouton permettant de choisir entre differente
        //initialisations
        groupeBoutons = new ButtonGroup();
        choixInit = new JRadioButton[8];

        bouton = new JRadioButton("Démo");
        choixInit[0] = bouton;
        groupeBoutons.add(bouton);
        bouton.setSelected(true);
        bouton = new JRadioButton("DémoNoyade");
        choixInit[1] = bouton;
        groupeBoutons.add(bouton);

        // Démo pour montrer la defaite lorsque les tuiles permettant de recuperer les tresors sont submergées
        bouton = new JRadioButton("DémoCSubmerge");
        choixInit[2] = bouton;
        groupeBoutons.add(bouton);

        // Démo pour montrer la defaite lorque l'Héliport est submergé
        bouton = new JRadioButton("DémoHeliSub");
        choixInit[3] = bouton;
        groupeBoutons.add(bouton);

        // Démo pour montrer la defaire lorsque le nivaeau d'eau est au maximum
        bouton = new JRadioButton("DémoNiveauEau");
        choixInit[4] = bouton;
        groupeBoutons.add(bouton);

        // Démo pour montrer la victoire
        bouton = new JRadioButton("DémoVictoire");
        choixInit[5] = bouton;
        groupeBoutons.add(bouton);

        // Démo pour montrer que la partie ne s'arrête pas quand toutes les tuiles pour récupérer un trésor sont submergées, 
        // mais ce trésor est déjà possedé
        bouton = new JRadioButton("TestTresors");
        choixInit[6] = bouton;
        groupeBoutons.add(bouton);

        bouton = new JRadioButton("Aléatoire");
        choixInit[7] = bouton;
        groupeBoutons.add(bouton);

        options.add(new JLabel(""));
        options.add(new JLabel(""));
        options.add(new JLabel(""));
        options.add(labelInit);

        for (int i = 0; i < 8; i++) {
            choixInit[i].setFont(police);
            choixInit[i].setOpaque(false);
            options.add(choixInit[i]);
        }

        //Zone de validation
        btnValider = new JButton("Démarrer");
        btnValider.setFont(police);
        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> noms = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    String nom = nomJ[i].getText();

                    noms.add(nomJ[i].getText());
                }

                String dif = choixDiff.getSelectedItem().toString();

                String init = new String();
                for (int i = 0; i < choixInit.length; i++) {

                    if (choixInit[i].isSelected()) {
                        init = choixInit[i].getText();
                    }
                }

                Message m = new Message(TypesMessage.COMMENCER_PARTIE, noms, dif, init, nb);
                notifierObservateur(m);
                fenetre.setVisible(false);

            }
        });

        validation.add(new JLabel(""));
        validation.add(new JLabel(""));
        validation.add(btnValider);
        infoValid = new JLabel("");
        infoValid.setFont(police);
        validation.add(infoValid);
        validation.add(new JLabel(""));

        fenetre.setVisible(true);
    }

    public void montrer(boolean b) {
        fenetre.setVisible(b);
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
