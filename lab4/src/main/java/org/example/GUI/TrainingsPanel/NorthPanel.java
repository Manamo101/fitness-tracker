package org.example.GUI.TrainingsPanel;

import org.example.Logic.ProgramOperation;

import javax.swing.*;
import java.awt.*;

class NorthPanel extends JPanel {
    private final JButton newTrainingButton;
    private final JButton deleteTrainingButton;
    private final JButton changeTrainingNameButton;
    private final JComboBox<String> comboBox;
    {
        JLabel label = new JLabel("Trainings: ");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN,25));
        this.add(label);

        String[] array = ProgramOperation.trainingNames();
        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(array));
        this.add(comboBox);

        newTrainingButton = new JButton("add new");
        newTrainingButton.setFocusPainted(false);
        this.add(newTrainingButton);

        deleteTrainingButton = new JButton("delete");
        deleteTrainingButton.setFocusPainted(false);
        this.add(deleteTrainingButton);

        changeTrainingNameButton = new JButton("change name");
        changeTrainingNameButton.setFocusPainted(false);
        this.add(changeTrainingNameButton);
    }
    public void setTrainingPanelActionListener(TrainingPanelActionListener trainingPanelActionListener) {
        newTrainingButton.addActionListener(trainingPanelActionListener);
        deleteTrainingButton.addActionListener(trainingPanelActionListener);
        changeTrainingNameButton.addActionListener(trainingPanelActionListener);
        comboBox.addActionListener(trainingPanelActionListener);
    }
    public JButton getNewTrainingButton() {
        return newTrainingButton;
    }
    public JButton getDeleteTrainingButton() {
        return deleteTrainingButton;
    }
    public JButton getChangeTrainingNameButton() {
        return changeTrainingNameButton;
    }
    public JComboBox<String> getComboBox(){
        return comboBox;
    }
}
