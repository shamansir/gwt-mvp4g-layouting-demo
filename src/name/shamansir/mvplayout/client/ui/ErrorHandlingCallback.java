package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ErrorHandlingCallback<T> implements AsyncCallback<T> {
	
	private final ChildEventBus eventBus;
	
	public ErrorHandlingCallback(ChildEventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onFailure(Throwable caught) {
		eventBus.handle(caught);
	}

}
