package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import ucv.codelab.service.ProcesosAtencionesEspecialidad;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmAtencionesEspecialidad;

public class AtencionesEspecialidadController implements ActionListener {

    private FrmAtencionesEspecialidad view;

    public AtencionesEspecialidadController(FrmAtencionesEspecialidad view) {
        this.view = view;

        ProcesosAtencionesEspecialidad.presentacion(view);

        view.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            clicBuscar();
        }
    }

    private void clicBuscar() {
        LocalDate fechaInicio = ComprobarDatos.obtenerFecha(view.txtFechaInicio.getText());
        LocalDate fechaFin = ComprobarDatos.obtenerFecha(view.txtFechaFin.getText());
        if (fechaInicio == null || fechaFin == null) {
            Mensajes.error("Error al buscar", "Verifique que las fechas estén en formato dd/mm/yyyy");
            return;
        }
        // Si la fecha de inicio es mayor que la fecha de fin
        if (fechaInicio.isAfter(fechaFin)) {
            Mensajes.error("Error al buscar", "La fecha de inicio no puede ser mayor que la fecha de fin.");
            return;
        }
        ProcesosAtencionesEspecialidad.actualizarTabla(view, fechaInicio, fechaFin);
    }
}
