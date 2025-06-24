package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.Especialidad;
import ucv.codelab.service.ProcesosEditarEspecialidad;
import ucv.codelab.view.FrmMantenimientoEspecialidad;

public class EditarEspecialidadController implements ActionListener {

    private FrmMantenimientoEspecialidad view;

    private List<Especialidad> especialidades;

    private Especialidad especialidadEnEdicion;

    public EditarEspecialidadController(FrmMantenimientoEspecialidad view) {
        this.view = view;

        especialidades = ProcesosEditarEspecialidad.especialidadesActivas();

        ProcesosEditarEspecialidad.presentacion(view, especialidades);

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
            especialidades = ProcesosEditarEspecialidad.especialidadesFiltradas(view);
            ProcesosEditarEspecialidad.presentacion(view, especialidades);
        } else if (e.getSource() == view.btnEditar) {
            Optional<Especialidad> especialidadEditar = ProcesosEditarEspecialidad.seleccionarEspecialidad(view);
            if (especialidadEditar.isPresent()) {
                // Guarda la variable de la especialidad en edicion
                especialidadEnEdicion = especialidadEditar.get();
                // Carga los datos segun la especialidad
                ProcesosEditarEspecialidad.cargarDatos(view, especialidadEnEdicion);
                // Habilita unicamente los campos editables y el boton de Actualizar
                ProcesosEditarEspecialidad.habilitarCamposEditables(view);
            }
        } else if (e.getSource() == view.btnActualizar) {
            // Valida que la especialidad a actualizar no sea nula
            if (especialidadEnEdicion != null) {
                // LLama al metodo para guardar
                if (ProcesosEditarEspecialidad.actualizarEspecialidad(view, especialidadEnEdicion)) {
                    // Si tuvo exito lanza un mensaje de confirmacion
                    JOptionPane.showMessageDialog(view, "Cambios guardados correctamente", "Cambios realizados",
                            JOptionPane.PLAIN_MESSAGE);
                    // Elimina la cache de la especialidad en edicion
                    especialidadEnEdicion = null;
                } else {
                    // Si ocurre un error al guardar retorna sin cambios
                    return;
                }
            }
            // Deshabilita la edicion
            ProcesosEditarEspecialidad.deshabilitarEdicion(view);

            // Actualiza el resultado mostrado
            especialidades = ProcesosEditarEspecialidad.especialidadesFiltradas(view);
            ProcesosEditarEspecialidad.presentacion(view, especialidades);
        } else if (e.getSource() == view.btnEliminar) {
            ProcesosEditarEspecialidad.borrarEspecialidad(view);
            especialidades = ProcesosEditarEspecialidad.especialidadesFiltradas(view);
            ProcesosEditarEspecialidad.presentacion(view, especialidades);
        }
    }
}