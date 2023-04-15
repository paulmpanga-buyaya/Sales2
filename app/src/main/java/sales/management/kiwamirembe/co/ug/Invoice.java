package sales.management.kiwamirembe.co.ug;

public class Invoice {

    public int invoice_id;
    public String invoice_number;
    public long invoice_date;
    public int invoice_total;
    public int invoice_discount;
    public int invoice_sync_status;

    public Invoice() {
    }

    public Invoice(int invoice_id, String invoice_number, long invoice_date, int invoice_total, int invoice_discount, int invoice_sync_status) {
        this.invoice_id = invoice_id;
        this.invoice_number = invoice_number;
        this.invoice_date = invoice_date;
        this.invoice_total = invoice_total;
        this.invoice_discount = invoice_discount;
        this.invoice_sync_status = invoice_sync_status;
    }

    public Invoice(String invoice_number, long invoice_date, int invoice_total, int invoice_discount, int invoice_sync_status) {
        this.invoice_number = invoice_number;
        this.invoice_date = invoice_date;
        this.invoice_total = invoice_total;
        this.invoice_discount = invoice_discount;
        this.invoice_sync_status = invoice_sync_status;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public long getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(long invoice_date) {
        this.invoice_date = invoice_date;
    }

    public int getInvoice_total() {
        return invoice_total;
    }

    public void setInvoice_total(int invoice_total) {
        this.invoice_total = invoice_total;
    }

    public int getInvoice_discount() {
        return invoice_discount;
    }

    public void setInvoice_discount(int invoice_discount) {
        this.invoice_discount = invoice_discount;
    }

    public int getInvoice_sync_status() {
        return invoice_sync_status;
    }

    public void setInvoice_sync_status(int invoice_sync_status) {
        this.invoice_sync_status = invoice_sync_status;
    }
}
