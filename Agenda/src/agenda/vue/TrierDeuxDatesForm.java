/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import agenda.Rdv;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class TrierDeuxDatesForm {

    private Rdv rdv;
    private Rdv rdv_supp = new Rdv();
    private JFrame frame;
    private Box b1;
    private JLabel label_date_debut;
    private JLabel label_date_fin;
    private JTextField entree_date_debut;
    private JTextField entree_date_fin;
    private Box b2;
    private Box b3;
    private JLabel label_hDebut;
    private JLabel label_hFin;
    private JTextField entree_hDebut;
    private JTextField entree_hFin;
    private JButton button;
    private Box b4;
    private Box b5;
    private Box b6;
    private boolean filled = false;
    private final Object obj = new Object();
    private ArrayList<Object> critere;
    private CalendrierAff aff = new CalendrierAff();

    public ArrayList<Object> affichage_demandeTri() {

        rdv = new Rdv();

        filled = false;
        frame = new JFrame(" Bienvenue dans le tri entre deux dates des rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        
        b1 = Box.createHorizontalBox();
        label_date_debut = new JLabel("Date de debut de période ( jj/MM/yyyy)");
        entree_date_debut = new JTextField();
        b1.add(label_date_debut);
        b1.add(entree_date_debut);

        b2 = Box.createHorizontalBox();
        label_hDebut = new JLabel("Heure de début de période (hh:mm) ");
        label_hDebut.setLocation(100, 300);
        entree_hDebut = new JTextField();
        b2.add(label_hDebut);
        b2.add(entree_hDebut);
        b2.setSize(200, 100);

        b3 = Box.createHorizontalBox();
        label_date_fin = new JLabel("Date de fin de période ( jj/MM/yyyy)");
        entree_date_fin = new JTextField();
        b3.add(label_date_fin);
        b3.add(entree_date_fin);

        b4 = Box.createHorizontalBox();
        label_hFin = new JLabel("Heure de début de fin (hh:mm) ");
        label_hFin.setLocation(100, 300);
        entree_hFin = new JTextField();
        b4.add(label_hFin);
        b4.add(entree_hFin);
        b4.setSize(200, 100);

        b5 = Box.createHorizontalBox();
        button = new JButton("Valider la saisie");
        button.addActionListener(this::fillTrier);
        button.setSize(300, 300);
        b5.add(button);

        b6 = Box.createVerticalBox();
        b6.add(b1);
        b6.add(b2);
        b6.add(b3);
        b6.add(b4);
        b6.add(b5);

        frame.getContentPane().add(b6);
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

        return critere;

    }

    private ArrayList<Object> fillTrier(ActionEvent e) {

        critere = new ArrayList();

        LocalDate date_debut = LocalDate.parse(entree_date_debut.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        while (entree_date_debut.getText().length() == 0) {
            aff.affichage_nonsaisi();
            date_debut = LocalDate.parse(entree_date_debut.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        LocalDate date_fin = LocalDate.parse(entree_date_fin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        while (entree_date_fin.getText().length() == 0) {
            aff.affichage_nonsaisi();
            date_fin = LocalDate.parse(entree_date_fin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        LocalTime h_debut = LocalTime.parse(entree_hDebut.getText());
        while (entree_hDebut.getText().length() == 0) {
            aff.affichage_nonsaisi();
            h_debut = LocalTime.parse(entree_hDebut.getText());
        }

        LocalTime h_fin = LocalTime.parse(entree_hFin.getText());
        while (entree_hFin.getText().length() == 0) {
            aff.affichage_nonsaisi();
            h_fin = LocalTime.parse(entree_hFin.getText());
        }

        critere.add(date_debut);
        critere.add(h_debut);
        critere.add(date_fin);
        critere.add(h_fin);

        filled = true;

        return critere;
    }
}
