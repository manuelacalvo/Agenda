/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CALVO Manuela
 */
public class CalendrierAff {
    JFrame frame ;
    public void affichage_nonsaisi()
    {
        frame = new JFrame("Attention ");
        JOptionPane.showMessageDialog(frame, " Veuillez remplir tous les champs ");
    }
    
    public void affichage_nonfichier()
    {
        frame = new JFrame("Attention ");
        JOptionPane.showMessageDialog(frame, " Fichier non trouvé ");
    }
}
