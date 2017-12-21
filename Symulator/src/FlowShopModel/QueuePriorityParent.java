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
public class QueuePriorityParent   implements java.io.Serializable {
    protected String name;
    //Should be overridded in child classes
    public int pickHighestPriorityJob(LinkedList<Job> inputList){
        return -1;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name=newName;
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
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }
    
}
