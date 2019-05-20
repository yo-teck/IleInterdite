/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

        
    import java.util.ArrayList;
/**
 *
 * @author dusema
 */
public class Tuile {
    
  private Etat etat;
  private Evenement event;
  private int x;
  private int y;
  private String nom;
  private ArrayList<Joueur> joueurs;

    public Tuile(Etat etat, Evenement event, int x, int y, String nom) {
        this.setEtat(etat);
        this.setEvent(event);
        this.setX(x);
        this.setY(y);
        this.setNom(nom);
        joueurs = new ArrayList<>();
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  
  
  
    
}
