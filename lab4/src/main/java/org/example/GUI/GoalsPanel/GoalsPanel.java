package org.example.GUI.GoalsPanel;

import org.example.Logic.ProgramOperation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalsPanel extends JPanel implements ActionListener, ListSelectionListener {
    private final JList<String> list;
    private final JScrollPane scrollPane;
    private final JButton addButton;
    private final JButton modifyButton;
    private final JButton deleteButton;

    public GoalsPanel() {
        // North part
        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Goals");
        titleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel1.add(titleLabel);
        titleLabel.setPreferredSize(new Dimension(100, 50));
        this.add(panel1, BorderLayout.NORTH);

        // Center part
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(ProgramOperation.listGoals());
        list = new JList<>();
        list.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        list.setModel(listModel);
        list.addListSelectionListener(this);
        list.setAlignmentX(RIGHT_ALIGNMENT);

        scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
        this.add(scrollPane, BorderLayout.CENTER);
        // South part
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel2.setPreferredSize(new Dimension(100, 100));
        addButton = new JButton("add");
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> new GoalsController(list, GoalsController.NEW_EXERCISE));

        modifyButton = new JButton("modify");
        modifyButton.setFocusPainted(false);
        modifyButton.setEnabled(false);
        modifyButton.addActionListener(e -> new GoalsController(list, GoalsController.MODIFY_EXERCISE));

        deleteButton = new JButton("delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(this);

        panel2.add(addButton);
        panel2.add(modifyButton);
        panel2.add(deleteButton);
        this.add(panel2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton){
            int respond = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this goal", "Delete goal", JOptionPane.OK_CANCEL_OPTION);
            if (respond == 0){

                ProgramOperation.deleteGoal(ProgramOperation.getListedGoals(list.getSelectedValue()).get("exercise"));
                DefaultListModel<String> listModel = new DefaultListModel<>();
                listModel.addAll(ProgramOperation.listGoals());
                list.removeAll();
                list.setModel(listModel);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list){
            deleteButton.setEnabled(!list.isSelectionEmpty());
            modifyButton.setEnabled(!list.isSelectionEmpty());
        }
    }
}