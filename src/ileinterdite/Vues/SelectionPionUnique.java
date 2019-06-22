/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

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
public class SelectionPionUnique extends JPanel {

    private Pion pion;
    private Dimension dimension;
    private boolean[] carteSelection;
    private boolean imgBack;
    private boolean actif;

    public SelectionPionUnique(Pion pion, boolean imgBack) {

        dimension = getSize();
        this.actif = false;
        this.pion = pion;
        this.imgBack = imgBack;

    }

    public Pion getPion() {
        return pion;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
        repaint();
    }

    public boolean isActif() {
        return actif;
    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        File chemin = new File("");
        if (imgBack) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteFond.png").getImage(), 0, 0, largeurf, hauteurf, this);
        }
        if (actif) {
            g2d.drawImage(pion.getRole().getImgAventurier().getImage(), 0, 0, largeurf, hauteurf, this);
        } else {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgRole/" + pion.getRole().getNomA() + "D.png").getImage(), 0, 0, largeurf, hauteurf, this);
        }

    }
}
