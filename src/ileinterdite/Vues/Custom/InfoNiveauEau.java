/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues.Custom;

import ileinterdite.NiveauEau;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
public class InfoNiveauEau extends JPanel {

    private NiveauEau niveauEau;

//Permet d'afficher l'image en fonction du niveau d'eau
    public InfoNiveauEau(NiveauEau niveauEau) {

        this.niveauEau = niveauEau;

    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int niveau = niveauEau.getNiveau();
        int hauteurf = dimension.height;
        int largeurf = dimension.width;
        File chemin = new File("");
        g2d.drawImage((new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgNiveauEau/niveauDesEaux" + niveau + ".png")).getImage(), 0, 0, largeurf, hauteurf, this);

    }
}
