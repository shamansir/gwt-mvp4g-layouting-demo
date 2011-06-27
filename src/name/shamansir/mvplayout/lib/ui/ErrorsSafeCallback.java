package name.shamansir.mvplayout.lib.ui;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ErrorsSafeCallback<T> implements AsyncCallback<T> {
	
	private final ChildEventBus eventBus;
	
	public ErrorsSafeCallback(ChildEventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onFailure(Throwable caught) {
		eventBus.handle(caught);
	}

}
