package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Medico;
import ucv.codelab.model.Paciente;
import ucv.codelab.repository.HistoriaClinicaRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.repository.MySQLConexion;
import ucv.codelab.repository.PacienteRepository;
import ucv.codelab.util.ComprobarDatos;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmAtencionesDia;
import ucv.codelab.view.PanelBase;

public class ProcesosAtencionesDia {

    private static String[] titulo = { "ID ATENCION", "PACIENTE", "MEDICO", "FECHA Y HORA DE ATENCION" };
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public static void presentacion(FrmAtencionesDia view) {
        cargarDatos(view, new ArrayList<>());
        personalizarTabla(view);
    }

    private static void personalizarTabla(FrmAtencionesDia view) {
        // Personalizar cabecera
        JTableHeader header = view.tblResultados.getTableHeader();
        header.setBackground(Color.BLUE);
        header.setForeground(PanelBase.TEXT_COLOR);
        header.setFont(new Font("DialogInput", Font.BOLD, 12));

        // Personalizar contenido de la tabla
        view.tblResultados.setBackground(Color.WHITE);
        view.tblResultados.setForeground(PanelBase.TEXT_COLOR);
        view.tblResultados.setFont(new Font("Dialog", Font.PLAIN, 11));

        // Personalizar tamaños de columnas
        TableColumnModel columnModel = view.tblResultados.getColumnModel();

        // Ajustar ancho de columnas específicas
        columnModel.getColumn(0).setPreferredWidth(10); // ID ATENCION
        columnModel.getColumn(1).setPreferredWidth(300); // PACIENTE
        columnModel.getColumn(2).setPreferredWidth(300); // MEDICO
        columnModel.getColumn(3).setPreferredWidth(30); // HORA DE ATENCION

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    private static void cargarDatos(FrmAtencionesDia view, List<Object[]> datos) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Object[] dato : datos) {
            dtm.addRow(dato);
        }
    }

    public static void actualizarTabla(FrmAtencionesDia view, LocalDate fecha) {
        String dni = ComprobarDatos.limpiarString(view.txtDni.getText());
        String nombre = ComprobarDatos.limpiarString(view.txtNombre.getText());
        String apellido = ComprobarDatos.limpiarString(view.txtApellido.getText());

        if (dni == null && nombre == null && apellido == null) {
            Mensajes.error("Error de busqueda", "Debe especificarse por lo menos un filtro");
            return;
        }

        List<Object[]> resultados = new ArrayList<>();

        try (Connection conn = new MySQLConexion().getConexion()) {
            // Busca el medico
            Medico medico = buscarMedico(dni, nombre, apellido, conn);
            if (medico == null) {
                // Limpia los resultados si no se encuentra médico
                cargarDatos(view, resultados);
                return;
            }

            // Busca las historias del medico en la fecha indicada
            List<HistoriaClinica> historias = buscarHistorias(medico, fecha, conn);

            PacienteRepository pr = new PacienteRepository(conn);
            for (HistoriaClinica historiaClinica : historias) {

                Optional<Paciente> paciente = pr.buscarPorId(historiaClinica.getIdPaciente());

                resultados.add(new Object[] {
                        historiaClinica.getIdHistoria(),
                        paciente.isPresent() ? paciente.get().getApellido() + ", " + paciente.get().getNombre()
                                : historiaClinica.getIdPaciente(),
                        medico.getApellido() + ", " + medico.getNombre(),
                        formatter.format(historiaClinica.getFechaHora()) });
            }
        } catch (Exception e) {
            Mensajes.errorConexion();
        }
        // Actualiza los resultados
        cargarDatos(view, resultados);
    }

    private static List<HistoriaClinica> buscarHistorias(Medico medico, LocalDate fecha, Connection conn) {
        HistoriaClinicaRepository hcr = new HistoriaClinicaRepository(conn);
        return hcr.buscarPorMedicoYFecha(medico.getIdMedico(), fecha);
    }

    private static Medico buscarMedico(String dni, String nombre, String apellido, Connection conn) {
        MedicoRepository mr = new MedicoRepository(conn);
        List<Medico> medico = mr.buscarFiltrado(dni, nombre, apellido);

        // Si hay muchas coincidencias
        if (medico.size() > 1) {
            Mensajes.error("Error de busqueda", "Múltiples médicos encontrados, se recomienda filtrar por DNI");
            return null;
        }
        // Si no hay coincidencias
        if (medico.size() < 1) {
            Mensajes.error("Error de busqueda", "No se encontró el médico indicado, verifique los datos");
            return null;
        }
        // Devuelve el medico encontrado
        return medico.get(0);
    }
}
