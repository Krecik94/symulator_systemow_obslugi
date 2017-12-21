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
public class SPTPriority extends QueuePriorityParent  implements java.io.Serializable {

    public SPTPriority() {
        this.name = "SPT";
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        int current_shortest = inputList.get(0).getCurrentRequiredTimeUnits();
        int current_shortest_index = 0;
        for (int i = 1; i < inputList.size(); ++i) {
            if (inputList.get(i).getCurrentRequiredTimeUnits() < current_shortest) {
                current_shortest_index = i;
                current_shortest = inputList.get(i).getCurrentRequiredTimeUnits();
            }
        }
        return current_shortest_index;
    }

}
