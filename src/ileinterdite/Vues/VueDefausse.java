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
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author richomml
 */
public class VueDefausse implements Observe {

    private Observateur observateur;

    private JFrame fenetre;

    private FondMonde conteneur;
    private SelectionCarteMultiple cartesSelectionnees;
    private ArrayList<CarteTresor> carteRenvoi;

    private JLabel texte;

    private Font police;
    private File chemin = new File("");

    private JButton btnValider;

    public VueDefausse(Pion pionActif) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            police = Font.createFont(Font.TRUETYPE_FONT, new File(chemin.getAbsolutePath() + "/src/ressources/police/PiecesofEight.ttf"));
            ge.registerFont(police);
        } catch (FontFormatException ex) {
            System.out.println("Nont");
        } catch (IOException ex) {
            System.out.println("Nont");

        }
        police = new Font("Pieces of Eight", Font.PLAIN, 24);
        fenetre = new JFrame("Cartes de " + pionActif.getNomj());
        fenetre.setSize(600, 100 + 100 * ((pionActif.getNbCartes() / 5) + 1));
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.add(new SelectionCarteMultiple(pionActif, true));

        conteneur = new FondMonde();
        conteneur.setLayout(new BorderLayout());
        texte = new JLabel(pionActif.getNomj() + "Vous avez plus de 5 cartes veuillez défausser au moins " + (pionActif.getNbCartes() - 5) + " cartes : ");
        texte.setFont(police);

        conteneur.add(texte, BorderLayout.NORTH);

        cartesSelectionnees = new SelectionCarteMultiple(pionActif, false);
        cartesSelectionnees.getCartesSelectionnees();

        cartesSelectionnees.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carteRenvoi = cartesSelectionnees.getCartesSelectionnees();
                if (carteRenvoi.size() < pionActif.getNbCartes() - 5) {
                    btnValider.setEnabled(false);
                } else {
                    btnValider.setEnabled(true);
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
        });

        cartesSelectionnees.setPreferredSize(new Dimension(600, 100 * ((pionActif.getNbCartes() / 5) + 1)));
        conteneur.add(cartesSelectionnees, BorderLayout.CENTER);

        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.DEFAUSSE);

                m.setCartesTresor(carteRenvoi);

                notifierObservateur(m);
                fenetre.setVisible(false);
            }
        });

        btnValider.setPreferredSize(new Dimension(600, 20));
        btnValider.setEnabled(false);

        conteneur.add(btnValider, BorderLayout.SOUTH);
        fenetre.add(conteneur);
        fenetre.setVisible(true);
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
