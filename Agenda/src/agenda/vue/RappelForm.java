/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import agenda.Rdv;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tatiana Meister
 */
public class RappelForm {
 
    private JFrame frame;
    
    
    public void affichage_utilisateur(Rdv rdv)
    {
       
         
        frame = new JFrame(" Bienvenue dans la gestion d'Agenda ");
        frame.setPreferredSize(new Dimension(800, 600));
       
        JOptionPane.showMessageDialog(frame, rdv.getLibelle()+" dans 15 minutes");
  
    }
   
}
