package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Medico;
import ucv.codelab.model.Paciente;
import ucv.codelab.service.ProcesosRegistrarHistoria;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistroHistoriaC;

public class RegistrarHistoriaController implements ActionListener {

    private FrmRegistroHistoriaC view;
    private Medico medico;
    private Paciente paciente;

    public RegistrarHistoriaController(FrmRegistroHistoriaC view) {
        this.view = view;

        /* No hay necesidad de hacer cambios en la interfaz al iniciar */

        view.btnBuscarMedico.addActionListener(this);
        view.btnBuscarPaciente.addActionListener(this);
        view.btnGuardarHistoria.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscarMedico) {
            clicBuscarMedico();
        } else if (e.getSource() == view.btnBuscarPaciente) {
            clicBuscarPaciente();
        } else if (e.getSource() == view.btnGuardarHistoria) {
            clicGuardar();
        }
    }

    private void clicBuscarMedico() {
        Optional<Medico> buscarMedico = ProcesosRegistrarHistoria.buscarMedico(view);
        if (buscarMedico.isPresent()) {
            medico = buscarMedico.get();
            view.txtNombresMedico.setText(medico.getNombre());
            view.txtApellidosMedico.setText(medico.getApellido());

            JOptionPane.showMessageDialog(null, "Medico seleccionado con éxito");
        } else {
            Mensajes.error("Error de búsqueda", "Medico no encontrado.");
        }
    }

    private void clicBuscarPaciente() {
        Optional<Paciente> buscarPaciente = ProcesosRegistrarHistoria.buscarPaciente(view);
        if (buscarPaciente.isPresent()) {
            paciente = buscarPaciente.get();
            view.txtNombresPaciente.setText(paciente.getNombre());
            view.txtApellidosPaciente.setText(paciente.getApellido());

            JOptionPane.showMessageDialog(null, "Paciente seleccionado con éxito");
        } else {
            Mensajes.error("Error de búsqueda", "Paciente no encontrado.");
        }
    }

    private void clicGuardar() {
        if (paciente == null || medico == null) {
            Mensajes.error("Error al guardar", "Primero busca un paciente activo.");
            return;
        }

        Optional<HistoriaClinica> historia = ProcesosRegistrarHistoria.validarDatos(view, paciente, medico);
        // Verifica que la historia clínica exista
        if (historia.isPresent()) {
            if (ProcesosRegistrarHistoria.guardarHistoria(view, historia.get())) {
                // Al terminar limpia la seleccion actual
                paciente = null;
                medico = null;
                ProcesosRegistrarHistoria.limpiarEntradas(view);

                JOptionPane.showMessageDialog(null, "Historia clínica guardada con éxito");
            }
        } else {
            Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
        }

    }
}
