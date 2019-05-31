/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageAventurier;

import ileinterdite.Grille;
import ileinterdite.PackageTuile.Tuile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author dusema
 */
public class Navigateur extends Aventurier {

    public Navigateur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur=Color.YELLOW;        
    }
    
    @Override
    public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuileValable =new ArrayList<>();
        ArrayList<Tuile> tuileSubmerger =new ArrayList<>();
        tuileValable = grille.getTuilesCroix(tuile);

        while (! tuileSubmerger.containsAll(grille.getSubmerge(tuileValable))){

            for (Tuile tuileS : grille.getSubmerge(tuileValable)){
                if ( ! tuileSubmerger.contains(tuileS)){
                    tuileSubmerger.add(tuileS);
                    for (Tuile tuileNew : grille.getTuilesCroix(tuileS)){
                        if (! tuileValable.contains(tuileNew)){
                            tuileValable.add(tuileNew);
                        }
                   }
                }
            }
        }       
        tuileValable.remove(tuile);
        return grille.getNonSubmerge(tuileValable);
    }
}
