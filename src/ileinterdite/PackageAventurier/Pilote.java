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
public class Pilote extends Aventurier {

    public Pilote(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur = Color.BLUE;
    }

    public Pilote() {
        super("Pilote","Le Pilote peut, une fois par tour, voler jusqu'à n'importe "
                        + "quelle tuile de l'île pour une action. ");
        this.couleur = Color.BLUE;
    }

    // + utiliserhelico()
}
