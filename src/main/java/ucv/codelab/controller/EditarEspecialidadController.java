package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import ucv.codelab.model.Especialidad;
import ucv.codelab.service.ProcesosEditarEspecialidad;
import ucv.codelab.view.FrmMantenimientoEspecialidad;

public class EditarEspecialidadController implements ActionListener {

    private FrmMantenimientoEspecialidad view;

    private List<Especialidad> especialidades;

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
                // TODO pendiente mostrar para editar
            }
        } else if (e.getSource() == view.btnActualizar) {
            ProcesosEditarEspecialidad.presentacion(view, especialidades);
        } else if (e.getSource() == view.btnEliminar) {
            ProcesosEditarEspecialidad.borrarEspecialidad(view);
            especialidades = ProcesosEditarEspecialidad.especialidadesFiltradas(view);
            ProcesosEditarEspecialidad.presentacion(view, especialidades);
        }
    }
}