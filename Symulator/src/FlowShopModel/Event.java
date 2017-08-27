/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowShopModel;

/**
 *
 * @author Marcin2
 */
public class Event {

    public int eventTime = 0;
    public String eventText = "";

    public Event(int initialTime, String initialText) {
        eventTime = initialTime;
        eventText = initialText;
    }
}
