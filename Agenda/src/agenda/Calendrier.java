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
public class Calendrier implements java.io.Serializable{
    
    private ArrayList<Rdv> m_liste_rdv;
    private String m_nom;
    private String m_prenom;

   
    ///CONSTRUCTEURS///
    public Calendrier()
    {
        m_liste_rdv = null;
        m_nom = " ";
        m_prenom = " ";
    }
    
    public Calendrier(String _nom, String _prenom)
    {
        m_liste_rdv = null;
        m_nom = _nom;
        m_prenom = _prenom;
    }
    
    ///GETTERS///
    public ArrayList<Rdv> getListeRdv()
    {
        return m_liste_rdv;
    }
    
    public String getNom()
    {
        return m_nom;
    }
    
    public String getPrenom()
    {
        return m_prenom;
    }
    
    ///SETTERS///
    public void setListeRdv(ArrayList<Rdv> _rdv)
    {
        m_liste_rdv = _rdv;
    }   
    public void setPrenom(String _prenom)
    {
        if(!Objects.equals(_prenom, " ")) m_prenom = _prenom;
    }
    public void setNom(String _nom)
    {
        if(!Objects.equals(_nom, " ")) m_nom = _nom;
    }
    
    ///METHODES///
    
    @Override
    public String toString()
    {
        
       String nom_prenom = " ";
 
        for(Rdv rdv : getListeRdv())
        {
            nom_prenom += rdv.toString() + "\n";
        }
       
        return nom_prenom;
    }
    
    public void trier_entre_deux_dates()
    {
        ArrayList<Rdv> liste = getListeRdv();
        Rdv vide;
        
        for(int j= 0; j<liste.size()-1; j++)
        {
            for( int i = 0; i< liste.size()-1; i++)
            {
                if(liste.get(i).getDate().isAfter( liste.get(i+1).getDate()))
                {
                vide = liste.get(i);
                liste.set(i ,liste.get(i+1));
                liste.set(i+1, vide);
                }
            }
        }
        for(Rdv rdv : liste)
        {
           String chaine = rdv.toString();
           System.out.println(chaine);
        }
        
    }
    
    public void modifier_rdv(LocalDate date_rdv, LocalTime heure_rdv)
    {
        LocalDate date;
        LocalTime h_deb;
        String _date, _h_debut, _h_fin;
        Scanner keyboard = new Scanner(System.in);
        int choix;
        boolean _rappel = false;
        String _libelle;
        String chaine;
        Rdv rdv_a_modifier = new Rdv();
        boolean trouve = false;
        ArrayList<Rdv> liste_rdv = getListeRdv();
       
        
        for(Rdv rdv : getListeRdv())
        {
            if(Objects.equals(rdv.getDate(), date_rdv)&& Objects.equals(rdv.getHdebut(),heure_rdv ))
            {
                trouve = true;
                rdv_a_modifier = rdv;
            }
            
        }
        
        if(trouve){
            
                liste_rdv.remove(rdv_a_modifier);
                
                
                System.out.println(" Saisir les nouvelles informations du rdv");
                System.out.println(" Entrer le libelle du rdv ");
                _libelle = keyboard.next();
                rdv_a_modifier.setLibelle(_libelle);
                
                
                System.out.println(" Entrer la nouvelle date au format jj/mm/aaaa");
                _date = keyboard.next();
                try {
                    date = LocalDate.parse(_date,  DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                catch(DateTimeParseException e){
                    System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                    _date = keyboard.next();
                    date = LocalDate.parse(_date,  DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                rdv_a_modifier.setDate(date);
                
                System.out.println(" Entrer la nouvelle heure de début du rdv ");
                _h_debut = keyboard.next();
                try{
                    h_deb = LocalTime.parse(_h_debut);
                }
                catch(DateTimeParseException e){
                    System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                    _h_debut = keyboard.next();
                    h_deb = LocalTime.parse(_h_debut);
                }
                rdv_a_modifier.setHdebut(h_deb);
                
                System.out.println(" Entrer la nouvelle heure de fin du rdv ");
                _h_fin = keyboard.next();
                LocalTime h_fin = LocalTime.parse(_h_fin);
                rdv_a_modifier.setHfin(h_fin);
                
                System.out.println(" Entrer 1 si vous voulez avoir un rappel sinon 0 ");
                choix = keyboard.nextInt();
                while(choix != 0 && choix!=1)
                {
                    System.out.println("Mauvaise saisie.\nEntrer 1 si vous voulez avoir un rappel sinon 0 ");
                    choix = keyboard.nextInt();
                }
                if(choix == 1)
                {
                _rappel = true;
                }
                rdv_a_modifier.setRappel(_rappel);
                
                System.out.println(" Votre rendez-vous a été modifié");
                chaine = rdv_a_modifier.toString();
                System.out.println(chaine);
                
                
                liste_rdv.add(rdv_a_modifier);
                setListeRdv(liste_rdv);
                 
            } else System.out.println("Ce rendez-vous n'existe pas.");
            
    }
   public void rappel()
   {
       LocalDate date =  LocalDate.now();
       LocalTime heure = LocalTime.now();
       Duration duree = Duration.ofMinutes(15);
       
       
       
       for(Rdv rdv : getListeRdv()){

            LocalTime rappel = rdv.getHdebut().minus(duree);
            
           if(rdv.getRappel()== true && Objects.equals(rdv.getDate(), date))
           {
               if(rappel.compareTo(heure) < 0){
               String chaine = "Rappel de votre rdv dans 15 minutes " + rdv.getLibelle();
               JOptionPane jop = new JOptionPane();
               jop.showMessageDialog(null, rdv.getLibelle()+" dans moins de 15 minutes");
               
               rdv.setRappel(false);
              // JOptionPane.showMessageDialog(null , chaine , "Afficher un message",JOptionPane.INFORMATION_MESSAGE); 
               }
           }
       }
       
   }
   public void recher_rdv()
   {
       int choix;
       ArrayList<Rdv> rdv_trouve =  new ArrayList<>();
       Scanner keyboard = new Scanner(System.in);
       String chaine;
       
       System.out.println("1. Recherche de Rendez vous par nom");
       System.out.println("2. Recherche de Rendez vous par horaire");
       System.out.println("3. Recherche de Rendez vous par date");
       
       choix = keyboard.nextInt();
      // keyboard.useDelimiter(" |\n|:|h|/");
       
       switch(choix){
            case 1 :
                System.out.println("Saisir le libelle à rechercher");
                String libelle = keyboard.next();
                
                for(Rdv rdv : getListeRdv()){
                    
                    if(Objects.equals(rdv.getLibelle(), libelle)) 
                    {
                        rdv_trouve.add(rdv);   
                    }
                    
                }
                
                break;
            case 2 :
                System.out.println("Saisir une heure de début");
                String h_deb = keyboard.next();
                LocalTime debut;
                try{
                    debut = LocalTime.parse(h_deb);
                }
                catch(DateTimeParseException e){
                    System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                    h_deb = keyboard.next();
                    debut = LocalTime.parse(h_deb);
                }
                
                System.out.println("Saisir une heure de fin");
                String h_fin = keyboard.next();
                LocalTime fin;
                try{
                    fin = LocalTime.parse(h_fin);
                }
                catch(DateTimeParseException e){
                    System.out.println("L'heure saisie n'est pas valide, veuillez recommencer.");
                    h_fin = keyboard.next();
                    fin = LocalTime.parse(h_fin);
                }
                for(Rdv rdv : getListeRdv()){
                        if(Objects.equals(rdv.getHdebut(), debut) && Objects.equals(rdv.getHfin(), fin)) rdv_trouve.add(rdv);           
                }
                break;
            case 3 :
                 LocalDate date_rch = null;
                System.out.println("Saisir la date à rechercher");
                String date = keyboard.next();
                
                try{
                    date_rch = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                catch(DateTimeParseException e){
                    System.out.println("La date saisie n'est pas valide, veuillez recommencer.");
                    date = keyboard.next();
                    date_rch = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                
                for(Rdv rdv : getListeRdv()){
                    if(Objects.equals(rdv.getDate(), date_rch)) rdv_trouve.add(rdv);
                }
                break;
            default : 
                System.out.println("Mauvaise saisie");
        }
       
        if(rdv_trouve.isEmpty())
        {
            System.out.println(" Pas de rdv trouvé");
        }
        else
        {
            for(Rdv _rdv : rdv_trouve)
                {
                 chaine = _rdv.toString();
                 System.out.println(chaine);
                 
                }
        }
        System.out.println("\n");
       
       
   }
   
  
    
    
    public void supprimer_rdv(LocalDate date_rdv, LocalTime heure_rdv)
    {
        ArrayList<Rdv> liste_rdv = getListeRdv();
        Rdv rdv_supp = null;
        //Appelle recherche rdv
        for(Rdv rdv : liste_rdv)
        {
            if(Objects.equals(rdv.getDate(), date_rdv)&& Objects.equals(rdv.getHdebut(),heure_rdv ))
            {
                rdv_supp = rdv;
            }
        }
        
        liste_rdv.remove(rdv_supp);
        setListeRdv(liste_rdv);
        //Si null, pas de rdv à supprimer
        //Sinon, Enlever le rdv de array list
        System.out.println(" Votre rendez-vous a bien été supprimé");
    }
    
    public void vider_calendrier()
    {
        ArrayList<Rdv> liste_rdv = getListeRdv();
        liste_rdv.clear();
        setListeRdv(liste_rdv);
        System.out.println(" Votre calendrier a été vidé");
    }
      public void save() throws Exception
    {
        FileOutputStream fos;
        fos = new FileOutputStream(getNom()+"_"+getPrenom()+".txt");
        
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(fos);
        
        
            oos.writeObject(getListeRdv());
        
        
        oos.close();
        System.out.println(" Votre calendrier a bien était enregistré");
        
    }
    
    public Object load() throws Exception
    {
        FileInputStream fis;
        ObjectInputStream ois;
       
        fis = new FileInputStream(getNom()+"_"+getPrenom()+".txt");
        ois = new ObjectInputStream(fis);
         System.out.println(" Dans load");
            Object obj = ois.readObject();
        ois.close();
        
        return obj;
        
        
    }
    public void ouverture_calendrier_data()
    {
        try{
            ArrayList<Rdv> rdvs;
            rdvs = (ArrayList<Rdv>) load();
            setListeRdv(rdvs);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    public void ouverture_calendrier(String nom, String prenom) throws FileNotFoundException //Tatiana
    {
        //lecture fichier
        File fichiercal = new File(nom+"_"+prenom+".txt");
        if (!fichiercal.exists()) { 
            try {
                fichiercal.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Calendrier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
        ArrayList<Rdv> liste_rdv = new ArrayList<>();
        
        Scanner lec_fichiercal = new Scanner(fichiercal);
            
        while(lec_fichiercal.hasNext())
        {
            //System.out.println("\n\nin\n");
            LocalDate date;
            LocalTime h_debut;
            LocalTime h_fin;
            String libelle;
            boolean rappel;
            String _date, _h_debut, _h_fin;
           
            _date = lec_fichiercal.next();
            date = LocalDate.parse(_date);  
 
            _h_debut = lec_fichiercal.next();        
            h_debut = LocalTime.parse(_h_debut);
           
            _h_fin = lec_fichiercal.next();
            h_fin = LocalTime.parse(_h_fin);
                
            libelle = lec_fichiercal.next();
            rappel = lec_fichiercal.nextBoolean();
                
            Rdv rdv = new Rdv(date, h_debut, h_fin, libelle, rappel);
            liste_rdv.add(rdv);       
        }
        
        setListeRdv(liste_rdv);
        if(liste_rdv.isEmpty()){
            System.out.println("Votre agenda est vide");
        }       
       
        
    }
    
    public void sauvegarde_calendrier() throws IOException // Tatiana
    {
        // Tri du calendrier entier
        //ecriture du nouveau calendrier dans fichier
        //Fermer fichier
        //Retour menu
        //lecture fichier
        File fichiercal = new File(getNom()+"_"+getPrenom()+".txt");
        if (!fichiercal.exists()) {
            System.out.println("le fichier calendrier de "+" "+" n'existe pas");

            System.exit(1);

        }
        FileWriter fichiercalWriter = new FileWriter(fichiercal);
        
        for(Rdv rdv : getListeRdv())
        {
            fichiercalWriter.write(rdv.getDate().toString());
            fichiercalWriter.write(" ");
            fichiercalWriter.write(rdv.getHdebut().toString());
            fichiercalWriter.write(" ");
            fichiercalWriter.write(rdv.getHfin().toString());
            fichiercalWriter.write(" ");
            fichiercalWriter.write(rdv.getLibelle());
            fichiercalWriter.write(" ");
            
            if(rdv.getRappel())
            fichiercalWriter.write("true");
            else fichiercalWriter.write("false");
            
            fichiercalWriter.write("\r\n");
            
        }
        fichiercalWriter.close();
       System.out.println("votre calendrier a bien été enregistré");
    }
    
}
