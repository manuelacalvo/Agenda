/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CALVO Manuela
 */
public class IDForm {
    private Map<String, String> ID;
    private JFrame frame;
    private boolean filled = false;
    private CalendrierAff aff = new CalendrierAff();
    
    public Map<String, String> affichage_utilisateur(ArrayList<String> liste_membre)
    {
        filled = false;
        ID = new HashMap<>();
        String saisie_prenom = null;
        String saisie_nom = null;
        String[] liste = new String[liste_membre.size()+1];
        liste[0] = "Créer un nouvel utilisateur";
        for(int i = 1; i<liste.length; i++){
            liste[i] = liste_membre.get(i-1);
        }
        frame = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame.setPreferredSize(new Dimension(800, 600));
        
        String choix = (String)JOptionPane.showInputDialog(frame, "Qui êtes vous ?", "Identification", JOptionPane.QUESTION_MESSAGE, null, liste, liste[0]);
        if (choix.equals(liste[0])){
            
            saisie_nom = JOptionPane.showInputDialog(frame, "Saisissez votre nom");
           while(saisie_nom.length() == 0 )
            {
                aff.affichage_nonsaisi();
                saisie_nom = JOptionPane.showInputDialog(frame, "Saisissez votre nom");
            } 
           
            saisie_prenom = JOptionPane.showInputDialog(frame, "Saisissez votre prenom");
            while(saisie_prenom.length() == 0 )
            {
                aff.affichage_nonsaisi();
                saisie_prenom = JOptionPane.showInputDialog(frame, "Saisissez votre prenom");
            } 
            
        }else{
            int index_separation = choix.indexOf(" ");
            saisie_nom = choix.substring(0, index_separation);
            saisie_prenom = choix.substring(index_separation+1, choix.length());
           
        }
        ID.put("prenom", saisie_prenom);
        ID.put("nom", saisie_nom); 
        return ID;
    }
    
}
