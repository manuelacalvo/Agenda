/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controleur;

import agenda.Calendrier;
import agenda.Rdv;
import agenda.vue.ModifierRdvForm;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author CALVO Manuela
 */
public class ModifierRdvControleur {

    private ModifierRdvForm choix = new ModifierRdvForm();
    private LocalDate _date;

    public Rdv liste_rch(Calendrier cal, LocalDate date) {
        ArrayList<Rdv> liste_rdv_rch = new ArrayList();
        for (Rdv rdv : cal.getListeRdv()) {
            if (rdv.getDate().equals(date)) {
                liste_rdv_rch.add(rdv);
            }
        }
        String[] liste_libelle = new String[liste_rdv_rch.size()];
        for (int i = 0; i < liste_libelle.length; i++) {
            liste_libelle[i] = liste_rdv_rch.get(i).getLibelle();
        }
        return choix.affichage_libelle_rdv_modif(liste_libelle, date);
    }

    public LocalDate remplissage_RechercheRdv(Calendrier cal) {
        return choix.affichage_demandeModif(cal);

    }

    public ArrayList<Rdv> verifRdv(Calendrier cal) {
        String[] liste_libelle = new String[cal.getListeRdv().size()];
        for (int i = 0; i < liste_libelle.length; i++) {
            liste_libelle[i] = cal.getListeRdv().get(i).getLibelle();
        }
        _date = remplissage_RechercheRdv(cal);
        Rdv rdv_rch = liste_rch(cal, _date);
        Rdv rdv = new Rdv();
        boolean trouve = false;
        for (Rdv _rdv : cal.getListeRdv()) {
            if (_rdv.getDate().equals(rdv_rch.getDate()) && _rdv.getLibelle().equals(rdv_rch.getLibelle())) {
                rdv = _rdv;
                trouve = true;
            }
        }
        ArrayList<Rdv> liste_rdv = cal.getListeRdv();

        if (trouve) {
            for (Rdv rdv2 : cal.getListeRdv()) {
                if (Objects.equals(rdv.getDate(), rdv2.getDate())) {
                    if (rdv.getHdebut().isAfter(rdv2.getHdebut())) {
                        if (rdv.getHdebut().isBefore(rdv2.getHfin())) {
                            trouve = false;
                        }
                    } else if (rdv.getHfin().isAfter(rdv2.getHdebut())) {
                        if (rdv.getHfin().isBefore(rdv2.getHfin())) {
                            trouve = false;
                        }
                    } else if (rdv.getHdebut().isBefore(rdv2.getHdebut())) {
                        if (rdv.getHfin().isAfter(rdv2.getHfin())) {
                            trouve = false;
                        }
                    }
                }
            }

        }
        if (trouve) {
            liste_rdv.remove(rdv);
            Rdv rdv_ajout = this.remplissage_ModifRdv();
            liste_rdv.add(rdv_ajout);
        } else {
            choix.affichage_erreur();
        }
        return liste_rdv;

    }

    public Rdv remplissage_ModifRdv() {
        return choix.affichage_ModifRdv();

    }

}
