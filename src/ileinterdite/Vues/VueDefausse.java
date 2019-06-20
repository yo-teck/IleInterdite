/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author richomml
 */
public class VueDefausse implements Observe {

    private Observateur observateur;

    private JFrame fenetre;

    private JPanel conteneurCartes;

    private HashMap<Integer, JCheckBox> cartesADefausser;

    private JButton btnValider;

    public VueDefausse(Pion pionActif) {
        fenetre = new JFrame("Cartes de " + pionActif.getNomj());
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        conteneurCartes = new JPanel(new GridLayout(7, 1));

        cartesADefausser = new HashMap<>();
        JCheckBox caseACocher;

        int i = 0;
        for (CarteTresor ct : pionActif.getCartesTresors()) {
            caseACocher = new JCheckBox(ct.getType().toString());
            cartesADefausser.put(i, caseACocher);
            i++;
        }

        for (Integer j : cartesADefausser.keySet()) {
            conteneurCartes.add(cartesADefausser.get(j));
        }

        fenetre.add(new JLabel(pionActif.getNomj() + " a plus de 5 cartes veuillez défausser au moins " + (pionActif.getNbCartes() - 5) + " cartes : "), BorderLayout.NORTH);
        fenetre.add(conteneurCartes, BorderLayout.CENTER);

        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.DEFAUSSE);
                for (int i = 0; i < pionActif.getNbCartes(); i++) {
                    if (cartesADefausser.get(i).isSelected()) {
                        System.out.println(cartesADefausser.get(i).isSelected());
                        m.addCarteTresor(pionActif.getCartesTresors().get(i));
                    }
                }
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });
        btnValider.setEnabled(false);
        int somme = 0;
        for (int j = 0; j < pionActif.getNbCartes(); j++) {
            if (cartesADefausser.get(i).isSelected()) {
                somme++;
            }
        }
        if(somme==0){
            btnValider.setEnabled(true);
        }
        fenetre.add(btnValider, BorderLayout.SOUTH);
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
