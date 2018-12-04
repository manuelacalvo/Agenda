/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import agenda.Rdv;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CALVO Manuela
 */
public class SuppRdvForm {

    private Rdv rdv;
    private Rdv rdv_supp = new Rdv();
    private JFrame frame;
    private Box b1;
    private JLabel label_date;
    private JTextField entree_date;
    private Box b2;
    private JLabel label_hDebut;
    private JTextField entree_hDebut;
    private JButton button;
    private JButton button2;
    private Box b4;
    private Box b5;
    private boolean filled = false;
    private final Object obj = new Object();
    private int choix;
    private Box affichage;
    private JTextArea fenetre_aff;
    private CalendrierAff aff = new CalendrierAff();

    public Rdv affichage_demandeSupp() {

        rdv = new Rdv();

        filled = false;
        frame = new JFrame(" Bienvenue dans la suppression d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        

        b1 = Box.createHorizontalBox();
        label_date = new JLabel("Date du Rdv ( jj/MM/yyyy)");
        entree_date = new JTextField();
        b1.add(label_date);
        b1.add(entree_date);

        b2 = Box.createHorizontalBox();
        label_hDebut = new JLabel("Heure de début (hh:mm) ");
        label_hDebut.setLocation(100, 300);
        entree_hDebut = new JTextField();
        b2.add(label_hDebut);
        b2.add(entree_hDebut);
        b2.setSize(200, 100);

        b4 = Box.createHorizontalBox();
        button = new JButton("Valider la saisie");
        button.addActionListener(this::fillRecherche);
        button.setSize(300, 300);
        b4.add(button);

        b5 = Box.createVerticalBox();
        b5.add(b1);
        b5.add(b2);
        b5.add(b4);

        frame.getContentPane().add(b5);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boolean fill = false;
        while (!fill) {
            synchronized (obj) {
                fill = filled;
            }
        }

        frame.dispose();

        return rdv;
    }

    public int validSupp(String chaine) {
        choix = 0;

        filled = false;
        frame = new JFrame(" Bienvenue dans la suppression d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        

        affichage = Box.createHorizontalBox();
        fenetre_aff = new JTextArea(" Voici le rendez-vous que vous voulez supprimer " + "\n" + chaine);
        affichage.add(fenetre_aff);

        b4 = Box.createHorizontalBox();
        button = new JButton("Supprimer");
        button.addActionListener(this::fillSupp);
        button.setSize(300, 300);

        button2 = new JButton("Annuler");
        button2.addActionListener(this::fillPasSupp);
        button2.setSize(300, 300);
        b4.add(button);
        b4.add(button2);

        b5 = Box.createVerticalBox();
        b5.add(affichage);
        b5.add(b4);

        frame.getContentPane().add(b5);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boolean fill = false;
        while (!fill) {
            synchronized (obj) {
                fill = filled;
            }
        }

        frame.dispose();

        return choix;
    }

    private Rdv fillRecherche(ActionEvent e) {
        rdv = new Rdv();

        LocalDate date = LocalDate.parse(entree_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        while(entree_date.getText().length() == 0 )
            {
                aff.affichage_nonsaisi();
                date = LocalDate.parse(entree_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } 
        
        LocalTime hDebut = LocalTime.parse(entree_hDebut.getText());
         while(entree_hDebut.getText().length() == 0 )
            {
                aff.affichage_nonsaisi();
                hDebut = LocalTime.parse(entree_hDebut.getText());
            } 

        rdv.setDate(date);
        rdv.setHdebut(hDebut);

        filled = true;

        return rdv;
    }

    private void fillSupp(ActionEvent e) {

        choix = 1;

        filled = true;

    }

    private void fillPasSupp(ActionEvent e) {

        choix = 0;

        filled = true;

    }
}
