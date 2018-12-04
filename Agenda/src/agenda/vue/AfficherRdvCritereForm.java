/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import agenda.Rdv;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Tatiana Meister
 */
public class AfficherRdvCritereForm {
    
    private Rdv rdv_rch = new Rdv();
    private JFrame frame;
    private JFrame frame2;
    private boolean filled;
    public static final String[] choix_str = { "libelle", "date", "heure de début"};
    CalendrierAff aff = new CalendrierAff();
    
    public Rdv affichage_choixcritere(String chaine) {

        Rdv rdv = new Rdv();
        
        filled = false;
        frame = new JFrame(" Bienvenue dans la recherche d'un rendez-vous ");
        frame.setPreferredSize(new Dimension(800, 600));
        
        String choixcritere = (String) JOptionPane.showInputDialog(frame, 
        "Sur quel critere souhaitez vous effectuer votre recherche?",
        "Recherche Rdv",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        choix_str, 
        choix_str[0]);
        if (choixcritere.equals(choix_str[0])){
            afficher_saisie_libelle();
        }
        else if (choixcritere.equals(choix_str[1])){
            afficher_saisie_date();
        }
        if (choixcritere.equals(choix_str[2])){
            afficher_saisie_HDebut();
        }
        return rdv_rch;
    }
    public Rdv afficher_saisie_libelle(){
        
        int i = 1;
        filled = false;
        frame2 = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame2.setPreferredSize(new Dimension(800, 600));
       
        frame2.setAlwaysOnTop(true);
        
        
           
             String libelle = JOptionPane.showInputDialog(frame2, "Saisir libelle à rechercher");
             
            while(libelle.isEmpty())
            {
                aff.affichage_nonsaisi();
                libelle = JOptionPane.showInputDialog(frame2, "Saisir libelle à rechercher");
            }
        
        
        rdv_rch.setLibelle(libelle);
        
        return rdv_rch;
        
    }
    public void afficher_rdv_critere(ArrayList<Rdv> rdv_aff){
        JFrame frame3;
        frame3 = new JFrame(" Recherche RDV ");
        frame3.setPreferredSize(new Dimension(800, 600));
        
        frame3.setAlwaysOnTop(true);
        
        if(rdv_aff!=null)JOptionPane.showMessageDialog(frame3, rdv_aff.toString());
        else JOptionPane.showMessageDialog(frame3, "Aucun Rendez vous trouvé");
        
    }
    public Rdv afficher_saisie_date(){
        
        int i = 2;
        
        filled = false;
        frame2 = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame2.setPreferredSize(new Dimension(800, 600));
        
        frame2.setAlwaysOnTop(true);
        
        String string_date = JOptionPane.showInputDialog(frame2, "Saisir la date à rechercher (jj/mm/aaaa");
        
        while(string_date.isEmpty())
            {
                aff.affichage_nonsaisi();
                string_date = JOptionPane.showInputDialog(frame2, "Saisir la date à rechercher (jj/mm/aaaa");
            }
        LocalDate date = LocalDate.parse(string_date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        rdv_rch.setDate(date);
        
        return rdv_rch;
        
    }
    public Rdv afficher_saisie_HDebut(){
        
        int i = 3;
        
        filled = false;
        frame2 = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame2.setPreferredSize(new Dimension(800, 600));
        
        frame2.setAlwaysOnTop(true);
        
        String string_Hdeb = JOptionPane.showInputDialog(frame2, "Saisir l'heure de début à rechercher (hh:mm)");
        
         while(string_Hdeb.isEmpty())
            {
                aff.affichage_nonsaisi();
                string_Hdeb = JOptionPane.showInputDialog(frame2, "Saisir l'heure de début à rechercher (hh:mm)");
            }
        LocalTime hDeb = LocalTime.parse(string_Hdeb);
        rdv_rch.setHdebut(hDeb);
        
        return rdv_rch;
        
    }   
}

