/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FlowShopModel.CustomPriority;
import FlowShopModel.FIFOPriority;
import FlowShopModel.Job;
import FlowShopModel.LIFOPriority;
import FlowShopModel.SPTPriority;
import FlowShopModel.LPTPriority;
import FlowShopModel.LWRPriority;
import FlowShopModel.Machine;
import FlowShopModel.MyFlowShopModel;
import FlowShopModel.QueuePriorityParent;
import View.MyView;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Marcin2
 */
public class MyController {

    private MyView currentView;
    private MyFlowShopModel currentModel;
    private DefaultComboBoxModel queuePriorityListModel;
    private LinkedList<QueuePriorityParent> customPriorityList = new LinkedList<QueuePriorityParent>();

    public MyController() {
        currentModel = new MyFlowShopModel();
        currentView = new MyView(this);
        queuePriorityListModel = new DefaultComboBoxModel();
        queuePriorityListModel.addElement(new FIFOPriority());
        queuePriorityListModel.addElement(new LIFOPriority());
        queuePriorityListModel.addElement(new SPTPriority());
        queuePriorityListModel.addElement(new LPTPriority());
        queuePriorityListModel.addElement(new LWRPriority());

        System.out.println("test2");

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentView.setVisible(true);
            }
        });

        //CUSTOM QUEUE TEST
        HashMap<Integer, Integer> testMap = new HashMap<Integer, Integer>();
        testMap.put(1, 1);
        testMap.put(2, 4);
        testMap.put(3, 2);
        testMap.put(4, 3);
        customPriorityList.add(new CustomPriority(testMap));
        currentView.updateInitialPriorityComboBoxModel(getQueuePriorityListModelForInitialQueue());
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

    public LinkedList<Job> getJobList() {
        return currentModel.getJobList();
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
        } else {
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

    public DefaultListModel getCustomQueuPriorityListModel() {
        DefaultListModel returnModel = new DefaultListModel();
        for (int i = 0; i < customPriorityList.size(); ++i) {
            returnModel.addElement(customPriorityList.get(i));
        }
        return returnModel;
    }

    public void addNewCustomPriority() {
        String newPriorityName;
        boolean currentNamePresent = false;
        newPriorityName = "Priorytet 0";

        for (int i = 0; i < customPriorityList.size(); ++i) {
            newPriorityName = "Priorytet " + i;
            currentNamePresent = false;
            for (int j = 0; j < customPriorityList.size(); ++j) {
                if (customPriorityList.get(j).getName().equals(newPriorityName)) {
                    currentNamePresent = true;
                    break;
                }
            }
            if (currentNamePresent == false) {
                break;
            }
            newPriorityName = "Priorytet " + (1 + i);
        }
        CustomPriority priorityToAdd = new CustomPriority(new HashMap<Integer, Integer>());
        priorityToAdd.setName(newPriorityName);
        customPriorityList.add(priorityToAdd);
        currentView.updateInitialPriorityComboBoxModel(getQueuePriorityListModelForInitialQueue());
    }

    public void removeCustomPriorityAtIndex(int index) {
        CustomPriority priorityToDelete = (CustomPriority) customPriorityList.get(index);
        LinkedList<Machine> allMachines = currentModel.getMachineList();
        for (int i = 0; i < allMachines.size(); ++i) {
            if (allMachines.get(i).getQueuePriority().getName().equals(priorityToDelete.getName())) {
                allMachines.get(i).setQueuePriority(new FIFOPriority());
            }
        }
        if (currentModel.getInitialQueuePriority().getName().equals(priorityToDelete.getName())) {
            currentModel.changeInitialQueuePriority(new FIFOPriority());
        }
        customPriorityList.remove(index);
        currentView.updateInitialPriorityComboBoxModel(getQueuePriorityListModelForInitialQueue());
    }

    public LinkedList<QueuePriorityParent> getCustomQueuePriorityList() {
        return customPriorityList;
    }

    public void changeInitialQueuePriority(QueuePriorityParent priorityToSet) {
        currentModel.changeInitialQueuePriority(priorityToSet);
    }

    public DefaultComboBoxModel getQueuePriorityListModelForInitialQueue() {
        DefaultComboBoxModel returnQueuePriorityListModel = new DefaultComboBoxModel();
        returnQueuePriorityListModel.addElement(new FIFOPriority());
        returnQueuePriorityListModel.addElement(new LIFOPriority());
        returnQueuePriorityListModel.addElement(new LWRPriority());

        for (int i = 0; i < customPriorityList.size(); ++i) {
            returnQueuePriorityListModel.addElement(customPriorityList.get(i));
        }

        return returnQueuePriorityListModel;
    }

    public void addMachineToJobAtIndex(int index, Machine machineToAdd) {
        System.err.println(machineToAdd.getName());
        currentModel.addMachineToJobAtIndex(index, machineToAdd);

    }

    public boolean isDeadlocked() {
        return currentModel.isDeadlocked();
    }

    public int getMode() {
        return currentModel.getMode();
    }

    public void setMode(int newMode) {
        currentModel.setMode(newMode);
        changeNumberOfMachines(0);
        int numberOfIterations=currentModel.getJobList().size();
        for (int i = 0; i < numberOfIterations; ++i) {
            removeJob(0);
        }
    }

    public DefaultComboBoxModel getMissingMachineListModelAtIndex(int index) {
        return currentModel.getMissingMachineListModelAtIndex(index);
    }

    public DefaultComboBoxModel getQueuePriorityListModel() {
        DefaultComboBoxModel returnQueuePriorityListModel = new DefaultComboBoxModel();
        returnQueuePriorityListModel.addElement(new FIFOPriority());
        returnQueuePriorityListModel.addElement(new LIFOPriority());
        returnQueuePriorityListModel.addElement(new SPTPriority());
        returnQueuePriorityListModel.addElement(new LPTPriority());
        returnQueuePriorityListModel.addElement(new LWRPriority());

        for (int i = 0; i < customPriorityList.size(); ++i) {
            returnQueuePriorityListModel.addElement(customPriorityList.get(i));
        }

        return returnQueuePriorityListModel;
    }

    public void moveMachineAtIndexInJobAtIndexUpInRequiredOrder(int machineIndex, int jobIndex) {
        currentModel.moveMachineAtIndexInJobAtIndexUpInRequiredOrder(machineIndex, jobIndex);
    }

    public void moveMachineAtIndexInJobAtIndexDownInRequiredOrder(int machineIndex, int jobIndex) {
        currentModel.moveMachineAtIndexInJobAtIndexDownInRequiredOrder(machineIndex, jobIndex);
    }

    public void changeMachineQueuePriority(QueuePriorityParent priorityToSet, int machineIndex) {
        currentModel.changeMachineQueuePriority(priorityToSet, machineIndex);
    }

    public void removeMachineAtIndexInJobAtIndex(int machineIndex, int jobIndex) {
        currentModel.removeMachineAtIndexInJobAtIndex(machineIndex, jobIndex);
    }

}
