/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.NiveauEau;
import ileinterdite.OTresor;
import java.awt.Color;
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
public class InfoTresor extends JPanel {
    
    private OTresor tresor;
    
    public InfoTresor(OTresor tresor) {

        this.tresor = tresor;
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;
        
                File chemin = new File("");


        g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgDecor/boisPanneau.png").getImage(), 0, 0, largeurf,hauteurf , this);
        if (tresor.isEstRecupere()) {
            g2d.drawImage((tresor.getImageTresorR()).getImage(), 0, 0, largeurf, hauteurf, this);
        } else if (!tresor.isEstRecupere()) {
            g2d.drawImage((tresor.getImageTresorNR()).getImage(), 0, 0, largeurf, hauteurf, this);
        }
        
    }
}
