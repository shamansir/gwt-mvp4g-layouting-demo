package name.shamansir.mvplayout.lib.utils;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SafeCallback<T> implements AsyncCallback<T> {
    
    private final ChildEventBus eventBus;
    
    public SafeCallback(ChildEventBus eventBus) {
    	this.eventBus = eventBus;
    }

    @Override
    public void onFailure(Throwable caught) {
    	eventBus.handle(caught);
    }

}
