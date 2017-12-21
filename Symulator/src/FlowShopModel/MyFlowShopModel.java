/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Marcin2
 */
public class MyFlowShopModel implements java.io.Serializable {

    private LinkedList<Job> allJobs = new LinkedList<Job>();
    private LinkedList<Machine> allMachines = new LinkedList<Machine>();
    private LinkedList<Job> finishedJobs = new LinkedList<Job>();
    private LinkedList<java.awt.Color> simulationData = new LinkedList<java.awt.Color>();
    private int numberOfMachines = 0;
    private static int idCounter = 0;
    private boolean simulationIsRunning = false;
    private int stepCounter = 0;
    private boolean stepFinished = false;
    private boolean eventOccured = false;
    private QueuePriorityParent initialQueuePriority;
    private LinkedList<Job> prioritySortedJobList = new LinkedList<Job>();
    private boolean deadlockOccured = false;
    //0 for flow shop
    //1 for job shop
    private int currentMode = 0;
    private int totalMachineOccupied = 0;
    private int totalJobWaiting = 0;

    public MyFlowShopModel() {
        changeNumberOfMachines(0);
        initialQueuePriority = new FIFOPriority();
    }

    public int getID() {
        return idCounter++;
    }

    public int getMode() {
        return currentMode;
    }

    public void setMode(int newMode) {
        currentMode = newMode;
    }

    public void changeNumberOfMachines(int newNumber) {
        int oldNumber = allMachines.size();
        if (newNumber == allMachines.size()) {
            return;
        } else if (newNumber < allMachines.size()) {
            while (newNumber != allMachines.size()) {
                allMachines.remove(newNumber);
            }
        } else if (newNumber > allMachines.size()) {
            while (newNumber != allMachines.size()) {
                allMachines.add(new Machine(getID()));
                allMachines.get(allMachines.size() - 1).setName("Maszyna " + allMachines.size());
            }
        }
        for (int i = 0; i < allJobs.size(); ++i) {
            allJobs.get(i).resetData();
            for (int j = 0; j < newNumber; ++j) {
                allJobs.get(i).addRequiredMachine(allMachines.get(j));
                allJobs.get(i).addRequiredTimeUnits(1);
            }
        }

    }

    public void addMachineToJobAtIndex(int index, Machine machineToAdd) {
        allJobs.get(index).addRequiredMachine(machineToAdd);
        allJobs.get(index).addRequiredTimeUnits(1);
    }

    public DefaultComboBoxModel getMissingMachineListModelAtIndex(int index) {
        DefaultComboBoxModel returnModel = new DefaultComboBoxModel();
        LinkedList<Machine> existingMachineList = allJobs.get(index).getRequiredMachinesList();
        boolean machineFound = false;

        for (int i = 0; i < allMachines.size(); ++i) {
            machineFound = false;
            for (int j = 0; j < existingMachineList.size(); ++j) {
                if (existingMachineList.get(j).getID() == allMachines.get(i).getID()) {
                    machineFound = true;
                    break;
                }
            }
            if (!machineFound) {
                returnModel.addElement(allMachines.get(i));
            }
        }

        return returnModel;
    }

    public void addJob() {
        Job newJob = new Job(getID());
        for (int i = 0; i < allMachines.size(); ++i) {
            newJob.addRequiredMachine(allMachines.get(i));
            newJob.addRequiredTimeUnits(1);
        }

        boolean nameTaken = false;
        boolean nameSet = false;

        for (int i = 0; i < allJobs.size(); ++i) {
            nameTaken = false;
            for (int j = 0; j < allJobs.size(); ++j) {
                if (allJobs.get(j).getName().compareTo("Zadanie " + (i + 1)) == 0) {
                    nameTaken = true;
                }
            }
            if (!nameTaken) {
                newJob.setName("Zadanie " + (i + 1));
                nameSet = true;
                break;
            }

        }
        if (!nameSet) {
            newJob.setName("Zadanie " + (allJobs.size() + 1));
        }
        allJobs.add(newJob);
    }

    public boolean removeJob(int jobID) {
        for (int i = 0; i < allJobs.size(); ++i) {
            if (allJobs.get(i).getID() == jobID) {
                allJobs.remove(jobID);
                return true;
            }
        }
        return false;
    }

    public LinkedList<Machine> getRequiredMachinesList(int index) {
        return allJobs.get(index).getRequiredMachinesList();
    }

    public LinkedList<Integer> getRequiredTimeUnitsList(int index) {
        return allJobs.get(index).getRequiredTimeUnitsList();
    }

    public LinkedList<Integer> getAcquiredTimeUnitsList(int index) {
        return allJobs.get(index).getAcquiredTimeUnitsList();
    }

    public int getStepCount() {
        return stepCounter;
    }

    public LinkedList<Job> getJobList() {
        return allJobs;
    }

    public LinkedList<Machine> getMachineList() {
        return allMachines;
    }

    public LinkedList<java.awt.Color> getSimulationData() {
        return simulationData;
    }

    public void changeTimeUnitsRequired(int jobIndex, int machineIndex, int newValue) {
        allJobs.get(jobIndex).setRequiredTImeUnits(machineIndex, newValue);
    }

    public void changeQueueSize(int machineIndex, int newValue) {
        allMachines.get(machineIndex).setQueueMaxSize(newValue);
    }

    public void changeJobName(int jobIndex, String newName) {
        allJobs.get(jobIndex).setName(newName);
    }

    public void changeMachineName(int machineIndex, String newName) {
        allMachines.get(machineIndex).setName(newName);
    }

    public void changeJobColor(int jobIndex, java.awt.Color newColor) {
        if (jobIndex >= 0) {
            allJobs.get(jobIndex).setColor(newColor);
        }
    }

    public String getJobName(int jobIndex) {
        return allJobs.get(jobIndex).getName();
    }

    public java.awt.Color getJobColor(int jobIndex) {
        return allJobs.get(jobIndex).getColor();
    }

    public void removeJobByIndex(int index) {
        allJobs.remove(index);
    }

    public void changeMachineQueuePriority(QueuePriorityParent priorityToSet, int machineIndex) {
        allMachines.get(machineIndex).setQueuePriority(priorityToSet);
    }

    public void changeInitialQueuePriority(QueuePriorityParent priorityToSet) {
        initialQueuePriority = priorityToSet;
    }

    public QueuePriorityParent getInitialQueuePriority() {
        return initialQueuePriority;
    }

    public void moveMachineAtIndexInJobAtIndexUpInRequiredOrder(int machineIndex, int jobIndex) {
        if (jobIndex < 0 || jobIndex >= allJobs.size()) {
            System.err.println("Bad index");
            return;
        }
        allJobs.get(jobIndex).moveMachineAtIndexUpInRequiredOrder(machineIndex);
    }

    public void moveMachineAtIndexInJobAtIndexDownInRequiredOrder(int machineIndex, int jobIndex) {
        if (jobIndex < 0 || jobIndex >= allJobs.size()) {
            System.err.println("Bad index");
            return;
        }
        allJobs.get(jobIndex).moveMachineAtIndexDownInRequiredOrder(machineIndex);
    }

    public void removeMachineAtIndexInJobAtIndex(int machineIndex, int jobIndex) {
        if (jobIndex < 0 || jobIndex >= allJobs.size()) {
            System.err.println("Bad index");
            return;
        }
        allJobs.get(jobIndex).removeMachineAtIndex(machineIndex);
    }

    public DefaultListModel getJobListModel() {
        DefaultListModel returnModel = new DefaultListModel();
        for (int i = 0; i < allJobs.size(); ++i) {
            returnModel.addElement(allJobs.get(i).getName());
        }
        return returnModel;
    }

    public DefaultListModel getMachineListModel() {
        DefaultListModel returnModel = new DefaultListModel();
        for (int i = 0; i < allMachines.size(); ++i) {
            returnModel.addElement(allMachines.get(i).getName());
        }
        return returnModel;
    }

    public void executeStep() {

        // can't execute step with simulation not running
        if (simulationIsRunning == false) {
            return;
        }
        stepCounter += 1;
        stepFinished = false;

        // adding times units to all jobs on machines
        for (int i = 0; i < allMachines.size(); ++i) {
            allMachines.get(i).addTimeUnits(stepCounter);
        }

        // getting simulation data in shape of list of colors (for gantt panel)
        for (int i = 0; i < allMachines.size(); ++i) {
            allMachines.get(i).setStepCounter(stepCounter);
            if (allMachines.get(i).getActiveJob() == null) {
                simulationData.add(new java.awt.Color(255, 255, 255));
            } else {
                totalMachineOccupied += 1;
                allMachines.get(i).setTotalOccupied(allMachines.get(i).getTotalOccupied() + 1);
                if (!allMachines.get(i).getActiveJob().isOnMachineTooLong()) {
                    simulationData.add(allMachines.get(i).getActiveJob().getColor());
                } else {
                    simulationData.add(new java.awt.Color(255, 255, 255));
                }
            }
        }

        while (stepFinished == false) {
            stepFinished = true;
            for (int i = 0; i < prioritySortedJobList.size(); ++i) {
                //case of unfinished task with for example 4/6 time units. Needs to sit, all good. Continue.
                if (!(prioritySortedJobList.get(i).isCompletedOnCurrentMachine())) {
                    continue;
                } else if (prioritySortedJobList.get(i).isFinished()) {
                    if (finishedJobs.indexOf(prioritySortedJobList.get(i)) == -1) {
                        finishedJobs.add(prioritySortedJobList.get(i));
                        if (prioritySortedJobList.get(i).getAssignedMachine() != null) {
                            prioritySortedJobList.get(i).getAssignedMachine().removeCurrentActiveJob(stepCounter);
                        }
                        prioritySortedJobList.get(i).removeCurrentlyAssignedMachine();
                        stepFinished = false;
                        eventOccured = true;
                    }
                } else {
                    if (prioritySortedJobList.get(i).getNextRequiredMachine().isFull()) {
                        if (prioritySortedJobList.get(i).getNextRequiredMachine().checkIfDeadlocked(new LinkedList<Machine>())) {
                            deadlockOccured = true;
                        }
                        continue;
                    } else {
                        assignJobToMachine(prioritySortedJobList.get(i), prioritySortedJobList.get(i).getNextRequiredMachine());
                        stepFinished = false;
                        eventOccured = true;
                    }
                }
            }
        }
        for (int i = 0; i < prioritySortedJobList.size(); ++i) {
            if (prioritySortedJobList.get(i).isCompletedOnCurrentMachine() && !prioritySortedJobList.get(i).isFinished() && prioritySortedJobList.get(i).getNextRequiredMachine().isFull()) {
                prioritySortedJobList.get(i).setTotalWaiting(prioritySortedJobList.get(i).getTotalWaiting() + 1);
                totalJobWaiting += 1;
            } else if (prioritySortedJobList.get(i).isInQueue()) {
                prioritySortedJobList.get(i).setTotalWaiting(prioritySortedJobList.get(i).getTotalWaiting() + 1);
                totalJobWaiting += 1;
            }
        }
        
    }

    //Method to execute steps until any task finishes
    public void jumpToNextEvent() {
        if (simulationIsRunning == false) {
            return;
        }
        if (finishedJobs.size() != allJobs.size()) {
            eventOccured = false;
            while (!eventOccured && !isDeadlocked()) {
                executeStep();
            }
        }
    }

    public void jumpToEndOfSimulation() {
        if (simulationIsRunning == false) {
            return;
        }
        if (finishedJobs.size() != allJobs.size()) {
            eventOccured = false;
            while (!isSimulationFinished() && !isDeadlocked()) {
                executeStep();
            }
        }
    }

    public void assignJobToMachine(Job jobToAssign, Machine machineToAssignTo) {
        if (jobToAssign.getAssignedMachine() != null) {
            if (jobToAssign.getAssignedMachine().getActiveJob() == jobToAssign) {
                jobToAssign.getAssignedMachine().removeCurrentActiveJob(stepCounter);
            }
        }
        machineToAssignTo.addJob(jobToAssign, stepCounter);
        jobToAssign.assignMachine(machineToAssignTo, stepCounter);
    }

    public int getAverageMachineOccupation() {
        if (stepCounter == 0 || allMachines.size() == 0) {
            return 0;
        }
        return (int) ((totalMachineOccupied * 100) / (stepCounter * allMachines.size()));
    }

    public float getAverageJobWaiting() {
        if (stepCounter == 0 || allJobs.size() == 0) {
            return 0;
        }
        return (float) (((float) totalJobWaiting / (float) allJobs.size()));
    }

    public void resetSimulationProgress() {
        for (int i = 0; i < allJobs.size(); ++i) {
            allJobs.get(i).resetSimulationProgress();
        }
        for (int i = 0; i < allMachines.size(); ++i) {
            allMachines.get(i).resetSimulationProgress();
        }
        finishedJobs.clear();
        simulationData.clear();
        for (int i = 0; i < allMachines.size(); ++i) {
            allMachines.get(i).setTotalOccupied(0);
            allMachines.get(i).setStepCounter(0);
        }

        for (int i = 0; i < allJobs.size(); ++i) {
            allJobs.get(i).setTotalWaiting(0);
        }

        totalMachineOccupied = 0;
        totalJobWaiting = 0;
        prioritySortedJobList.clear();
        stepCounter = 0;
        simulationIsRunning = false;
        deadlockOccured = false;
    }

    public boolean isSimulationFinished() {
        boolean returnValue = true;

        for (int i = 0; i < allJobs.size(); ++i) {
            if (!allJobs.get(i).isFinished()) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public boolean isSimulationRunning() {
        return simulationIsRunning;
    }

    public boolean isDeadlocked() {
        return deadlockOccured;
    }

    public void initializeTheSimulation() {

        finishedJobs.clear();
        simulationData.clear();
        prioritySortedJobList.clear();
        for (int i = 0; i < allMachines.size(); ++i) {
            allMachines.get(i).setTotalOccupied(0);
            allMachines.get(i).setStepCounter(0);
        }
        for (int i = 0; i < allJobs.size(); ++i) {
            allJobs.get(i).setTotalWaiting(0);
        }
        totalJobWaiting = 0;
        totalMachineOccupied = 0;
        stepCounter = 0;
        simulationIsRunning = true;
        deadlockOccured = false;
        LinkedList<Job> notSortedJobList = new LinkedList<Job>();
        for (int i = 0; i < allJobs.size(); ++i) {
            notSortedJobList.add(allJobs.get(i));
        }

        // Creating a class list sorted according to set priority.
        // Tasks will be handled according to this list
        int iterations = notSortedJobList.size();
        int nextPriorityIndex = 0;
        for (int i = 0; i < iterations; ++i) {
            nextPriorityIndex = initialQueuePriority.pickHighestPriorityJob(notSortedJobList);
            prioritySortedJobList.add(notSortedJobList.get(nextPriorityIndex));
            notSortedJobList.remove(nextPriorityIndex);
        }

        for (int i = 0; i < prioritySortedJobList.size(); ++i) {
            if (prioritySortedJobList.get(i).hasNextRequiredMachine()) {
                if (!(prioritySortedJobList.get(i).getNextRequiredMachine().isFull())) {
                    assignJobToMachine(prioritySortedJobList.get(i), prioritySortedJobList.get(i).getNextRequiredMachine());
                }
            }
        }
    }

}
