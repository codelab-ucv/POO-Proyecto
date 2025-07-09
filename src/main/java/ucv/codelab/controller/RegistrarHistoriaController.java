package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Paciente;
import ucv.codelab.service.ProcesosRegistrarHistoria;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistroHistoriaC;

public class RegistrarHistoriaController implements ActionListener {

    private FrmRegistroHistoriaC view;
    private Paciente paciente;

    public RegistrarHistoriaController(FrmRegistroHistoriaC view) {
        this.view = view;

        /* No hay necesidad de hacer cambios en la interfaz al iniciar */

        view.btnBuscarPaciente.addActionListener(this);
        view.btnGuardarHistoria.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscarPaciente) {
            clicBuscarPaciente();
        } else if (e.getSource() == view.btnGuardarHistoria) {
            clicGuardar();
        }
    }

    private void clicGuardar() {
        if (paciente == null) {
            Mensajes.error("Error al guardar", "Primero busca un paciente activo.");
            return;
        }

        Optional<HistoriaClinica> historia = ProcesosRegistrarHistoria.validarDatos(view);
        // Verifica que la historia clínica exista
        if (historia.isPresent()) {
            if (ProcesosRegistrarHistoria.guardarHistoria(view, historia.get())) {
                // Al terminar limpia la seleccion actual
                paciente = null;
                ProcesosRegistrarHistoria.limpiarEntradas(view);

                JOptionPane.showMessageDialog(null, "Historia clínica guardada con éxito");
            }
        } else {
            Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
        }

    }

    private void clicBuscarPaciente() {
        Optional<Paciente> buscarPaciente = ProcesosRegistrarHistoria.buscarPaciente(view);
        if (buscarPaciente.isPresent()) {
            paciente = buscarPaciente.get();
            JOptionPane.showMessageDialog(null, "Paciente seleccionado con éxito");
        } else {
            Mensajes.error("Error de búsqueda", "Paciente no encontrado.");
        }
    }
}
