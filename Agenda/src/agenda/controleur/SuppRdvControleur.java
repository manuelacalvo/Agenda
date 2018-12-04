/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import agenda.vue.SuppRdvForm;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author CALVO Manuela
 */
public class SuppRdvControleur {

    private SuppRdvForm choix = new SuppRdvForm();

    public Rdv remplissage_RechercheRdv() {
        return choix.affichage_demandeSupp();

    }

    public ArrayList<Rdv> verifRdv(Calendrier cal) {
        Rdv rdv = remplissage_RechercheRdv();
        Rdv rdv_a_supprimer = new Rdv();
        boolean trouve = false;
        int supp = 0;
        ArrayList<Rdv> liste_rdv = cal.getListeRdv();

        for (Rdv rdv2 : liste_rdv) {
            if (Objects.equals(rdv.getDate(), rdv2.getDate()) && Objects.equals(rdv.getHdebut(), rdv2.getHdebut())) {
                rdv_a_supprimer = rdv2;

                trouve = true;
            }
        }
        if (trouve == true) {
            
            supp = choix.validSupp(rdv_a_supprimer.toString());
            
            if(supp == 1)
            {
                liste_rdv.remove(rdv_a_supprimer);
            }
            

        }

        return liste_rdv;

    }
    
    public ArrayList<Rdv> vider_calendrier(Calendrier cal){
        ArrayList<Rdv> liste_rdv = cal.getListeRdv();
        liste_rdv.clear();
        return liste_rdv;
    }
    
}
