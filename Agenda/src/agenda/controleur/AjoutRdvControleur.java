/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Rdv;
import agenda.Calendrier;
import agenda.vue.AjoutRdvForm;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author CALVO Manuela
 */
public class AjoutRdvControleur {
    private AjoutRdvForm choix = new AjoutRdvForm();
            
            
    public Rdv remplissage_RechercheRdv()
    {
        return choix.affichage_demandeAjout();
     
    }
    
    public ArrayList<Rdv> verifRdv(Calendrier cal)
    {
        Rdv rdv = remplissage_RechercheRdv();
        
       boolean trouve = true;
       ArrayList<Rdv> liste_rdv = cal.getListeRdv();
        for(Rdv rdv2 : cal.getListeRdv()){
            if(Objects.equals(rdv.getDate(), rdv2.getDate())){
                if(rdv.getHdebut().isAfter(rdv2.getHdebut())){
                    if(rdv.getHdebut().isBefore(rdv2.getHfin())) trouve = false;
                }
                else if (rdv.getHfin().isAfter(rdv2.getHdebut())){
                    if(rdv.getHfin().isBefore(rdv2.getHfin())) trouve = false;
                }
                if(rdv2.getHfin().isBefore(rdv.getHfin())){
                   if(rdv2.getHdebut().isAfter(rdv.getHdebut())) trouve = false;
                }
            }           
        }
        if (trouve){
            
         Rdv rdv_temp = choix.affichage_ajoutLibelle();
         rdv.setLibelle(rdv_temp.getLibelle());
         rdv.setRappel(rdv_temp.getRappel());
         liste_rdv.add(rdv);
            
        }
        else choix.affichage_erreur();
        
        return liste_rdv;
        
    }
    
    
}
