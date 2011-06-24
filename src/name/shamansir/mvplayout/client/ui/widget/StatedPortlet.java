package name.shamansir.mvplayout.client.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.state.IsStatedPortletView;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class StatedPortlet extends Composite implements IsStatedPortletView {
	
	private final FlowPanel holder;
	private final Map<State, Plug> plugs = new HashMap<State, Plug>();
	
	@UiConstructor
	public StatedPortlet() {
		this.holder = new FlowPanel() {
			
			@Override
			public void add(Widget w) {
				// TODO: just store inner plugs in memory
				if (!(w instanceof Plugs)) throw new IllegalStateException("Only Plugs instance can be a child of StatedPortlet");
				super.add(w);
			}
			
		};
	}

	@Override
	protected void initWidget(Widget widget) {
		holder.add(widget);
		super.initWidget(holder);
	}
	
	protected void register(Plug plug, State forState) {
		plugs.put(forState, plug);
	}
	
	@Override
	public Pluggable getMainView() {
		return plugs.get(State.HAS_DATA);
	}

	@Override
	public Pluggable getEmptyView() {
		return plugs.get(State.NO_DATA);
	}

	@Override
	public Pluggable getLoadingView() {
		return plugs.get(State.LOADING_DATA);
	}

	@Override
	public Pluggable getNoMatchesView() {
		return plugs.get(State.NO_MATCHES);
	}
	
}
