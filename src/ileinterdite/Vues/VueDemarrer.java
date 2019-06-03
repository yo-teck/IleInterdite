/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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

    private String[] difficulte = new String[]{"Novice", "Normal", "Elite", "Legendaire"};
    private JRadioButton[] choixInit;
    private ButtonGroup groupeBoutons;

    public VueDemarrer() {
        JFrame fenetre = new JFrame("Ile Interdite - Menu Démarrage");
        fenetre.setSize(600, 250);

        JPanel conteneur = new JPanel(new BorderLayout());
        fenetre.add(conteneur);

        JPanel joueurs = new JPanel(new GridLayout(2, 4));

        JPanel options = new JPanel(new GridLayout(3, 5));
        conteneur.add(options, BorderLayout.CENTER);

        JPanel validation = new JPanel(new GridLayout(1, 5));
        conteneur.add(validation, BorderLayout.SOUTH);

        //Zone pour les noms des joueurs
        JLabel j1 = new JLabel("Nom du Joueur 1 : ");
        JLabel j2 = new JLabel("Nom du Joueur 2 : ");
        JLabel j3 = new JLabel("Nom du Joueur 3 : ");
        JLabel j4 = new JLabel("Nom du Joueur 4 : ");

        JTextField nomj1 = new JTextField();
        JTextField nomj2 = new JTextField();
        JTextField nomj3 = new JTextField();
        JTextField nomj4 = new JTextField();

        joueurs.add(j1);
        joueurs.add(nomj1);
        joueurs.add(j2);
        joueurs.add(nomj2);
        joueurs.add(j3);
        joueurs.add(nomj3);
        joueurs.add(j4);
        joueurs.add(nomj4);

        conteneur.add(joueurs, BorderLayout.NORTH);

        //Zone pour les options
        JLabel labelDiff = new JLabel("Difficulté : ");
        options.add(labelDiff);
        JComboBox choixDiff = new JComboBox(difficulte);
        options.add(choixDiff);
        JLabel labelInit = new JLabel("Choix Initialisation :");

        JRadioButton bouton;

        groupeBoutons = new ButtonGroup();
        choixInit = new JRadioButton[2];

        bouton = new JRadioButton("Démo");
        choixInit[0] = bouton;
        groupeBoutons.add(bouton);
        bouton.setSelected(true);

        bouton = new JRadioButton("Aléatoire");
        choixInit[1] = bouton;
        groupeBoutons.add(bouton);

        options.add(labelInit);
        options.add(new JLabel(""));
        options.add(choixInit[0]);
        options.add(choixInit[1]);

        //Zone de validation
        JButton btnValider = new JButton("Valider");
        
        btnValider.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<String> noms = new ArrayList<>();
                        noms.add(nomj1.getText());
                        noms.add(nomj2.getText());
                        noms.add(nomj3.getText());
                        noms.add(nomj4.getText());

                        String dif = choixDiff.getSelectedItem().toString();
                        
                        String init = new String();
                        for (int i = 0; i < choixInit.length; i++) {
                            
                            if (choixInit[i].isSelected()){
                                init = choixInit[i].getText();
                            }                        
                        }
                        System.out.println(init);
                        Message m = new Message(TypesMessage.COMMENCER_PARTIE,noms,dif,init);
                        notifierObservateur(m);
                        fenetre.setVisible(false);
                    }
                });

        validation.add(new JLabel(""));
        validation.add(new JLabel(""));
        validation.add(btnValider);
        validation.add(new JLabel(""));
        validation.add(new JLabel(""));

        fenetre.setVisible(true);
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
