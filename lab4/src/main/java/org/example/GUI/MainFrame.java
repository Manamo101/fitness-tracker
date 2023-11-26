package org.example.GUI;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    JTabbedPane tabbedPane;

    public MainFrame(String name){
        super(name);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setTabPlacement(SwingConstants.LEFT);

        this.setVisible(true);
        try{
            ImageIcon programIcon = new ImageIcon(MainFrame.class.getClassLoader().getResource("ProgramIcon.png"));
            this.setIconImage(programIcon.getImage());
        }
        catch (NullPointerException e){
            System.out.println("no icon found");
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(800,600));
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
            tabbedPane.addTab("Exercises", new ImageIcon(iconScaled),new ExercisePanel());
        }
        catch (NullPointerException e){
            System.out.println("image is not found");
            tabbedPane.addTab("Exercises", new ExercisePanel());
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
    }private void addGoalsTab() {
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
