/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CALVO Manuela
 */
public class MenuForm {

    JFrame princip;
    Box fenetre;
    Box menu;
    Box affichage;
    JLabel label_menu;
    private final Object lock = new Object();
    JRadioButton choix1;
    JRadioButton choix2;
    JRadioButton choix3;
    JRadioButton choix4;
    JRadioButton choix5;
    JRadioButton choix6;
    JRadioButton choix7;
    JRadioButton choix8;
    JRadioButton choix9;
    JTextArea fenetre_aff;
    JTextField entree_menu;
    Box choix_menu;
    JButton valid_menu;
    int choix = 0;
    private boolean filled = false;

    public void setFilled(boolean _filled) {
        filled = _filled;

    }

    public int affichage_Menu(String chaine) {
        filled = false;
        // Menu : qu'est ce que tu veux faire ? switch
        //bonjour qui c'est ?
        princip = new JFrame(" Bienvenue dans le menu de l'Agenda ");
        princip.setPreferredSize(new Dimension(800, 600));
        princip.setLocationRelativeTo(null);

        fenetre = Box.createHorizontalBox();

        menu = Box.createVerticalBox();
        label_menu = new JLabel("Que voulez-vous faire avec votre calendrier?");
        choix1 = new JRadioButton(" 1. Afficher tous vos RDV");
        choix2 = new JRadioButton(" 2. Afficher RDV selon un critère");
        choix3 = new JRadioButton(" 3. Modifier un RDV");
        choix4 = new JRadioButton(" 4. Supprimer un RDV");
        choix5 = new JRadioButton(" 5. Supprimer tous les RDV");
        choix6 = new JRadioButton(" 6. Ajouter un RDV");
        choix7 = new JRadioButton(" 7. Sauvegarder et fermer l'agenda");
        choix8 = new JRadioButton(" 8. Changer d'utilisateur");
        choix9 = new JRadioButton(" 9. Fermer le programme");
        
        menu.add(label_menu);

        menu.add(choix1);
        menu.add(choix2);
        menu.add(choix3);
        menu.add(choix4);
        menu.add(choix5);
        menu.add(choix6);
        menu.add(choix7);
        menu.add(choix8);
        menu.add(choix9);

        affichage = Box.createHorizontalBox();
        fenetre_aff = new JTextArea(chaine);
        affichage.add(fenetre_aff);

        fenetre.add(menu);
        fenetre.add(affichage);

        choix_menu = Box.createHorizontalBox();
        valid_menu = new JButton("Valider la saisie");
        valid_menu.addActionListener(this::fillID);
        valid_menu.setSize(300, 300);
        choix_menu.add(valid_menu);

        Box boite = Box.createVerticalBox();
        boite.add(fenetre);

        boite.add(choix_menu);

        princip.getContentPane().add(boite);
        princip.pack();
        princip.setAlwaysOnTop(true);
        princip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        princip.setVisible(true);

        boolean fill = false;
        while (!fill) {
            synchronized (lock) {
                fill = filled;
            }
        }
        princip.dispose();

        return choix;

    }

    private int fillBoite() {
        int i = 0;
        if (choix1.isSelected() == true) {
            i = 1;
        }
        if (choix2.isSelected() == true) {
            i = 2;
        }
        if (choix3.isSelected() == true) {
            i = 3;
        }
        if (choix4.isSelected() == true) {
            i = 4;
        }
        if (choix5.isSelected() == true) {
            i = 5;
        }
        if (choix6.isSelected() == true) {
            i = 6;
        }
        if (choix7.isSelected() == true) {
            i = 7;
        }
        if (choix8.isSelected() == true) {
            i = 8;
        }
        if (choix9.isSelected() == true) {
            i = 9;
        }

        return i;

    }

    private void fillID(ActionEvent e) {
        choix = fillBoite();
        synchronized (lock) {
            filled = true;
        }
    }
    
}
