/**
 * 
 */
package name.shamansir.mvplayout.client.id;

import name.shamansir.mvplayout.lib.ui.structure.Group;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.Portal;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum P {
    
    USERS_LIST(L.LIST, G.USER, "list"),
    USER_EDIT(L.EDIT, G.USER, "edit"),
    USER_SHOW(L.ITEM, G.USER, "show"),
    
    NEWS_LIST(L.LIST, G.NEWS, "list"),
    NEWS_EDIT(L.EDIT, G.NEWS, "edit"),
    NEWS_SHOW(L.ITEM, G.NEWS, "show"),
    
    COMPANY_LIST(L.LIST, G.COMPANY, "list"),
    COMPANY_EDIT(L.EDIT, G.COMPANY, "edit"),
    COMPANY_SHOW(L.ITEM, G.COMPANY, "show");
    
    public final Portal portal;
    
    private P(Portal portal) {
        this.portal = portal;
    }
    
    private P(LayoutId layout, Group group, String event) {
        this(new Portal(layout, group, event));
    }

    public static P by(Portal view) {
        return P.values()[view.id];
    }

}
