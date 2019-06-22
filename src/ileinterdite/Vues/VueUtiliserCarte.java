/**
 *
 * @author richomml
 */
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
import ileinterdite.Vues.Fond.FondMonde;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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
public class VueUtiliserCarte implements Observe {

    private JFrame fenetre;

    private FondMonde conteneur;
    private JPanel conteneurUtil;
    private JPanel conteneurBoutonsConfirmation;

    private JButton btnValider;
    private JButton btnAnnuler;

    private Font police;
    private JLabel txtlololo;
    private JLabel textNomJ;

    private File chemin = new File("");
    private boolean activeValide;

    private MouseListener[] ms;
    private SelectionCarteUniqueUtilisable[] carteSelection;
    private Message msg;

    private ArrayList<Pion> pionsCartesUtilise;

    public VueUtiliserCarte(ArrayList<Pion> pions) {

        pionsCartesUtilise = new ArrayList<>();
        for (Pion pion : pions) {
            for (CarteTresor carte : pion.getCartesTresors()) {
                if ((carte.getType().equals(CTresor.HELICO) || carte.getType().equals(CTresor.SAC_SABLE)) && !pionsCartesUtilise.contains(pion)) {
                    pionsCartesUtilise.add(pion);

                }
            }
        }

        //Initialisation de la police
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 35);

        //Parametrage de la fenetre
        fenetre = new JFrame(" - Utiliser une carte");
        fenetre.setLayout(new BorderLayout());

        fenetre.setResizable(true);
        fenetre.setSize(800, 300 + pionsCartesUtilise.size() * 100);

        //creation d'un JLabel avec un backgrouned
        conteneur = new FondMonde();
        conteneur.setOpaque(false);
        conteneur.setLayout(new BorderLayout());

        //Creation du JPanel du haut
        conteneurUtil = new JPanel(new GridLayout(pionsCartesUtilise.size() + 2, 1));
        conteneurUtil.setOpaque(false);
        conteneurUtil.setPreferredSize(new Dimension(800, pionsCartesUtilise.size() * 80));

        txtlololo = new JLabel("Selectionne un carte : ");
        txtlololo.setFont(police);
        conteneurUtil.add(txtlololo);

        carteSelection = new SelectionCarteUniqueUtilisable[pionsCartesUtilise.size()];
        creeMouseListener(pionsCartesUtilise);
        for (int i = 0; i < pionsCartesUtilise.size(); i++) {
            carteSelection[i] = new SelectionCarteUniqueUtilisable(pionsCartesUtilise.get(i), false);
            carteSelection[i].setActif(false);
            carteSelection[i].addMouseListener(ms[i]);
            conteneurUtil.add(carteSelection[i]);

        }
        textNomJ = new JLabel("");
        textNomJ.setFont(police);
        conteneurUtil.add(textNomJ);

        conteneur.add(conteneurUtil, BorderLayout.CENTER);

        conteneurBoutonsConfirmation = new JPanel(new GridLayout(1, 5));

        btnValider = new JButton("Valider");
        btnValider.setEnabled(false);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.UTILISER_CARTE);
                for (int i = 0; i < carteSelection.length; i++) {
                    if (carteSelection[i].isActif()) {
                        m.setPion(carteSelection[i].getPion());
                        m.setCarteTresor(carteSelection[i].getCarte());
                    }
                }
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnValider);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnAnnuler);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneur.add(conteneurBoutonsConfirmation, BorderLayout.SOUTH);

        fenetre.add(conteneur);

        fenetre.setVisible(true);
    }

    public void creeMouseListener(ArrayList<Pion> pions) {
        ms = new MouseListener[4];
        MouseListener m0 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteSelection[0].setActif(true);
                btnValider.setEnabled(true);
                textNomJ.setFont(police.deriveFont(24f));
                textNomJ.setText("Utiliser la carte " + carteSelection[0].getCarte().getType() + " de " + carteSelection[0].getPion().getNomj() + " [" + carteSelection[0].getPion().getRole().getNomA() + "]");
                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 0) {
                        carteSelection[j].setActif(false);

                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener m1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteSelection[1].setActif(true);
                btnValider.setEnabled(true);
                textNomJ.setFont(police.deriveFont(24f));
                textNomJ.setText("Utiliser la carte " + carteSelection[1].getCarte().getType() + "de " + carteSelection[1].getPion().getNomj() + " [" + carteSelection[1].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 1) {
                        carteSelection[j].setActif(false);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteSelection[2].setActif(true);
                btnValider.setEnabled(true);
                textNomJ.setFont(police.deriveFont(24f));
                textNomJ.setText("Utiliser la carte " + carteSelection[2].getCarte().getType() + "de " + carteSelection[2].getPion().getNomj() + " [" + carteSelection[2].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {
                    if (j != 2) {
                        carteSelection[j].setActif(false);

                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener m3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteSelection[3].setActif(true);
                btnValider.setEnabled(true);
                textNomJ.setFont(police.deriveFont(24f));
                textNomJ.setText("Utiliser la carte " + carteSelection[3].getCarte().getType() + "de " + carteSelection[3].getPion().getNomj() + " [" + carteSelection[3].getPion().getRole().getNomA() + "]");

                for (int j = 0; j < pionsCartesUtilise.size(); j++) {

                    if (j != 3) {
                        carteSelection[j].setActif(false);

                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ms[0] = m0;
        ms[1] = m1;
        ms[2] = m2;
        ms[3] = m3;
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

/*public class VueUtiliserCarte implements Observe {

    private JFrame fenetre;

    private JPanel conteneurBoutonsConfirmation;

    private JButton btnValider;
    private JButton btnAnnuler;
    private JButton btnSauvegarde;
    
    private int compteurDeCarteNull;
    private int pionSansCarte;

    public VueUtiliserCarte(ArrayList<Pion> pions) {
        fenetre = new JFrame("Utiliser une carte");
        fenetre.setSize(500, 500);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new GridLayout(pions.size() * 2 + 1, 1));

        Message m = new Message(TypesMessage.UTILISER_CARTE);
        btnSauvegarde = new JButton();
        
        pionSansCarte = 0 ;
        for (Pion pion : pions) {
            compteurDeCarteNull = 0;
            fenetre.add(new JLabel("Cartes de " + pion.getNomj() + " :"));
            JPanel conteneurCarte = new JPanel(new GridLayout(1, pion.getNbCartes()));
            
            for (CarteTresor ct : pion.getCartesTresors()) {
                
                if (ct.getType() == CTresor.SAC_SABLE || ct.getType() == CTresor.HELICO) {
                    JButton boutonCarte = new JButton(ct.getType().toString());
                    
                    boutonCarte.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            m.setPion(pion);
                            m.setTuile(pion.getTuilePosition()); // Pour check la position sur heliport pour la victoire
                            m.setCarteTresor(ct);
                            boutonCarte.setEnabled(false);
                            btnSauvegarde.setEnabled(true);
                            btnSauvegarde = boutonCarte;
                        }
                    });
                    
                    conteneurCarte.add(boutonCarte);
                } else if(ct.getType() != CTresor.SAC_SABLE || ct.getType() != CTresor.HELICO){
                    compteurDeCarteNull++;
                }
                if(compteurDeCarteNull == pion.getCartesTresors().size()){
                    pionSansCarte++;
                }
                
            }
            
            fenetre.add(conteneurCarte);
        }

        conteneurBoutonsConfirmation = new JPanel(new GridLayout(1, 5));

        btnValider = new JButton("Valider");
        if(pionSansCarte == pions.size()){
            btnValider.setEnabled(false);
        }
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ANNULER);
                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnValider);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        conteneurBoutonsConfirmation.add(btnAnnuler);
        conteneurBoutonsConfirmation.add(new JLabel(""));
        fenetre.add(conteneurBoutonsConfirmation);
        fenetre.setVisible(true);
    }
    
    
    public int getPionSansCarte(){
        return pionSansCarte;
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
}*/
