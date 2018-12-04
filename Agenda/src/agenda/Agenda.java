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
     * @throws java.lang.Exception
     */
   

    public static void main(String[] args) throws Exception  {
        
        MainControleur debut = new MainControleur();
        
        debut.main();
    }
      
}
        
    


