/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.vue.CalendrierAff;

/**
 *
 * @author CALVO Manuela
 */
public class CalendrierControleur {

    CalendrierAff aff = new CalendrierAff();

    public String affichage_agenda(Calendrier cal) {
        String chaine = " ";
        
        if (cal.getListeRdv().isEmpty()) {
            chaine = "Votre agenda est vide.";
        } else {

            chaine = cal.toString();

        }

        System.out.println(chaine);
        return chaine;
    }

    public void affichage_nonsaisi() {
        aff.affichage_nonsaisi();
    }
    
    public void affichage_fichier_non() {
        aff.affichage_nonfichier();
    }
}
