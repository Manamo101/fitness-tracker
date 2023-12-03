package org.example.GUI.TrainingsPanel;

import javax.swing.*;
import java.awt.*;

public class TrainingsPanel extends JPanel {

    public TrainingsPanel(){
        NorthPanel northPanel = new NorthPanel();
        CenterPanel centerPanel = new CenterPanel();
        TrainingPanelActionListener al = new TrainingPanelActionListener(northPanel, centerPanel);
        northPanel.setTrainingPanelActionListener(al);
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(new JLabel("button"), BorderLayout.SOUTH);
    }
}
