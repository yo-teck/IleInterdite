/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues.Custom;

import ileinterdite.OTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
public class SelectionCarteMultiple extends JPanel {

    private ArrayList<CarteTresor> cartes;
    private Pion pion;
    private ArrayList<CarteTresor> cartesSelectionnees;
    private boolean[] carteSelection;
    private Dimension dimension;
    private boolean imgBack;

    public SelectionCarteMultiple(Pion pion, boolean imgBack) {
        dimension = getSize();
        this.cartes = pion.getCartesTresors();
        this.pion = pion;
        this.imgBack = imgBack;
        cartesSelectionnees = new ArrayList<>();
        
        carteSelection = new boolean[cartes.size()];
        for (int i = 0; i < cartes.size(); i++) {
            carteSelection[i] = false;
        }


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicx = e.getX();
                int clicy = e.getY();
                int hauteurf = getHeight();
                int largeurf = getWidth();

                int taille = hauteurf / ((cartes.size() / 5) + 1);
                int largeur = largeurf / 5;

                int positionX = 0;
                int positionY = 0;

                for (int i = 0; i < cartes.size(); i++) {

                    if (clicx > positionX && clicx < positionX + largeur && clicy > positionY && clicy < positionY + taille) {
                        carteSelection[i] = !carteSelection[i];
                        
                    }
                    positionX += largeur;
                    if (i == 4) {
                        positionX = 0;
                        positionY += taille;
                    } else if (i == 9) {
                        positionX = 0;
                        positionY += taille;
                    } else if (i == 14) {
                        positionX = 0;
                        positionY += taille;
                    }

                }
                
                for (int i = 0; i < cartes.size(); i++) {

                    if (carteSelection[i] && (!cartesSelectionnees.contains(cartes.get(i))) ){
                        cartesSelectionnees.add(cartes.get(i));
                    } if ((! carteSelection[i]) && cartesSelectionnees.contains(cartes.get(i)) ){
                        cartesSelectionnees.remove(cartes.get(i));
                    }
                    
                }

                repaint();
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

    public Pion getPion() {
        return pion;
    }

    public ArrayList<CarteTresor> getCartesSelectionnees() {
        return cartesSelectionnees;
    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        int taille = hauteurf / ((cartes.size() / 5) + 1);

        int positionX = largeurf / (2 * 5) - taille / 2;
        int positionY = 0;
        File chemin = new File("");
        if (imgBack) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgDecor/fondCarteMonde.jpg").getImage(), 0, 0, largeurf, hauteurf, this);
        }
        System.out.println(chemin.getAbsolutePath());
        for (int i = 0; i < cartes.size(); i++) {

            if (!carteSelection[i]) {
                g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + "D.png").getImage(), positionX, positionY, taille, taille, this);
            } else {
                g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + ".png").getImage(), positionX, positionY, taille, taille, this);
            }
            positionX += largeurf / (5);
            if (i == 4) {
                positionX = largeurf / (2 * 5) - taille / 2;
                positionY += taille;
            } else if (i == 9) {
                positionX = largeurf / (2 * 5) - taille / 2;
                positionY += taille;
            } else if (i == 14) {
                positionX = largeurf / (2 * 5) - taille / 2;
                positionY += taille;
            }
        }

    }
}
