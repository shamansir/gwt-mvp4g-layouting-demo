package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class LayoutWithState extends Layout {
	
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
