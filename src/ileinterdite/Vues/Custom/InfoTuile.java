/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues.Custom;

import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author domingoy
 */
public class InfoTuile extends JPanel {

    private ArrayList<Pion> pions;
    private Tuile tuile;
    private int taille;
    private BufferedImage image;

    //Permet de mettre toute les info sur la tuile image de fond et joueur dessus
    public InfoTuile(Tuile tuile) {

        this.tuile = tuile;
        this.pions = tuile.getPions();

    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;

        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        File path = new File("");

        if (tuile.getEtat() == Etat.NULL) {
            g2d.drawImage(tuile.getImageNULL().getImage(), 0, 0, largeurf, hauteurf, this);
        } else if (tuile.getEtat() == Etat.INONDE) {
            g2d.drawImage(tuile.getImageINNONDER().getImage(), 0, 0, largeurf, hauteurf, this);
        } else if (tuile.getEtat() == Etat.SUBMERGE) {
            g2d.drawImage(tuile.getImageSUBMERGER().getImage(), 0, 0, largeurf, hauteurf, this);
        } else {
            g2d.drawImage(tuile.getImageSEC().getImage(), 0, 0, largeurf, hauteurf, this);
        }

        taille = largeurf / 3;

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
                g2d.drawImage(image, taille, 0, taille, taille, this);
            } else if (i == 2) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, taille * 2, 0, taille, taille, this);

            } else if (i == 3) {
                try {
                    image = ImageIO.read(new File(path.getAbsolutePath() + "/src/ressources/imgRole/" + pions.get(i).getRole().getNomA() + ".png"));
                } catch (IOException ex) {
                }
                g2d.drawImage(image, taille, hauteurf - taille, taille, taille, this);

            }
        }

    }

}
