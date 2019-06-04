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
import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public abstract class Aventurier {

    private String nomA;
    private String description;
    private Pion pion;
    private Tuile tuileDepart;
    protected Color couleur;

    public Aventurier(String nomA, String description, Tuile tuileDepart) {
        setNomA(nomA);
        setDescription(description);
        setTuileDepart(tuileDepart);
    }

    public Aventurier(String nomA, String description) {
        setNomA(nomA);
        setDescription(description);
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

    public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesDispoPourDeplacement = new ArrayList<>();
        tuilesDispoPourDeplacement = grille.getTuilesCroix(tuile);
        tuilesDispoPourDeplacement = grille.getNonSubmerge(tuilesDispoPourDeplacement);
        tuilesDispoPourDeplacement.remove(tuile);
        return tuilesDispoPourDeplacement;

    }
    
    public ArrayList<Tuile> getTuilesAdjacentesInnondees(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentesInnondees = new ArrayList<>();
        tuilesAdjacentesInnondees= grille.getTuilesCroix(tuile);
        tuilesAdjacentesInnondees= grille.getTuilesInnondees(tuilesAdjacentesInnondees);
               
        return tuilesAdjacentesInnondees;        
        
    }
    
   
}
