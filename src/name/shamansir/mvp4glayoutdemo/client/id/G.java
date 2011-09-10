/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.id;

import name.shamansir.mvp4glayout.client.ui.structure.Group;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public enum G implements Group {
    USER, NEWS, COMPANY;

    @Override
    public String id() { return name(); }
    
}
