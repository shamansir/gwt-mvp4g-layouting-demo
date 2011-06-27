package name.shamansir.mvplayout.lib.exception;

@SuppressWarnings("serial")
public class PortalNotFoundException extends ItemNotFoundException {
	
	public PortalNotFoundException(String spec) { super("Portal not found for spec " + spec); }	

}