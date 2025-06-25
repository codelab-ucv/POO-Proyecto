package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.Paciente;
import ucv.codelab.service.ProcesosEditarPaciente;
import ucv.codelab.view.FrmMantenimientoPaciente;

public class EditarPacienteController implements ActionListener {

    private FrmMantenimientoPaciente view;

    private List<Paciente> pacientes;

    private Paciente pacienteEnEdicion;

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
                // Guarda la variable del paciente en edicion
                pacienteEnEdicion = pacienteEditar.get();
                // Carga los datos segun el paciente
                ProcesosEditarPaciente.cargarDatosEdicion(view, pacienteEnEdicion);
                // Habilita unicamente los campos editables y el boton de Actualizar
                ProcesosEditarPaciente.habilitarCamposEditables(view);
            }
        } else if (e.getSource() == view.btnActualizar) {
            // Valida que el medico a actualizar no sea nulo
            if (pacienteEnEdicion != null) {
                // LLama al metodo para guardar
                if (ProcesosEditarPaciente.actualizarPaciente(view, pacienteEnEdicion)) {
                    // Si tuvo exito lanza un mensaje de confirmacion
                    JOptionPane.showMessageDialog(view, "Cambios guardados correctamente", "Cambios realizados",
                            JOptionPane.PLAIN_MESSAGE);
                    // Elimina la cache de la especialidad en edicion
                    pacienteEnEdicion = null;
                } else {
                    // Si ocurre un error al guardar retorna sin cambios
                    return;
                }
            }
            // Deshabilita la edicion
            ProcesosEditarPaciente.deshabilitarEdicion(view);

            // Actualiza el resultado mostrado
            pacientes = ProcesosEditarPaciente.pacientesFiltrados(view);
            ProcesosEditarPaciente.presentacion(view, pacientes);

        } else if (e.getSource() == view.btnEliminar) {
            ProcesosEditarPaciente.borrarPaciente(view);
            pacientes = ProcesosEditarPaciente.pacientesFiltrados(view);
            ProcesosEditarPaciente.presentacion(view, pacientes);
        }
    }
}
