/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import agenda.vue.TrierDeuxDatesForm;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author CALVO Manuela
 */
public class TrierDeuxDatesControleur {
    
   private TrierDeuxDatesForm choix = new TrierDeuxDatesForm();

  
    public String verifTri(Calendrier cal) {
        ArrayList<Object> critere = new ArrayList();
        ArrayList<Rdv> rdv = new ArrayList();
        String chaine = "";
        critere = choix.affichage_demandeTri();

        LocalDate date_debut = (LocalDate) critere.get(0);
        LocalDate date_fin = (LocalDate) critere.get(2);
        LocalTime h_debut = (LocalTime) critere.get(1);
        LocalTime h_fin = (LocalTime) critere.get(3);
        rdv = cal.trier_entre_deux_dates(date_debut, date_fin, h_debut, h_fin);
       
        for(Rdv rdv2 : rdv)
        {
            chaine += rdv2.toString() + "\n";
        }
        return chaine;

    }

    
}
