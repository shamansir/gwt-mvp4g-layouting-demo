package name.shamansir.mvplayout.client.ui.widget;

import name.shamansir.mvplayout.client.ui.IsPortletView;
import name.shamansir.mvplayout.client.ui.Pluggable;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class Portlet extends Composite implements IsPortletView {
	
    private Plug plug;
    private Plugs plugs;
    
	@UiConstructor
	public Portlet() {
		super();
	}
	
	@Override
	protected void initWidget(Widget widget) {
        if (!(widget instanceof Plug)) 
            throw new IllegalStateException("Plug instance must be a root of Portlet view");
        plug = (Plug)widget;
        plug.setContainer(this);
        plugs.add(plug);
		super.initWidget(plug);
	}
	
	@Override
	public Pluggable getMainView() { return plug; }
	
	public Plugs getPlugs() { return plugs; }

}
