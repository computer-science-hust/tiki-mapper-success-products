package models;

public class OrderEvent {
	private long timeCreate;
	private long guid;
	
	public OrderEvent(long timeCreate, long guid) {
		super();
		this.timeCreate = timeCreate;
		this.guid = guid;
	}

	public long getTimeCreate() {
		return timeCreate;
	}
	
	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}
	
	public long getGuid() {
		return guid;
	}
	
	public void setGuid(long guid) {
		this.guid = guid;
	}
	
	@Override
	public String toString() {
		return "GUID : " + guid + ", TIME : " + timeCreate; 
	}
}
