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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author richomml
 */
public class VueInformation implements Observe {

    private JFrame fenetre;
    private JPanel conteneur;
    private JPanel conteneurNoms;
    private JPanel conteneurDescriptions;

    public VueInformation(ArrayList<Pion> pions) {
        fenetre = new JFrame("Informations");
        fenetre.setSize(1135, 500);
        configureWindow(fenetre);

        conteneur = new JPanel(new BorderLayout());

        conteneurNoms = new JPanel(new GridLayout(pions.size(), 1));
        for (int i = 0; i < pions.size(); i++) {
            conteneurNoms.add(new JLabel(pions.get(i).getNomj() + " (" + pions.get(i).getRole().getNomA() + ")   "));
        }
        conteneur.add(conteneurNoms, BorderLayout.WEST);

        conteneurDescriptions = new JPanel(new GridLayout(pions.size(), 1));
        for (int i = 0; i < pions.size(); i++) {
            conteneurDescriptions.add(new JLabel(pions.get(i).getRole().getDescription()));
        }
        conteneur.add(conteneurDescriptions, BorderLayout.CENTER);

        fenetre.add(conteneur);
        fenetre.setVisible(true);
    }

    private void configureWindow(JFrame window) {
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
                window.setVisible(false);
                Message m = new Message(TypesMessage.FERMER_INFO);
                notifierObservateur(m);
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

