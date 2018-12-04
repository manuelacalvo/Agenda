/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import agenda.controleur.MainControleur;
import com.sun.prism.j2d.J2DPipeline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author CALVO Manuela MEISTER Tatiana
 */
public class Agenda {

    /**
     * @param args the command line arguments
     */
    private static String nom;
    private static String prenom;
    private static int passe = 0;
    private static String ID = null;
    private static int choix = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        
        MainControleur debut = new MainControleur();
        
        debut.main();
    }
      
}
        //déclaration variables
        //String ID = null;

       /* ArrayList<String> liste_membre = new ArrayList<>();
        
        String date;
        String heure;
        int jour, mois, annee, h, min;
        LocalDate _date = null;
        LocalTime _heure = null;

        int verif_id = 0;

       
            
            System.out.println(" Que voulez-vous faire avec votre calendrier? ");
            System.out.println(" 1. Afficher tous vos RDV ");
            System.out.println(" 2. Afficher RDV selon un critère ");
            System.out.println(" 3. Modifier un RDV ");
            System.out.println(" 4. Supprimer un RDV ");
            System.out.println(" 5. Supprimer tous les RDV ");
            System.out.println(" 6. Ajouter un RDV");
            System.out.println(" 7. Sauvegarder et fermer l'agenda ");
            System.out.println(" 8. Changer d'utilisateur ");
            System.out.println(" 9. Fermer le programme ");

            //choix = keyboard.nextInt();
            switch (choix) {
                case 1:
                    if (cal.getListeRdv().isEmpty()) {
                        System.out.println("Votre agenda est vide.");
                    } else {
                        cal.toString();
                    }
                    cal.rappel();
                    break;

                case 2:
                    cal.recher_rdv();
                    cal.rappel();
                    break;

                case 3:
                    System.out.println(" Entrer la date du RDV à modifier (format jj/mm/aaaa)");
                    date = keyboard.next();
                    try {
                        _date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (DateTimeParseException e) {
                        System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                        date = keyboard.next();
                        _date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                    System.out.println(" Entrer l'heure du RDV à modifier (hh:mm)");
                    heure = keyboard.next();
                    try {
                        _heure = LocalTime.parse(heure);
                    } catch (DateTimeParseException e) {
                        System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                        heure = keyboard.next();
                        _heure = LocalTime.parse(heure);
                    }

                    cal.modifier_rdv(_date, _heure);
                    cal.rappel();
                    break;

                case 4:
                    boolean rdv_trouve = false;
                    if (cal.getListeRdv().isEmpty()) {
                        rdv_trouve = true;
                    }
                    while (!rdv_trouve) {
                        System.out.println(" Entrer la date du RDV à suprimer (format jj/mm/aaaa)");
                        date = keyboard.next();
                        try {
                            _date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (DateTimeParseException e) {
                            System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                            date = keyboard.next();
                            _date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        }

                        System.out.println(" Entrer l'heure du RDV à supprimer (hh:mm)");
                        heure = keyboard.next();
                        try {
                            _heure = LocalTime.parse(heure);
                        } catch (DateTimeParseException e) {
                            System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                            heure = keyboard.next();
                            _heure = LocalTime.parse(heure);
                        }

                        for (Rdv rdv : cal.getListeRdv()) {
                            if (Objects.equals(rdv.getDate(), _date) && Objects.equals(rdv.getHdebut(), _heure)) {
                                System.out.println(" Vous allez supprimer ce rendez-vous : ");
                                String chaine = rdv.toString();
                                System.out.println(chaine);

                                rdv_trouve = true;
                            }
                        }
                    }

                    if (!cal.getListeRdv().isEmpty()) {
                        cal.supprimer_rdv(_date, _heure);
                    }
                    cal.rappel();
                    break;

                case 5:
                    cal.vider_calendrier();
                    break;

                case 6:
                    cal.ajouter_rdv();
                    cal.rappel();
                    break;

                case 7:
                    //cal.sauvegarde_calendrier();
                    cal.save();
                    break;

                case 8:
                    // cal.sauvegarde_calendrier();
                    cal.save();
                    System.out.println(" Bienvenu dans la gestion d'Agenda ");
                    System.out.println(" Comment vous appellez-vous? (Nom puis prénom) ");
                    nom = keyboard.next();
                    prenom = keyboard.next();

                    ID = nom + " " + prenom;
                    System.out.println("Etes vous bien " + ID + " ?\n 1. Oui\n 2. Non");
                    verif_id = keyboard.nextInt();
                    while (verif_id != 1) {
                        System.out.println(" Comment vous appellez-vous? (Nom Prénom) ");
                        nom = keyboard.next();
                        prenom = keyboard.next();

                        ID = nom + " " + prenom;

                        System.out.println("Etes vous bien " + ID + " ?\n 1. Oui\n 2. Non");
                        verif_id = keyboard.nextInt();
                    }

                    cal = new Calendrier(nom, prenom);
                    for (int i = 0; i < liste_membre.size(); i++) {
                        if (Objects.equals(liste_membre.get(i), ID)) {
                            System.out.println("Nous avons trouvé votre calendrier");
                            ArrayList<Rdv> liste_rdv = new ArrayList<>();
                            cal.setListeRdv(liste_rdv);
                            cal.ouverture_calendrier_data();
                            trouve = true;
                        }
                    }

                    if (trouve == false) {
                        System.out.println("Nous n'avons pas trouvé de calendrier à votre nom, nous allons en créer un");
                        //sinon crée un nouveau fichier pour cette personne (un nouveau calendrier)
                        //cal.ouverture_calendrier(nom, prenom);
                        File fichier2 = new File(nom + "_" + prenom + ".txt");
                        fichier2.createNewFile();
                        ArrayList<Rdv> liste_rdv = new ArrayList<>();
                        cal.setListeRdv(liste_rdv);
                        cal.ouverture_calendrier_data();
                        liste_membre.add(ID);
                    }
                    break;

                case 9:
                    quitter = true;
                    cal.save();
                    break;
                default:
                    System.out.println("Ce choix n'existe pas.");
                    break;
            };

        
    

    File fichiercal = new File("fichier_personne.txt");

    if (!fichiercal.exists () 
        ) {
            System.out.println("le fichier des membres n'existe pas");

        System.exit(1);

    }
    FileWriter fichiercalWriter = new FileWriter(fichiercal);

    int i = 0;
    for(String membre : liste_membre

    
        )
        {
            i++;
        fichiercalWriter.write(membre);
        if (i != liste_membre.size()) {
            fichiercalWriter.write("\r\n");
        }

    }

    fichiercalWriter.close ();

    System.out.println (

    "Les utilisateurs ont bien été enregistrés");
    keyboard.close ();

}*/
       
    


