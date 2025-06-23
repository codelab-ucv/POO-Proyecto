package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Paciente;
import ucv.codelab.service.ProcesosEditarPaciente;
import ucv.codelab.view.FrmMantenimientoPaciente;

public class EditarPacienteController implements ActionListener {

    private FrmMantenimientoPaciente view;

    private List<Paciente> pacientes;

    public EditarPacienteController(FrmMantenimientoPaciente view) {
        this.view = view;

        pacientes = ProcesosEditarPaciente.pacientesActivos();

        ProcesosEditarPaciente.presentacion(view, pacientes);

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
            pacientes = ProcesosEditarPaciente.pacientesFiltrados(view);
            ProcesosEditarPaciente.presentacion(view, pacientes);
        } else if (e.getSource() == view.btnEditar) {
            Optional<Paciente> pacienteEditar = ProcesosEditarPaciente.seleccionarPaciente(view);
            if (pacienteEditar.isPresent()) {
                // TODO pendiente mostrar para editar
            }
        } else if (e.getSource() == view.btnActualizar) {
            ProcesosEditarPaciente.presentacion(view, pacientes);
        } else if (e.getSource() == view.btnEliminar) {
            ProcesosEditarPaciente.borrarPaciente(view);
            pacientes = ProcesosEditarPaciente.pacientesFiltrados(view);
            ProcesosEditarPaciente.presentacion(view, pacientes);
        }
    }
}
