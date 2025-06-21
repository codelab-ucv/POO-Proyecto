package ucv.codelab.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
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
import java.awt.event.ActionListener;

public class FrmMenu extends JFrame {

    public FrmMenu() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void initComponents() {
        setMinimumSize(new Dimension(1280, 720));

        panelEscritorio = new JPanel();
        menuBarPrincipal = new JMenuBar();

        // Menús principales
        menuPacientes = new JMenu();
        menuDoctores = new JMenu();
        menuEspecialidades = new JMenu();
        menuHistoriasClinicas = new JMenu();
        menuExportarPdf = new JMenu();

        // MenuItems de Pacientes
        menuItemRegistrarPaciente = new JMenuItem();
        menuItemMantenimientoPacientes = new JMenuItem();

        // MenuItems de Doctores
        menuItemRegistrarDoctor = new JMenuItem();
        menuItemMantenimientoDoctores = new JMenuItem();

        // MenuItems de Especialidades
        menuItemRegistrarEspecialidad = new JMenuItem();
        menuItemMantenimientoEspecialidades = new JMenuItem();

        // MenuItems de Historias Clínicas
        menuItemRegistrarHistoriaClinica = new JMenuItem();
        menuItemBusquedaMantenimientoHistorias = new JMenuItem();
        menuItemAtencionesPorDia = new JMenuItem();
        menuItemAtencionesPorEspecialidad = new JMenuItem();

        // MenuItems de Exportar
        menuItemExportarHistoriaClinica = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelEscritorio.setBackground(new Color(0, 102, 102));
        panelEscritorio.setLayout(new BorderLayout());

        // Configuración del menú Pacientes
        configurarMenu(menuPacientes, "Pacientes", "/ucv/codelab/images/IconoPaciente.png");
        configurarMenuItem(menuItemRegistrarPaciente, menuPacientes, "Registrar Pacientes",
                "/ucv/codelab/images/RegistrarPa.png",
                evt -> menuItemRegistrarPacienteActionPerformed(evt));
        configurarMenuItem(menuItemMantenimientoPacientes, menuPacientes, "Mantenimiento de Pacientes",
                "/ucv/codelab/images/ManteniminetoPa.png",
                evt -> menuItemMantenimientoPacientesActionPerformed(evt));
        menuBarPrincipal.add(menuPacientes);

        // Configuración del menú Doctores
        configurarMenu(menuDoctores, "Doctores", "/ucv/codelab/images/IconoDoctor.png");
        configurarMenuItem(menuItemRegistrarDoctor, menuDoctores, "Registrar Doctor",
                "/ucv/codelab/images/RegistrarDo.png",
                evt -> menuItemRegistrarDoctorActionPerformed(evt));
        configurarMenuItem(menuItemMantenimientoDoctores, menuDoctores, "Mantenimiento de Doctores",
                "/ucv/codelab/images/ManteniminetoDoc.png",
                evt -> menuItemMantenimientoDoctoresActionPerformed(evt));
        menuBarPrincipal.add(menuDoctores);

        // Configuración del menú Especialidades
        configurarMenu(menuEspecialidades, "Especialidades", "/ucv/codelab/images/IconoEspecia..png");
        configurarMenuItem(menuItemRegistrarEspecialidad, menuEspecialidades, "Registrar Especialidad",
                "/ucv/codelab/images/RegistrarEs.png",
                evt -> menuItemRegistrarEspecialidadActionPerformed(evt));
        configurarMenuItem(menuItemMantenimientoEspecialidades, menuEspecialidades, "Mantenimiento de Especialidades",
                "/ucv/codelab/images/ManteniminetoEs.png",
                evt -> menuItemMantenimientoEspecialidadesActionPerformed(evt));
        menuBarPrincipal.add(menuEspecialidades);

        // Configuración del menú Historias Clínicas
        configurarMenu(menuHistoriasClinicas, "Historias Clínicas", "/ucv/codelab/images/IconoHistorialC..png");
        configurarMenuItem(menuItemRegistrarHistoriaClinica, menuHistoriasClinicas, "Registrar Historia C.",
                "/ucv/codelab/images/RegistrarHistoriaC.png",
                evt -> menuItemRegistrarHistoriaClinicaActionPerformed(evt));
        configurarMenuItem(menuItemBusquedaMantenimientoHistorias, menuHistoriasClinicas, "Búsqueda y Mantenimiento",
                "/ucv/codelab/images/BuscarMante.png",
                evt -> menuItemBusquedaMantenimientoHistoriasActionPerformed(evt));
        configurarMenuItem(menuItemAtencionesPorDia, menuHistoriasClinicas, "Atenciones del Día por Médico",
                "/ucv/codelab/images/AtencionDia.png",
                evt -> menuItemAtencionesPorDiaActionPerformed(evt));
        configurarMenuItem(menuItemAtencionesPorEspecialidad, menuHistoriasClinicas, "Atenciones por Especialidad",
                "/ucv/codelab/images/AtencionXespecialidad.png",
                evt -> menuItemAtencionesPorEspecialidadActionPerformed(evt));
        menuBarPrincipal.add(menuHistoriasClinicas);

        // Configuración del menú Exportar PDF
        configurarMenu(menuExportarPdf, "Exportar PDF", "/ucv/codelab/images/IconoExpo.PDF.png");
        configurarMenuItem(menuItemExportarHistoriaClinica, menuExportarPdf, "Exportar Historia Clínica",
                "/ucv/codelab/images/ExportarPDF.png",
                evt -> menuItemExportarHistoriaClinicaActionPerformed(evt));
        menuBarPrincipal.add(menuExportarPdf);

        setJMenuBar(menuBarPrincipal);

        add(panelEscritorio);

        pack();
    }

    private void configurarMenu(JMenu menu, String titulo, String imagePath) {
        menu.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        menu.setText(titulo);
        menu.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    private void configurarMenuItem(JMenuItem menuItem, JMenu menu, String titulo, String imagePath,
            ActionListener accion) {
        menuItem.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        menuItem.setText(titulo);
        menuItem.addActionListener(accion);
        menu.add(menuItem);
    }

    private void abrirFormularioEnEscritorio(JPanel formulario) {
        panelEscritorio.removeAll();
        panelEscritorio.add(formulario, BorderLayout.CENTER);
        panelEscritorio.revalidate();
        panelEscritorio.repaint();
        this.revalidate();
        this.repaint();
    }

    // Event handlers para Pacientes
    private void menuItemRegistrarPacienteActionPerformed(ActionEvent evt) {
        FrmRegistroPa frm = new FrmRegistroPa();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemMantenimientoPacientesActionPerformed(ActionEvent evt) {
        FrmMantenimientoPa frm = new FrmMantenimientoPa();
        abrirFormularioEnEscritorio(frm);
    }

    // Event handlers para Doctores
    private void menuItemRegistrarDoctorActionPerformed(ActionEvent evt) {
        FrmRegistroDoc frm = new FrmRegistroDoc();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemMantenimientoDoctoresActionPerformed(ActionEvent evt) {
        FrmMantenimientoDoc frm = new FrmMantenimientoDoc();
        abrirFormularioEnEscritorio(frm);
    }

    // Event handlers para Especialidades
    private void menuItemRegistrarEspecialidadActionPerformed(ActionEvent evt) {
        FrmRegistroEsp frm = new FrmRegistroEsp();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemMantenimientoEspecialidadesActionPerformed(ActionEvent evt) {
        FrmMantenimientoEspe frm = new FrmMantenimientoEspe();
        abrirFormularioEnEscritorio(frm);
    }

    // Event handlers para Historias Clínicas
    private void menuItemRegistrarHistoriaClinicaActionPerformed(ActionEvent evt) {
        FrmRegistroHistoriaC frm = new FrmRegistroHistoriaC();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemBusquedaMantenimientoHistoriasActionPerformed(ActionEvent evt) {
        FrmBusquedaMante frm = new FrmBusquedaMante();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemAtencionesPorDiaActionPerformed(ActionEvent evt) {
        FrmAtencionesDia frm = new FrmAtencionesDia();
        abrirFormularioEnEscritorio(frm);
    }

    private void menuItemAtencionesPorEspecialidadActionPerformed(ActionEvent evt) {
        FrmAtencionesEsp frm = new FrmAtencionesEsp();
        abrirFormularioEnEscritorio(frm);
    }

    // Event handler para Exportar
    private void menuItemExportarHistoriaClinicaActionPerformed(ActionEvent evt) {
        FrmExportarHisC frm = new FrmExportarHisC();
        abrirFormularioEnEscritorio(frm);
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

    // Variables de componentes con nombres descriptivos y estandarizados
    private JPanel panelEscritorio;
    private JMenuBar menuBarPrincipal;

    // Menús principales
    private JMenu menuPacientes;
    private JMenu menuDoctores;
    private JMenu menuEspecialidades;
    private JMenu menuHistoriasClinicas;
    private JMenu menuExportarPdf;

    // MenuItems de Pacientes
    private JMenuItem menuItemRegistrarPaciente;
    private JMenuItem menuItemMantenimientoPacientes;

    // MenuItems de Doctores
    private JMenuItem menuItemRegistrarDoctor;
    private JMenuItem menuItemMantenimientoDoctores;

    // MenuItems de Especialidades
    private JMenuItem menuItemRegistrarEspecialidad;
    private JMenuItem menuItemMantenimientoEspecialidades;

    // MenuItems de Historias Clínicas
    private JMenuItem menuItemRegistrarHistoriaClinica;
    private JMenuItem menuItemBusquedaMantenimientoHistorias;
    private JMenuItem menuItemAtencionesPorDia;
    private JMenuItem menuItemAtencionesPorEspecialidad;

    // MenuItems de Exportar
    private JMenuItem menuItemExportarHistoriaClinica;
}