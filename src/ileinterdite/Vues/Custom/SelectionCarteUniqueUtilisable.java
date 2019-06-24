/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues.Custom;

import ileinterdite.OTresor;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import java.awt.Color;
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
public class SelectionCarteUniqueUtilisable extends JPanel {

    private ArrayList<CarteTresor> cartes;
    private Pion pion;
    private CarteTresor carte;
    private boolean[] carteSelection;
    private Dimension dimension;
    private boolean imgBack;
    private boolean actif;

//Permet de selectionner une unique carte utilisable (en couleur si il est actif et en nuance de gris si il est inactif)
    public SelectionCarteUniqueUtilisable(Pion pion, boolean imgBack) {
        dimension = getSize();

        this.pion = pion;
        this.imgBack = imgBack;
        this.actif = true;

        //initialisation des cartes qui peuvent etre utilisé 
        cartes = new ArrayList<>();
        for (CarteTresor carteUti : pion.getCartesTresors()) {

            if (carteUti.getType().equals(CTresor.HELICO) || carteUti.getType().equals(CTresor.SAC_SABLE)) {
                this.cartes.add(carteUti);

            }
        }
        this.carteSelection = new boolean[cartes.size()];
        for (int i = 0; i < cartes.size(); i++) {
            carteSelection[i] = false;
        }
        carteSelection[0] = true;
        carte = cartes.get(0);

        //creation des if permetant selon un clique de changer la carte selectionner
        //a une variable et d'enlever l'ancienne
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicx = e.getX();
                int hauteurf = getHeight();
                int largeurf = getWidth();

                int position = largeurf / 6;

                if (!(clicx > 0 && clicx < position)) {
                    for (int i = 0; i < cartes.size(); i++) {

                        if (clicx > position && clicx < position + largeurf / 6) {
                            carteSelection[i] = true;
                            carte = cartes.get(i);

                        } else {
                            carteSelection[i] = false;

                        }
                        position += largeurf / (6);

                    }
                    if (clicx > position) {
                        carteSelection[cartes.size() - 1] = true;
                        carte = cartes.get(cartes.size() - 1);
                    }
                } else {
                    position = largeurf / 6;
                    for (int i = 0; i < cartes.size(); i++) {
                        carteSelection[i] = false;
                    }

                    carteSelection[0] = true;
                    carte = cartes.get(0);
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
        }
        );
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

        int position = largeurf / (2 * 6) - hauteurf / 2;

        File chemin = new File("");
        if (imgBack) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteFond.png").getImage(), 0, 0, largeurf, hauteurf, this);
        }

        if (actif) {
            g2d.drawImage(pion.getRole().getImgAventurier().getImage(), position, 0, hauteurf, hauteurf, this);
            g2d.setBackground(Color.black);

            position += largeurf / (6);
            g2d.drawLine(position, 20, position, hauteurf - 20);
            g2d.drawLine(position + 1, 20, position + 1, hauteurf - 20);
            for (int i = 0; i < cartes.size(); i++) {
                if (!carteSelection[i]) {
                    g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + "D.png").getImage(), position, 0, hauteurf, hauteurf, this);
                } else {
                    g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + ".png").getImage(), position, 0, hauteurf, hauteurf, this);
                }
                position += largeurf / (6);

            }
        } else {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgRole/" + pion.getRole().getNomA() + "D.png").getImage(), position, 0, hauteurf, hauteurf, this);
            position += largeurf / (6);
            g2d.setBackground(Color.black);
            g2d.drawLine(position, 20, position, hauteurf - 20);
            g2d.drawLine(position + 1, 20, position + 1, hauteurf - 20);
            for (int i = 0; i < cartes.size(); i++) {

                g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + cartes.get(i).getType() + "D.png").getImage(), position, 0, hauteurf, hauteurf, this);

                position += largeurf / (6);

            }
        }
    }
}
