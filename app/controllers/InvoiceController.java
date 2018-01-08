package controllers;

import it.innove.play.pdf.PdfGenerator;
import com.typesafe.config.Config;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.invoice;


import javax.inject.Inject;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class InvoiceController extends Controller {

    @Inject
    public PdfGenerator pdfGenerator;

    @Inject
    public Config configuration;

   public Result download() {

       return pdfGenerator.ok(invoice.render("Hello world"), "api.hrwebshop.tk");
   }


}
