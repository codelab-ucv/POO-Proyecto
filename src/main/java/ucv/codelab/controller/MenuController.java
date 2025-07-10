package ucv.codelab.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ucv.codelab.view.FrmAtencionesDia;
import ucv.codelab.view.FrmAtencionesEspecialidad;
import ucv.codelab.view.FrmMantenimientoHistoria;
import ucv.codelab.view.FrmExportarHistoria;
import ucv.codelab.view.FrmMantenimientoMedico;
import ucv.codelab.view.FrmMantenimientoEspecialidad;
import ucv.codelab.view.FrmMantenimientoPaciente;
import ucv.codelab.view.FrmMenu;
import ucv.codelab.view.FrmRegistrarMedico;
import ucv.codelab.view.FrmRegistrarEspecialidad;
import ucv.codelab.view.FrmRegistroHistoriaC;
import ucv.codelab.view.FrmRegistrarPaciente;

public class MenuController implements ActionListener {

    private FrmMenu view;

    public MenuController(FrmMenu view) {
        this.view = view;
        configurarVentana();
        configurarMenuItems();
    }

    private void configurarVentana() {
        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setTitle("Posta Juan Pablo");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void configurarMenuItems() {
        // Event handlers para Pacientes
        view.menuItemRegistrarPaciente.addActionListener(this);
        view.menuItemMantenimientoPacientes.addActionListener(this);

        // Event handlers para Medicos
        view.menuItemRegistrarMedicos.addActionListener(this);
        view.menuItemMantenimientoMedicos.addActionListener(this);

        // Event handlers para Especialidades
        view.menuItemRegistrarEspecialidad.addActionListener(this);
        view.menuItemMantenimientoEspecialidades.addActionListener(this);

        // Event handlers para Historias Clínicas
        view.menuItemRegistrarHistoriaClinica.addActionListener(this);
        view.menuItemBusquedaMantenimientoHistorias.addActionListener(this);
        view.menuItemAtencionesPorDia.addActionListener(this);
        view.menuItemAtencionesPorEspecialidad.addActionListener(this);

        // Event handler para Exportar
        view.menuItemExportarHistoriaClinica.addActionListener(this);
    }

    private void mostrarPanel(JPanel panel) {
        view.panelEscritorio.removeAll();
        view.panelEscritorio.add(panel, BorderLayout.CENTER);
        view.panelEscritorio.revalidate();
        view.panelEscritorio.repaint();
        view.revalidate();
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Event handlers para Pacientes
        if (e.getSource() == view.menuItemRegistrarPaciente) {
            FrmRegistrarPaciente frm = new FrmRegistrarPaciente();
            new RegistrarPacienteController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemMantenimientoPacientes) {
            FrmMantenimientoPaciente frm = new FrmMantenimientoPaciente();
            new EditarPacienteController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Medicos
        else if (e.getSource() == view.menuItemRegistrarMedicos) {
            FrmRegistrarMedico frm = new FrmRegistrarMedico();
            new RegistrarMedicoController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemMantenimientoMedicos) {
            FrmMantenimientoMedico frm = new FrmMantenimientoMedico();
            new EditarMedicoController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Especialidades
        else if (e.getSource() == view.menuItemRegistrarEspecialidad) {
            FrmRegistrarEspecialidad frm = new FrmRegistrarEspecialidad();
            new RegistrarEspecialidadController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemMantenimientoEspecialidades) {
            FrmMantenimientoEspecialidad frm = new FrmMantenimientoEspecialidad();
            new EditarEspecialidadController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Historias Clínicas
        else if (e.getSource() == view.menuItemRegistrarHistoriaClinica) {
            FrmRegistroHistoriaC frm = new FrmRegistroHistoriaC();
            new RegistrarHistoriaController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemBusquedaMantenimientoHistorias) {
            FrmMantenimientoHistoria frm = new FrmMantenimientoHistoria();
            new EditarHistoriaController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemAtencionesPorDia) {
            FrmAtencionesDia frm = new FrmAtencionesDia();
            new AtencionesDiaController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemAtencionesPorEspecialidad) {
            FrmAtencionesEspecialidad frm = new FrmAtencionesEspecialidad();
            new AtencionesEspecialidadController(frm);
            mostrarPanel(frm);
        }

        // Event handler para Exportar
        else if (e.getSource() == view.menuItemExportarHistoriaClinica) {
            FrmExportarHistoria frm = new FrmExportarHistoria();
            new ExportarHistoriaController(frm);
            mostrarPanel(frm);
        }
    }
}
