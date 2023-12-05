package org.example.GUI.TrainingsPanel;

import org.example.Logic.ProgramOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrainingPanelActionListener implements ActionListener {
    private final JComboBox<String> comboBox;
    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;
    private final SouthPanel southPanel;

    TrainingPanelActionListener(NorthPanel northPanel, CenterPanel centerPanel, SouthPanel southPanel){
        this.northPanel = northPanel;
        comboBox = northPanel.getComboBox();
        this.centerPanel = centerPanel;
        this.southPanel = southPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == northPanel.getNewTrainingButton()){
            String newTrainingName;
            boolean isFist = true;
            do{
                if (!isFist){
                    JOptionPane.showMessageDialog(null,"Wrong or occupied name!");
                }
                isFist = false;
                newTrainingName = JOptionPane.showInputDialog(null,"Type training name:", "New training", JOptionPane.PLAIN_MESSAGE);
                if (newTrainingName == null){
                    break;
                }
            }
            while (!ProgramOperation.addNewTraining(newTrainingName.trim()));
            if(newTrainingName != null){
                comboBox.addItem(newTrainingName.trim());
                comboBox.setSelectedItem(newTrainingName);
            }
        }

        if(e.getSource() == northPanel.getDeleteTrainingButton()){
            Object name = comboBox.getSelectedItem();

            int i = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete '" + name + "'?", "Delete training", JOptionPane.OK_CANCEL_OPTION);
            if (i == 0){
                ProgramOperation.deleteTraining((String)name);
                comboBox.removeItem(name);
            }
        }

        if (e.getSource() == northPanel.getChangeTrainingNameButton()){
            Object oldName = comboBox.getSelectedItem();
            String newName;
            boolean isFist = true;
            do{
                if (!isFist){
                    JOptionPane.showMessageDialog(null,"Wrong or occupied name!");
                }
                isFist = false;
                newName = JOptionPane.showInputDialog(null,"Type new training name:", "New training name", JOptionPane.PLAIN_MESSAGE);
                if (newName == null){
                    break;
                }
            }
            while (!ProgramOperation.changeTrainingName((String) oldName, newName.trim()));
            if(newName != null){
                int i = comboBox.getSelectedIndex();
                System.out.println(i);
                comboBox.removeItemAt(i);
                comboBox.insertItemAt(newName, i);
                comboBox.setSelectedIndex(i);
            }
        }

        if (e.getSource() == comboBox){
            List<String> list = ProgramOperation.listExercises((String)comboBox.getSelectedItem());
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addAll(list);
            centerPanel.getListing().removeAll();
            centerPanel.getListing().setModel(listModel);
        }

        if (e.getSource() == southPanel.getNewExerciseButton()){
            new ExerciseController(ExerciseController.NEW_EXERCISE, centerPanel.getListing());
//            String exercise;
//            boolean isFist = true;
//            do{
//                if (!isFist){
//                    JOptionPane.showMessageDialog(null,"Existing exercise or invalid input!");
//                }
//                isFist = false;
//                exercise = JOptionPane.showInputDialog(null,"Type training name:", "New training", JOptionPane.PLAIN_MESSAGE);
//                if (exercise == null){
//                    break;
//                }
//            }
//            while (!ProgramOperation.addNewTraining(newTrainingName.trim()));
//            if(newTrainingName != null){
//
//            }
        }
    }
}
