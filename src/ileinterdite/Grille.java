/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageTuile.Tuile;
import ileinterdite.PackageTuile.Etat;
import java.util.ArrayList;

/**
 *
 * @author dusema
 */
public class Grille {

    private static Tuile[][] ile;

   public Grille() {
        setIle(new Tuile[6][6]);
    }

    public static Tuile[][] getIle() {
        return ile;
    }

    public static void setIle(Tuile[][] ile) {
        Grille.ile = ile;
    }

    public void addTuile(Tuile t) {
        ile[t.getI()][t.getJ()] = t;
    }

    public void addTuile(Tuile t, int i, int j) {
        ile[i][j] = t;
    }

    public Tuile getTuile(int i, int j) {
        return ile[i][j];
    }

    public ArrayList<Tuile> getTuilesCroix(Tuile t) {
        ArrayList<Tuile> tAC = new ArrayList<>();
        
        if (t.getI()+1<=5 && getTuile(t.getI()+1, t.getJ()).getEtat()!=Etat.NULL){
            tAC.add(getTuile(t.getI()+1, t.getJ()));
        }
        if (t.getI()-1>=0 && getTuile(t.getI()-1, t.getJ()).getEtat()!=Etat.NULL){
            tAC.add(getTuile(t.getI()-1, t.getJ()));
        }        
        if (t.getJ()+1<=5 && getTuile(t.getI(), t.getJ()+1).getEtat()!=Etat.NULL){
            tAC.add(getTuile(t.getI(), t.getJ()+1));
        }
        if (t.getJ()-1>=0 && getTuile(t.getI(), t.getJ()-1).getEtat()!=Etat.NULL){
            tAC.add(getTuile(t.getI(), t.getJ()-1));
        }        
               
        return tAC;

    }

    public ArrayList<Tuile> getTuilesDiagonale(Tuile t) {
        ArrayList<Tuile> tAD = new ArrayList<>();
        
        if ( (t.getI()+1<=5 || t.getJ()+1<=5) && getTuile(t.getI()+1, t.getJ()+1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()+1, t.getJ()+1));
        }
        if ( (t.getI()-1>=0 || t.getJ()-1>=0) && getTuile(t.getI()-1, t.getJ()-1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()-1, t.getJ()-1));
        }
        if ( (t.getI()+1<=5 || t.getJ()-1>=0) && getTuile(t.getI()+1, t.getJ()-1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()+1, t.getJ()-1));
        }
        if (t.getI()-1>=0 || t.getJ()+1<=5 && getTuile(t.getI()-1, t.getJ()+1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()-1, t.getJ()+1));
        }               
        
        return tAD;
    }

    public ArrayList<Tuile> getNonSubmerge(ArrayList<Tuile> tAC) {
        ArrayList<Tuile> tns = new ArrayList<>();
        for (Tuile tuile : tAC) {
            if (tuile.getEtat() != Etat.SUBMERGE) {
                tns.add(tuile);
            }
        }
        return tns;
    }

    public ArrayList<Tuile> getSubmerge(ArrayList<Tuile> tAC) {
        ArrayList<Tuile> ts = new ArrayList<>();
        for (Tuile tuile : tAC) {
            if (tuile.getEtat() == Etat.SUBMERGE) {
                ts.add(tuile);
            }
        }
        return ts;
    }
    
     public ArrayList<Tuile> getTuilesInnondees(ArrayList<Tuile> tAC) {
        ArrayList<Tuile> ti = new ArrayList<>();         
        for (Tuile tuile : tAC) {
            if (tuile.getEtat() != Etat.SUBMERGE || tuile.getEtat() != Etat.SEC) {
                ti.add(tuile);
            }
        }
        return ti;
    }
    

    
}
