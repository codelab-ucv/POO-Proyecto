package ucv.codelab.view;

import javax.swing.JInternalFrame;

public class FrmMenu extends javax.swing.JFrame {

    public FrmMenu() {
        initComponents();
         setExtendedState(MAXIMIZED_BOTH); // Maximiza la ventana al iniciar
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        opRegistrarPacientes = new javax.swing.JMenuItem();
        opMantenimientoPacientes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        poRegistrarDoctor = new javax.swing.JMenuItem();
        opMantenimientoDoctores = new javax.swing.JMenuItem();
        opRegistrarEspecialidad = new javax.swing.JMenu();
        opRegistroEspecialidad = new javax.swing.JMenuItem();
        opMantenimientoEspecialidades = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        opRegistrarHistoriaClinica = new javax.swing.JMenuItem();
        opBusquedaMantenimiento = new javax.swing.JMenuItem();
        opAtencionesDia = new javax.swing.JMenuItem();
        opAtencionEspecialidad = new javax.swing.JMenuItem();
        opExportarHistoriaC = new javax.swing.JMenu();
        opExportarHistoria = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Escritorio.setBackground(new java.awt.Color(0, 102, 102));
        Escritorio.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoPaciente.png"))); // NOI18N
        jMenu1.setText("Pacientes");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        opRegistrarPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RegistrarPa.png"))); // NOI18N
        opRegistrarPacientes.setText("Registrar Pacientes");
        opRegistrarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opRegistrarPacientesActionPerformed(evt);
            }
        });
        jMenu1.add(opRegistrarPacientes);

        opMantenimientoPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ManteniminetoPa.png"))); // NOI18N
        opMantenimientoPacientes.setText("Mantenimiento de  Pacientes");
        opMantenimientoPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opMantenimientoPacientesActionPerformed(evt);
            }
        });
        jMenu1.add(opMantenimientoPacientes);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoDoctor.png"))); // NOI18N
        jMenu2.setText("Doctores");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        poRegistrarDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RegistrarDo.png"))); // NOI18N
        poRegistrarDoctor.setText("Registrar Doctor");
        poRegistrarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poRegistrarDoctorActionPerformed(evt);
            }
        });
        jMenu2.add(poRegistrarDoctor);

        opMantenimientoDoctores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ManteniminetoDoc.png"))); // NOI18N
        opMantenimientoDoctores.setText("Mantenimiento de Doctores");
        opMantenimientoDoctores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opMantenimientoDoctoresActionPerformed(evt);
            }
        });
        jMenu2.add(opMantenimientoDoctores);

        jMenuBar1.add(jMenu2);

        opRegistrarEspecialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoEspecia..png"))); // NOI18N
        opRegistrarEspecialidad.setText("Especialidades");
        opRegistrarEspecialidad.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        opRegistroEspecialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RegistrarEs.png"))); // NOI18N
        opRegistroEspecialidad.setText("Registrar Especialidad");
        opRegistroEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opRegistroEspecialidadActionPerformed(evt);
            }
        });
        opRegistrarEspecialidad.add(opRegistroEspecialidad);

        opMantenimientoEspecialidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ManteniminetoEs.png"))); // NOI18N
        opMantenimientoEspecialidades.setText("Mantenimiento de Especialidades");
        opMantenimientoEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opMantenimientoEspecialidadesActionPerformed(evt);
            }
        });
        opRegistrarEspecialidad.add(opMantenimientoEspecialidades);

        jMenuBar1.add(opRegistrarEspecialidad);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoHistorialC..png"))); // NOI18N
        jMenu4.setText("Historias Clinicas");
        jMenu4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        opRegistrarHistoriaClinica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RegistrarHistoriaC.png"))); // NOI18N
        opRegistrarHistoriaClinica.setText("Registrar Historia C.");
        opRegistrarHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opRegistrarHistoriaClinicaActionPerformed(evt);
            }
        });
        jMenu4.add(opRegistrarHistoriaClinica);

        opBusquedaMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarMante.png"))); // NOI18N
        opBusquedaMantenimiento.setText("Busqueda y Mantenimiento");
        opBusquedaMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opBusquedaMantenimientoActionPerformed(evt);
            }
        });
        jMenu4.add(opBusquedaMantenimiento);

        opAtencionesDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AtencionDia.png"))); // NOI18N
        opAtencionesDia.setText("Atenciones del Día por Medico");
        opAtencionesDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opAtencionesDiaActionPerformed(evt);
            }
        });
        jMenu4.add(opAtencionesDia);

        opAtencionEspecialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AtencionXespecialidad.png"))); // NOI18N
        opAtencionEspecialidad.setText("Atenciones por Especialidad");
        opAtencionEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opAtencionEspecialidadActionPerformed(evt);
            }
        });
        jMenu4.add(opAtencionEspecialidad);

        jMenuBar1.add(jMenu4);

        opExportarHistoriaC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoExpo.PDF.png"))); // NOI18N
        opExportarHistoriaC.setText("Exportar PDF");
        opExportarHistoriaC.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        opExportarHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ExportarPDF.png"))); // NOI18N
        opExportarHistoria.setText("Exportar Historia Clinica");
        opExportarHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opExportarHistoriaActionPerformed(evt);
            }
        });
        opExportarHistoriaC.add(opExportarHistoria);

        jMenuBar1.add(opExportarHistoriaC);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opRegistrarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opRegistrarPacientesActionPerformed
        FrmRegistroPa frm = new FrmRegistroPa();  
        Escritorio.add(frm);  
        frm.setVisible(true);                               
    }//GEN-LAST:event_opRegistrarPacientesActionPerformed

    private void opMantenimientoPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opMantenimientoPacientesActionPerformed
        FrmMantenimientoPa frm2 = new FrmMantenimientoPa(); 
        Escritorio.add(frm2);                
        frm2.setVisible(true);
    }//GEN-LAST:event_opMantenimientoPacientesActionPerformed

    private void opAtencionEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opAtencionEspecialidadActionPerformed
        FrmAtencionesEsp frm3 = new FrmAtencionesEsp(); 
        Escritorio.add(frm3);                
        frm3.setVisible(true);
    }//GEN-LAST:event_opAtencionEspecialidadActionPerformed

    private void poRegistrarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poRegistrarDoctorActionPerformed
        FrmRegistroDoc frm4 = new FrmRegistroDoc(); 
        Escritorio.add(frm4);                
        frm4.setVisible(true);  
    }//GEN-LAST:event_poRegistrarDoctorActionPerformed

    private void opMantenimientoDoctoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opMantenimientoDoctoresActionPerformed
        FrmMantenimientoDoc frm5 = new FrmMantenimientoDoc(); // Tu JInternalFrame
        Escritorio.add(frm5);                // Lo agregas al DesktopPane
        frm5.setVisible(true);
    }//GEN-LAST:event_opMantenimientoDoctoresActionPerformed

    private void opRegistroEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opRegistroEspecialidadActionPerformed
        FrmRegistroEsp frm6 = new FrmRegistroEsp(); 
        Escritorio.add(frm6);              
        frm6.setVisible(true);  
    }//GEN-LAST:event_opRegistroEspecialidadActionPerformed

    private void opMantenimientoEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opMantenimientoEspecialidadesActionPerformed
        FrmMantenimientoEspe frm7 = new FrmMantenimientoEspe();
        Escritorio.add(frm7);              
        frm7.setVisible(true);
    }//GEN-LAST:event_opMantenimientoEspecialidadesActionPerformed

    private void opRegistrarHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opRegistrarHistoriaClinicaActionPerformed
        FrmRegistroHistoriaC frm8 = new FrmRegistroHistoriaC(); 
        Escritorio.add(frm8);                
        frm8.setVisible(true); 
    }//GEN-LAST:event_opRegistrarHistoriaClinicaActionPerformed

    private void opBusquedaMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opBusquedaMantenimientoActionPerformed
        FrmBusquedaMante frm9 = new FrmBusquedaMante(); 
        Escritorio.add(frm9);                
        frm9.setVisible(true); 
    }//GEN-LAST:event_opBusquedaMantenimientoActionPerformed

    private void opAtencionesDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opAtencionesDiaActionPerformed
        FrmAtencionesXDia frm10 = new FrmAtencionesXDia(); 
        Escritorio.add(frm10);                
        frm10.setVisible(true);
    }//GEN-LAST:event_opAtencionesDiaActionPerformed

    private void opExportarHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opExportarHistoriaActionPerformed
        FrmExportarHisC frm11 = new FrmExportarHisC(); 
        Escritorio.add(frm11);                
        frm11.setVisible(true);
    }//GEN-LAST:event_opExportarHistoriaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem opAtencionEspecialidad;
    private javax.swing.JMenuItem opAtencionesDia;
    private javax.swing.JMenuItem opBusquedaMantenimiento;
    private javax.swing.JMenuItem opExportarHistoria;
    private javax.swing.JMenu opExportarHistoriaC;
    private javax.swing.JMenuItem opMantenimientoDoctores;
    private javax.swing.JMenuItem opMantenimientoEspecialidades;
    private javax.swing.JMenuItem opMantenimientoPacientes;
    private javax.swing.JMenu opRegistrarEspecialidad;
    private javax.swing.JMenuItem opRegistrarHistoriaClinica;
    private javax.swing.JMenuItem opRegistrarPacientes;
    private javax.swing.JMenuItem opRegistroEspecialidad;
    private javax.swing.JMenuItem poRegistrarDoctor;
    // End of variables declaration//GEN-END:variables
}
