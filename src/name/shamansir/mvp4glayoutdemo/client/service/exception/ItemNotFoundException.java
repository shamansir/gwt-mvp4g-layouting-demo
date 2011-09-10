package name.shamansir.mvp4glayoutdemo.client.service.exception;

@SuppressWarnings("serial")
public class ItemNotFoundException extends Exception {
    
    public ItemNotFoundException() { super(); }
    public ItemNotFoundException(String description) { super(description); }
    public ItemNotFoundException(int position) { super("No such item at position " + position); }

}
