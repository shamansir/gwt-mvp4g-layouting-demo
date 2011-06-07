package name.shamansir.mvplayout.client.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

import com.google.gwt.user.client.ui.Composite;

public abstract class Layout extends Composite {
	
	private final LayoutId id;
	private final Place[] places;
	protected final Map<Place, IsOutlet> outlets = new HashMap<Place, IsOutlet>();
	
	private boolean outletsLoaded;	
	
	protected Layout(LayoutId id, Place[] places) {
		this.id = id;
		this.places = places;		
	}

	public LayoutId id() { return id; }
	
	protected abstract IsOutlet prepareOutlet(Place place) throws IndexOutOfBoundsException;	
	
	public final Place[] places() { return places; };	
	public final boolean has(Place place) { return outlets.containsKey(place); };
	
	public final IsOutlet outlet(Place place) { ensureOutletsLoaded(); return outlets.get(place); }	
	protected final Map<Place, IsOutlet> outlets() { ensureOutletsLoaded(); return outlets; }
 	
	
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
	}	
	
}
