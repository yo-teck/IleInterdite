/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public class Pion {
    
    
    private String nomj;
    private static int nbAction = 3;
    private Aventurier role;
    private Tuile tuilePosition;
    private ArrayList<CarteTresor> cartesTresor;
    private Color couleur;

    public Pion(String nomj, Aventurier role, Tuile tuilePosition) {
        this.setNomj(nomj);
        this.setRole(role);
        this.setCouleur(role.getCouleur());
        cartesTresor = new ArrayList<>();
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

    public static int getNbAction() {
        return nbAction;
    }

    public Aventurier getRole() {
        return role;
    }

    public Tuile getTuilePosition() {
        return tuilePosition;
    }

    public ArrayList<CarteTresor> getCartesTresor() {
        return cartesTresor;
    }

    public void setNomj(String nomj) {
        this.nomj = nomj;
    }

    public static void setNbAction(int nbAction) {
        Pion.nbAction = nbAction;
    }

    public void setRole(Aventurier role) {
        this.role = role;
    }

    public void setTuilePosition(Tuile tuilePosition) {
        this.tuilePosition = tuilePosition;
    }

    public void setCartesTresor(ArrayList<CarteTresor> cartesTresor) {
        this.cartesTresor = cartesTresor;
    }
}
