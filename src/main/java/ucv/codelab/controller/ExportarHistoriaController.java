package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.service.ProcesosExportarHistoria;
import ucv.codelab.view.FrmExportarHistoria;

public class ExportarHistoriaController implements ActionListener {

    private FrmExportarHistoria view;

    private List<HistoriaClinica> historias;

    public ExportarHistoriaController(FrmExportarHistoria view) {
        this.view = view;

        historias = new ArrayList<>();
        ProcesosExportarHistoria.presentacion(view, historias);

        view.btnBuscar.addActionListener(this);
        view.btnExportarPdf.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {
            clicBuscar();
        } else if (e.getSource() == view.btnExportarPdf) {
            clicExportar();
        }
    }

    private void clicBuscar() {
        historias = ProcesosExportarHistoria.historiasFiltradas(view);
        ProcesosExportarHistoria.presentacion(view, historias);
    }

    private void clicExportar() {
        HistoriaClinica historiaExportar = ProcesosExportarHistoria.descargarHistoria(view);
        if (historiaExportar != null) {
            if(ProcesosExportarHistoria.exportar(historiaExportar)){
                JOptionPane.showMessageDialog(null, "PDF exportado con exito a la carpeta Documents");
            }
        }
    }
}
