package name.shamansir.mvplayout.client.ui.pages.main.presenter;

import name.shamansir.mvplayout.client.exception.PortalNotFoundException;
import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.allen_sauer.gwt.log.client.Log;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = MainView.class)
public class MainPresenter extends LazyPresenter<MainPresenter.IMainView, MainEventBus> {
    
    public static final State DEFAULT_LAYOUT_STATE = State.LOADING_DATA;
    
    private CanBuildLayout currentBuilder;

	public interface IMainView extends LazyView {

        public Portal getCurPortal();

        public void clear();
        public void switchLayout(Layout to);
        public void beforePortalChange(Portal portal);        
        public void whenPortalChanged(Portal portal);

		public void showError(Throwable caught);
		
	}
	
    public void onNewPortal(final Portal portal, CanBuildLayout builder) {    	    	
    	Log.debug("//////// New page: " + portal);
    	
    	/* if (isAnonymous() && !portal.worksWithAnonymous()) {
    		NavigationUtils.navigateFirstPage();
    		return; // throw anonymous away
    	} */
    	
    	if ((view.getCurPortal() != null) &&
    		view.getCurPortal().equals(portal)) return; // no need in rebuilding layout
    	
    	view.beforePortalChange(portal);
    	
    	final State state = builder.layoutHasStates() ? DEFAULT_LAYOUT_STATE : null;
    	if (state != null) builder.prepare(state);
    	final Layout layoutBuilt = builder.build(state);
    	if (!portal.layout.equals(layoutBuilt.id())) {
    		throw new IllegalArgumentException("Layout of passed portal (" + portal + " - " + portal.layout + ") does not matches " +
					                           "the passed layout built (" +  layoutBuilt.id() + ")");    		
    	}
    	currentBuilder = builder;
    	
    	view.switchLayout(layoutBuilt);    	
    	
		view.whenPortalChanged(portal);    	
	}
    
    public void updateState(State state) {
    	if (currentBuilder == null) throw new IllegalStateException("Current layout builder is null, so I can not update state");
    	if (state == null) throw new IllegalArgumentException("Passed state is null");
    	if (!currentBuilder.layoutHasStates()) Log.warn("Current layout " + currentBuilder.getLayout() + " do not supports states, why call updateState()?");
    	if ((currentBuilder.curState() != null) && currentBuilder.curState().equals(state)) return;     	
    	
    	if (state != null) currentBuilder.prepare(state);
    	// FIXME: implement
    	//currentBuilder.update(state, view.getPlugs());		
    }
    
    public void clearPage() { view.clear(); }    
    
    public void onPortalNotFound(PortalNotFoundException pnfe) { 
    	Log.debug("Portal not found: " + pnfe.getLocalizedMessage());
    };
    
    public void onHandle(Throwable caught) {
    	view.showError(caught);
    }
    
    /* public void forceLayout(LayoutId layout) { view.switchLayout(LayoutFactory.getLayout(layout)); } */
	
}
