/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

import java.util.LinkedList;

/**
 *
 * @author Marcin2
 */
public class Job {

    private Machine currentMachine = null;
    private int ID = 0;
    private LinkedList<Machine> requiredMachines = new LinkedList<Machine>();
    private LinkedList<Integer> requiredTimeUnits = new LinkedList<Integer>();
    private LinkedList<Integer> acquiredTimeUnits = new LinkedList<Integer>();
    private java.awt.Color color = java.awt.Color.red;
    private String name;
    private int totalWaiting = 0;

    public Job(int newID) {
        ID = newID;
        name = "Zadanie " + ID;
        color = new java.awt.Color((int) Math.floor(Math.random() * 255), (int) Math.floor(Math.random() * 255), (int) Math.floor(Math.random() * 255));
    }

    public boolean isFinished() {
        for (int i = 0; i < acquiredTimeUnits.size(); ++i) {
            if (acquiredTimeUnits.get(i) < requiredTimeUnits.get(i)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public java.awt.Color getColor() {
        return color;
    }

    public void setTotalWaiting(int newTotalWaiting) {
        totalWaiting = newTotalWaiting;
    }

    public int getTotalWaiting() {
        return totalWaiting;
    }

    public void setColor(java.awt.Color newColor) {
        color = newColor;
    }

    public void moveMachineAtIndexUpInRequiredOrder(int index) {
        if (index <= 0) {
            return;
        } else {
            Machine machineAtIndex = requiredMachines.get(index);
            int acquiredTimeUnitsAtIndex = acquiredTimeUnits.get(index);
            int requiredTimeUnitsAtIndex = requiredTimeUnits.get(index);

            requiredMachines.set(index, requiredMachines.get(index - 1));
            acquiredTimeUnits.set(index, acquiredTimeUnits.get(index - 1));
            requiredTimeUnits.set(index, requiredTimeUnits.get(index - 1));

            requiredMachines.set(index - 1, machineAtIndex);
            acquiredTimeUnits.set(index - 1, acquiredTimeUnitsAtIndex);
            requiredTimeUnits.set(index - 1, requiredTimeUnitsAtIndex);
        }
    }

    public void moveMachineAtIndexDownInRequiredOrder(int index) {
        if (index >= (requiredMachines.size() - 1)) {
            return;
        } else {
            Machine machineAtIndex = requiredMachines.get(index);
            int acquiredTimeUnitsAtIndex = acquiredTimeUnits.get(index);
            int requiredTimeUnitsAtIndex = requiredTimeUnits.get(index);

            requiredMachines.set(index, requiredMachines.get(index + 1));
            acquiredTimeUnits.set(index, acquiredTimeUnits.get(index + 1));
            requiredTimeUnits.set(index, requiredTimeUnits.get(index + 1));

            requiredMachines.set(index + 1, machineAtIndex);
            acquiredTimeUnits.set(index + 1, acquiredTimeUnitsAtIndex);
            requiredTimeUnits.set(index + 1, requiredTimeUnitsAtIndex);
        }
    }

    public void removeMachineAtIndex(int index) {
        if (index < 0 || index >= requiredMachines.size()) {
            System.err.println("Bad index");
            return;
        } else {
            requiredMachines.remove(index);
            requiredTimeUnits.remove(index);
            acquiredTimeUnits.remove(index);
        }
    }

    public void addRequiredMachine(Machine newMachine) {
        requiredMachines.add(newMachine);
    }

    public void resetData() {
        requiredMachines.clear();
        requiredTimeUnits.clear();
        acquiredTimeUnits.clear();
        currentMachine = null;
    }

    public void resetSimulationProgress() {
        currentMachine = null;
        for (int i = 0; i < acquiredTimeUnits.size(); ++i) {
            acquiredTimeUnits.set(i, 0);
        }
    }

    public boolean isOnMachineTooLong() {
        if (currentMachine == null) {
            return true;
        } else {
            int index = requiredMachines.indexOf(currentMachine);
            if (requiredTimeUnits.get(index) < acquiredTimeUnits.get(index)) {
                return true;

            } else {
                return false;
            }
        }
    }

    public float getCompletionPercentage() {
        if (currentMachine == null) {
            return 0;
        }
        int index = requiredMachines.indexOf(currentMachine);
        return (float) acquiredTimeUnits.get(index) / requiredTimeUnits.get(index);
    }

    public int getCurrentAcquiredTimeUnits() {
        if (currentMachine == null) {
            return 0;
        }
        int index = requiredMachines.indexOf(currentMachine);
        return acquiredTimeUnits.get(index);
    }

    public int getTotalRemainingRequiredTimeUnits() {
        int totalSum = 0;
        int currentMachineIndex = 0;
        if (currentMachine != null) {
            currentMachineIndex = requiredMachines.indexOf(currentMachine);
        }
        for (int i = currentMachineIndex; i < requiredMachines.size(); ++i) {
            totalSum += requiredTimeUnits.get(i);
        }
        return totalSum;
    }

    public int getCurrentRequiredTimeUnits() {
        if (currentMachine == null) {
            return 0;
        }
        int index = requiredMachines.indexOf(currentMachine);
        return requiredTimeUnits.get(index);
    }

    public boolean isCompletedOnCurrentMachine() {
        if (currentMachine == null) {
            return true;
        }
        if (currentMachine == null && (!isFinished())) {
            return false;
        } else if (isFinished()) {
            return true;
        } else {
            int index = requiredMachines.indexOf(currentMachine);
            if (requiredTimeUnits.get(index) > acquiredTimeUnits.get(index)) {
                return false;

            } else {
                return true;
            }
        }
    }

    public boolean hasNextRequiredMachine() {
        if (requiredMachines.size() == 0) {
            return false;
        } else if (currentMachine == null) {
            return true;
        } else if (requiredMachines.indexOf(currentMachine) < requiredMachines.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInQueue() {
        if (getAssignedMachine() == null) {
            return false;
        } else if (getAssignedMachine().getActiveJob() == this) {
            return false;
        } else {
            return true;
        }
    }

    public Machine getNextRequiredMachine() {

        if (currentMachine == null) {
            return requiredMachines.get(0);
        } else {
            return requiredMachines.get(requiredMachines.indexOf(currentMachine) + 1);
        }
    }

    public LinkedList<Machine> getRequiredMachinesList() {
        return requiredMachines;
    }

    public LinkedList<Integer> getRequiredTimeUnitsList() {
        return requiredTimeUnits;
    }

    public LinkedList<Integer> getAcquiredTimeUnitsList() {
        return acquiredTimeUnits;
    }

    public void addRequiredTimeUnits(int newTimeUnitsRequired) {
        requiredTimeUnits.add(newTimeUnitsRequired);
        acquiredTimeUnits.add(0);
    }

    public void setRequiredTImeUnits(int index, int newTimeUnitsRequired) {
        requiredTimeUnits.set(index, newTimeUnitsRequired);
    }

    public void assignMachine(Machine newMachine) {
        currentMachine = newMachine;
    }

    public void removeCurrentlyAssignedMachine() {
        currentMachine = null;
    }

    public Machine getAssignedMachine() {
        Machine returnMachine = currentMachine;
        return returnMachine;
    }

    public void addTimeUnit() {
        acquiredTimeUnits.set(requiredMachines.indexOf(currentMachine), acquiredTimeUnits.get(requiredMachines.indexOf(currentMachine)) + 1);
    }

    public int getID() {
        int returnID = ID;
        return returnID;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Job)) {
            return false;
        }
        Job otherMyClass = (Job) other;
        if (otherMyClass.getID() == this.getID()) {
            return true;
        } else {
            return false;
        }
    }

}
