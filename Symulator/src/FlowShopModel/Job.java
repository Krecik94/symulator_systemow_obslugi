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

    public void setColor(java.awt.Color newColor) {
        color = newColor;
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
        int requiredSum = 0;
        int acquiredSum = 0;

        for (int i = 0; i < requiredTimeUnits.size(); ++i) {
            requiredSum += requiredTimeUnits.get(i);
        }

        for (int i = 0; i < acquiredTimeUnits.size(); ++i) {
            acquiredSum += acquiredTimeUnits.get(i);
        }
        System.out.println("TEST "+this.name +" "+ (requiredSum - acquiredSum));
        return requiredSum - acquiredSum;
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
