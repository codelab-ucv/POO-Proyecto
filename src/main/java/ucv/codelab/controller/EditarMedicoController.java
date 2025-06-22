package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Medico;
import ucv.codelab.service.ProcesosEditarMedico;
import ucv.codelab.view.FrmMantenimientoDoc;

public class EditarMedicoController implements ActionListener {

    private FrmMantenimientoDoc view;

    private List<Medico> medicos;

    public EditarMedicoController(FrmMantenimientoDoc view) {
        this.view = view;

        medicos = ProcesosEditarMedico.medicosActivos();

        ProcesosEditarMedico.presentacion(view, medicos);

        configurarBotones();
    }

    private void configurarBotones() {
        view.btnBuscar.addActionListener(this);
        view.btnEditar.addActionListener(this);
        view.btnActualizar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            medicos = ProcesosEditarMedico.medicosFiltrados(view);
            ProcesosEditarMedico.presentacion(view, medicos);
        } else if (e.getSource() == view.btnEditar) {
            Optional<Medico> medicoEditar = ProcesosEditarMedico.seleccionarMedico(view);
            if (medicoEditar.isPresent()) {
                // TODO pendiente mostrar para editar
            }
        } else if (e.getSource() == view.btnActualizar) {
            ProcesosEditarMedico.presentacion(view, medicos);
        } else if (e.getSource() == view.btnEliminar) {
            ProcesosEditarMedico.borrarPaciente(view);
            medicos = ProcesosEditarMedico.pacientesFiltrados(view);
            ProcesosEditarMedico.presentacion(view, medicos);
        }
    }
}
