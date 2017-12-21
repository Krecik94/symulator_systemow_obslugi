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
public class FIFOPriority extends QueuePriorityParent implements java.io.Serializable {

    public FIFOPriority() {
        this.name = "FIFO";
    }

    public int pickHighestPriorityJob(LinkedList<Job> inputList) {
        return 0;
    }

}
