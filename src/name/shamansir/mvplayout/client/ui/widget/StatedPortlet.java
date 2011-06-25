package name.shamansir.mvplayout.client.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.state.IsStatedPortletView;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class StatedPortlet extends Composite implements IsStatedPortletView {
	
	private Plugs plugs;
	private final Map<State, Plug> states = new HashMap<State, Plug>();
	
	@UiConstructor
	public StatedPortlet() {
		
	}

	@Override
	protected void initWidget(Widget widget) {
	    if (!(widget instanceof Plugs)) 
	        throw new IllegalStateException("Plugs instance must be a root of StatedPortlet view");
	    plugs = (Plugs)widget; 
	    plugs.setPortlet(this);
	}
	
	protected void register(Plug plug, State forState) {
		states.put(forState, plug);
	}
	
	@Override
	public Pluggable getMainView() {
		return states.get(State.HAS_DATA);
	}

	@Override
	public Pluggable getEmptyView() {
		return states.get(State.NO_DATA);
	}

	@Override
	public Pluggable getLoadingView() {
		return states.get(State.LOADING_DATA);
	}

	@Override
	public Pluggable getNoMatchesView() {
		return states.get(State.NO_MATCHES);
	}
	
	@Override
	public void prepareFor(State to) {
	    
	}
	
}
