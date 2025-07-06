package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import ucv.codelab.service.ProcesosAtencionesDia;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmAtencionesDia;

public class AtencionesDiaController implements ActionListener {

    private FrmAtencionesDia view;

    public AtencionesDiaController(FrmAtencionesDia view) {
        this.view = view;

        ProcesosAtencionesDia.presentacion(view);

        view.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            clicBuscar();
        }
    }

    private void clicBuscar() {
        LocalDate fecha = ComprobarDatos.obtenerFecha(view.txtFecha.getText());
        if (fecha == null) {
            Mensajes.error("Error al buscar", "Verifique que la fecha esté en formado dd/mm/yyyy");
            return;
        }
        ProcesosAtencionesDia.actualizarTabla(view, fecha);
    }
}
