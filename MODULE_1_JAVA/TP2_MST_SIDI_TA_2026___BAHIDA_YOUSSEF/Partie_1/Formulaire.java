package MODULE_1_JAVA.TP2_MST_SIDI_TA_2026___BAHIDA_YOUSSEF.Partie_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.FileWriter;
import java.io.IOException;



public class Formulaire extends JFrame implements ActionListener {
    
    //Attributs

        //les zones de texte
        private JTextField tFcne,tFnom,tFprenom,tFemail;
        //les boutons radio
        private JRadioButton btnRHomme,btnRFemme;
        private ButtonGroup sexeRBtn;
        //Liste combo 
        private JComboBox<String> villes;
        //Checkbox
        private JCheckBox checkAr,checkFr,checkEn,checkEs;
        //les boutons effacer et enregistrer 
        private JButton btnEff,btnEnreg;

    //Constructeur
        public Formulaire()
        {
            //la configuration de base de interface 
            this.setTitle("Formulaire-MST-SIDI-2026");
            this.setSize(400,500);
            this.setLayout(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            

            JPanel panelMain = new JPanel();
            panelMain.setLayout(null);
            panelMain.setBorder(new LineBorder(Color.BLUE,5));
            //eLeMENTS 
                    //CNE 
                    JLabel cneP= new JLabel("CNE",SwingConstants.CENTER);
                    cneP.setOpaque(true);
                    cneP.setBackground(Color.LIGHT_GRAY);
                    cneP.setBounds(20,20,80,20);
                    tFcne = new JTextField(12);
                    tFcne.setBounds(120,20,150,21);
                    //Ajout
                    panelMain.add(cneP);
                    panelMain.add(tFcne);
                //-------------------------------------------------------------------------
                    //Nom
                    JLabel nomP= new JLabel("Nom",SwingConstants.CENTER);
                    nomP.setOpaque(true);
                    nomP.setBackground(Color.LIGHT_GRAY);
                    nomP.setBounds(20,50,80,20);
                    tFnom = new JTextField(12);
                    tFnom.setBounds(120,50,150,21);
                    //Ajout
                    panelMain.add(nomP);
                    panelMain.add(tFnom);
                //-------------------------------------------------------------------------
                    //Prenom
                    JLabel prenomP= new JLabel("Prenom",SwingConstants.CENTER);
                    prenomP.setOpaque(true);
                    prenomP.setBackground(Color.LIGHT_GRAY);
                    prenomP.setBounds(20,80,80,20);
                    tFprenom = new JTextField(12);
                    tFprenom.setBounds(120,80,150,21);
                    //Ajout
                    panelMain.add(prenomP);
                    panelMain.add(tFprenom);
                //-------------------------------------------------------------------------
                    //Homme-Femme
                    JLabel sexeP= new JLabel("Sexe",SwingConstants.CENTER);
                    sexeP.setOpaque(true);
                    sexeP.setBackground(Color.LIGHT_GRAY);
                    sexeP.setBounds(20,110,80,20);
                    btnRHomme = new JRadioButton("Homme",false);
                    btnRFemme = new JRadioButton("Femme",false);
                    btnRHomme.setBounds(120,110,80,20);
                    btnRFemme.setBounds(200,110,80,20);
                    sexeRBtn = new ButtonGroup();
                    sexeRBtn.add(btnRFemme);
                    sexeRBtn.add(btnRHomme);
                    //Ajout
                    panelMain.add(sexeP);
                    panelMain.add(btnRHomme);
                    panelMain.add(btnRFemme);
                //-------------------------------------------------------------------------
                    //Ville
                    String[] ville = {"Rabat","Marrakech","Azrou","Errachidia","Midelt","Tanger"};
                    villes= new JComboBox<>(ville);
                    JLabel villeP= new JLabel("Ville",SwingConstants.CENTER);
                    villeP.setOpaque(true);
                    villeP.setBackground(Color.LIGHT_GRAY);
                    villeP.setBounds(20,140,80,20);
                    villes.setBounds(120,140,80,20);
                    //Ajout
                    panelMain.add(villeP);
                    panelMain.add(villes);

                //-------------------------------------------------------------------------
                    //Email
                    JLabel emailP= new JLabel("Email",SwingConstants.CENTER);
                    emailP.setOpaque(true);
                    emailP.setBackground(Color.LIGHT_GRAY);
                    emailP.setBounds(20,170,80,20);
                    tFemail = new JTextField(12);
                    tFemail.setBounds(120,170,150,21);  
                    //Ajout
                    panelMain.add(emailP);
                    panelMain.add(tFemail);

                //-------------------------------------------------------------------------
                //Langues
                    JLabel langP= new JLabel("Langues",SwingConstants.CENTER);
                    langP.setOpaque(true);
                    langP.setBackground(Color.LIGHT_GRAY);
                    langP.setBounds(20,210,80,20);
                    checkAr = new JCheckBox("Ar");
                    checkFr = new JCheckBox("Fr");
                    checkEn = new JCheckBox("En");
                    checkEs = new JCheckBox("Es");
                    checkAr.setBounds(120,210,50,20);
                    checkFr.setBounds(170,210,50,20);
                    checkEn.setBounds(220,210,50,20);
                    checkEs.setBounds(270,210,50,20);
                    
                    //Ajout
                    panelMain.add(langP);
                    panelMain.add(checkAr);
                    panelMain.add(checkFr);
                    panelMain.add(checkEn);
                    panelMain.add(checkEs);
                //-------------------------------------------------------------------------
                    //Boutons
                    btnEff = new JButton("Effacer");
                    btnEnreg = new JButton("Enregistrer");
                    btnEff.setBackground(Color.LIGHT_GRAY);
                    btnEnreg.setBackground(Color.LIGHT_GRAY);
                    btnEff.addActionListener(this);
                    btnEnreg.addActionListener(this);
                    btnEff.setBounds(140,300,70,30);
                    btnEnreg.setBounds(220,300,90,30);
                    btnEff.setBorder(new LineBorder(Color.BLACK,1));
                    btnEnreg.setBorder(new LineBorder(Color.BLACK,1));
                    //Ajout
                    panelMain.add(btnEff);
                    panelMain.add(btnEnreg);
            //-------------------------------------------------------------------------
    
            //Finalement
            this.add(panelMain); 
            panelMain.setBounds(10,10,360,400);
            this.setVisible(true);
        }
    //getters 
    public JTextField gettFcne() {
        return tFcne;
    }
    public JTextField gettFnom() {
        return tFnom;
    }
    public JTextField gettFprenom() {
        return tFprenom;
    }
    public JTextField gettFemail() {
        return tFemail;
    }
    public JRadioButton getBtnRHomme() {
        return btnRHomme;
    }
    public JRadioButton getBtnRFemme() {
        return btnRFemme;
    }
    public JComboBox<String> getVilles() {
        return villes;
    }
    public JCheckBox getCheckAr() {
        return checkAr;
    }
    public JCheckBox getCheckFr() {
        return checkFr;
    }
    public JCheckBox getCheckEn() {
        return checkEn;
    }
    public JCheckBox getCheckEs() {
        return checkEs;
    }

    //Methodes
    public void effacer()
    {
        //Effacer les champs
        tFcne.setText("");
        tFnom.setText("");
        tFprenom.setText("");
        tFemail.setText("");
        sexeRBtn.clearSelection();
        villes.setSelectedIndex(0);
        checkAr.setSelected(false);
        checkEn.setSelected(false);
        checkEs.setSelected(false);
        checkFr.setSelected(false);
    }
    public void enregistrer()
    {
        //Enregistrer les donnees
        if(tFcne.getText().isEmpty() || tFnom.getText().isEmpty() || tFprenom.getText().isEmpty() || tFemail.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Champs manquants", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Enregistrer les donnees
            String cne = tFcne.getText();
            String nom = tFnom.getText();
            String prenom = tFprenom.getText();
            String email = tFemail.getText();
            String sexe = btnRHomme.isSelected() ? "Homme" : btnRFemme.isSelected() ? "Femme" : "Non specifie";
            String ville = (String) villes.getSelectedItem();
            StringBuilder langues = new StringBuilder();
            if(checkAr.isSelected()) langues.append("Ar ");
            if(checkFr.isSelected()) langues.append("Fr ");
            if(checkEn.isSelected()) langues.append("En ");
            if(checkEs.isSelected()) langues.append("Es ");

            //Formatage des donnees
            String info = "CNE: " + cne + "\nNom: " + nom + "\nPrenom: " + prenom + "\nEmail: " + email +
                          "\nSexe: " + sexe + "\nVille: " + ville + "\nLangues: " + langues.toString();
            //Enregistrer dans un fichier
            try (FileWriter writer = new FileWriter("informations.txt", true)) {
                writer.write(info + "\n-----------------------------\n");
                JOptionPane.showMessageDialog(this, info, "Informations Enregistrees", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement dans le fichier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==btnEff)
        {
            //Effacer les champs
            this.effacer();
        }
        else if(e.getSource()==btnEnreg)
        {
            this.enregistrer();
        }

    }



}
