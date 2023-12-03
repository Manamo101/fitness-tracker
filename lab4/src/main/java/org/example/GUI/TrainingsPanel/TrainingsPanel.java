package org.example.GUI.TrainingsPanel;

import javax.swing.*;
import java.awt.*;

public class TrainingsPanel extends JPanel {

    public TrainingsPanel(){
        NorthPanel northPanel = new NorthPanel();
        CenterPanel centerPanel = new CenterPanel();
        SouthPanel southPanel = new SouthPanel();
        TrainingPanelActionListener al = new TrainingPanelActionListener(northPanel, centerPanel, southPanel);
        northPanel.setTrainingPanelActionListener(al);
        southPanel.setTrainingPanelActionListener(al);
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
