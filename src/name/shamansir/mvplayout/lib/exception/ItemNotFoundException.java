package name.shamansir.mvplayout.lib.exception;

@SuppressWarnings("serial")
public class ItemNotFoundException extends Exception {
	
	public ItemNotFoundException() { super(); }
	public ItemNotFoundException(String description) { super(description); }
	public ItemNotFoundException(int position) { super("Item at position " + position + " was not found"); }

}
