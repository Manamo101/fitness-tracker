package org.example.GUI.TrainingsPanel;

import javax.swing.*;
import java.awt.*;

class SouthPanel extends JPanel {
    private final JButton deleteExerciseButton;
    private final JButton newExerciseButton;
    private final JButton changeExerciseButton;
    {
        deleteExerciseButton = new JButton("delete");
        deleteExerciseButton.setFocusPainted(false);

        newExerciseButton = new JButton("add");
        newExerciseButton.setFocusPainted(false);

        changeExerciseButton = new JButton("modify");
        changeExerciseButton.setFocusPainted(false);

        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.add(deleteExerciseButton);
        this.add(newExerciseButton);
        this.add(changeExerciseButton);
    }
    public void setTrainingPanelActionListener(TrainingPanelActionListener trainingPanelActionListener){
        deleteExerciseButton.addActionListener(trainingPanelActionListener);
        newExerciseButton.addActionListener(trainingPanelActionListener);
        changeExerciseButton.addActionListener(trainingPanelActionListener);
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
