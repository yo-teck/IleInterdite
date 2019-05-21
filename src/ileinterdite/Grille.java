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
public class Grille {
    
    private static Tuile[][] ile;
    
    Grille(){
        this.ile = new Tuile[6][6];
    }

    public static Tuile[][] getIle() {
        return ile;
    }
    
    public void addTuile(Tuile t){
        ile[t.getI()][t.getJ()] = t;
    }
    
    
    
}
