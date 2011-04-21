package name.shamansir.mvplayout.client.ui.layout.base;

import java.util.Map;

import name.shamansir.mvplayout.client.ui.layout.base.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.Place;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class Layout {
	
	private final LayoutId id;
	
	protected Layout(LayoutId id) {
		this.id = id;
	}

	public LayoutId id() { return id; }
	public abstract HasWidgets place(Place place);
	public abstract Map<Place, HasWidgets> places();
	
	public static enum State { NO_DATA, HAS_DATA, LOADING_DATA, NO_MATCHES };
	
}
