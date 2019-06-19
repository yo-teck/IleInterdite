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
public class VueDonnerCarte implements Observe {

    private JFrame fenetre;

    private JPanel conteneurJoueurs;
    private JPanel conteneurCartes;

    private JLabel labelJoueurs;
    private JLabel labelCartes;

    private JRadioButton[] boutonsJoueurs;
    private JRadioButton[] boutonsCartes;

    private ButtonGroup groupeJoueurs;
    private ButtonGroup groupeCartes;

    private JButton btnValider;

    private int numPion;

    public VueDonnerCarte(Pion pionActif, ArrayList<Pion> pions) {
        ArrayList<Pion> pionsDon = new ArrayList<>();
        pionsDon.addAll(pions);
        fenetre = new JFrame(pionActif.getNomj() + " - Donner une carte");
        fenetre.setLayout(new BorderLayout());
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setSize(500, 500);

        //On enlève le joueur actif de l'ArrayList des pions car il ne peut pas se donner de carte à lui-même
        int n = 0;
        for (Pion pion : pions) {
            if (pion == pionActif) {
                numPion = n;
            }
            n++;
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
        fenetre.add(btnValider, BorderLayout.SOUTH);
        fenetre.setVisible(true);
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
