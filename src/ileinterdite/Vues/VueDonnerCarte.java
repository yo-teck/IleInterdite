/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Message;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.Pion;
import ileinterdite.TypesMessage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author richomml
 */
public class VueDonnerCarte implements Observe {
    private JFrame fenetre;
    
    private JPanel conteneurJoueurs;
    private JPanel conteneurCartes;
    
    private JLabel labelJoueurs;
    private JLabel labelCartes;
    
    private JRadioButton[] boutonsJoueurs;
    private JRadioButton[] boutonsCartes;
    
    private ButtonGroup groupeJoueurs;
    private ButtonGroup groupeCartes;
    
    private JButton btnValider;
    
    public VueDonnerCarte(Pion pionActif, ArrayList<Pion> pions){
        fenetre = new JFrame(pionActif.getNomj() + " - Donner une carte");
        fenetre.setLayout(new BorderLayout());
        fenetre.setSize(500,500);
        
        //On enlève le joueur actif de l'ArrayList des pions car il ne peut pas se donner de carte à lui-même
        for(Pion pion : pions){
            if(pion == pionActif){
                pions.remove(pion);
            }
        }
        
        //Création de la zone de choix du joueur
        conteneurJoueurs = new JPanel(new GridLayout(pions.size(), 1));
        
        labelJoueurs = new JLabel("Choisissez un joueur :");
        conteneurJoueurs.add(labelJoueurs);
        
        boutonsJoueurs = new JRadioButton[pions.size()];
        groupeJoueurs = new ButtonGroup();
        int i = 0;
        for(Pion pion : pions){
            JRadioButton nouveauBouton = new JRadioButton(pion.getNomj());         
            groupeJoueurs.add(nouveauBouton);
            boutonsJoueurs[i]=nouveauBouton;
            conteneurJoueurs.add(boutonsJoueurs[i]);
            i++;
        }
        
        fenetre.add(conteneurJoueurs, BorderLayout.WEST);
        
        //Création de la zone de choix de la carte à envoyer
        conteneurCartes = new JPanel(new GridLayout(pionActif.getNbCartes(), 1));
        
        labelCartes = new JLabel("Choisissez une carte :");
        conteneurCartes.add(labelCartes);
        
        boutonsCartes = new JRadioButton[pionActif.getNbCartes()];
        groupeCartes = new ButtonGroup();
        i = 0;
        for(CarteTresor ct : pionActif.getCartesTresors()){
            JRadioButton nouveauBouton = new JRadioButton(ct.getType().toString());
            groupeCartes.add(nouveauBouton);
            boutonsCartes[i] = nouveauBouton;
            conteneurCartes.add(boutonsCartes[i]);
            i++;
        }
        
        fenetre.add(conteneurCartes, BorderLayout.EAST);
        
        //Ajout bouton valider
        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Message m = new Message(TypesMessage.DONNER_CARTE);
                int i = 0;
                while(!boutonsCartes[i].isSelected()){
                    i++;
                }      
                m.setCarteTresor(pionActif.getCartesTresors().get(i));
                
                i = 0;
                while(!boutonsJoueurs[i].isSelected()){
                    i++;
                }
                m.setPion(pions.get(i));
                notifierObservateur(m);
            }
        });
        fenetre.add(btnValider, BorderLayout.SOUTH);
    }

    private Observateur observateur;

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
