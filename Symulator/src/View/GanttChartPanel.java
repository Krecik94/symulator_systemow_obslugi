/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.Job;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javafx.scene.paint.Color;

/**
 *
 * @author Marcin2
 */
public class GanttChartPanel extends javax.swing.JPanel {

    private int currentXSize = 0;
    private int currentYSize = 0;
    private int machineCount = 0;
    private int stepCount = 10;
    private int currentGapHeight = 0;
    private int currentMachineHeight = 0;
    private int currentStepWidth = 0;
    private LinkedList<java.awt.Color> simulationData = new LinkedList<java.awt.Color>();

    @Override
    public void paintComponent(Graphics g) {
        setPreferredSize(new Dimension(stepCount*30, 527));
        super.paintComponent(g); // Do the original draw
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        currentXSize = getSize().width;
        currentYSize = getSize().height;

        if (machineCount == 0 || machineCount == 1) {
            currentGapHeight = 0;
        } else {
            currentGapHeight = (int) (((currentYSize * 0.8) * 0.2) / (machineCount - 1));
        }

        if (machineCount == 0 || machineCount == 1) {
            currentMachineHeight = (int) ((currentYSize * 0.8) * 0.8);
        } else {
            currentMachineHeight = (int) (((currentYSize * 0.8) * 0.8) / (machineCount));
        }

        currentStepWidth = (int) (currentXSize * 0.8) / stepCount;

        g2.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 12));
        setBackground(new java.awt.Color(255, 255, 255));
        g2.drawLine((int) (currentXSize * 0.1), (int) (currentYSize * 0.1), (int) (currentXSize * 0.1), (int) (currentYSize * 0.9));
        g2.drawLine((int) (currentXSize * 0.1), (int) (currentYSize * 0.9), (int) (currentXSize * 0.9), (int) (currentYSize * 0.9));

        if (machineCount > 0) {
            g2.drawLine((int) (currentXSize * 0.1) - 2, (int) (currentYSize * 0.1), (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1));

            g2.setColor(new java.awt.Color(200, 200, 200));
            g2.drawLine((int) (currentXSize * 0.9), (int) (currentYSize * 0.1), (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1));
            g2.setColor(java.awt.Color.black);

            g2.drawLine((int) (currentXSize * 0.1) - 2, (int) (currentYSize * 0.1) + currentMachineHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + currentMachineHeight);

            g2.setColor(new java.awt.Color(200, 200, 200));
            g2.drawLine((int) (currentXSize * 0.9), (int) (currentYSize * 0.1) + currentMachineHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + currentMachineHeight);
            g2.setColor(java.awt.Color.black);

            g2.drawString("1", (int) (currentXSize * 0.1) - 4 - fontMetrics.stringWidth("1"), (int) (currentYSize * 0.1) + currentMachineHeight / 2 + 6);
        }

        for (int i = 0; i < machineCount - 1; ++i) {
            g2.drawLine((int) (currentXSize * 0.1) - 2, (int) (currentYSize * 0.1) + (i + 1) * currentMachineHeight + (i + 1) * currentGapHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + (i + 1) * currentMachineHeight + (i + 1) * currentGapHeight);

            g2.setColor(new java.awt.Color(200, 200, 200));
            g2.drawLine((int) (currentXSize * 0.9), (int) (currentYSize * 0.1) + (i + 1) * currentMachineHeight + (i + 1) * currentGapHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + (i + 1) * currentMachineHeight + (i + 1) * currentGapHeight);
            g2.setColor(java.awt.Color.black);

            g2.drawLine((int) (currentXSize * 0.1) - 2, (int) (currentYSize * 0.1) + (i + 2) * currentMachineHeight + (i + 1) * currentGapHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + (i + 2) * currentMachineHeight + (i + 1) * currentGapHeight);

            g2.setColor(new java.awt.Color(200, 200, 200));
            g2.drawLine((int) (currentXSize * 0.9), (int) (currentYSize * 0.1) + (i + 2) * currentMachineHeight + (i + 1) * currentGapHeight, (int) (currentXSize * 0.1) + 2, (int) (currentYSize * 0.1) + (i + 2) * currentMachineHeight + (i + 1) * currentGapHeight);
            g2.setColor(java.awt.Color.black);

            g2.drawString("" + (i + 2), (int) (currentXSize * 0.1) - 4 - fontMetrics.stringWidth("" + (i + 2)), (int) (currentYSize * 0.1) + (i + 1) * currentMachineHeight + (i + 1) * currentGapHeight + currentMachineHeight / 2 + 6);
        }

        for (int i = 0; i < stepCount; ++i) {
            g2.drawLine((int) (currentXSize * 0.1) + (i + 1) * currentStepWidth, (int) (currentYSize * 0.9) - 2, (int) (currentXSize * 0.1) + (i + 1) * currentStepWidth, (int) (currentYSize * 0.9) + 2);

            g2.setColor(new java.awt.Color(200, 200, 200));
            g2.drawLine((int) (currentXSize * 0.1) + (i + 1) * currentStepWidth, (int) (currentYSize * 0.9) - 2, (int) (currentXSize * 0.1) + (i + 1) * currentStepWidth, (int) (currentYSize * 0.1));
            g2.setColor(java.awt.Color.black);

            g2.drawString("" + (i + 1), (int) (currentXSize * 0.1) + (i) * currentStepWidth + currentStepWidth / 2 - fontMetrics.stringWidth("" + (i + 1)) / 2, (int) (currentYSize * 0.9) + 14);
        }

        if (machineCount != 0) {
            if (simulationData.size() % machineCount != 0) {
                System.out.println("ERROR WYKRESU");
            }
            for (int i = 0; i < (int) simulationData.size() / machineCount; ++i) {
                for (int j = 0; j < machineCount; j++) {
                    g2.setColor(simulationData.get(i * machineCount + j));
                    g2.fillRect((int) (currentXSize * 0.1) + (i) * currentStepWidth + 1, (int) (currentYSize * 0.1) + (j) * currentMachineHeight + (j) * currentGapHeight + 1, currentStepWidth - 1, currentMachineHeight - 1);
                }
            }

        }

    }

    public void setMachineCount(int newMachineCount) {
        machineCount = newMachineCount;
        revalidate();
        repaint();
    }

    public void setStepCount(int newStepCount) {
        if (newStepCount > 10) {
            stepCount = newStepCount;
        } else {
            stepCount = 10;
        }
        revalidate();
        repaint();
    }
    
    public void setSimulationData(LinkedList<java.awt.Color> newSimulationData) {
        simulationData=newSimulationData;
        System.out.println("ASDSADASDAS"+newSimulationData.size());
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(stepCount*30, 527);
    }
    
}
