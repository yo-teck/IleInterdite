/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageAventurier;

import ileinterdite.PackageTuile.Tuile;
import java.awt.Color;

/**
 *
 * @author dusema
 */
public class Ingenieur extends Aventurier {

   public Ingenieur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur=Color.RED;
    }
    
   public Ingenieur() {
        super("Ingenieur",
                "L'Ingénieur peut assécher 2 tuiles pour une action.");
        this.couleur=Color.RED;
    }    
    
    
    
    
    
    
    
    
    
}
