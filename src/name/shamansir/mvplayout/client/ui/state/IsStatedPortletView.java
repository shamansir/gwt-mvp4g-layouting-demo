/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.HasMainView;
import name.shamansir.mvplayout.client.ui.IsPortletView;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.state</dd>
 * </dl>
 *
 * <code>IsStatedPortletView</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 16, 2011 8:26:14 PM 
 *
 */
public interface IsStatedPortletView extends IsPortletView, HasMainView,
                                             HasStatesPanels, HandlesStateChange {

}
