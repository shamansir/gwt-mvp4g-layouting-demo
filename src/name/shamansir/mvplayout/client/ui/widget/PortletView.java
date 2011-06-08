package name.shamansir.mvplayout.client.ui.widget;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;

public class PortletView extends FlowPanel implements Pluggable {
	
	private final String alias;
	private Place place;
	
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

    @Override
    public Place getPlace() { return place; }

    @Override
    public void setPlace(Place place) {
        this.place = place;        
    }

}
