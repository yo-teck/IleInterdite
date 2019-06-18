/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Pion;
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
public class VueInformation {
    private JFrame fenetre;
    private JPanel conteneur;
    private JPanel conteneurNoms;
    private JPanel conteneurDescriptions;
    
    VueInformation(ArrayList<Pion> pions){
        fenetre = new JFrame();
        fenetre.setSize(750, 500);
        
        conteneur = new JPanel(new BorderLayout());
        
        conteneurNoms = new JPanel(new GridLayout(pions.size(), 1));        
        for (int i = 0; i<pions.size(); i++){
            conteneurNoms.add(new JLabel(pions.get(i).getNomj() + " (" + pions.get(i).getRole().getNomA() + ")   "));
        }        
        conteneur.add(conteneurNoms, BorderLayout.WEST);
        
        conteneurDescriptions = new JPanel(new GridLayout(pions.size(), 1));        
        for (int i = 0; i<pions.size(); i++){
            conteneurDescriptions.add(new JLabel(pions.get(i).getRole().getDescription()));
        }        
        conteneur.add(conteneurDescriptions, BorderLayout.CENTER);
        
        fenetre.add(conteneur);
        fenetre.setVisible(true);
    }
}
