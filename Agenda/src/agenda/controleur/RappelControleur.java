/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import agenda.vue.RappelForm;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

/**
 *
 * @author Tatiana Meister
 */
public class RappelControleur {
    private RappelForm rappel = new RappelForm();
    
    public void affichage_rappel(Calendrier cal){
        for(Rdv rdv : cal.getListeRdv()){
            
            if(rdv.getDate().equals(LocalDate.now())){
                long a = MINUTES.between(rdv.getHdebut(), LocalTime.now());
                if(a==-14){ 
                    rappel.affichage_utilisateur(rdv);
                }
            }
        }
    }
    
    
}
