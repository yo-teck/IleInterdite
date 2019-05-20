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

    public Aventurier(String nomA, String description, Joueur joueur, Tuile tuileDepart) {
        this.nomA = nomA;
        this.description = description;
        this.joueur = joueur;
        this.tuileDepart = tuileDepart;
    }
    
    
            
}
