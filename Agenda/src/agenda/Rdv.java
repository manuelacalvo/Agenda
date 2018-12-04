/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.util.converter.LocalDateStringConverter;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author CALVO Manuela MEISTER Tatiana
 */
public class Rdv implements java.io.Serializable{
    private LocalDate m_date;
    private LocalTime m_h_debut;
    private LocalTime m_h_fin;
    private String m_libelle;
    private boolean m_rappel;
    
    public Rdv()
    {
        m_rappel = false;
        m_libelle = " ";   
    }
    
    public Rdv( LocalDate _date, LocalTime _h_debut, LocalTime _h_fin, String _libelle, boolean _rappel)
    {
        setDate(_date);
        setHdebut(_h_debut);
        setHfin(_h_fin);
        setLibelle(_libelle);
        setRappel(_rappel);
        
    }
    
   
    
    @Override
    public String toString()
    {
        String chaine ="";
        if(getRappel()) chaine = " Libelle = " + getLibelle()+ " Date = "+getDate()+" Heure début = " + getHdebut() + " Heure fin = " + getHfin() + " Rappel = Oui ";
        else  chaine = " Libelle = " + getLibelle()+ " Date = "+getDate()+ " Heure début = " + getHdebut() + " Heure fin = " + getHfin() + " Rappel = Non";
        return chaine;
    }
    
    public LocalDate getDate()
    {
        return m_date;
    }
    
    public LocalTime getHdebut()
    {
        return m_h_debut;
    }
    
    public LocalTime getHfin()
    {
        return m_h_fin;
    }
    
    public String getLibelle()
    {
        return m_libelle;
    }
    
    public boolean getRappel()
    {
        return m_rappel;
    }
    
    public final void setDate( LocalDate _date)
    {
        m_date = _date;
    }
    
    public final void setHdebut( LocalTime _h_debut)
    {
        m_h_debut = _h_debut;
    }
    
    public final void setHfin( LocalTime _h_fin)
    {
        if(_h_fin.isAfter(m_h_debut)){
            m_h_fin = _h_fin;
        }
        else{
            
            m_h_fin = m_h_debut.plusHours(2);
        }
    }
    
    public final void setLibelle ( String _libelle)
    {
        if(_libelle != " " && _libelle.length()< 30)
        m_libelle = _libelle;
    }
    
    public final void setRappel ( boolean _rappel)
    {
        m_rappel = _rappel;
    }
    
    
    
}
