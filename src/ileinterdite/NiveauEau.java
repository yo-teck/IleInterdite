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
public class NiveauEau {
    private int niveau ;
    

    public NiveauEau(Difficulte diff) {
        setDifficulte(diff);
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
    
    public int getEcheleon(){
        if (getNiveau()<= 2){
            return 2;//Carte piochées
        }
        else if (getNiveau()>=3 && getNiveau() < 6){
            return 3;//Carte piochées
            
        }
        
        else if (getNiveau()>=6 && getNiveau() < 8){
            return 4;//Carte piochées
            
        }
        else if (getNiveau()>=8 && getNiveau() < 10){
            return 5;//Carte piochées
            
        } else /*if (getNiveau()==10)*/ {
             return 6;//FIN DE LA PARTIE
        }    
        //}    
    }
    
    
    public void setDifficulte(Difficulte diff) {
        if (diff== Difficulte.NOVICE){
            setNiveau(1);
        } else if (diff== Difficulte.NORMAL){
            setNiveau(2);
        }else if (diff== Difficulte.ELITE){
            setNiveau(3);
        }else if (diff== Difficulte.LEGENDAIRE){
            setNiveau(4);
        }
    }
    
    
}
