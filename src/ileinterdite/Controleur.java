/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

/**
 *
 * @author gherrazs
 */
public class Controleur {
    
    
    
    

    /**
     * @param args the command line arguments
     */
    Grille ile = new Grille();
    
    private void initGrille() {

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
    
    public static void main(String[] args) {
        System.out.println("bj");
           
        
    }
    public void initAventurier(){
       /* 
      Explorateur explorateur = new Explorateur("Explorateur","L'Explorateur peut se déplacer et assécher en diagonale.",txy) ; 
      Ingenieur ingenieur = new Ingenieur ("Ingenieur","L'Ingénieur peut assécher 2 tuiles pour une action.",t03);  
      Messager messager   =new Messager("Messager","Le Messager peut donner des cartes Trésor à un autre joueur n'importe où sur l'île pour 1 action par carte. ",txy);
      Navigateur navigateur = new Navigateur("Navigateur","Le Navigateur peut déplacer un autre joueur d'une ou deux tuiles adjacentes pour une action. ",txy);
      Pilote pilote = new Pilote("Pilote","Le Pilote peut, une fois par tour, voler jusqu'à n'importe quelle tuile de l'île pour une action. ",txy);
      Plongeur plongeur = new Plongeur("Plongeur","Le Plongeur peut passer par une ou deux tuiles adjacentes inondées et/ou manquantes pour une action (il doit terminer le tour sur une tuile).",txy);
    */
}
    
 }
