package models;

public class MapperEvent {
	private long guid;
	private String listProducts;
	
	public MapperEvent(long guid, String listProducts) {
		this.guid = guid;
		this.listProducts = listProducts;
	}
	
	public MapperEvent(OrderEvent order, SuccessEvent success) {
		if(order.getGuid() == success.getGuid()) {
			this.guid = order.getGuid();
			this.listProducts = success.getUrl();
		}
	}
	
	public long getGuid() {
		return guid;
	}
	
	public void setGuid(long guid) {
		this.guid = guid;
	}
	
	public String getListProducts() {
		return listProducts;
	}
	
	public void setListProducts(String listProducts) {
		this.listProducts = listProducts;
	}
	
	@Override
	public String toString() {
		return "GUID : " + guid + ", PRODUCTS : " + listProducts;
	}
}
