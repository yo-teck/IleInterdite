/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageAventurier;

import ileinterdite.Tuile;
import java.awt.Color;

/**
 *
 * @author dusema
 */
public class Plongeur extends Aventurier{

    public Plongeur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur=Color.MAGENTA;   
    }

        
}
