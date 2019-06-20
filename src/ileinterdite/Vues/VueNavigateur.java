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
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JFrame fenetre;

    private int numPion;

    private JPanel conteneurJoueurs;
    private JPanel conteneurBoutons;

    
    private JRadioButton[] boutonsJoueurs;
    private ButtonGroup groupeJoueurs;

    private JButton btnValider;
    private JButton btnAnnuler;
    
    private JLabel labelJoueurs;

    public VueNavigateur(Pion pionActif, ArrayList<Pion> pions) {
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
