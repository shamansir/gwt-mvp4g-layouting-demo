package name.shamansir.mvplayout.client.ui.layout.base;

import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.layout.base.Layout.State;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.Place;
import name.shamansir.mvplayout.pages.base.ChildEventBus;

public abstract class LayoutBuilder<E extends ChildEventBus> {
	
	protected abstract boolean layout(Portal view, Layout layout, State state, Map<Place, HasWidgets> panels, E eventBus);

}
