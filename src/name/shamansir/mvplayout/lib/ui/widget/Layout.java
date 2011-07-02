package name.shamansir.mvplayout.lib.ui.widget;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeEvent;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollEvent;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.ui.IsOutlet;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.utils.StringUtils;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Composite;

public abstract class Layout extends Composite implements PageScrollListener, PageResizeListener {
	
	private final LayoutId id;
	private final Place[] places;
	protected final Map<Place, IsOutlet> outlets = new HashMap<Place, IsOutlet>();
	private final Map<Place, Pluggable> plugged = new HashMap<Place, Pluggable>();
	
	private boolean outletsLoaded;	
	
	protected Layout(LayoutId id, Place[] places) {
		this.id = id;
		this.places = places;		
	}

	public LayoutId id() { return id; }
	
	protected abstract IsOutlet prepareOutlet(Place place) throws IndexOutOfBoundsException;	
	
	public final Place[] places() { return places; };	
	public final boolean has(Place place) { ensureOutletsLoaded(); return outlets.containsKey(place); };
	
	public final IsOutlet outlet(Place place) { ensureOutletsLoaded(); return outlets.get(place); }	
	public final Map<Place, IsOutlet> outlets() { ensureOutletsLoaded(); return outlets; }
 	
	protected void ensureOutletsLoaded() {
		if (outletsLoaded) return;
		for (Place place: this.places) {
			this.outlets.put(place, prepareOutlet(place));
		}
		outletsLoaded = true;
	}
	
	public void clear() {
		for (Place place: this.places) {
			outlets.get(place).clear();
		}
		plugged.clear();		
	}	
	
	public void onPageScroll(PageScrollEvent event) {
		Log.debug("Layout " + id + " handled scrolling event");
	}
	
	public void onPageResize(PageResizeEvent event) { 
		Log.debug("Layout " + id + " handled resize event");
	}
	
    public void plug(Place where, Pluggable what) {
        if (!has(where)) throw new IllegalStateException("Layout " + id + " has no place " + where);
        // if it is the same place and the same pluggable there, just refresh it
        // else, put a new pluggable in corresponding outlet
        if (plugged.containsKey(where) &&
            plugged.get(where).id().equals(what.id())) {            
            plugged.get(where).refresh(); // just refresh, nothing else
        } else {
            final IsOutlet outlet = outlet(where);
            if (!outlet.isVisible()) Log.warn("Outlet at place " + where + " is not visible, but you are plugging " + what + " there"); 
            outlet.clear();
            outlet.add(what.asWidget());
            what.asWidget().addStyleName(generatePlugCSSClassName(what));
            plugged.put(where, what);            
            Log.debug("Plugged " + what.id() + " into outlet at place " + where);
            what.setPlace(where);
            what.refresh();            
        }
    }
    
    public Pluggable getPluggable(Place where) {
        return plugged.get(where);
    }

    // FIXME: make protected
    public void whenBuilt() {
        
    }
    
    protected static String generatePlugCSSClassName(Pluggable plug) {
        return "plug plug-" + StringUtils.toCSS(plug.id());
    }
}
