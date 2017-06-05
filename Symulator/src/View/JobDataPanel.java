/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import FlowShopModel.Machine;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import javax.swing.LayoutStyle;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.Insets;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

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
    private LinkedList<JButton> moveMachineUpButtonList = new LinkedList<JButton>();
    private LinkedList<JButton> moveMachineDownButtonList = new LinkedList<JButton>();
    private LinkedList<JButton> deleteMachineButtonList = new LinkedList<JButton>();
    private JComboBox missingMachinesComboBox = new JComboBox();

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
        moveMachineUpButtonList.clear();
        moveMachineDownButtonList.clear();
        deleteMachineButtonList.clear();
        currentJobColor = newJobColor;
        nameChangeJLabel = new JLabel("Nazwa:");
        JLabel jobColorJLabel = new JLabel("Kolor:");
        JButton jobColorJButton = new JButton();
        missingMachinesComboBox = new JComboBox();
        JButton addMissingMachineButton = new JButton();
        nameChangeTextField = new JTextField();
        tmpVerticalParallelGroupList.clear();
        //setPreferredSize(new Dimension(190, 308));
        if (myView.getMissingMachineListModelOfCurrentJob().getSize() == 0) {
            setPreferredSize(new Dimension(185, (requiredMachinesList.size() * 43) + 36 + 36));
        } else {
            setPreferredSize(new Dimension(185, (requiredMachinesList.size() * 43) + 36 + 36 + 36));
        }
        nameChangeTextField.setPreferredSize(new Dimension(20, 20));

        nameChangeTextField.setMaximumSize(new Dimension(128, 28));

        jobColorJButton.setPreferredSize(new Dimension(128, 28));
        jobColorJButton.setMinimumSize(new Dimension(50, 28));
        jobColorJButton.setBackground(currentJobColor);
        jobColorJButton.setForeground(currentJobColor);
        jobColorJButton.setOpaque(false);

        missingMachinesComboBox.setModel(myView.getMissingMachineListModelOfCurrentJob());
        missingMachinesComboBox.setPrototypeDisplayValue("XXXXXXXX");
        missingMachinesComboBox.setPreferredSize(new Dimension(128, 28));
        missingMachinesComboBox.setMinimumSize(new Dimension(50, 28));

        if (jobName != null) {
            nameChangeTextField.setText(jobName);
        }

        addMissingMachineButton.setText("Dodaj");
        addMissingMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMissingMachineActionPerformed(evt);
            }
        });

        for (int i = 0; i < requiredMachinesList.size(); ++i) {
            JButton moveMachineUpButtonToAdd = new JButton();

            Dimension tmpMinSize = moveMachineUpButtonToAdd.getMinimumSize();
            Dimension tmpMaxSize = moveMachineUpButtonToAdd.getMaximumSize();
            Dimension tmpPrefSize = moveMachineUpButtonToAdd.getPreferredSize();

            try {
                Image img = ImageIO.read(getClass().getResource("/resources/up.png"));
                moveMachineUpButtonToAdd.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            moveMachineUpButtonToAdd.setMinimumSize(tmpMinSize);
            moveMachineUpButtonToAdd.setMaximumSize(tmpMaxSize);
            moveMachineUpButtonToAdd.setPreferredSize(tmpPrefSize);

            moveMachineUpButtonToAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    moveMachineUpButtonActionPerformed(evt);
                }
            });
            moveMachineUpButtonToAdd.setPreferredSize(new Dimension(30, 15));
            moveMachineUpButtonList.add(moveMachineUpButtonToAdd);

            JButton moveMachineDownButtonToAdd = new JButton();

            tmpMinSize = moveMachineDownButtonToAdd.getMinimumSize();
            tmpMaxSize = moveMachineDownButtonToAdd.getMaximumSize();
            tmpPrefSize = moveMachineDownButtonToAdd.getPreferredSize();

            try {
                Image img = ImageIO.read(getClass().getResource("/resources/down.png"));
                moveMachineDownButtonToAdd.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            moveMachineDownButtonToAdd.setMinimumSize(tmpMinSize);
            moveMachineDownButtonToAdd.setMaximumSize(tmpMaxSize);
            moveMachineDownButtonToAdd.setPreferredSize(tmpPrefSize);

            moveMachineDownButtonToAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    moveMachineDownButtonActionPerformed(evt);
                }
            });
            moveMachineDownButtonToAdd.setPreferredSize(new Dimension(30, 15));
            moveMachineDownButtonList.add(moveMachineDownButtonToAdd);

            JButton deleteMachineButtonToAdd = new JButton();

            tmpMinSize = deleteMachineButtonToAdd.getMinimumSize();
            tmpMaxSize = deleteMachineButtonToAdd.getMaximumSize();
            tmpPrefSize = deleteMachineButtonToAdd.getPreferredSize();

            try {
                Image img = ImageIO.read(getClass().getResource("/resources/delete2.png"));
                deleteMachineButtonToAdd.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }

            deleteMachineButtonToAdd.setMinimumSize(tmpMinSize);
            deleteMachineButtonToAdd.setMaximumSize(tmpMaxSize);
            deleteMachineButtonToAdd.setPreferredSize(tmpPrefSize);

            deleteMachineButtonToAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteMachineButtonActionPerformed(evt);
                }
            });
            deleteMachineButtonToAdd.setPreferredSize(new Dimension(15, 30));
            deleteMachineButtonList.add(deleteMachineButtonToAdd);

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

        // Setting layout
        /*
        myLayout = new GroupLayout(this);
        myLayout.setAutoCreateGaps(true);
        myLayout.setAutoCreateContainerGaps(true);
        this.setLayout(myLayout);

        SequentialGroup tmpVerticalGroup = myLayout.createSequentialGroup();

        if (jobName != null) {
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(nameChangeJLabel)
                    .addComponent(nameChangeTextField));
            tmpVerticalGroup.addGroup(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(jobColorJLabel)
                    .addComponent(jobColorJButton));
        }
        for (int i = 0; i < jLabelList.size(); ++i) {
            tmpVerticalParallelGroupList.add(myLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelList.get(i))
                    .addGroup(myLayout.createSequentialGroup()
                            .addComponent(moveMachineUpButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0)
                            .addComponent(moveMachineDownButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(deleteMachineButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerList.get(i), 30, 30, 30));
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
        tmpParallelGroup.addGap(0);
        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        for (int i = 0; i < moveMachineUpButtonList.size(); ++i) {
            tmpParallelGroup.addComponent(moveMachineUpButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
            tmpParallelGroup.addComponent(moveMachineDownButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
            
        }
        tmpParallelGroup.addGap(0);
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        for (int i = 0; i < deleteMachineButtonList.size(); ++i) {
            tmpParallelGroup.addComponent(deleteMachineButtonList.get(i), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE);
        }
        tmpParallelGroup.addGap(0);
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        tmpHorizontalGroup.addGroup(tmpParallelGroup);
        tmpParallelGroup = myLayout.createParallelGroup();
        if (jobName != null) {
            tmpParallelGroup.addComponent(nameChangeTextField);
            tmpParallelGroup.addComponent(jobColorJButton);
        }
        for (int i = 0; i < jSpinnerList.size(); ++i) {
            tmpParallelGroup.addComponent(jSpinnerList.get(i));
        }
        tmpParallelGroup.addGap(0);
        tmpHorizontalGroup.addGroup(tmpParallelGroup);

        myLayout.setHorizontalGroup(tmpHorizontalGroup);
         */
        // end of setting layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 0.0;
        //c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 0, 0, 0);

        if (jobName != null) {
            c.gridx = 0;
            c.gridwidth = 4;
            c.gridheight = 2;
            c.gridy = 0;
            c.insets = new Insets(5, 2, 5, 0);
            add(nameChangeJLabel, c);
            c.insets = new Insets(0, 0, 0, 0);

            c.gridx = 4;
            c.gridwidth = 5;
            c.gridheight = 2;
            c.gridy = 0;
            c.ipady = 10;
            c.insets = new Insets(5, 0, 0, 2);
            add(nameChangeTextField, c);
            c.insets = new Insets(0, 0, 0, 0);

            c.gridx = 0;
            c.gridwidth = 4;
            c.gridheight = 2;
            c.gridy = 2;
            c.ipady = 0;
            c.insets = new Insets(5, 2, 5, 2);
            add(jobColorJLabel, c);
            c.insets = new Insets(0, 0, 0, 0);

            c.gridx = 4;
            c.gridwidth = 5;
            c.gridheight = 2;
            c.gridy = 2;
            add(jobColorJButton, c);
        }

        for (int i = 0; i < jLabelList.size(); ++i) {
            c.gridx = 0;
            c.gridwidth = 3;
            c.gridheight = 2;
            c.gridy = (2 * i) + 4;
            c.insets = new Insets(7, 2, 0, 0);
            add(jLabelList.get(i), c);
            c.insets = new Insets(0, 0, 0, 0);

            c.gridx = 3;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.gridy = (2 * i) + 4;
            c.insets = new Insets(7, 0, 0, 0);
            c.ipady = 6;
            c.weightx = 0.3;
            add(moveMachineUpButtonList.get(i), c);
            c.insets = new Insets(0, 0, 0, 0);

            c.gridx = 3;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.gridy = (2 * i) + 5;
            add(moveMachineDownButtonList.get(i), c);
            c.ipady = 0;

            c.gridx = 5;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.gridy = (2 * i) + 4;
            c.insets = new Insets(7, 0, 0, 0);
            add(deleteMachineButtonList.get(i), c);
            c.insets = new Insets(0, 0, 0, 0);
            c.weightx = 1.0;

            c.gridx = 6;
            c.gridwidth = 3;
            c.gridheight = 2;
            c.gridy = (2 * i) + 4;
            c.insets = new Insets(7, 0, 0, 2);
            add(jSpinnerList.get(i), c);
            c.insets = new Insets(0, 0, 0, 0);

        }

        if (myView.getMissingMachineListModelOfCurrentJob().getSize() > 0 && jobName != null) {
            //Adding missing machines adding
            c.gridx = 0;
            c.gridwidth = 6;
            c.gridheight = 2;
            c.gridy = (2 * jLabelList.size()) + 4;
            add(missingMachinesComboBox, c);

            c.gridx = 6;
            c.gridwidth = 3;
            c.gridheight = 2;
            c.gridy = (2 * jLabelList.size()) + 4;
            add(addMissingMachineButton, c);

            // Adding empty scalable Label to allow components to start from top of panel.
            c.gridx = 0;
            c.gridwidth = 9;
            c.gridheight = 2;
            c.gridy = (2 * jLabelList.size()) + 6;
            c.weighty = 1.0;
            add(new JLabel(), c);
        } else {
            c.gridx = 0;
            c.gridwidth = 9;
            c.gridheight = 2;
            c.gridy = (2 * jLabelList.size()) + 4;
            c.weighty = 1.0;
            add(new JLabel(), c);
        }
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

    private void addMissingMachineActionPerformed(java.awt.event.ActionEvent evt) {
        System.err.println(missingMachinesComboBox.getSelectedIndex());
        if (missingMachinesComboBox.getSelectedIndex() >= 0) {
            myView.addMachineToJobAtIndex((Machine) missingMachinesComboBox.getSelectedItem());
        }
    }

    private void moveMachineUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (moveMachineUpButtonList.indexOf(source) != -1) {
            myView.moveMachineAtIndexInJobAtIndexUpInRequiredOrder(moveMachineUpButtonList.indexOf(source));
        }
    }

    private void moveMachineDownButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (moveMachineDownButtonList.indexOf(source) != -1) {
            myView.moveMachineAtIndexInJobAtIndexDownInRequiredOrder(moveMachineDownButtonList.indexOf(source));
        }
    }

    private void deleteMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (deleteMachineButtonList.indexOf(source) != -1) {
            myView.removeMachineAtIndexInJobAtIndex(deleteMachineButtonList.indexOf(source));
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
