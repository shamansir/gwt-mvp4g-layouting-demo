package name.shamansir.mvplayout.lib.ui.widget;

import name.shamansir.mvplayout.lib.mvp.IsPortalView;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class Portal extends Composite implements IsPortalView {
	
    private Plugs plugs;
    
	@UiConstructor
	public Portal() {
		super();
	}
	
	@Override
	protected void initWidget(Widget widget) {
        if (!(widget instanceof Plugs)) 
            throw new IllegalStateException("Plugs instance must be a root of Portal view");
        plugs = (Plugs)widget; 
        plugs.setContainer(this);
	}	
	
	public Plugs getPlugs() { return plugs; }

}
