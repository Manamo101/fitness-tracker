package org.example.GUI.GoalsPanel;

import org.apache.commons.validator.GenericValidator;
import org.example.Logic.ProgramOperation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class GoalsController extends JFrame {
    static final int NEW_EXERCISE = 1;
    static final int MODIFY_EXERCISE = 2;
    private final int model;
    private final JList<String> list;
    private JPanel panel1;
    private JRadioButton repetitionsRadioButton;
    private JRadioButton timeRadioButton;
    private JTextField timeRepValueField;
    private JCheckBox loadingCheckBox;
    private JTextField loadingField;
    private JButton addButton;
    private JButton modifyButton;
    private JButton cancelButton;
    private JComboBox<String> comboBox;
    private JTextField deadlineField;

    GoalsController(JList<String> list, int model) {
        this.list = list;
        this.model = model;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMaximumSize(new Dimension(400, 300));
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(panel1);
        comboBox.setModel(new DefaultComboBoxModel<>(ProgramOperation.listAllExercises()));
        timeRadioButton.setSelected(true);

        cancelButton.addActionListener(e -> dispose());
        addButton.addActionListener(e -> dataValidator());
        modifyButton.addActionListener(e -> dataValidator());
        loadingCheckBox.addActionListener(e -> loadingField.setEnabled(loadingCheckBox.isSelected()));

        if (model == 1) {
            this.setTitle("New exercise");
            modifyButton.setVisible(false);
            addButton.setVisible(true);
            loadingField.setEnabled(false);
            repetitionsRadioButton.setSelected(true);

        } else if (model == 2) {
            this.setTitle("Exercise modification");
            modifyButton.setVisible(true);
            addButton.setVisible(false);
            comboBox.setEnabled(false);

            HashMap<String, String> hashMap = ProgramOperation.getListedGoals(list.getSelectedValue());
            comboBox.setSelectedItem(hashMap.get("exercise"));
            timeRepValueField.setText(hashMap.get("amount"));
            if (hashMap.get("timeRep").equals("sec")) {
                timeRadioButton.setSelected(true);
            } else {
                repetitionsRadioButton.setSelected(true);
            }
            if (hashMap.get("loading") == null) {
                loadingField.setEnabled(false);
            } else {
                loadingField.setText(hashMap.get("loading"));
                loadingCheckBox.setSelected(true);
            }
            deadlineField.setText(hashMap.get("deadline"));
        }
    }

    private void dataValidator() {
        boolean isGood = true;
        if (!GenericValidator.isInt(timeRepValueField.getText())) {
            isGood = false;
            timeRepValueField.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            timeRepValueField.setBorder(null);
        }
        if (!GenericValidator.isInt(loadingField.getText())) {
            if (loadingCheckBox.isSelected()) {
                isGood = false;
                loadingField.setBorder(BorderFactory.createLineBorder(Color.RED));
            } else {
                loadingField.setBorder(null);
            }
        }
        if (!GenericValidator.isDate(deadlineField.getText(), "yyyy-MM-dd", true)) {
            deadlineField.setBorder(BorderFactory.createLineBorder(Color.RED));
            isGood = false;
        } else {
            deadlineField.setBorder(null);
        }
        if (isGood) {
            if (model == NEW_EXERCISE) {
                if (!ProgramOperation.addGoal(getValues())) {
                    JOptionPane.showMessageDialog(this, "A goal with this exercise already exists", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addAll(ProgramOperation.listGoals());
                    list.removeAll();
                    list.setModel(listModel);
                    this.dispose();
                }
            } else if (model == MODIFY_EXERCISE) {
                ProgramOperation.modifyGoal(getValues(), (String) comboBox.getSelectedItem());
                DefaultListModel<String> listModel = new DefaultListModel<>();
                listModel.addAll(ProgramOperation.listGoals());
                list.removeAll();
                list.setModel(listModel);
                this.dispose();
            }
        }
    }

    private String[] getValues() {
        String repetition = repetitionsRadioButton.isSelected() ? timeRepValueField.getText() : null;
        String time = timeRadioButton.isSelected() ? timeRepValueField.getText() : null;
        String loading = loadingCheckBox.isSelected() ? loadingField.getText() : null;
        return new String[]{(String) comboBox.getSelectedItem(), repetition, time, loading, deadlineField.getText()};
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setDoubleBuffered(true);
        panel1.setEnabled(true);
        panel1.setInheritsPopupMenu(false);
        panel1.setMaximumSize(new Dimension(1000, 1000));
        panel1.setMinimumSize(new Dimension(400, 300));
        panel1.setPreferredSize(new Dimension(400, 300));
        panel1.setRequestFocusEnabled(false);
        panel1.setVisible(true);
        panel2.add(panel1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel3.setFocusable(false);
        panel3.setMinimumSize(new Dimension(10, 42));
        panel1.add(panel3, BorderLayout.CENTER);
        panel3.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 12, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("select exercise");
        panel3.add(label1);
        comboBox = new JComboBox();
        panel3.add(comboBox);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setPreferredSize(new Dimension(40, 10));
        panel3.add(panel4);
        repetitionsRadioButton = new JRadioButton();
        repetitionsRadioButton.setFocusable(false);
        repetitionsRadioButton.setText("repetitions");
        panel3.add(repetitionsRadioButton);
        timeRadioButton = new JRadioButton();
        timeRadioButton.setFocusable(false);
        timeRadioButton.setPreferredSize(new Dimension(70, 21));
        timeRadioButton.setText("time (s)");
        panel3.add(timeRadioButton);
        timeRepValueField = new JTextField();
        timeRepValueField.setPreferredSize(new Dimension(50, 25));
        panel3.add(timeRepValueField);
        loadingCheckBox = new JCheckBox();
        loadingCheckBox.setBorderPaintedFlat(false);
        loadingCheckBox.setContentAreaFilled(true);
        loadingCheckBox.setFocusable(false);
        loadingCheckBox.setHorizontalTextPosition(2);
        loadingCheckBox.setRolloverEnabled(false);
        loadingCheckBox.setSelected(false);
        loadingCheckBox.setText("loading (kg) ");
        panel3.add(loadingCheckBox);
        loadingField = new JTextField();
        loadingField.setPreferredSize(new Dimension(50, 25));
        panel3.add(loadingField);
        final JSeparator separator1 = new JSeparator();
        separator1.setPreferredSize(new Dimension(60, 0));
        panel3.add(separator1);
        final JLabel label2 = new JLabel();
        label2.setText("deadline");
        panel3.add(label2);
        deadlineField = new JTextField();
        panel3.add(deadlineField);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setPreferredSize(new Dimension(250, 10));
        panel3.add(panel5);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel6.setMinimumSize(new Dimension(10, 10));
        panel6.setPreferredSize(new Dimension(50, 10));
        panel3.add(panel6);
        addButton = new JButton();
        addButton.setEnabled(true);
        addButton.setFocusable(false);
        addButton.setText("add");
        addButton.setVisible(true);
        panel3.add(addButton);
        modifyButton = new JButton();
        modifyButton.setFocusable(false);
        modifyButton.setText("modify");
        panel3.add(modifyButton);
        cancelButton = new JButton();
        cancelButton.setFocusable(false);
        cancelButton.setText("cancel");
        panel3.add(cancelButton);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel7.setPreferredSize(new Dimension(60, 20));
        panel1.add(panel7, BorderLayout.WEST);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel8, BorderLayout.SOUTH);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel9, BorderLayout.NORTH);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel10.setPreferredSize(new Dimension(60, 10));
        panel1.add(panel10, BorderLayout.EAST);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(timeRadioButton);
        buttonGroup.add(repetitionsRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }


}
