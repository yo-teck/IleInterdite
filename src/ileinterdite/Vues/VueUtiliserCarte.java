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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author richomml
 */
public class VueUtiliserCarte implements Observe {

    private JFrame fenetre;

    private JPanel conteneurBoutonsConfirmation;

    private JButton btnValider;
    private JButton btnAnnuler;
    private JButton btnSauvegarde;

    public VueUtiliserCarte(ArrayList<Pion> pions) {
        fenetre = new JFrame("Utiliser une carte");
        fenetre.setSize(500, 500);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new GridLayout(pions.size() * 2 + 1, 1));

        Message m = new Message(TypesMessage.UTILISER_CARTE);
        btnSauvegarde = new JButton();
        
        for (Pion pion : pions) {
            fenetre.add(new JLabel("Cartes de " + pion.getNomj() + " :"));
            JPanel conteneurCarte = new JPanel(new GridLayout(1, pion.getNbCartes()));
            
            for (CarteTresor ct : pion.getCartesTresors()) {
                
                if (ct.getType() == CTresor.SAC_SABLE || ct.getType() == CTresor.HELICO) {
                    JButton boutonCarte = new JButton(ct.getType().toString());
                    
                    boutonCarte.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            m.setPion(pion);
                            m.setCarteTresor(ct);
                            boutonCarte.setEnabled(false);
                            btnSauvegarde.setEnabled(true);
                            btnSauvegarde = boutonCarte;
                        }
                    });
                    
                    conteneurCarte.add(boutonCarte);
                }
                
            }
            
            fenetre.add(conteneurCarte);
        }

        conteneurBoutonsConfirmation = new JPanel(new GridLayout(1, 5));

        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(m);
                fenetre.setVisible(false);
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

        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnValider);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnAnnuler);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        fenetre.add(conteneurBoutonsConfirmation);
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
