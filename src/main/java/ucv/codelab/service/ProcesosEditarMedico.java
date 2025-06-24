package ucv.codelab.service;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ucv.codelab.enumerados.GradoAcademico;
import ucv.codelab.enumerados.Sexo;
import ucv.codelab.model.Especialidad;
import ucv.codelab.model.Medico;
import ucv.codelab.repository.EspecialidadRepository;
import ucv.codelab.repository.MedicoRepository;
import ucv.codelab.util.Mensajes;
import ucv.codelab.util.MySQLConexion;
import ucv.codelab.view.FrmMantenimientoMedico;
import ucv.codelab.view.PanelBase;

public class ProcesosEditarMedico {

    private static String[] titulo = { "CODGIO", "ESPECIALIDAD", "NOMBRE", "APELLIDO", "DNI", "FECHA DE NACIMIENTO",
            "SEXO", "TELEFONO", "EMAIL", "COLEGIATURA", "GRADO ACADEMICO" };

    public static void presentacion(FrmMantenimientoMedico view, List<Medico> medicos) {
        cargarDatos(view, medicos);
        personalizarTabla(view);
        deshabilitarEdicion(view);
    }

    public static void deshabilitarEdicion(FrmMantenimientoMedico view) {
        view.txtEditarCodigo.setEnabled(false);
        view.txtEditarNombre.setEnabled(false);
        view.txtEditarApellidos.setEnabled(false);
        view.cmbEditarEspecialidad.setEnabled(false);
        view.txtEditarDni.setEnabled(false);
        view.cmbEditarSexo.setEnabled(false);
        view.txtEditarColegiatura.setEnabled(false);
        view.txtEditarTelefono.setEnabled(false);
        view.txtEditarFechaNacimiento.setEnabled(false);
        view.cmbEditarGradoAcademico.setEnabled(false);
        view.txtEditarCorreo.setEnabled(false);
        view.btnActualizar.setEnabled(false);
    }

    private static void cargarDatos(FrmMantenimientoMedico view, List<Medico> medicos) {
        // Crea el modelo con la edicion deshabilitada
        DefaultTableModel dtm = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.tblResultados.setModel(dtm);

        for (Medico medico : medicos) {
            dtm.addRow(medico.registro());
        }

        // Configura los ComboBox con enumerados
        view.cmbEditarSexo.removeAllItems();
        for (Sexo sexo : Sexo.values()) {
            view.cmbEditarSexo.addItem(sexo.getValor().toUpperCase());
        }
        view.cmbEditarSexo.setSelectedItem(null);
        view.cmbEditarGradoAcademico.removeAllItems();
        for (GradoAcademico gradoAcademico : GradoAcademico.values()) {
            view.cmbEditarGradoAcademico.addItem(gradoAcademico.getValor().toUpperCase());
        }
        view.cmbEditarGradoAcademico.setSelectedItem(null);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
            List<Especialidad> especialidades = especialidadRepository.buscarTodos();
            for (Especialidad especialidad : especialidades) {
                view.cmbEditarEspecialidad.addItem(especialidad.getEspecialidad());
            }
            view.cmbEditarEspecialidad.setSelectedItem(null);
        } catch (Exception e) {
            Mensajes.errorConexion();
        }
    }

    private static void personalizarTabla(FrmMantenimientoMedico view) {
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
        columnModel.getColumn(0).setPreferredWidth(80); // CODIGO
        columnModel.getColumn(1).setPreferredWidth(150); // ESPECIALIDAD
        columnModel.getColumn(2).setPreferredWidth(120); // NOMBRE
        columnModel.getColumn(3).setPreferredWidth(120); // APELLIDO
        columnModel.getColumn(4).setPreferredWidth(100); // DNI
        columnModel.getColumn(5).setPreferredWidth(140); // FECHA DE NACIMIENTO
        columnModel.getColumn(6).setPreferredWidth(80); // SEXO
        columnModel.getColumn(7).setPreferredWidth(110); // TELEFONO
        columnModel.getColumn(8).setPreferredWidth(200); // EMAIL
        columnModel.getColumn(9).setPreferredWidth(120); // COLEGIATURA
        columnModel.getColumn(10).setPreferredWidth(150); // GRADO ACADEMICO

        // Personalizar altura de filas
        view.tblResultados.setRowHeight(25);

        // Hacer que las columnas no se puedan reordenar
        header.setReorderingAllowed(false);
    }

    public static List<Medico> medicosActivos() {
        // Descarga los datos
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository medicoRepository = new MedicoRepository(conn);
            List<Medico> medicos = medicoRepository.buscarTodos();
            try {
                EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
                for (Medico medico : medicos) {
                    medico.setEspecialidad(especialidadRepository.buscarPorId(medico.getIdEspecialidad()).get());
                }
            } catch (Exception e) {
                /*
                 * En caso de que ocurra un error al obtener los nombres de las especialidades
                 * cancela la actualizacion
                 */
            }
            return medicos;
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static List<Medico> medicosFiltrados(FrmMantenimientoMedico view) {
        String dni = limpiarString(view.txtDni.getText());
        String nombre = limpiarString(view.txtNombre.getText());
        String apellido = limpiarString(view.txtApellido.getText());

        if (dni == null && nombre == null && apellido == null) {
            return medicosActivos();
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository medicoRepository = new MedicoRepository(conn);
            // Actualiza el nombre de especialidades
            List<Medico> medicos = medicoRepository.buscarFiltrado(dni, nombre, apellido);
            try {
                EspecialidadRepository especialidadRepository = new EspecialidadRepository(conn);
                for (Medico medico : medicos) {
                    medico.setEspecialidad(especialidadRepository.buscarPorId(medico.getIdEspecialidad()).get());
                }
            } catch (Exception e) {
                /*
                 * En caso de que ocurra un error al obtener los nombres de las especialidades
                 * cancela la actualizacion
                 */
            }
            return medicos;
        } catch (Exception e) {
            Mensajes.errorConexion();
            return new ArrayList<>();
        }
    }

    public static void borrarMedico(FrmMantenimientoMedico view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del medico a eliminar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return;
        }

        try {
            int idMedico = Integer.valueOf(input);
            try (Connection conn = MySQLConexion.getInstance().getConexion()) {
                MedicoRepository medicoRepository = new MedicoRepository(conn);
                medicoRepository.desactivar(idMedico);
            }
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
    }

    public static Optional<Medico> seleccionarMedico(FrmMantenimientoMedico view) {
        String input = JOptionPane.showInputDialog(view, "Ingrese el ID del medico a editar");
        input = limpiarString(input);

        // Si se cancela la eliminacion o esta vacio
        if (input == null) {
            return Optional.empty();
        }

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            int idBuscado = Integer.parseInt(input);

            MedicoRepository medicoRepository = new MedicoRepository(conn);
            return medicoRepository.buscarPorId(idBuscado);
        } catch (NumberFormatException e) {
            Mensajes.error("Valor inválido", "Ingrese un número válido");
        } catch (SQLException e) {
            Mensajes.errorConexion();
        }
        return Optional.empty();
    }

    private static String limpiarString(String parametro) {
        if (parametro != null) {
            parametro = parametro.trim();
            if (parametro.isEmpty()) {
                return null;
            }
        }
        return parametro;
    }

    // Se verifico previamente que contiene datos, no hace falta revalidar
    public static void cargarDatos(FrmMantenimientoMedico view, Medico medicoEnEdicion) {
        // Carga los datos en el formulario
        view.txtEditarCodigo.setText(medicoEnEdicion.getIdMedico() + "");
        view.txtEditarNombre.setText(medicoEnEdicion.getNombre());
        view.txtEditarApellidos.setText(medicoEnEdicion.getApellido());
        view.txtEditarDni.setText(medicoEnEdicion.getDni());
        view.txtEditarColegiatura.setText(medicoEnEdicion.getColegiatura());
        view.txtEditarTelefono.setText(medicoEnEdicion.getTelefono());
        view.txtEditarCorreo.setText(medicoEnEdicion.getEmail());

        // Inserta la fecha en el formato indicado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.txtEditarFechaNacimiento.setText(medicoEnEdicion.getFechaNacimiento().format(formatter));

        String especialidadStr = null;

        // Carga los datos ca
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository repo = new EspecialidadRepository(conn);
            especialidadStr = repo.buscarPorId(medicoEnEdicion.getIdEspecialidad()).get().getEspecialidad();
        } catch (Exception e) {
            /*
             * En caso de que ocurra un error al obtener los nombres de las especialidades
             * cancela el cambio
             */
        }

        // Coloca el valor de los ComboBox
        for (int i = 0; i < view.cmbEditarEspecialidad.getItemCount(); i++) {
            if (view.cmbEditarEspecialidad.getItemAt(i).equalsIgnoreCase(especialidadStr)) {
                view.cmbEditarEspecialidad.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < view.cmbEditarSexo.getItemCount(); i++) {
            if (view.cmbEditarSexo.getItemAt(i).equalsIgnoreCase(medicoEnEdicion.getSexo().getValor())) {
                view.cmbEditarSexo.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < view.cmbEditarGradoAcademico.getItemCount(); i++) {
            if (view.cmbEditarGradoAcademico.getItemAt(i)
                    .equalsIgnoreCase(medicoEnEdicion.getGradoAcademico().getValor())) {
                view.cmbEditarGradoAcademico.setSelectedIndex(i);
                break;
            }
        }
    }

    // Habilita campos editables
    public static void habilitarCamposEditables(FrmMantenimientoMedico view) {
        view.txtEditarNombre.setEnabled(true);
        view.txtEditarApellidos.setEnabled(true);
        view.cmbEditarEspecialidad.setEnabled(true);
        view.txtEditarDni.setEnabled(true);
        view.cmbEditarSexo.setEnabled(true);
        view.txtEditarColegiatura.setEnabled(true);
        view.txtEditarTelefono.setEnabled(true);
        view.txtEditarFechaNacimiento.setEnabled(true);
        view.cmbEditarGradoAcademico.setEnabled(true);
        view.txtEditarCorreo.setEnabled(true);
        view.btnActualizar.setEnabled(true);
    }

    public static boolean actualizarMedico(FrmMantenimientoMedico view,
            Medico medicoEnEdicion) {
        // Primero valida los campos obligatorios
        String strEspecialidad = limpiarString(view.cmbEditarEspecialidad.getSelectedItem().toString());
        String nombre = limpiarString(view.txtEditarNombre.getText());
        String apellido = limpiarString(view.txtEditarApellidos.getText());
        String dni = limpiarString(view.txtEditarDni.getText());
        String strFechaNacimiento = limpiarString(view.txtEditarFechaNacimiento.getText());
        String sexo = limpiarString(view.cmbEditarSexo.getSelectedItem().toString());
        String colegiatura = limpiarString(view.txtEditarColegiatura.getText());
        String gradoAcademico = limpiarString(view.cmbEditarGradoAcademico.getSelectedItem().toString());

        if (strEspecialidad == null || nombre == null || apellido == null || dni == null || strFechaNacimiento == null
                || sexo == null || colegiatura == null || gradoAcademico == null) {
            return false;
        }

        // Verifica que se pueda parsear la fecha
        LocalDate fechaNacimiento = obtenerFecha(strFechaNacimiento);
        if (fechaNacimiento == null) {
            return false;
        }

        // Verifica que se pueda obtener la especialidad
        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            EspecialidadRepository repo = new EspecialidadRepository(conn);
            Optional<Especialidad> especialidadEncontrada = repo.buscarPorNombre(strEspecialidad);
            // Si por alguna razon no encuentra la especialidad retorna
            if (especialidadEncontrada.isEmpty()) {
                return false;
            }
            medicoEnEdicion.setEspecialidad(especialidadEncontrada.get());
            medicoEnEdicion.setIdEspecialidad(especialidadEncontrada.get().getIdEspecialidad());
        } catch (Exception e) {
            Mensajes.errorConexion();
            return false;
        }

        // Si no hay problemas limpia los demas campos editables
        String telefono = limpiarString(view.txtEditarTelefono.getText());
        String correo = limpiarString(view.txtEditarCorreo.getText());

        // Actualiza los datos de la cache
        medicoEnEdicion.setNombre(nombre);
        medicoEnEdicion.setApellido(apellido);
        medicoEnEdicion.setDni(dni);
        medicoEnEdicion.setSexo(Sexo.fromString(sexo));
        medicoEnEdicion.setColegiatura(colegiatura);
        medicoEnEdicion.setGradoAcademico(GradoAcademico.fromString(gradoAcademico));
        medicoEnEdicion.setTelefono(telefono);
        medicoEnEdicion.setEmail(correo);

        try (Connection conn = MySQLConexion.getInstance().getConexion()) {
            MedicoRepository repo = new MedicoRepository(conn);
            repo.actualizar(medicoEnEdicion);
            return true;
        } catch (SQLException e) {
            Mensajes.errorConexion();
            return false;
        }
    }

    private static LocalDate obtenerFecha(String fechaString) {
        // Obtiene la fecha de un formato dd/mm/yyyy
        if (fechaString != null) {
            String[] fechaSeparada = fechaString.split("/");
            try {
                return LocalDate.of(Integer.valueOf(fechaSeparada[2]), Integer.valueOf(fechaSeparada[1]),
                        Integer.valueOf(fechaSeparada[0]));
            } catch (Exception e) {
            }
        }
        return null;
    }
}
