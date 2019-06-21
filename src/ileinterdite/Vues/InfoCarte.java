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
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
public class InfoCarte extends JPanel {

    private ArrayList<CarteTresor> cartes;
    private Pion pion;
    private boolean back;
    private boolean icone;

    public InfoCarte(Pion pion, boolean back, boolean icone) {

        this.cartes = pion.getCartesTresors();
        this.pion = pion;
        this.back = back;
        this.icone = icone;
    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        int position = largeurf/(5*2) - hauteurf/2;
        
        File chemin = new File("");

        if (back) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteFond.png").getImage(), 0, 0, largeurf, hauteurf, this);
        }
        if (icone) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgRole/" + pion.getRole().getNomA() + ".png").getImage(), 0, 0, 50, 50, this);
        }
        
        for (CarteTresor c : cartes) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/" + c.getType() + ".png").getImage(), position, 0, hauteurf, hauteurf, this);
            position += largeurf / 5;
        }
        for (int i = 0; i < 5 - cartes.size(); i++) {
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteVide.png").getImage(), position, 0, hauteurf, hauteurf, this);
            position += largeurf / 5;
        }

    }
}
