/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Pion;
import java.awt.Color;
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
public class InfoBouton extends JPanel {

    private ArrayList<Pion> pions;
    private int diametre;              // diametre du bouton en pixels
    private BufferedImage image;

    public InfoBouton(ArrayList<Pion> pions) /*throws IOException*/ {
        setBackground(Color.white);

        setDoubleBuffered(true);
        Dimension dimension = getSize();
        this.pions = pions;
        // image = ImageIO.read(new File("/users/info/etu-s2/domingoy/M2105/TP5/m2105_ihm_tp5/src/bouton/tropheefeu.png"));

    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;

        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        if (hauteurf > largeurf) {
            diametre = largeurf * 20 / 100;
        } else if (hauteurf < largeurf) {
            diametre = hauteurf * 20 / 100;
        }


        for (int i = 0; i < pions.size(); i++) {
            if (i == 0) {
                g2d.setColor(pions.get(i).getCouleur());
                g2d.fillOval(0, 0, diametre, diametre);
            } else if (i == 1) {
                g2d.setColor(pions.get(i).getCouleur());
                g2d.fillOval(largeurf - diametre, 0, diametre, diametre);
            } else if (i == 2) {
                g2d.setColor(pions.get(i).getCouleur());
                g2d.fillOval(0, hauteurf - diametre, diametre, diametre);
            } else if (i == 3) {
                g2d.setColor(pions.get(i).getCouleur());
                g2d.fillOval(largeurf - diametre, hauteurf - diametre, diametre, diametre);
            }
        }

        //g2d.drawImage(image, ((largeurf/2)-(image.getWidth()/2)), (hauteurf-image.getHeight()), this);
    }

    ////////////////////////////////////////////////////////////////////////////
    /// Variante pour l'implémentation du pattern Observateur/Observé
    /// Observe est désormais une 'interface'
}
