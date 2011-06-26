package name.shamansir.mvplayout.client.ui.pages.main.view;

import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.pages.main.presenter.MainPresenter.IMainView;
import name.shamansir.mvplayout.client.ui.widget.Layout;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasScrolling;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public final class MainView extends Composite implements IMainView {

	@UiTemplate("MainView.ui.xml")
	interface EurekaPageUiBinder extends UiBinder<Widget, MainView> { }	
	private static EurekaPageUiBinder uiBinder = GWT.create(EurekaPageUiBinder.class);
	
	@UiField ScrollPanel scrollable;	
	@UiField FlowPanel layoutHolder;
	
	private Layout currentLayout;
	private Portal currentPortal;
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		scrollable.addScrollHandler(new ScrollHandler() {
			@Override public void onScroll(ScrollEvent event) {
				MainView.this.fireEvent(new PageScrollEvent(MainView.this, scrollable, scrollable.getVerticalScrollPosition(), event));
			}
		});
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override public void onResize(ResizeEvent event) {
				MainView.this.fireEvent(new PageResizeEvent(MainView.this, event.getWidth(), event.getHeight(), event));
			}
		});		
	}
	
    // ========================= Layouts =======================================
	
	@Override
	public void switchLayout(Layout to) {
		Log.debug("Switching to layout " + to.id());
		
		layoutHolder.clear();
		if (currentLayout != null) {
			layoutHolder.removeStyleName(generateLayoutCSSClassName(currentLayout));
		}
				
		currentLayout = to;		
		layoutHolder.add(currentLayout);
		layoutHolder.addStyleName(generateLayoutCSSClassName(currentLayout));
		
		for (Place place: currentLayout.places()) {
			Widget target = (Widget)currentLayout.outlet(place);
			target.addStyleName(generatePlaceCSSClassName(place));
		}
	}
	
	@Override
	public void beforePortalChange(Portal to) {
		if (currentLayout != null) {
			layoutHolder.removeStyleName(generatePortalCSSClassName(currentPortal));
		}
		
		currentPortal = to;
		layoutHolder.addStyleName(generatePortalCSSClassName(currentPortal));
	}
	
	@Override
	public Portal getCurPortal() { return currentPortal; }
	
	@Override
	public Layout getCurLayout() { return currentLayout; }
	
	protected static String generatePortalCSSClassName(Portal portal) {
		return "portal-" + portal.name().toLowerCase().replace('_', '-');
		// return "p-" + portal.name().toLowerCase().replace('_', '-') + " " + ((portal.group != null) ? "p-" + portal.group.code() : "p-top-level");
	}
	
	protected static String generateLayoutCSSClassName(Layout layout) {
		return "layout-" + layout.id().name().toLowerCase().replace('_', '-');
	}
	
	protected static String generatePlaceCSSClassName(Place place) {
		//if (Place.STATUS.equals(place)) return "l-status b-empty";
		return "place-" + place.name().toLowerCase().replace('_', '-');
	}	

	@Override
	public void clear() { currentLayout.clear(); }

    @Override
    public void whenPortalChanged(Portal portal) { }
    
    // ========================= Notification ==================================
    
    @Override
    public void showError(Throwable caught) {
    	Window.alert("Error: " + caught.getMessage());
    }
    
    // ========================= Scroll / Resize ===============================
    
	@Override
	public HandlerRegistration addPageScrollHandler(PageScrollListener handler) {
		return addHandler(handler, PageScrollEvent.TYPE);
	}
	
	@Override
	public HandlerRegistration addPageResizeHandler(PageResizeListener handler) {
		return addHandler(handler, PageResizeEvent.TYPE);
	}	

	public static interface PageScrollListener extends EventHandler { 
		public void onPageScroll(PageScrollEvent event); 
	}
	
	public static interface PageResizeListener extends EventHandler { 
		public void onPageResize(PageResizeEvent event); 
	}	
	
	public static class PageResizeEvent extends GwtEvent<PageResizeListener> {
		
		private final MainView pageView;
		private final int newWidth;
		private final int newHeight;
		private final ResizeEvent resizeEvent;
		
		public PageResizeEvent(MainView pageView, int newWidth, int newHeight, ResizeEvent event) {
			super();
			this.pageView = pageView;
			this.newWidth = newWidth;			
			this.newHeight = newHeight;
			this.resizeEvent = event;
		}

		public static final Type<PageResizeListener> TYPE = new Type<PageResizeListener>();
		
		@Override
		protected void dispatch(PageResizeListener handler) {
			handler.onPageResize(this);			
		}

		@Override
		public Type<PageResizeListener> getAssociatedType() {
			return TYPE;
		}
		
		public MainView getPage() { return pageView; }
		public int getNewWidth() { return newWidth; }
		public int getNewHeight() { return newHeight; }
		public ResizeEvent getResizeEvent() { return resizeEvent; }
		
	}
	
	public static class PageScrollEvent extends GwtEvent<PageScrollListener> {
		
		private final MainView pageView;
		private final int scrollPos;
		private final HasScrolling scrollable;		
		private final ScrollEvent scrollEvent;
		
		public PageScrollEvent(MainView pageView, HasScrolling scrollable, int scrollPos, ScrollEvent scrollEvent) {
			super();
			this.pageView = pageView;	
			this.scrollable = scrollable;
			this.scrollPos = scrollPos;
			this.scrollEvent = scrollEvent;
		}

		public static final Type<PageScrollListener> TYPE = new Type<PageScrollListener>();
		
		@Override
		protected void dispatch(PageScrollListener handler) {
			handler.onPageScroll(this);			
		}

		@Override
		public Type<PageScrollListener> getAssociatedType() {
			return TYPE;
		}
		
		public MainView getPage() { return pageView; }
		public HasScrolling getScrollable() { return scrollable; }		
		public int getScrollPosition() { return scrollPos; }
		public ScrollEvent getScrollEvent() { return scrollEvent; }
		
	}

	/* @Override
	public void forceResize() {
		fireEvent(new PageResizeEvent(MainView.this, Window.getClientWidth(), Window.getClientHeight(), null));
	} */  

}
