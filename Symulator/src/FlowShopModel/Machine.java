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
public class Machine  implements java.io.Serializable {

    private LinkedList<Job> currentQueueList = new LinkedList<Job>();
    private Job currentJob = null;
    private int queueMaxSize = 0;
    private int ID = 0;
    private String name;
    private QueuePriorityParent queuePriority;
    private int totalOccupied = 0;
    private int stepCounter = 0;
    public LinkedList<Event> eventList = new LinkedList<Event>();

    public Machine(int newID) {
        ID = newID;
        name = "Maszyna " + ID;
        queueMaxSize = 0;
        queuePriority = new FIFOPriority();
    }

    public boolean isFull() {

        if (currentQueueList.size() >= queueMaxSize && currentJob != null) {

            return true;

        } else {
            return false;
        }
    }

    public boolean addJob(Job newJob, int simulationTime) {
        if (this.isFull()) {
            return false;
        } else {
            if (currentJob != null) {
                currentQueueList.add(newJob);
                eventList.add(new Event(simulationTime, "Przyjęcie <b>" + newJob.getName() + "</b> na kolejkę"));
                return true;
            } else {
                currentJob = newJob;
                eventList.add(new Event(simulationTime, "Rozpoczęcie obsługiwania <b>" + newJob.getName()+"</b>"));
                return true;
            }
        }
    }

    public void resetSimulationProgress() {
        currentJob = null;
        currentQueueList.clear();
        eventList.clear();
    }

    public void removeCurrentActiveJob(int simulationTime) {
        eventList.add(new Event(simulationTime, "Zakończenie obsługiwania <b>" + currentJob.getName()+"</b>"));
        currentJob = null;
        for (int i = 0; i < currentQueueList.size(); ++i) {
            System.out.println(currentQueueList.get(i).getName());
        }
        if (currentQueueList.size() != 0) {
            int highestPriorityJobIndex = queuePriority.pickHighestPriorityJob(currentQueueList);
            currentJob = currentQueueList.get(highestPriorityJobIndex);
            currentJob.eventList.add(new Event(simulationTime, "Wejście na <b>" + getName()+"</b>"));
            eventList.add(new Event(simulationTime, "Rozpoczęcie obsługiwania <b>" + currentJob.getName()+"</b>"));
            currentQueueList.remove(highestPriorityJobIndex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setTotalOccupied(int newTotalOcupied) {
        totalOccupied = newTotalOcupied;
    }

    public int getTotalOccupied() {
        return totalOccupied;
    }

    public LinkedList<Event> getEventList() {
        return eventList;
    }

    public void setStepCounter(int newStepCounter) {
        stepCounter = newStepCounter;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    public Job getActiveJob() {
        return currentJob;
    }

    public void addTimeUnits(int simulationTime) {
        if (currentJob != null) {
            currentJob.addTimeUnit(simulationTime);
        }
    }

    public void setQueuePriority(QueuePriorityParent priorityToSet) {
        queuePriority = priorityToSet;
    }

    public QueuePriorityParent getQueuePriority() {
        return queuePriority;
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

    public boolean checkIfDeadlocked(LinkedList<Machine> machinesCheckedSoFar) {
        for (int i = 0; i < machinesCheckedSoFar.size(); ++i) {
            if (machinesCheckedSoFar.get(i).getID() == this.getID()) {
                return true;
            }
        }
        if (!this.isFull()) {
            return false;
        } else if (currentJob == null) {
            return false;
        } else if (!currentJob.isCompletedOnCurrentMachine()) {
            return false;
        } else if (!currentJob.hasNextRequiredMachine()) {
            return false;
        } else if (!currentJob.getNextRequiredMachine().isFull()) {
            return false;
        } else {
            machinesCheckedSoFar.add(this);
            return currentJob.getNextRequiredMachine().checkIfDeadlocked(machinesCheckedSoFar);
        }
    }

    @Override
    public String toString() {
        return name;
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
