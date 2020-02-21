//Barrett
//InvoiceList.java
//import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InvoiceList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Invoice> invoices = new LinkedList<Invoice>();
	private static InvoiceList invoicelist;
	
	private InvoiceList() {};
	
	public static InvoiceList instance()
	{
		if (invoicelist == null)
			return (invoicelist = new InvoiceList());
		else
			return invoicelist;
	}
	
	public boolean addInvoice(Invoice invoice)
	{
		invoices.add(invoice);
		return true;
	}
	 
	public Iterator<Invoice> getinvoices()
	{
		return invoices.iterator();
	}
	
	/*
	public Invoice searchInvoice(String invoiceID)
	{
		Iterator<Invoice> invoiceIterator = invoices.iterator();

		while (invoiceIterator.hasNext())
		{
			Invoice invoice = (Invoice)(invoiceIterator.next());
			if (invoice.getInvoiceID().equals(invoiceID))
			{
				return invoice;
			}
		}

		return null;
	}
	*/
	
	public String toString()
	{
		return invoices.toString();
	}
	
	
}
