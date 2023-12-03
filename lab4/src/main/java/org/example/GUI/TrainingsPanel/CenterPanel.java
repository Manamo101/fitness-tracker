package org.example.GUI.TrainingsPanel;

import javax.swing.*;
import java.awt.*;

class CenterPanel extends JPanel{
    private final JList<String> listing;
    {
        JLabel label = new JLabel("Exercises");
        label.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(label);
        labelPanel.setPreferredSize(new Dimension(100,100));

//      JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        listing = new JList<>();
        listing.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(listing, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,300));
        scrollPane.setSize(new Dimension(400,300));
        this.add(labelPanel);
        this.add(scrollPane);
    }
    public JList<String> getListing() {
        return listing;
    }
}
