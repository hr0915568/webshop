package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvoiceRow {

    @Id
    public Long id;

    @Column(name = "invoice_id")
    public int invoiceId;


    public String description11;



}