package ucv.codelab.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import ucv.codelab.model.Diagnostico;
import ucv.codelab.model.ExamenFisico;
import ucv.codelab.model.HistoriaClinica;
import ucv.codelab.model.Medico;
import ucv.codelab.model.Paciente;
import ucv.codelab.model.Tratamiento;

public class MakePdf {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void make(File ubicacion, HistoriaClinica historiaClinica, Paciente paciente, Medico medico)
            throws IOException {
        // Inicializar el escritor PDF
        PdfWriter pdfWriter = new PdfWriter(ubicacion);
        // Inicializar el documento PDF
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        // Inicializar el documento para agregar contenido
        Document document = new Document(pdfDocument);

        // Crear fuentes
        PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");
        PdfFont regularFont = PdfFontFactory.createFont("Helvetica");

        // Título principal
        Paragraph titulo = new Paragraph("POSTA JUAN PABLO")
                .setFont(boldFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(titulo);

        // Historia clínica número
        Paragraph historiaNum = new Paragraph("HISTORIA CLÍNICA Nº " + historiaClinica.getIdHistoria())
                .setFont(regularFont)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10);
        document.add(historiaNum);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy");
        // Fecha y hora
        Paragraph fechaHora = new Paragraph("FECHA Y HORA DE REGISTRO: " + dtf.format(historiaClinica.getFechaHora()))
                .setFont(regularFont)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10);
        document.add(fechaHora);

        // Datos del paciente
        document.add(new Paragraph("I. PACIENTE")
                .setFont(boldFont)
                .setFontSize(12));
        document.add(new Paragraph("NOMBRE Y APELLIDOS: " + paciente.getApellido() + ", " + paciente.getNombre())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph("FECHA DE NACIMIENTO: " + paciente.getFechaNacimiento()
                + " \t\t\t\t\tDNI: " + paciente.getDni())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph(" DIRECCIÓN: " + paciente.getDireccion()
                + " TELÉFONO: " + paciente.getTelefono())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));

        // Datos de la historia
        document.add(new Paragraph("II. DATOS DE LA CONSULTA")
                .setFont(boldFont)
                .setFontSize(12));
        document.add(new Paragraph("a. MOTIVO DE LA CONSULTA: " + historiaClinica.getMotivoConsulta())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph("b. ANTECEDENTES: " + historiaClinica.getAntecedentes())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph("c. TIEMPO DE ENFERMEDAD: " + historiaClinica.getTiempoEnfermedad())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));

        // TODO pendiente filtrar si es que no existe examen fisico

        // Examen fisico
        ExamenFisico examenFisico = historiaClinica.getExamenFisico();
        document.add(new Paragraph("III. EXAMEN FISICO")
                .setFont(boldFont)
                .setFontSize(12));
        document.add(new Paragraph("PESO (kg): " + examenFisico.getPeso()
                + " \t\t\t\t\tTALLA (cm): " + examenFisico.getTalla()
                + " \t\t\t\t\tTEMPERATURA: " + examenFisico.getTemperatura())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph("PRESION ARTERIAL: " + examenFisico.getPresionArterial()
                + " \t\t\tFRECUENCIA CARDIACA: " + examenFisico.getFrecuenciaCardiaca()
                + " \t\t\tFRECUENCIA RESPIRATORIA: " + examenFisico.getFrecuenciaRespiratoria())
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));

        // Diagnostico
        List<Diagnostico> diagnosticos = historiaClinica.getDiagnostico();
        document.add(new Paragraph("IV. DIAGNOSTICOS")
                .setFont(boldFont)
                .setFontSize(12));
        for (Diagnostico diagnostico : diagnosticos) {
            document.add(new Paragraph("Diagnostico " + diagnostico.getTipo()
                    + " \t\t\t\t\tCIE10: " + diagnostico.getCodigoCIE10())
                    .setFont(regularFont)
                    .setFontSize(10));
            document.add(new Paragraph("Descripcion:\n- " + diagnostico.getDescripcion())
                    .setFont(regularFont)
                    .setFontSize(10));
        }
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));

        // Tratamiento
        List<Tratamiento> tratamientos = historiaClinica.getTratamiento();
        document.add(new Paragraph("V. TRATAMIENTOS")
                .setFont(boldFont)
                .setFontSize(12));
        for (Tratamiento tratamiento : tratamientos) {
            document.add(new Paragraph("Descripcion:\n- " + tratamiento.getDescripcion())
                    .setFont(regularFont)
                    .setFontSize(10));
            document.add(new Paragraph("Indicaciones:\n- " + tratamiento.getIndicaciones())
                    .setFont(regularFont)
                    .setFontSize(10));
        }
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));

        // Medico
        document.add(new Paragraph(" ")
                .setFont(regularFont)
                .setFontSize(10));
        document.add(new Paragraph("VI. MEDICO")
                .setFont(boldFont)
                .setFontSize(12));
        document.add(new Paragraph("NOMBRE Y APELLIDOS: " + medico.getApellido() + ", " + medico.getNombre()
                + "\t\t\tNro COLEGIATURA: " + medico.getColegiatura())
                .setFont(regularFont)
                .setFontSize(10));

        document.close();
        pdfDocument.close();
    }
}