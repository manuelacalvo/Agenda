/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.vue.IDForm;
import java.util.ArrayList;
import java.util.Map;



/**
 *
 * @author CALVO Manuela
 */
public class IDContr {
    
    private IDForm id = new IDForm();
    public Map<String, String> remplissage_ID(ArrayList<String> liste_membre)
    {
        return id.affichage_utilisateur(liste_membre); 
        
        
        
    }
    
}
