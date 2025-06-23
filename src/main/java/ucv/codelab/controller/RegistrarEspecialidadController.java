package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import ucv.codelab.model.Especialidad;
import ucv.codelab.service.ProcesosRegistrarEspecialidad;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistrarEspecialidad;

public class RegistrarEspecialidadController implements ActionListener {

    private FrmRegistrarEspecialidad view;

    public RegistrarEspecialidadController(FrmRegistrarEspecialidad view) {
        this.view = view;

        /* No hay necesidad de hacer cambios en la interfaz al iniciar */

        view.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            Optional<Especialidad> especialidad = ProcesosRegistrarEspecialidad.validarDatos(view);
            // Verifica que la especialidad exista
            if (especialidad.isPresent()) {
                if (ProcesosRegistrarEspecialidad.guardarEspecialidad(view, especialidad.get())) {
                    ProcesosRegistrarEspecialidad.limpiarEntradas(view);
                }
            } else {
                Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
            }
        }
    }

}
