/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import static agenda.vue.AfficherRdvCritereForm.choix_str;
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
        JOptionPane jop1 = new JOptionPane();
        JOptionPane jop2 = new JOptionPane();
        JOptionPane jop3 = new JOptionPane();
        frame = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        String choix = (String)jop1.showInputDialog(frame, "Qui êtes vous ?", "Identification", JOptionPane.QUESTION_MESSAGE, null, liste, liste[0]);
        if (jop1.CANCEL_OPTION == 0){
            System.out.println("in");
        }
        if (choix.equals(liste[0])){
            saisie_nom = jop2.showInputDialog(frame, "Saisissez votre nom");
            if (jop2.CANCEL_OPTION == 0){
                System.out.println("in");
            }
            saisie_prenom = jop3.showInputDialog(frame, "Saisissez votre prenom");
            if (jop3.CANCEL_OPTION == 0){
                System.out.println("in");
            }
            
        }else{
            int index_separation = choix.indexOf(" ");
            saisie_nom = choix.substring(0, index_separation);
            saisie_prenom = choix.substring(index_separation+1, choix.length());
            System.out.println(saisie_nom+" "+saisie_prenom);
        }
        ID.put("prenom", saisie_prenom);
        ID.put("nom", saisie_nom); 
        
        for(String str : liste_membre){
            if (str.equals(saisie_nom+" "+saisie_prenom) && choix.equals(liste[0])) affichage_existant();
        }
        return ID;
    }
    public void affichage_existant(){
        frame = new JFrame("Nouvel Utilisateur");
        JOptionPane.showMessageDialog(frame, "Vous avez déjà un calendrier");
    }
    
}
