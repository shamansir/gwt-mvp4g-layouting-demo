package name.shamansir.mvp4glayoutdemo.client;

import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.LayoutBuilders;
import name.shamansir.mvp4glayout.client.ui.Layouts;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayoutdemo.client.id.G;
import name.shamansir.mvp4glayoutdemo.client.id.L;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutDouble;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutEdit;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutItem;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutList;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutSingle;
import name.shamansir.mvp4glayoutdemo.client.layout.LayoutTriple;
import name.shamansir.mvp4glayoutdemo.client.page.company.layout.CompanyLayoutBuilder;
import name.shamansir.mvp4glayoutdemo.client.page.main.MainModule;
import name.shamansir.mvp4glayoutdemo.client.page.news.layout.NewsLayoutBuilder;
import name.shamansir.mvp4glayoutdemo.client.page.user.layout.UserLayoutBuilder;

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
        Layouts.register(L.SINGLE, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutSingle(); }});
        Layouts.register(L.DOUBLE, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutDouble(); }});        
        Layouts.register(L.TRIPLE, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutTriple(); }});        
    }
    
    private void registerBuilders() {
        LayoutBuilders.register(G.USER, new LayoutBuilders.LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new UserLayoutBuilder();
            }
        });
        LayoutBuilders.register(G.NEWS, new LayoutBuilders.LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new NewsLayoutBuilder();
            }
        });
        LayoutBuilders.register(G.COMPANY, new LayoutBuilders.LazyMaker() {            
            @Override public LayoutBuilder<?> create() { 
                return new CompanyLayoutBuilder();
            }
        });        
        
    }
    
}
