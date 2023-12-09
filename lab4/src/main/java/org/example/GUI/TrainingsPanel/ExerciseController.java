package org.example.GUI.TrainingsPanel;

import org.apache.commons.validator.GenericValidator;
import org.example.Logic.ProgramOperation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class ExerciseController extends JFrame {
    static final int NEW_EXERCISE = 1;
    static final int MODIFY_EXERCISE = 2;
    private final int model;
    private final JComboBox<String> comboBox;
    private final JList<String> list;
    private final String trainingName;
    private String name;
    private JPanel panel1;
    private JTextField exerciseNameField;
    private JRadioButton repetitionsRadioButton;
    private JRadioButton timeRadioButton;
    private JTextField timeRepValueField;
    private JCheckBox loadingCheckBox;
    private JTextField loadingField;
    private JButton addButton;
    private JButton modifyButton;
    private JButton cancelButton;

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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel2.setFocusable(false);
        panel2.setMinimumSize(new Dimension(10, 42));
        panel1.add(panel2, BorderLayout.CENTER);
        panel2.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 12, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Type name");
        panel2.add(label1);
        exerciseNameField = new JTextField();
        exerciseNameField.setHorizontalAlignment(2);
        exerciseNameField.setMinimumSize(new Dimension(50, 30));
        exerciseNameField.setPreferredSize(new Dimension(120, 25));
        exerciseNameField.setText("");
        panel2.add(exerciseNameField);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.setPreferredSize(new Dimension(40, 10));
        panel2.add(panel3);
        repetitionsRadioButton = new JRadioButton();
        repetitionsRadioButton.setFocusable(false);
        repetitionsRadioButton.setText("repetitions");
        panel2.add(repetitionsRadioButton);
        timeRadioButton = new JRadioButton();
        timeRadioButton.setFocusable(false);
        timeRadioButton.setPreferredSize(new Dimension(70, 21));
        timeRadioButton.setText("time (s)");
        panel2.add(timeRadioButton);
        timeRepValueField = new JTextField();
        timeRepValueField.setPreferredSize(new Dimension(50, 25));
        panel2.add(timeRepValueField);
        loadingCheckBox = new JCheckBox();
        loadingCheckBox.setBorderPaintedFlat(false);
        loadingCheckBox.setContentAreaFilled(true);
        loadingCheckBox.setFocusable(false);
        loadingCheckBox.setHorizontalTextPosition(2);
        loadingCheckBox.setRolloverEnabled(false);
        loadingCheckBox.setSelected(false);
        loadingCheckBox.setText("loading");
        panel2.add(loadingCheckBox);
        loadingField = new JTextField();
        loadingField.setPreferredSize(new Dimension(50, 25));
        panel2.add(loadingField);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setPreferredSize(new Dimension(300, 10));
        panel2.add(panel4);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setMinimumSize(new Dimension(10, 10));
        panel5.setPreferredSize(new Dimension(60, 10));
        panel2.add(panel5);
        addButton = new JButton();
        addButton.setEnabled(true);
        addButton.setFocusable(false);
        addButton.setText("add");
        addButton.setVisible(true);
        panel2.add(addButton);
        modifyButton = new JButton();
        modifyButton.setFocusable(false);
        modifyButton.setText("modify");
        panel2.add(modifyButton);
        cancelButton = new JButton();
        cancelButton.setFocusable(false);
        cancelButton.setText("cancel");
        panel2.add(cancelButton);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel6.setPreferredSize(new Dimension(60, 20));
        panel1.add(panel6, BorderLayout.WEST);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel7, BorderLayout.SOUTH);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel8, BorderLayout.NORTH);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel9.setPreferredSize(new Dimension(60, 10));
        panel1.add(panel9, BorderLayout.EAST);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(repetitionsRadioButton);
        buttonGroup.add(timeRadioButton);
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

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    ExerciseController(JList<String> list, JComboBox<String> comboBox, String trainingName, int model) {
        this.model = model;
        this.comboBox = comboBox;
        this.list = list;
        this.trainingName = trainingName;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMaximumSize(new Dimension(400, 300));
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(panel1);

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
            HashMap<String, String> hashMap = ProgramOperation.getListedRecord(list.getSelectedValue());
            exerciseNameField.setText(hashMap.get("exercise"));
            name = hashMap.get("exercise");
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
        }
    }

    private HashMap<String, String> getValues() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("exercise", exerciseNameField.getText());
        hashMap.put("amount", timeRepValueField.getText());
        if (timeRadioButton.isSelected()) {
            hashMap.put("timeRep", "sec");
        } else {
            hashMap.put("timeRep", "X");
        }
        if (loadingCheckBox.isSelected()) {
            hashMap.put("loading", loadingField.getText());
        } else {
            hashMap.put("loading", null);
        }
        return hashMap;
    }

    private void dataValidator() {
        boolean isGood = true;
        if (exerciseNameField.getText().isBlank()) {
            isGood = false;
            exerciseNameField.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            exerciseNameField.setBorder(null);
        }
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
        if (isGood) {
            if (model == NEW_EXERCISE) {
                if (!ProgramOperation.addExercise(getValues(), trainingName)) {
                    JOptionPane.showMessageDialog(this, "exercise already exists", "exercise occupied", JOptionPane.ERROR_MESSAGE);

                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addAll(ProgramOperation.listExercises(trainingName));
                    list.setModel(listModel);
                } else {
                    comboBox.setSelectedIndex(comboBox.getSelectedIndex());
                    this.dispose();
                }
            } else if (model == MODIFY_EXERCISE) {
                ProgramOperation.modifyExercise(getValues(), trainingName, name);
                comboBox.setSelectedIndex(comboBox.getSelectedIndex());
                this.dispose();
            }
        }
    }
}
