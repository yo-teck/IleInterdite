/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

/**
 *
 * @author dusema
 */
public abstract class Aventurier {
    private String nomA;
    private String description;
    private Joueur joueur;
    private Tuile tuileDepart;

    public Aventurier(String nomA, String description,Tuile tuileDepart) {
        this.nomA = nomA;
        this.description = description;
        this.tuileDepart =tuileDepart;
       
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

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Tuile getTuileDepart() {
        return tuileDepart;
    }

    public void setTuileDepart(Tuile tuileDepart) {
        this.tuileDepart = tuileDepart;
    }
    
    
            
}
