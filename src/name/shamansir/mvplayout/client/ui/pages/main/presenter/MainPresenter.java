package name.shamansir.mvplayout.client.ui.pages.main.presenter;

import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = MainView.class)
public class MainPresenter extends LazyPresenter<MainPresenter.IMainView, MainEventBus> {

	public interface IMainView extends LazyView {
		
	}
	
	/*
    public void onNewPortal(final Portal portal, CanBuildLayout builder) {    	    	
    	Log.debug("//////// New page: " + portal.code());
    	
    	if (isAnonymous() && !portal.worksWithAnonymous()) {
    		NavigationUtils.navigateFirstPage();
    		return; // throw anonymous away
    	}
    	
    	if ((view.getCurrentPortal() != null) &&
    		view.getCurrentPortal().equals(portal) && 
    		(portal.getEventsCount() <= 1)) return; // no need in rebuilding layout
    	
    	view.beforePortalChange(portal);
    	
    	view.showLoadingScreen();
    	
    	if (!toolbarShown) {
	    	eventBus.toolbar(view.getToolbarHolder());
	    	toolbarShown = false;
	    }
    	
    	final State state = builder.layoutHasStates() ? DEFAULT_LAYOUT_STATE : null;
    	if (state != null) builder.prepare(state);
    	final Layout layoutBuilt = builder.build(state);
    	if (!portal.layout.equals(layoutBuilt.getLayoutId())) {
    		throw new IllegalArgumentException("Layout of passed portal (" + portal + " - " + portal.layout + ") does not matches " +
					                           "the passed layout built (" +  layoutBuilt.getLayoutId() + ")");    		
    	}
    	currentBuilder = builder;
    	
    	view.switchLayout(layoutBuilt);    	
    	
    	new Timer() {
			@Override public void run() {
				Log.debug("Will update footer..");
				view.updateFooter(portal.footerMustBeCollapsed());
			}
		}.schedule(2000);
		
		view.hideLoadingScreen();
		
		view.whenPortalChanged(portal);    	
	}
    
    @FromEventBus
    public void updateState(State state) {
    	if (currentBuilder == null) throw new IllegalStateException("Current layout builder is null, so I can not update state");
    	if (state == null) throw new IllegalArgumentException("Passed state is null");
    	if (!currentBuilder.layoutHasStates()) Log.warn("Current layout " + currentBuilder.getLayout() + " do not supports states, why call updateState()?");
    	if ((currentBuilder.curState() != null) && currentBuilder.curState().equals(state)) return;     	
    	
    	if (state != null) currentBuilder.prepare(state);
    	view.switchLayout(currentBuilder.build(state));		
    }
    
	@FromEventBus
	public void forceLayout(LayoutId layout) { view.switchLayout(LayoutFactory.getLayout(layout)); }
	
	@FromEventBus
	public void project(HasWidgets where, Widget what) { view.project(where, what); }
	
	@FromEventBus
	public void changeWidget(Place where, Widget widget) { view.changeWidget(where, widget); }
	
	@FromEventBus
	public void clearPage() { 
		view.clear();
	}

	 */
	
}
