/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageCarteTresor.CarteTresor;
import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageTuile.Evenement;
import ileinterdite.PackageTuile.Tuile;
import ileinterdite.PackageTuile.Etat;
import ileinterdite.PackageAventurier.Navigateur;
import ileinterdite.PackageAventurier.Ingenieur;
import ileinterdite.PackageAventurier.Messager;
import ileinterdite.PackageAventurier.Plongeur;
import ileinterdite.PackageAventurier.Explorateur;
import ileinterdite.PackageAventurier.Pilote;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gherrazs
 */
public class Controleur implements Observateur {

    /**
     * @param args the command line arguments
     */
    
    public static Grille ile = new Grille();
    public ArrayList<CarteTresor> pile = new ArrayList<>();
    public ArrayList<CarteTresor> defausse = new ArrayList<>();
    public ArrayList<Tuile> tuilesPiochees = new ArrayList<>();
    public ArrayList<OTresor> tresors = new ArrayList<>();
    public static ArrayList<Pion> pions = new ArrayList<>();
    public NiveauEau niveauEau;
    public VueJeu ihm = new VueJeu(ile,niveauEau,pions);

    public static void initGrilleDemo() {

        //Ligne 0
        Tuile t00 = new Tuile(Etat.NULL, 0, 0);
        Tuile t01 = new Tuile(Etat.NULL, 0, 1);
        Tuile t02 = new Tuile(Etat.SEC, Evenement.RIEN, 0, 2, "Le Pont des Abimes");
        Tuile t03 = new Tuile(Etat.INONDE, Evenement.SPAWN_INGENIEUR, 0, 3, "La Porte de Bronze");
        Tuile t04 = new Tuile(Etat.NULL, 0, 4);
        Tuile t05 = new Tuile(Etat.NULL, 0, 5);
        //Ligne 1
        Tuile t10 = new Tuile(Etat.NULL, 1, 0);
        Tuile t11 = new Tuile(Etat.SEC, Evenement.FEU, 1, 1, "La Caverne des Ombres");
        Tuile t12 = new Tuile(Etat.SEC, Evenement.SPAWN_PLONGEUR, 1, 2, "La Porte de Fer");
        Tuile t13 = new Tuile(Etat.SEC, Evenement.SPAWN_NAVIGATEUR, 1, 3, "La Porte d'Or");
        Tuile t14 = new Tuile(Etat.SEC, Evenement.RIEN, 1, 4, "Les Falaises de l'Oubli");
        Tuile t15 = new Tuile(Etat.NULL, 1, 5);
        //Ligne 2
        Tuile t20 = new Tuile(Etat.SEC, Evenement.EAU, 2, 0, "Le Palais de Corail");
        Tuile t21 = new Tuile(Etat.SEC, Evenement.SPAWN_MESSAGER, 2, 1, "La Porte d'Argent");
        Tuile t22 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 2, 2, "Les Dunes de l'Illusion");
        Tuile t23 = new Tuile(Etat.SEC, Evenement.HELIPORT, 2, 3, "Héliport");
        Tuile t24 = new Tuile(Etat.SEC, Evenement.SPAWN_EXPLORATEUR, 2, 4, "La Porte de Cuivre");
        Tuile t25 = new Tuile(Etat.SEC, Evenement.AIR, 2, 5, "Le Jardin des Hurlements");
        //Ligne 3
        Tuile t30 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 0, "La Foret Pourpre");
        Tuile t31 = new Tuile(Etat.INONDE, Evenement.RIEN, 3, 1, "Le Lagon Perdu");
        Tuile t32 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 3, 2, "Le Marais Brumeux");
        Tuile t33 = new Tuile(Etat.INONDE, Evenement.RIEN, 3, 3, "Observatoire");
        Tuile t34 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 3, 4, "Le Rocher Fantome");
        Tuile t35 = new Tuile(Etat.INONDE, Evenement.FEU, 3, 5, "La Caverne du Brasier");
        //Ligne 4
        Tuile t40 = new Tuile(Etat.NULL, 4, 0);
        Tuile t41 = new Tuile(Etat.SEC, Evenement.TERRE, 4, 1, "Le Temple du Soleil");
        Tuile t42 = new Tuile(Etat.SUBMERGE, Evenement.TERRE, 4, 2, "Le Temple de la Lune");
        Tuile t43 = new Tuile(Etat.SEC, Evenement.EAU, 4, 3, "Le Palais des Marées");
        Tuile t44 = new Tuile(Etat.SEC, Evenement.RIEN, 4, 4, "Le Val du Crépuscule");
        Tuile t45 = new Tuile(Etat.NULL, 4, 5);
        //Ligne 5
        Tuile t50 = new Tuile(Etat.NULL, 5, 0);
        Tuile t51 = new Tuile(Etat.NULL, 5, 1);
        Tuile t52 = new Tuile(Etat.SEC, Evenement.RIEN, 5, 2, "La Tour du Guet");
        Tuile t53 = new Tuile(Etat.INONDE, Evenement.AIR, 5, 3, "Le Jardin des Murmures");
        Tuile t54 = new Tuile(Etat.NULL, 5, 4);
        Tuile t55 = new Tuile(Etat.NULL, 5, 5);

        //Ajout des tuiles dans la grille
        // Ligne 0
        ile.addTuile(t00);
        ile.addTuile(t01);
        ile.addTuile(t02);
        ile.addTuile(t03);
        ile.addTuile(t04);
        ile.addTuile(t05);
        // Ligne 1
        ile.addTuile(t10);
        ile.addTuile(t11);
        ile.addTuile(t12);
        ile.addTuile(t13);
        ile.addTuile(t14);
        ile.addTuile(t15);
        // Ligne 2
        ile.addTuile(t20);
        ile.addTuile(t21);
        ile.addTuile(t22);
        ile.addTuile(t23);
        ile.addTuile(t24);
        ile.addTuile(t25);
        //Ligne 3
        ile.addTuile(t30);
        ile.addTuile(t31);
        ile.addTuile(t32);
        ile.addTuile(t33);
        ile.addTuile(t34);
        ile.addTuile(t35);
        //Ligne 4
        ile.addTuile(t40);
        ile.addTuile(t41);
        ile.addTuile(t42);
        ile.addTuile(t43);
        ile.addTuile(t44);
        ile.addTuile(t45);
        //Ligne 5
        ile.addTuile(t50);
        ile.addTuile(t51);
        ile.addTuile(t52);
        ile.addTuile(t53);
        ile.addTuile(t54);
        ile.addTuile(t55);

    }

    //Initialisation de la grille de manière aléatoire
    public static void initGrilleAleatoire() {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        //Instanciation des tuiles
        Tuile t00 = new Tuile(Etat.NULL);
        Tuile t02 = new Tuile(Etat.SEC, Evenement.RIEN, "Le Pont des Abimes");
        Tuile t03 = new Tuile(Etat.SEC, Evenement.SPAWN_INGENIEUR, "La Porte de Bronze");
        Tuile t11 = new Tuile(Etat.SEC, Evenement.FEU, "La Caverne des Ombres");
        Tuile t12 = new Tuile(Etat.SEC, Evenement.SPAWN_PLONGEUR, "La Porte de Fer");
        Tuile t13 = new Tuile(Etat.SEC, Evenement.SPAWN_NAVIGATEUR, "La Porte d'Or");
        Tuile t14 = new Tuile(Etat.SEC, Evenement.RIEN, "Les Falaises de l'Oubli");
        Tuile t20 = new Tuile(Etat.SEC, Evenement.EAU, "Le Palais de Corail");
        Tuile t21 = new Tuile(Etat.SEC, Evenement.SPAWN_MESSAGER, "La Porte d'Argent");
        Tuile t22 = new Tuile(Etat.SEC, Evenement.RIEN, "Les Dunes de l'Illusion");
        Tuile t23 = new Tuile(Etat.SEC, Evenement.HELIPORT, "Héliport");
        Tuile t24 = new Tuile(Etat.SEC, Evenement.SPAWN_EXPLORATEUR, "La Porte de Cuivre");
        Tuile t25 = new Tuile(Etat.SEC, Evenement.AIR, "Le Jardin des Hurlements");
        Tuile t30 = new Tuile(Etat.SEC, Evenement.RIEN, "La Foret Pourpre");
        Tuile t31 = new Tuile(Etat.SEC, Evenement.RIEN, "Le Lagon Perdu");
        Tuile t32 = new Tuile(Etat.SEC, Evenement.RIEN, "Le Marais Brumeux");
        Tuile t33 = new Tuile(Etat.SEC, Evenement.RIEN, "Observatoire");
        Tuile t34 = new Tuile(Etat.SEC, Evenement.RIEN, "Le Rocher Fantome");
        Tuile t35 = new Tuile(Etat.SEC, Evenement.FEU, "La Caverne du Brasier");
        Tuile t41 = new Tuile(Etat.SEC, Evenement.TERRE, "Le Temple du Soleil");
        Tuile t42 = new Tuile(Etat.SEC, Evenement.TERRE, "Le Temple de la Lune");
        Tuile t43 = new Tuile(Etat.SEC, Evenement.EAU, "Le Palais des Marées");
        Tuile t44 = new Tuile(Etat.SEC, Evenement.RIEN, "Le Val du Crépuscule");
        Tuile t52 = new Tuile(Etat.SEC, Evenement.RIEN, "La Tour du Guet");
        Tuile t53 = new Tuile(Etat.SEC, Evenement.AIR, "Le Jardin des Murmures");

        //Ajout des tuiles dans un ArrayList et mélange aléatoire des tuiles.
        tuiles.add(t02);
        tuiles.add(t03);
        tuiles.add(t11);
        tuiles.add(t12);
        tuiles.add(t13);
        tuiles.add(t14);
        tuiles.add(t20);
        tuiles.add(t21);
        tuiles.add(t22);
        tuiles.add(t23);
        tuiles.add(t24);
        tuiles.add(t25);
        tuiles.add(t30);
        tuiles.add(t31);
        tuiles.add(t32);
        tuiles.add(t33);
        tuiles.add(t34);
        tuiles.add(t35);
        tuiles.add(t41);
        tuiles.add(t42);
        tuiles.add(t43);
        tuiles.add(t44);
        tuiles.add(t52);
        tuiles.add(t53);
        
        Collections.shuffle(tuiles);
        
        int n = 0;
        for (int i =0;i <= 5; i++) {
            for (int j = 0;j<6;j++) {
                if (n == 0 || n == 1 || n == 4 || n == 5 || n == 6 || n ==11 || n ==24 || n ==29 || n==30 || n ==31 || n==34 || n==35) {
                    ile.addTuile(t00,i,j);                   
                }else{
                    ile.addTuile(tuiles.get(0), i, j);
                    if(ile.getTuile(i, j).getEvent()==Evenement.SPAWN_EXPLORATEUR){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Explorateur")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    } else if(ile.getTuile(i, j).getEvent()==Evenement.SPAWN_INGENIEUR){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Ingenieur")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    } else if(ile.getTuile(i, j).getEvent()==Evenement.SPAWN_MESSAGER){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Messager")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    } else if(ile.getTuile(i, j).getEvent()==Evenement.SPAWN_NAVIGATEUR){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Navigateur")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    } else if(ile.getTuile(i, j).getEvent()==Evenement.SPAWN_PLONGEUR){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Plongeur")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    } else if(ile.getTuile(i, j).getEvent()==Evenement.HELIPORT){
                        for(Pion pion : pions){
                            if(pion.getRole().getNomA().equals("Pilote")){
                                pion.setTuilePosition(tuiles.get(0));
                            }
                        }
                    }
                    
                tuiles.remove(0);
                }
                n++;        
            }
        }
    }

    public void initAventurier() {

        Explorateur explorateur = new Explorateur("Explorateur",
                "L'Explorateur peut se déplacer et assécher en diagonale.",
                ile.getTuile(2, 4));

        Ingenieur ingenieur = new Ingenieur("Ingenieur",
                "L'Ingénieur peut assécher 2 tuiles pour une action.",
                ile.getTuile(0, 3));

        Messager messager = new Messager("Messager",
                "Le Messager peut donner des cartes Trésor à un autre joueur n'importe où sur l'île pour 1 action par carte. ",
                ile.getTuile(2, 1));

        Navigateur navigateur = new Navigateur("Navigateur",
                "Le Navigateur peut déplacer un autre joueur d'une ou deux tuiles adjacentes pour une action. ",
                ile.getTuile(1, 3));

        Pilote pilote = new Pilote("Pilote",
                "Le Pilote peut, une fois par tour, voler jusqu'à n'importe quelle tuile de l'île pour une action. ",
                ile.getTuile(2, 3));

        Plongeur plongeur = new Plongeur("Plongeur",
                "Le Plongeur peut passer par une ou deux tuiles adjacentes inondées et/ou manquantes pour une action (il doit terminer le tour sur une tuile).",
                ile.getTuile(1, 2));

        
        
        
    }

    public void initCartes() {

        // Cartes spéciales
        CarteTresor C1 = new CarteTresor(CTresor.SAC_SABLE);
        CarteTresor C2 = new CarteTresor(CTresor.SAC_SABLE);
        CarteTresor C3 = new CarteTresor(CTresor.HELICO);
        CarteTresor C4 = new CarteTresor(CTresor.HELICO);
        CarteTresor C5 = new CarteTresor(CTresor.HELICO);
        
        // Cartes clé trésors
        //FEU
        CarteTresor C6 = new CarteTresor(CTresor.CLE_FEU);
        CarteTresor C7 = new CarteTresor(CTresor.CLE_FEU);
        CarteTresor C8 = new CarteTresor(CTresor.CLE_FEU);
        CarteTresor C9 = new CarteTresor(CTresor.CLE_FEU);
        CarteTresor C10 = new CarteTresor(CTresor.CLE_FEU);

        //EAU
        CarteTresor C11 = new CarteTresor(CTresor.CLE_EAU);
        CarteTresor C12 = new CarteTresor(CTresor.CLE_EAU);
        CarteTresor C13 = new CarteTresor(CTresor.CLE_EAU);
        CarteTresor C14 = new CarteTresor(CTresor.CLE_EAU);
        CarteTresor C15 = new CarteTresor(CTresor.CLE_EAU);

        //TERRE
        CarteTresor C16 = new CarteTresor(CTresor.CLE_TERRE);
        CarteTresor C17 = new CarteTresor(CTresor.CLE_TERRE);
        CarteTresor C18 = new CarteTresor(CTresor.CLE_TERRE);
        CarteTresor C19 = new CarteTresor(CTresor.CLE_TERRE);
        CarteTresor C20 = new CarteTresor(CTresor.CLE_TERRE);

        //AIR
        CarteTresor C21 = new CarteTresor(CTresor.CLE_AIR);
        CarteTresor C22 = new CarteTresor(CTresor.CLE_AIR);
        CarteTresor C23 = new CarteTresor(CTresor.CLE_AIR);
        CarteTresor C24 = new CarteTresor(CTresor.CLE_AIR);
        CarteTresor C25 = new CarteTresor(CTresor.CLE_AIR);

        //Cartes montée des eaux 
        CarteTresor C26 = new CarteTresor(CTresor.MONTEE_DES_EAUX);
        CarteTresor C27 = new CarteTresor(CTresor.MONTEE_DES_EAUX);
        CarteTresor C28 = new CarteTresor(CTresor.MONTEE_DES_EAUX);

        //On ajoute les cartes dans la pile
        pile.add(C1);
        pile.add(C2);
        pile.add(C3);
        pile.add(C4);
        pile.add(C5);
        pile.add(C6);
        pile.add(C7);
        pile.add(C8);
        pile.add(C9);
        pile.add(C10);
        pile.add(C11);
        pile.add(C12);
        pile.add(C13);
        pile.add(C14);
        pile.add(C15);
        pile.add(C16);
        pile.add(C17);
        pile.add(C18);
        pile.add(C19);
        pile.add(C20);
        pile.add(C21);
        pile.add(C22);
        pile.add(C23);
        pile.add(C24);
        pile.add(C25);
        pile.add(C26);
        pile.add(C27);
        pile.add(C28);
    }

    public void initTresors() {
        OTresor tTerre = new OTresor(Tresor.TERRE, false);
        OTresor tFeu = new OTresor(Tresor.FEU, false);
        OTresor tAir = new OTresor(Tresor.AIR, false);
        OTresor tEau = new OTresor(Tresor.EAU, false);

        tresors.add(tEau);
        tresors.add(tAir);
        tresors.add(tFeu);
        tresors.add(tTerre);
    }

    public void initDemo() {
        initGrilleDemo();
        initAventurier();
        initCartes();
        initTresors();
        niveauEau = new NiveauEau(Difficulte.NOVICE);
    }
    
    public void initAleatoire(Difficulte diff){ //Il faut set la difficulté de la partie avant de la commencer
        initGrilleAleatoire();
        niveauEau = new NiveauEau(diff);
    }

    public void seDeplacer(Pion pion){
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = ile.getNonSubmerge(pion.getRole().getTuilesDispoPourDeplacement(ile, pion.getTuilePosition()));
        //On montre les cases dispo puis on demande la case à l'utilisateur puis on le déplace sur la tuile.

    }
    
    @Override
    public void traiterMessage(Message m) {

    }
    
    public static void main(String[] args) {
        System.out.println("bj");
        initGrilleDemo();
        new VueJeu(ile);
        
        System.out.println("?");

        
        
        
    }

    
}
