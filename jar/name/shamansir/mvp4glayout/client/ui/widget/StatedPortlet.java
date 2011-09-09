package name.shamansir.mvp4glayout.client.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvp4glayout.client.mvp.state.IsStatedPortletView;
import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.state.State;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;

public abstract class StatedPortlet extends Portlet implements IsStatedPortletView {
    
    private Plugs plugs;
    private final Map<State, Plug> states = new HashMap<State, Plug>();	
    
    @UiConstructor
    public StatedPortlet() {
    	super();
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
    public Plugs getPlugs() {
        return plugs;
    }
    
    @Override 
    public Widget asWidget() { return plugs; }    
    
}
