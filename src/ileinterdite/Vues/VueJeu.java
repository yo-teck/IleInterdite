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
 * @author Lucas
 */
public class VueJeu implements Observe {

    private JFrame fenetre;
    private JPanel contenuMilieu;
    private JPanel contenuGauche;
    private JPanel contenuDroite;
    private JPanel map;
    private JPanel niveauEau;
    private JPanel zoneBoutons;
    private JPanel contenuBas;
    private JPanel zoneTresors;
    private JPanel zoneCartes;
    private JPanel zoneJoueurs;
    int x = 8;
    int k = 1;
    int ci;
    int cj;
    int y = 0;
 public VueJeu(Grille grille){
     JFrame frame = new JFrame();
        frame.setTitle("Ile Interdite / POO-COO-IHM");
        frame.setSize(900,900);
        frame.setLayout(new BorderLayout());
        JPanel carte = new JPanel();
        JPanel joueurs = new JPanel();
        JButton[] B_joueurs = new JButton[4];
        
        /////////////////////////////////////////////////////////////////////// Fenetre de demarrage
        
       /* JButton debut = new JButton("Démarrer la partie");
        frame.add(debut,BorderLayout.SOUTH);*/
       
        String[] roles = new String[] {"Explorateur","Plongeur","Navigateur","Pilote","Ingénieur","Messager" };
        JPanel choix = new JPanel();/*
        
        choix.setLayout(new GridLayout(5,2));
        
        choix.add(new JLabel("Bienvenue dans l'île interdite veuillez choisir 4 rôles : "));
        choix.add(new JLabel(""));
        frame.add(choix,BorderLayout.NORTH);
        for(int i = 1; i <= 4 ; i++){
            
            choix.add(new JLabel("Joueur"+ i +" : "));
            choix.add(new JComboBox(roles));
            //On ne choisis pas mais utili pour la demo.
            //Pour recupérer le choix, il faute faire 4 jCombobox différentes avec des nom de variable, puis .IsSelected();
        }
        /*debut.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            

            carte.setVisible(true);

            debut.setVisible(false);
        
            JButton finT = new JButton("Fin tour");
            frame.add(finT,BorderLayout.SOUTH);
            B_joueurs[0].setBackground(Color.PINK);
            finT.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            
             B_joueurs[y].setBackground(Color.WHITE);            
             y++;
              if(y == 4){
                 B_joueurs[y-1].setBackground(Color.WHITE);
                 y = 0;
                 B_joueurs[y].setBackground(Color.PINK);
              }
              
              else{
                   B_joueurs[y].setBackground(Color.PINK);
   
              }
              
              
             
             
             
         }
         
         });
        }
            
       });*/
        
       
        
     
        
        ///////////////////////////////////////////////////////////////////////
        
        ////////////////////////////////////////////////////////////////////// Coté niveau eau
        JPanel nivoO = new JPanel();
        
        nivoO.setLayout(new GridLayout(13,1));
        JButton[] n_Eau = new JButton[10];
        JLabel txtO = new JLabel("Niveau d'eau : " + k);
        nivoO.add(txtO);
        for(int i = 0 ; i < 10 ;i++){ //////////////////////Initialisation de la barre d'eau
            
            n_Eau[i] = new JButton(" ");
            n_Eau[i].setEnabled(false);
            n_Eau[i].setBackground(Color.white);
            nivoO.add(n_Eau[i]);
            if (i == 9){
                n_Eau[i].setBackground(Color.blue);
            }
            
        }
        JButton monterLvl = new JButton("Monter niveau eau");
        JButton baisserLvl = new JButton("Baisser niveau eau");
        nivoO.add(monterLvl);
        nivoO.add(baisserLvl);
        
        monterLvl.addActionListener(new ActionListener(){ ///////////Augmentation de l'eau
         @Override
         public void actionPerformed(ActionEvent e) {
           if(x > 0){
             n_Eau[x].setBackground(Color.blue);
             x--;
             k++;
             txtO.setText("Niveau d'eau : " + k);
             
           }
           else if (x == 0){
                  n_Eau[x].setBackground(Color.blue);
           }
           else{
                 System.out.println("Le niveau d'eau est au maximum !");
           }
         }

        });
        
         baisserLvl.addActionListener(new ActionListener(){ /////////////////Baisse de l'eau
         @Override
         public void actionPerformed(ActionEvent e) {
             if(x < 9){
            n_Eau[x].setBackground(Color.white);
            x++; // Variable servant a augmenter ou baisser le niveau d'eau
            k--; // Variable indiquant le niveau d'eau
            txtO.setText("Niveau d'eau : " + k); // Mise a jour du texte indiquant le niveau d'eau
             }
             else if (x == 9){
                  n_Eau[x].setBackground(Color.white);
           }
             else{
                 System.out.println("Le niveau d'eau est au minimum !");
             }
         }

        });
         
        frame.add(nivoO,BorderLayout.WEST);
       

        ////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////////////////////////////////// Coté carte
        
        carte.setLayout(new GridLayout(6,6));
        JButton[] T_Map = new JButton[36];
        ci =0;
        cj =0;
        for(int i = 0; i < 36; i++){ // Boucle afin d'ajouter tout les boutons de la grille 
               T_Map[i] = new JButton();
               Tuile tuileSelect = grille.getTuile(ci, cj);
               T_Map[i].setText(tuileSelect.getNom());
               carte.add(T_Map[i]);
               if(i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i ==11 || i ==24 || i ==29 || i==30 || i ==31 || i==34 || i==35){
                   T_Map[i].setEnabled(false); // Cases null non cliquable
                   T_Map[i].setText(""); // Nom eau sur les cases nulls
                   T_Map[i].setBackground(Color.WHITE); //Couleur fond
                   T_Map[i].setForeground(Color.WHITE); // Couleur front
               }
               else{
                   T_Map[i].addActionListener(
                           new ActionListener(){
                               @Override 
                               public void actionPerformed(ActionEvent e) {
                                   System.out.println("Toute les tuiles en croix :");
                                   for (Tuile t : grille.getTuilesCroix(tuileSelect)){
                                       System.out.println(t.getNom());
                                   }
                                   System.out.println("");
                                   System.out.println("Celles plongeur :");
                                   for (Tuile t : grille.getTuilesDispoPourDeplacement(grille, tuileSelect)){
                                       System.out.println(t.getNom());
                                   }
                                System.out.println("");  
                               } 
                           }
                   );
                   if (tuileSelect.getEtat()==Etat.INONDE){
                        T_Map[i].setBackground(new Color(119, 181, 254));                       
                   }else if (tuileSelect.getEtat()==Etat.SUBMERGE){
                        T_Map[i].setBackground(new Color(34, 66, 124));
                   }else{
                        T_Map[i].setBackground(new Color(136, 66, 29));
                   }

                   //T_Map[i].setForeground(Color.green);
               }
               cj++;               
               if (cj==6){
                    ci++;
                    cj =0;
               }
;
        }
       // fenetre.add(map);
        carte.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        frame.add(carte);
        frame.setVisible(true);
        
        
        
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
        
 }
 ////////////////////////////////////////////////////////////////////////////////////////////////// FIN CONSTRUCTEUR SANS PARAMETRES
 
 
 
 
 
 
 
 
 
    public VueJeu(Grille g, NiveauEau lvlEau, ArrayList<Pion> pions) {
        fenetre = new JFrame("Ile Interdite");
        this.configureWindow(fenetre);

        contenuMilieu = new JPanel();
        contenuMilieu.setLayout(new BorderLayout());
        
        contenuGauche = new JPanel();
        contenuGauche.setLayout(new BorderLayout());
        
        contenuDroite = new JPanel();
        contenuDroite.setLayout(new BorderLayout());

        map = new JPanel();
        map.setLayout(new GridLayout(6, 6));
        contenuMilieu.add(map, BorderLayout.CENTER);

        niveauEau = new JPanel();
        niveauEau.setLayout(new GridLayout(10, 1));
        contenuGauche.add(niveauEau, BorderLayout.CENTER);

        zoneBoutons = new JPanel();
        zoneBoutons.setLayout(new GridLayout(12, 3));
        contenuDroite.add(zoneBoutons, BorderLayout.CENTER);

        contenuBas = new JPanel();
        contenuBas.setLayout(new GridLayout(1, 3));
        contenuMilieu.add(contenuBas, BorderLayout.SOUTH);

        zoneTresors = new JPanel();
        zoneTresors.setLayout(new GridLayout(2, 2));
        contenuBas.add(zoneTresors);

        zoneCartes = new JPanel();
        zoneCartes.setLayout(new GridLayout(5, 3));
        contenuBas.add(zoneCartes);

        zoneJoueurs = new JPanel();
        zoneJoueurs.setLayout(new GridLayout(4, 2));
        contenuBas.add(zoneJoueurs);

        JLabel nbActions = new JLabel("Nombre d'actions restantes : ");

        JButton dplaC = new JButton("Se déplacer");
        JButton asseche = new JButton("Assécher");
        JButton finT = new JButton("Fin du tour");
        JButton utilCAJ = new JButton("Utiliser Carte D'un autre joueur");
        JButton recupT = new JButton("Récupérer Trésor");

        /* // CARTE = BOUTONS 
        JButton utilC = new JButton("Utiliser Carte");
         */
        // A CREER 6 FOIS
        JButton donCarte = new JButton("Donner Carte");
        //
        
        for(int i = 0; i < 6; i++){
            for(int j = 0 ; i < 6; j++){
                map.add(new JButton(g.getTuile(i, j).getNom()));
            }
        }

        fenetre.setVisible(true);
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
