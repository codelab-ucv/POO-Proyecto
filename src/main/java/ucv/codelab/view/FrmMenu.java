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
import java.awt.Dimension;
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
        setMinimumSize(new Dimension(1280, 720));

        escritorio = new JPanel();
        barraMenu = new JMenuBar();
        menuPaciente = new JMenu();
        opRegistrarPacientes = new JMenuItem();
        opMantenimientoPacientes = new JMenuItem();
        menuMedico = new JMenu();
        poRegistrarDoctor = new JMenuItem();
        opMantenimientoDoctores = new JMenuItem();
        menuEspecialidad = new JMenu();
        opRegistroEspecialidad = new JMenuItem();
        opMantenimientoEspecialidades = new JMenuItem();
        menuHistoriaClinica = new JMenu();
        opRegistrarHistoriaClinica = new JMenuItem();
        opBusquedaMantenimiento = new JMenuItem();
        opAtencionesDia = new JMenuItem();
        opAtencionEspecialidad = new JMenuItem();
        menuExportar = new JMenu();
        opExportarHistoria = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new Color(0, 102, 102));
        escritorio.setForeground(new Color(0, 0, 0));

        escritorio.setLayout(new BorderLayout());

        menuPaciente.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoPaciente.png")));
        menuPaciente.setText("Pacientes");
        menuPaciente.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistrarPacientes.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarPa.png")));
        opRegistrarPacientes.setText("Registrar Pacientes");
        opRegistrarPacientes.addActionListener(evt -> opRegistrarPacientesActionPerformed(evt));
        menuPaciente.add(opRegistrarPacientes);

        opMantenimientoPacientes
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoPa.png")));
        opMantenimientoPacientes.setText("Mantenimiento de Pacientes");
        opMantenimientoPacientes.addActionListener(evt -> opMantenimientoPacientesActionPerformed(evt));
        menuPaciente.add(opMantenimientoPacientes);

        barraMenu.add(menuPaciente);

        menuMedico.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoDoctor.png")));
        menuMedico.setText("Doctores");
        menuMedico.setFont(new Font("Dialog", Font.BOLD, 16));

        poRegistrarDoctor.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarDo.png")));
        poRegistrarDoctor.setText("Registrar Doctor");
        poRegistrarDoctor.addActionListener(evt -> poRegistrarDoctorActionPerformed(evt));
        menuMedico.add(poRegistrarDoctor);

        opMantenimientoDoctores
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoDoc.png")));
        opMantenimientoDoctores.setText("Mantenimiento de Doctores");
        opMantenimientoDoctores.addActionListener(evt -> opMantenimientoDoctoresActionPerformed(evt));
        menuMedico.add(opMantenimientoDoctores);

        barraMenu.add(menuMedico);

        menuEspecialidad.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoEspecia..png")));
        menuEspecialidad.setText("Especialidades");
        menuEspecialidad.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistroEspecialidad.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarEs.png")));
        opRegistroEspecialidad.setText("Registrar Especialidad");
        opRegistroEspecialidad.addActionListener(evt -> opRegistroEspecialidadActionPerformed(evt));
        menuEspecialidad.add(opRegistroEspecialidad);

        opMantenimientoEspecialidades
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ManteniminetoEs.png")));
        opMantenimientoEspecialidades.setText("Mantenimiento de Especialidades");
        opMantenimientoEspecialidades.addActionListener(evt -> opMantenimientoEspecialidadesActionPerformed(evt));
        menuEspecialidad.add(opMantenimientoEspecialidades);

        barraMenu.add(menuEspecialidad);

        menuHistoriaClinica.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoHistorialC..png")));
        menuHistoriaClinica.setText("Historias Clinicas");
        menuHistoriaClinica.setFont(new Font("Dialog", Font.BOLD, 16));

        opRegistrarHistoriaClinica
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/RegistrarHistoriaC.png")));
        opRegistrarHistoriaClinica.setText("Registrar Historia C.");
        opRegistrarHistoriaClinica.addActionListener(evt -> opRegistrarHistoriaClinicaActionPerformed(evt));
        menuHistoriaClinica.add(opRegistrarHistoriaClinica);

        opBusquedaMantenimiento.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BuscarMante.png")));
        opBusquedaMantenimiento.setText("Busqueda y Mantenimiento");
        opBusquedaMantenimiento.addActionListener(evt -> opBusquedaMantenimientoActionPerformed(evt));
        menuHistoriaClinica.add(opBusquedaMantenimiento);

        opAtencionesDia.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/AtencionDia.png")));
        opAtencionesDia.setText("Atenciones del Día por Medico");
        opAtencionesDia.addActionListener(evt -> opAtencionesDiaActionPerformed(evt));
        menuHistoriaClinica.add(opAtencionesDia);

        opAtencionEspecialidad
                .setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/AtencionXespecialidad.png")));
        opAtencionEspecialidad.setText("Atenciones por Especialidad");
        opAtencionEspecialidad.addActionListener(evt -> opAtencionEspecialidadActionPerformed(evt));
        menuHistoriaClinica.add(opAtencionEspecialidad);

        barraMenu.add(menuHistoriaClinica);

        menuExportar.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoExpo.PDF.png")));
        menuExportar.setText("Exportar PDF");
        menuExportar.setFont(new Font("Dialog", Font.BOLD, 16));

        opExportarHistoria.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ExportarPDF.png")));
        opExportarHistoria.setText("Exportar Historia Clinica");
        opExportarHistoria.addActionListener(evt -> opExportarHistoriaActionPerformed(evt));
        menuExportar.add(opExportarHistoria);

        barraMenu.add(menuExportar, BorderLayout.NORTH);

        setJMenuBar(barraMenu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(escritorio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(escritorio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }

    private void opRegistrarPacientesActionPerformed(ActionEvent evt) {
        FrmRegistroPa frm = new FrmRegistroPa();
        escritorio.removeAll();
        escritorio.add(frm, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opMantenimientoPacientesActionPerformed(ActionEvent evt) {
        FrmMantenimientoPa frm2 = new FrmMantenimientoPa();
        escritorio.removeAll();
        escritorio.add(frm2, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opAtencionEspecialidadActionPerformed(ActionEvent evt) {
        FrmAtencionesEsp frm3 = new FrmAtencionesEsp();
        escritorio.removeAll();
        escritorio.add(frm3, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void poRegistrarDoctorActionPerformed(ActionEvent evt) {
        FrmRegistroDoc frm4 = new FrmRegistroDoc();
        escritorio.removeAll();
        escritorio.add(frm4, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opMantenimientoDoctoresActionPerformed(ActionEvent evt) {
        FrmMantenimientoDoc frm5 = new FrmMantenimientoDoc();
        escritorio.removeAll();
        escritorio.add(frm5, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opRegistroEspecialidadActionPerformed(ActionEvent evt) {
        FrmRegistroEsp frm6 = new FrmRegistroEsp();
        escritorio.removeAll();
        escritorio.add(frm6, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opMantenimientoEspecialidadesActionPerformed(ActionEvent evt) {
        FrmMantenimientoEspe frm7 = new FrmMantenimientoEspe();
        escritorio.removeAll();
        escritorio.add(frm7, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opRegistrarHistoriaClinicaActionPerformed(ActionEvent evt) {
        FrmRegistroHistoriaC frm8 = new FrmRegistroHistoriaC();
        escritorio.removeAll();
        escritorio.add(frm8, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opBusquedaMantenimientoActionPerformed(ActionEvent evt) {
        FrmBusquedaMante frm9 = new FrmBusquedaMante();
        escritorio.removeAll();
        escritorio.add(frm9, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opAtencionesDiaActionPerformed(ActionEvent evt) {
        FrmAtencionesDia frm10 = new FrmAtencionesDia();
        escritorio.removeAll();
        escritorio.add(frm10, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    private void opExportarHistoriaActionPerformed(ActionEvent evt) {
        FrmExportarHisC frm11 = new FrmExportarHisC();
        escritorio.removeAll();
        escritorio.add(frm11, BorderLayout.CENTER);
        escritorio.revalidate();
        escritorio.repaint();
        this.revalidate();
        this.repaint();
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

    private JPanel escritorio;
    private JMenu menuPaciente;
    private JMenu menuMedico;
    private JMenu menuHistoriaClinica;
    private JMenuBar barraMenu;
    private JMenuItem opAtencionEspecialidad;
    private JMenuItem opAtencionesDia;
    private JMenuItem opBusquedaMantenimiento;
    private JMenuItem opExportarHistoria;
    private JMenu menuExportar;
    private JMenuItem opMantenimientoDoctores;
    private JMenuItem opMantenimientoEspecialidades;
    private JMenuItem opMantenimientoPacientes;
    private JMenu menuEspecialidad;
    private JMenuItem opRegistrarHistoriaClinica;
    private JMenuItem opRegistrarPacientes;
    private JMenuItem opRegistroEspecialidad;
    private JMenuItem poRegistrarDoctor;
}