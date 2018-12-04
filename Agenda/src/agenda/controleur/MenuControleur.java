/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.vue.IDForm;
import agenda.vue.MenuForm;

/**
 *
 * @author CALVO Manuela
 */
public class MenuControleur {
    private MenuForm choix = new MenuForm();
            
            
    public int remplissage_choix(String chaine)
    {
       return choix.affichage_Menu(chaine);
        
        
      
    }
    
   
}
