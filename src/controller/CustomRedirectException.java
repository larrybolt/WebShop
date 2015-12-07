package controller;

public class CustomRedirectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2149951515637465740L;
	/**
	 * 
	 */
	private String location;
	
	public CustomRedirectException(String location){
		super("redirect");
		setLocation(location);
	}

	public String getLocation() {
		return location;
	}

	private void setLocation(String location) {
		this.location = location;
	}
}