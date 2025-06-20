package ucv.codelab.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class FrmMenu extends JFrame {

    public FrmMenu() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void initComponents() {
        Escritorio = new JPanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        opRegistrarPacientes = new JMenuItem();
        opMantenimientoPacientes = new JMenuItem();
        jMenu2 = new JMenu();
        poRegistrarDoctor = new JMenuItem();
        opMantenimientoDoctores = new JMenuItem();
        opRegistrarEspecialidad = new JMenu();
        opRegistroEspecialidad = new JMenuItem();
        opMantenimientoEspecialidades = new JMenuItem();
        jMenu4 = new JMenu();
        opRegistrarHistoriaClinica = new JMenuItem();
        opBusquedaMantenimiento = new JMenuItem();
        opAtencionesDia = new JMenuItem();
        opAtencionEspecialidad = new JMenuItem();
        opExportarHistoriaC = new JMenu();
        opExportarHistoria = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Escritorio.setBackground(new Color(0, 102, 102));
        Escritorio.setForeground(new Color(0, 0, 0));

        Escritorio.setLayout(new BorderLayout());

        jMenu1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoPaciente.png")));
        jMenu1.setText("Pacientes");
        jMenu1.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistrarPacientes.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarPa.png")));
        opRegistrarPacientes.setText("Registrar Pacientes");
        opRegistrarPacientes.addActionListener(evt -> opRegistrarPacientesActionPerformed(evt));
        jMenu1.add(opRegistrarPacientes);

        opMantenimientoPacientes
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoPa.png")));
        opMantenimientoPacientes.setText("Mantenimiento de Pacientes");
        opMantenimientoPacientes.addActionListener(evt -> opMantenimientoPacientesActionPerformed(evt));
        jMenu1.add(opMantenimientoPacientes);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoDoctor.png")));
        jMenu2.setText("Doctores");
        jMenu2.setFont(new Font("Dialog", Font.BOLD, 16));

        poRegistrarDoctor.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarDo.png")));
        poRegistrarDoctor.setText("Registrar Doctor");
        poRegistrarDoctor.addActionListener(evt -> poRegistrarDoctorActionPerformed(evt));
        jMenu2.add(poRegistrarDoctor);

        opMantenimientoDoctores
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoDoc.png")));
        opMantenimientoDoctores.setText("Mantenimiento de Doctores");
        opMantenimientoDoctores.addActionListener(evt -> opMantenimientoDoctoresActionPerformed(evt));
        jMenu2.add(opMantenimientoDoctores);

        jMenuBar1.add(jMenu2);

        opRegistrarEspecialidad.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoEspecia..png")));
        opRegistrarEspecialidad.setText("Especialidades");
        opRegistrarEspecialidad.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistroEspecialidad.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarEs.png")));
        opRegistroEspecialidad.setText("Registrar Especialidad");
        opRegistroEspecialidad.addActionListener(evt -> opRegistroEspecialidadActionPerformed(evt));
        opRegistrarEspecialidad.add(opRegistroEspecialidad);

        opMantenimientoEspecialidades
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoEs.png")));
        opMantenimientoEspecialidades.setText("Mantenimiento de Especialidades");
        opMantenimientoEspecialidades.addActionListener(evt -> opMantenimientoEspecialidadesActionPerformed(evt));
        opRegistrarEspecialidad.add(opMantenimientoEspecialidades);

        jMenuBar1.add(opRegistrarEspecialidad);

        jMenu4.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoHistorialC..png")));
        jMenu4.setText("Historias Clinicas");
        jMenu4.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistrarHistoriaClinica
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarHistoriaC.png")));
        opRegistrarHistoriaClinica.setText("Registrar Historia C.");
        opRegistrarHistoriaClinica.addActionListener(evt -> opRegistrarHistoriaClinicaActionPerformed(evt));
        jMenu4.add(opRegistrarHistoriaClinica);

        opBusquedaMantenimiento.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BuscarMante.png")));
        opBusquedaMantenimiento.setText("Busqueda y Mantenimiento");
        opBusquedaMantenimiento.addActionListener(evt -> opBusquedaMantenimientoActionPerformed(evt));
        jMenu4.add(opBusquedaMantenimiento);

        opAtencionesDia.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/AtencionDia.png")));
        opAtencionesDia.setText("Atenciones del Día por Medico");
        opAtencionesDia.addActionListener(evt -> opAtencionesDiaActionPerformed(evt));
        jMenu4.add(opAtencionesDia);

        opAtencionEspecialidad
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/AtencionXespecialidad.png")));
        opAtencionEspecialidad.setText("Atenciones por Especialidad");
        opAtencionEspecialidad.addActionListener(evt -> opAtencionEspecialidadActionPerformed(evt));
        jMenu4.add(opAtencionEspecialidad);

        jMenuBar1.add(jMenu4);

        opExportarHistoriaC.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoExpo.PDF.png")));
        opExportarHistoriaC.setText("Exportar PDF");
        opExportarHistoriaC.setFont(new Font("Dialog", Font.BOLD, 16));

        opExportarHistoria.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ExportarPDF.png")));
        opExportarHistoria.setText("Exportar Historia Clinica");
        opExportarHistoria.addActionListener(evt -> opExportarHistoriaActionPerformed(evt));
        opExportarHistoriaC.add(opExportarHistoria);

        jMenuBar1.add(opExportarHistoriaC);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(Escritorio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(Escritorio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }

    private void opRegistrarPacientesActionPerformed(ActionEvent evt) {
        FrmRegistroPa frm = new FrmRegistroPa();
        Escritorio.removeAll();
        Escritorio.add(frm, BorderLayout.CENTER);
    }

    private void opMantenimientoPacientesActionPerformed(ActionEvent evt) {
        FrmMantenimientoPa frm2 = new FrmMantenimientoPa();
        Escritorio.removeAll();
        Escritorio.add(frm2, BorderLayout.CENTER);
    }

    private void opAtencionEspecialidadActionPerformed(ActionEvent evt) {
        FrmAtencionesEsp frm3 = new FrmAtencionesEsp();
        Escritorio.removeAll();
        Escritorio.add(frm3, BorderLayout.CENTER);
    }

    private void poRegistrarDoctorActionPerformed(ActionEvent evt) {
        FrmRegistroDoc frm4 = new FrmRegistroDoc();
        Escritorio.removeAll();
        Escritorio.add(frm4, BorderLayout.CENTER);
    }

    private void opMantenimientoDoctoresActionPerformed(ActionEvent evt) {
        FrmMantenimientoDoc frm5 = new FrmMantenimientoDoc();
        Escritorio.removeAll();
        Escritorio.add(frm5, BorderLayout.CENTER);
    }

    private void opRegistroEspecialidadActionPerformed(ActionEvent evt) {
        FrmRegistroEsp frm6 = new FrmRegistroEsp();
        Escritorio.removeAll();
        Escritorio.add(frm6, BorderLayout.CENTER);
    }

    private void opMantenimientoEspecialidadesActionPerformed(ActionEvent evt) {
        FrmMantenimientoEspe frm7 = new FrmMantenimientoEspe();
        Escritorio.removeAll();
        Escritorio.add(frm7, BorderLayout.CENTER);
    }

    private void opRegistrarHistoriaClinicaActionPerformed(ActionEvent evt) {
        FrmRegistroHistoriaC frm8 = new FrmRegistroHistoriaC();
        Escritorio.removeAll();
        Escritorio.add(frm8, BorderLayout.CENTER);
    }

    private void opBusquedaMantenimientoActionPerformed(ActionEvent evt) {
        FrmBusquedaMante frm9 = new FrmBusquedaMante();
        Escritorio.removeAll();
        Escritorio.add(frm9, BorderLayout.CENTER);
    }

    private void opAtencionesDiaActionPerformed(ActionEvent evt) {
        FrmAtencionesXDia frm10 = new FrmAtencionesXDia();
        Escritorio.removeAll();
        Escritorio.add(frm10, BorderLayout.CENTER);
    }

    private void opExportarHistoriaActionPerformed(ActionEvent evt) {
        FrmExportarHisC frm11 = new FrmExportarHisC();
        Escritorio.removeAll();
        Escritorio.add(frm11, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            new FrmMenu().setVisible(true);
        });
    }

    private JPanel Escritorio;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu4;
    private JMenuBar jMenuBar1;
    private JMenuItem opAtencionEspecialidad;
    private JMenuItem opAtencionesDia;
    private JMenuItem opBusquedaMantenimiento;
    private JMenuItem opExportarHistoria;
    private JMenu opExportarHistoriaC;
    private JMenuItem opMantenimientoDoctores;
    private JMenuItem opMantenimientoEspecialidades;
    private JMenuItem opMantenimientoPacientes;
    private JMenu opRegistrarEspecialidad;
    private JMenuItem opRegistrarHistoriaClinica;
    private JMenuItem opRegistrarPacientes;
    private JMenuItem opRegistroEspecialidad;
    private JMenuItem poRegistrarDoctor;
}