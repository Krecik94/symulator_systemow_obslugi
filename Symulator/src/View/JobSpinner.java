/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JSpinner;

/**
 *
 * @author Marcin2
 */
public class JobSpinner extends JSpinner {

    private int myIndex = 0;

    public JobSpinner(int newIndex) {
        myIndex = newIndex;
    }
    
}
