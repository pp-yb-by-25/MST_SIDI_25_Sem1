package MODULE_1_JAVA.TP2_MST_SIDI_TA_2026___BAHIDA_YOUSSEF.Partie_2;


import java.awt.event.*;
import javax.swing.*;

import MODULE_1_JAVA.TP2_MST_SIDI_TA_2026___BAHIDA_YOUSSEF.Partie_1.Formulaire;


public class FormulaireMenu extends Formulaire{


    public FormulaireMenu()
    {
        super();
        this.setTitle("Formulaire avec Menu - MST SIDI 2026");
        this.setLocation(400, 150);
        
        //Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();
        
        //Création des menus
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuAide = new JMenu("Aide");
        
        //Création des éléments de menu
        JMenuItem itemEnregistrer = new JMenuItem("Enregistrer");
        JMenuItem itemEffacer = new JMenuItem("Effacer");
        JMenuItem itemQuitter = new JMenuItem("Quitter");

        JMenuItem itemQuestion = new JMenuItem("Question");
        JMenuItem itemApropos = new JMenuItem("À propos");
        
        //Ajout des éléments aux menus
        menuFichier.add(itemEnregistrer);
        menuFichier.addSeparator();
        menuFichier.add(itemEffacer);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);
        menuAide.add(itemQuestion);
        menuAide.addSeparator();
        menuAide.add(itemApropos);
        
        //Ajout des menus à la barre de menu
        menuBar.add(menuFichier);
        menuBar.add(menuAide);
        
        //Définir la barre de menu pour la fenêtre
        this.setJMenuBar(menuBar);
        
        //Action pour "Effacer"
        itemEffacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réinitialiser tous les champs du formulaire
                effacer();
            }
        });
        //Action pour "Enregistrer"
        itemEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Enregistrer les données du formulaire
                enregistrer();
            }
        });
        //Action pour "Quitter"
        itemQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //Action pour "À propos"
        itemApropos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "Formulaire MST SIDI 2026\nVersion 1.0\nDéveloppé par Youssef BAHIDA", "À propos", JOptionPane.INFORMATION_MESSAGE);
            } 
        });
        //Action pour "Question"
        itemQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Remplissez le formulaire avec vos informations personnelles.\nUtilisez le menu Fichier pour enregistrer ou effacer les données.", "Aide", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        this.setVisible(true);
    }



    
}