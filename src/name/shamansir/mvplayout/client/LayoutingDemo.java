package name.shamansir.mvplayout.client;

import name.shamansir.mvplayout.client.id.G;
import name.shamansir.mvplayout.client.id.L;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.layout.LayoutEdit;
import name.shamansir.mvplayout.client.layout.LayoutItem;
import name.shamansir.mvplayout.client.layout.LayoutList;
import name.shamansir.mvplayout.client.pages.company.layout.CompanyLayoutBuilder;
import name.shamansir.mvplayout.client.pages.main.MainModule;
import name.shamansir.mvplayout.client.pages.news.layout.NewsLayoutBuilder;
import name.shamansir.mvplayout.client.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.LayoutBuilders;
import name.shamansir.mvplayout.lib.ui.Layouts;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.LayoutBuilders.LazyMaker;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

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
	    registerPortals();
	    registerLayouts();
	    registerBuilders();
	    
		Mvp4gModule mvpModule = (Mvp4gModule) GWT.create(MainModule.class);
    	mvpModule.createAndStartModule();
    	RootLayoutPanel.get().add((Widget) mvpModule.getStartView());
	}
	
	private void registerPortals() {
	    Portal.registerAll(P.values());
	}
	
	private void registerLayouts() {
        Layouts.register(L.LIST, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutList(); }});
        Layouts.register(L.EDIT, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutEdit(); }});
        Layouts.register(L.ITEM, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutItem(); }});	    
	}
	
    private void registerBuilders() {
        LayoutBuilders.register(G.USER, new LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new UserLayoutBuilder();
            }
        });
        LayoutBuilders.register(G.NEWS, new LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new NewsLayoutBuilder();
            }
        });
        LayoutBuilders.register(G.COMPANY, new LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new CompanyLayoutBuilder();
            }
        });        
        
    }
	
}
