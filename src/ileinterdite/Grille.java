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

   private Tuile[][] ile;

   public Grille() {
        setIle(new Tuile[6][6]);
    }

    public Tuile[][] getIle() {
        return ile;
    }

    public void setIle(Tuile[][] ile) {
        this.ile = ile;
    }

    public void addTuile(Tuile t) {
        ile[t.getI()][t.getJ()] = t;
    }

    public void addTuile(Tuile t, int i, int j) {
        ile[i][j] = t;
        t.setI(i);
        t.setJ(j);
    }

    public Tuile getTuile(int i, int j) {
        return ile[i][j];
    }
    
    public ArrayList<Tuile> getTuiles(){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for(int i = 0; i<6; i++){
            for(int j = 0; j<6; j++){
                tuiles.add(ile[i][j]);
            }
        }
        return tuiles;
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
        tAC.add(t);      
        return tAC;

    }

    public ArrayList<Tuile> getTuilesDiagonale(Tuile t) {
        ArrayList<Tuile> tAD = new ArrayList<>();

        if ( ((t.getI()+1)<=5 && (t.getJ()+1)<=5) && getTuile(t.getI()+1, t.getJ()+1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()+1, t.getJ()+1));
        }
        if ( (t.getI()-1>=0 && t.getJ()-1>=0) && getTuile(t.getI()-1, t.getJ()-1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()-1, t.getJ()-1));
        }
        if ( (t.getI()+1<=5 && t.getJ()-1>=0) && getTuile(t.getI()+1, t.getJ()-1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()+1, t.getJ()-1));
        }
        if ( (t.getI()-1>=0 && t.getJ()+1<=5) && getTuile(t.getI()-1, t.getJ()+1).getEtat()!=Etat.NULL){
            tAD.add(getTuile(t.getI()-1, t.getJ()+1));
        }               

        return tAD;
    }

    public ArrayList<Tuile> getNonSubmerge(ArrayList<Tuile> tAC) {
        ArrayList<Tuile> tns = new ArrayList<>();
        for (Tuile tuile : tAC) {
            if (tuile.getEtat() != Etat.SUBMERGE && tuile.getEtat() != Etat.NULL) {
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
            if (tuile.getEtat() == Etat.INONDE) {
                ti.add(tuile);
            }
        }
        return ti;
    }
     
     
     
     public ArrayList<Tuile> getSubInn(ArrayList<Tuile> tuiles){
         ArrayList<Tuile> temp = new ArrayList<>();
         temp=getSubmerge(tuiles);
         temp.addAll(getTuilesInnondees(tuiles));
         return temp;
     }
     /* dans le plongeur
        public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuileValable =new ArrayList<>();
        ArrayList<Tuile> tuileSubmerger =new ArrayList<>();
        tuileValable = grille.getTuilesCroix(tuile);

        while (! tuileSubmerger.containsAll(grille.getSubmerge(tuileValable))){
            for (Tuile tuileS : grille.getSubmerge(tuileValable)){
                if ( ! tuileSubmerger.contains(tuileS)){
                    tuileSubmerger.add(tuileS);
                    for (Tuile tuileNew : grille.getTuilesCroix(tuileS)){
                        if (! tuileValable.contains(tuileNew)){
                            tuileValable.add(tuileNew);
                        }
                   }
                }
            }
        }       
        tuileValable.remove(tuile);
        return grille.getNonSubmerge(tuileValable);
    }

    */
}
