/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author CALVO Manuela
 */
public class MainControleur {

    public void main() throws FileNotFoundException, IOException, Exception {

        ArrayList<String> liste_membre = new ArrayList<>();
        boolean trouve = false;
        boolean change = false;
        boolean quitter = false;
        String chaine = " ";

        File fichier = new File("fichier_personne.txt");
        if (!fichier.exists()) {
            System.out.println("le fichier  n'existe pas");

            System.exit(1);

        }

        Scanner lec_fichier = new Scanner(fichier);

        //ouverture fichier personne ==> Manuela
        while (lec_fichier.hasNextLine()) {

            String nom_test;
            String prenom_test;
            String ID_test;

            nom_test = lec_fichier.next();
            prenom_test = lec_fichier.next();

            ID_test = nom_test + " " + prenom_test;

            // sauvegarde dans une liste  Nom_Prenom 
            liste_membre.add(ID_test);

        }
        lec_fichier.close();

        while (change == false && quitter == false) {
            IDContr recup_id = new IDContr();
            Map<String, String> personne_id = recup_id.remplissage_ID(liste_membre);
            do{
                personne_id = recup_id.remplissage_ID(liste_membre);
            }while(personne_id.get("prenom").equals("retour"));
            
            String nom = personne_id.get("nom");
            String prenom = personne_id.get("prenom");
            String ID = nom + " " + prenom;
            System.out.println(ID);
            Calendrier cal = new Calendrier(nom, prenom);
            ArrayList<Rdv> liste_rdv = new ArrayList<>();

            for (int i = 0; i < liste_membre.size(); i++) {   

                if (Objects.equals(liste_membre.get(i), ID)) {

                    cal.setListeRdv(liste_rdv);
                    cal.ouverture_calendrier_data();
                    liste_rdv = cal.getListeRdv();
                    trouve = true;
                }
            }

            if (trouve == false) {

                //sinon crée un nouveau fichier pour cette personne (un nouveau calendrier)
                File fichier2 = new File(nom + "_" + prenom + ".txt");
                fichier2.createNewFile();

                cal.setListeRdv(liste_rdv);
                liste_membre.add(ID);

            }

            MenuControleur remp_choix = new MenuControleur();
            CalendrierControleur action_gene = new CalendrierControleur();
            AfficherRdvCritere aff_critere = new AfficherRdvCritere();
            AjoutRdvControleur ajout = new AjoutRdvControleur();
            ModifierRdvControleur modif = new ModifierRdvControleur();
            SuppRdvControleur supp = new SuppRdvControleur();
            RappelControleur rapp = new RappelControleur();
            
            int choix;

            if (!cal.getListeRdv().isEmpty()) {
                cal.trier_entre_deux_dates();

            }

            while (quitter == false && change == false) {
                rapp.affichage_rappel(cal);
                choix = remp_choix.remplissage_choix(chaine);
                System.out.println(liste_rdv.size());
                switch (choix) {
                    case 1:
                        chaine = action_gene.affichage_agenda(cal);
                        break;
                    case 2 :
                        chaine = aff_critere.affichage_agenda(cal);
                        break;
                    case 3 :
                        if(!cal.getListeRdv().isEmpty())liste_rdv = modif.verifRdv(cal);
                        else modif.erreur_vide();
                        cal.setListeRdv(liste_rdv);
                        break;
                    case 4 : 
                        if(!cal.getListeRdv().isEmpty())liste_rdv = supp.verifRdv(cal);
                        else supp.erreur_vide();
                        cal.setListeRdv(liste_rdv);
                        break;    
                    case 5 :
                       if(!cal.getListeRdv().isEmpty())liste_rdv = supp.vider_calendrier(cal);
                       else supp.erreur_vide();
                       cal.setListeRdv(liste_rdv);
                       break;
                    case 6:
                        cal.setListeRdv(ajout.verifRdv(cal));
                        break;
                    case 7 :
                        quitter = true;
                        cal.save();
                        break;
                    case 8:
                        cal.save();
                        personne_id = recup_id.remplissage_ID(liste_membre);
                        nom = personne_id.get("nom");
                        prenom = personne_id.get("prenom");
                        ID = nom + " " + prenom;

                        cal = new Calendrier(nom, prenom);
                        liste_rdv = new ArrayList<>();

                        for (int i = 0; i < liste_membre.size(); i++) {   

                            if (Objects.equals(liste_membre.get(i), ID)) {

                                 cal.setListeRdv(liste_rdv);
                                cal.ouverture_calendrier_data();
                                liste_rdv = cal.getListeRdv();
                                trouve = true;
                            }
                        }
                        if (trouve == false) {
                            File fichier2 = new File(nom + "_" + prenom + ".txt");
                            fichier2.createNewFile();
                            cal.setListeRdv(liste_rdv);
                            liste_membre.add(ID);
                        }
                        break;
                    case 9:
                        quitter = true;
                        cal.save();
                        break;
                }
            }

            File fichiercal = new File("fichier_personne.txt");
            if (!fichiercal.exists()) {
                System.out.println("le fichier des membres n'existe pas");

                System.exit(1);

            }
            FileWriter fichiercalWriter = new FileWriter(fichiercal);

            int i = 0;
            for (String membre : liste_membre) {
                i++;
                fichiercalWriter.write(membre);
                if (i != liste_membre.size()) {
                    fichiercalWriter.write("\r\n");
                }

            }
            fichiercalWriter.close();

        }
    }
}
