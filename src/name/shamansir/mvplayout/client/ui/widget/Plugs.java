/**
 * 
 */
package name.shamansir.mvplayout.client.ui.widget;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import name.shamansir.mvplayout.client.ui.IsPortletView;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasWidgets;
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
public class Plugs extends Widget implements HasWidgets {
    
    // TODO: just store inner plugs in memory
    private Set<Plug> plugs = new HashSet<Plug>();
    
    @UiConstructor
    public Plugs() { }
    
    public void add(Widget w) {
        if (!(w instanceof Plug)) throw new IllegalStateException("Only Plug instances can be children of Plugs");
        plugs.add((Plug)w);
    }

    @Override
    public void clear() {
        plugs.clear();
    }
    
    public Set<Plug> getPlugs() {
        return plugs;
    }

    @Override
    public Iterator<Widget> iterator() {
        return new HashSet<Widget>(plugs).iterator();
    }

    @Override
    public boolean remove(Widget w) {
        if (!(w instanceof Plug)) throw new IllegalStateException("Only Plug instances can be children of Plugs");
        return plugs.remove((Plug)w);
    }

    public void setPortlet(IsPortletView statedPortlet) {
        for (Plug plug: plugs) {
            plug.setPortlet(statedPortlet);
        }
    }

}
