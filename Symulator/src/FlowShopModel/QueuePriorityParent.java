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
public class QueuePriorityParent {
    protected String name;
    //Should be overridded in child classes
    public int pickHighestPriorityJob(LinkedList<Job> inputList){
        return -1;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
