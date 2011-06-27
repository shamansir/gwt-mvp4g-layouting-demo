package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.widget.Layout;
import name.shamansir.mvplayout.lib.utils.ArrayUtils;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class LayoutWithState extends Layout {
    
    private final Place statusPlace;
	
	protected LayoutWithState(LayoutId id, Place[] places, Place statusPlace) {
		super(id, ArrayUtils.concat(places, new Place[] { statusPlace }));
		this.statusPlace = statusPlace;
	}
	
	@Override
	protected void ensureOutletsLoaded() {
		super.ensureOutletsLoaded();
		
		if (!has(statusPlace)) throw new IllegalStateException("Layout with state must contain STATUS place");
	}
	
	public HasWidgets holderFor(State state) { return outlet(statusPlace); }
		
	public abstract void prepare(State state);	

}
