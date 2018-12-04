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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author CALVO Manuela
 */
public class AjoutRdvForm {

    private Rdv rdv;
    private Rdv rdv_ajout = new Rdv();
    private JFrame frame;
    private Box b1;
    private JLabel label_date;
    private JLabel valeur_date;
    private JTextField entree_date;
    private Box b2;
    private JLabel label_hDebut;
    private JTextField entree_hDebut;
    private JLabel valeur_hDebut;
    private JLabel label_hFin;
    private JTextField entree_hFin;
    private JLabel valeur_hFin;
    private JLabel label_Libelle;
    private JTextField entree_Libelle;
    private JLabel label_Rappel;
    private JTextField entree_Rappel;
    private Box b3;
    private JButton button;
    private Box b4;
    private Box b5;
    private Box b6;
    private Box b7;
    private boolean filled = false;
    private final Object obj = new Object();

    public Rdv affichage_demandeAjout() {

        Rdv rdv = new Rdv();
        
        filled = false;
        frame = new JFrame(" Bienvenue dans l'ajout d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);

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

        b3 = Box.createHorizontalBox();
        label_hFin = new JLabel("Heure de fin (hh:mm) ");
        label_hFin.setLocation(100, 300);
        entree_hFin = new JTextField();
        b3.add(label_hFin);
        b3.add(entree_hFin);
        b3.setSize(200, 100);

        b4 = Box.createHorizontalBox();
        button = new JButton("Valider la saisie");
        button.addActionListener(this::fillRecherche);
        button.setSize(300, 300);
        b4.add(button);

        b5 = Box.createVerticalBox();
        b5.add(b1);
        b5.add(b2);
        b5.add(b3);
        b5.add(b4);

        frame.getContentPane().add(b5);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boolean fill = false;
        while(!fill){
            synchronized(obj){
                fill = filled;
            }
        }
        LocalDate date = LocalDate.parse(entree_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime hDebut = LocalTime.parse(entree_hDebut.getText());
        LocalTime hFin = LocalTime.parse(entree_hFin.getText());
        rdv.setDate(date);
        rdv.setHdebut(hDebut);
        rdv.setHfin(hFin);
        
        frame.dispose();
        
        return rdv;
    }
    public Rdv affichage_ajoutLibelle(){
        frame = new JFrame(" Bienvenue dans la modification d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        
        String libelle = (String)JOptionPane.showInputDialog(frame, " Saisissez le nom du rendez vous à ajouter");
        String[] rappel_str = {"Oui", "Non"};
        String choix_rappel = (String) JOptionPane.showInputDialog(frame, 
        "Voulez vous un rappel",
        "Ajout Rdv",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        rappel_str, 
        rappel_str[0]);
        boolean rappel;
        if(choix_rappel.equals(rappel_str[0])) rappel = true;
        else rappel = false;
        
        Rdv rdv_temp = new Rdv();
        rdv_temp.setLibelle(libelle);
        rdv_temp.setRappel(rappel);
        return rdv_temp;
        
    }
    
    public void affichage_erreur(){
        frame = new JFrame("Erreur");
        JOptionPane.showMessageDialog(frame, "Erreur. Les rendez vous ne peuvent pas se chevaucher");
    }
    private Rdv fillRecherche(ActionEvent e) {
        rdv = new Rdv();
        Boolean _rappel;

        LocalDate date = LocalDate.parse(entree_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime hDebut = LocalTime.parse(entree_hDebut.getText());
        LocalTime hFin = LocalTime.parse(entree_hFin.getText());

        rdv.setDate(date);
        rdv.setHdebut(hDebut);
        rdv.setHfin(hFin);

        filled = true;
        
        return rdv;
    }

    private void fillAjout(ActionEvent e) {
        
        Boolean _rappel;

        LocalDate date = LocalDate.parse(entree_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime hDebut = LocalTime.parse(entree_hDebut.getText());
        LocalTime hFin = LocalTime.parse(entree_hFin.getText());
        String libelle = entree_Libelle.getText();
        int rappel = Integer.parseInt(entree_Rappel.getText());

        if (rappel == 1) {
            _rappel = true;
        } else {
            _rappel = false;
        }

        rdv_ajout.setDate(date);
        rdv_ajout.setHdebut(hDebut);
        rdv_ajout.setHfin(hFin);
        rdv_ajout.setLibelle(libelle);
        rdv_ajout.setRappel(_rappel);

        filled = true;
        
        System.out.println(rdv_ajout.toString());
        
    }

}
