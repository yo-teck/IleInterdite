package ileinterdite.Vues.Custom;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Yoann
 */
//Permet d'avoir une image de fond avec une planche
public class Fond extends JPanel {
    private String lien;
    public Fond(String lien) {
        this.lien = lien;
    }

    @Override
    public void paintComponent(Graphics g) {

        Dimension dimension = getSize();
        Graphics2D g2d = (Graphics2D) g;

        int hauteurf = dimension.height;
        int largeurf = dimension.width;

        File chemin = new File("");
        System.out.println(hauteurf + "x" + largeurf);
        g2d.drawImage(new ImageIcon(chemin.getAbsolutePath() + lien).getImage(), 0, 0, largeurf, hauteurf, this);

    }
}