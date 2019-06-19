/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Evenement;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author domingoy
 */
public class InfoBouton extends JPanel {

    private ArrayList<Pion> pions;
    private Tuile tuile;
    private int taille;              // diametre du bouton en pixels
    private BufferedImage image;

    public InfoBouton(Tuile tuile) {

        Dimension dimension = getSize();

        this.tuile = tuile;
        this.pions = tuile.getPions();

    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;

        int hauteurf = dimension.height;
        int largeurf = dimension.width;

      
        taille = 50;


        File path = new File("");
        for (int i = 0; i < pions.size(); i++) {
            if (i == 0) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, 0, 0, taille, taille, this);
            } else if (i == 1) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, largeurf - taille, 0, taille, taille, this);
            } else if (i == 2) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, 0, hauteurf - taille, taille, taille, this);

            } else if (i == 3) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, largeurf - taille, hauteurf - taille, taille, taille, this);

            }
        }
        /*
        taille -=10;
        if (tuile.getEtat()!=Etat.SUBMERGE&&(tuile.getEvent() == Evenement.AIR || tuile.getEvent() == Evenement.EAU
                || tuile.getEvent() == Evenement.TERRE || tuile.getEvent() == Evenement.FEU)) {

            try {

                image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources_imgTresor/" + tuile.getEvent() + ".png"));
            } catch (IOException ex) {

            }
            g2d.drawImage(image, ((largeurf / 2) - (taille/2)), (hauteurf - image.getHeight() - (taille - image.getHeight())), taille, taille, this);
        }*/
    }

}
