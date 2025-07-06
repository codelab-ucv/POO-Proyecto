package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.Medico;
import ucv.codelab.service.ProcesosRegistrarMedico;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmRegistrarMedico;

public class RegistrarMedicoController implements ActionListener {

    private FrmRegistrarMedico view;

    public RegistrarMedicoController(FrmRegistrarMedico view) {
        this.view = view;

        ProcesosRegistrarMedico.presentacion(view);

        view.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            Optional<Medico> medico = ProcesosRegistrarMedico.validarDatos(view);
            // Verifica que el medico exista
            if (medico.isPresent()) {
                if (ProcesosRegistrarMedico.guardarMedico(view, medico.get())) {
                    ProcesosRegistrarMedico.limpiarEntradas(view);
                    JOptionPane.showMessageDialog(null, "Medico guardado con exito");
                }
            } else {
                Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
            }
        }
    }
}
