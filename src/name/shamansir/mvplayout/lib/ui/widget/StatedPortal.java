package name.shamansir.mvplayout.lib.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortalView;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.state.State;

import com.google.gwt.uibinder.client.UiConstructor;

public abstract class StatedPortal extends Portal implements IsStatedPortalView {
    
    private final Map<State, Plug> states = new HashMap<State, Plug>();	
    
    @UiConstructor
    public StatedPortal() {
    	super();
    }
    
    protected void register(Plug plug, State forState) {
        if (forState.equals(State.HAS_DATA)) 
            throw new IllegalArgumentException("No plug for HAS_DATA state required, if you are using StatedPortal");
    	states.put(forState, plug);
    }
    
    @Override
    public Pluggable getViewFor(State state) {
    	return states.get(state);
    }
    
    @Override
    public boolean hasViewFor(State state) {
        return states.containsKey(state);
    }
        
}
