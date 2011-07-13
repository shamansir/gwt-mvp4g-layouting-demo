/**
 * 
 */
package name.shamansir.mvplayout.lib.mvp;

import java.util.ArrayList;
import java.util.List;

import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.PlugsContainer;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.state.HasStatesPanels;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.HandlerRegistration;
import com.mvp4g.client.presenter.LazyPresenter;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public abstract class AMainPresenter<V extends IsMainView, E extends IsMainEventBus>
                extends LazyPresenter<V, E> {
    
    public static final State DEFAULT_LAYOUT_STATE = State.LOADING_DATA; // TODO: move to builder
    
    private CanBuildLayout currentBuilder;
    protected UrlBuilder url = PortalUrlBuilder.get();
    
    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();    
    
    public void onNewPortal(final Portal portal, CanBuildLayout builder) {              
        Log.debug("New page: " + portal);
        
        /* if (isAnonymous() && !portal.worksWithAnonymous()) {
            NavigationUtils.navigateFirstPage();
            return; // throw anonymous away
        } */
        
        if ((view.getCurPortal() != null) &&
            view.getCurPortal().equals(portal)) return; // no need in rebuilding layout
        
        if (builder.built()) builder.reset();
        
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
            Layout layout = getActualLayout();
            PlugsContainer container = layout.getPluggable(where).getContainer();
            if (!(container instanceof HasStatesPanels)) throw new IllegalStateException("Container " + container + " at place " + where + " does not implements HasStatesPanels, so it can not change states");
            HasStatesPanels panels = (HasStatesPanels)container;
            // TODO: check if it is already in this state
            Pluggable plug = panels.getViewFor(state);
            plug.changeState(state);
            layout.plug(where, plug);
        }
    }
    
    public void plug(Place where, Pluggable what) {
        if (currentBuilder == null) throw new IllegalStateException("Current layout builder is null, so I can not plug widgets");
        getActualLayout().plug(where, what);
    }
    
    protected Layout getActualLayout() {
        return (!currentBuilder.built()) ? currentBuilder.getLayout() : view.getCurLayout();
    }
    
    public void clearPage() { view.clear(); }    
    
    public void onPortalNotFound(PortalNotFoundException pnfe) { 
        Log.debug("Portal not found: " + pnfe.getLocalizedMessage());
    };
    
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
