package models;

public class SuccessEvent {
	private long timeCreate;
	private String url;
	private long guid;
	
	public SuccessEvent(long timeCreate, String url, long guid) {
		super();
		this.timeCreate = timeCreate;
		this.url = url;
		this.guid = guid;
	}

	public long getTimeCreate() {
		return timeCreate;
	}
	
	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public long getGuid() {
		return guid;
	}
	
	public void setGuid(long guid) {
		this.guid = guid;
	}
	
	@Override
	public String toString() {
		return guid + " " + timeCreate + " " + url;
	}
}