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
    private ArrayList<CarteTresor> cartesTresor;
    private Pion pion;
    private ArrayList<String> nomJoueurs;
    private String difficulte;
    private String modeInitialisation;
    private ArrayList<Pion> pions;
    private OTresor objetTresor;

    public OTresor getObjetTresor() {
        return objetTresor;
    }

    public void setObjetTresor(OTresor objetTresor) {
        this.objetTresor = objetTresor;
    }

    public Message(TypesMessage type) {
        setType(type);
        this.cartesTresor = new ArrayList<>();
    }

    public Message(TypesMessage type, Pion pion) {
        this.type = type;
        this.pion = pion;
        this.cartesTresor = new ArrayList<>();
    }

    public Message(TypesMessage type, ArrayList<String> nomJoueurs, String difficulte, String modeInitialisation) {
        this.type = type;
        this.nomJoueurs = nomJoueurs;
        this.difficulte = difficulte;
        this.modeInitialisation = modeInitialisation;
        this.cartesTresor = new ArrayList<>();

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

    public ArrayList<Pion> getPions() {
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

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    public void setTypeCarteTresor(CTresor typeCarteTresor) {
        this.typeCarteTresor = typeCarteTresor;
    }

    public void setNomJoueurs(ArrayList<String> nomJoueurs) {
        this.nomJoueurs = nomJoueurs;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public void setModeInitialisation(String modeInitialisation) {
        this.modeInitialisation = modeInitialisation;
    }

    public void setPions(ArrayList<Pion> pions) {
        this.pions = pions;
    }

    public ArrayList<CarteTresor> getCartesTresor() {
        return cartesTresor;
    }

    public void setCartesTresor(ArrayList<CarteTresor> cartesTresor) {
        this.cartesTresor = cartesTresor;
    }

    public void addCarteTresor(CarteTresor carteTresor) {
        this.cartesTresor.add(carteTresor);
    }
}
