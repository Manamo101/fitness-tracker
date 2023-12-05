package org.example.GUI.TrainingsPanel;

import javax.swing.*;

public class ExerciseController extends JFrame {
    static final int NEW_EXERCISE = 1;
    static final int MODIFY_EXERCISE = 2;
    private JPanel panel1;
    private JTextField exerciseTextField;
    private JRadioButton repetitionsRadioButton;
    private JRadioButton timeRadioButton;
    private JTextField textField1;
    private JCheckBox loadingCheckBox;
    private JTextField textField2;

    ExerciseController(int model, JList<String> list){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(panel1);
        if (model == 1){
            this.setTitle("New exercise");
        }
        else if (model == 2){
            this.setTitle("Exercise modification");
        }
    }
}
