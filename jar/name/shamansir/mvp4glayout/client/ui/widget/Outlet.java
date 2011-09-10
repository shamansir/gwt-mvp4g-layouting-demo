/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvp4glayout.client.ui.IsOutlet;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
 * </dl>
 *
 * <code>Outlet</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 7, 2011 8:09:31 PM 
 *
 */
public class Outlet extends FlowPanel implements IsOutlet {
    
    @UiConstructor
    public Outlet() {  }
    
    public void add(Widget w) {
        if (!(w instanceof Plug)) throw new IllegalStateException("Only Plug instances can be children of Outlet");
        super.add(w);
    }    

}
