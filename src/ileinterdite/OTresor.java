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
public class OTresor {
    private Tresor type;
    private boolean estRecupere;

    
    public OTresor(Tresor type, boolean estRecupere) {
        setType(type);
        setEstRecupere(estRecupere);
    }

    public Tresor getType() {
        return type;
    }

    public void setType(Tresor type) {
        this.type = type;
    }

    public boolean isEstRecupere() {
        return estRecupere;
    }

    public void setEstRecupere(boolean estRecupere) {
        this.estRecupere = estRecupere;
    }
       
}
