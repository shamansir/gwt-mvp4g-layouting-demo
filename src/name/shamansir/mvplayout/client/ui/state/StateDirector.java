package name.shamansir.mvplayout.client.ui.state;

import java.util.Collection;

import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

public final class StateDirector {
	
	private final ViewWithStates view;
	private final HandlesStates reactor;
	
	protected StateDirector(ViewWithStates view, HandlesStates reactor) {
		this.view = view;
		this.reactor = reactor;
	}
	
	public final void update() {
	    reactor.updateState(State.LOADING_DATA);
		view.prepareFor(State.LOADING_DATA);
	}
	
	public final void update(int dataSize) {
		if (dataSize > 0) {
		    reactor.updateState(State.HAS_DATA);
			view.prepareFor(State.HAS_DATA);
		} else {
		    reactor.updateState(State.NO_DATA);
			view.prepareFor(State.NO_DATA);
		}
	}	
	
	public final void update(Collection<?> collection) {
		update(collection.size());
	}
	
	public final <A> void update(Collection<A> collection, Filter<A> filter, MatchHandler<A> handler) {
		if (collection == null) {
			update(State.LOADING_DATA);
			return;
		} else if (collection.isEmpty()) { 
			update(State.NO_DATA);
			return;
		}

		int passed = 0;
		if (filter != null) for (A subject: collection) {
			final boolean matched = filter.matches(subject);
			if (handler != null) handler.handle(subject, matched);
			if (matched) passed++;
		}
		if (passed != 0) {
			update(State.HAS_DATA);
		} else {
			update(State.NO_MATCHES);
		}
	}
	
	public final <A> void update(Collection<A> collection, Filter<A> filter) {
		update(collection, filter, null);
	}
	
	public final void update(State to) {
		reactor.updateState(to);
		view.prepareFor(to);
	}	
	
	public final void loading() { update(State.LOADING_DATA); };
	public final void noData() { update(State.NO_DATA); };
	public final void noMatches() { update(State.NO_MATCHES); };	
	public final void gotData() { update(State.HAS_DATA); };
	public final void gotData(Collection<?> data) { update(data); };
	public final void gotData(int size) { update(size); };
	public final void check(Collection<?> data) { update(data); };	
	public final void check(int size) { update(size); };

	public static interface Filter<What> {
	    public boolean matches(What what);	    
	}
	
	public static interface MatchHandler<Type> {
	    public void handle(Type subject, boolean matched);
	}
	
}

