public class SupplierList
{
	private static SupplierList singleton;
	List<Supplier> suppliers;
	
	private SupplierList()
	{
	}
	
	public static SupplierList getInstance()
	{
		return singleton;
	}
	
	public Iterator GetSuppliers()
	{
		return suppliers.iterator();
	}
}