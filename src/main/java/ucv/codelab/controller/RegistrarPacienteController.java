package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import ucv.codelab.model.Paciente;
import ucv.codelab.service.ProcesosRegistrarPaciente;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistrarPaciente;

public class RegistrarPacienteController implements ActionListener {

    private FrmRegistrarPaciente view;

    public RegistrarPacienteController(FrmRegistrarPaciente view) {
        this.view = view;

        ProcesosRegistrarPaciente.presentacion(view);

        view.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            Optional<Paciente> paciente = ProcesosRegistrarPaciente.validarDatos(view);
            // Verifica que el paciente exista
            if (paciente.isPresent()) {
                if (ProcesosRegistrarPaciente.guardarPaciente(view, paciente.get())) {
                    ProcesosRegistrarPaciente.limpiarEntradas(view);
                }
            } else {
                Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
            }
        }
    }
}
