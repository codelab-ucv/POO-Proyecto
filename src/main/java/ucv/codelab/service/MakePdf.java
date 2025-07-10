package ucv.codelab.service;

import java.io.File;
import java.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import ucv.codelab.model.HistoriaClinica;

public class MakePdf {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void make(File ubicacion, HistoriaClinica historiaClinica) throws IOException {
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
                .setTextAlignment(TextAlignment.CENTER).setFontSize(12);
        document.add(historiaNum);

        document.close();
        pdfDocument.close();
    }
}