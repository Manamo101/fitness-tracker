package org.example.GUI;

import javax.swing.*;
import java.awt.*;

public class SessionPanel extends JPanel {
    JLabel label1;
    JPanel panel;
    JScrollPane scrollPane;
    SessionPanel(){
        this.setLayout(new BorderLayout());
        label1 = new JLabel("Sessions");
        this.add(label1, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("hello"));

        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(50,50));
        scrollPane.getViewport().add(label1);
        panel.add(scrollPane);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
