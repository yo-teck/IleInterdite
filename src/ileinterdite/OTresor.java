/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageTuile.Etat;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author dusema
 */
public class OTresor {

    private Tresor type;
    private boolean estRecupere;
    private ImageIcon imageTresorR;
    private ImageIcon imageTresorNR;

    public OTresor(Tresor type, boolean estRecupere) {
        setType(type);
        setEstRecupere(estRecupere);
        creeImage();
    }

    public Tresor getType() {
        return type;
    }

    public void creeImage() {
        File chemin = new File("");
        imageTresorR = new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgTresor/" + getType() + "R.png");
        imageTresorNR = new ImageIcon(chemin.getAbsolutePath() + "/src/ressources/imgTresor/" + getType() + "NR.png");
    }

    public void setType(Tresor type) {
        this.type = type;
    }

    public boolean isEstRecupere() {
        return estRecupere;
    }

    public void setEstRecupere(boolean estRecupere) {
        this.estRecupere = estRecupere;
    }

    public ImageIcon getImageTresorR() {
        return imageTresorR;
    }

    public ImageIcon getImageTresorNR() {
        return imageTresorNR;
    }
    
}
