/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

//import Entities.Reservation;

/**
 *
 * @author morganholmes
 */
public class MakeReservationDetail extends javax.swing.JFrame {
    private static ArrayList<Room> chosenRooms;
    FancyHotelSingleton singleton;
    private static String startDate;
    private static String endDate;
    private static float numDays;
    private Float cost = 0.0f;  
    /**
     * Creates new form MakeReservationDetail
     */
    public MakeReservationDetail(ArrayList<Room> chosenRooms, String startDate,
            String endDate) {
        initComponents();
        singleton = FancyHotelSingleton.getInstance();
        this.chosenRooms = chosenRooms;
        this.startDate = startDate;
        this.endDate = endDate;
        startField.setText(startDate);
        endField.setText(endDate);
        
        numDays = singleton.countDays(startDate, endDate);
        DefaultTableModel model = (DefaultTableModel) chosenRoomsTable.getModel();
        model.setRowCount(0);
        for (Room room: chosenRooms) {
            model.addRow(new Object[]{room.getRoomNumber(),
                room.getRoomCategory(),room.getNumPeople(),
                room.getCostPerDay(),room.getExtraBedCost()});
            cost += (room.getCostPerDay() * numDays);
        }
        ArrayList<Entities.PaymentInformation> cards = singleton.getUserCards();
        String[] numOnly = new String[cards.size()];
        for(int i = 0; i < cards.size(); i++){
            numOnly[i] = cards.get(i).getCardNum();
        }
        DefaultComboBoxModel boxMod = new DefaultComboBoxModel(numOnly);
        cardBox.setModel(boxMod);
        cardBox.setMaximumRowCount(cards.size());

        costField.setText(cost.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        chosenRoomsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cardBox = new javax.swing.JComboBox<>();
        startField = new javax.swing.JTextField();
        endField = new javax.swing.JTextField();
        costField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        updateCost = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));

        chosenRoomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Room Category", "# Persons Allowed", "Cost Per Day", "Cost of Extra Bed per Day", "Extra Bed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        chosenRoomsTable.setAlignmentX(0.0F);
        chosenRoomsTable.setAlignmentY(0.0F);
        chosenRoomsTable.setShowGrid(true);
        jScrollPane2.setViewportView(chosenRoomsTable);

        jLabel1.setText("Start Date:");

        jLabel2.setText("End Date:");

        jLabel3.setText("Total Cost:");

        jLabel4.setText("Use Card");

        cardBox.setMaximumRowCount(100);
        cardBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        startField.setEditable(false);
        startField.setText("jTextField1");

        endField.setEditable(false);
        endField.setText("jTextField2");

        costField.setEditable(false);
        costField.setText("jTextField3");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        updateCost.setText("Update Cost");
        updateCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCostActionPerformed(evt);
            }
        });

        jLabel5.setText("Reservation Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateCost))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cardBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        //open payment info
        //make a reservation.
        String worked = singleton.makeReservation(chosenRooms, startDate, 
                endDate, (String)cardBox.getSelectedItem(), cost);
        if(worked != null){
                Confirm confirm = new Confirm(worked); 
                confirm.setVisible(true);
        }
        
        //get all the info from the elements
        //Reservation res = new Reservation();
        Reservation res = null; //to be deleted
        //new ConfirmationScreen(this.user, res).setVisible(true);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void updateCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCostActionPerformed
        // TODO add your handling code here:
        Float cost = 0.0f;
        DefaultTableModel model = (DefaultTableModel) chosenRoomsTable.getModel();
        int numRows = model.getRowCount();
        for (int i = 0; i < numRows; i++) {
            cost += ((float)model.getValueAt(i,3) * numDays);
            if(model.getValueAt(i,5) != null){
                if((boolean)model.getValueAt(i, 5))
                cost += 10.0f;
                chosenRooms.get(i).setExtraBed(true);
            }
        }
        costField.setText(cost.toString());
    }//GEN-LAST:event_updateCostActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MakeReservationDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakeReservationDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakeReservationDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakeReservationDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakeReservationDetail(chosenRooms, startDate, endDate).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cardBox;
    private javax.swing.JTable chosenRoomsTable;
    private javax.swing.JTextField costField;
    private javax.swing.JTextField endField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField startField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton updateCost;
    // End of variables declaration//GEN-END:variables
}
