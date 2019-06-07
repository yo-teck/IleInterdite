/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.PackageTuile.Tuile;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Message {

    private TypesMessage type;
    private Tuile tuile;
    private CTresor typeCarteTresor;
    private CarteTresor carteTresor;
    private Pion pion;
    private ArrayList<String> nomJoueurs;
    private String difficulte;
    private String modeInitialisation;
    private ArrayList<Pion> pions;

    public Message(TypesMessage type) {
        setType(type);
    }

    public Message(TypesMessage type, Pion pion) {
        this.type = type;
        this.pion = pion;
    }

    public Message(TypesMessage type, ArrayList<String> nomJoueurs, String difficulte, String modeInitialisation) {
        this.type = type;
        this.nomJoueurs = nomJoueurs;
        this.difficulte = difficulte;
        this.modeInitialisation = modeInitialisation;
    }

    public void setType(TypesMessage type) {
        this.type = type;
    }

    public TypesMessage getType() {
        return type;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public CTresor getTypeCarteTresor() {
        return typeCarteTresor;
    }

    public Pion getAmi() {
        return pion;
    }

    public ArrayList<String> getNomJoueurs() {
        return nomJoueurs;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public String getModeInitialisation() {
        return modeInitialisation;
    }
    
    public ArrayList<Pion> getPions(){
        return pions;
    }

    public CarteTresor getCarteTresor() {
        return carteTresor;
    }

    public void setCarteTresor(CarteTresor carteTresor) {
        this.carteTresor = carteTresor;
    }

    public Pion getPion() {
        return pion;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    
}
