package org.example.GUI;

import com.formdev.flatlaf.FlatDefaultsAddon;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.DataPersistence.DatabaseFunctionality;
import org.example.DataPersistence.SQLiteFunctionality;
import org.example.DataPersistence.SQLiteQuery;
import org.example.GUI.TrainingsPanel.TrainingPanel;
import org.example.Logic.ProgramOperation;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private final JTabbedPane tabbedPane;
    public MainFrame(String name){
/*
FlatLaf Light (class com.formdev.flatlaf.FlatLightLaf)
FlatLaf Dark (class com.formdev.flatlaf.FlatDarkLaf)
FlatLaf IntelliJ (based on FlatLaf Light) looks like IntelliJ theme from IntelliJ IDEA 2019.2+ (class com.formdev.flatlaf.FlatIntelliJLaf)
FlatLaf Darcula (based on FlatLaf Dark) looks like Darcula theme from IntelliJ IDEA 2019.2+ (class com.formdev.flatlaf.FlatDarculaLaf)
FlatLaf macOS Light v3 (class com.formdev.flatlaf.themes.FlatMacLightLaf)
FlatLaf macOS Dark v3 (class com.formdev.flatlaf.themes.FlatMacDarkLaf)
*/
        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); //a'la stare macos
//            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf"); // jak intellijLaf tyle że węższe odstępy między komponentami
//            UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacLightLaf"); // spoko
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf"); // najładnieszy
//            UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel"); // potworek
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(SwingConstants.LEFT);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(name);
        this.setMinimumSize(new Dimension(800,600));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        try{
            ImageIcon programIcon = new ImageIcon(MainFrame.class.getClassLoader().getResource("ProgramIcon.png"));
            this.setIconImage(programIcon.getImage());
        }
        catch (NullPointerException e){
            System.out.println("no icon found");
        }
        databaseFileSelector();
        this.add(tabbedPane);
        addExerciseTab();
        addSessionTab();
        addGoalsTab();
        addStatsTab();
    }
    private void databaseFileSelector(){
        JFileChooser fileChooser = new JFileChooser(".");
        String path;
        DatabaseFunctionality database;
        boolean isFirst = true;
        boolean isGood = false;
        do{
            if (!isFirst){
                JOptionPane.showMessageDialog(this, "invalid file", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }
            isFirst = false;
            int respond = fileChooser.showOpenDialog(this);
            if (respond == JFileChooser.APPROVE_OPTION){
                path = fileChooser.getSelectedFile().getAbsolutePath();
                if(SQLiteFunctionality.doesDatabaseExist(path)){
                    database = new SQLiteFunctionality(path);
                    ProgramOperation.setDatabase(database);
                    isGood = true;
                }
            }
            else{
                System.exit(1);
            }
        }
        while(!isGood);
    }
    private void addExerciseTab(){
        try{
            URL exerciseTabIconPath = MainFrame.class.getClassLoader().getResource("exerciseTabIcon.png");
            ImageIcon icon = new ImageIcon(exerciseTabIconPath);
            Image iconScaled = icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
            tabbedPane.addTab("Trainings", new ImageIcon(iconScaled),new TrainingPanel());
        }
        catch (NullPointerException e){
            System.out.println("image is not found");
            tabbedPane.addTab("Trainings", new TrainingPanel());
        }
    }

    private void addSessionTab() {
        try{
            URL exerciseTabIconPath = MainFrame.class.getClassLoader().getResource("sessionTabIcon.png");
            ImageIcon icon = new ImageIcon(exerciseTabIconPath);
            Image iconScaled = icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
            tabbedPane.addTab("Sessions", new ImageIcon(iconScaled),new SessionPanel());
        }
        catch (NullPointerException e){
            System.out.println("image is not found");
            tabbedPane.addTab("Sessions", new SessionPanel());
        }
    }
    private void addGoalsTab() {
        try{
            URL exerciseTabIconPath = MainFrame.class.getClassLoader().getResource("goalsTabIcon.png");
            ImageIcon icon = new ImageIcon(exerciseTabIconPath);
            Image iconScaled = icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
            tabbedPane.addTab("Goals", new ImageIcon(iconScaled),new SessionPanel());
        }
        catch (NullPointerException e){
            System.out.println("image is not found");
            tabbedPane.addTab("Goals", new SessionPanel());
        }
    }
    private void addStatsTab() {
        try{
            URL exerciseTabIconPath = MainFrame.class.getClassLoader().getResource("statsTabIcon.png");
            ImageIcon icon = new ImageIcon(exerciseTabIconPath);
            Image iconScaled = icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
            tabbedPane.addTab("Statistics", new ImageIcon(iconScaled),new SessionPanel());
        }
        catch (NullPointerException e){
            System.out.println("image is not found");
            tabbedPane.addTab("Statistics", new SessionPanel());
        }
    }
}
