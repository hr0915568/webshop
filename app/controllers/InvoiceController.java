package controllers;

import it.innove.play.pdf.PdfGenerator;
import com.typesafe.config.Config;
import models.Invoice;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.InvoiceService;
import views.html.invoice;


import javax.inject.Inject;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class InvoiceController extends FEBasecontroller {

    @Inject
    public PdfGenerator pdfGenerator;

    @Inject
    public Config configuration;

   public Result download() {

       return pdfGenerator.ok(invoice.render("Hello world"), "api.hrwebshop.tk");
   }

   public Result getInvoices()
   {
       if(isLoggedIn() == false) {
           return badRequest("Not logged in");
       }

        List<Invoice> invoices = InvoiceService.getAllInvoicesOfUser(getSessionUser());

       return ok(Json.toJson(invoices));
   }



}
