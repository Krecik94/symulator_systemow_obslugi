/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.Machine;
import FlowShopModel.QueuePriorityParent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author Marcin2
 */
public class MachineDataPanel extends javax.swing.JPanel {

    private JLabel test2;
    private JLabel test;
    private GroupLayout myLayout;
    private JLabel labelToAdd;
    private JSpinner spinnerToAdd;
    private LinkedList<ParallelGroup> tmpVerticalParallelGroupList = new LinkedList<ParallelGroup>();
    private ParallelGroup tmpParallelGroup;
    private MyView myView;
    private JLabel nameChangeJLabel = new JLabel("Nazwa:");
    private JTextField nameChangeTextField = new JTextField();
    private JLabel queueSizeChangeLabel = new JLabel("Rozmiar kolejki:");
    private JSpinner queueSizeSpinner = new JSpinner();
    private JLabel machineNameLabel = new JLabel("Maszyna");
    private JLabel queueSizeLabel = new JLabel("1");
    private JComboBox queuePriorityComboBox = new JComboBox();
    private JLabel queuePriorityLabel = new JLabel("Priorytet kolejki:");

    private java.awt.Color currentJobColor = java.awt.Color.red;

    public MachineDataPanel(MyView ownerView) {
        myView = ownerView;
        setPreferredSize(new Dimension(190, 200));
    }

    public MachineDataPanel() {
        //System.out.println("test");
    }

    public void updateMachinePanelForEditting(LinkedList<Machine> allMachines, int machineListIndex) {
        removeAll();
        nameChangeJLabel = new JLabel("Nazwa:");
        nameChangeTextField = new JTextField();
        queuePriorityComboBox = new JComboBox();
        tmpVerticalParallelGroupList.clear();
        //setPreferredSize(new Dimension(190, 308));
        setPreferredSize(new Dimension(190, 200));
        nameChangeTextField.setPreferredSize(new Dimension(20, 20));

        nameChangeTextField.setMaximumSize(new Dimension(128, 28));

        if (machineListIndex < 0) {
            revalidate();
            repaint();
            return;
        }
        //System.out.println("PANEL MASZYNY");

        if (allMachines.get(machineListIndex).getName() != null) {
            nameChangeTextField.setText(allMachines.get(machineListIndex).getName());
        }

        nameChangeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MachineTextFieldActionPerformed(evt);
            }
        });

        queueSizeSpinner.setMaximumSize(new Dimension(128, 28));
        queueSizeSpinner.setValue(allMachines.get(machineListIndex).getQueueMaxSize());
        queuePriorityComboBox.setMaximumSize(new Dimension(128, 28));

        DefaultComboBoxModel currentQueuPriorityModel=myView.getQueuePriorityListModel();
        
        queuePriorityComboBox.setModel(currentQueuPriorityModel);

        QueuePriorityParent selectedPriority = allMachines.get(machineListIndex).getQueuePriority();
        int foundIndex=0;
        for (int i=0;i< currentQueuPriorityModel.getSize(); ++i)
        {
            if(((QueuePriorityParent)currentQueuPriorityModel.getElementAt(i)).getName()==selectedPriority.getName()){
                foundIndex=i;
                break;
            }
        }
        
        queuePriorityComboBox.setSelectedIndex(foundIndex);
        
        /*if (selectedPriority.getName() == "FIFO") {
            queuePriorityComboBox.setSelectedIndex(0);
        } else if (selectedPriority.getName() == "LIFO") {
            queuePriorityComboBox.setSelectedIndex(1);
        } else if (selectedPriority.getName() == "SPT") {
            queuePriorityComboBox.setSelectedIndex(2);
        } else if (selectedPriority.getName() == "LPT") {
            queuePriorityComboBox.setSelectedIndex(3);
        } else if (selectedPriority.getName() == "LWR") {
            queuePriorityComboBox.setSelectedIndex(4);
        }*/
        queuePriorityComboBox.setSelectedItem(allMachines.get(machineListIndex).getQueuePriority());

        queuePriorityComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    queuePriorityComboBoxActionPerformed(evt);
                    System.err.println("action Performed");
                    System.err.println(queuePriorityComboBox.getActionListeners());
                }
            }
        });

        queueSizeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                //jSpinner5StateChanged(evt);
                machineDataSpinnerChanged(evt);
            }
        });

        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        if (allMachines.get(machineListIndex).getName() != null) {
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(nameChangeJLabel).addComponent(nameChangeTextField));
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(queueSizeChangeLabel).addComponent(queueSizeSpinner));
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(queuePriorityLabel).addComponent(queuePriorityComboBox));
        }

        myLayout.setVerticalGroup(tmpVerticalGroup);

        SequentialGroup tmpHorizontalGroup = myLayout.createSequentialGroup();
        tmpParallelGroup = myLayout.createParallelGroup();
        if (allMachines.get(machineListIndex).getName() != null) {
            tmpParallelGroup.addComponent(nameChangeJLabel);
            tmpParallelGroup.addComponent(queueSizeChangeLabel);
            tmpParallelGroup.addComponent(queuePriorityLabel);
        }

        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        if (allMachines.get(machineListIndex).getName() != null) {
            tmpParallelGroup.addComponent(nameChangeTextField);
            tmpParallelGroup.addComponent(queueSizeSpinner);
            tmpParallelGroup.addComponent(queuePriorityComboBox);
        }

        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);
        revalidate();
        repaint();
        //queuePriorityComboBox=null;

    }

    public void updateMachinePanelForDisplay(LinkedList<Machine> allMachines, int machineListIndex) {
        removeAll();
        nameChangeJLabel = new JLabel("Nazwa:");
        tmpVerticalParallelGroupList.clear();
        //setPreferredSize(new Dimension(190, 308));
        setPreferredSize(new Dimension(190, 200));
        nameChangeTextField.setPreferredSize(new Dimension(20, 20));

        nameChangeTextField.setMaximumSize(new Dimension(128, 28));

        if (machineListIndex < 0) {
            revalidate();
            repaint();
            return;
        }

        //System.out.println("PANEL MASZYNY");
        if (allMachines.get(machineListIndex).getName() != null) {
            machineNameLabel.setText(allMachines.get(machineListIndex).getName());
        }

        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        queueSizeLabel.setText("" + allMachines.get(machineListIndex).getQueueMaxSize());
        SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        if (allMachines.get(machineListIndex).getName() != null) {
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(nameChangeJLabel).addComponent(machineNameLabel));
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(queueSizeChangeLabel).addComponent(queueSizeLabel));
        }

        myLayout.setVerticalGroup(tmpVerticalGroup);

        SequentialGroup tmpHorizontalGroup = myLayout.createSequentialGroup();
        tmpParallelGroup = myLayout.createParallelGroup();
        if (allMachines.get(machineListIndex).getName() != null) {
            tmpParallelGroup.addComponent(nameChangeJLabel);
            tmpParallelGroup.addComponent(queueSizeChangeLabel);
        }

        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        if (allMachines.get(machineListIndex).getName() != null) {
            tmpParallelGroup.addComponent(machineNameLabel);
            tmpParallelGroup.addComponent(queueSizeLabel);
        }

        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);
        revalidate();
        repaint();

    }

    private void MachineTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField source = (JTextField) evt.getSource();
        String value = source.getText();
        if (value.compareTo("") != 0) {
            myView.machineNameChanged(value);
        }
    }

    private void queuePriorityComboBoxActionPerformed(java.awt.event.ItemEvent evt) {
        JComboBox source = (JComboBox) evt.getSource();
        QueuePriorityParent selectedPriority = (QueuePriorityParent) source.getSelectedItem();
        myView.machineQueuePriorityChanged(selectedPriority);
    }

    private void machineDataSpinnerChanged(javax.swing.event.ChangeEvent evt) {
        JSpinner source = (JSpinner) evt.getSource();
        if ((Integer) source.getValue() < 0) {
            source.setValue(0);
        } else {
            myView.machineDataSpinnerChanged((Integer) source.getValue());
        }
    }

    public void test() {
        test = new JLabel("test");
        test.setSize(test.getPreferredSize());

        test2 = new JLabel("asd");
        test2.setSize(test2.getPreferredSize());

        JButton testButton = new JButton();
        testButton.setSize(50, 50);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(test)
                                .addComponent(test2)
                                .addComponent(testButton)
                                .addGap(0, 190, Short.MAX_VALUE)
                        ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(test)
                        .addComponent(test2)
                        .addGap(20, 20, 20)
                        .addComponent(testButton)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 266, Short.MAX_VALUE))
        );

        revalidate();
        repaint();
    }

}
