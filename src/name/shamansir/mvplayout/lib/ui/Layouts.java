package name.shamansir.mvplayout.lib.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

public class Layouts {
    
    public static abstract class LazyMaker {
        public abstract Layout create();
    }
    
    private static final Map<LayoutId, Layout> layouts = new HashMap<LayoutId, Layout>();
    private static final Map<LayoutId, LazyMaker> makers = new HashMap<LayoutId, LazyMaker>();
    
    private Layouts() { }
    
    public static Layout get(LayoutId id) {
    	if (layouts.containsKey(id)) return layouts.get(id);
    	if (makers.containsKey(id)) {
    	    final Layout layout = makers.get(id).create();		
    	    if (layout != null) layouts.put(id, layout);
    	    else throw new IllegalStateException("Layout with layout id " + id.name() + " was not created by Maker");
    	    return layout;
    	} else throw new IllegalStateException("No Layout Maker registered for layout with id " + id.name());
    }
    
    public static void register(LayoutId id, LazyMaker maker) {
        makers.put(id, maker);
    }

}
