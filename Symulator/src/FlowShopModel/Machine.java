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
public class Machine {

    private LinkedList<Job> currentQueueList = new LinkedList<Job>();
    private Job currentJob = null;
    private int queueMaxSize = 0;
    private int ID = 0;
    private String name;
    private QueuePriorityParent queuePriority;

    public Machine(int newID) {
        ID = newID;
        name = "Maszyna " + ID;
        queueMaxSize = 0;
        queuePriority = new LIFOPriority();
    }

    public boolean isFull() {

        if (currentQueueList.size() >= queueMaxSize && currentJob != null) {

            return true;

        } else {
            return false;
        }
    }

    public boolean addJob(Job newJob) {
        if (this.isFull()) {
            return false;
        } else {
            if (currentJob != null) {
                currentQueueList.add(newJob);
                return true;
            } else {
                currentJob = newJob;
                return true;
            }
        }
    }

    public void resetSimulationProgress() {
        currentJob = null;
        currentQueueList.clear();
    }

    public void removeCurrentActiveJob() {
        currentJob = null;
        if (currentQueueList.size() != 0) {
            int highestPriorityJobIndex = queuePriority.pickHighestPriorityJob(currentQueueList);
            currentJob = currentQueueList.get(highestPriorityJobIndex);
            currentQueueList.remove(highestPriorityJobIndex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Job getActiveJob() {
        return currentJob;
    }

    public void addTimeUnits() {
        if (currentJob != null) {
            currentJob.addTimeUnit();
        }
    }

    public int getID() {
        int returnID = ID;
        return returnID;
    }

    public void setQueueMaxSize(int newSize) {
        queueMaxSize = newSize;
    }

    public LinkedList<Job> getCurrentQueue() {
        return currentQueueList;
    }

    public int getQueueMaxSize() {
        return queueMaxSize;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Machine)) {
            return false;
        }
        Machine otherMyClass = (Machine) other;
        if (otherMyClass.getID() == this.getID()) {
            return true;
        } else {
            return false;
        }
    }

}
