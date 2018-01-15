package controllers;

import it.innove.play.pdf.PdfGenerator;
import com.typesafe.config.Config;
import models.Invoice;
import models.Order;
import models.OrderProduct;
import models.Product;
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

   public Result download(Long id) {

       Invoice invoicetoget = InvoiceService.getInvoice(id);

//       List<OrderProduct> data = invoicetoget.getOrdermodel().getOrderProducts();
//       for (OrderProduct item : data){
//            item.getOrderedproduct().getProductname();
//            item.getPriceAtOrdertime();
//       }


       return pdfGenerator.ok(invoice.render("Hello world", invoicetoget), "api.hrwebshop.tk");
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
