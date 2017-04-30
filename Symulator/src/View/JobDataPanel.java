/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.Machine;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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
public class JobDataPanel extends javax.swing.JPanel {

    private JLabel test2;
    private JLabel test;
    private JButton testButton;
    private GroupLayout myLayout;
    private LinkedList<JSpinner> jSpinnerList = new LinkedList<JSpinner>();
    private LinkedList<JLabel> jLabelList = new LinkedList<JLabel>();
    private LinkedList<JLabel> jLabelListProgress = new LinkedList<JLabel>();
    private JLabel labelToAdd;
    private JSpinner spinnerToAdd;
    private LinkedList<ParallelGroup> tmpVerticalParallelGroupList = new LinkedList<ParallelGroup>();
    private ParallelGroup tmpParallelGroup;
    private MyView myView;
    private JLabel nameChangeJLabel = new JLabel("Nazwa:");
    private JTextField nameChangeTextField = new JTextField();
    private JColorChooser jobColorChooser = new JColorChooser();
    private JLabel jobColorJLabel = new JLabel("Kolor:");
    private JButton jobColorJButton = new JButton();
    private java.awt.Color currentJobColor = java.awt.Color.red;

    public JobDataPanel(MyView ownerView) {
        myView = ownerView;
        jobColorChooser.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] tmpPanels = jobColorChooser.getChooserPanels();
        //Setting only HSV panel to be visible
        AbstractColorChooserPanel[] tmpPanels1 = {tmpPanels[1]};
        jobColorChooser.setChooserPanels(tmpPanels1);
    }

    public JobDataPanel() {
        //System.out.println("test");
    }

    public void updatePanelForEditting(LinkedList<Machine> requiredMachinesList, LinkedList<Integer> requiredTimeUnitsList, String jobName, java.awt.Color newJobColor) {
        //System.out.println(nameChangeTextField.getSize());
        removeAll();
        jLabelList.clear();
        jSpinnerList.clear();
        currentJobColor = newJobColor;
        nameChangeJLabel = new JLabel("Nazwa:");
        JLabel jobColorJLabel = new JLabel("Kolor:");
        JButton jobColorJButton = new JButton();
        nameChangeTextField = new JTextField();
        tmpVerticalParallelGroupList.clear();
        //setPreferredSize(new Dimension(190, 308));
        setPreferredSize(new Dimension(185, (requiredMachinesList.size() * 36) + 36 + 36));
        nameChangeTextField.setPreferredSize(new Dimension(20, 20));

        nameChangeTextField.setMaximumSize(new Dimension(128, 28));

        jobColorJButton.setPreferredSize(new Dimension(128, 28));
        jobColorJButton.setMinimumSize(new Dimension(50, 28));
        jobColorJButton.setBackground(currentJobColor);
        jobColorJButton.setForeground(currentJobColor);
        jobColorJButton.setOpaque(false);

        if (jobName != null) {
            nameChangeTextField.setText(jobName);
        }

        nameChangeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobTextFieldActionPerformed(evt);
            }
        });

        jobColorJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobColorButtonActionPerformed(evt);
                //System.out.println("action preformed...");
            }
        });

        if (requiredMachinesList.size() != requiredTimeUnitsList.size()) {
            //System.out.println("ROZMIERY LIST NIEZGODNE");
            return;
        }

        for (int i = 0; i < requiredMachinesList.size(); ++i) {
            labelToAdd = new JLabel(requiredMachinesList.get(i).getName());
            jLabelList.add(labelToAdd);
        }

        for (int i = 0; i < requiredTimeUnitsList.size(); ++i) {
            //TODO ADD SPINNERS

            spinnerToAdd = new JSpinner();
            spinnerToAdd.setValue(requiredTimeUnitsList.get(i));
            spinnerToAdd.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    //jSpinner5StateChanged(evt);
                    jobDataSpinnerChanged(evt);
                }
            });
            jSpinnerList.add(spinnerToAdd);
        }

        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        if (jobName != null) {
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(nameChangeJLabel).addComponent(nameChangeTextField));
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(jobColorJLabel).addComponent(jobColorJButton));
        }
        for (int i = 0; i < jLabelList.size(); ++i) {
            tmpVerticalParallelGroupList.add(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(jLabelList.get(i)).addComponent(jSpinnerList.get(i), 30, 30, 30));
        }
        for (int i = 0; i < tmpVerticalParallelGroupList.size(); ++i) {
            tmpVerticalGroup.addGroup(tmpVerticalParallelGroupList.get(i));
        }

        myLayout.setVerticalGroup(tmpVerticalGroup);

        SequentialGroup tmpHorizontalGroup = myLayout.createSequentialGroup();
        tmpParallelGroup = myLayout.createParallelGroup();
        if (jobName != null) {
            tmpParallelGroup.addComponent(nameChangeJLabel);
            tmpParallelGroup.addComponent(jobColorJLabel);
        }
        for (int i = 0; i < jLabelList.size(); ++i) {
            tmpParallelGroup.addComponent(jLabelList.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        if (jobName != null) {
            tmpParallelGroup.addComponent(nameChangeTextField);
            tmpParallelGroup.addComponent(jobColorJButton);
        }
        for (int i = 0; i < jSpinnerList.size(); ++i) {
            tmpParallelGroup.addComponent(jSpinnerList.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);
        revalidate();
        repaint();

    }

    public void updatePanelForProgressDisplay(LinkedList<Machine> requiredMachinesList, LinkedList<Integer> requiredTimeUnitsList, LinkedList<Integer> acquiredTimeUnitsList) {
        removeAll();
        jLabelList.clear();
        jLabelListProgress.clear();
        setPreferredSize(new Dimension(185, requiredMachinesList.size() * 22));

        if (requiredMachinesList.size() != requiredTimeUnitsList.size() || requiredTimeUnitsList.size() != acquiredTimeUnitsList.size()) {
           //System.out.println("ROZMIERY LIST NIEZGODNE");
            return;
        }

        for (int i = 0; i < requiredMachinesList.size(); ++i) {
            labelToAdd = new JLabel(requiredMachinesList.get(i).getName());
            jLabelList.add(labelToAdd);
        }

        for (int i = 0; i < requiredTimeUnitsList.size(); ++i) {
            labelToAdd = new JLabel(acquiredTimeUnitsList.get(i) + " / " + requiredTimeUnitsList.get(i));
            jLabelListProgress.add(labelToAdd);
        }

        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        for (int i = 0; i < jLabelList.size(); ++i) {
            tmpVerticalParallelGroupList.add(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(jLabelList.get(i)).addComponent(jLabelListProgress.get(i)));
        }
        for (int i = 0; i < tmpVerticalParallelGroupList.size(); ++i) {
            tmpVerticalGroup.addGroup(tmpVerticalParallelGroupList.get(i));
        }

        myLayout.setVerticalGroup(tmpVerticalGroup);

        SequentialGroup tmpHorizontalGroup = myLayout.createSequentialGroup();
        tmpParallelGroup = myLayout.createParallelGroup();

        for (int i = 0; i < jLabelList.size(); ++i) {
            tmpParallelGroup.addComponent(jLabelList.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpHorizontalGroup.addGap(50, 50, 50);
        tmpParallelGroup = myLayout.createParallelGroup();

        for (int i = 0; i < jLabelListProgress.size(); ++i) {
            tmpParallelGroup.addComponent(jLabelListProgress.get(i));
        }
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);

        revalidate();
        repaint();
    }

    private void jobTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField source = (JTextField) evt.getSource();
        String value = source.getText();
        if (value.compareTo("") != 0) {
            myView.jobNameChanged(value);
        }
    }

    private void jobDataSpinnerChanged(javax.swing.event.ChangeEvent evt) {
        JSpinner source = (JSpinner) evt.getSource();
        if ((Integer) source.getValue() < 1) {
            source.setValue(1);
        } else {
            if (jSpinnerList.indexOf(source) != -1) {
                myView.jobDataSpinnerChanged(jSpinnerList.indexOf(source), (Integer) source.getValue());
            }
        }
    }

    private void jobColorChooserActionPerformed() {
        java.awt.Color tmpColor = jobColorChooser.getColor();
        myView.jobColorChanged(tmpColor);
    }

    private void jobColorButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //java.awt.Color tmpColor = JColorChooser.showDialog(null, "Wybierz kolor", currentJobColor);
        jobColorChooser.setColor(currentJobColor);
        JDialog tmp = JColorChooser.createDialog(null, "Wybierz kolor", true, jobColorChooser, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobColorChooserActionPerformed();
            }
        }, null);
        tmp.setVisible(true);
        tmp.dispose();
        /*
        if (tmpColor != null) {
            myView.jobColorChanged(tmpColor);
        }*/

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
