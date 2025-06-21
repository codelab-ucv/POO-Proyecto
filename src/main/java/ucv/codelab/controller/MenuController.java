package ucv.codelab.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ucv.codelab.service.ProcesosMenu;
import ucv.codelab.view.FrmAtencionesDia;
import ucv.codelab.view.FrmAtencionesEsp;
import ucv.codelab.view.FrmBusquedaMante;
import ucv.codelab.view.FrmExportarHisC;
import ucv.codelab.view.FrmMantenimientoDoc;
import ucv.codelab.view.FrmMantenimientoEspe;
import ucv.codelab.view.FrmMantenimientoPa;
import ucv.codelab.view.FrmMenu;
import ucv.codelab.view.FrmRegistroDoc;
import ucv.codelab.view.FrmRegistroEsp;
import ucv.codelab.view.FrmRegistroHistoriaC;
import ucv.codelab.view.FrmRegistroPa;

public class MenuController {

    private FrmMenu view;

    public MenuController(FrmMenu view) {
        this.view = view;

        // Muestra la ventana
        ProcesosMenu.presentacion(view);
        
        // Configura los  Menu Items
        clicMenuItems();
    }

    private void clicMenuItems() {
        // Event handlers para Pacientes
        view.menuItemRegistrarPaciente.addActionListener(mostrarPanel(new FrmRegistroPa()));
        view.menuItemMantenimientoPacientes.addActionListener(mostrarPanel(new FrmMantenimientoPa()));

        // Event handlers para Doctores
        view.menuItemRegistrarDoctor.addActionListener(mostrarPanel(new FrmRegistroDoc()));
        view.menuItemMantenimientoDoctores.addActionListener(mostrarPanel(new FrmMantenimientoDoc()));

        // Event handlers para Especialidades
        view.menuItemRegistrarEspecialidad.addActionListener(mostrarPanel(new FrmRegistroEsp()));
        view.menuItemMantenimientoEspecialidades.addActionListener(mostrarPanel(new FrmMantenimientoEspe()));

        // Event handlers para Historias Clínicas
        view.menuItemRegistrarHistoriaClinica.addActionListener(mostrarPanel(new FrmRegistroHistoriaC()));
        view.menuItemBusquedaMantenimientoHistorias.addActionListener(mostrarPanel(new FrmBusquedaMante()));
        view.menuItemAtencionesPorDia.addActionListener(mostrarPanel(new FrmAtencionesDia()));
        view.menuItemAtencionesPorEspecialidad.addActionListener(mostrarPanel(new FrmAtencionesEsp()));

        // Event handler para Exportar
        view.menuItemExportarHistoriaClinica.addActionListener(mostrarPanel(new FrmExportarHisC()));
    }

    private ActionListener mostrarPanel(JPanel panel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpia el panel
                view.panelEscritorio.removeAll();
                view.panelEscritorio.add(panel, BorderLayout.CENTER);
                view.panelEscritorio.revalidate();
                view.panelEscritorio.repaint();
                view.revalidate();
                view.repaint();
            }
        };
    }
}
