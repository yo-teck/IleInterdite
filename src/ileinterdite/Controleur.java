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
import ileinterdite.PackageAventurier.Aventurier;
import ileinterdite.PackageAventurier.Navigateur;
import ileinterdite.PackageAventurier.Ingenieur;
import ileinterdite.PackageAventurier.Messager;
import ileinterdite.PackageAventurier.Plongeur;
import ileinterdite.PackageAventurier.Explorateur;
import ileinterdite.PackageAventurier.Pilote;
import ileinterdite.Vues.VueFin;
import ileinterdite.Vues.VueDefausse;
import ileinterdite.Vues.VueDemarrer;
import ileinterdite.Vues.VueDonnerCarte;
import ileinterdite.Vues.VueGrille;
import ileinterdite.Vues.VueIndividuelle;
import ileinterdite.Vues.VueInformation;
import ileinterdite.Vues.VueNavigateur;
import ileinterdite.Vues.VueUtiliserCarte;
import java.sql.Time;
import static java.time.LocalTime.now;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author TeamEverest
 */
public class Controleur implements Observateur {

    /**
     * @param args the command line arguments
     */
    private Grille ile;

    private ArrayList<CarteTresor> pile;
    private ArrayList<CarteTresor> defausse;
    private ArrayList<Tuile> pileCarteInondations;
    private ArrayList<Tuile> tuilesPiochees;
    private ArrayList<OTresor> tresors;
    private ArrayList<Pion> pions;

    private Pion pionActif;

    private NiveauEau niveauEau;

    private VueGrille ihm;
    private VueIndividuelle vi;
    private VueDemarrer menu;
    private VueDefausse vueDefausse;
    private VueDonnerCarte vueDonCarte;
    private VueUtiliserCarte vueUtiliserCarte;
    private VueInformation vueInfo;
    private VueNavigateur vueNavigateur;

    private VueFin vueFin;

    private boolean debutDePartie;

    private int h;
    private int min;
    private int sec;

    public Controleur() {
        h = now().getHour();
        min = now().getMinute();
        sec = now().getSecond();

        ile = new Grille();

        pile = new ArrayList<>();
        defausse = new ArrayList<>();

        tuilesPiochees = new ArrayList<>();
        pileCarteInondations = new ArrayList<>();

        tresors = new ArrayList<>();
        pions = new ArrayList<>();
        niveauEau = new NiveauEau(Difficulte.NOVICE);
        debutDePartie = true;

        menu = new VueDemarrer();
        menu.addObservateur(this);

    }

    public ArrayList<Pion> getPions() {
        return pions;
    }

    /////////////////////////////////////////////////////////////////////////////
    /////Méthodes d'initialisations du jeu
    /////////////////////////////////////////////////////////////////////////////
    
    public void initGrilleDemo() {

        //Ligne 0
        Tuile t00 = new Tuile(Etat.NULL, 0, 0);
        Tuile t01 = new Tuile(Etat.NULL, 0, 1);
        Tuile t02 = new Tuile(Etat.SEC, Evenement.RIEN, 0, 2, "Le_Pont_des_Abimes");
        Tuile t03 = new Tuile(Etat.INONDE, Evenement.SPAWN_INGENIEUR, 0, 3, "La_Porte_de_Bronze");
        Tuile t04 = new Tuile(Etat.NULL, 0, 4);
        Tuile t05 = new Tuile(Etat.NULL, 0, 5);
        //Ligne 1
        Tuile t10 = new Tuile(Etat.NULL, 1, 0);
        Tuile t11 = new Tuile(Etat.SEC, Evenement.FEU, 1, 1, "La_Caverne_des_Ombres");
        Tuile t12 = new Tuile(Etat.SEC, Evenement.SPAWN_PLONGEUR, 1, 2, "La_Porte_de_Fer");
        Tuile t13 = new Tuile(Etat.SEC, Evenement.SPAWN_NAVIGATEUR, 1, 3, "La_Porte_d'Or");
        Tuile t14 = new Tuile(Etat.SEC, Evenement.RIEN, 1, 4, "Les_Falaises_de_l'Oubli");
        Tuile t15 = new Tuile(Etat.NULL, 1, 5);
        //Ligne 2
        Tuile t20 = new Tuile(Etat.SEC, Evenement.EAU, 2, 0, "Le_Palais_de_Corail");
        Tuile t21 = new Tuile(Etat.SEC, Evenement.SPAWN_MESSAGER, 2, 1, "La_Porte_d'Argent");
        Tuile t22 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 2, 2, "Les_Dunes_de_l'Illusion");
        Tuile t23 = new Tuile(Etat.SEC, Evenement.HELIPORT, 2, 3, "Heliport");
        Tuile t24 = new Tuile(Etat.SEC, Evenement.SPAWN_EXPLORATEUR, 2, 4, "La_Porte_de_Cuivre");
        Tuile t25 = new Tuile(Etat.SEC, Evenement.AIR, 2, 5, "Le_Jardin_des_Hurlements");
        //Ligne 3
        Tuile t30 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 0, "La_Foret_Pourpre");
        Tuile t31 = new Tuile(Etat.INONDE, Evenement.RIEN, 3, 1, "Le_Lagon_Perdu");
        Tuile t32 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 3, 2, "Le_Marais_Brumeux");
        Tuile t33 = new Tuile(Etat.INONDE, Evenement.RIEN, 3, 3, "Observatoire");
        Tuile t34 = new Tuile(Etat.SUBMERGE, Evenement.RIEN, 3, 4, "Le_Rocher_Fantome");
        Tuile t35 = new Tuile(Etat.INONDE, Evenement.FEU, 3, 5, "La_Caverne_du_Brasier");
        //Ligne 4
        Tuile t40 = new Tuile(Etat.NULL, 4, 0);
        Tuile t41 = new Tuile(Etat.SEC, Evenement.TERRE, 4, 1, "Le_Temple_du_Soleil");
        Tuile t42 = new Tuile(Etat.SUBMERGE, Evenement.TERRE, 4, 2, "Le_Temple_de_la_Lune");
        Tuile t43 = new Tuile(Etat.SEC, Evenement.EAU, 4, 3, "Le_Palais_des_Marees");
        Tuile t44 = new Tuile(Etat.SEC, Evenement.RIEN, 4, 4, "Le_Val_du_Crepuscule");
        Tuile t45 = new Tuile(Etat.NULL, 4, 5);
        //Ligne 5
        Tuile t50 = new Tuile(Etat.NULL, 5, 0);
        Tuile t51 = new Tuile(Etat.NULL, 5, 1);
        Tuile t52 = new Tuile(Etat.SEC, Evenement.RIEN, 5, 2, "La_Tour_du_Guet");
        Tuile t53 = new Tuile(Etat.INONDE, Evenement.AIR, 5, 3, "Le_Jardin_des_Murmures");
        Tuile t54 = new Tuile(Etat.NULL, 5, 4);
        Tuile t55 = new Tuile(Etat.NULL, 5, 5);

        for (Pion pion : pions) {
            if (pion.getRole().getNomA().equals("Explorateur")) {
                pion.setTuilePositionIni(t24);

            } else if (pion.getRole().getNomA().equals("Ingenieur")) {
                pion.setTuilePositionIni(t03);

            } else if (pion.getRole().getNomA().equals("Messager")) {
                pion.setTuilePositionIni(t21);

            } else if (pion.getRole().getNomA().equals("Navigateur")) {
                pion.setTuilePositionIni(t13);

            } else if (pion.getRole().getNomA().equals("Plongeur")) {
                pion.setTuilePositionIni(t12);

            } else if (pion.getRole().getNomA().equals("Pilote")) {
                pion.setTuilePositionIni(t23);
            }
        }
        //Ajout des tuiles dans la grille
        ile = new Grille();
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

        pileCarteInondations.clear();
        pileCarteInondations.addAll(ile.getNonSubmerge(ile.getTuiles()));
        Collections.shuffle(pileCarteInondations);

    }

    //Initialisation de la grille de manière aléatoire
    public void initGrilleAleatoire() {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        //Instanciation des tuiles
        Tuile t00 = new Tuile(Etat.NULL, 0, 0);
        Tuile t02 = new Tuile(Etat.SEC, Evenement.RIEN, 0, 2, "Le_Pont_des_Abimes");
        Tuile t03 = new Tuile(Etat.SEC, Evenement.SPAWN_INGENIEUR, 0, 3, "La_Porte_de_Bronze");
        Tuile t11 = new Tuile(Etat.SEC, Evenement.FEU, 1, 1, "La_Caverne_des_Ombres");
        Tuile t12 = new Tuile(Etat.SEC, Evenement.SPAWN_PLONGEUR, 1, 2, "La_Porte_de_Fer");
        Tuile t13 = new Tuile(Etat.SEC, Evenement.SPAWN_NAVIGATEUR, 1, 3, "La_Porte_d'Or");
        Tuile t14 = new Tuile(Etat.SEC, Evenement.RIEN, 1, 4, "Les_Falaises_de_l'Oubli");        //Ligne 2
        Tuile t20 = new Tuile(Etat.SEC, Evenement.EAU, 2, 0, "Le_Palais_de_Corail");
        Tuile t21 = new Tuile(Etat.SEC, Evenement.SPAWN_MESSAGER, 2, 1, "La_Porte_d'Argent");
        Tuile t22 = new Tuile(Etat.SEC, Evenement.RIEN, 2, 2, "Les_Dunes_de_l'Illusion");
        Tuile t23 = new Tuile(Etat.SEC, Evenement.HELIPORT, 2, 3, "Heliport");
        Tuile t24 = new Tuile(Etat.SEC, Evenement.SPAWN_EXPLORATEUR, 2, 4, "La_Porte_de_Cuivre");
        Tuile t25 = new Tuile(Etat.SEC, Evenement.AIR, 2, 5, "Le_Jardin_des_Hurlements");
        Tuile t30 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 0, "La_Foret_Pourpre");
        Tuile t31 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 1, "Le_Lagon_Perdu");
        Tuile t32 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 2, "Le_Marais_Brumeux");
        Tuile t33 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 3, "Observatoire");
        Tuile t34 = new Tuile(Etat.SEC, Evenement.RIEN, 3, 4, "Le_Rocher_Fantome");
        Tuile t35 = new Tuile(Etat.SEC, Evenement.FEU, 3, 5, "La_Caverne_du_Brasier");
        Tuile t41 = new Tuile(Etat.SEC, Evenement.TERRE, 4, 1, "Le_Temple_du_Soleil");
        Tuile t42 = new Tuile(Etat.SEC, Evenement.TERRE, 4, 2, "Le_Temple_de_la_Lune");
        Tuile t43 = new Tuile(Etat.SEC, Evenement.EAU, 4, 3, "Le_Palais_des_Marees");
        Tuile t44 = new Tuile(Etat.SEC, Evenement.RIEN, 4, 4, "Le_Val_du_Crepuscule");
        Tuile t52 = new Tuile(Etat.SEC, Evenement.RIEN, 5, 2, "La_Tour_du_Guet");
        Tuile t53 = new Tuile(Etat.SEC, Evenement.AIR, 5, 3, "Le_Jardin_des_Murmures");

        for (Pion pion : pions) {
            if (pion.getRole().getNomA().equals("Explorateur")) {
                pion.setTuilePositionIni(t24);

            } else if (pion.getRole().getNomA().equals("Ingenieur")) {
                pion.setTuilePositionIni(t03);

            } else if (pion.getRole().getNomA().equals("Messager")) {
                pion.setTuilePositionIni(t21);

            } else if (pion.getRole().getNomA().equals("Navigateur")) {
                pion.setTuilePositionIni(t13);

            } else if (pion.getRole().getNomA().equals("Plongeur")) {
                pion.setTuilePositionIni(t12);

            } else if (pion.getRole().getNomA().equals("Pilote")) {
                pion.setTuilePositionIni(t23);
            }
        }
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
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (n == 0 || n == 1 || n == 4 || n == 5 || n == 6 || n == 11 || n == 24 || n == 29 || n == 30 || n == 31 || n == 34 || n == 35) {
                    ile.addTuile(t00, i, j);
                } else {
                    ile.addTuile(tuiles.get(0), i, j);
                    tuiles.remove(0);
                }
                n++;
            }
        }

        pileCarteInondations.clear();
        pileCarteInondations.addAll(ile.getNonSubmerge(ile.getTuiles()));
        Collections.shuffle(pileCarteInondations);
    }

    public ArrayList<Aventurier> initAventurier() {

        ArrayList<Aventurier> aventuriers = new ArrayList<>();

        Explorateur explorateur = new Explorateur();
        aventuriers.add(explorateur);

        Ingenieur ingenieur = new Ingenieur();
        aventuriers.add(ingenieur);

        Messager messager = new Messager();
        aventuriers.add(messager);

        Navigateur navigateur = new Navigateur();
        aventuriers.add(navigateur);

        Pilote pilote = new Pilote();
        aventuriers.add(pilote);

        Plongeur plongeur = new Plongeur();
        aventuriers.add(plongeur);

        Collections.shuffle(aventuriers);

        return aventuriers;
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

        //On vide les piles de cartes pour commencer une nouvelle partie
        defausse.clear();
        pile.clear();

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

        Collections.shuffle(pile);
    }
    
    public void initPion(int nbJoueurs) {
        ArrayList<Aventurier> aventuriers = initAventurier();
        pions.clear();
        for (int i = 0; i < nbJoueurs + 1; i++) {
            this.pions.add(new Pion(aventuriers.get(i)));
        }
        pionActif = pions.get(0);
    }

    public void initInondation() {
        for (int i = 0; i < 6; i++) {
            if (pileCarteInondations.get(0).getEtat() == Etat.SEC) {
                pileCarteInondations.get(0).setEtat(Etat.INONDE);
            } else if (pileCarteInondations.get(i).getEtat() == Etat.INONDE) {
                pileCarteInondations.get(0).setEtat(Etat.SUBMERGE);
            }
            tuilesPiochees.add(pileCarteInondations.get(0));
            pileCarteInondations.remove(0);
        }
    }

    public void inonderTuiles() {

        for (int i = 0; i < niveauEau.getEchelon(); i++) {
            if (pileCarteInondations.get(0).getEtat() == Etat.SEC) {
                pileCarteInondations.get(0).setEtat(Etat.INONDE);
            } else if (pileCarteInondations.get(0).getEtat() == Etat.INONDE) {
                pileCarteInondations.get(0).setEtat(Etat.SUBMERGE);
            }
            tuilesPiochees.add(pileCarteInondations.get(0));
            pileCarteInondations.remove(0);
        }

    }
    public void initTresors() {
        OTresor tTerre = new OTresor(Tresor.TERRE, false);
        OTresor tFeu = new OTresor(Tresor.FEU, false);
        OTresor tAir = new OTresor(Tresor.AIR, false);
        OTresor tEau = new OTresor(Tresor.EAU, false);

        tresors.clear();
        tresors.add(tEau);
        tresors.add(tAir);
        tresors.add(tFeu);
        tresors.add(tTerre);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Méthodes de déplacement des pions
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void seDeplacer(Pion pion) {
        ihm.activationBoutons(false);
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = pion.getRole().getTuilesDispoPourDeplacement(ile, pion.getTuilePosition());
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);
        }

        //On montre les cases dispo puis on demande la case à l'utilisateur puis on le déplace sur la tuile.
        Message m = new Message(TypesMessage.TUILE_DEPLACEMENT);
        m.setPion(pion);
        ihm.setMsg(m);
        ihm.setCliquable(ile, pion.getCouleur());

    }

    public void seDeplacerNavigateur(Pion pion) {
        ihm.activationBoutons(false);
        Pion explo = new Pion(new Explorateur());
        Pion normal = new Pion(new Navigateur());

        ArrayList<Tuile> tuilesDispo = new ArrayList<>();

        tuilesDispo = explo.getRole().getTuilesDispoPourDeplacement(ile, pion.getTuilePosition());
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);
        }
        if (pion.getRole().getNomA().equals("Plongeur") || pion.getRole().getNomA().equals("Explorateur")) {
            tuilesDispo = normal.getRole().getTuilesDispoPourDeplacement(ile, pion.getTuilePosition());
        } else {
            tuilesDispo = pion.getRole().getTuilesDispoPourDeplacement(ile, pion.getTuilePosition());
        }

        for (int j = 0; j < tuilesDispo.size(); j++) {
            ArrayList<Tuile> tuileSave = new ArrayList<>();
            tuileSave = (normal.getRole().getTuilesDispoPourDeplacement(ile, tuilesDispo.get(j)));
            for (Tuile tuile : tuileSave) {
                tuile.setActif(true);
            }
        }

        //On montre les cases dispo puis on demande la case à l'utilisateur puis on le déplace sur la tuile.
        Message m = new Message(TypesMessage.TUILE_DEPLACEMENT_AMI);
        m.setPion(pion);
        ihm.setMsg(m);
        ihm.setCliquable(ile, pion.getCouleur());

    }

    public void seDeplacerHelico(Pion pion) {
        ihm.activationBoutons(false);
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = ile.getNonSubmerge(ile.getTuiles());
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);

        }

        //On montre les cases dispo puis on demande la case à l'utilisateur puis on le déplace sur la tuile.
        Message m = new Message(TypesMessage.TUILE_DEPLACEMENT_HELICO);
        m.setPion(pion);
        ihm.setMsg(m);
        ihm.setCliquable(ile, pion.getCouleur());
    }

    public void seDéplacerCoule(Pion pion){
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Méthodes d'assechement des tuiles
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void assecher(Pion pion) {
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = ile.getTuilesInondees(pion.getRole().getTuilesAdjacentesInnondees(ile, pion.getTuilePosition()));
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);

        }

        //On montre les cases dispo pour l'assechement puis on demande la case à l'utilisateur puis on asseche la tuile.
        ihm.setMsg(new Message(TypesMessage.TUILE_ASSECHEMENT));
        ihm.setCliquable(ile, pionActif.getCouleur());

    }

    public void assecherSacSable() {
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = ile.getTuilesInondees(ile.getTuiles());
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);

        }
        //On montre les cases dispo pour l'assechement puis on demande la case à l'utilisateur puis on asseche la tuile.
        ihm.setMsg(new Message(TypesMessage.TUILE_ASSECHEMENT_SS));
        ihm.setCliquable(ile, pionActif.getCouleur());

    }

    public void assecherIngenieur() {
        ArrayList<Tuile> tuilesDispo = new ArrayList<>();
        tuilesDispo = ile.getTuilesInondees(pionActif.getRole().getTuilesAdjacentesInnondees(ile, pionActif.getTuilePosition()));
        for (Tuile tuile : tuilesDispo) {
            tuile.setActif(true);

        }

        //On montre les cases dispo pour l'assechement puis on demande la case à l'utilisateur puis on asseche la tuile.
        ihm.setMsg(new Message(TypesMessage.TUILE_ASSECHEMENT_INGENIEUR));
        ihm.setCliquable(ile, pionActif.getCouleur());
    }

    @Override
    public void traiterMessage(Message m) {

        //Traitements du message pour initialiser une partie
        if (m.getType() == TypesMessage.COMMENCER_PARTIE) {
            initCartes();
            initTresors();
            initPion(m.getNbJoueurs());

            int i = 0;

            for (Pion pion : pions) {
                pion.setNomj(m.getNomJoueurs().get(i));
                i++;
            }

            if (m.getDifficulte().equals("Novice")) {
                niveauEau.setDifficulte(Difficulte.NOVICE);
            } else if (m.getDifficulte().equals("Normal")) {
                niveauEau.setDifficulte(Difficulte.NORMAL);
            } else if (m.getDifficulte().equals("Elite")) {
                niveauEau.setDifficulte(Difficulte.ELITE);
            } else if (m.getDifficulte().equals("Legendaire")) {
                niveauEau.setDifficulte(Difficulte.LEGENDAIRE);
            }
            if (m.getModeInitialisation().equals("Démo")) {
                demo();
            } else {
                aleatoire();
            }
            
        } else if (m.getType() == TypesMessage.RECOMMENCER) {
            debutDePartie = true;
            ihm.dispose();
            menu.montrer(true);
            
        //Traitements des messages d'action du joueur
        } else if (m.getType() == TypesMessage.FIN_TOUR) {
            
            fairePiocher(pionActif);
            pionActif.getRole().setCapaciteUtilisee(false);
            joueurSuivant();
            
            if (pionActif.getNbCartes() > 5) {
                vueDefausse = new VueDefausse(pionActif);
                vueDefausse.addObservateur(this);
                ihm.activationBoutons(false);
            }
            
            pionActif.setNbAction(3);
            ihm.actualiserInfoJA(pionActif);
            
        } else if (m.getType() == TypesMessage.DEFAUSSE) {
            
            for (CarteTresor ct : m.getCartesTresor()) {
                defausser(pionActif, ct);
            }
            
            ihm.actualiserCartes(pions);
            ihm.activationBoutons(true);
            
        } else if (m.getType() == TypesMessage.DEPLACEMENT) {
            
            seDeplacer(pionActif);
            
            //rendre non clicable apres choix
            ihm.activationBoutons(false);
            ihm.activationDeplacement(true);
            
        } else if (m.getType() == TypesMessage.TUILE_DEPLACEMENT) {
            m.getPion().setTuilePosition(m.getTuile());

            ihm.setNonCliquable(ile);
            ihm.repaintInfoTuile();
            ihm.changerEtatBouton();
            
            //decrementer le nombre d'action du joueur en cours
            pionActif.setNbAction(pionActif.getNbAction() - 1);
            ihm.actualiserInfoJA(pionActif);
            ihm.activationBoutons(true);
            
        } else if (m.getType() == TypesMessage.TUILE_DEPLACEMENT_HELICO) {
            m.getPion().setTuilePosition(m.getTuile());
            
            ihm.setNonCliquable(ile);
            ihm.repaintInfoTuile();
            ihm.actualiserInfoJA(pionActif);
            ihm.activationBoutons(true);
            
        } else if (m.getType() == TypesMessage.ASSECHER) {
            
            assecher(pionActif);
            
            ihm.activationBoutons(false);
            ihm.activationAssechement(true);
            
        } else if (m.getType() == TypesMessage.TUILE_ASSECHEMENT) {
            m.getTuile().setEtat(Etat.SEC);
            
            ihm.setNonCliquable(ile);
            
            //decrementer le nombre d'action du joueur en cours
            pionActif.setNbAction(pionActif.getNbAction() - 1);
            ihm.actualiserInfoJA(pionActif);
            ihm.activationBoutons(true);
            ihm.changerEtatBouton();
            
        } else if (m.getType() == TypesMessage.TUILE_ASSECHEMENT_SS) {
            m.getTuile().setEtat(Etat.SEC);
            
            ihm.setNonCliquable(ile);
            
            //decrementer le nombre d'action du joueur en cours
            ihm.actualiserInfoJA(pionActif);
            ihm.actualiserCartes(pions);
            ihm.activationBoutons(true);
            
        } else if (m.getType() == TypesMessage.TUILE_ASSECHEMENT_INGENIEUR) {
            m.getTuile().setEtat(Etat.SEC);
            
            assecher(pionActif);
            
        } else if (m.getType() == TypesMessage.VUE_DONNER_CARTE) {
            
            vueDonCarte = new VueDonnerCarte(pionActif, pions);
            vueDonCarte.addObservateur(this);
            
        } else if (m.getType() == TypesMessage.DONNER_CARTE) {    
            
            donnerCarte(m.getCarteTresor(), m.getPion());
            ihm.activationBoutons(true);
            
            //decrementer le nombre d'action du joueur en cours
            pionActif.setNbAction(pionActif.getNbAction() - 1);
            ihm.actualiserInfoJA(pionActif);
            
        } else if (m.getType() == TypesMessage.RECUPERER_TROPHEE) {
            
            for (OTresor objetTresor : tresors) {               
                if (objetTresor.getType() == m.getObjetTresor().getType()) {
                    objetTresor.setEstRecupere(true);
                    ihm.actualiserInfoTresor();
                }              
            }
            
            int cnt = 0;
            int i = 0;
            
            while (cnt < 4 && i < pionActif.getNbCartes()) {
                if (pionActif.getCartesTresors().get(i).getType().toString().contains(m.getObjetTresor().getType().toString())) {
                    pionActif.getCartesTresors().remove(i);
                    i--;
                    ihm.actualiserCartes(pions);
                    cnt++;
                }
                i++;
            }
            
            //decrementer le nombre d'action du joueur en cours           
            pionActif.setNbAction(pionActif.getNbAction() - 1);
            ihm.actualiserInfoJA(pionActif);
            
        } else if (m.getType() == TypesMessage.ANNULER) {
            
            ihm.activationBoutons(true);
            ihm.setNonCliquable(ile);
            ihm.actualiserGrille(ile);
            
        } else if (m.getType() == TypesMessage.VUE_UTILISER_CARTE) {
            
            vueUtiliserCarte = new VueUtiliserCarte(pions);
            ihm.activationBoutons(false);
            vueUtiliserCarte.addObservateur(this);
            
        } else if (m.getType() == TypesMessage.UTILISER_CARTE) {
            
            if (m.getCarteTresor().getType() == CTresor.SAC_SABLE) {
                assecherSacSable();
                defausser(m.getPion(), m.getCarteTresor());
                ihm.actualiserInfoJA(pionActif);
            } else { //Carte Helicoptere
                
                if (m.getTuile().getNom() == "Heliport" && m.getTuile().getPions().size() == 4) {
                    
                    if (checkVictoire()) {
                        int hFin = now().getHour() - h;
                        int minFin = now().getMinute() - min;
                        int secFin = now().getSecond() - sec;
                        String temps = new String("Votre temps : " + hFin + "h" + minFin + "m" + secFin + "s");
                        vueFin = new VueFin("VICTOIRE ! ", temps);
                        vueFin.addObservateur(this);
                        System.out.println("Victoire");
                    }
                    
                } else {
                    seDeplacerHelico(m.getPion());
                    defausser(m.getPion(), m.getCarteTresor());
                    ihm.actualiserInfoJA(pionActif);
                }
            }
            
        } else if (m.getType() == TypesMessage.AFFICHER_INFO) {
            
            vueInfo = new VueInformation(pions);
            vueInfo.addObservateur(this);
            ihm.activationInfo(false);
            
        } else if (m.getType() == TypesMessage.FERMER_INFO) {
            
            ihm.activationInfo(true);
            
        } else if (m.getType() == TypesMessage.CAPACITE) {

            if (pionActif.getRole().getNomA().equals("Pilote")) {
                seDeplacerHelico(pionActif);
                pionActif.setNbAction(pionActif.getNbAction() - 1);
                pionActif.getRole().setCapaciteUtilisee(true);
            } else if (pionActif.getRole().getNomA().equals("Ingenieur")) {
                assecherIngenieur();
                pionActif.getRole().setCapaciteUtilisee(true);
            } else if (pionActif.getRole().getNomA().equals("Navigateur")) {
                //seDeplacerHelico(m.getPion());
                vueNavigateur = new VueNavigateur(pionActif, pions);
                vueNavigateur.addObservateur(this);
                ihm.activationBoutons(false);
            }

        } else if (m.getType() == TypesMessage.DEPLACEMENT_AMI) {
            
            System.out.println(m.getPion());
            seDeplacerNavigateur(m.getPion());
            ihm.activationBoutons(true);
            
        } else if (m.getType() == TypesMessage.TUILE_DEPLACEMENT_AMI) {

            m.getPion().setTuilePosition(m.getTuile());

            ihm.setNonCliquable(ile);
            ihm.repaintInfoTuile();
            
            //decrementer le nombre d'action du joueur en cours
            pionActif.setNbAction(pionActif.getNbAction() - 1);
            ihm.actualiserInfoJA(pionActif);
            ihm.activationBoutons(true);
        }
        //On vérifie des conditions grâce à la méthode check pour activer/désactiver des boutons
        check();
        
        if (checkDefaite()) {
            //Lancer vue defaite ;
            int hFin = now().getHour() - h;
            int minFin = now().getMinute() - min;
            int secFin = now().getSecond() - sec;
            String temps = new String(hFin + "h" + minFin + "m" + secFin + "s");
            vueFin = new VueFin("DEFAITE !", temps);

            vueFin.addObservateur(this);

        }
    }

    public void demo() {

        initGrilleDemo();

        debutDePartie = true;

        ihm = new VueGrille(ile, niveauEau, pions, tresors);
        ihm.addObservateur(this);
        initPioche(pions);
        for (Pion pion : pions) {
            System.out.print("nom :" + pion.getNomj() + ", ");
            System.out.print("tuile :" + pion.getTuilePosition().getNom() + ", ");
            System.out.println("Role :" + pion.getRole().getNomA());
            for (CarteTresor ct : pion.getCartesTresors()) {
                System.out.println(ct.getType().toString());
            }
            System.out.println("");

        }

        jouerUnTour();

    }

    public void aleatoire() {

        debutDePartie = true;

        initGrilleAleatoire();
        initInondation();
        jouerUnTour();

        System.out.println("");
        ihm = new VueGrille(ile, niveauEau, pions, tresors);
        ihm.addObservateur(this);
        initPioche(pions);
        for (Pion pion : pions) {
            System.out.print("nom :" + pion.getNomj() + ", ");
            System.out.print("tuile :" + pion.getTuilePosition().getNom() + ", ");
            System.out.print("Role :" + pion.getRole().getNomA());
            for (CarteTresor ct : pion.getCartesTresors()) {
                System.out.println(ct.getType().toString());
            }
            System.out.println("");

        }
        //vi = new VueIndividuelle(pions.get(0), pions.get(1), pions.get(2), pions.get(3)); (créations de 4 fenêtres contenant chacune les infos du joeur 'couleur du pion et nom de la personne)
        //vi.addObservateur(this);
    }

    public boolean estFini() {
        return true;
    }

    public Pion getPionActif() {
        return pionActif;
    }

    public void joueurSuivant() {

        int i = 0;
        while (i < pions.size() && pions.get(i) != pionActif) {
            i++;
        }

        if (pions.get(i) == pionActif) {
            pionActif = pions.get((i + 1) % pions.size());
        }
        ihm.joueurSuivant();
        ihm.activationBoutons(true);
    }

    public void defausser(Pion pion, CarteTresor carteTresor) {
        pion.getCartesTresors().remove(carteTresor);
        defausse.add(carteTresor);
    }

    public void donnerCarte(CarteTresor carteTresor, Pion pion) {

        pionActif.getCartesTresors().remove(carteTresor);
        pion.addCarte(carteTresor);

        ihm.actualiserCartes(pions);

    }

    public void jouerUnTour() {
        if (!estFini()) {
            pionActif.setNbAction(3);
            /*
            if (pionActif.getNbCartes() > 5) {
                vueDefausse = new VueDefausse(pionActif);
            }
             */
            //A FAIRE
            //
            while (!isNbActionNul()) {
                //proposer des actions +             
                check();
            }

            fairePiocher(pionActif);
            jouerUnTour();

        }
    }

    public boolean isNbActionNul() {
        return pionActif.getNbAction() == 0;

    }

    public void check() {
        if (pionActif.getRole().getTuilesDispoPourDeplacement(ile, pionActif.getTuilePosition()).size() == 0) {
            //desactiver seDeplacer
            ihm.activationDeplacement(false);
        }
        if (pionActif.getRole().getTuilesAdjacentesInnondees(ile, pionActif.getTuilePosition()).size() == 0) {
            //desactiver assecher
            ihm.activationAssechement(false);
        }
        if (pionActif.getCartesTresors().size() == 0) {
            //desactiver donnercarte
            ihm.activationDon(false);
        }
        if (pionActif.getRole().getNomA().equals("Messager")) {
            ihm.activationDon(true);
        } else if (pionActif.getTuilePosition().getPions().size() >= 2) {
            ihm.activationDon(true);
        } else {
            ihm.activationDon(false);
        }
        if (pionActif.getNbAction() <= 0) {
            ihm.activationBoutons(false);
            ihm.activationFinTour(true);
        }

        if (pionActif.getRole().getNomA().equals("Plongeur") || pionActif.getRole().getNomA().equals("Explorateur")
                || pionActif.getRole().getNomA().equals("Messager") || pionActif.getRole().isCapaciteUtilisee()
                || (pionActif.getRole().getTuilesAdjacentesInnondees(ile, pionActif.getTuilePosition()).size() < 2 && pionActif.getRole().getNomA().equals("Ingenieur"))) {
            ihm.activationCapacite(false);
        }
        for (Pion pion : pions) {
            if (pion.getTuilePosition().getEtat() == Etat.SUBMERGE && !checkDefaite()) {

                if (pion.getRole().getNomA().equals("Pilote")) {
                    seDeplacerHelico(pion);
                } else {
                    seDeplacer(pion);
                }

                pionActif.setNbAction(pionActif.getNbAction() + 1);
            }
        } 

    }

    public boolean checkVictoire() {
        if (!debutDePartie) {
            int c = 0;
            for (OTresor tresor : tresors) {
                if (tresor.isEstRecupere()) {
                    c++;

                    //Aller dans traiter message UTILISER_CARTE et ajouter un test pour voir si les 4 joueurs sont sur la case heliport et le nombre de tresor possedé = 4
                }
            }
            return c == 4;
        } else {
            return false;
        }
    }

    public boolean checkDefaite() {
        if (!debutDePartie) {
            int a = 0;
            int t = 0;
            int f = 0;
            int e = 0;
            for (Tuile tuile : ile.getTuiles()) {
                if (tuile.getEtat() == Etat.SUBMERGE && tuile.getEvent() == Evenement.HELIPORT) {
                    return true;
                } else if (tuile.getEtat() == Etat.SUBMERGE && tuile.getEvent() == Evenement.AIR) {
                    a++;
                } else if (tuile.getEtat() == Etat.SUBMERGE && tuile.getEvent() == Evenement.TERRE) {
                    t++;
                } else if (tuile.getEtat() == Etat.SUBMERGE && tuile.getEvent() == Evenement.FEU) {
                    f++;
                } else if (tuile.getEtat() == Etat.SUBMERGE && tuile.getEvent() == Evenement.EAU) {
                    e++;
                }
            }

            for (Pion p : pions) {
                if (p.getTuilePosition().getEtat() == Etat.SUBMERGE && p.getRole().getTuilesDispoPourDeplacement(ile, p.getTuilePosition()).size() == 0) {
                    return true;
                }
            }
            if (a == 2 || t == 2 || f == 2 || e == 2) {
                System.out.println("troph");
                return true;

            } else if (niveauEau.getNiveau() == 10) {
                System.out.println("lvl");
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void initPioche(ArrayList<Pion> pions) {
        for (Pion pion : pions) {
            fairePiocher(pion);
        }
        debutDePartie = false;
    }

    public void fairePiocher(Pion pion) {
        ihm.actualiserGrille(ile);
        // ihm.actualiserCartes(pions);
        for (int i = 0; i < 2; i++) {
            verifPile();
            CarteTresor ct = pile.get(0);
            if (ct.getType() == CTresor.MONTEE_DES_EAUX && !debutDePartie) {
                niveauEau.setNiveau(niveauEau.getNiveau() + 1);
                ihm.actualiserInfoNiveauEau();
                pileCarteInondations.addAll(tuilesPiochees);
                Collections.shuffle(pileCarteInondations);
                tuilesPiochees.clear();
                defausse.add(ct);
                pile.remove(ct);
                System.out.println("Montée des eaux !");
            } else if (ct.getType() == CTresor.MONTEE_DES_EAUX && debutDePartie) {
                Collections.shuffle(pile);
                System.out.println("Mte");
                i--;
            } else {
                pion.addCarte(ct);
                pile.remove(ct);
            }
        }

        if (!debutDePartie && pileCarteInondations.size() >= niveauEau.getEchelon()) {
            //Si pile de cartes inondation suffisante on tire les cartes et on inonde
            for (int i = 0; i < niveauEau.getEchelon(); i++) {
                tuilesPiochees.add(pileCarteInondations.get(0));
                pileCarteInondations.remove(0);
            }
            inonderTuiles();
        } else if (pileCarteInondations.size() < niveauEau.getEchelon()) {
            //Si pile de cartes inondation insuffisante, on remet les cartes de la défausse dans la pile et on tire les cartes et on inonde
            pileCarteInondations.addAll(tuilesPiochees);
            Collections.shuffle(pileCarteInondations);
            tuilesPiochees.clear();
            System.out.println("Problème reglé");
            for (int i = 0; i < niveauEau.getEchelon(); i++) {
                tuilesPiochees.add(pileCarteInondations.get(0));
                pileCarteInondations.remove(0);
            }
            inonderTuiles();
        }

        ihm.actualiserGrille(ile);
        ihm.actualiserCartes(pions);

    }

    public void verifPile() {
        if (pile.isEmpty()) {
            Collections.shuffle(defausse);
            pile.addAll(defausse);
            defausse.clear();
            System.out.println("Pile remise.");
        }
    }

    public static void main(String[] args) {

        new Controleur();

    }

}
