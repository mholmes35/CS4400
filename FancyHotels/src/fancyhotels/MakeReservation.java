/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyhotels;

import Entities.Customer;
import java.sql.Date;
import Entities.Room;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ernestwilliams
 */
public class MakeReservation extends javax.swing.JFrame {
    FancyHotelSingleton singleton;
    private static String location;
    private static String startDate;
    private static String endDate;

    /**
     * Creates new form MakeReservation
     */
    public MakeReservation(String location, String startDate, String endDate) {
        singleton = FancyHotelSingleton.getInstance();
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        
        initComponents();
        try{
            if (singleton.isPast(startDate, endDate)) {
               throw new Error("Cannot make a reservation for a date in the past.");
            }

            ArrayList<Room> availableRooms = singleton.findRooms(location, 
                    startDate, endDate);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            for (Room room: availableRooms) {
                model.addRow(new Object[]{room.getRoomNumber(),
                    room.getRoomCategory(),room.getNumPeople(),
                    room.getCostPerDay(),room.getExtraBedCost()});
            }
        }catch(Exception e){
             System.out.println("Exception: " + e);
        }
        
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        checkDetailsButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Make a Reservation");

        checkDetailsButton.setText("Check Details");
        checkDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDetailsButtonActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Room Category", "# Persons Allowed", "Cost Per Day", "Cost of Extra Bed per Day", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAlignmentX(0.0F);
        jTable2.setAlignmentY(0.0F);
        jTable2.setShowGrid(true);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(checkDetailsButton)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkDetailsButton)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDetailsButtonActionPerformed
        // TODO add your handling code here:
        //open up modified make reservation screen
        //frame or panel?
        ArrayList<Room> chosenRooms = new ArrayList<Room>();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int numRows = model.getRowCount();
        for(int i =0; i < numRows; i++){
            if(model.getValueAt(i,5) != null){
                if((boolean)model.getValueAt(i,5)){
                    int numPeople = (int)model.getValueAt(i,2);
                    String category = (String)model.getValueAt(i,1);
                    float cPD = (float)model.getValueAt(i,3);
                    int roomNum = (int)model.getValueAt(i,0);
                    chosenRooms.add(new Room(numPeople, category, cPD, roomNum, 
                    location, false, 10.00f));
                }
            }
        }
        new MakeReservationDetail(chosenRooms, startDate, endDate).setVisible(true);
    }//GEN-LAST:event_checkDetailsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MakeReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakeReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakeReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakeReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakeReservation(location, startDate, endDate).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkDetailsButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
