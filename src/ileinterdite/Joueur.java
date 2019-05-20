/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public class Joueur {
    
    
    private String nomj;
    private static int nbAction = 3;
    private Aventurier role;
    private Tuile tuilePosition;
    private ArrayList<CarteTresor> cartesTresor;

    public Joueur(String nomj, Aventurier role, Tuile tuilePosition) {
        this.setNomj(nomj);
        this.setRole(role);
        cartesTresor = new ArrayList<>();
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
        Joueur.nbAction = nbAction;
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
