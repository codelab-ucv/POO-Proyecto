package ucv.codelab.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ucv.codelab.view.FrmAtencionesDia;
import ucv.codelab.view.FrmAtencionesEsp;
import ucv.codelab.view.FrmBusquedaMante;
import ucv.codelab.view.FrmExportarHisC;
import ucv.codelab.view.FrmMantenimientoDoc;
import ucv.codelab.view.FrmMantenimientoEspe;
import ucv.codelab.view.FrmMantenimientoPa;
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

        // Event handlers para Doctores
        view.menuItemRegistrarDoctor.addActionListener(this);
        view.menuItemMantenimientoDoctores.addActionListener(this);

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
            FrmMantenimientoPa frm = new FrmMantenimientoPa();
            new EditarPacienteController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Doctores
        else if (e.getSource() == view.menuItemRegistrarDoctor) {
            FrmRegistrarMedico frm = new FrmRegistrarMedico();
            new RegistrarMedicoController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemMantenimientoDoctores) {
            FrmMantenimientoDoc frm = new FrmMantenimientoDoc();
            new EditarMedicoController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Especialidades
        else if (e.getSource() == view.menuItemRegistrarEspecialidad) {
            FrmRegistrarEspecialidad frm = new FrmRegistrarEspecialidad();
            new RegistrarEspecialidadController(frm);
            mostrarPanel(frm);
        } else if (e.getSource() == view.menuItemMantenimientoEspecialidades) {
            FrmMantenimientoEspe frm = new FrmMantenimientoEspe();
            new EditarEspecialidadController(frm);
            mostrarPanel(frm);
        }

        // Event handlers para Historias Clínicas
        else if (e.getSource() == view.menuItemRegistrarHistoriaClinica) {
            mostrarPanel(new FrmRegistroHistoriaC());
        } else if (e.getSource() == view.menuItemBusquedaMantenimientoHistorias) {
            mostrarPanel(new FrmBusquedaMante());
        } else if (e.getSource() == view.menuItemAtencionesPorDia) {
            mostrarPanel(new FrmAtencionesDia());
        } else if (e.getSource() == view.menuItemAtencionesPorEspecialidad) {
            mostrarPanel(new FrmAtencionesEsp());
        }

        // Event handler para Exportar
        else if (e.getSource() == view.menuItemExportarHistoriaClinica) {
            mostrarPanel(new FrmExportarHisC());
        }
    }
}
