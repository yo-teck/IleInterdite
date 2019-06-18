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
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bognery
 */
public class VueIndividuelle implements Observe {
//vues pour chaque aventurier, afin de savoir quels roles ils ont et leurs cartes

    private JFrame vJ1;
    private JFrame vJ2;
    private JFrame vJ3;
    private JFrame vJ4;
    private Observateur observateur;

    public VueIndividuelle(Pion p1, Pion p2, Pion p3, Pion p4) {
        vJ1 = new JFrame();
        vJ2 = new JFrame();
        vJ3 = new JFrame();
        vJ4 = new JFrame();
        JPanel PanelH1 = new JPanel();
        JPanel PanelH2 = new JPanel();
        JPanel PanelH3 = new JPanel();
        JPanel PanelH4 = new JPanel();
        PanelH1.setBackground(p1.getCouleur());
        PanelH2.setBackground(p2.getCouleur());
        PanelH3.setBackground(p3.getCouleur());
        PanelH4.setBackground(p4.getCouleur());
        JLabel l1 = new JLabel(p1.getNomj());
        JLabel l2 = new JLabel(p2.getNomj());
        JLabel l3 = new JLabel(p3.getNomj());
        JLabel l4 = new JLabel(p4.getNomj());
        PanelH1.add(l1, BorderLayout.CENTER);
        PanelH2.add(l2, BorderLayout.CENTER);
        PanelH3.add(l3, BorderLayout.CENTER);
        PanelH4.add(l4, BorderLayout.CENTER);
        vJ1.add(PanelH1, BorderLayout.NORTH);
        vJ2.add(PanelH2, BorderLayout.NORTH);
        vJ3.add(PanelH3, BorderLayout.NORTH);
        vJ4.add(PanelH4, BorderLayout.NORTH);
        vJ1.setVisible(true);
        vJ2.setVisible(true);
        vJ3.setVisible(true);
        vJ4.setVisible(true);
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
