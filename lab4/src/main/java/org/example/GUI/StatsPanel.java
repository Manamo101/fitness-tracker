package org.example.GUI;

import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xml.CategoryDatasetHandler;
import org.jfree.data.xy.CategoryTableXYDataset;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button1 = new JButton("<-");
        JButton button2 = new JButton("->");
        panel1.add(button1);
        panel1.add(Box.createRigidArea(new Dimension(50,0)));
        panel1.add(button2);
        this.add(panel1);

        CardLayout cardLayout = new CardLayout();
        JPanel panel2 = new JPanel(cardLayout);
//        panel2.add(new JLabel("AAAAA"));
//        panel2.add(new JLabel("BBBBB"));
//        panel2.add(new JLabel("CCCCC"));
//        panel2.add(new JLabel("DDDDD"));
        this.add(panel2);

        button1.addActionListener(e -> cardLayout.previous(panel2));
        button2.addActionListener(e -> cardLayout.next(panel2));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(30,"qwer", "1");
        dataset.addValue(20,"qwer", "2");
        dataset.addValue(0,"qwer", "5");
        dataset.addValue(30,"qwer", "3");

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Chart", "date", "loading", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel2.add(chartPanel);

    }
}
