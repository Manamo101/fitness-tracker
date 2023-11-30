package org.example.GUI;

import org.example.Logic.ProgramOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

class TrainingsPanel extends JPanel implements ActionListener {
    JButton newTrainingButton;
    JComboBox<String> comboBox;

    TrainingsPanel(){
        this.setLayout(new BorderLayout());
        this.add(northPanel(), BorderLayout.NORTH);
        this.add(centerPanel(), BorderLayout.CENTER);
    }
    JPanel northPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Trainings: ");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN,15));
        panel.add(label);

        String[] array = ProgramOperation.trainingNames();
        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(array));
        panel.add(comboBox);

        newTrainingButton = new JButton("add new");
        newTrainingButton.addActionListener(this);
        newTrainingButton.setFocusPainted(false);
        panel.add(newTrainingButton);


        return panel;
    }
    JPanel centerPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        List<String> list = Arrays.asList("Dips", "push-ups", "aaa", "bbb");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(list);
        listModel.add(0,"abc1");
        listModel.add(0,"abc");
        listModel.add(0,"abc");
        listModel.add(0,"abc");
        listModel.add(0,"abc2");
        listModel.add(0,"abc");
        listModel.add(0,"abc");
        listModel.add(0,"abc");
        listModel.add(0,"abc3");
        JList<String> listing = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listing, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,300));
        panel.add(scrollPane);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newTrainingButton){
            String newTrainingName;
            boolean isFist = true;
            do{
                if (!isFist){
                    JOptionPane.showMessageDialog(this,"Wrong or occupied name!");
                }
                isFist = false;
                newTrainingName = JOptionPane.showInputDialog(this,"Type new training name:", "New training", JOptionPane.PLAIN_MESSAGE);
                if (newTrainingName == null){
                    break;
                }
            }
            while (!ProgramOperation.addNewTraining(newTrainingName.trim()));
            if(newTrainingName != null){
                comboBox.addItem(newTrainingName.trim());
            }
        }

    }
}
