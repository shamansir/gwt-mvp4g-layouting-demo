/**
 * 
 */
package name.shamansir.mvplayout.client.id;

import name.shamansir.mvplayout.client.layout.LayoutEdit;
import name.shamansir.mvplayout.client.layout.LayoutItem;
import name.shamansir.mvplayout.client.layout.LayoutList;
import name.shamansir.mvplayout.lib.ui.Layouts;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum L implements LayoutId {
    LIST, EDIT, ITEM;

    @Override
    public String id() {
        return name();
    }
    
    static {
        Layouts.register(L.LIST, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutList(); }});
        Layouts.register(L.EDIT, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutEdit(); }});
        Layouts.register(L.ITEM, new Layouts.LazyMaker() {            
            @Override public Layout create() { return new LayoutItem(); }});        
    }
}
