/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import agenda.vue.AfficherRdvCritereForm;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Tatiana Meister
 */
public class AfficherRdvCritere {
    
   private AfficherRdvCritereForm choix = new AfficherRdvCritereForm();
   private Calendrier calendrier;         
     
   public String affichage_agenda(Calendrier cal) {
       this.calendrier = cal;
       String chaine = " ";
       Rdv rdv = new Rdv();
       ArrayList<Rdv> liste_rdv = new ArrayList();
       
       rdv = choix.affichage_choixcritere(chaine);
   
       
        for(Rdv _rdv : this.calendrier.getListeRdv()){
            if(rdv.getLibelle()!=null){
                if(Objects.equals(_rdv.getLibelle(), rdv.getLibelle()))
                    liste_rdv.add(_rdv);
            }
            else if(rdv.getDate()!= null){
                if(Objects.equals(_rdv.getDate(), rdv.getDate()))
                    liste_rdv.add(_rdv);
           }
            else if(rdv.getHdebut()!=null){
                if(Objects.equals(_rdv.getHdebut(), rdv.getHdebut()))
                    liste_rdv.add(_rdv);
                    
            }
       }
        choix.afficher_rdv_critere(liste_rdv);
       return chaine;
    } 
   

    
}
