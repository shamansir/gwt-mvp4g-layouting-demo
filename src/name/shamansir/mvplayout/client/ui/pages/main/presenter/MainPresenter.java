package name.shamansir.mvplayout.client.ui.pages.main.presenter;

import java.util.ArrayList;
import java.util.List;

import name.shamansir.mvplayout.client.exception.PortalNotFoundException;
import name.shamansir.mvplayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView.PageResizeListener;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView.PageScrollListener;
import name.shamansir.mvplayout.client.ui.state.HandlesStateChange;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.Layout;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.HandlerRegistration;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = MainView.class)
public class MainPresenter extends LazyPresenter<MainPresenter.IMainView, MainEventBus> {
    
    public static final State DEFAULT_LAYOUT_STATE = State.LOADING_DATA;
    
    private CanBuildLayout currentBuilder;
    
    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    
	public interface IMainView extends LazyView {

        public Portal getCurPortal();

        public void clear();
        public void switchLayout(Layout to);
        public void beforePortalChange(Portal portal);        
        public void whenPortalChanged(Portal portal);
        
		public void showError(Throwable caught);

		public HandlerRegistration addPageResizeHandler(PageResizeListener handler);
		public HandlerRegistration addPageScrollHandler(PageScrollListener handler);

		Layout getCurLayout();
		
	}
	
    public void onNewPortal(final Portal portal, CanBuildLayout builder) {    	    	
    	Log.debug("New page: " + portal);
    	
    	/* if (isAnonymous() && !portal.worksWithAnonymous()) {
    		NavigationUtils.navigateFirstPage();
    		return; // throw anonymous away
    	} */
    	
    	if ((view.getCurPortal() != null) &&
    		view.getCurPortal().equals(portal)) return; // no need in rebuilding layout
    	
    	unregisterHandlers();
    	
    	view.beforePortalChange(portal);
    	
    	final State state = builder.layoutHasStates() ? DEFAULT_LAYOUT_STATE : null;
    	
    	currentBuilder = builder;
    	final Layout layoutBuilt = builder.build(state);
    	if (!portal.layout.equals(layoutBuilt.id())) {
    		throw new IllegalArgumentException("Layout of passed portal (" + portal + " - " + portal.layout + ") does not matches " +
					                           "the passed layout built (" +  layoutBuilt.id() + ")");    		
    	}
    	// currentBuilder = builder; // TODO: may be it must be here
    	
    	view.switchLayout(layoutBuilt);
    	subscribePageEvents(layoutBuilt);
    	
		view.whenPortalChanged(portal);    	
	}
    
    public void updateState(Place where, State state) {
    	if (currentBuilder == null) throw new IllegalStateException("Current layout builder is null, so I can not update state");
    	if (!currentBuilder.built()) throw new IllegalStateException("Current layout builder has not built a layout yet");
    	if (state == null) throw new IllegalArgumentException("Passed state is null");
    	if (where == null) { // update whole page
        	if (!currentBuilder.layoutHasStates()) Log.warn("Current layout " + currentBuilder.layoutId() + " do not supports states, please ensure you do what you want");
        	if ((currentBuilder.curState() != null) && currentBuilder.curState().equals(state)) return;
        	//currentBuilder.reset();
        	currentBuilder.build(state); // just changes layout inside it, do not re-renders anything that not required
    	} else {
    	    Pluggable portlet = getActualLayout().getPluggable(where);
    	    if (!(portlet instanceof HandlesStateChange)) throw new IllegalStateException("Portlet at place " + where + " does not implements HandlesStateChange, so it can not change states");
    	    ((HandlesStateChange)portlet).prepareFor(state);
    	}
    }
    
    public void plug(Place where, Pluggable what) {
    	if (currentBuilder == null) throw new IllegalStateException("Current layout builder is null, so I can not plug widgets");
    	getActualLayout().plug(where, what);
    }
    
    protected Layout getActualLayout() {
    	return (currentBuilder.built()) ? currentBuilder.getLayout() : view.getCurLayout();
    }
    
    public void clearPage() { view.clear(); }    
    
    public void onPortalNotFound(PortalNotFoundException pnfe) { 
    	Log.debug("Portal not found: " + pnfe.getLocalizedMessage());
    };
    
    public void onStart() {
        eventBus.users(null);
    }
    
    public void onHandle(Throwable caught) {
    	view.showError(caught);
    }
    
    /* public void forceLayout(LayoutId layout) { view.switchLayout(LayoutFactory.getLayout(layout)); } */
    
    public void subscribePageScroll(PageScrollListener listener) {
    	handlers.add(view.addPageScrollHandler(listener));
    }

    public void subscribePageResize(PageResizeListener listener) {
    	handlers.add(view.addPageResizeHandler(listener));
    }    
    
    protected void unregisterHandlers() {
    	for (HandlerRegistration handler: handlers) {
    		handler.removeHandler();
    	}
    	handlers.clear();
    }
    
    protected void subscribePageEvents(Layout layout) {
    	handlers.add(view.addPageResizeHandler(layout));
    	handlers.add(view.addPageScrollHandler(layout));
    }    
    
}
