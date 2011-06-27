package name.shamansir.mvplayout.lib.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortletView;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.structure.Place;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class StatedPortlet extends Composite implements IsStatedPortletView {
	
	private Plugs plugs;
	private Place place;
	private final Map<State, Plug> states = new HashMap<State, Plug>();	
	
	@UiConstructor
	public StatedPortlet() {
		
	}

	@Override
	protected void initWidget(Widget widget) {
	    if (!(widget instanceof Plugs)) 
	        throw new IllegalStateException("Plugs instance must be a root of StatedPortlet view");
	    plugs = (Plugs)widget; 
	    plugs.setContainer(this);
	}
	
	protected void register(Plug plug, State forState) {
		states.put(forState, plug);
	}
	
	@Override
	public Pluggable getMainView() {
		return states.get(State.HAS_DATA);
	}

	@Override
	public Pluggable getViewFor(State state) {
		return states.get(state);
	}
	
    @Override
    public boolean hasViewFor(State state) {
        return states.containsKey(state);
    }
    
    @Override
    public void setPlace(Place place) {
        this.place = place;
    }
    
    @Override
    public Place getPlace() {
        return place;
    }
    
    @Override
    public Plugs getPlugs() {
        return plugs;
    }
	
}
