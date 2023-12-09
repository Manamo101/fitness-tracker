package org.example.GUI.TrainingsPanel;

import org.example.Logic.ProgramOperation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class CenterPanel extends JPanel{
    private final JList<String> listing;
    {
        JLabel label = new JLabel("Exercises");
        label.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(label);

        listing = new JList<>();
        listing.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(listing, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(0,Integer.MAX_VALUE));
        this.add(labelPanel);
        this.add(scrollPane);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    }
    CenterPanel(JComboBox comboBox){
        List<String> list = ProgramOperation.listExercises((String)comboBox.getSelectedItem());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(list);
        listing.removeAll();
        listing.setModel(listModel);

    }
    public void setTrainingPanelActionListener(TrainingPanelActionListener trainingPanelActionListener) {
        listing.addListSelectionListener(trainingPanelActionListener);
    }
    public JList<String> getListing() {
        return listing;
    }
}
