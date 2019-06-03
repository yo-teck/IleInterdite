/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.Vues;

import ileinterdite.Grille;
import ileinterdite.Message;
import ileinterdite.NiveauEau;
import ileinterdite.Observateur;
import ileinterdite.Observe;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.Pion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Yoann
 */
public class VueGrille implements Observe{
    
    
        private JFrame fenetre;
    private JPanel conteneurMilieu;
    private JPanel conteneurGauche;
    private JPanel conteneurDroite;

    private JPanel niveauEau;
    private JPanel zoneBoutons;
    private JPanel conteneurBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;
    int ci;
    int cj;

    
 public VueGrille (Grille grille, NiveauEau niveauEau) {
     JFrame frame = new JFrame();
        frame.setTitle("Ile Interdite");
        frame.setSize(1400,800);
        frame.setLayout(new BorderLayout());
        JPanel conteneurTuile = new JPanel();
        JPanel joueurs = new JPanel();
        JButton[] B_joueurs = new JButton[4];
        
        /////////////////////////////////////////////////////////////////////// Fenetre de demarrage
       
        String[] roles = new String[] {"Explorateur","Plongeur","Navigateur","Pilote","Ingénieur","Messager" };
        JPanel choix = new JPanel();        
        
        ///////////////////////////////////////////////////////////////////////
        
        ////////////////////////////////////////////////////////////////////// Coté niveau eau
        JPanel conteneurNivEau = new JPanel();
                
        conteneurNivEau.setLayout(new GridLayout(11,1));
        JButton[] n_Eau = new JButton[10];
        
        JLabel txtO = new JLabel("Niveau d'eau : " + niveauEau.getNiveau());
        conteneurNivEau.add(txtO);
        for(int i =0 ; i < 10 ;i++){ //////////////////////Initialisation de la barre d'eau
            
            n_Eau[i] = new JButton(" ");
            n_Eau[i].setEnabled(false);
            
            if (i >= 10-niveauEau.getNiveau()){
                n_Eau[i].setBackground(Color.blue);
            }else{
                n_Eau[i].setBackground(Color.white);
            }
            
            conteneurNivEau.add(n_Eau[i]);
    
        }   
        
        frame.add(conteneurNivEau,BorderLayout.WEST);
       

        ////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////////////////////////////////// Coté carte
        
        conteneurTuile.setLayout(new GridLayout(6,6));
        conteneurTuile.setPreferredSize(new Dimension(1300,700));
        JButton[] Tuile = new JButton[36];
        ci =0;
        cj =0;
        for(int i = 0; i < 36; i++){ // Boucle afin d'ajouter tout les boutons de la grille 
               Tuile[i] = new JButton();
               Tuile tuileSelect = grille.getTuile(ci, cj);
               Tuile[i].setText(tuileSelect.getNom());
               conteneurTuile.add(Tuile[i]);
               if(i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i ==11 || i ==24 || i ==29 || i==30 || i ==31 || i==34 || i==35){
                   Tuile[i].setEnabled(false); // Cases null non cliquable
                   Tuile[i].setText(""); // Nom eau sur les cases nulls
                   Tuile[i].setBackground(Color.WHITE); //Couleur fond
                   Tuile[i].setForeground(Color.WHITE); // Couleur front
               }
               else{
                   Tuile[i].addActionListener(
                           new ActionListener(){
                               @Override 
                               public void actionPerformed(ActionEvent e) {
                                   System.out.println("Deplacement standart :");
                                   for (Tuile t : grille.getNonSubmerge(grille.getTuilesCroix(tuileSelect))){
                                       System.out.print(t.getNom()+", ");
                                   }
                                   
                                   System.out.println("");
                                   
                                   System.out.println("Deplacement explorateur :");
                                   for (Tuile t : grille.getNonSubmerge(grille.getTuilesCroix(tuileSelect))){
                                       System.out.print(t.getNom()+", ");
                                   }
                                   for (Tuile t : grille.getNonSubmerge(grille.getTuilesDiagonale(tuileSelect))){
                                       System.out.print(t.getNom()+", ");
                                   }
                                   
                                   System.out.println("");
                                   
                                   System.out.println("Celles plongeur :");
                                   for (Tuile t : grille.getTuilesDispoPourDeplacement(grille, tuileSelect)){
                                       System.out.print(t.getNom()+", ");
                                   }
                                System.out.println("");
                                System.out.println("");  
                               } 
                           }
                   );
                   
                   if (tuileSelect.getEtat()==Etat.INONDE){
                        Tuile[i].setBackground(new Color(119, 181, 254));                       
                   }else if (tuileSelect.getEtat()==Etat.SUBMERGE){
                        Tuile[i].setBackground(new Color(34, 66, 124));
                   }else{
                        Tuile[i].setBackground(new Color(136, 66, 29));
                   }

               }
               cj++;               
               if (cj==6){
                    ci++;
                    cj =0;
               };
        }
        
        conteneurTuile.setSize(900, 900);
       // fenetre.add(map);
        conteneurTuile.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        conteneurBas = new JPanel(new BorderLayout());

        //Creation temporaire des trophées
        zoneTresors = new JPanel();
        
        zoneTresors.setLayout(new GridLayout(2,2));
        
        JButton tresorEau = new JButton("Eau");
        tresorEau.setBackground(Color.lightGray);
        tresorEau.setEnabled(false);
        zoneTresors.add(tresorEau);
        
        JButton tresorFeu = new JButton("Feu");
        tresorFeu.setBackground(Color.lightGray);
        tresorFeu.setEnabled(false);        
        zoneTresors.add(tresorFeu);
        
        JButton tresorAir = new JButton("Air");
        tresorAir.setBackground(Color.lightGray);
        tresorAir.setEnabled(false);
        zoneTresors.add(tresorAir);
        
        JButton tresorTerre = new JButton("Terre");
        tresorTerre.setBackground(Color.lightGray);
        tresorTerre.setEnabled(false);
        zoneTresors.add(tresorTerre);
        
        conteneurBas.add(zoneTresors,BorderLayout.WEST);
        
        
        //Création temporaire zone carte
        
        zoneCartes = new JPanel();        
        
        zoneCartes.setLayout(new GridLayout(1,5));
        JButton carte;
        for (int i = 0; i<5;i++){
            carte = new JButton(""+i);
            zoneCartes.add(carte);
        }
        
        conteneurBas.add(zoneCartes,BorderLayout.CENTER);

        
        conteneurBas.setPreferredSize(new Dimension(1300,100));
        frame.add(conteneurBas,BorderLayout.SOUTH);
       /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
       
       //////////////////////////////////////////////////////////////////////////////////// Fenetre joueurs choisis
        
       
        joueurs.setLayout(new GridLayout(20,2));
        joueurs.add(new JLabel("Rôle des joueurs :"));
        joueurs.add(new JLabel(""));
       
        ArrayList<String> AL_roles = new ArrayList<>();
        for(int i = 0; i < 6 ; i++){
            AL_roles.add(roles[i]);
        }
        
        Collections.shuffle(AL_roles);
        for(int i = 0 ; i < 4 ;i ++){
            B_joueurs[i] = new JButton("Joueur" + (i+1) + " : ");
            B_joueurs[i].setBackground(Color.white);
            joueurs.add(B_joueurs[i]);
            joueurs.add(new JLabel(AL_roles.get(i)));
            
        }
        joueurs.setVisible(true);
        frame.add(joueurs,BorderLayout.EAST);
        
        
        
        frame.add(conteneurTuile);
        frame.setVisible(true);
 }
    
        public JPanel lolxD(){
            return new JPanel();
        }
        private void configureWindow(JFrame window) {
        window.setSize(500, 200);
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowListener() {
            public void windowOpened(java.awt.event.WindowEvent e) {
            }

            public void windowClosed(java.awt.event.WindowEvent e) {
            }

            public void windowIconified(java.awt.event.WindowEvent e) {
            }

            public void windowDeiconified(java.awt.event.WindowEvent e) {
            }

            public void windowActivated(java.awt.event.WindowEvent e) {
            }

            public void windowDeactivated(java.awt.event.WindowEvent e) {
            }

            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
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
