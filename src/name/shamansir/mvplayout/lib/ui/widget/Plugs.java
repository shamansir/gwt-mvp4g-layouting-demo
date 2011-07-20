/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.widget;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import name.shamansir.mvplayout.lib.ui.PlugsContainer;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.lib.ui.widget</dd>
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
    
    public void add(Plug p) {
        plugs.add(p);
    }
    
    public void add(Widget w) {
        if (!(w instanceof Plug)) throw new IllegalStateException("Only Plug instances can be children of Plugs");
        add((Plug)w);
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

    public void setContainer(PlugsContainer portlet) {
        for (Plug plug: plugs) {
            plug.setContainer(portlet);
        }
        /*if (portlet instanceof Widget) {
            setElement(((Widget)portlet).getElement());
        } else {
            throw new IllegalStateException("Portlet must be a widget");
        }*/
    }    

}
