/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author bruno
 */
public class CompartilharImagemView extends javax.swing.JInternalFrame {

    /**
     * Creates new form CompartilharImagemView
     */
    public CompartilharImagemView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrUsuarios = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnCompartilhar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsuarios.setToolTipText("");
        scrUsuarios.setViewportView(tbUsuarios);

        lblNome.setText("Nome:");

        btnBuscar.setText("Buscar");

        btnCompartilhar.setText("Compartilhar");

        btnFechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnFechar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCompartilhar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnCompartilhar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCompartilhar() {
        return btnCompartilhar;
    }

    public JButton getBtnFechar() {
        return btnFechar;
    }

    public JLabel getLblNome() {
        return lblNome;
    }

    public JScrollPane getScrUsuarios() {
        return scrUsuarios;
    }

    public JTable getTbUsuarios() {
        return tbUsuarios;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCompartilhar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JScrollPane scrUsuarios;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
