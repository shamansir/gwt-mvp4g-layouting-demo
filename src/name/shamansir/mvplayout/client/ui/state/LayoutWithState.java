package name.shamansir.mvplayout.client.ui.state;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.Layouts;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

public abstract class LayoutWithState extends Layout {
	
	public static enum State { NO_DATA, HAS_DATA, LOADING_DATA, NO_MATCHES };	

	protected LayoutWithState(LayoutId id, Place[] places) {
		super(id, places);
	}
	
	@Override
	protected void ensurePanelsLoaded() {
		super.ensurePanelsLoaded();
		
		if (!has(Place.STATUS)) throw new IllegalStateException("Layout with state must contain STATUS place");
	}
	
	public HasWidgets emptyHolder() { return panel(Place.STATUS); }
	
	public HasWidgets loadingHolder() { return panel(Place.STATUS); }
	
	public HasWidgets noMatchesHolder() { return panel(Place.STATUS); }
	
	public abstract void prepare(State state);	

}
