package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import play.mvc.Controller;


import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class InvoiceController extends Controller {

    public static Document invoice() {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("HelloWorld.pdf"));

            document.open();
            document.add(new Paragraph("A Hello World PDF document."));
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return document;

    }

    public static void main(String[] args) {
        invoice();
    }
}
