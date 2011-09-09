package name.shamansir.mvp4glayout.client.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvp4glayout.client.mvp.ChildEventBus;
import name.shamansir.mvp4glayout.client.mvp.IsMainEventBus;
import name.shamansir.mvp4glayout.client.ui.state.LayoutWithState;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.structure.LayoutId;
import name.shamansir.mvp4glayout.client.ui.structure.PortalId;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;

import com.allen_sauer.gwt.log.client.Log;

public abstract class LayoutBuilder<E extends ChildEventBus> {
    
    private static final Map<Portal, CanBuildLayout> cache = new HashMap<Portal, CanBuildLayout>();  
    
    public static interface CanBuildLayout {
        
        public static final State DEFAULT_LAYOUT_STATE = State.LOADING_DATA;
        
    	public Layout build(State state);
    	public boolean built();
    	
    	public void reset();
    	
    	public boolean layoutHasStates();		
    	public LayoutId layoutId();
    	public Layout getLayout();
    	public Portal getPortal();
    	public State curState();
    	
    }

    public final CanBuildLayout make(final Portal view, final E eventBus) {
    	if (cache.containsKey(view)) {
    		final CanBuildLayout builder = cache.get(view);
    		//builder.reset(); // it will be manually reseted in handler, if required
    		return builder;
    	} else {
    		final CanBuildLayout newBuilder = new CanLayoutModuleView(view, eventBus);
    		cache.put(view, newBuilder);
    		return newBuilder;
    	}
    }
    
    protected abstract boolean layout(Portal view, Layout layout, State state, E eventBus);
    
    protected static abstract class CanBuildStatedLayout implements CanBuildLayout {	    
    	
    	protected final Portal view;
    	protected final Layout layout;
    	protected final boolean hasStates;
    	
    	protected boolean built;
    	protected State curState;
    	
    	protected CanBuildStatedLayout(Portal view) {
    		this.view = view;
    		this.layout = Layouts.get(view.layout);
    		this.hasStates = (this.layout instanceof LayoutWithState);
    	}
    	
    	protected void prepare(State state) {
    		Log.debug("Preparing to build layout " + layout.id() + " with state " + state + " (Layout has states: " + hasStates + ")");
    		if (!hasStates)  throw new IllegalStateException("Layout " + layout.id() + " requires to support states to use prepare() method");
    		if (state == null) throw new IllegalArgumentException("Passed state is null");
    		if ((curState != null) && curState.equals(state)) throw new IllegalStateException("Current state is already prepared");
    		curState = state;
    		((LayoutWithState)layout).changeState(state); 
    	}		
    	
    	@Override
    	public final Layout build(State state) {		    
    		Log.debug("Building layout " + layout.id() + " with state " + state + " (Layout has states: " + hasStates + ") for view " + view);
    		if (built && (hasStates && curState.equals(state))) throw new IllegalStateException("Layout " + layout.id() + " was already built for state " + state + " with this builder, reset it or use another builder");
    		if (built && (!hasStates && (state == null))) throw new IllegalStateException("Layout " + layout.id() + " was already built with this builder, reset it or use another builder");
    		if (hasStates && (state == null)) throw new IllegalStateException("Layout " + layout.id() + " requires state to be set, use layoutHasStates() method of builder to determine is current layout requires states");
    		if (state != null) prepare(state);			
    		if (!doLayout(view, layout, state)) {
    			throw new IllegalStateException("Layout " + layout.id() + " was not built ");
    		}
    		built = true;
    		layout.whenBuilt();
    		return layout;
    	}
    	
    	protected abstract boolean doLayout(Portal view, Layout layout, State state);
    	
    	@Override
    	public boolean built() { return built; }		

    	@Override
    	public boolean layoutHasStates() { return hasStates; }

    	@Override
    	public LayoutId layoutId() { return view.layout; }
    	
    	@Override
    	public Layout getLayout() { return layout; }		

    	@Override
    	public Portal getPortal() { return view; }

    	@Override
    	public State curState() { return curState; }
    	
    	@Override
    	public void reset() {
    		curState = null;
    		built = false;
    		layout.clear();
    	}
    	
    	
    }
    
    protected class CanLayoutModuleView extends CanBuildStatedLayout {

    	protected final E eventBus;
    	
    	protected CanLayoutModuleView(Portal view, E eventBus) {
    		super(view);
    		this.eventBus = eventBus;
    	}

    	@Override
    	protected boolean doLayout(Portal view, Layout layout, State state) {
    	    boolean result = layout(view, layout, state, eventBus);
    	    //TODO: if (result) eventBus.layoutBuilt(view);
    		return result;
    	}
    	
    	
    }
    
    /**
     * Use like:
     * 
     * <pre>
     * new CanLayoutMainView(Portal.VIEW_404, eventBus) {
     *
     *    	protected boolean doLayout(Portal view, Layout layout, State state,
     *				Map<Place, HasWidgets> outlets) {
     *			outlets.get(Place.MAIN).clear();
     *			outlets.get(Place.MAIN).add(dialog);
     *			return true;
     *		}
     *		
     *	}.run();
     * </pre>
     *
     */
    public static abstract class CanLayoutMainView extends CanBuildStatedLayout implements Runnable {
    
    	protected final IsMainEventBus eventBus;		
    	
    	public CanLayoutMainView(PortalId view, IsMainEventBus eventBus) {
    		super(Portal.fromId(view));
    		this.eventBus = eventBus;
    	}
    	
    	@Override
    	public final void run() {
    		eventBus.newPortal(view, this);
    		//TODO: eventBus.layoutBuilt(view);
    	}

    }
}
