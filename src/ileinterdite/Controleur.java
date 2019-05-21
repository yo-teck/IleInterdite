/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

/**
 *
 * @author gherrazs
 */
public class Controleur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Tuile tuile1 = new Tuile(Etat.SEC, Evenement.SPAWN_MESSAGER, 5, 5, "tuile a");
         Messager messager = new Messager("Messager","xhvgdvfgdf",tuile1);
        // messager.setJoueur();
        
    }
    
}
