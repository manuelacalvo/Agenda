/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import agenda.Calendrier;
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
public class ModifierRdvForm {
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
    private CalendrierAff aff = new CalendrierAff();
    
    public LocalDate affichage_demandeModif(Calendrier cal) {

        rdv = new Rdv();
        LocalDate date_rdv_amodif;
        filled = false;
        frame = new JFrame(" Bienvenue dans la modification d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        

        String _date =  (String)JOptionPane.showInputDialog(frame, " Saisissez la date du rendez vous à modifier");
        
        while(_date.length() == 0)
            {
                aff.affichage_nonsaisi();
                 _date = (String)JOptionPane.showInputDialog(frame, " Saisissez la date du rendez vous à modifier");
            } 
        date_rdv_amodif = LocalDate.parse(_date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date_rdv_amodif;
    }
    public Rdv affichage_libelle_rdv_modif(String[] libelles_rch, LocalDate date){
        
        
        String choix_libelle = (String) JOptionPane.showInputDialog(frame, 
        "Quel rdv souhaitez vous modifier",
        "Recherche Rdv",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        libelles_rch, 
        libelles_rch[0]);
        
        rdv.setDate(date);
        rdv.setLibelle(choix_libelle);
        
        return rdv;
    }
    public void affichage_erreur(){
        frame = new JFrame("Erreur");
        JOptionPane.showMessageDialog(frame, "Erreur. Les rendez vous ne peuvent pas se chevaucher");
    }
    
    public Rdv affichage_ModifRdv() {

        
        
        filled = false;
        frame = new JFrame(" Bienvenue dans l'ajout d'un rendez-vous ");
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

        b3 = Box.createHorizontalBox();
        label_hFin = new JLabel("Heure de fin (hh:mm) ");
        label_hFin.setLocation(100, 300);
        entree_hFin = new JTextField();
        b3.add(label_hFin);
        b3.add(entree_hFin);
        b3.setSize(200, 100);

        b4 = Box.createHorizontalBox();
        label_Libelle = new JLabel("Libelle ");
        label_Libelle.setLocation(100, 300);
        entree_Libelle = new JTextField();
        b4.add(label_Libelle);
        b4.add(entree_Libelle);
        b4.setSize(200, 100);

        b5 = Box.createHorizontalBox();
        label_Rappel = new JLabel("Rappel ( 0 si non 1 si oui) ");
        label_Rappel.setLocation(100, 300);
        entree_Rappel = new JTextField();
        b5.add(label_Rappel);
        b5.add(entree_Rappel);
        b5.setSize(200, 100);

        b6 = Box.createHorizontalBox();
        button = new JButton("Valider la saisie");
        button.addActionListener(this::fillModif);
        button.setSize(300, 300);
        b6.add(button);

        b7 = Box.createVerticalBox();
        b7.add(b1);
        b7.add(b2);
        b7.add(b3);
        b7.add(b4);
        b7.add(b5);
        b7.add(b6);

        frame.getContentPane().add(b7);
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
        frame.dispose();
        
        return rdv_ajout;
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
     
      private void fillModif(ActionEvent e) {
        
        Boolean _rappel = false;

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
        
        LocalTime hFin = LocalTime.parse(entree_hFin.getText());
        while(entree_hFin.getText().length() == 0 )
            {
                aff.affichage_nonsaisi();
                hFin = LocalTime.parse(entree_hFin.getText());
            } 
        
        String libelle = entree_Libelle.getText();
        while(entree_Libelle.getText().length() == 0 )
            {
                aff.affichage_nonsaisi();
                libelle = entree_Libelle.getText();
            } 
        
        int rappel = Integer.parseInt(entree_Rappel.getText());
         while(entree_Rappel.getText().length() == 0 || entree_Rappel.getText() != "0" || entree_Rappel.getText() != "1" )
            {
                aff.affichage_nonsaisi();
                rappel = Integer.parseInt(entree_Rappel.getText());
            }
         
        if (rappel == 1) {
            _rappel = true;
        } 

        rdv_ajout.setDate(date);
        rdv_ajout.setHdebut(hDebut);
        rdv_ajout.setHfin(hFin);
        rdv_ajout.setLibelle(libelle);
        rdv_ajout.setRappel(_rappel);

        filled = true;
        
        
        
    }
}
