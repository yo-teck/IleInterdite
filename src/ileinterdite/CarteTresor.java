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
public class CarteTresor {
    
    private CTresor type;
    private static int id = 1; // Total de carte
    private int idCarte;    
    private String description;

    public CarteTresor(CTresor type) {
        setType(type);
        idCarte = id;
        setDescription(type);
        id++;
    }

    public CTresor getType() {
        return type;
    }

    public void setType(CTresor type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(CTresor type) {
        if(type==CTresor.SAC_SABLE){
            this.description = "Permet d'assécher une case inondée.";            
        }
        else if (type == CTresor.HELICO){
            this.description = "Permet de voler n'importe où seul ou en groupe. NB: Nécessaire pour utiliser l'héliport et terminer la partie.";
        }
        else if(type == CTresor.CLE_FEU || type == CTresor.CLE_TERRE || type == CTresor.CLE_AIR || type == CTresor.CLE_EAU ){
            this.description = "Réunissez 4 clés du même type et utilisez les sur une tuile du même type afin de récuperer le trésor correspondant.";
        }
        else if (type == CTresor.MONTEE_DES_EAUX){
            this.description = "Montez le niveau d'eau et remettez les cartes inondation dans la pile des cartes tuile.";
        }
    
    
    

    }
}
