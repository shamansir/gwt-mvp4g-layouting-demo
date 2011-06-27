/**
 * 
 */
package name.shamansir.mvplayout.client.id;

import name.shamansir.mvplayout.client.pages.company.layout.CompanyLayoutBuilder;
import name.shamansir.mvplayout.client.pages.news.layout.NewsLayoutBuilder;
import name.shamansir.mvplayout.client.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.LayoutBuilders;
import name.shamansir.mvplayout.lib.ui.LayoutBuilders.LazyMaker;
import name.shamansir.mvplayout.lib.ui.structure.Group;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum G implements Group {
    USER, NEWS, COMPANY;

    @Override
    public String id() {
        return name();
    }
    
    static {
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
