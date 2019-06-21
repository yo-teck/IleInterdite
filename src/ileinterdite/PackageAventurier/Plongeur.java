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

/**
 *
 * @author dusema
 */
public class Plongeur extends Aventurier {

    public Plongeur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur = new Color(198, 49, 231);
    }

    public Plongeur() {
        super("Plongeur", "Le Plongeur peut passer par une ou deux tuiles adjacentes "
                + "inondées et/ou manquantes pour une action (il doit "
                + "terminer le tour sur une tuile).");
        this.couleur = new Color(198, 49, 231);
    }

    @Override
    public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuileValable = new ArrayList<>();
        ArrayList<Tuile> tuileSubmerger = new ArrayList<>();
        tuileValable = grille.getTuilesCroix(tuile);

        while (!tuileSubmerger.containsAll(grille.getSubInn(tuileValable))) {
            for (Tuile tuileS : grille.getSubInn(tuileValable)) {
                if (!tuileSubmerger.contains(tuileS)) {
                    tuileSubmerger.add(tuileS);
                    for (Tuile tuileNew : grille.getTuilesCroix(tuileS)) {
                        if (!tuileValable.contains(tuileNew)) {
                            tuileValable.add(tuileNew);
                        }
                    }
                }
            }
        }
        tuileValable.remove(tuile);

        return grille.getNonSubmerge(tuileValable);
        //attention le plongeur peut travrser les inondées

    }

}
