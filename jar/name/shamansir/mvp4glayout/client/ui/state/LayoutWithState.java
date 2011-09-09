package name.shamansir.mvp4glayout.client.ui.state;

import name.shamansir.mvp4glayout.client.ui.structure.LayoutId;
import name.shamansir.mvp4glayout.client.ui.structure.Place;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayout.util.ArrayUtils;
import name.shamansir.mvp4glayout.util.StringUtils;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class LayoutWithState extends Layout implements ChangesState {
    
    private final Place statusPlace;
    private State state;
    
    protected LayoutWithState(LayoutId id, Place[] places, Place statusPlace) {
    	super(id, ArrayUtils.concat(places, new Place[] { statusPlace }));
    	this.statusPlace = statusPlace;
    }
    
    @Override
    protected void ensureOutletsLoaded() {
    	super.ensureOutletsLoaded();
    	
    	// FIXME: check it once
    	if (!outlets.containsKey(statusPlace)) throw new IllegalStateException("Layout with state must contain STATUS place");
    }
    
    public HasWidgets holderFor(State state) { return outlet(statusPlace); }
    	
    public abstract void prepare(State state);
    
    @Override
    public State getState() { return this.state; }

    @Override
    public final void changeState(State state) {
        if (this.state != null) removeStyleName(generateStateCSSClassName(this.state));
        this.state = state;
        prepare(this.state);
        addStyleName(generateStateCSSClassName(this.state));
    }    
    
    protected static String generateStateCSSClassName(State state) {
        return "state-" + StringUtils.toCSS(state.name());
    }    

}
