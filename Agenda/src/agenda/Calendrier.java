/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.File;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.System.out;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;
import java.util.Collections;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

/**
 *
 * @author CALVO Manuela MEISTER Tatiana
 */
public class Calendrier implements java.io.Serializable {

    private ArrayList<Rdv> m_liste_rdv;
    private String m_nom;
    private String m_prenom;

    ///CONSTRUCTEURS///
    public Calendrier() {
        m_liste_rdv = null;
        m_nom = " ";
        m_prenom = " ";
    }

    public Calendrier(String _nom, String _prenom) {
        m_liste_rdv = null;
        m_nom = _nom;
        m_prenom = _prenom;
    }

    ///GETTERS///
    public ArrayList<Rdv> getListeRdv() {
        return m_liste_rdv;
    }

    public String getNom() {
        return m_nom;
    }

    public String getPrenom() {
        return m_prenom;
    }

    ///SETTERS///
    public void setListeRdv(ArrayList<Rdv> _rdv) {
        m_liste_rdv = _rdv;
    }

    public void setPrenom(String _prenom) {
        if (!Objects.equals(_prenom, " ")) {
            m_prenom = _prenom;
        }
    }

    public void setNom(String _nom) {
        if (!Objects.equals(_nom, " ")) {
            m_nom = _nom;
        }
    }

    ///METHODES///
    @Override
    public String toString() {

        String nom_prenom = " ";

        for (Rdv rdv : getListeRdv()) {
            nom_prenom += rdv.toString() + "\n";
        }

        return nom_prenom;
    }

    public void trier() {
        ArrayList<Rdv> liste = getListeRdv();
        Rdv vide;

        for (int j = 0; j < liste.size() - 1; j++) {
            for (int i = 0; i < liste.size() - 1; i++) {
                if (liste.get(i).getDate().isAfter(liste.get(i + 1).getDate())) {
                    vide = liste.get(i);
                    liste.set(i, liste.get(i + 1));
                    liste.set(i + 1, vide);

                }

            }
        }

        for (int j = 0; j < liste.size() - 1; j++) {
            for (int i = 0; i < liste.size() - 1; i++) {
                if (liste.get(i).getDate().isEqual(liste.get(i + 1).getDate())) {
                    if (liste.get(i).getHdebut().compareTo(liste.get(i + 1).getHdebut()) > 0) {
                        
                        vide = liste.get(i);
                        liste.set(i, liste.get(i + 1));
                        liste.set(i + 1, vide);
                    }

                }

            }
        }
       

    }

    public ArrayList<Rdv> trier_entre_deux_dates(LocalDate date_debut, LocalDate date_fin, LocalTime h_debut, LocalTime h_fin) {
        trier();
        ArrayList<Rdv> rdv_entre_date = new ArrayList<>();

        for (Rdv rdv : getListeRdv()) {
            if (rdv.getDate().compareTo(date_debut) >= 0 && rdv.getDate().compareTo(date_fin) <= 0) {

                if (rdv.getDate().compareTo(date_debut) == 0 && rdv.getHdebut().compareTo(h_debut) >= 0) {
                    rdv_entre_date.add(rdv);
                } else if (rdv.getDate().compareTo(date_fin) == 0 && rdv.getHfin().compareTo(h_fin) <= 0) {
                    rdv_entre_date.add(rdv);
                } else if (rdv.getDate().compareTo(date_debut) != 0 && rdv.getDate().compareTo(date_fin) != 0) {
                    rdv_entre_date.add(rdv);
                }

            }
        }

        return rdv_entre_date;
    }

    
   
   

    
   

    public void save() throws Exception {
        FileOutputStream fos;
        fos = new FileOutputStream(getNom() + "_" + getPrenom() + ".txt");

        ObjectOutputStream oos;
        oos = new ObjectOutputStream(fos);

        oos.writeObject(getListeRdv());

        oos.close();
       

    }

    public Object load() throws Exception {
        FileInputStream fis;
        ObjectInputStream ois;

        fis = new FileInputStream(getNom() + "_" + getPrenom() + ".txt");
        ois = new ObjectInputStream(fis);
        
        Object obj = ois.readObject();
        ois.close();

        return obj;

    }

    public void ouverture_calendrier_data() {
        try {
            ArrayList<Rdv> rdvs;
            rdvs = (ArrayList<Rdv>) load();
            setListeRdv(rdvs);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    

   
}
