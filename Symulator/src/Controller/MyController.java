/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FlowShopModel.Machine;
import FlowShopModel.MyFlowShopModel;
import View.MyView;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Marcin2
 */
public class MyController {

    private MyView currentView;
    private MyFlowShopModel currentModel;
    private DefaultComboBoxModel queuePriorityListModel;

    public MyController() {
        currentModel = new MyFlowShopModel();
        currentView = new MyView(this);
        queuePriorityListModel = new DefaultComboBoxModel();
        queuePriorityListModel.addElement("FIFO");
        queuePriorityListModel.addElement("LIFO");
        queuePriorityListModel.addElement("SPT");
        queuePriorityListModel.addElement("LPT");
        queuePriorityListModel.addElement("LWR");
        
        System.out.println("test2");

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentView.setVisible(true);
            }
        });
    }

    public void test() {
        System.out.println("Udany");
        System.out.println(currentModel.getID());
    }

    public void addJob() {
        currentModel.addJob();
        updateJobList();
    }

    public void removeJob(int index) {
        currentModel.removeJobByIndex(index);
        updateJobList();
    }

    public void updateAnimationPanel() {
        currentView.updateAnimationPanel(currentModel.getJobList(), currentModel.getMachineList());
    }

    public void updateJobList() {
        currentView.updateListModel(currentModel.getJobListModel());
    }

    public void updateMachineList() {
        currentView.updateMachineListModel(currentModel.getMachineListModel());
    }

    public void changeJobName(int jobIndex, String newName) {
        currentModel.changeJobName(jobIndex, newName);
        updateJobList();
    }

    public void changeMachineName(int machineIndex, String newName) {
        currentModel.changeMachineName(machineIndex, newName);
        updateMachineList();
    }

    public void changeJobColor(int jobIndex, java.awt.Color newColor) {
        currentModel.changeJobColor(jobIndex, newColor);
        updateJobList();
        updateJobDataPanel();
        updateAnimationPanel();
    }

    public void changeNumberOfMachines(int newNumber) {
        if (newNumber >= 0) {
            currentModel.changeNumberOfMachines(newNumber);
        }
    }

    public void initializeTheSimulation() {
        currentModel.initializeTheSimulation();
    }

    public void resetSimulationProgress() {
        currentModel.resetSimulationProgress();
    }

    public void updateGanttChart() {
        currentView.setGanttChartSimulationData(currentModel.getSimulationData());
        currentView.setGanttChartStepCount(currentModel.getStepCount());
    }

    public void executeStep() {
        currentModel.executeStep();
        if (currentModel.isSimulationFinished()) {
            currentView.disableButtonsOnFinish();
        }
    }

    public void jumpToNextEvent() {
        currentModel.jumpToNextEvent();
        if (currentModel.isSimulationFinished()) {
            currentView.disableButtonsOnFinish();
        }
    }

    public void changeTimeUnitsRequired(int jobIndex, int machineIndex, int newValue) {
        currentModel.changeTimeUnitsRequired(jobIndex, machineIndex, newValue);
    }

    public void changeQueueSize(int machineIndex, int newValue) {
        currentModel.changeQueueSize(machineIndex, newValue);
    }

    public void updateMachineDataPanel() {
        if (currentModel.isSimulationRunning() == false) {
            currentView.updateMachinePanelForEditting(currentModel.getMachineList());
        }
        else{
            currentView.updateMachinePanelForDisplay(currentModel.getMachineList());
        }

    }

    public void updateJobDataPanel() {
        int currentIndex = currentView.getCurrentJobListIndex();
        if (currentModel.isSimulationRunning() == false) {
            if (currentIndex == -1) {
                currentView.updateJobDataPanelForEditting(new LinkedList<Machine>(), new LinkedList<Integer>(), null, java.awt.Color.red);
            } else {
                currentView.updateJobDataPanelForEditting(currentModel.getRequiredMachinesList(currentIndex), currentModel.getRequiredTimeUnitsList(currentIndex), currentModel.getJobName(currentIndex), currentModel.getJobColor(currentIndex));
            }
        } else {

            if (currentIndex == -1) {
                currentView.updateJobDataPanelForProgressDisplay(new LinkedList<Machine>(), new LinkedList<Integer>(), new LinkedList<Integer>());
            } else {
                currentView.updateJobDataPanelForProgressDisplay(currentModel.getRequiredMachinesList(currentIndex), currentModel.getRequiredTimeUnitsList(currentIndex), currentModel.getAcquiredTimeUnitsList(currentIndex));

            }
        }
    }
    
    public DefaultComboBoxModel getQueuePriorityListModel(){
        return queuePriorityListModel;
    }
    
    

}
