/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

import java.util.LinkedList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Marcin2
 */
public class MyFlowShopModel {

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

    public MyFlowShopModel() {
        changeNumberOfMachines(0);
        initialQueuePriority = new FIFOPriority();
    }

    public int getID() {
        return idCounter++;
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
                //System.out.println("weszlo");
                newJob.setName("Zadanie " + (i + 1));
                nameSet = true;
                break;
            }

        }
        if (!nameSet) {
            newJob.setName("Zadanie " + (allJobs.size() + 1));
        }
        allJobs.add(newJob);
        //System.out.println("dodano");
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
        allJobs.get(jobIndex).setColor(newColor);
    }

    public String getJobName(int jobIndex) {
        return allJobs.get(jobIndex).getName();
    }

    public java.awt.Color getJobColor(int jobIndex) {
        return allJobs.get(jobIndex).getColor();
    }

    public void removeJobByIndex(int index) {
        allJobs.remove(index);
        //System.out.println(allJobs.size());
    }

    public void changeMachineQueuePriority(QueuePriorityParent priorityToSet, int machineIndex) {
        allMachines.get(machineIndex).setQueuePriority(priorityToSet);
    }
    
    public void changeInitialQueuePriority(QueuePriorityParent priorityToSet) {
        initialQueuePriority = priorityToSet;
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
            allMachines.get(i).addTimeUnits();
        }

        // getting simulation data in shape of list of colors (for gantt panel)
        for (int i = 0; i < allMachines.size(); ++i) {
            if (allMachines.get(i).getActiveJob() == null) {
                simulationData.add(new java.awt.Color(255, 255, 255));
            } else {
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
                    //System.out.println("nieukonczone - kontynuuje");
                    continue;
                } else if (prioritySortedJobList.get(i).isFinished()) {
                    if (finishedJobs.indexOf(prioritySortedJobList.get(i)) == -1) {
                        finishedJobs.add(prioritySortedJobList.get(i));
                        prioritySortedJobList.get(i).getAssignedMachine().removeCurrentActiveJob();
                        prioritySortedJobList.get(i).removeCurrentlyAssignedMachine();
                        stepFinished = false;
                        eventOccured = true;
                    }
                } else {
                    if (prioritySortedJobList.get(i).getNextRequiredMachine().isFull()) {
                        continue;
                    } else {
                        assignJobToMachine(prioritySortedJobList.get(i), prioritySortedJobList.get(i).getNextRequiredMachine());
                        stepFinished = false;
                        eventOccured = true;
                    }
                }
            }
        }
        if (finishedJobs.size() == prioritySortedJobList.size()) {

            //System.out.println("UKONCZONE");
        }

        //TESTOWE WYPISANIE
        //System.out.println("Krok " + stepCounter);
        for (int i = 0; i < prioritySortedJobList.size(); ++i) {
            //System.out.println(prioritySortedJobList.get(i).getAcquiredTimeUnitsList());
        }
        //System.out.println();
        //System.out.println();
    }

    //Method to execute steps until any task finishes
    public void jumpToNextEvent() {
        if (simulationIsRunning == false) {
            return;
        }
        if (finishedJobs.size() != allJobs.size()) {
            eventOccured = false;
            while (!eventOccured) {
                executeStep();
            }
        }
    }

    public void assignJobToMachine(Job jobToAssign, Machine machineToAssignTo) {
        if (jobToAssign.getAssignedMachine() != null) {
            if (jobToAssign.getAssignedMachine().getActiveJob() == jobToAssign) {
                jobToAssign.getAssignedMachine().removeCurrentActiveJob();
            }
        }
        machineToAssignTo.addJob(jobToAssign);
        jobToAssign.assignMachine(machineToAssignTo);
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
        prioritySortedJobList.clear();
        stepCounter = 0;
        simulationIsRunning = false;
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

    public void initializeTheSimulation() {

        finishedJobs.clear();
        simulationData.clear();
        prioritySortedJobList.clear();
        stepCounter = 0;
        simulationIsRunning = true;
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
                    //allJobs.get(i).getNextRequiredMachine().addJob(allJobs.get(i));
                    assignJobToMachine(prioritySortedJobList.get(i), prioritySortedJobList.get(i).getNextRequiredMachine());
                }
            }
        }
    }

}
