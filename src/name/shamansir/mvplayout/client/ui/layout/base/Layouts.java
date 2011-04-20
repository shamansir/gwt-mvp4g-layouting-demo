package name.shamansir.mvplayout.client.ui.layout.base;

import name.shamansir.mvplayout.client.ui.layout.LayoutEdit;
import name.shamansir.mvplayout.client.ui.layout.LayoutItem;
import name.shamansir.mvplayout.client.ui.layout.LayoutList;

public class Layouts {
	
	public enum Place { A, B, C, D, STATUS };
	
	public enum LayoutId { LIST, ITEM, EDIT };
	
	private Layouts() { }
	
	public static Layout get(LayoutId layout) {
		switch (layout) {
			case LIST: return new LayoutList();
			case ITEM: return new LayoutItem();
			case EDIT: return new LayoutEdit();
		}
		return null;
	}

}
