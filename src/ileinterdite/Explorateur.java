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
public class Explorateur extends Aventurier{

     Explorateur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur=Color.GREEN;
    }
    
    
     @Override
     public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesDispoPourDeplacement = new ArrayList<>();
        tuilesDispoPourDeplacement = grille.getTuilesCroix(tuile);
        tuilesDispoPourDeplacement.addAll(grille.getTuilesDiagonale(tuile));
        tuilesDispoPourDeplacement = grille.getNonSubmerge(tuilesDispoPourDeplacement);

        return tuilesDispoPourDeplacement;

    }
     
     
     @Override
     public ArrayList<Tuile> getTuilesAdjacentesInnondees(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentesInnondees = new ArrayList<>();
        tuilesAdjacentesInnondees= grille.getTuilesCroix(tuile);
        tuilesAdjacentesInnondees.addAll(grille.getTuilesDiagonale(tuile));
        tuilesAdjacentesInnondees = grille.getTuilesInnondees(tuilesAdjacentesInnondees);
        
        
        return tuilesAdjacentesInnondees;
        
     
     }
     
     
     
     
     
     
     
     

}
