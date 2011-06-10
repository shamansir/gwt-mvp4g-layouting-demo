/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.PortletPresenter;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.mvp4g.client.view.LazyView;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
 * </dl>
 *
 * <code>StatedPortalPresenter</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 28, 2011 10:16:17 PM 
 *
 */
public abstract class StatedPortletPresenter<V extends PluggableWithStates & LazyView, 
                                   E extends ChildEventBus> 
                                   extends PortletPresenter<V, E>
								   implements PlugsStates {
    
    protected StateDirector state;
    
    protected StatedPortletPresenter() {

    }
    
    @Override
    public void bindView() {
        state = new PortletStateDirector<V>(view, eventBus); 
    }

}
