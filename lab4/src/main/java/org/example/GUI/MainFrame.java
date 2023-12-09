package org.example.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.GUI.TrainingsPanel.TrainingPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private final JTabbedPane tabbedPane;
    public MainFrame(String name){
        try {
//            javax.swing.plaf.basic.BasicLookAndFeel
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
        this.add(tabbedPane);
        addExerciseTab();
        addSessionTab();
        addGoalsTab();
        addStatsTab();
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
