/**
 * 
 */
package name.shamansir.mvplayout.client.id;

import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.structure.Group;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.PortalId;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum P implements PortalId {
    
    LINKS(L.SINGLE, null, "links"),
    VIEW_404(L.SINGLE, null, "404"),
    
    USERS_LIST(L.LIST, G.USER, "list"),
    USER_EDIT(L.EDIT, G.USER, "edit"),
    USER_SHOW(L.ITEM, G.USER, "show"),
    
    NEWS_LIST(L.LIST, G.NEWS, "list"),
    NEWS_EDIT(L.TRIPLE, G.NEWS, "edit"),
    NEWS_SHOW(L.ITEM, G.NEWS, "show"),
    
    COMPANY_LIST(L.SINGLE, G.COMPANY, "list"),
    COMPANY_SHOW(L.SINGLE, G.COMPANY, "show");
    
    public final Portal portal;
    
    private P(LayoutId layout, Group group, String event) {
        this.portal = new Portal(this, layout, group, event);
    }

    public static P by(Portal view) {
        return valueOf(view.id);
    }

    @Override
    public String id() { return name(); }
    
    public Portal portal() { return portal; };    

}
