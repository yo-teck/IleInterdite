/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import ileinterdite.Vues.InfoCarte;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
public class DonnerCarte extends JPanel {

    private ArrayList<CarteTresor> cartes;
    
    private ArrayList<Pion> pionsNonA;
    private JPanel conteneur;

    private InfoCarte carteA;
    private JPanel conteneurText;
    private JPanel conteneurSelection;

    private boolean[] carteSelection;

    public DonnerCarte(Pion pionActif, ArrayList<Pion> pions) {
        pionsNonA = new ArrayList<>();
        pionsNonA.addAll(pions);
        pionsNonA.remove(pionActif);
        
        conteneurText = new JPanel(new GridLayout(3, 1));
        conteneurText.add(new JLabel("Selectionner la carte a donner :"));
       // carteA = new InfoCarte(pionActif);

        carteA.addMouseListener(
                new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int clicX= e.getX();
               int taille = carteA.getHeight()/5;
               int position =(taille)/4;
               
               for (int i = 0; i <pionActif.getNbCartes();i++){
                   if ( position+taille>clicX && clicX>position){

                   }
                   position+=taille;
               }
                

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
        });
        
        
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
