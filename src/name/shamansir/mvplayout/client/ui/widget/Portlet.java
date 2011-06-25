package name.shamansir.mvplayout.client.ui.widget;

import name.shamansir.mvplayout.client.ui.IsPortletView;
import name.shamansir.mvplayout.client.ui.Pluggable;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class Portlet extends Composite implements IsPortletView {
	
	private final Plug plug;
	
	@UiConstructor
	public Portlet(String alias) {
		super();
		
		plug = new Plug(alias);
		plug.setPortlet(this);
	}
	
	@Override
	protected void initWidget(Widget widget) {
		plug.add(widget);
		super.initWidget(plug);
	}
	
	@Override
	public Pluggable getMainView() { return plug; }

}
