/**
 * 
 */
package name.shamansir.mvplayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.widget</dd>
 * </dl>
 *
 * <code>Plugs</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 17, 2011 8:05:02 AM 
 *
 */
public class Plugs extends FlowPanel {
    
    // TODO: just store inner plugs in memory
    
    @UiConstructor
    public Plugs() { }
    
    public void add(Widget w) {
        if (!(w instanceof Plug)) throw new IllegalStateException("Only Plug instances can be children of Plugs");
        super.add(w);
    }

}
