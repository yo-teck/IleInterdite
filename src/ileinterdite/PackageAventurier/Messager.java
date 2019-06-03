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
public class Messager extends Aventurier {
    

    
    public Messager(String nomA, String description, Tuile t ) {
        super(nomA, description,t);
        this.couleur=Color.ORANGE;
    }
    
    public Messager() {
        super("Messager","Le Messager peut donner des cartes Trésor à un autre joueur "
                + "n'importe où sur l'île pour 1 action par carte. ");
        this.couleur=Color.ORANGE;
    }
     // +donnercarte n'importe quel tour
    
}
