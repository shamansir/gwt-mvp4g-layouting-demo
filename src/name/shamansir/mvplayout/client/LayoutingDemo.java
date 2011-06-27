package name.shamansir.mvplayout.client;

import name.shamansir.mvplayout.client.pages.main.MainModule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.Mvp4gModule;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LayoutingDemo implements EntryPoint {
	
	public void onModuleLoad() {
		Mvp4gModule mvpModule = (Mvp4gModule) GWT.create(MainModule.class);
    	mvpModule.createAndStartModule();
    	RootLayoutPanel.get().add((Widget) mvpModule.getStartView());
	}
	
}
