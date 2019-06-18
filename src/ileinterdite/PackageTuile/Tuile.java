/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageTuile;

import ileinterdite.Pion;
import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public class Tuile {

    private Etat etat;
    private Evenement event;
    private int i;
    private int j;
    private String nom;
    private ArrayList<Pion> pions;
    private boolean actif;

    //Constructeur pour tuiles hors île (placement manuel)
    public Tuile(Etat etat, int i, int j) {
        if (etat == etat.NULL) {
            this.setEtat(etat);
            this.setEvent(event.RIEN);
            this.setI(i);
            this.setJ(j);
            this.setNom("");
        }
        this.actif = false;
        pions = new ArrayList<>();
    }

    //Constructeur pour tuile à placement manuel
    public Tuile(Etat etat, Evenement event, int i, int j, String nom) {
        this.setEtat(etat);
        this.setEvent(event);
        this.setI(i);
        this.setJ(j);
        this.setNom(nom);
        pions = new ArrayList<>();
        this.actif = false;
    }

    //Constructeur pour tuile hors île à placement aléatoire
    public Tuile(Etat etat) {
        if (etat == etat.NULL) {
            this.setEtat(etat);
            this.setEvent(event.RIEN);
            this.setNom("");
        }
        this.actif = false;
        pions = new ArrayList<>();
    }

    //Constructeur pour partie à placement aléatoire
    public Tuile(Etat etat, Evenement event, String nom) {
        this.setEtat(etat);
        this.setEvent(event);
        this.setNom(nom);
        pions = new ArrayList<>();
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addPion(Pion pion) {
        pions.add(pion);
    }
    public void removePion(Pion pion) {
        pions.remove(pion);
    }
    public ArrayList<Pion> getPions() {
        return pions;
    }
}
