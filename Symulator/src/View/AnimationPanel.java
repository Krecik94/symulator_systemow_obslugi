/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.Job;
import FlowShopModel.Machine;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Marcin2
 */
public class AnimationPanel extends javax.swing.JPanel {

    private LinkedList<Job> allJobs = new LinkedList<Job>();
    private LinkedList<Machine> allMachines = new LinkedList<Machine>();

    int notStartedJobsOffset = 0;
    int sizeRelatedOffset = 0;
    int queueOffsetSoFar = 0;
    int totalQueueOffset = 0;
    int currentQueueSize = 0;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Do the original draw
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        totalQueueOffset = 0;
        if (allJobs.size() == 0) {
            notStartedJobsOffset = 60;
        } else {
            notStartedJobsOffset = 20 + (((int) (allJobs.size() - 1) / 3) + 1) * 60;
        }

        for (int i = 0; i < allMachines.size(); ++i) {
            totalQueueOffset += allMachines.get(i).getQueueMaxSize() * 40 + 10;
        }

        sizeRelatedOffset = (int) (getSize().width - (notStartedJobsOffset * 2 + allMachines.size() * 90 + totalQueueOffset)) / 2;
        if (sizeRelatedOffset < 0) {
            sizeRelatedOffset = 0;
        }
        queueOffsetSoFar = 0;

        //Drawing all jobs in initial queue
        for (int i = 0; i < allJobs.size(); ++i) {

            if ((allJobs.get(i).getAssignedMachine() == null && !allJobs.get(i).isFinished()) || allJobs.get(i).getAssignedMachine() == null && allMachines.size() == 0) {
                g2.drawRect(sizeRelatedOffset + 20 + 60 * ((int) i / 3), 10 + 50 * ((int) i % 3), 40, 40);
                g2.setColor(allJobs.get(i).getColor());
                g2.fillRect(sizeRelatedOffset + (20 + 60 * ((int) i / 3)) + 1, (10 + 50 * ((int) i % 3)) + 1, 39, 39);
                g2.setColor(java.awt.Color.BLACK);

            }
        }

        //Drawing mahines
        for (int i = 0; i < allMachines.size(); ++i) {

            if (allMachines.get(i).getQueueMaxSize() >= 0) {
                currentQueueSize = allMachines.get(i).getQueueMaxSize();
                g2.drawRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i, 55, (40 * currentQueueSize) + 10, 50);
                for (int j = 0; j < currentQueueSize; ++j) {
                    g2.drawRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 10 + 40 * j, 65, 30, 30);
                }
                for (int j = 0; j < allMachines.get(i).getCurrentQueue().size(); ++j) {
                    g2.setColor(allMachines.get(i).getCurrentQueue().get(j).getColor());
                    g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 10 + 40 * (currentQueueSize - j - 1) + 1, 66, 29, 29);
                    g2.setColor(java.awt.Color.BLACK);
                }
                queueOffsetSoFar += currentQueueSize * 40 + 10;
            }

            g2.drawRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i, 30, 60, 80);

            if (allMachines.get(i).getActiveJob() != null) {

                if (allMachines.get(i).getActiveJob().getCompletionPercentage() <= 1) {
                    g2.setColor(java.awt.Color.GREEN);
                    g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 1, 31 + 79 - (int) (allMachines.get(i).getActiveJob().getCompletionPercentage() * 79), 59, (int) (allMachines.get(i).getActiveJob().getCompletionPercentage() * 79));
                    g2.setColor(java.awt.Color.BLACK);
                }

                if (allMachines.get(i).getActiveJob().isOnMachineTooLong()) {
                    g2.setColor(java.awt.Color.yellow);
                    g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 1, 31, 59, 79);
                    g2.setColor(java.awt.Color.BLACK);
                }
                if (allMachines.get(i).checkIfDeadlocked(new LinkedList<Machine>())) {
                    g2.setColor(java.awt.Color.red);
                    g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 1, 31, 59, 79);
                    g2.setColor(java.awt.Color.BLACK);
                }

                g2.setColor(allMachines.get(i).getActiveJob().getColor());
                g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 11, 30 + 31, 39, 39);
                g2.setColor(java.awt.Color.BLACK);
            }
            g2.drawRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * i + 10, 30 + 30, 40, 40);
            g2.drawString("M" + (i + 1), queueOffsetSoFar + sizeRelatedOffset + (notStartedJobsOffset + 90 * i) + 30 - fontMetrics.stringWidth("M" + (i + 1)) / 2, 43);

            if (allMachines.get(i).getActiveJob() != null) {
                g2.drawString("" + allMachines.get(i).getActiveJob().getCurrentAcquiredTimeUnits() + "/" + allMachines.get(i).getActiveJob().getCurrentRequiredTimeUnits(), queueOffsetSoFar + sizeRelatedOffset + (notStartedJobsOffset + 90 * i) + 30 - fontMetrics.stringWidth("" + allMachines.get(i).getActiveJob().getCurrentAcquiredTimeUnits() + "/" + allMachines.get(i).getActiveJob().getCurrentRequiredTimeUnits()) / 2, 57);
            }

        }

        //Drawing finished jobs
        if (allMachines.size() != 0) {
            for (int i = 0; i < allJobs.size(); ++i) {

                if (allJobs.get(i).getAssignedMachine() == null && allJobs.get(i).isFinished()) {
                    g2.drawRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * allMachines.size() - 30 + 20 + 60 * ((int) i / 3), 10 + 50 * ((int) i % 3), 40, 40);
                    g2.setColor(allJobs.get(i).getColor());
                    g2.fillRect(queueOffsetSoFar + sizeRelatedOffset + notStartedJobsOffset + 90 * allMachines.size() - 30 + (20 + 60 * ((int) i / 3)) + 1, (10 + 50 * ((int) i % 3)) + 1, 39, 39);
                    g2.setColor(java.awt.Color.BLACK);

                }
            }
        }

    }

    public void setAllJobs(LinkedList<Job> allJobsList) {
        allJobs = allJobsList;
        revalidate();
        repaint();
    }

    public void setAllMachines(LinkedList<Machine> allMachinestList) {
        allMachines = allMachinestList;
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(notStartedJobsOffset * 2 + allMachines.size() * 90 + totalQueueOffset, 150);
    }
}
