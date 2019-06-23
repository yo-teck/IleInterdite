/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageAventurier;

import ileinterdite.Grille;
import ileinterdite.Pion;
import ileinterdite.PackageTuile.Tuile;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author dusema
 */
public abstract class Aventurier {

    private String nomA;
    private String description;
    private Pion pion;
    private Tuile tuileDepart;
    private ImageIcon imgAventurier;
    private boolean capaciteUtilisee;
    private File chemin = new File("");
    protected Color couleur;
    

    public Aventurier(String nomA, String description, Tuile tuileDepart) {
        setNomA(nomA);
        setDescription(description);
        setTuileDepart(tuileDepart);
        setImgAventurier(new ImageIcon(getChemin().getAbsolutePath() + "/src/ressources/imgRole/" + getNomA() + ".png"));
        capaciteUtilisee = false;
    }

    public Aventurier(String nomA, String description) {
        setNomA(nomA);
        setDescription(description);
        setImgAventurier(new ImageIcon(getChemin().getAbsolutePath() + "/src/ressources/imgRole/" + getNomA() + ".png"));
        capaciteUtilisee = false;
    }

    public boolean isCapaciteUtilisee() {
        return capaciteUtilisee;
    }

    public void setCapaciteUtilisee(boolean capaciteUtilisee) {
        this.capaciteUtilisee = capaciteUtilisee;
    }

    public Color getCouleur() {
        return couleur;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pion getPion() {
        return pion;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    public Tuile getTuileDepart() {
        return tuileDepart;
    }

    public void setTuileDepart(Tuile tuileDepart) {
        this.tuileDepart = tuileDepart;
    }

    public File getChemin() {
        return chemin;
    }

    public void setImgAventurier(ImageIcon imgAventurier) {
        this.imgAventurier = imgAventurier;
    }

    public ImageIcon getImgAventurier() {
        return imgAventurier;
    }

    public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesDispoPourDeplacement = new ArrayList<>();
        tuilesDispoPourDeplacement = grille.getTuilesCroix(tuile);
        tuilesDispoPourDeplacement = grille.getNonSubmerge(tuilesDispoPourDeplacement);
        tuilesDispoPourDeplacement.remove(tuile);
        return tuilesDispoPourDeplacement;

    }

    public ArrayList<Tuile> getTuilesAdjacentesInnondees(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentesInnondees = new ArrayList<>();
        tuilesAdjacentesInnondees = grille.getTuilesCroix(tuile);
        tuilesAdjacentesInnondees = grille.getTuilesInondees(tuilesAdjacentesInnondees);

        return tuilesAdjacentesInnondees;

    }


    
}
