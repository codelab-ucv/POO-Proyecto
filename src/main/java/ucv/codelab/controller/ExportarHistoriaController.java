package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ucv.codelab.view.FrmExportarHistoria;

public class ExportarHistoriaController implements ActionListener {

    private FrmExportarHistoria view;

    public ExportarHistoriaController(FrmExportarHistoria view) {
        this.view = view;

        view.btnBuscar.addActionListener(this);
        view.btnExportarPdf.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscar) {

        } else if (e.getSource() == view.btnExportarPdf) {

        }
    }
}
