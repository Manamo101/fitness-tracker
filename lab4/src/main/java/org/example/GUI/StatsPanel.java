package org.example.GUI;

import org.apache.commons.validator.GenericValidator;
import org.example.Logic.ProgramOperation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StatsPanel extends JPanel {
    private final JPanel chartPanel;
    {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button1 = new JButton("<-");
        JButton button2 = new JButton("->");
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonPanel.add(button2);
        this.add(buttonPanel);

        CardLayout cardLayout = new CardLayout();
        chartPanel = new JPanel(cardLayout);
        this.add(chartPanel);

        button1.addActionListener(e -> cardLayout.previous(chartPanel));
        button2.addActionListener(e -> cardLayout.next(chartPanel));

        createCharts();
    }
    void createCharts(){
        chartPanel.removeAll();
        ArrayList<String> rawGoal = ProgramOperation.listGoals();
        for (String raw : rawGoal){
            HashMap<String, String> hashMap = ProgramOperation.getListedGoals(raw);
            ArrayList<ArrayList<String>> stats = ProgramOperation.selectExerciseStats(hashMap.get("exercise"));
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            String rowKey = hashMap.get("timeRep").equals("X") ? "repetitions" : "time";
            for(int i = 1 ; isDayValid(i) ;i++){
                dataset.addValue(0, rowKey, String.valueOf(i));
            }
            for (ArrayList<String> record : stats){
                if (rowKey.equals("repetitions")){
                    dataset.addValue(Integer.parseInt(record.get(1)), rowKey, record.get(6).substring(8,10).replace("0", ""));
                }
                else {
                    dataset.addValue(Integer.parseInt(record.get(2)), rowKey, record.get(6).substring(8,10).replace("0", ""));
                }
                if (record.get(3) != null){
                    dataset.addValue(Integer.parseInt(record.get(3)), "loading", record.get(6).substring(8,10).replace("0", ""));
                }
            }
            JFreeChart chart;
            chart = ChartFactory.createBarChart(hashMap.get("exercise"), "current month", "loading", dataset, PlotOrientation.VERTICAL, true, true, false);
            ValueMarker timeRepMarker = new ValueMarker(Integer.parseInt(hashMap.get("amount")));
            timeRepMarker.setLabel(rowKey + " goal");
            timeRepMarker.setPaint(Color.red);
            timeRepMarker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
            timeRepMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
            chart.getCategoryPlot().addRangeMarker(timeRepMarker);

            if (hashMap.get("loading") != null){
                ValueMarker loadingMarker = new ValueMarker(Integer.parseInt(hashMap.get("loading")));
                loadingMarker.setLabel("loading goal");
                loadingMarker.setPaint(Color.blue);
                loadingMarker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
                loadingMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                chart.getCategoryPlot().addRangeMarker(loadingMarker);
            }
            if (hashMap.get("deadline").substring(0,7).equals(Instant.now().toString().substring(0,7))){
                String day;
                if (hashMap.get("deadline").charAt(8) == '0'){
                    day = hashMap.get("deadline").substring(9,10);
                }
                else{
                    day = hashMap.get("deadline").substring(8,10);
                }
                CategoryMarker loadingMarker = new CategoryMarker(day);
                loadingMarker.setLabel("deadline");
                loadingMarker.setPaint(Color.green);
                loadingMarker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
                loadingMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                chart.getCategoryPlot().addDomainMarker(loadingMarker);
            }

            chartPanel.add(new ChartPanel(chart));
        }
    }
    boolean isDayValid(int i){
        String currentDate = String.valueOf(Instant.now());
        String date = new Scanner(currentDate.replace('T',' ')).next();
        String checkingDate = date.substring(0,8).concat(i >= 10 ? String.valueOf(i) : 0+String.valueOf(i));
        return GenericValidator.isDate(checkingDate, "yyyy-MM-dd", true);
    }
}
