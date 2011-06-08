package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.widget.Layout;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class LayoutWithState extends Layout {
	
	public static enum State { NO_DATA, HAS_DATA, LOADING_DATA, NO_MATCHES };	

	protected LayoutWithState(LayoutId id, Place[] places) {
		super(id, places);
	}
	
	@Override
	protected void ensureOutletsLoaded() {
		super.ensureOutletsLoaded();
		
		if (!has(Place.STATUS)) throw new IllegalStateException("Layout with state must contain STATUS place");
	}
	
	public HasWidgets emptyHolder() { return outlet(Place.STATUS); }
	
	public HasWidgets loadingHolder() { return outlet(Place.STATUS); }
	
	public HasWidgets noMatchesHolder() { return outlet(Place.STATUS); }
	
	public abstract void prepare(State state);	

}
