/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

import java.util.LinkedList;
import java.util.HashMap;

/**
 *
 * @author Marcin2
 */
public class CustomPriority extends QueuePriorityParent {

    private static int counter = 0;

    public CustomPriority(HashMap<Integer, Integer> initPriorityToJobMap) {
        this.name = "Priorytet " + counter++;
        this.priorityToJobMap = initPriorityToJobMap;
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        int returnValue = 0;
        if (inputList.size() != 0 && inputList.size() != 1) {
            int currentHighestPriority = 1;
            if (priorityToJobMap.containsKey(inputList.get(0).getID())) {
                currentHighestPriority = priorityToJobMap.get(inputList.get(0).getID());
            }

            for (int i = 1; i < inputList.size(); ++i) {
                if (priorityToJobMap.containsKey(inputList.get(i).getID())) {
                    if (currentHighestPriority > priorityToJobMap.get(inputList.get(i).getID())) {
                        currentHighestPriority = priorityToJobMap.get(inputList.get(i).getID());
                        returnValue = i;
                    }
                } else {
                    if (currentHighestPriority > 1) {
                        currentHighestPriority = 1;
                        returnValue = i;
                    }
                }
            }
        }
        return returnValue;
    }

    private HashMap<Integer, Integer> priorityToJobMap;
}
