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
//Permet de selectionner une unique carte  (en couleur si il est actif et en nuance de gris si il est inactif)
public class SelectionCarteUnique extends JPanel {

    private ArrayList<CarteTresor> cartes;
    private Pion pion;
    private CarteTresor carte;
    private boolean[] carteSelection;
    private Dimension dimension;
    private boolean imgBack;
    private boolean actif;

    public SelectionCarteUnique(Pion pion, boolean imgBack) {
        dimension = getSize();
        this.cartes = pion.getCartesTresors();
        this.pion = pion;
        this.imgBack = imgBack;
        this.actif = true;
        carteSelection = new boolean[cartes.size()];
        for (int i = 0; i < cartes.size(); i++) {
            carteSelection[i] = false;
        }
        carteSelection[0] = true;
        carte = pion.getCartesTresors().get(0);
        
        //Permet changer la variable de la carte selectionnée
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicx = e.getX();
                int hauteurf = getHeight();
                int largeurf = getWidth();

                int position = 0;

                //creation des if permetant selon un clique de changer la carte selectionner
                //a une variable et d'enlever l'ancienne
                if (clicx > position || clicx < largeurf - position * 2) {
                    for (int i = 0; i < cartes.size(); i++) {

                        if (clicx > position && clicx < position + largeurf / cartes.size()) {
                            carteSelection[i] = true;
                            carte = pion.getCartesTresors().get(i);

                        } else {
                            carteSelection[i] = false;

                        }
                        position += largeurf / cartes.size();

                    }
                }
                System.out.println(carte.getType());
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Pion getPion() {
        return pion;
    }

    public CarteTresor getCarte() {
        return carte;
    }

    @Override
    public void paintComponent(Graphics g) {
//Placage des cartes sur l'image 

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        int position = largeurf / (2 * cartes.size()) - hauteurf / 2;

        File chemin = new File("");
        if (imgBack) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteFond.png").getImage(), 0, 0, largeurf, hauteurf, this);
        }

        if (actif) {
            for (int i = 0; i < cartes.size(); i++) {
                if (!carteSelection[i]) {
                    g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + "D.png").getImage(), position, 0, hauteurf, hauteurf, this);
                } else {
                    g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + ".png").getImage(), position, 0, hauteurf, hauteurf, this);
                }
                position += largeurf / cartes.size();

            }
        } else {
            for (int i = 0; i < cartes.size(); i++) {
                g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + "D.png").getImage(), position, 0, hauteurf, hauteurf, this);
            }
        }
    }
}
