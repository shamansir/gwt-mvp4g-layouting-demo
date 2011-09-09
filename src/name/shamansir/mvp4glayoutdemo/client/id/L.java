/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.id;

import name.shamansir.mvp4glayout.client.ui.structure.LayoutId;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum L implements LayoutId {
    LIST, EDIT, ITEM, SINGLE, DOUBLE, TRIPLE;

    @Override
    public String id() { return name(); }
    
}
