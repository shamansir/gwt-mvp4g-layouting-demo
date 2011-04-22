package name.shamansir.mvplayout.client.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.layout.LayoutEdit;
import name.shamansir.mvplayout.client.ui.layout.LayoutItem;
import name.shamansir.mvplayout.client.ui.layout.LayoutList;

public class Layouts {
	
	public enum Place { A, B, C, D, STATUS };
	
	public enum LayoutId { LIST, ITEM, EDIT };
	
	private static final Map<LayoutId, Layout> layouts = new HashMap<LayoutId, Layout>(); 	
	
	private Layouts() { }
	
	public static Layout get(LayoutId id) {
		if (layouts.containsKey(id)) return layouts.get(id);
		final Layout layout;		
		switch (id) {
			case LIST: layout = new LayoutList(); break;
			case ITEM: layout = new LayoutItem(); break;
			case EDIT: layout = new LayoutEdit(); break;
			default: layout = null;
		}
		if (layout != null) layouts.put(id, layout);
		else throw new IllegalArgumentException("Layout with layout id " + id.name() + " is not registered in LayoutFactory");
		return layout;
	}

}
