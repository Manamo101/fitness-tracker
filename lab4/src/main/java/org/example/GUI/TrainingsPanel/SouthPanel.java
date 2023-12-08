package org.example.GUI.TrainingsPanel;

import javax.swing.*;
import java.awt.*;

class SouthPanel extends JPanel {
    private final JButton deleteExerciseButton;
    private final JButton newExerciseButton;
    private final JButton changeExerciseButton;
    {
        newExerciseButton = new JButton("add");
        newExerciseButton.setFocusPainted(false);

        deleteExerciseButton = new JButton("delete");
        deleteExerciseButton.setFocusPainted(false);
        deleteExerciseButton.setEnabled(false);

        changeExerciseButton = new JButton("modify");
        changeExerciseButton.setFocusPainted(false);
        changeExerciseButton.setEnabled(false);

        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.add(newExerciseButton);
        this.add(deleteExerciseButton);
        this.add(changeExerciseButton);
    }
    public void setTrainingPanelActionListener(TrainingPanelActionListener trainingPanelActionListener){
        deleteExerciseButton.addActionListener(trainingPanelActionListener);
        newExerciseButton.addActionListener(trainingPanelActionListener);
        changeExerciseButton.addActionListener(trainingPanelActionListener);
    }
    public void setButtonAccessibility(boolean enabled){
        if (enabled){
            changeExerciseButton.setEnabled(true);
            deleteExerciseButton.setEnabled(true);
        }
        else {
            changeExerciseButton.setEnabled(false);
            deleteExerciseButton.setEnabled(false);
        }
    }

    public JButton getDeleteExerciseButton() {
        return deleteExerciseButton;
    }
    public JButton getNewExerciseButton() {
        return newExerciseButton;
    }
    public JButton getChangeExerciseButton() {
        return changeExerciseButton;
    }
}
