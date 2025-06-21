package ucv.codelab.controller;

import ucv.codelab.service.ProcesosMenu;
import ucv.codelab.view.FrmMenu;

public class MenuController {

    private FrmMenu view;

    public MenuController(FrmMenu view) {
        this.view = view;

        // Muestra la ventana
        ProcesosMenu.presentacion(view);
    }
}
