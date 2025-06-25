package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.Medico;
import ucv.codelab.service.ProcesosEditarMedico;
import ucv.codelab.view.FrmMantenimientoMedico;

public class EditarMedicoController implements ActionListener {

    private FrmMantenimientoMedico view;

    private List<Medico> medicos;

    private Medico medicoEnEdicion;

    public EditarMedicoController(FrmMantenimientoMedico view) {
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
            clicBuscar();
        } else if (e.getSource() == view.btnEditar) {
            clicEditar();
        } else if (e.getSource() == view.btnActualizar) {
            clicActualizar();
            // Actualiza el resultado mostrado
            clicBuscar();
        } else if (e.getSource() == view.btnEliminar) {
            clicEliminar();
            // Actualiza el resultado mostrado
            clicBuscar();
        }
    }

    private void clicBuscar() {
        medicos = ProcesosEditarMedico.medicosFiltrados(view);
        ProcesosEditarMedico.presentacion(view, medicos);
    }

    private void clicEditar() {
        Optional<Medico> medicoEditar = ProcesosEditarMedico.seleccionarMedico(view);
        if (medicoEditar.isPresent()) {
            // Guarda la variable del medico en edicion
            medicoEnEdicion = medicoEditar.get();
            // Carga los datos segun el medico
            ProcesosEditarMedico.cargarDatosEdicion(view, medicoEnEdicion);
            // Habilita unicamente los campos editables y el boton de Actualizar
            ProcesosEditarMedico.habilitarCamposEditables(view);
        }
    }

    private void clicActualizar() {
        // Valida que el medico a actualizar no sea nulo
        if (medicoEnEdicion != null) {
            // LLama al metodo para guardar
            if (ProcesosEditarMedico.actualizarMedico(view, medicoEnEdicion)) {
                // Si tuvo exito lanza un mensaje de confirmacion
                JOptionPane.showMessageDialog(view, "Cambios guardados correctamente", "Cambios realizados",
                        JOptionPane.PLAIN_MESSAGE);
                // Elimina la cache de la especialidad en edicion
                medicoEnEdicion = null;
            } else {
                // Si ocurre un error al guardar retorna sin cambios
                return;
            }
        }
        // Deshabilita la edicion
        ProcesosEditarMedico.deshabilitarEdicion(view);
    }

    private void clicEliminar() {
        ProcesosEditarMedico.borrarMedico(view);
    }
}
