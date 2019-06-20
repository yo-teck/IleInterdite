/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.NiveauEau;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Evenement;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author Yoann
 */
public class InfoNiveauEau extends JPanel{
    


    private NiveauEau niveauEau;


    public InfoNiveauEau(NiveauEau niveauEau) {

        this.niveauEau = niveauEau ;
        
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
