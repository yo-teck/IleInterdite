/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.PackageAventurier.Aventurier;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public class Pion {
    
    
    private String nomj;
    private int nbAction = 3;
    private Aventurier role;
    private Tuile tuilePosition;
    private ArrayList<CarteTresor> cartesTresors;
    private Color couleur;

    public Pion(Aventurier role) {
        
        this.setRole(role);
        this.setCouleur(role.getCouleur());
        cartesTresors = new ArrayList<>();
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public String getNomj() {
        return nomj;
    }

    public int getNbAction() {
        return nbAction;
    }

    public Aventurier getRole() {
        return role;
    }

    public Tuile getTuilePosition() {
        return tuilePosition;
    }

    public ArrayList<CarteTresor> getCartesTresors() {
        return cartesTresors;
    }

    public void addCarte(CarteTresor CT){
        this.cartesTresors.add(CT);
    }
    public void setNomj(String nomj) {
        this.nomj = nomj;
    }

    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
    }

    public void setRole(Aventurier role) {
        this.role = role;
    }

    public void setTuilePosition(Tuile tuilePosition) {
        this.tuilePosition = tuilePosition;
    }

    public void setCartesTresors(ArrayList<CarteTresor> cartesTresors) {
        this.cartesTresors = cartesTresors;
    }
    
    public int getNbCartes(){
        return getCartesTresors().size();
    }
    
    
}
