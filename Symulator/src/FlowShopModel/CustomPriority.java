/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Marcin2
 */
public class CustomPriority extends QueuePriorityParent {

    public CustomPriority(Map<Integer, Integer> initPriorityToJobMap) {
        this.name = "Priorytet własny 1";
        this.priorityToJobMap = initPriorityToJobMap;
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        return 0;
    }

    private Map<Integer, Integer> priorityToJobMap;
}
