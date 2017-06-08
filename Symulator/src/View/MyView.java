package View;

import Controller.MyController;
import FlowShopModel.Job;
import FlowShopModel.Machine;
import FlowShopModel.QueuePriorityParent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcin2
 */
public class MyView extends javax.swing.JFrame {

    /**
     * Creates new form OknoGlowne
     */
    public MyView(MyController paramController) {
        myController = paramController;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setTitle("Symulator systemów obsługi");
        stepButton.setEnabled(false);
        resetButton.setEnabled(false);
        jumpButton.setEnabled(false);
        jComboBox1.setModel(myController.getQueuePriorityListModel());
        jComboBox1.setPrototypeDisplayValue("XXXXXXXX");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        machineCountSpinner = new javax.swing.JSpinner();
        machineCountLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JobPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        addJobButton = new javax.swing.JButton();
        removeJobButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        animationPanel = new View.AnimationPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jobDataPanel1 = new View.JobDataPanel(this);
        jScrollPane6 = new javax.swing.JScrollPane();
        machineDataPanel1 = new View.MachineDataPanel(this);
        jPanel1 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        jumpButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        ganttChartPanel1 = new View.GanttChartPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista maszyn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));

        jList2.setModel(MachineListModel);
        jList2.setToolTipText("Lista maszyn");
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(jList2);

        jLabel1.setText("Priorytet początkowy:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setToolTipText("Zmień priorytet początkowy zadań wchodzących do systemu");
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        machineCountSpinner.setToolTipText("Zmień liczbę maszyn");
        machineCountSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                machineCountSpinnerStateChanged(evt);
            }
        });

        machineCountLabel.setText("Liczba Maszyn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(machineCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(machineCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, 63, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(machineCountLabel)
                    .addComponent(machineCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JobPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista zadań", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        JobPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        JobPanel.setPreferredSize(new java.awt.Dimension(50, 50));

        jList1.setModel(listModel);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("Lista zadań");
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        addJobButton.setText("Dodaj Zadanie");
        addJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJobButtonActionPerformed(evt);
            }
        });

        removeJobButton.setText("Usuń ");
        removeJobButton.setToolTipText("Usuń wybrane zadanie");
        removeJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJobButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JobPanelLayout = new javax.swing.GroupLayout(JobPanel);
        JobPanel.setLayout(JobPanelLayout);
        JobPanelLayout.setHorizontalGroup(
            JobPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JobPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JobPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(JobPanelLayout.createSequentialGroup()
                        .addComponent(addJobButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(removeJobButton)))
                .addContainerGap())
        );
        JobPanelLayout.setVerticalGroup(
            JobPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JobPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(JobPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addJobButton)
                    .addComponent(removeJobButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(JobPanel);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        animationPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout animationPanelLayout = new javax.swing.GroupLayout(animationPanel);
        animationPanel.setLayout(animationPanelLayout);
        animationPanelLayout.setHorizontalGroup(
            animationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );
        animationPanelLayout.setVerticalGroup(
            animationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(animationPanel);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jobDataPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel zadania", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jobDataPanel1.setPreferredSize(new java.awt.Dimension(190, 308));

        javax.swing.GroupLayout jobDataPanel1Layout = new javax.swing.GroupLayout(jobDataPanel1);
        jobDataPanel1.setLayout(jobDataPanel1Layout);
        jobDataPanel1Layout.setHorizontalGroup(
            jobDataPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        jobDataPanel1Layout.setVerticalGroup(
            jobDataPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jobDataPanel1);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        machineDataPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel maszyny", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout machineDataPanel1Layout = new javax.swing.GroupLayout(machineDataPanel1);
        machineDataPanel1.setLayout(machineDataPanel1Layout);
        machineDataPanel1Layout.setHorizontalGroup(
            machineDataPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        machineDataPanel1Layout.setVerticalGroup(
            machineDataPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        jScrollPane6.setViewportView(machineDataPanel1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Krok");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        jumpButton.setText("Skocz");
        jumpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(stepButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jumpButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(resetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepButton)
                    .addComponent(jumpButton))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ganttChartPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ganttChartPanel1Layout = new javax.swing.GroupLayout(ganttChartPanel1);
        ganttChartPanel1.setLayout(ganttChartPanel1Layout);
        ganttChartPanel1Layout.setHorizontalGroup(
            ganttChartPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        ganttChartPanel1Layout.setVerticalGroup(
            ganttChartPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(ganttChartPanel1);

        jTabbedPane1.addTab("Wykres", jScrollPane1);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(453, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addContainerGap(432, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Statystyki", jPanel3);

        jMenu3.setText("Tryb");
        jMenuBar2.add(jMenu3);

        jMenu1.setText("Opcje");

        jMenuItem1.setText("Manager priorytetów kolejek");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJobButtonActionPerformed
        if (jList1.getSelectedIndex() != -1) {
            myController.removeJob(jList1.getSelectedIndex());
            myController.updateAnimationPanel();
        }
    }//GEN-LAST:event_removeJobButtonActionPerformed

    private void machineCountSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_machineCountSpinnerStateChanged
        // TODO add your handling code here:
        int value = (Integer) machineCountSpinner.getValue();
        ganttChartPanel1.setMachineCount(value);
        if (value < 0) {
            machineCountSpinner.setValue(0);
        } else {
            myController.changeNumberOfMachines(value);
            myController.updateJobDataPanel();
            myController.updateAnimationPanel();
            myController.updateMachineList();
            myController.updateMachineDataPanel();
        }
    }//GEN-LAST:event_machineCountSpinnerStateChanged

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        myController.updateJobDataPanel();
    }//GEN-LAST:event_jList1ValueChanged

    private void addJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJobButtonActionPerformed
        // TODO add your handling code here:
        myController.addJob();
        myController.updateAnimationPanel();
    }//GEN-LAST:event_addJobButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        if ((int) machineCountSpinner.getValue() > 0) {
            myController.initializeTheSimulation();
            myController.updateJobDataPanel();
            myController.updateMachineDataPanel();
            myController.updateAnimationPanel();
            startButton.setEnabled(false);
            stepButton.setEnabled(true);
            jumpButton.setEnabled(true);
            resetButton.setEnabled(true);
            addJobButton.setEnabled(false);
            removeJobButton.setEnabled(false);
            machineCountLabel.setEnabled(false);
            machineCountSpinner.setEnabled(false);
            jComboBox1.setEnabled(false);
        }

    }//GEN-LAST:event_startButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        // TODO add your handling code here:
        myController.executeStep();
        myController.updateJobDataPanel();
        myController.updateMachineDataPanel();
        myController.updateGanttChart();
        myController.updateAnimationPanel();
        System.err.println(myController.isDeadlocked());
        if (myController.isDeadlocked()) {
            performDeadlockCleanup();
        }


    }//GEN-LAST:event_stepButtonActionPerformed

    public void performDeadlockCleanup() {
        stepButton.setEnabled(false);
        jumpButton.setEnabled(false);
        JOptionPane.showMessageDialog(this,
                "Wystąpiło zakleszczenie.",
                "Uwaga",
                JOptionPane.WARNING_MESSAGE);
    }
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        myController.resetSimulationProgress();
        myController.updateJobDataPanel();
        myController.updateMachineDataPanel();
        myController.updateGanttChart();
        myController.updateAnimationPanel();
        startButton.setEnabled(true);
        stepButton.setEnabled(false);
        jumpButton.setEnabled(false);
        resetButton.setEnabled(false);
        addJobButton.setEnabled(true);
        removeJobButton.setEnabled(true);
        machineCountLabel.setEnabled(true);
        machineCountSpinner.setEnabled(true);
        jComboBox1.setEnabled(true);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void jumpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpButtonActionPerformed
        // TODO add your handling code here:
        myController.jumpToNextEvent();
        myController.updateJobDataPanel();
        myController.updateMachineDataPanel();
        myController.updateGanttChart();
        myController.updateAnimationPanel();
    }//GEN-LAST:event_jumpButtonActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        myController.updateMachineDataPanel();

    }//GEN-LAST:event_jList2ValueChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        queuePriorityJFrame = new QueuePriorityManager(this, myController);
        queuePriorityJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        queuePriorityJFrame.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        JComboBox source = (JComboBox) evt.getSource();
        QueuePriorityParent selectedPriority = (QueuePriorityParent) source.getSelectedItem();
        myController.changeInitialQueuePriority(selectedPriority);

    }//GEN-LAST:event_jComboBox1ItemStateChanged
    public void updateListModel(DefaultListModel newModel) {
        int oldIndex = jList1.getSelectedIndex();
        listModel = newModel;
        jList1.setModel(listModel);
        jList1.setSelectedIndex(oldIndex);

    }

    public DefaultComboBoxModel getQueuePriorityListModel() {
        return myController.getQueuePriorityListModel();
    }

    public void updateMachineListModel(DefaultListModel newModel) {
        int oldIndex = jList2.getSelectedIndex();
        MachineListModel = newModel;
        jList2.setModel(MachineListModel);
        jList2.setSelectedIndex(oldIndex);

    }

    public void jobDataSpinnerChanged(int index, int newValue) {
        System.out.println("Zmieniono: " + index + "  " + newValue);
        myController.changeTimeUnitsRequired(jList1.getSelectedIndex(), index, newValue);

    }

    public void machineDataSpinnerChanged(int newValue) {
        System.out.println("Zmieniono: " + "  " + newValue);
        myController.changeQueueSize(jList2.getSelectedIndex(), newValue);
        myController.updateAnimationPanel();

    }

    public void machineQueuePriorityChanged(QueuePriorityParent selectedPriority) {
        myController.changeMachineQueuePriority(selectedPriority, jList2.getSelectedIndex());
    }

    public void jobNameChanged(String newName) {
        myController.changeJobName(jList1.getSelectedIndex(), newName);
    }

    public void machineNameChanged(String newName) {
        myController.changeMachineName(jList2.getSelectedIndex(), newName);
        myController.updateJobDataPanel();
    }

    public void jobColorChanged(java.awt.Color newColor) {
        myController.changeJobColor(jList1.getSelectedIndex(), newColor);
    }

    public void updateAnimationPanel(LinkedList<Job> allJobsList, LinkedList<Machine> allMachinestList) {
        animationPanel.setAllJobs(allJobsList);
        animationPanel.setAllMachines(allMachinestList);
    }

    public void updateJobDataPanelForEditting(LinkedList<Machine> requiredMachinesList, LinkedList<Integer> requiredTimeUnitsList, String jobName, java.awt.Color jobColor) {
        jobDataPanel1.updatePanelForEditting(requiredMachinesList, requiredTimeUnitsList, jobName, jobColor);
    }

    public void updateJobDataPanelForProgressDisplay(LinkedList<Machine> requiredMachinesList, LinkedList<Integer> requiredTimeUnitsList, LinkedList<Integer> acquiredTimeUnitsList) {
        jobDataPanel1.updatePanelForProgressDisplay(requiredMachinesList, requiredTimeUnitsList, acquiredTimeUnitsList);
    }

    public void updateMachinePanelForEditting(LinkedList<Machine> allMachines) {
        machineDataPanel1.updateMachinePanelForEditting(allMachines, jList2.getSelectedIndex());
    }

    public void updateMachinePanelForDisplay(LinkedList<Machine> allMachines) {
        machineDataPanel1.updateMachinePanelForDisplay(allMachines, jList2.getSelectedIndex());
    }

    public void jobSpinnerChanged(int index, int value) {
//TODO dodac obsluge zmiany stanu spinnerow od zadan
    }

    public int getCurrentJobListIndex() {
        return jList1.getSelectedIndex();
    }

    public void disableButtonsOnFinish() {
        stepButton.setEnabled(false);
        jumpButton.setEnabled(false);
    }

    public void setGanttChartStepCount(int newStepCount) {
        ganttChartPanel1.setStepCount(newStepCount);
    }

    public void setGanttChartSimulationData(LinkedList<java.awt.Color> newSimulationData) {
        ganttChartPanel1.setSimulationData(newSimulationData);
    }

    public void updateInitialPriorityComboBoxModel(DefaultComboBoxModel newModel) {
        String currentName = ((QueuePriorityParent) jComboBox1.getSelectedItem()).getName();
        jComboBox1.setModel(newModel);
        for (int i = 0; i < newModel.getSize(); ++i) {
            if (((QueuePriorityParent) newModel.getElementAt(i)).getName().equals(currentName)) {
                jComboBox1.setSelectedIndex(i);
                break;
            }
        }
    }

    public DefaultComboBoxModel getMissingMachineListModelOfCurrentJob() {
        if (jList1.getSelectedIndex() != -1) {
            return myController.getMissingMachineListModelAtIndex(jList1.getSelectedIndex());
        }
        return new DefaultComboBoxModel();
    }

    public void addMachineToJobAtIndex(Machine machineToAdd) {
        if (jList1.getSelectedIndex() != -1) {
            myController.addMachineToJobAtIndex(jList1.getSelectedIndex(), machineToAdd);
        }
        myController.updateJobDataPanel();
    }

    public void moveMachineAtIndexInJobAtIndexUpInRequiredOrder(int machineIndex) {
        myController.moveMachineAtIndexInJobAtIndexUpInRequiredOrder(machineIndex, jList1.getSelectedIndex());
        myController.updateJobDataPanel();
    }

    public void moveMachineAtIndexInJobAtIndexDownInRequiredOrder(int machineIndex) {
        myController.moveMachineAtIndexInJobAtIndexDownInRequiredOrder(machineIndex, jList1.getSelectedIndex());
        myController.updateJobDataPanel();
    }

    public void removeMachineAtIndexInJobAtIndex(int machineIndex) {
        myController.removeMachineAtIndexInJobAtIndex(machineIndex, jList1.getSelectedIndex());
        myController.updateJobDataPanel();
    }

    /**
     * @param args the command line arguments
     */
    private ListModel listModel = new DefaultListModel();
    private ListModel MachineListModel = new DefaultListModel();
    private MyController myController;
    private JFrame queuePriorityJFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JobPanel;
    private javax.swing.JButton addJobButton;
    private View.AnimationPanel animationPanel;
    private View.GanttChartPanel ganttChartPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private View.JobDataPanel jobDataPanel1;
    private javax.swing.JButton jumpButton;
    private javax.swing.JLabel machineCountLabel;
    private javax.swing.JSpinner machineCountSpinner;
    private View.MachineDataPanel machineDataPanel1;
    private javax.swing.JButton removeJobButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}
