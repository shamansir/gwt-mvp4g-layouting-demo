package name.shamansir.mvp4glayout.client.exception;

@SuppressWarnings("serial")
public class PortalNotFoundException extends Exception {
    
    public PortalNotFoundException(String spec) { super("Portal not found for spec " + spec); }	

}
