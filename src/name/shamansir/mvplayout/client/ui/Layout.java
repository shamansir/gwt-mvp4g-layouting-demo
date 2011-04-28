package name.shamansir.mvplayout.client.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class Layout extends Composite {
	
	private final LayoutId id;
	private final Place[] places;
	protected final Map<Place, HasWidgets> panels = new HashMap<Place, HasWidgets>();
	
	private boolean panelsLoaded;	
	
	protected Layout(LayoutId id, Place[] places) {
		this.id = id;
		this.places = places;		
	}

	public LayoutId id() { return id; }
	
	protected abstract HasWidgets preparePanel(Place place) throws IndexOutOfBoundsException;	
	
	public final Place[] places() { return places; };	
	public final boolean has(Place place) { return panels.containsKey(place); };
	
	public final HasWidgets panel(Place place) { ensurePanelsLoaded(); return panels.get(place); }	
	protected final Map<Place, HasWidgets> panels() { ensurePanelsLoaded(); return panels; }
 	
	
	protected void ensurePanelsLoaded() {
		if (panelsLoaded) return;
		for (Place place: this.places) {
			this.panels.put(place, preparePanel(place));
		}
		panelsLoaded = true;
	}
	
	public void clear() {
		for (Place place: this.places) {
			panels.get(place).clear();
		}
	}	
	
}
