/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.CustomPriority;
import FlowShopModel.Job;
import FlowShopModel.Machine;
import java.util.LinkedList;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author Marcin2
 */
public class QueueManagerNewPriorityPanel extends javax.swing.JPanel {

    private LinkedList<JLabel> jobNameLabels = new LinkedList<JLabel>();
    private LinkedList<JSpinner> jobPrioritySpinners = new LinkedList<JSpinner>();
    private GroupLayout myLayout;
    private GroupLayout.ParallelGroup tmpParallelGroup;

    public QueueManagerNewPriorityPanel() {

    }

    public void updateMachinePanelForEditting(LinkedList<Job> allJobs, CustomPriority selectedPriority) {
        removeAll();
        jobNameLabels.clear();
        jobPrioritySpinners.clear();
        for (int i = 0; i < allJobs.size(); ++i) {
            jobNameLabels.add(new JLabel(allJobs.get(i).getName()));
        }
        for (int i = 0; i < allJobs.size(); ++i) {
            JSpinner spinnerToAdd = new JSpinner();
            spinnerToAdd.setValue(selectedPriority.getPriorityForID(allJobs.get(i).getID()));
            jobPrioritySpinners.add(spinnerToAdd);
        }

        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        GroupLayout.SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        for (int i = 0; i < jobNameLabels.size(); ++i) {
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(jobNameLabels.get(i)).addComponent(jobPrioritySpinners.get(i), 30, 30, 30));
        }

        myLayout.setVerticalGroup(tmpVerticalGroup);

        GroupLayout.SequentialGroup tmpHorizontalGroup = myLayout.createSequentialGroup();
        tmpParallelGroup = myLayout.createParallelGroup();

        for (int i = 0; i < jobNameLabels.size(); ++i) {
            tmpParallelGroup.addComponent(jobNameLabels.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();

        for (int i = 0; i < jobPrioritySpinners.size(); ++i) {
            tmpParallelGroup.addComponent(jobPrioritySpinners.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);
        revalidate();
        repaint();

    }

    public LinkedList<Integer> getSpinnerValues() {
        LinkedList<Integer> returnList = new LinkedList<Integer>();
        for (int i = 0; i < jobPrioritySpinners.size(); ++i) {
            returnList.add((int) jobPrioritySpinners.get(i).getValue());
        }
        return returnList;
    }

}
