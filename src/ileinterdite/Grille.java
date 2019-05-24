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
public class Grille {
    
    private static Tuile[][] ile;
    
    Grille(){
        setIle(new Tuile[6][6]);
    }

    public static Tuile[][] getIle() {
        return ile;
    }

    public static void setIle(Tuile[][] ile) {
        Grille.ile = ile;
    }
    
    public void addTuile(Tuile t){
        ile[t.getI()][t.getJ()] = t;
    }
    
    public void addTuile(Tuile t, int i, int j){
        ile[i][j]=t;
    }
    
    public Tuile getTuile(int i, int j) {
        return ile[i][j];
    }
    
    
    public ArrayList<Tuile> getTuilesCroix(Tuile t){
        ArrayList<Tuile> tAC = new ArrayList<>();
        int droite = t.getJ()+1; //Tuile à droite de l'origine
        int gauche = t.getJ()-1; //Tuile à gauche de l'origine
        int haut = t.getI()+1; //Tuile en haut de l'origine
        int bas = t.getI()-1; //Tuile en bas de l'origine
        
        //Ligne 1 et 6
        if (t.getI()==0) {
            tAC.add(getTuile(bas, t.getI()));
            tAC.add(getTuile(t.getJ(), droite));
            tAC.add(getTuile(t.getJ(), gauche));            
        }
        if (t.getI()==5){
            tAC.add(getTuile(haut, t.getI()));
            tAC.add(getTuile(t.getJ(), droite));
            tAC.add(getTuile(t.getJ(), gauche));             
        } 
        //Ligne 2 et 5
        if (t.getI()==1) {
            tAC.add(getTuile(haut, t.getI()));            
            tAC.add(getTuile(bas, t.getI()));
            tAC.add(getTuile(t.getJ(), droite));
            tAC.add(getTuile(t.getJ(), gauche));            
        }
        if (t.getI()==4){
            tAC.add(getTuile(haut, t.getI()));
            tAC.add(getTuile(bas, t.getI()));            
            tAC.add(getTuile(t.getJ(), droite));
            tAC.add(getTuile(t.getJ(), gauche));             
        }
        //Ligne 3 et 4
        if (t.getI()==2) {
            tAC.add(getTuile(haut, t.getI()));            
            tAC.add(getTuile(bas, t.getI()));
            if(droite  <= 5){
                tAC.add(getTuile(t.getJ(), droite));                
            }
            if(gauche >= 0){
                tAC.add(getTuile(t.getJ(), gauche));                  
            }    
        }
        if (t.getI()==3){
            tAC.add(getTuile(haut, t.getI()));
            tAC.add(getTuile(bas, t.getI()));            
            if(droite  <= 5){
                tAC.add(getTuile(t.getJ(), droite));                
            }
            if(gauche >= 0){
                tAC.add(getTuile(t.getJ(), gauche));                  
            }               
        }             
        return tAC;
    }
    
    public ArrayList<Tuile> getTuilesDiagonale(Tuile t){
        ArrayList<Tuile> tAD = new ArrayList<>();
        int droite = t.getI()+1;
        int gauche = t.getI()-1;
        int haut = t.getJ()+1;
        int bas = t.getJ()-1;
        
        if(droite  < 6 && gauche <= 0 && haut >=0 && bas < 6){
            // Lignes 1 et 6
            if(t.getJ() == 0){
                tAD.add(getTuile(gauche,bas));
                tAD.add(getTuile(droite,bas));
            }
            else if(t.getJ() == 5){
                tAD.add(getTuile(droite,haut));
                tAD.add(getTuile(gauche,haut));
            }
            
            if(gauche > 1 && t.getJ() == 0 ){
                tAD.add(getTuile(gauche,bas));
                tAD.add(getTuile(droite,bas)); 
            }
            if(gauche > 1 && t.getJ() == 5 ){
                tAD.add(getTuile(gauche,haut));
                tAD.add(getTuile(droite,haut)); 
            }
            ///////////////////////////////////////////////////////// A COMPLETER A PARTIR D'ICI
            if(droite < 4 && t.getJ() == 0 || t.getJ() == 5){
                tAD.add(getTuile(droite,t.getJ()));             
            }
            
            // Lignes 2 et 5
            if(t.getJ() == 1 || t.getJ()== 4){
                tAD.add(getTuile(t.getI(),haut));
                tAD.add(getTuile(t.getI(),bas)); 
            }
            
            if(gauche > 0 && t.getJ() == 1 || t.getJ() == 4 ){
                tAD.add(getTuile(gauche,t.getJ()));             
            }
            
            if(droite < 5 && t.getJ() == 1 || t.getJ() == 4){
                tAD.add(getTuile(droite,t.getJ()));             
            }
            
            // Lignes 3 et 4
            if(t.getJ() == 2 || t.getJ() == 3){
                tAD.add(getTuile(t.getI(),haut));
                tAD.add(getTuile(t.getI(),bas)); 
            }
            
            if(gauche == 0 && t.getJ() == 2 || t.getJ() == 3 ){
                tAD.add(getTuile(gauche,t.getJ()));             
            }
            
            if(droite == 5 && t.getJ() == 2 || t.getJ() == 3){
                tAD.add(getTuile(droite,t.getJ()));             
            }
            
        }
        
        
        
        return tAD;
    
    }    
    
}
