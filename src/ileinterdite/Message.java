/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageTuile.Tuile;

/**
 *
 * @author Lucas
 */
public class Message {
    TypesMessage type;
    Tuile tuile;
    CTresor carte_tresor;
    Pion ami;
    
    public Message(TypesMessage type){
        setType(type);
    }
    
    public void setType(TypesMessage type){
        this.type=type;
    }
}
