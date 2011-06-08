package name.shamansir.mvplayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

public class PortalView extends Composite implements Pluggable {
	
	private final String alias;
	
	@UiConstructor
	public PortalView(final Portal portal) {
		this.alias = portal.name();
	}

	@Override
	public String id() {
		return alias;
	}

	@Override
	public void refresh() {
		// TODO:
	}

    @Override
    public Place getPlace() { return null; }

    @Override
    public void setPlace(Place place) { }

}
