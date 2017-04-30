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
public class LPTPriority extends QueuePriorityParent {

    public LPTPriority() {
        this.name = "LPT";
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        int current_longest = inputList.get(0).getCurrentRequiredTimeUnits();
        int current_longest_index = 0;
        for (int i = 1; i < inputList.size(); ++i) {
            if (inputList.get(i).getCurrentRequiredTimeUnits() > current_longest) {
                current_longest_index = i;
                current_longest = inputList.get(i).getCurrentRequiredTimeUnits();
            }
        }
        return current_longest_index;
    }

}