package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.service.ProcesosEditarHistoria;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmMantenimientoHistoria;

public class EditarHistoriaController implements ActionListener {

    private FrmMantenimientoHistoria view;

    private List<HistoriaClinica> historias;

    private HistoriaClinica historiaEnEdicion;

    public EditarHistoriaController(FrmMantenimientoHistoria view) {
        this.view = view;

        historias = new ArrayList<>();
        ProcesosEditarHistoria.presentacion(view, historias);

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
        } else if (e.getSource() == view.btnEliminar) {
            clicEliminar();
            // Actualiza el resultado mostrado
            clicBuscar();
        }
    }

    private void clicBuscar() {
        historias = ProcesosEditarHistoria.historiasFiltradas(view);
        ProcesosEditarHistoria.presentacion(view, historias);
    }

    private void clicEditar() {
        Optional<HistoriaClinica> historiaEditar = ProcesosEditarHistoria.seleccionarHistoria(view);
        if (historiaEditar.isPresent()) {
            // Guarda la variable de la historia en edicion
            historiaEnEdicion = historiaEditar.get();
            // Carga los datos segun la historia
            ProcesosEditarHistoria.cargarDatosEdicion(view, historiaEnEdicion);
            // Habilita unicamente los campos editables y el boton de Actualizar
            ProcesosEditarHistoria.habilitarCamposEditables(view);
        }
    }

    private void clicActualizar() {
        // Valida que la historia a actualizar no sea nulo
        if (historiaEnEdicion != null) {
            // LLama al metodo para guardar
            if (ProcesosEditarHistoria.actualizarHistoria(view, historiaEnEdicion)) {
                // Si tuvo exito lanza un mensaje de confirmacion
                JOptionPane.showMessageDialog(view, "Cambios guardados correctamente", "Cambios realizados",
                        JOptionPane.PLAIN_MESSAGE);
                // Elimina la cache de la historia en edicion
                historiaEnEdicion = null;
            } else {
                // Si ocurre un error al guardar retorna sin cambios
                Mensajes.error("Error al guardar", "Verifique que los campos esten correctamente ingresados");
                return;
            }
        }
        // Deshabilita la edicion
        ProcesosEditarHistoria.deshabilitarEdicion(view);
        // Actualiza el resultado mostrado
        clicBuscar();
    }

    private void clicEliminar() {
        ProcesosEditarHistoria.borrarHistoria(view);
    }
}