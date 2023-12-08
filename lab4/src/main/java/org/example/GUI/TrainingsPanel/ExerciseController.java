package org.example.GUI.TrainingsPanel;

import org.apache.commons.validator.GenericValidator;
import org.example.Logic.ProgramOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ExerciseController extends JFrame implements ActionListener{
    static final int NEW_EXERCISE = 1;
    static final int MODIFY_EXERCISE = 2;
    final int model;
    private JPanel panel1;
    private JTextField exerciseNameField;
    private JRadioButton repetitionsRadioButton;
    private JRadioButton timeRadioButton;
    private JTextField timeRepValueField;
    private JCheckBox loadingCheckBox;
    private JTextField loadingField;
    private JButton addButton;
    private JButton modifyButton;
    private final JList<String> list;

    ExerciseController(JList<String> list, int model){
        this.model = model;
        this.list = list;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMaximumSize(new Dimension(400,300));
        this.setSize(400,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(panel1);

        addButton.addActionListener(this);
        modifyButton.addActionListener(this);
        timeRadioButton.addActionListener(this);
        loadingField.addActionListener(this);
        exerciseNameField.addActionListener(this);
        timeRepValueField.addActionListener(this);
        loadingCheckBox.addActionListener(this);
        repetitionsRadioButton.addActionListener(this);

        if (model == 1){
            this.setTitle("New exercise");
            modifyButton.setVisible(false);
            addButton.setVisible(true);
            loadingField.setEnabled(false);
            repetitionsRadioButton.setSelected(true);
        }
        else if (model == 2){
            this.setTitle("Exercise modification");
            modifyButton.setVisible(true);
            addButton.setVisible(false);
            HashMap<String, String> hashMap = ProgramOperation.getListedRecord(list.getSelectedValue());
            exerciseNameField.setText(hashMap.get("exercise"));
            timeRepValueField.setText(hashMap.get("amount"));
            if(hashMap.get("timeRep").equals("sec")){
                timeRadioButton.setSelected(true);
            }
            else{
                repetitionsRadioButton.setSelected(true);
            }
            if (hashMap.get("loading") == null){
                loadingField.setEnabled(false);
            }
            else {
                loadingField.setText(hashMap.get("loading"));
                loadingCheckBox.setSelected(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadingCheckBox){
            loadingField.setEnabled(loadingCheckBox.isSelected());
        }
        if (e.getSource() == addButton || e.getSource() == modifyButton){
            boolean isGood = true;

            if (exerciseNameField.getText().isBlank()){
                isGood = false;
                exerciseNameField.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            else {
                exerciseNameField.setBorder(null);
            }
            if(!GenericValidator.isInt(timeRepValueField.getText())){
                isGood = false;
                timeRepValueField.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            else{
                timeRepValueField.setBorder(null);
            }
            if(!GenericValidator.isInt(loadingField.getText())) {
                isGood = false;
                if (loadingCheckBox.isSelected()){
                    loadingField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else{
                    loadingField.setBorder(null);
                }
            }
            if (isGood){
//                this.setVisible(false);
//                this.dispose();
            }
        }
    }
}
