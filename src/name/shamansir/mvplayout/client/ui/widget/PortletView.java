package name.shamansir.mvplayout.client.ui.widget;

import name.shamansir.mvplayout.client.ui.Pluggable;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;

public class PortletView extends FlowPanel implements Pluggable {
	
	private final String alias;
	
	@UiConstructor
	public PortletView(final String alias) {
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
