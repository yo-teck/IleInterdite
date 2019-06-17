/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author richomml
 */
public class VueDefausse implements Observe {
    private Observateur observateur;
    private JFrame fenetre;
    private JPanel conteneurCartes;
    
    public VueDefausse(Pion pionActif){
        fenetre = new JFrame("Cartes de " + pionActif.getNomj());
        conteneurCartes = new JPanel(new GridLayout(5,2));
        for (CarteTresor c : pionActif.getCartesTresors()){
            JButton btnCarte = new JButton(c.getType().toString());
            btnCarte.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e){                   
                    Message m = new Message(TypesMessage.DEFAUSSE);
                    m.setCarteTresor(c);
                    notifierObservateur(m);                  
                }
            });
            conteneurCartes.add(btnCarte);
        }
        fenetre.add(conteneurCartes);
    }
    
    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }

    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
}
