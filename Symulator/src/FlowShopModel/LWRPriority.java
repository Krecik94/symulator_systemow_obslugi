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
public class LWRPriority extends QueuePriorityParent {

    public LWRPriority() {
        this.name = "LWR";
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        int current_lowest = inputList.get(0).getTotalRemainingRequiredTimeUnits();
        int current_lowest_index = 0;
        for (int i = 1; i < inputList.size(); ++i) {
            if (inputList.get(i).getTotalRemainingRequiredTimeUnits() < current_lowest) {
                current_lowest_index = i;
                current_lowest = inputList.get(i).getTotalRemainingRequiredTimeUnits();
            }
        }
        return current_lowest_index;
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
        QueuePriorityParent otherMyClass = (QueuePriorityParent) other;
        if (otherMyClass.getName() == this.getName()) {
            return true;
        } else {
            return false;
        }
    }

}