/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp;

import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.structure.Place;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayout.util.StringUtils;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasScrolling;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public abstract class AMainView extends Composite implements IsMainView {
    
    private Layout currentLayout;
    private Portal currentPortal;
    
    private HasScrolling scrollable;
    private Panel layoutHolder;
    private Panel portalHolder; 
    
    protected abstract HasScrolling getScrollable();
    protected abstract Panel getLayoutHolder();
    protected abstract Panel getPortalHolder();
    
    public abstract void makeView();
    
    @Override
    public final void createView() {
        makeView();
        
        scrollable = getScrollable();
        layoutHolder = getLayoutHolder();
        portalHolder = getPortalHolder();
        
        if (scrollable != null) {
            scrollable.addScrollHandler(new ScrollHandler() {
                @Override public void onScroll(ScrollEvent event) {
                    AMainView.this.fireEvent(new PageScrollEvent(AMainView.this, scrollable, scrollable.getVerticalScrollPosition(), event));
                }
            });
        }
        
        Window.addResizeHandler(new ResizeHandler() {
            @Override public void onResize(ResizeEvent event) {
                AMainView.this.fireEvent(new PageResizeEvent(AMainView.this, event.getWidth(), event.getHeight(), event));
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
            portalHolder.removeStyleName(generatePortalCSSClassName(currentPortal));
        }
        
        currentPortal = to;
        portalHolder.addStyleName(generatePortalCSSClassName(currentPortal));
    }
    
    @Override
    public Portal getCurPortal() { return currentPortal; }
    
    @Override
    public Layout getCurLayout() { return currentLayout; }
    
    protected static String generatePortalCSSClassName(Portal portal) {
        return "portal portal-" + StringUtils.toCSS(portal.name()) + " " + 
               ((portal.group != null) ? "group-" + StringUtils.toCSS(portal.group.name())
                                       : "group-top-level");
    }
    
    protected static String generateLayoutCSSClassName(Layout layout) {
        return "page layout-" + StringUtils.toCSS(layout.id().name());
    }
    
    protected static String generatePlaceCSSClassName(Place place) {
        return "place place-" + StringUtils.toCSS(place.name());
    }

    @Override
    public void clear() { currentLayout.clear(); }
        
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
        
        private final AMainView pageView;
        private final int newWidth;
        private final int newHeight;
        private final ResizeEvent resizeEvent;
        
        public PageResizeEvent(AMainView pageView, int newWidth, int newHeight, ResizeEvent event) {
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
        
        public AMainView getPage() { return pageView; }
        public int getNewWidth() { return newWidth; }
        public int getNewHeight() { return newHeight; }
        public ResizeEvent getResizeEvent() { return resizeEvent; }
        
    }
    
    public static class PageScrollEvent extends GwtEvent<PageScrollListener> {
        
        private final AMainView pageView;
        private final int scrollPos;
        private final HasScrolling scrollable;      
        private final ScrollEvent scrollEvent;
        
        public PageScrollEvent(AMainView pageView, HasScrolling scrollable, int scrollPos, ScrollEvent scrollEvent) {
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
        
        public AMainView getPage() { return pageView; }
        public HasScrolling getScrollable() { return scrollable; }      
        public int getScrollPosition() { return scrollPos; }
        public ScrollEvent getScrollEvent() { return scrollEvent; }
        
    }

    /* @Override
    public void forceResize() {
        fireEvent(new PageResizeEvent(MainView.this, Window.getClientWidth(), Window.getClientHeight(), null));
    } */  
    

}
