package ucv.codelab.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class FrmMenu extends JFrame {

    public FrmMenu() {
        initComponents();
    }

    private void initComponents() {
        setMinimumSize(new Dimension(1440, 810));

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

        panelEscritorio.setBackground(new Color(0, 102, 102));
        panelEscritorio.setLayout(new BorderLayout());

        // Configuración del menú Pacientes
        configurarMenu(menuPacientes, "Pacientes", "/ucv/codelab/images/IconoPaciente.png");
        configurarMenuItem(menuItemRegistrarPaciente, menuPacientes, "Registrar Pacientes",
                "/ucv/codelab/images/RegistrarPa.png");
        configurarMenuItem(menuItemMantenimientoPacientes, menuPacientes, "Mantenimiento de Pacientes",
                "/ucv/codelab/images/ManteniminetoPa.png");
        menuBarPrincipal.add(menuPacientes);

        // Configuración del menú Doctores
        configurarMenu(menuDoctores, "Doctores", "/ucv/codelab/images/IconoDoctor.png");
        configurarMenuItem(menuItemRegistrarDoctor, menuDoctores, "Registrar Doctor",
                "/ucv/codelab/images/RegistrarDo.png");
        configurarMenuItem(menuItemMantenimientoDoctores, menuDoctores, "Mantenimiento de Doctores",
                "/ucv/codelab/images/ManteniminetoDoc.png");
        menuBarPrincipal.add(menuDoctores);

        // Configuración del menú Especialidades
        configurarMenu(menuEspecialidades, "Especialidades", "/ucv/codelab/images/IconoEspecia..png");
        configurarMenuItem(menuItemRegistrarEspecialidad, menuEspecialidades, "Registrar Especialidad",
                "/ucv/codelab/images/RegistrarEs.png");
        configurarMenuItem(menuItemMantenimientoEspecialidades, menuEspecialidades, "Mantenimiento de Especialidades",
                "/ucv/codelab/images/ManteniminetoEs.png");
        menuBarPrincipal.add(menuEspecialidades);

        // Configuración del menú Historias Clínicas
        configurarMenu(menuHistoriasClinicas, "Historias Clínicas", "/ucv/codelab/images/IconoHistorialC..png");
        configurarMenuItem(menuItemRegistrarHistoriaClinica, menuHistoriasClinicas, "Registrar Historia C.",
                "/ucv/codelab/images/RegistrarHistoriaC.png");
        configurarMenuItem(menuItemBusquedaMantenimientoHistorias, menuHistoriasClinicas, "Búsqueda y Mantenimiento",
                "/ucv/codelab/images/BuscarMante.png");
        configurarMenuItem(menuItemAtencionesPorDia, menuHistoriasClinicas, "Atenciones del Día por Médico",
                "/ucv/codelab/images/AtencionDia.png");
        configurarMenuItem(menuItemAtencionesPorEspecialidad, menuHistoriasClinicas, "Atenciones por Especialidad",
                "/ucv/codelab/images/AtencionXespecialidad.png");
        menuBarPrincipal.add(menuHistoriasClinicas);

        // Configuración del menú Exportar PDF
        configurarMenu(menuExportarPdf, "Exportar PDF", "/ucv/codelab/images/IconoExpo.PDF.png");
        configurarMenuItem(menuItemExportarHistoriaClinica, menuExportarPdf, "Exportar Historia Clínica",
                "/ucv/codelab/images/ExportarPDF.png");
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

    private void configurarMenuItem(JMenuItem menuItem, JMenu menu, String titulo, String imagePath) {
        menuItem.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        menuItem.setText(titulo);
        menu.add(menuItem);
    }

    // Variables de componentes con nombres descriptivos y estandarizados
    public JPanel panelEscritorio;
    private JMenuBar menuBarPrincipal;

    // Menús principales
    private JMenu menuPacientes;
    private JMenu menuDoctores;
    private JMenu menuEspecialidades;
    private JMenu menuHistoriasClinicas;
    private JMenu menuExportarPdf;

    // MenuItems de Pacientes
    public JMenuItem menuItemRegistrarPaciente;
    public JMenuItem menuItemMantenimientoPacientes;

    // MenuItems de Doctores
    public JMenuItem menuItemRegistrarDoctor;
    public JMenuItem menuItemMantenimientoDoctores;

    // MenuItems de Especialidades
    public JMenuItem menuItemRegistrarEspecialidad;
    public JMenuItem menuItemMantenimientoEspecialidades;

    // MenuItems de Historias Clínicas
    public JMenuItem menuItemRegistrarHistoriaClinica;
    public JMenuItem menuItemBusquedaMantenimientoHistorias;
    public JMenuItem menuItemAtencionesPorDia;
    public JMenuItem menuItemAtencionesPorEspecialidad;

    // MenuItems de Exportar
    public JMenuItem menuItemExportarHistoriaClinica;
}