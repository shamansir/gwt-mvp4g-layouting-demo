package name.shamansir.mvplayout.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

public abstract class LayoutBuilder<E extends ChildEventBus> {
	
	private static final Map<Portal, CanBuildLayout> cache = new HashMap<Portal, CanBuildLayout>();  
	
	public static interface CanBuildLayout {
		public void prepare(State state);		
		public Layout build(State state);
		
		public boolean layoutHasStates();		
		public LayoutId getLayout();
		public Portal getPortal();
		public State curState();
	}

	public final CanBuildLayout make(final Portal view, final E eventBus) {
		if (cache.containsKey(view)) return cache.get(view);
		final CanBuildLayout newBuilder = new CanBuildLayoutImpl(view, eventBus);
		cache.put(view, newBuilder);
		return newBuilder;
	}
	
	protected abstract boolean layout(Portal view, Layout layout, State state, Map<Place, HasWidgets> panels, E eventBus);
	
	protected class CanBuildLayoutImpl implements CanBuildLayout {

		private final Portal view;
		private final Layout layout;
		private final boolean hasStates;
		private final E eventBus;
		
		private State curState;
		
		protected CanBuildLayoutImpl(Portal view, E eventBus) {
			this.view = view;
			this.layout = Layouts.get(view.layout);
			this.hasStates = (this.layout instanceof LayoutWithState);
			this.eventBus = eventBus;
		}
		
		@Override
		public void prepare(State state) {
			Log.debug("Preparing to build layout " + layout.id() + " with state " + state + " (Layout has states: " + hasStates + ")");
			if (!hasStates)  throw new IllegalStateException("Layout " + layout.id() + " requires to support states to use prepare() method");
			if (state == null) throw new IllegalArgumentException("Passed state is null");
			if ((curState != null) && curState.equals(state)) throw new IllegalStateException("Current state is already prepared");
			curState = state;
			((LayoutWithState)layout).prepare(state); 
		}		
		
		@Override
		public Layout build(State state) {
			Log.debug("Building " + layout.id() + " with state " + state + " (Layout has states: " + hasStates + ") for view " + view);
			if ((state == null) && hasStates) throw new IllegalStateException("Layout " + layout.id() + " requires state to be set, use layoutHasStates() method of builder to determine is current layout requires states");
			if (((state != null) && ((curState != null) && !curState.equals(state)))) throw new IllegalStateException("Passed state " + state + " is not prepared, call prepare() before build()");
			if (!layout(view, layout, state, layout.panels(), eventBus)) {
				throw new IllegalStateException("Layout " + layout.id() + " was not built ");
			}
			curState = null;
			return layout;
		}

		@Override
		public boolean layoutHasStates() { return hasStates; }

		@Override
		public LayoutId getLayout() { return view.layout; }

		@Override
		public Portal getPortal() { return view; }

		@Override
		public State curState() { return curState; }
		
	}

}
