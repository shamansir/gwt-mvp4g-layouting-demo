package name.shamansir.mvplayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;

import name.shamansir.mvplayout.client.ui.Pluggable;

public class PortalView extends Composite implements Pluggable {
	
	private final String alias;
	
	@UiConstructor
	public PortalView(final String alias) {
		this.alias = alias;
	}

	@Override
	public String id() {
		return alias;
	}

	@Override
	public void refresh() {
		// TODO:
	}

}
