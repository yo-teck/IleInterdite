/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

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
public class DonnerCarte extends JPanel{
        private ArrayList<CarteTresor> cartes;
        private Pion pion;
    public DonnerCarte(Pion pion) {

        this.cartes = pion.getCartesTresors();
        this.pion=pion;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;
        int hauteurf = dimension.height;
        int largeurf = dimension.width;
        
        int position =(largeurf/5)/4;
        
        File chemin = new File("");


        g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteFond.png").getImage(), 0, 0, largeurf,hauteurf , this);
        g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgRole/"+pion.getRole().getNomA()+".png").getImage(), 0, 0, 50, 50, this);
        for (CarteTresor c : cartes){
            g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/"+c.getType()+".png").getImage(), position, 0,hauteurf ,hauteurf , this);
            position+=largeurf/5;
        }
        for (int i =0;i<5-cartes.size();i++){
             g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgCarte/carteVide.png").getImage(), position, 0,hauteurf ,hauteurf , this);
             position+=largeurf/5;
        }
        
    }
}
